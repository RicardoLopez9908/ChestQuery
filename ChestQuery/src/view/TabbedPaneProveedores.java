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
import model.Proveedor;

public class TabbedPaneProveedores extends JTabbedPane {

	private Controlador controlador;

	private JPanel pnl_agregarProveedor;
	private JPanel pnl_centroAgregarProveedor;
	private JPanel pnl_surAgregarProveedor;

	private JPanel pnl_eliminarProveedor;
	private JPanel pnl_norteEliminarProveedor;
	private JPanel pnl_centroEliminarProveedor;
	private String datosTablaProveedores[][];
	private JTable tablaProveedores1;
	private JTable tablaProveedores2;

	private JPanel pnl_surEliminarProveedor;

	private JPanel pnl_consultarProveedor;
	private JPanel pnl_norteConsultarProveedor;
	private JPanel pnl_centroConsultarProveedor;
	private JPanel pnl_surConsultarProveedor;

	private DefaultTableModel modeloTablaProveedores;

	private Proveedor anterioresDatosProveedor;
	private Proveedor nuevosDatosProveedor;

	// -------------------------------------------------
	private static Font FUENTE = new Font("dialog", 4, 18);
	String[] columnasTablaProveedores = new String[] { "Nombre", "Dirección", "Telefono", "Mail" };

	public TabbedPaneProveedores(Controlador controlador) {
		this.controlador = controlador;
		this.componentesAgregarProveedores();
		this.componentesEliminarProveedores();
		this.componentesConsultarProveedores();
		this.agregarPaneles();
	}

	private void agregarPaneles() {
		this.addTab("Agregar Proveedor", pnl_agregarProveedor);

		this.addTab("Eliminar Proveedor", pnl_eliminarProveedor);

		this.addTab("Consultar Proveedor", pnl_consultarProveedor);

	}

