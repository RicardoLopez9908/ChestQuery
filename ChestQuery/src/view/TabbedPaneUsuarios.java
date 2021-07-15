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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controller.Controlador;
import model.Modelo;

public class TabbedPaneUsuarios extends JTabbedPane {

	
	private Controlador controlador;
	
	private JPanel pnl_agregarUsuario;
	private JPanel pnl_centroAgregarUsuario;
	private JPanel pnl_surAgregarUsuario;

	private JPanel pnl_eliminarUsuario;
	private JPanel pnl_norteEliminarUsuario;
	private JPanel pnl_centroEliminarUsuario;
	private String datosTablaUsuarios[][];
	DefaultTableModel modeloTablaUsuarios;
	private JPanel pnl_surEliminarUsuario;

	private JPanel pnl_consultarUsuario;
	private JPanel pnl_norteConsultarUsuario;
	private JPanel pnl_centroConsultarUsuario;
	private JPanel pnl_surConsultarUsuario;

	// -------------------------------------------------
	private static Font FUENTE = new Font("dialog", 4, 18);
	private String[] columnasTablaUsuarios = new String[] {"Nombre", "Contraseña", "Nivel de acceso","Diseño" };
	private String[] nivelesDeAcceso = new String[] { "DEFAULT", "MEDIUM", "PREMIUM" };

	
	public TabbedPaneUsuarios(Controlador controlador) {
		this.controlador = controlador;
		this.componentesAgregarUsuarios();
		this.componentesEliminaUsuarios();
		this.componentesConsultarUsuarios();
		this.agregarPaneles();
	}

	
	private void agregarPaneles() {
		this.addTab("Agregar usuario", pnl_agregarUsuario);

		this.addTab("Eliminar usuario", pnl_eliminarUsuario);

		this.addTab("Consultar usuario", pnl_consultarUsuario);

	}

