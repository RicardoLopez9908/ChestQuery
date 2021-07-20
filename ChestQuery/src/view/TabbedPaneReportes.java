package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

import controller.Controlador;

public class TabbedPaneReportes extends JTabbedPane {

	private Controlador controlador;

	private JPanel pnl_consultarReportes;
	private JPanel pnl_norteConsultarReportes;
	private JPanel pnl_centroConsultarReportes;
	private String datosTablaReportes[][];
	private JTable tablaReportes;

	private JPanel pnl_surConsultarReportes;


	private DefaultTableModel modeloTablaProveedores;


	// -------------------------------------------------
	private static Font FUENTE = new Font("dialog", 4, 18);
	String[] columnasTablaReportes = new String[] { "Instrucción realizada", "Nombre de usuario", "Numero de usuario", "Fecha" };

	public TabbedPaneReportes(Controlador controlador) {
		this.controlador = controlador;
		this.componentesConsultarReportes();
		this.agregarPaneles();
	}

	private void agregarPaneles() {
		this.addTab("Consultar reportes", pnl_consultarReportes);
	}
	
	private void componentesConsultarReportes() {

		pnl_consultarReportes = new JPanel(new BorderLayout());

		GridBagLayout gbl_norteConsultarReportes = new GridBagLayout();
		gbl_norteConsultarReportes.columnWidths = new int[] { 0, 40, 0, 40, 0 }; // COLUMNAS
		gbl_norteConsultarReportes.rowHeights = new int[] { 70, 0, 70 }; // FILAS
		pnl_norteConsultarReportes = new JPanel(gbl_norteConsultarReportes);

		BorderLayout bl_centroConsultarReportes = new BorderLayout();
		pnl_centroConsultarReportes = new JPanel(bl_centroConsultarReportes);

		GridBagLayout gbl_surConsultarReportes = new GridBagLayout();
		gbl_surConsultarReportes.columnWidths = new int[] { 0, 0, 40, 0, 0 }; // COLUMNAS
		gbl_surConsultarReportes.rowHeights = new int[] { 40, 0, 40 }; // FILAS
		pnl_surConsultarReportes = new JPanel(gbl_surConsultarReportes);

		pnl_consultarReportes.add(pnl_norteConsultarReportes, BorderLayout.NORTH);
		pnl_consultarReportes.add(pnl_centroConsultarReportes, BorderLayout.CENTER);
		pnl_consultarReportes.add(pnl_surConsultarReportes, BorderLayout.SOUTH);

		// -----------------NORTE----------------------------

		JComboBox<String> columnasReportes = new JComboBox<String>(columnasTablaReportes);
		columnasReportes.setFont(FUENTE);
		GridBagConstraints gbc_columnasReportes = new GridBagConstraints();
		gbc_columnasReportes.gridx = 0;
		gbc_columnasReportes.gridy = 1;
		pnl_norteConsultarReportes.add(columnasReportes, gbc_columnasReportes);

		JTextField txt_reporteBuscado = new JTextField();
		GridBagConstraints gbc_reporteBuscado = new GridBagConstraints();
		gbc_reporteBuscado.gridx = 2;
		gbc_reporteBuscado.gridy = 1;
		txt_reporteBuscado.setColumns(20);
		txt_reporteBuscado.setFont(FUENTE);
	
		pnl_norteConsultarReportes.add(txt_reporteBuscado, gbc_reporteBuscado);

		JButton btn_buscar = new JButton("Buscar");
		GridBagConstraints gbc_buscar = new GridBagConstraints();
		gbc_buscar.gridx = 4;
		gbc_buscar.gridy = 1;

		pnl_norteConsultarReportes.add(btn_buscar, gbc_buscar);

		// -----------------CENTRO---------------------------
		datosTablaReportes = controlador.getDatosTablaReportes();
		modeloTablaProveedores = new DefaultTableModel();
		modeloTablaProveedores.setDataVector(datosTablaReportes, columnasTablaReportes);
		tablaReportes = new JTable(modeloTablaProveedores) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dimensionarTablas(tablaReportes);

		TableRowSorter<DefaultTableModel> filtroTablaReportes = new TableRowSorter<DefaultTableModel>(
				modeloTablaProveedores);
		tablaReportes.setRowSorter(filtroTablaReportes);

		btn_buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((columnasReportes.getSelectedIndex() != -1) && (txt_reporteBuscado.getText() != null)) {
					filtroTablaReportes.setRowFilter(
							RowFilter.regexFilter(txt_reporteBuscado.getText(), columnasReportes.getSelectedIndex()));

				}
			}
		});

		JScrollPane scp_tablaReportes = new JScrollPane();
		scp_tablaReportes.setViewportView(tablaReportes);

		pnl_centroConsultarReportes.add(scp_tablaReportes);

		// -----------------SUR------------------------------

		JButton btn_actualizar = new JButton("Actualizar");
		btn_actualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					actualizarDatosTabla();
					dimensionarTablas(tablaReportes);
			}
		});
		GridBagConstraints gbc_actualizar = new GridBagConstraints();
		gbc_actualizar.gridx = 3;
		gbc_actualizar.gridy = 1;
		pnl_surConsultarReportes.add(btn_actualizar, gbc_actualizar);

	}

	private void actualizarDatosTabla() {
		modeloTablaProveedores.setRowCount(0);
		datosTablaReportes = controlador.getDatosTablaReportes();
		modeloTablaProveedores.setDataVector(datosTablaReportes, columnasTablaReportes);

	}
	
	private void dimensionarTablas(JTable tabla) {
		TableColumnModel columnModel = tabla.getColumnModel();

		columnModel.getColumn(0).setPreferredWidth(450); 	//instruccion
		columnModel.getColumn(1).setPreferredWidth(75); 	//usuario
		columnModel.getColumn(2).setPreferredWidth(15); 	//numero de usuario
		columnModel.getColumn(3).setPreferredWidth(75); 	//fecha
	}

	public void mostrar() {
		this.setEnabled(true);
		this.setVisible(true);
	}

	public void ocultar() {
		this.setEnabled(false);
		;
		this.setVisible(false);
	}

}
