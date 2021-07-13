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
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class TabbedPaneInventario extends JTabbedPane{

	
	private JPanel pnl_agregarArticulo;
	private JPanel pnl_centroAgregarArticulo;
	private JPanel pnl_surAgregarArticulo;
	
	private JPanel pnl_eliminarArticulo;
	private JPanel pnl_norteEliminarArticulo;
	private JPanel pnl_centroEliminarArticulo;
	private JPanel pnl_surEliminarArticulo;
	
	
	private JPanel pnl_consultarArticulo;
	
	private JFormattedTextField txt_vencimiento;
	//-------------------------------------------------
	private static Font FUENTE = new Font("dialog", 4, 18);
	
	
	public TabbedPaneInventario() {
		this.componentesAgregarArticulo();
		this.agregarPaneles();
	}
	
	private void agregarPaneles() {
		this.addTab("Agregar articulo",pnl_agregarArticulo);

		this.addTab("Eliminar articulo",pnl_eliminarArticulo);

		this.addTab("Consultar articulo",pnl_consultarArticulo);

	}
	
	private void componentesAgregarArticulo() {
	
		//--------------AGREGAR ARTICULO--------------------
		pnl_agregarArticulo = new JPanel(new BorderLayout());
		
		GridBagLayout gbl_centroAgregarArticulo = new GridBagLayout();
		gbl_centroAgregarArticulo.columnWidths = new int[] { 0, 100, 70, 100, 0 };	//COLUMNAS
		gbl_centroAgregarArticulo.rowHeights = new int[] { 0, 70, 70, 70, 70, 70, 70,70, 0 };		//FILAS
		pnl_centroAgregarArticulo = new JPanel(gbl_centroAgregarArticulo);
		
		
		GridBagLayout gbl_surAgregarArticulo = new GridBagLayout();
		gbl_surAgregarArticulo.columnWidths = new int[] { 0, 0, 40, 0, 0 };	//COLUMNAS
		gbl_surAgregarArticulo.rowHeights = new int[] { 0, 0,70 };		//FILAS
		pnl_surAgregarArticulo = new JPanel(gbl_surAgregarArticulo);
		
		pnl_agregarArticulo.add(pnl_centroAgregarArticulo,BorderLayout.CENTER);
		pnl_agregarArticulo.add(pnl_surAgregarArticulo,BorderLayout.SOUTH);
		
		
		//CENTRO:
		
		JLabel lbl_nombre = new JLabel("Nombre:");
		lbl_nombre.setFont(FUENTE);
		GridBagConstraints gbc_nombre = new GridBagConstraints();
		gbc_nombre.anchor = GridBagConstraints.EAST;
		gbc_nombre.gridx = 1;
		gbc_nombre.gridy = 1;
		pnl_centroAgregarArticulo.add(lbl_nombre,gbc_nombre);
		
		JTextField txt_nombre = new JTextField();
		txt_nombre.setFont(FUENTE);
		txt_nombre.setColumns(15);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridx = 3;
		gbc_txtNombre.gridy = 1;
		txt_nombre.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));}
			}
		});
		pnl_centroAgregarArticulo.add(txt_nombre,gbc_txtNombre);
		
		
		
		JLabel lbl_codigo = new JLabel("Codigo:");
		lbl_codigo.setFont(FUENTE);
		GridBagConstraints gbc_codigo = new GridBagConstraints();
		gbc_codigo.anchor = GridBagConstraints.EAST;
		gbc_codigo.gridx = 1;
		gbc_codigo.gridy = 2;
		pnl_centroAgregarArticulo.add(lbl_codigo,gbc_codigo);
		
		JTextField txt_codigo = new JTextField();
		txt_codigo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();}
			}
		});
		txt_codigo.setFont(FUENTE);
		txt_codigo.setColumns(15);
		GridBagConstraints gbc_txtCodigo = new GridBagConstraints();
		gbc_txtCodigo.gridx = 3;
		gbc_txtCodigo.gridy = 2;
		pnl_centroAgregarArticulo.add(txt_codigo,gbc_txtCodigo);
		
		
		
		JLabel lbl_cantidad = new JLabel("Cantidad:");
		lbl_cantidad.setFont(FUENTE);
		GridBagConstraints gbc_cantidad = new GridBagConstraints();
		gbc_cantidad.anchor = GridBagConstraints.EAST;
		gbc_cantidad.gridx = 1;
		gbc_cantidad.gridy = 3;
		pnl_centroAgregarArticulo.add(lbl_cantidad,gbc_cantidad);
		
		JTextField txt_cantidad = new JTextField();
		txt_cantidad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!Character.isDigit(c)) {
					e.consume();}
			}
		});
		txt_cantidad.setFont(FUENTE);
		txt_cantidad.setColumns(15);
		GridBagConstraints gbc_txtCantidad = new GridBagConstraints();
		gbc_txtCantidad.gridx = 3;
		gbc_txtCantidad.gridy = 3;
		pnl_centroAgregarArticulo.add(txt_cantidad,gbc_txtCantidad);
		
		
		
		JLabel lbl_detalle = new JLabel("Detalle:");
		lbl_detalle.setFont(FUENTE);
		GridBagConstraints gbc_detalle = new GridBagConstraints();
		gbc_detalle.anchor = GridBagConstraints.EAST;
		gbc_detalle.gridx = 1;
		gbc_detalle.gridy = 4;
		pnl_centroAgregarArticulo.add(lbl_detalle,gbc_detalle);
		
		JTextField txt_detalle = new JTextField();
		txt_detalle.setFont(FUENTE);
		txt_detalle.setColumns(15);
		GridBagConstraints gbc_txtDetalle = new GridBagConstraints();
		gbc_txtDetalle.gridx = 3;
		gbc_txtDetalle.gridy = 4;
		txt_detalle.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));}
			}
		});
		pnl_centroAgregarArticulo.add(txt_detalle,gbc_txtDetalle);
		
		
		
		JLabel lbl_vencimiento = new JLabel("Vencimiento:");
		lbl_vencimiento.setFont(FUENTE);
		GridBagConstraints gbc_vencimiento = new GridBagConstraints();
		gbc_vencimiento.anchor = GridBagConstraints.EAST;
		gbc_vencimiento.gridx = 1;
		gbc_vencimiento.gridy = 5;
		pnl_centroAgregarArticulo.add(lbl_vencimiento,gbc_vencimiento);
		
		txt_vencimiento = null;
		try {
			MaskFormatter formatter = new MaskFormatter("## / ## / ####");
			txt_vencimiento = new JFormattedTextField(formatter);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		txt_vencimiento.setFont(FUENTE);
		txt_vencimiento.setColumns(15);
		GridBagConstraints gbc_txtVencimiento = new GridBagConstraints();
		gbc_txtVencimiento.gridx = 3;
		gbc_txtVencimiento.gridy = 5;
		pnl_centroAgregarArticulo.add(txt_vencimiento,gbc_txtVencimiento);
		
		
		JLabel lbl_posicion = new JLabel("Posición:");
		lbl_posicion.setFont(FUENTE);
		GridBagConstraints gbc_posicion = new GridBagConstraints();
		gbc_posicion.anchor = GridBagConstraints.EAST;
		gbc_posicion.gridx = 1;
		gbc_posicion.gridy = 6;
		pnl_centroAgregarArticulo.add(lbl_posicion,gbc_posicion);
		
		JTextField txt_posicion = new JTextField();
		txt_posicion.setFont(FUENTE);
		txt_posicion.setColumns(15);
		GridBagConstraints gbc_txtPosicion = new GridBagConstraints();
		gbc_txtPosicion.gridx = 3;
		gbc_txtPosicion.gridy = 6;
		pnl_centroAgregarArticulo.add(txt_posicion,gbc_txtPosicion);
		
		
		JLabel lbl_proveedor = new JLabel("Proveedor:");
		lbl_proveedor.setFont(FUENTE);
		GridBagConstraints gbc_proveedor = new GridBagConstraints();
		gbc_proveedor.anchor = GridBagConstraints.EAST;
		gbc_proveedor.gridx = 1;
		gbc_proveedor.gridy = 7;
		pnl_centroAgregarArticulo.add(lbl_proveedor,gbc_proveedor);
		
		JTextField txt_proveedor = new JTextField();
		txt_proveedor.setFont(FUENTE);
		txt_proveedor.setColumns(15);
		GridBagConstraints gbc_txtProveedor = new GridBagConstraints();
		gbc_txtProveedor.gridx = 3;
		gbc_txtProveedor.gridy = 7;
		txt_proveedor.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));}
			}
		});pnl_centroAgregarArticulo.add(txt_proveedor,gbc_txtProveedor);
		
		
		
		
		//SUR:
		JButton btn_cancelar = new JButton("Cancelar");
		GridBagConstraints gbc_cancelar = new GridBagConstraints();
		gbc_cancelar.anchor = GridBagConstraints.EAST;
		gbc_cancelar.gridx = 1;
		gbc_cancelar.gridy = 1;
		btn_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_nombre.setText("");
				txt_codigo.setText("");
				txt_vencimiento.setText("");
				txt_cantidad.setText("");
				txt_detalle.setText("");
				txt_posicion.setText("");
				txt_proveedor.setText("");
			}
		});
		pnl_surAgregarArticulo.add(btn_cancelar,gbc_cancelar);
		
		
		JButton btn_agregar = new JButton("Agregar");
		GridBagConstraints gbc_agregar = new GridBagConstraints();
		gbc_agregar.gridx = 3;
		gbc_agregar.gridy = 1;
		pnl_surAgregarArticulo.add(btn_agregar,gbc_agregar);
		
		
	}
	
	
	
	private void componentesEliminarArticulos() {
		//--------------ELIMINAR ARTICULO--------------------
		
		pnl_eliminarArticulo = new JPanel(new BorderLayout());
		
		GridBagLayout gbl_norteEliminarArticulo = new GridBagLayout();
		gbl_norteEliminarArticulo.columnWidths = new int[] { 0, 0, 40, 0, 0 };	//COLUMNAS
		gbl_norteEliminarArticulo.rowHeights = new int[] { 0, 0,70 };		//FILAS
		pnl_norteEliminarArticulo = new JPanel(gbl_norteEliminarArticulo);
		
		
		GridBagLayout gbl_centroEliminarArticulo = new GridBagLayout();
		gbl_centroEliminarArticulo.columnWidths = new int[] { 0, 100, 70, 100, 0 };	//COLUMNAS
		gbl_centroEliminarArticulo.rowHeights = new int[] { 0, 70, 70, 70, 70, 70, 70,70, 0 };		//FILAS
		pnl_centroEliminarArticulo = new JPanel(gbl_centroEliminarArticulo);
		
		
		GridBagLayout gbl_surEliminarArticulo = new GridBagLayout();
		gbl_surEliminarArticulo.columnWidths = new int[] { 0, 0, 40, 0, 0 };	//COLUMNAS
		gbl_surEliminarArticulo.rowHeights = new int[] { 0, 0,70 };		//FILAS
		pnl_surEliminarArticulo = new JPanel(gbl_surEliminarArticulo);
		
		pnl_eliminarArticulo.add(pnl_norteEliminarArticulo,BorderLayout.NORTH);
		pnl_eliminarArticulo.add(pnl_centroEliminarArticulo,BorderLayout.CENTER);
		pnl_eliminarArticulo.add(pnl_surEliminarArticulo,BorderLayout.SOUTH);
		
		
		
	}
	
	
	private void componentesConsultarArticulo() {

		//--------------CONSULTAR ARTICULO--------------------
		
		pnl_consultarArticulo = new JPanel();
		
		
		
	}
	
	
	
	public void mostrar() {
		this.setVisible(true);
	}
	
	public void ocultar() {
		this.disable();
		this.setVisible(false);
	}
	
}
