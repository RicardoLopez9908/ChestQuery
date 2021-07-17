package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import controller.Controlador;
import model.Cliente;

public class TabbedPaneClientes extends JTabbedPane {

	private Controlador controlador;

	private JPanel pnl_agregarCliente;
	private JPanel pnl_centroAgregarCliente;
	private JPanel pnl_surAgregarCliente;

	private JPanel pnl_eliminarCliente;
	private JPanel pnl_norteEliminarCliente;
	private JPanel pnl_centroEliminarCliente;
	private String datosTablaClientes[][];
	private JTable tablaClientes1;
	private JTable tablaClientes2;

	private JPanel pnl_surEliminarCliente;

	private JPanel pnl_consultarCliente;
	private JPanel pnl_norteConsultarCliente;
	private JPanel pnl_centroConsultarCliente;
	private JPanel pnl_surConsultarCliente;

	private DefaultTableModel modeloTablaClientes;

	private Cliente anterioresDatosCliente;
	private Cliente nuevosDatosCliente;

	// -------------------------------------------------
	private static Font FUENTE = new Font("dialog", 4, 18);
	String[] columnasTablaClientes = new String[] { "Nombre", "Dirección", "Telefono", "Mail" };

	public TabbedPaneClientes(Controlador controlador) {
		this.controlador = controlador;
		this.componentesAgregarClientes();
		this.componentesEliminarClientes();
		this.componentesConsultarClientes();
		this.agregarPaneles();
	}

	private void agregarPaneles() {
		this.addTab("Agregar Cliente", pnl_agregarCliente);

		this.addTab("Eliminar Cliente", pnl_eliminarCliente);

		this.addTab("Consultar Cliente", pnl_consultarCliente);

	}

