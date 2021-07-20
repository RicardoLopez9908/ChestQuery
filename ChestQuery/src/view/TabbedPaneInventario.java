package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;

import controller.Controlador;
import model.Articulo;

public class TabbedPaneInventario extends JTabbedPane {

	private Controlador controlador;

	private JPanel pnl_agregarArticulo;
	private JPanel pnl_centroAgregarArticulo;
	private JPanel pnl_surAgregarArticulo;

	private JPanel pnl_eliminarArticulo;
	private JPanel pnl_norteEliminarArticulo;
	private JPanel pnl_centroEliminarArticulo;
	private String datosTablaArticulos[][];
	private JTable tablaArticulos1;
	private JTable tablaArticulos2;

	private JPanel pnl_surEliminarArticulo;

	private JPanel pnl_consultarArticulo;
	private JPanel pnl_norteConsultarArticulo;
	private JPanel pnl_centroConsultarArticulo;
	private JPanel pnl_surConsultarArticulo;

	private JFormattedTextField txt_vencimiento1;
	private JFormattedTextField txt_vencimiento2;
	private DefaultTableModel modeloTablaArticulos;
	
	private Articulo anterioresDatosArticulo;
	private Articulo nuevosDatosArticulo;
	
	
	// -------------------------------------------------
	private static Font FUENTE = new Font("dialog", 4, 18);
	String[] columnasTablaArticulos = new String[] { "Codigo", "Nombre", "Posición", "Cantidad", "Proveedor",
			"Vencimiento", "Detalle" };

	public TabbedPaneInventario(Controlador controlador) {
		this.controlador = controlador;
		this.componentesAgregarArticulo();
		this.componentesEliminarArticulos();
		this.componentesConsultarArticulo();
		this.agregarPaneles();
	}

	private void agregarPaneles() {
		this.addTab("Agregar articulo", pnl_agregarArticulo);

		this.addTab("Eliminar articulo", pnl_eliminarArticulo);

		this.addTab("Consultar articulo", pnl_consultarArticulo);

	}