	private void componentesAgregarUsuarios() {

		// --------------AGREGAR ARTICULO--------------------
		pnl_agregarUsuario = new JPanel(new BorderLayout());

		GridBagLayout gbl_centroAgregarUsuario = new GridBagLayout();
		gbl_centroAgregarUsuario.columnWidths = new int[] { 0, 100, 70, 100, 0 }; // COLUMNAS
		gbl_centroAgregarUsuario.rowHeights = new int[] { 0, 70, 70, 70, 0 }; // FILAS
		pnl_centroAgregarUsuario = new JPanel(gbl_centroAgregarUsuario);

		GridBagLayout gbl_surAgregarUsuario = new GridBagLayout();
		gbl_surAgregarUsuario.columnWidths = new int[] { 0, 0, 40, 0, 0 }; // COLUMNAS
		gbl_surAgregarUsuario.rowHeights = new int[] { 0, 0, 70 }; // FILAS
		pnl_surAgregarUsuario = new JPanel(gbl_surAgregarUsuario);

		pnl_agregarUsuario.add(pnl_centroAgregarUsuario, BorderLayout.CENTER);
		pnl_agregarUsuario.add(pnl_surAgregarUsuario, BorderLayout.SOUTH);

		// CENTRO:

		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setFont(FUENTE);
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.gridx = 1;
		gbc_nombre.gridy = 1;
		pnl_centroAgregarUsuario.add(lbl_nombre, gbc_nombre);

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
		pnl_centroAgregarUsuario.add(txt_nombre, gbc_txtNombre);

		JLabel lbl_contrasena = new JLabel("Contraseña:");
		lbl_contrasena.setFont(FUENTE);
		GridBagConstraints gbc_contrasena = new GridBagConstraints();
		gbc_contrasena.anchor = GridBagConstraints.EAST;
		gbc_contrasena.gridx = 1;
		gbc_contrasena.gridy = 2;
		pnl_centroAgregarUsuario.add(lbl_contrasena, gbc_contrasena);

		JPasswordField txt_contrasena = new JPasswordField();
		txt_contrasena.setFont(FUENTE);
		txt_contrasena.setColumns(15);
		GridBagConstraints gbc_txtContrasena = new GridBagConstraints();
		gbc_txtContrasena.gridx = 3;
		gbc_txtContrasena.gridy = 2;
		txt_contrasena.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_centroAgregarUsuario.add(txt_contrasena, gbc_txtContrasena);

		JLabel lbl_nivelDeAcceso = new JLabel("Nivel de acceso:");
		lbl_nivelDeAcceso.setFont(FUENTE);
		GridBagConstraints gbc_nivelDeAcceso = new GridBagConstraints();
		gbc_nivelDeAcceso.anchor = GridBagConstraints.EAST;
		gbc_nivelDeAcceso.gridx = 1;
		gbc_nivelDeAcceso.gridy = 3;
		pnl_centroAgregarUsuario.add(lbl_nivelDeAcceso, gbc_nivelDeAcceso);

		JComboBox<String> txt_nivelDeAcceso = new JComboBox<String>(nivelesDeAcceso);
		txt_nivelDeAcceso.setFont(FUENTE);
		GridBagConstraints gbc_txtNivelDeAcceso = new GridBagConstraints();
		gbc_txtNivelDeAcceso.gridx = 3;
		gbc_txtNivelDeAcceso.gridy = 3;
		pnl_centroAgregarUsuario.add(txt_nivelDeAcceso, gbc_txtNivelDeAcceso);

		// SUR:
		JButton btn_cancelar = new JButton("Cancelar");
		GridBagConstraints gbc_cancelar = new GridBagConstraints();
		gbc_cancelar.anchor = GridBagConstraints.EAST;
		gbc_cancelar.gridx = 1;
		gbc_cancelar.gridy = 1;
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_nombre.setText("");
				txt_contrasena.setText("");
			}
		});
		pnl_surAgregarUsuario.add(btn_cancelar, gbc_cancelar);

		JButton btn_agregar = new JButton("Agregar");
		GridBagConstraints gbc_agregar = new GridBagConstraints();
		gbc_agregar.gridx = 3;
		gbc_agregar.gridy = 1;
		pnl_surAgregarUsuario.add(btn_agregar, gbc_agregar);

	}

	private void componentesEliminaUsuarios() {
		// --------------ELIMINAR USUARIOS--------------------
		
		pnl_eliminarUsuario = new JPanel(new BorderLayout());

		GridBagLayout gbl_norteEliminarUsuario = new GridBagLayout();
		gbl_norteEliminarUsuario.columnWidths = new int[] { 0, 40, 0, 40, 0 }; // COLUMNAS
		gbl_norteEliminarUsuario.rowHeights = new int[] { 70, 0, 70 }; // FILAS
		pnl_norteEliminarUsuario = new JPanel(gbl_norteEliminarUsuario);

		BorderLayout bl_centroEliminarUsuario = new BorderLayout();
		pnl_centroEliminarUsuario = new JPanel(bl_centroEliminarUsuario);

		GridBagLayout gbl_surEliminarUsuario = new GridBagLayout();
		gbl_surEliminarUsuario.columnWidths = new int[] { 0, 0, 40, 0, 0 }; // COLUMNAS
		gbl_surEliminarUsuario.rowHeights = new int[] { 40, 0, 40 }; // FILAS
		pnl_surEliminarUsuario = new JPanel(gbl_surEliminarUsuario);

		pnl_eliminarUsuario.add(pnl_norteEliminarUsuario, BorderLayout.NORTH);
		pnl_eliminarUsuario.add(pnl_centroEliminarUsuario, BorderLayout.CENTER);
		pnl_eliminarUsuario.add(pnl_surEliminarUsuario, BorderLayout.SOUTH);

		// -----------------NORTE----------------------------

		JComboBox<String> columnasUsuario = new JComboBox<String>(columnasTablaUsuarios);
		columnasUsuario.setFont(FUENTE);
		GridBagConstraints gbc_columnasUsuario = new GridBagConstraints();
		gbc_columnasUsuario.gridx = 0;
		gbc_columnasUsuario.gridy = 1;
		pnl_norteEliminarUsuario.add(columnasUsuario, gbc_columnasUsuario);

		JTextField txt_usuarioBuscado = new JTextField();
		GridBagConstraints gbc_usuarioBuscado = new GridBagConstraints();
		gbc_usuarioBuscado.gridx = 2;
		gbc_usuarioBuscado.gridy = 1;
		txt_usuarioBuscado.setColumns(20);
		txt_usuarioBuscado.setFont(FUENTE);
		txt_usuarioBuscado.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_norteEliminarUsuario.add(txt_usuarioBuscado, gbc_usuarioBuscado);

		JButton btn_buscar = new JButton("Buscar");
		GridBagConstraints gbc_buscar = new GridBagConstraints();
		gbc_buscar.gridx = 4;
		gbc_buscar.gridy = 1;
		pnl_norteEliminarUsuario.add(btn_buscar, gbc_buscar);

		// -----------------CENTRO---------------------------
		datosTablaUsuarios = controlador.getDatosTablaUsuarios();
		modeloTablaUsuarios = new DefaultTableModel();
		modeloTablaUsuarios.setDataVector(datosTablaUsuarios, columnasTablaUsuarios);
		JTable tablaUsuarios = new JTable(modeloTablaUsuarios);
		tablaUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane scp_tablaArticulos = new JScrollPane();
		scp_tablaArticulos.setViewportView(tablaUsuarios);
		
		pnl_centroEliminarUsuario.add(scp_tablaArticulos);
						

		// -----------------SUR------------------------------

		JButton btn_borrar = new JButton("Borrar");
		btn_borrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(tablaUsuarios.getSelectedRow()!= -1) {
					if(controlador.borrarUsuario(tablaUsuarios.getSelectedRow()+1)) {
						modeloTablaUsuarios.removeRow(tablaUsuarios.getSelectedRow());
					}
					else {
						JOptionPane.showMessageDialog(null,"No se puede eliminar el mismo usuario que el actual","ChestQuery",1);
					}
				}
				else{
					JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila" ,"ChestQuery",1);
				}
			}
		});
		GridBagConstraints gbc_borrar = new GridBagConstraints();
		gbc_borrar.gridx = 3;
		gbc_borrar.gridy = 1;
		pnl_surEliminarUsuario.add(btn_borrar, gbc_borrar);

	}

	private void componentesConsultarUsuarios() {
		// --------------CONSULTAR ARTICULO--------------------
		datosTablaUsuarios = controlador.getDatosTablaUsuarios();
		
		pnl_consultarUsuario = new JPanel(new BorderLayout());

		GridBagLayout gbl_norteConsultarUsuario = new GridBagLayout();
		gbl_norteConsultarUsuario.columnWidths = new int[] { 0, 40, 0, 40, 0 }; // COLUMNAS
		gbl_norteConsultarUsuario.rowHeights = new int[] { 70, 0, 70 }; // FILAS
		pnl_norteConsultarUsuario = new JPanel(gbl_norteConsultarUsuario);

		BorderLayout bl_centroConsultarUsuario = new BorderLayout();
		pnl_centroConsultarUsuario = new JPanel(bl_centroConsultarUsuario);

		GridBagLayout gbl_surConsultarUsuario = new GridBagLayout();
		gbl_surConsultarUsuario.columnWidths = new int[] { 0, 0, 40, 0, 0 }; // COLUMNAS
		gbl_surConsultarUsuario.rowHeights = new int[] { 40, 0, 40 }; // FILAS
		pnl_surConsultarUsuario = new JPanel(gbl_surConsultarUsuario);

		pnl_consultarUsuario.add(pnl_norteConsultarUsuario, BorderLayout.NORTH);
		pnl_consultarUsuario.add(pnl_centroConsultarUsuario, BorderLayout.CENTER);
		pnl_consultarUsuario.add(pnl_surConsultarUsuario, BorderLayout.SOUTH);

		// -----------------NORTE----------------------------

		JComboBox<String> columnasUsuario = new JComboBox<String>(columnasTablaUsuarios);
		columnasUsuario.setFont(FUENTE);
		GridBagConstraints gbc_columnasUsuario = new GridBagConstraints();
		gbc_columnasUsuario.gridx = 0;
		gbc_columnasUsuario.gridy = 1;
		pnl_norteConsultarUsuario.add(columnasUsuario, gbc_columnasUsuario);

		JTextField txt_usuarioBuscado = new JTextField();
		GridBagConstraints gbc_usuarioBuscado = new GridBagConstraints();
		gbc_usuarioBuscado.gridx = 2;
		gbc_usuarioBuscado.gridy = 1;
		txt_usuarioBuscado.setColumns(20);
		txt_usuarioBuscado.setFont(FUENTE);
		txt_usuarioBuscado.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));
				}
			}
		});
		pnl_norteConsultarUsuario.add(txt_usuarioBuscado, gbc_usuarioBuscado);

		JButton btn_buscar = new JButton("Buscar");
		GridBagConstraints gbc_buscar = new GridBagConstraints();
		gbc_buscar.gridx = 4;
		gbc_buscar.gridy = 1;
		pnl_norteConsultarUsuario.add(btn_buscar, gbc_buscar);

		// -----------------CENTRO---------------------------

		JTable tablaUsuarios = new JTable(datosTablaUsuarios, columnasTablaUsuarios); 
		tablaUsuarios.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		JScrollPane scp_tablaArticulos = new JScrollPane(tablaUsuarios,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		pnl_centroConsultarUsuario.add(scp_tablaArticulos);

		// -----------------SUR------------------------------

		JButton btn_actualizar = new JButton("Actualizar");
		GridBagConstraints gbc_actualizar = new GridBagConstraints();
		gbc_actualizar.gridx = 3;
		gbc_actualizar.gridy = 1;
		pnl_surConsultarUsuario.add(btn_actualizar, gbc_actualizar);

	}
	
	
	private void llenarTablaUsuarios() {
		
	}

	public void mostrar() {
		this.setVisible(true);
	}

	public void ocultar() {
		this.disable();
		this.setVisible(false);
	}

}