	private void componentesAgregarClientes() {

		// --------------AGREGAR CLIENTE--------------------
		pnl_agregarCliente = new JPanel(new BorderLayout());

		GridBagLayout gbl_centroAgregarCliente = new GridBagLayout();
		gbl_centroAgregarCliente.columnWidths = new int[] { 0, 100, 70, 100, 0 }; // COLUMNAS
		gbl_centroAgregarCliente.rowHeights = new int[] { 100, 100, 100, 100,100 }; // FILAS
		pnl_centroAgregarCliente = new JPanel(gbl_centroAgregarCliente);

		GridBagLayout gbl_surAgregarCliente = new GridBagLayout();
		gbl_surAgregarCliente.columnWidths = new int[] { 0, 0, 40, 0, 0 }; // COLUMNAS
		gbl_surAgregarCliente.rowHeights = new int[] { 0, 0, 70 }; // FILAS
		pnl_surAgregarCliente = new JPanel(gbl_surAgregarCliente);

		pnl_agregarCliente.add(pnl_centroAgregarCliente, BorderLayout.CENTER);
		pnl_agregarCliente.add(pnl_surAgregarCliente, BorderLayout.SOUTH);

		// CENTRO:

		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setFont(FUENTE);
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.gridx = 1;
		gbc_nombre.gridy = 1;
		pnl_centroAgregarCliente.add(lbl_nombre, gbc_nombre);

		JTextField txt_nombre = new JTextField();
		txt_nombre.setFont(FUENTE);
		txt_nombre.setColumns(15);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		pnl_centroAgregarCliente.add(txt_nombre, gbc_txtNombre);

		JLabel lbl_direccion = new JLabel("Dirección:");
		lbl_direccion.setFont(FUENTE);
		GridBagConstraints gbc_direccion = new GridBagConstraints();
		gbc_direccion.anchor = GridBagConstraints.EAST;
		gbc_direccion.gridx = 1;
		gbc_direccion.gridy = 2;
		pnl_centroAgregarCliente.add(lbl_direccion, gbc_direccion);

		JTextField txt_direccion = new JTextField();
		txt_direccion.setFont(FUENTE);
		txt_direccion.setColumns(15);
		GridBagConstraints gbc_txtDireccion = new GridBagConstraints();
		gbc_txtDireccion.gridx = 3;
		gbc_txtDireccion.gridy = 2;
		pnl_centroAgregarCliente.add(txt_direccion, gbc_txtDireccion);

		JLabel lbl_telefono = new JLabel("Telefono:");
		lbl_telefono.setFont(FUENTE);
		GridBagConstraints gbc_telefono = new GridBagConstraints();
		gbc_telefono.anchor = GridBagConstraints.EAST;
		gbc_telefono.gridx = 1;
		gbc_telefono.gridy = 3;
		pnl_centroAgregarCliente.add(lbl_telefono, gbc_telefono);

		JTextField txt_telefono = new JTextField();
		txt_telefono.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		txt_telefono.setFont(FUENTE);
		txt_telefono.setColumns(15);
		GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
		gbc_txtTelefono.gridx = 3;
		gbc_txtTelefono.gridy = 3;
		pnl_centroAgregarCliente.add(txt_telefono, gbc_txtTelefono);

		JLabel lbl_mail = new JLabel("Mail:");
		lbl_mail.setFont(FUENTE);
		GridBagConstraints gbc_mail = new GridBagConstraints();
		gbc_mail.anchor = GridBagConstraints.EAST;
		gbc_mail.gridx = 1;
		gbc_mail.gridy = 4;
		pnl_centroAgregarCliente.add(lbl_mail, gbc_mail);

		JTextField txt_mail = new JTextField();
		txt_mail.setFont(FUENTE);
		txt_mail.setColumns(15);
		GridBagConstraints gbc_txtMail = new GridBagConstraints();
		gbc_txtMail.gridx = 3;
		gbc_txtMail.gridy = 4;
		pnl_centroAgregarCliente.add(txt_mail, gbc_txtMail);

		// SUR:
		JButton btn_cancelar = new JButton("Cancelar");
		GridBagConstraints gbc_cancelar = new GridBagConstraints();
		gbc_cancelar.anchor = GridBagConstraints.EAST;
		gbc_cancelar.gridx = 1;
		gbc_cancelar.gridy = 1;
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_nombre.setText("");
				txt_direccion.setText("");
				txt_telefono.setText("");
				txt_mail.setText("");
			}
		});
		pnl_surAgregarCliente.add(btn_cancelar, gbc_cancelar);

		JButton btn_agregar = new JButton("Agregar");
		btn_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!((txt_nombre.getText().isEmpty()) || (txt_direccion.getText().isEmpty())
						|| (txt_telefono.getText().isEmpty()) || (txt_mail.getText().isEmpty()))) {
					controlador.agregarCliente(txt_nombre.getText(), txt_direccion.getText(),
							Long.parseLong(txt_telefono.getText()), txt_mail.getText());
					actualizarDatosTabla();
					txt_nombre.setText("");
					txt_direccion.setText("");
					txt_telefono.setText("");
					txt_mail.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Por favor, completar todas las filas", "ChestQuery", 1);
				}
			}
		});

		GridBagConstraints gbc_agregar = new GridBagConstraints();
		gbc_agregar.gridx = 3;
		gbc_agregar.gridy = 1;
		pnl_surAgregarCliente.add(btn_agregar, gbc_agregar);

	}

	private void componentesEliminarClientes() {
		// --------------ELIMINAR USUARIOS--------------------

		pnl_eliminarCliente = new JPanel(new BorderLayout());

		GridBagLayout gbl_norteEliminarCliente = new GridBagLayout();
		gbl_norteEliminarCliente.columnWidths = new int[] { 0, 40, 0, 40, 0 }; // COLUMNAS
		gbl_norteEliminarCliente.rowHeights = new int[] { 70, 0, 70 }; // FILAS
		pnl_norteEliminarCliente = new JPanel(gbl_norteEliminarCliente);

		BorderLayout bl_centroEliminarCliente = new BorderLayout();
		pnl_centroEliminarCliente = new JPanel(bl_centroEliminarCliente);

		GridBagLayout gbl_surEliminarCliente = new GridBagLayout();
		gbl_surEliminarCliente.columnWidths = new int[] { 0, 0, 40, 0, 0 }; // COLUMNAS
		gbl_surEliminarCliente.rowHeights = new int[] { 40, 0, 40 }; // FILAS
		pnl_surEliminarCliente = new JPanel(gbl_surEliminarCliente);

		pnl_eliminarCliente.add(pnl_norteEliminarCliente, BorderLayout.NORTH);
		pnl_eliminarCliente.add(pnl_centroEliminarCliente, BorderLayout.CENTER);
		pnl_eliminarCliente.add(pnl_surEliminarCliente, BorderLayout.SOUTH);

		// -----------------NORTE----------------------------

		JComboBox<String> columnasCliente = new JComboBox<String>(columnasTablaClientes);
		columnasCliente.setFont(FUENTE);
		GridBagConstraints gbc_columnasCliente = new GridBagConstraints();
		gbc_columnasCliente.gridx = 0;
		gbc_columnasCliente.gridy = 1;
		pnl_norteEliminarCliente.add(columnasCliente, gbc_columnasCliente);

		JTextField txt_clienteBuscado = new JTextField();
		GridBagConstraints gbc_clienteBuscado = new GridBagConstraints();
		gbc_clienteBuscado.gridx = 2;
		gbc_clienteBuscado.gridy = 1;
		txt_clienteBuscado.setColumns(20);
		txt_clienteBuscado.setFont(FUENTE);
		txt_clienteBuscado.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_norteEliminarCliente.add(txt_clienteBuscado, gbc_clienteBuscado);

		JButton btn_buscar = new JButton("Buscar");
		GridBagConstraints gbc_buscar = new GridBagConstraints();
		gbc_buscar.gridx = 4;
		gbc_buscar.gridy = 1;

		pnl_norteEliminarCliente.add(btn_buscar, gbc_buscar);

		// -----------------CENTRO---------------------------
		datosTablaClientes = controlador.getDatosTablaClientes();
		modeloTablaClientes = new DefaultTableModel();
		modeloTablaClientes.setDataVector(datosTablaClientes, columnasTablaClientes);
		modeloTablaClientes.fireTableDataChanged();
		tablaClientes1 = new JTable(modeloTablaClientes) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		TableRowSorter<DefaultTableModel> filtroTablaClientes = new TableRowSorter<DefaultTableModel>(
				modeloTablaClientes);
		tablaClientes1.setRowSorter(filtroTablaClientes);

		btn_buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((columnasCliente.getSelectedIndex() != -1) && (txt_clienteBuscado.getText() != null)) {
					filtroTablaClientes.setRowFilter(
							RowFilter.regexFilter(txt_clienteBuscado.getText(), columnasCliente.getSelectedIndex()));

				}
			}
		});

		JScrollPane scp_tablaClientes = new JScrollPane();
		scp_tablaClientes.setViewportView(tablaClientes1);

		pnl_centroEliminarCliente.add(scp_tablaClientes);

		// -----------------SUR------------------------------

		JButton btn_borrar = new JButton("Borrar");
		btn_borrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tablaClientes1.getSelectedRow() != -1) {
					controlador.borrarCliente((String) tablaClientes1.getValueAt(tablaClientes1.getSelectedRow(), 0), // nombre
							(String) tablaClientes1.getValueAt(tablaClientes1.getSelectedRow(), 1), // direccion
							Long.parseLong((String) tablaClientes1.getValueAt(tablaClientes1.getSelectedRow(), 2)), // telefono
							(String) tablaClientes1.getValueAt(tablaClientes1.getSelectedRow(), 3) // mail
					);
					actualizarDatosTabla();

				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila", "ChestQuery", 1);
				}
			}
		});
		GridBagConstraints gbc_borrar = new GridBagConstraints();
		gbc_borrar.gridx = 3;
		gbc_borrar.gridy = 1;
		pnl_surEliminarCliente.add(btn_borrar, gbc_borrar);

	}

	private void componentesConsultarClientes() {
		// --------------CONSULTAR CLIENTE--------------------

		pnl_consultarCliente = new JPanel(new BorderLayout());

		GridBagLayout gbl_norteConsultarCliente = new GridBagLayout();
		gbl_norteConsultarCliente.columnWidths = new int[] { 0, 40, 0, 40, 0 }; // COLUMNAS
		gbl_norteConsultarCliente.rowHeights = new int[] { 30, 0, 40 }; // FILAS
		pnl_norteConsultarCliente = new JPanel(gbl_norteConsultarCliente);

		BorderLayout bl_centroConsultarCliente = new BorderLayout();
		pnl_centroConsultarCliente = new JPanel(bl_centroConsultarCliente);

		GridBagLayout gbl_surConsultarCliente = new GridBagLayout();
		gbl_surConsultarCliente.columnWidths = new int[] { 5, 0, 20, 0, 20, 0, 150, 0, 5 }; // COLUMNAS
		gbl_surConsultarCliente.rowHeights = new int[] { 40, 0, 10, 0, 10, 0, 10, 0, 30 }; // FILAS
		pnl_surConsultarCliente = new JPanel(gbl_surConsultarCliente);

		pnl_consultarCliente.add(pnl_norteConsultarCliente, BorderLayout.NORTH);
		pnl_consultarCliente.add(pnl_centroConsultarCliente, BorderLayout.CENTER);
		pnl_consultarCliente.add(pnl_surConsultarCliente, BorderLayout.SOUTH);

		// -----------------NORTE----------------------------

		JComboBox<String> columnasCliente = new JComboBox<String>(columnasTablaClientes);
		columnasCliente.setFont(FUENTE);
		GridBagConstraints gbc_columnaCliente = new GridBagConstraints();
		gbc_columnaCliente.gridx = 0;
		gbc_columnaCliente.gridy = 1;
		pnl_norteConsultarCliente.add(columnasCliente, gbc_columnaCliente);

		JTextField txt_clienteBuscado = new JTextField();
		GridBagConstraints gbc_clienteBuscado = new GridBagConstraints();
		gbc_clienteBuscado.gridx = 2;
		gbc_clienteBuscado.gridy = 1;
		txt_clienteBuscado.setColumns(20);
		txt_clienteBuscado.setFont(FUENTE);
		txt_clienteBuscado.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_norteConsultarCliente.add(txt_clienteBuscado, gbc_clienteBuscado);

		JButton btn_buscar = new JButton("Buscar");
		GridBagConstraints gbc_buscar = new GridBagConstraints();
		gbc_buscar.gridx = 4;
		gbc_buscar.gridy = 1;
		pnl_norteConsultarCliente.add(btn_buscar, gbc_buscar);

		// -----------------CENTRO---------------------------
		tablaClientes2 = new JTable(modeloTablaClientes) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		TableRowSorter<DefaultTableModel> filtroTablaClientes = new TableRowSorter<DefaultTableModel>(
				modeloTablaClientes);
		tablaClientes2.setRowSorter(filtroTablaClientes);

		btn_buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((columnasCliente.getSelectedIndex() != -1) && (txt_clienteBuscado.getText() != null)) {
					filtroTablaClientes.setRowFilter(
							RowFilter.regexFilter(txt_clienteBuscado.getText(), columnasCliente.getSelectedIndex()));

				}
			}
		});

		JScrollPane scp_tablaClientes = new JScrollPane();
		scp_tablaClientes.setViewportView(tablaClientes2);

		pnl_centroConsultarCliente.add(scp_tablaClientes);

		// -----------------SUR------------------------------

		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setFont(FUENTE);
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.gridx = 1;
		gbc_nombre.gridy = 1;
		pnl_surConsultarCliente.add(lbl_nombre, gbc_nombre);

		JTextField txt_nombre = new JTextField();
		txt_nombre.setFont(FUENTE);
		txt_nombre.setColumns(15);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		pnl_surConsultarCliente.add(txt_nombre, gbc_txtNombre);

		JLabel lbl_direccion = new JLabel("Dirección:");
		lbl_direccion.setFont(FUENTE);
		GridBagConstraints gbc_direccion = new GridBagConstraints();
		gbc_direccion.anchor = GridBagConstraints.EAST;
		gbc_direccion.gridx = 1;
		gbc_direccion.gridy = 3;
		pnl_surConsultarCliente.add(lbl_direccion, gbc_direccion);

		JTextField txt_direccion = new JTextField();
		txt_direccion.setFont(FUENTE);
		txt_direccion.setColumns(15);
		GridBagConstraints gbc_txtDireccion = new GridBagConstraints();
		gbc_txtDireccion.gridx = 3;
		gbc_txtDireccion.gridy = 3;
		pnl_surConsultarCliente.add(txt_direccion, gbc_txtDireccion);

		JLabel lbl_telefono = new JLabel("Telefono:");
		lbl_telefono.setFont(FUENTE);
		GridBagConstraints gbc_telefono = new GridBagConstraints();
		gbc_telefono.anchor = GridBagConstraints.EAST;
		gbc_telefono.gridx = 1;
		gbc_telefono.gridy = 5;
		pnl_surConsultarCliente.add(lbl_telefono, gbc_telefono);

		JTextField txt_telefono = new JTextField();
		txt_telefono.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		txt_telefono.setFont(FUENTE);
		txt_telefono.setColumns(15);
		GridBagConstraints gbc_txtTelefono = new GridBagConstraints();
		gbc_txtTelefono.gridx = 3;
		gbc_txtTelefono.gridy = 5;
		pnl_surConsultarCliente.add(txt_telefono, gbc_txtTelefono);

		JLabel lbl_mail = new JLabel("Mail:");
		lbl_mail.setFont(FUENTE);
		GridBagConstraints gbc_mail = new GridBagConstraints();
		gbc_mail.anchor = GridBagConstraints.EAST;
		gbc_mail.gridx = 1;
		gbc_mail.gridy = 7;
		pnl_surConsultarCliente.add(lbl_mail, gbc_mail);

		JTextField txt_mail = new JTextField();
		txt_mail.setFont(FUENTE);
		txt_mail.setColumns(15);
		GridBagConstraints gbc_txtMail = new GridBagConstraints();
		gbc_txtMail.gridx = 3;
		gbc_txtMail.gridy = 7;
		pnl_surConsultarCliente.add(txt_mail, gbc_txtMail);

		JButton btn_actualizar = new JButton("Actualizar");
		GridBagConstraints gbc_actualizar = new GridBagConstraints();
		gbc_actualizar.gridx = 7;
		gbc_actualizar.gridy = 7;
		pnl_surConsultarCliente.add(btn_actualizar, gbc_actualizar);

		btn_actualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevosDatosCliente = new Cliente(txt_nombre.getText(), txt_direccion.getText(),
						Long.parseLong(txt_telefono.getText()), txt_mail.getText());
				if (!anterioresDatosCliente.equals(nuevosDatosCliente)) {
					controlador.actualizarCliente(anterioresDatosCliente, nuevosDatosCliente);
					txt_nombre.setText("");
					txt_direccion.setText("");
					txt_telefono.setText("");
					txt_mail.setText("");

					actualizarDatosTabla();

				}
			}
		});

		btn_buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((columnasCliente.getSelectedIndex() != -1) && (!txt_clienteBuscado.getText().equals(""))) {
					filtroTablaClientes.setRowFilter(
							RowFilter.regexFilter(txt_clienteBuscado.getText(), columnasCliente.getSelectedIndex()));

				}
			}
		});

		JButton btn_seleccionar = new JButton("Seleccionar");
		GridBagLayout gbl_pnlExtra = new GridBagLayout();
		gbl_pnlExtra.columnWidths = new int[] { 0, 0, 0 };
		JPanel pnlExtra = new JPanel(gbl_pnlExtra);
		GridBagConstraints gbc_btnSeleccionar = new GridBagConstraints();
		gbc_btnSeleccionar.gridx = 1;
		pnlExtra.add(btn_seleccionar, gbc_btnSeleccionar);
		btn_seleccionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tablaClientes2.getSelectedRow() != -1) {
					txt_nombre.setText((String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 0));
					txt_direccion.setText((String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 1));
					txt_telefono.setText((String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 2));
					txt_mail.setText((String) tablaClientes2.getValueAt(tablaClientes2.getSelectedRow(), 3));

					anterioresDatosCliente = new Cliente(txt_nombre.getText(), txt_direccion.getText(),
							Long.parseLong(txt_telefono.getText()), txt_mail.getText());

				}
			}
		});
		pnl_centroConsultarCliente.add(pnlExtra, BorderLayout.SOUTH);

	}

	private void actualizarDatosTabla() {
		modeloTablaClientes.setRowCount(0);
		datosTablaClientes = controlador.getDatosTablaClientes();
		modeloTablaClientes.setDataVector(datosTablaClientes, columnasTablaClientes);

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