	private void componentesAgregarProveedores() {

		// --------------AGREGAR PROVEEDOR--------------------
		pnl_agregarProveedor = new JPanel(new BorderLayout());

		GridBagLayout gbl_centroAgregarProveedor = new GridBagLayout();
		gbl_centroAgregarProveedor.columnWidths = new int[] { 0, 100, 70, 100, 0 }; // COLUMNAS
		gbl_centroAgregarProveedor.rowHeights = new int[] { 100, 100, 100, 100,100 }; // FILAS
		pnl_centroAgregarProveedor = new JPanel(gbl_centroAgregarProveedor);

		GridBagLayout gbl_surAgregarProveedor = new GridBagLayout();
		gbl_surAgregarProveedor.columnWidths = new int[] { 0, 0, 40, 0, 0 }; // COLUMNAS
		gbl_surAgregarProveedor.rowHeights = new int[] { 0, 0, 70 }; // FILAS
		pnl_surAgregarProveedor = new JPanel(gbl_surAgregarProveedor);

		pnl_agregarProveedor.add(pnl_centroAgregarProveedor, BorderLayout.CENTER);
		pnl_agregarProveedor.add(pnl_surAgregarProveedor, BorderLayout.SOUTH);

		// CENTRO:

		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setFont(FUENTE);
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.gridx = 1;
		gbc_nombre.gridy = 1;
		pnl_centroAgregarProveedor.add(lbl_nombre, gbc_nombre);

		JTextField txt_nombre = new JTextField();
		txt_nombre.setFont(FUENTE);
		txt_nombre.setColumns(15);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		txt_nombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_centroAgregarProveedor.add(txt_nombre, gbc_txtNombre);

		JLabel lbl_direccion = new JLabel("Dirección:");
		lbl_direccion.setFont(FUENTE);
		GridBagConstraints gbc_direccion = new GridBagConstraints();
		gbc_direccion.anchor = GridBagConstraints.EAST;
		gbc_direccion.gridx = 1;
		gbc_direccion.gridy = 2;
		pnl_centroAgregarProveedor.add(lbl_direccion, gbc_direccion);

		JTextField txt_direccion = new JTextField();
		txt_direccion.setFont(FUENTE);
		txt_direccion.setColumns(15);
		GridBagConstraints gbc_txtDireccion = new GridBagConstraints();
		gbc_txtDireccion.gridx = 3;
		gbc_txtDireccion.gridy = 2;
		txt_direccion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_centroAgregarProveedor.add(txt_direccion, gbc_txtDireccion);

		JLabel lbl_telefono = new JLabel("Telefono:");
		lbl_telefono.setFont(FUENTE);
		GridBagConstraints gbc_telefono = new GridBagConstraints();
		gbc_telefono.anchor = GridBagConstraints.EAST;
		gbc_telefono.gridx = 1;
		gbc_telefono.gridy = 3;
		pnl_centroAgregarProveedor.add(lbl_telefono, gbc_telefono);

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
		pnl_centroAgregarProveedor.add(txt_telefono, gbc_txtTelefono);

		JLabel lbl_mail = new JLabel("Mail:");
		lbl_mail.setFont(FUENTE);
		GridBagConstraints gbc_mail = new GridBagConstraints();
		gbc_mail.anchor = GridBagConstraints.EAST;
		gbc_mail.gridx = 1;
		gbc_mail.gridy = 4;
		pnl_centroAgregarProveedor.add(lbl_mail, gbc_mail);

		JTextField txt_mail = new JTextField();
		txt_mail.setFont(FUENTE);
		txt_mail.setColumns(15);
		GridBagConstraints gbc_txtMail = new GridBagConstraints();
		gbc_txtMail.gridx = 3;
		gbc_txtMail.gridy = 4;
		pnl_centroAgregarProveedor.add(txt_mail, gbc_txtMail);

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
		pnl_surAgregarProveedor.add(btn_cancelar, gbc_cancelar);

		JButton btn_agregar = new JButton("Agregar");
		btn_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!((txt_nombre.getText().isEmpty()) || (txt_direccion.getText().isEmpty())
						|| (txt_telefono.getText().isEmpty()) || (txt_mail.getText().isEmpty()))) {
					controlador.agregarProveedor(txt_nombre.getText(), txt_direccion.getText(),
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
		pnl_surAgregarProveedor.add(btn_agregar, gbc_agregar);

	}

	private void componentesEliminarProveedores() {
		// --------------ELIMINAR USUARIOS--------------------

		pnl_eliminarProveedor = new JPanel(new BorderLayout());

		GridBagLayout gbl_norteEliminarProveedor = new GridBagLayout();
		gbl_norteEliminarProveedor.columnWidths = new int[] { 0, 40, 0, 40, 0 }; // COLUMNAS
		gbl_norteEliminarProveedor.rowHeights = new int[] { 70, 0, 70 }; // FILAS
		pnl_norteEliminarProveedor = new JPanel(gbl_norteEliminarProveedor);

		BorderLayout bl_centroEliminarProveedor = new BorderLayout();
		pnl_centroEliminarProveedor = new JPanel(bl_centroEliminarProveedor);

		GridBagLayout gbl_surEliminarProveedor = new GridBagLayout();
		gbl_surEliminarProveedor.columnWidths = new int[] { 0, 0, 40, 0, 0 }; // COLUMNAS
		gbl_surEliminarProveedor.rowHeights = new int[] { 40, 0, 40 }; // FILAS
		pnl_surEliminarProveedor = new JPanel(gbl_surEliminarProveedor);

		pnl_eliminarProveedor.add(pnl_norteEliminarProveedor, BorderLayout.NORTH);
		pnl_eliminarProveedor.add(pnl_centroEliminarProveedor, BorderLayout.CENTER);
		pnl_eliminarProveedor.add(pnl_surEliminarProveedor, BorderLayout.SOUTH);

		// -----------------NORTE----------------------------

		JComboBox<String> columnasProveedor = new JComboBox<String>(columnasTablaProveedores);
		columnasProveedor.setFont(FUENTE);
		GridBagConstraints gbc_columnasProveedor = new GridBagConstraints();
		gbc_columnasProveedor.gridx = 0;
		gbc_columnasProveedor.gridy = 1;
		pnl_norteEliminarProveedor.add(columnasProveedor, gbc_columnasProveedor);

		JTextField txt_proveedorBuscado = new JTextField();
		GridBagConstraints gbc_proveedorBuscado = new GridBagConstraints();
		gbc_proveedorBuscado.gridx = 2;
		gbc_proveedorBuscado.gridy = 1;
		txt_proveedorBuscado.setColumns(20);
		txt_proveedorBuscado.setFont(FUENTE);
		txt_proveedorBuscado.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_norteEliminarProveedor.add(txt_proveedorBuscado, gbc_proveedorBuscado);

		JButton btn_buscar = new JButton("Buscar");
		GridBagConstraints gbc_buscar = new GridBagConstraints();
		gbc_buscar.gridx = 4;
		gbc_buscar.gridy = 1;

		pnl_norteEliminarProveedor.add(btn_buscar, gbc_buscar);

		// -----------------CENTRO---------------------------
		datosTablaProveedores = controlador.getDatosTablaProveedores();
		modeloTablaProveedores = new DefaultTableModel();
		modeloTablaProveedores.setDataVector(datosTablaProveedores, columnasTablaProveedores);
		modeloTablaProveedores.fireTableDataChanged();
		tablaProveedores1 = new JTable(modeloTablaProveedores) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		TableRowSorter<DefaultTableModel> filtroTablaProveedores = new TableRowSorter<DefaultTableModel>(
				modeloTablaProveedores);
		tablaProveedores1.setRowSorter(filtroTablaProveedores);

		btn_buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((columnasProveedor.getSelectedIndex() != -1) && (txt_proveedorBuscado.getText() != null)) {
					filtroTablaProveedores.setRowFilter(
							RowFilter.regexFilter(txt_proveedorBuscado.getText(), columnasProveedor.getSelectedIndex()));

				}
			}
		});

		JScrollPane scp_tablaProveedores = new JScrollPane();
		scp_tablaProveedores.setViewportView(tablaProveedores1);

		pnl_centroEliminarProveedor.add(scp_tablaProveedores);

		// -----------------SUR------------------------------

		JButton btn_borrar = new JButton("Borrar");
		btn_borrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tablaProveedores1.getSelectedRow() != -1) {
					controlador.borrarProveedor((String) tablaProveedores1.getValueAt(tablaProveedores1.getSelectedRow(), 0), // nombre
							(String) tablaProveedores1.getValueAt(tablaProveedores1.getSelectedRow(), 1), // direccion
							Long.parseLong((String) tablaProveedores1.getValueAt(tablaProveedores1.getSelectedRow(), 2)), // telefono
							(String) tablaProveedores1.getValueAt(tablaProveedores1.getSelectedRow(), 3) // mail
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
		pnl_surEliminarProveedor.add(btn_borrar, gbc_borrar);

	}

	private void componentesConsultarProveedores() {
		// --------------CONSULTAR PROVEEDOR--------------------

		pnl_consultarProveedor = new JPanel(new BorderLayout());

		GridBagLayout gbl_norteConsultarProveedor = new GridBagLayout();
		gbl_norteConsultarProveedor.columnWidths = new int[] { 0, 40, 0, 40, 0 }; // COLUMNAS
		gbl_norteConsultarProveedor.rowHeights = new int[] { 30, 0, 40 }; // FILAS
		pnl_norteConsultarProveedor = new JPanel(gbl_norteConsultarProveedor);

		BorderLayout bl_centroConsultarProveedor = new BorderLayout();
		pnl_centroConsultarProveedor = new JPanel(bl_centroConsultarProveedor);

		GridBagLayout gbl_surConsultarProveedor = new GridBagLayout();
		gbl_surConsultarProveedor.columnWidths = new int[] { 5, 0, 20, 0, 20, 0, 150, 0, 5 }; // COLUMNAS
		gbl_surConsultarProveedor.rowHeights = new int[] { 40, 0, 10, 0, 10, 0, 10, 0, 30 }; // FILAS
		pnl_surConsultarProveedor = new JPanel(gbl_surConsultarProveedor);

		pnl_consultarProveedor.add(pnl_norteConsultarProveedor, BorderLayout.NORTH);
		pnl_consultarProveedor.add(pnl_centroConsultarProveedor, BorderLayout.CENTER);
		pnl_consultarProveedor.add(pnl_surConsultarProveedor, BorderLayout.SOUTH);

		// -----------------NORTE----------------------------

		JComboBox<String> columnasProveedor = new JComboBox<String>(columnasTablaProveedores);
		columnasProveedor.setFont(FUENTE);
		GridBagConstraints gbc_columnaProveedor = new GridBagConstraints();
		gbc_columnaProveedor.gridx = 0;
		gbc_columnaProveedor.gridy = 1;
		pnl_norteConsultarProveedor.add(columnasProveedor, gbc_columnaProveedor);

		JTextField txt_proveedorBuscado = new JTextField();
		GridBagConstraints gbc_proveedorBuscado = new GridBagConstraints();
		gbc_proveedorBuscado.gridx = 2;
		gbc_proveedorBuscado.gridy = 1;
		txt_proveedorBuscado.setColumns(20);
		txt_proveedorBuscado.setFont(FUENTE);
		txt_proveedorBuscado.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_norteConsultarProveedor.add(txt_proveedorBuscado, gbc_proveedorBuscado);

		JButton btn_buscar = new JButton("Buscar");
		GridBagConstraints gbc_buscar = new GridBagConstraints();
		gbc_buscar.gridx = 4;
		gbc_buscar.gridy = 1;
		pnl_norteConsultarProveedor.add(btn_buscar, gbc_buscar);

		// -----------------CENTRO---------------------------
		tablaProveedores2 = new JTable(modeloTablaProveedores) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		TableRowSorter<DefaultTableModel> filtroTablaProveedores = new TableRowSorter<DefaultTableModel>(
				modeloTablaProveedores);
		tablaProveedores2.setRowSorter(filtroTablaProveedores);

		btn_buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((columnasProveedor.getSelectedIndex() != -1) && (txt_proveedorBuscado.getText() != null)) {
					filtroTablaProveedores.setRowFilter(
							RowFilter.regexFilter(txt_proveedorBuscado.getText(), columnasProveedor.getSelectedIndex()));

				}
			}
		});

		JScrollPane scp_tablaProveedores = new JScrollPane();
		scp_tablaProveedores.setViewportView(tablaProveedores2);

		pnl_centroConsultarProveedor.add(scp_tablaProveedores);

		// -----------------SUR------------------------------

		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setFont(FUENTE);
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.gridx = 1;
		gbc_nombre.gridy = 1;
		pnl_surConsultarProveedor.add(lbl_nombre, gbc_nombre);

		JTextField txt_nombre = new JTextField();
		txt_nombre.setFont(FUENTE);
		txt_nombre.setColumns(15);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		txt_nombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_surConsultarProveedor.add(txt_nombre, gbc_txtNombre);

		JLabel lbl_direccion = new JLabel("Dirección:");
		lbl_direccion.setFont(FUENTE);
		GridBagConstraints gbc_direccion = new GridBagConstraints();
		gbc_direccion.anchor = GridBagConstraints.EAST;
		gbc_direccion.gridx = 1;
		gbc_direccion.gridy = 3;
		pnl_surConsultarProveedor.add(lbl_direccion, gbc_direccion);

		JTextField txt_direccion = new JTextField();
		txt_direccion.setFont(FUENTE);
		txt_direccion.setColumns(15);
		GridBagConstraints gbc_txtDireccion = new GridBagConstraints();
		gbc_txtDireccion.gridx = 3;
		gbc_txtDireccion.gridy = 3;
		txt_direccion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_surConsultarProveedor.add(txt_direccion, gbc_txtDireccion);

		JLabel lbl_telefono = new JLabel("Telefono:");
		lbl_telefono.setFont(FUENTE);
		GridBagConstraints gbc_telefono = new GridBagConstraints();
		gbc_telefono.anchor = GridBagConstraints.EAST;
		gbc_telefono.gridx = 1;
		gbc_telefono.gridy = 5;
		pnl_surConsultarProveedor.add(lbl_telefono, gbc_telefono);

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
		pnl_surConsultarProveedor.add(txt_telefono, gbc_txtTelefono);

		JLabel lbl_mail = new JLabel("Mail:");
		lbl_mail.setFont(FUENTE);
		GridBagConstraints gbc_mail = new GridBagConstraints();
		gbc_mail.anchor = GridBagConstraints.EAST;
		gbc_mail.gridx = 1;
		gbc_mail.gridy = 7;
		pnl_surConsultarProveedor.add(lbl_mail, gbc_mail);

		JTextField txt_mail = new JTextField();
		txt_mail.setFont(FUENTE);
		txt_mail.setColumns(15);
		GridBagConstraints gbc_txtMail = new GridBagConstraints();
		gbc_txtMail.gridx = 3;
		gbc_txtMail.gridy = 7;
		pnl_surConsultarProveedor.add(txt_mail, gbc_txtMail);

		JButton btn_actualizar = new JButton("Actualizar");
		GridBagConstraints gbc_actualizar = new GridBagConstraints();
		gbc_actualizar.gridx = 7;
		gbc_actualizar.gridy = 7;
		pnl_surConsultarProveedor.add(btn_actualizar, gbc_actualizar);

		btn_actualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevosDatosProveedor = new Proveedor(txt_nombre.getText(), txt_direccion.getText(),
						Long.parseLong(txt_telefono.getText()), txt_mail.getText());
				if (!anterioresDatosProveedor.equals(nuevosDatosProveedor)) {
					controlador.actualizarProveedor(anterioresDatosProveedor, nuevosDatosProveedor);
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
				if ((columnasProveedor.getSelectedIndex() != -1) && (!txt_proveedorBuscado.getText().equals(""))) {
					filtroTablaProveedores.setRowFilter(
							RowFilter.regexFilter(txt_proveedorBuscado.getText(), columnasProveedor.getSelectedIndex()));

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
				if (tablaProveedores2.getSelectedRow() != -1) {
					txt_nombre.setText((String) tablaProveedores2.getValueAt(tablaProveedores2.getSelectedRow(), 0));
					txt_direccion.setText((String) tablaProveedores2.getValueAt(tablaProveedores2.getSelectedRow(), 1));
					txt_telefono.setText((String) tablaProveedores2.getValueAt(tablaProveedores2.getSelectedRow(), 2));
					txt_mail.setText((String) tablaProveedores2.getValueAt(tablaProveedores2.getSelectedRow(), 3));

					anterioresDatosProveedor = new Proveedor(txt_nombre.getText(), txt_direccion.getText(),
							Long.parseLong(txt_telefono.getText()), txt_mail.getText());

				}
			}
		});
		pnl_centroConsultarProveedor.add(pnlExtra, BorderLayout.SOUTH);

	}

	private void actualizarDatosTabla() {
		modeloTablaProveedores.setRowCount(0);
		datosTablaProveedores = controlador.getDatosTablaProveedores();
		modeloTablaProveedores.setDataVector(datosTablaProveedores, columnasTablaProveedores);

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