	private void componentesAgregarArticulo() {

		// --------------AGREGAR ARTICULO--------------------
		pnl_agregarArticulo = new JPanel(new BorderLayout());

		GridBagLayout gbl_centroAgregarArticulo = new GridBagLayout();
		gbl_centroAgregarArticulo.columnWidths = new int[] { 0, 100, 70, 100, 0 }; // COLUMNAS
		gbl_centroAgregarArticulo.rowHeights = new int[] { 0, 70, 70, 70, 70, 70, 70, 70, 0 }; // FILAS
		pnl_centroAgregarArticulo = new JPanel(gbl_centroAgregarArticulo);

		GridBagLayout gbl_surAgregarArticulo = new GridBagLayout();
		gbl_surAgregarArticulo.columnWidths = new int[] { 0, 0, 40, 0, 0 }; // COLUMNAS
		gbl_surAgregarArticulo.rowHeights = new int[] { 0, 0, 70 }; // FILAS
		pnl_surAgregarArticulo = new JPanel(gbl_surAgregarArticulo);

		pnl_agregarArticulo.add(pnl_centroAgregarArticulo, BorderLayout.CENTER);
		pnl_agregarArticulo.add(pnl_surAgregarArticulo, BorderLayout.SOUTH);

		// CENTRO:

		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setFont(FUENTE);
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.gridx = 1;
		gbc_nombre.gridy = 1;
		pnl_centroAgregarArticulo.add(lbl_nombre, gbc_nombre);

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
		pnl_centroAgregarArticulo.add(txt_nombre, gbc_txtNombre);

		JLabel lbl_codigo = new JLabel("Codigo:");
		lbl_codigo.setFont(FUENTE);
		GridBagConstraints gbc_codigo = new GridBagConstraints();
		gbc_codigo.anchor = GridBagConstraints.EAST;
		gbc_codigo.gridx = 1;
		gbc_codigo.gridy = 2;
		pnl_centroAgregarArticulo.add(lbl_codigo, gbc_codigo);

		JTextField txt_codigo = new JTextField();
		txt_codigo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		txt_codigo.setFont(FUENTE);
		txt_codigo.setColumns(15);
		GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
		gbc_txtCodigo.gridx = 3;
		gbc_txtCodigo.gridy = 2;
		pnl_centroAgregarArticulo.add(txt_codigo, gbc_txtCodigo);

		JLabel lbl_cantidad = new JLabel("Cantidad:");
		lbl_cantidad.setFont(FUENTE);
		GridBagConstraints gbc_cantidad = new GridBagConstraints();
		gbc_cantidad.anchor = GridBagConstraints.EAST;
		gbc_cantidad.gridx = 1;
		gbc_cantidad.gridy = 3;
		pnl_centroAgregarArticulo.add(lbl_cantidad, gbc_cantidad);

		JTextField txt_cantidad = new JTextField();
		txt_cantidad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		txt_cantidad.setFont(FUENTE);
		txt_cantidad.setColumns(15);
		GridBagConstraints gbc_txtCantidad = new GridBagConstraints();
		gbc_txtCantidad.gridx = 3;
		gbc_txtCantidad.gridy = 3;
		pnl_centroAgregarArticulo.add(txt_cantidad, gbc_txtCantidad);

		JLabel lbl_detalle = new JLabel("Detalle:");
		lbl_detalle.setFont(FUENTE);
		GridBagConstraints gbc_detalle = new GridBagConstraints();
		gbc_detalle.anchor = GridBagConstraints.EAST;
		gbc_detalle.gridx = 1;
		gbc_detalle.gridy = 4;
		pnl_centroAgregarArticulo.add(lbl_detalle, gbc_detalle);

		JTextField txt_detalle = new JTextField();
		txt_detalle.setFont(FUENTE);
		txt_detalle.setColumns(15);
		GridBagConstraints gbc_txtDetalle = new GridBagConstraints();
		gbc_txtDetalle.gridx = 3;
		gbc_txtDetalle.gridy = 4;
		txt_detalle.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_centroAgregarArticulo.add(txt_detalle, gbc_txtDetalle);

		JLabel lbl_vencimiento = new JLabel("Vencimiento:");
		lbl_vencimiento.setFont(FUENTE);
		GridBagConstraints gbc_vencimiento = new GridBagConstraints();
		gbc_vencimiento.anchor = GridBagConstraints.EAST;
		gbc_vencimiento.gridx = 1;
		gbc_vencimiento.gridy = 5;
		pnl_centroAgregarArticulo.add(lbl_vencimiento, gbc_vencimiento);

		try {
			MaskFormatter formatter = new MaskFormatter("## / ## / ####");
			txt_vencimiento1 = new JFormattedTextField(formatter);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txt_vencimiento1.setFont(FUENTE);
		txt_vencimiento1.setColumns(15);
		GridBagConstraints gbc_txtVencimiento = new GridBagConstraints();
		gbc_txtVencimiento.gridx = 3;
		gbc_txtVencimiento.gridy = 5;
		pnl_centroAgregarArticulo.add(txt_vencimiento1, gbc_txtVencimiento);

		JLabel lbl_posicion = new JLabel("Posición:");
		lbl_posicion.setFont(FUENTE);
		GridBagConstraints gbc_posicion = new GridBagConstraints();
		gbc_posicion.anchor = GridBagConstraints.EAST;
		gbc_posicion.gridx = 1;
		gbc_posicion.gridy = 6;
		pnl_centroAgregarArticulo.add(lbl_posicion, gbc_posicion);

		JTextField txt_posicion = new JTextField();
		txt_posicion.setFont(FUENTE);
		txt_posicion.setColumns(15);
		txt_posicion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_txtPosicion = new GridBagConstraints();
		gbc_txtPosicion.gridx = 3;
		gbc_txtPosicion.gridy = 6;
		pnl_centroAgregarArticulo.add(txt_posicion, gbc_txtPosicion);

		JLabel lbl_proveedor = new JLabel("Proveedor:");
		lbl_proveedor.setFont(FUENTE);
		GridBagConstraints gbc_proveedor = new GridBagConstraints();
		gbc_proveedor.anchor = GridBagConstraints.EAST;
		gbc_proveedor.gridx = 1;
		gbc_proveedor.gridy = 7;
		pnl_centroAgregarArticulo.add(lbl_proveedor, gbc_proveedor);

		JTextField txt_proveedor = new JTextField();
		txt_proveedor.setFont(FUENTE);
		txt_proveedor.setColumns(15);
		GridBagConstraints gbc_txtProveedor = new GridBagConstraints();
		gbc_txtProveedor.gridx = 3;
		gbc_txtProveedor.gridy = 7;
		txt_proveedor.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_centroAgregarArticulo.add(txt_proveedor, gbc_txtProveedor);

		// SUR:
		JButton btn_cancelar = new JButton("Cancelar");
		GridBagConstraints gbc_cancelar = new GridBagConstraints();
		gbc_cancelar.anchor = GridBagConstraints.EAST;
		gbc_cancelar.gridx = 1;
		gbc_cancelar.gridy = 1;
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_nombre.setText("");
				txt_codigo.setText("");
				txt_vencimiento1.setText("");
				txt_cantidad.setText("");
				txt_detalle.setText("");
				txt_posicion.setText("");
				txt_proveedor.setText("");
			}
		});
		pnl_surAgregarArticulo.add(btn_cancelar, gbc_cancelar);

		JButton btn_agregar = new JButton("Agregar");
		btn_agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!((txt_codigo.getText().isEmpty()) || (txt_nombre.getText().isEmpty())
						|| (txt_cantidad.getText().isEmpty()) || (txt_detalle.getText().isEmpty())
						|| (txt_posicion.getText().isEmpty()) || (txt_proveedor.getText().isEmpty())
						|| (txt_vencimiento1.getText().isEmpty()))) {
					controlador.agregarArticulo(
							txt_codigo.getText(),
							txt_nombre.getText(),
							txt_cantidad.getText(),
							txt_detalle.getText(),
							txt_posicion.getText(),
							txt_proveedor.getText(),
							txt_vencimiento1.getText());
					
					actualizarDatosTabla();
					txt_nombre.setText("");
					txt_codigo.setText("");
					txt_cantidad.setText("");
					txt_detalle.setText("");
					txt_posicion.setText("");
					txt_proveedor.setText("");
					txt_vencimiento1.setText("");
					dimensionarTablas(tablaArticulos1);
					dimensionarTablas(tablaArticulos2);
				}else {
					JOptionPane.showMessageDialog(null, "Por favor, completar todas las filas", "ChestQuery", 1);
				}
			}
		});

		GridBagConstraints gbc_agregar = new GridBagConstraints();
		gbc_agregar.gridx = 3;
		gbc_agregar.gridy = 1;
		pnl_surAgregarArticulo.add(btn_agregar, gbc_agregar);

	}

	private void componentesEliminarArticulos() {
		// --------------ELIMINAR USUARIOS--------------------

		pnl_eliminarArticulo = new JPanel(new BorderLayout());

		GridBagLayout gbl_norteEliminarArticulo = new GridBagLayout();
		gbl_norteEliminarArticulo.columnWidths = new int[] { 0, 40, 0, 40, 0 }; // COLUMNAS
		gbl_norteEliminarArticulo.rowHeights = new int[] { 70, 0, 70 }; // FILAS
		pnl_norteEliminarArticulo = new JPanel(gbl_norteEliminarArticulo);

		BorderLayout bl_centroEliminarArticulo = new BorderLayout();
		pnl_centroEliminarArticulo = new JPanel(bl_centroEliminarArticulo);

		GridBagLayout gbl_surEliminarArticulo = new GridBagLayout();
		gbl_surEliminarArticulo.columnWidths = new int[] { 0, 0, 40, 0, 0 }; // COLUMNAS
		gbl_surEliminarArticulo.rowHeights = new int[] { 40, 0, 40 }; // FILAS
		pnl_surEliminarArticulo = new JPanel(gbl_surEliminarArticulo);

		pnl_eliminarArticulo.add(pnl_norteEliminarArticulo, BorderLayout.NORTH);
		pnl_eliminarArticulo.add(pnl_centroEliminarArticulo, BorderLayout.CENTER);
		pnl_eliminarArticulo.add(pnl_surEliminarArticulo, BorderLayout.SOUTH);

		// -----------------NORTE----------------------------

		JComboBox<String> columnasArticulo = new JComboBox<String>(columnasTablaArticulos);
		columnasArticulo.setFont(FUENTE);
		GridBagConstraints gbc_columnasArticulo = new GridBagConstraints();
		gbc_columnasArticulo.gridx = 0;
		gbc_columnasArticulo.gridy = 1;
		pnl_norteEliminarArticulo.add(columnasArticulo, gbc_columnasArticulo);

		JTextField txt_articuloBuscado = new JTextField();
		GridBagConstraints gbc_articuloBuscado = new GridBagConstraints();
		gbc_articuloBuscado.gridx = 2;
		gbc_articuloBuscado.gridy = 1;
		txt_articuloBuscado.setColumns(20);
		txt_articuloBuscado.setFont(FUENTE);
		txt_articuloBuscado.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_norteEliminarArticulo.add(txt_articuloBuscado, gbc_articuloBuscado);

		JButton btn_buscar = new JButton("Buscar");
		GridBagConstraints gbc_buscar = new GridBagConstraints();
		gbc_buscar.gridx = 4;
		gbc_buscar.gridy = 1;

		pnl_norteEliminarArticulo.add(btn_buscar, gbc_buscar);

		// -----------------CENTRO---------------------------
		datosTablaArticulos = controlador.getDatosTablaArticulos();
		modeloTablaArticulos = new DefaultTableModel();
		modeloTablaArticulos.setDataVector(datosTablaArticulos, columnasTablaArticulos);
		modeloTablaArticulos.fireTableDataChanged();
		tablaArticulos1 = new JTable(modeloTablaArticulos) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablaArticulos1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		this.dimensionarTablas(tablaArticulos1);

		TableRowSorter<DefaultTableModel> filtroTablaArticulos = new TableRowSorter<DefaultTableModel>(
				modeloTablaArticulos);
		tablaArticulos1.setRowSorter(filtroTablaArticulos);

		btn_buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((columnasArticulo.getSelectedIndex() != -1) && (txt_articuloBuscado.getText() != null)) {
					filtroTablaArticulos.setRowFilter(
							RowFilter.regexFilter(txt_articuloBuscado.getText(), columnasArticulo.getSelectedIndex()));

				}
			}
		});

		JScrollPane scp_tablaArticulos = new JScrollPane();
		scp_tablaArticulos.setViewportView(tablaArticulos1);

		pnl_centroEliminarArticulo.add(scp_tablaArticulos);

		// -----------------SUR------------------------------

		JButton btn_borrar = new JButton("Borrar");
		btn_borrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (tablaArticulos1.getSelectedRow() != -1) {
					controlador.borrarArticulo(
							Long.parseLong((String) tablaArticulos1.getValueAt(tablaArticulos1.getSelectedRow(), 0)), // codigo
							((String) tablaArticulos1.getValueAt(tablaArticulos1.getSelectedRow(), 1)), // nombre
							Integer.parseInt((String) tablaArticulos1.getValueAt(tablaArticulos1.getSelectedRow(), 2)), // posicion
							Integer.parseInt((String) tablaArticulos1.getValueAt(tablaArticulos1.getSelectedRow(), 3)), // cantidad
							((String) tablaArticulos1.getValueAt(tablaArticulos1.getSelectedRow(), 4)), // proveedor
							((String) tablaArticulos1.getValueAt(tablaArticulos1.getSelectedRow(), 5)), // vencimiento
							((String) tablaArticulos1.getValueAt(tablaArticulos1.getSelectedRow(), 6)) // detalle
					);
					actualizarDatosTabla();
					dimensionarTablas(tablaArticulos1);
					dimensionarTablas(tablaArticulos2);

				} else {
					JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila", "ChestQuery", 1);
				}
			}
		});
		GridBagConstraints gbc_borrar = new GridBagConstraints();
		gbc_borrar.gridx = 3;
		gbc_borrar.gridy = 1;
		pnl_surEliminarArticulo.add(btn_borrar, gbc_borrar);

	}

	private void componentesConsultarArticulo() {
		// --------------CONSULTAR ARTICULO--------------------

		pnl_consultarArticulo = new JPanel(new BorderLayout());

		GridBagLayout gbl_norteConsultarArticulo = new GridBagLayout();
		gbl_norteConsultarArticulo.columnWidths = new int[] { 0, 40, 0, 40, 0 }; // COLUMNAS
		gbl_norteConsultarArticulo.rowHeights = new int[] { 30, 0, 40 }; // FILAS
		pnl_norteConsultarArticulo = new JPanel(gbl_norteConsultarArticulo);

		BorderLayout bl_centroConsultarArticulo = new BorderLayout();
		pnl_centroConsultarArticulo = new JPanel(bl_centroConsultarArticulo);

		GridBagLayout gbl_surConsultarArticulo = new GridBagLayout();
		gbl_surConsultarArticulo.columnWidths = new int[] { 5, 0, 20, 0, 20, 0, 150, 0, 5 }; // COLUMNAS
		gbl_surConsultarArticulo.rowHeights = new int[] { 40, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 10, 0, 30 }; // FILAS
		pnl_surConsultarArticulo = new JPanel(gbl_surConsultarArticulo);

		pnl_consultarArticulo.add(pnl_norteConsultarArticulo, BorderLayout.NORTH);
		pnl_consultarArticulo.add(pnl_centroConsultarArticulo, BorderLayout.CENTER);
		pnl_consultarArticulo.add(pnl_surConsultarArticulo, BorderLayout.SOUTH);

		// -----------------NORTE----------------------------

		JComboBox<String> columnasArticulo = new JComboBox<String>(columnasTablaArticulos);
		columnasArticulo.setFont(FUENTE);
		GridBagConstraints gbc_columnaArticulo = new GridBagConstraints();
		gbc_columnaArticulo.gridx = 0;
		gbc_columnaArticulo.gridy = 1;
		pnl_norteConsultarArticulo.add(columnasArticulo, gbc_columnaArticulo);

		JTextField txt_articuloBuscado = new JTextField();
		GridBagConstraints gbc_articuloBuscado = new GridBagConstraints();
		gbc_articuloBuscado.gridx = 2;
		gbc_articuloBuscado.gridy = 1;
		txt_articuloBuscado.setColumns(20);
		txt_articuloBuscado.setFont(FUENTE);
		txt_articuloBuscado.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_norteConsultarArticulo.add(txt_articuloBuscado, gbc_articuloBuscado);

		JButton btn_buscar = new JButton("Buscar");
		GridBagConstraints gbc_buscar = new GridBagConstraints();
		gbc_buscar.gridx = 4;
		gbc_buscar.gridy = 1;
		pnl_norteConsultarArticulo.add(btn_buscar, gbc_buscar);

		// -----------------CENTRO---------------------------
		tablaArticulos2 = new JTable(modeloTablaArticulos) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tablaArticulos2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		this.dimensionarTablas(tablaArticulos2);

		TableRowSorter<DefaultTableModel> filtroTablaArticulos = new TableRowSorter<DefaultTableModel>(
				modeloTablaArticulos);
		tablaArticulos2.setRowSorter(filtroTablaArticulos);

		btn_buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((columnasArticulo.getSelectedIndex() != -1) && (txt_articuloBuscado.getText() != null)) {
					filtroTablaArticulos.setRowFilter(
							RowFilter.regexFilter(txt_articuloBuscado.getText(), columnasArticulo.getSelectedIndex()));

				}
			}
		});

		JScrollPane scp_tablaArticulos = new JScrollPane();
		scp_tablaArticulos.setViewportView(tablaArticulos2);

		pnl_centroConsultarArticulo.add(scp_tablaArticulos);

		// -----------------SUR------------------------------


		JLabel lbl_codigo = new JLabel("Codigo:");
		lbl_codigo.setFont(FUENTE);
		GridBagConstraints gbc_codigo = new GridBagConstraints();
		gbc_codigo.anchor = GridBagConstraints.EAST;
		gbc_codigo.gridx = 1;
		gbc_codigo.gridy = 1;
		pnl_surConsultarArticulo.add(lbl_codigo, gbc_codigo);

		JTextField txt_codigo = new JTextField();
		txt_codigo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		txt_codigo.setFont(FUENTE);
		txt_codigo.setColumns(15);
		GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
		gbc_txtCodigo.gridx = 3;
		gbc_txtCodigo.gridy = 1;
		pnl_surConsultarArticulo.add(txt_codigo, gbc_txtCodigo);

		
		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setFont(FUENTE);
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.gridx = 1;
		gbc_nombre.gridy = 3;
		pnl_surConsultarArticulo.add(lbl_nombre, gbc_nombre);

		JTextField txt_nombre = new JTextField();
		txt_nombre.setFont(FUENTE);
		txt_nombre.setColumns(15);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 3;
		txt_nombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_surConsultarArticulo.add(txt_nombre, gbc_txtNombre);

		JLabel lbl_posicion = new JLabel("Posición:");
		lbl_posicion.setFont(FUENTE);
		GridBagConstraints gbc_posicion = new GridBagConstraints();
		gbc_posicion.anchor = GridBagConstraints.EAST;
		gbc_posicion.gridx = 1;
		gbc_posicion.gridy = 5;
		pnl_surConsultarArticulo.add(lbl_posicion, gbc_posicion);

		JTextField txt_posicion = new JTextField();
		txt_posicion.setFont(FUENTE);
		txt_posicion.setColumns(15);
		txt_posicion.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		GridBagConstraints gbc_txtPosicion = new GridBagConstraints();
		gbc_txtPosicion.gridx = 3;
		gbc_txtPosicion.gridy = 5;
		pnl_surConsultarArticulo.add(txt_posicion, gbc_txtPosicion);

		
		JLabel lbl_cantidad = new JLabel("Cantidad:");
		lbl_cantidad.setFont(FUENTE);
		GridBagConstraints gbc_cantidad = new GridBagConstraints();
		gbc_cantidad.anchor = GridBagConstraints.EAST;
		gbc_cantidad.gridx = 1;
		gbc_cantidad.gridy = 7;
		pnl_surConsultarArticulo.add(lbl_cantidad, gbc_cantidad);

		JTextField txt_cantidad = new JTextField();
		txt_cantidad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		txt_cantidad.setFont(FUENTE);
		txt_cantidad.setColumns(15);
		GridBagConstraints gbc_txtCantidad = new GridBagConstraints();
		gbc_txtCantidad.gridx = 3;
		gbc_txtCantidad.gridy = 7;
		pnl_surConsultarArticulo.add(txt_cantidad, gbc_txtCantidad);


		JLabel lbl_proveedor = new JLabel("Proveedor:");
		lbl_proveedor.setFont(FUENTE);
		GridBagConstraints gbc_proveedor = new GridBagConstraints();
		gbc_proveedor.anchor = GridBagConstraints.EAST;
		gbc_proveedor.gridx = 1;
		gbc_proveedor.gridy = 9;
		pnl_surConsultarArticulo.add(lbl_proveedor, gbc_proveedor);

		JTextField txt_proveedor = new JTextField();
		txt_proveedor.setFont(FUENTE);
		txt_proveedor.setColumns(15);
		GridBagConstraints gbc_txtProveedor = new GridBagConstraints();
		gbc_txtProveedor.gridx = 3;
		gbc_txtProveedor.gridy = 9;
		txt_proveedor.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_surConsultarArticulo.add(txt_proveedor, gbc_txtProveedor);
		

		JLabel lbl_vencimiento = new JLabel("Vencimiento:");
		lbl_vencimiento.setFont(FUENTE);
		GridBagConstraints gbc_vencimiento = new GridBagConstraints();
		gbc_vencimiento.anchor = GridBagConstraints.EAST;
		gbc_vencimiento.gridx = 1;
		gbc_vencimiento.gridy = 11;
		pnl_surConsultarArticulo.add(lbl_vencimiento, gbc_vencimiento);

		try {
			MaskFormatter formatter = new MaskFormatter("## / ## / ####");
			txt_vencimiento2 = new JFormattedTextField(formatter);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txt_vencimiento2.setFont(FUENTE);
		txt_vencimiento2.setColumns(15);
		GridBagConstraints gbc_txtVencimiento = new GridBagConstraints();
		gbc_txtVencimiento.gridx = 3;
		gbc_txtVencimiento.gridy = 11;
		pnl_surConsultarArticulo.add(txt_vencimiento2, gbc_txtVencimiento);


		JLabel lbl_detalle = new JLabel("Detalle:");
		lbl_detalle.setFont(FUENTE);
		GridBagConstraints gbc_detalle = new GridBagConstraints();
		gbc_detalle.anchor = GridBagConstraints.EAST;
		gbc_detalle.gridx = 1;
		gbc_detalle.gridy = 13;
		pnl_surConsultarArticulo.add(lbl_detalle, gbc_detalle);

		JTextField txt_detalle = new JTextField();
		txt_detalle.setFont(FUENTE);
		txt_detalle.setColumns(15);
		GridBagConstraints gbc_txtDetalle = new GridBagConstraints();
		gbc_txtDetalle.gridx = 3;
		gbc_txtDetalle.gridy = 13;
		txt_detalle.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_surConsultarArticulo.add(txt_detalle, gbc_txtDetalle);




		
		
		JButton btn_actualizar = new JButton("Actualizar");
		GridBagConstraints gbc_actualizar = new GridBagConstraints();
		gbc_actualizar.gridx = 7;
		gbc_actualizar.gridy = 13;
		pnl_surConsultarArticulo.add(btn_actualizar, gbc_actualizar);

		btn_actualizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				nuevosDatosArticulo = new Articulo(txt_nombre.getText(),Long.parseLong(txt_codigo.getText()),Integer.parseInt(txt_cantidad.getText()),Integer.parseInt(txt_posicion.getText()),txt_proveedor.getText(),txt_vencimiento2.getText(),txt_detalle.getText());
				if(!anterioresDatosArticulo.equals(nuevosDatosArticulo)) {
					controlador.actualizarArticulo(anterioresDatosArticulo,nuevosDatosArticulo);
					txt_codigo.setText("");
					txt_nombre.setText("");
					txt_cantidad.setText("");
					txt_detalle.setText("");
					txt_vencimiento2.setText("");
					txt_proveedor.setText("");
					txt_posicion.setText("");
					
					actualizarDatosTabla();
					dimensionarTablas(tablaArticulos1);
					dimensionarTablas(tablaArticulos2);
					
				}
			}
		} );
		
		btn_buscar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((columnasArticulo.getSelectedIndex() != -1) && (!txt_articuloBuscado.getText().equals(""))) {
					filtroTablaArticulos.setRowFilter(
							RowFilter.regexFilter(txt_articuloBuscado.getText(), columnasArticulo.getSelectedIndex()));

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
				if (tablaArticulos2.getSelectedRow() != -1) {
					txt_codigo.setText((String) tablaArticulos2.getValueAt(tablaArticulos2.getSelectedRow(), 0));
					txt_nombre.setText((String) tablaArticulos2.getValueAt(tablaArticulos2.getSelectedRow(), 1));
					txt_posicion.setText((String) tablaArticulos2.getValueAt(tablaArticulos2.getSelectedRow(), 2));
					txt_cantidad.setText((String) tablaArticulos2.getValueAt(tablaArticulos2.getSelectedRow(), 3));
					txt_proveedor.setText((String) tablaArticulos2.getValueAt(tablaArticulos2.getSelectedRow(), 4));
					txt_vencimiento2.setText((String) tablaArticulos2.getValueAt(tablaArticulos2.getSelectedRow(), 5));
					txt_detalle.setText((String) tablaArticulos2.getValueAt(tablaArticulos2.getSelectedRow(), 6));
					
					anterioresDatosArticulo = new Articulo(txt_nombre.getText(),Long.parseLong(txt_codigo.getText()),Integer.parseInt(txt_cantidad.getText()),Integer.parseInt(txt_posicion.getText()),txt_proveedor.getText(),txt_vencimiento2.getText(),txt_detalle.getText());
					
				}
			}
		});
		pnl_centroConsultarArticulo.add(pnlExtra, BorderLayout.SOUTH);

		
		
	}

	private void dimensionarTablas(JTable tabla) {
		TableColumnModel columnModel = tabla.getColumnModel();

		columnModel.getColumn(0).setPreferredWidth(130); // codigo
		columnModel.getColumn(1).setPreferredWidth(300); // nombre
		columnModel.getColumn(2).setPreferredWidth(100); // posición
		columnModel.getColumn(3).setPreferredWidth(75); // cantidad
		columnModel.getColumn(4).setPreferredWidth(200); // proveedor
		columnModel.getColumn(5).setPreferredWidth(100); // vencimiento
		columnModel.getColumn(6).setPreferredWidth(550); // detalle

	}

	private void actualizarDatosTabla() {
		modeloTablaArticulos.setRowCount(0);
		datosTablaArticulos = controlador.getDatosTablaArticulos();
		modeloTablaArticulos.setDataVector(datosTablaArticulos, columnasTablaArticulos);

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
