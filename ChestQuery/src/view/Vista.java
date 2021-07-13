package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Controlador;
import model.Modelo;

public class Vista {

	private Controlador controlador;
	private Modelo modelo;
	private JFrame window;
	private PanelPersonalizado panel;
	private JLabel lblUsuario;
	private JLabel lblContrasena;
	private JPasswordField txtContrasena; 
	private JTextField txtUsuario;
	private JButton bttAceptar;
	JLabel lblContrasenaErronea;
	
	// -------------------------------------------------
	private final String NOMBRE = "ChestQuery";
	private final int ALTO = 400;
	private final int ANCHO = 500;
	private final String IMAGEN_DE_INICIO = "/recursos/ChestQuery.jpg";
	private final String IMAGEN_ICONO = "/recursos/ChestQueryIcono.jpg";
	// --------------------------------------------------

	public Vista(Modelo modelo) {
		this.modelo = modelo;
		modelo.setVista(this);
		controlador = new Controlador(modelo,this);
		window = new JFrame(NOMBRE);
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(IMAGEN_ICONO)));
		window.setSize(ANCHO, ALTO);
		window.setLocationRelativeTo(null); // Para que aparezca en el centro de la pantalla
		window.setDefaultCloseOperation(3); // Para definir EXIT_ON_CLOSE
		panel = new PanelPersonalizado(IMAGEN_DE_INICIO);
		window.setContentPane(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 20, 120, 0 };	//COLUMNAS
		gbl_panel.rowHeights = new int[] { 75, 50, 5, 20,50 };		//FILAS
		panel.setLayout(gbl_panel); // Le asignamos un Layout a este panel
		this.agregarComponentes();

	}

	private void agregarComponentes() {

		// -----------------------TEXTO----------------------------------------

		lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("dialog", 1, 16));
		GridBagConstraints gbc_Usuario = new GridBagConstraints();
		gbc_Usuario.anchor = GridBagConstraints.EAST;
		gbc_Usuario.gridx = 1;
		gbc_Usuario.gridy = 1;
		panel.add(lblUsuario, gbc_Usuario);
		
		lblContrasena = new JLabel("Contraseña:");
		lblContrasena.setFont(new Font("dialog", 1, 16));
		GridBagConstraints gbc_Contraseña = new GridBagConstraints();
		gbc_Contraseña.anchor = GridBagConstraints.EAST;
		gbc_Contraseña.gridx = 1;
		gbc_Contraseña.gridy = 3;
		panel.add(lblContrasena, gbc_Contraseña);

		lblContrasenaErronea = new JLabel("Datos incorrectos");
		lblContrasenaErronea.setForeground(Color.RED);
		GridBagConstraints gbc_ContrasenaErronea = new GridBagConstraints();
		gbc_ContrasenaErronea.gridx = 3;
		gbc_ContrasenaErronea.gridy = 4;
		lblContrasenaErronea.setVisible(false);
		panel.add(lblContrasenaErronea,gbc_ContrasenaErronea);
		
		// ------------------------COMPLETAR FORMULARIO-------------------------

		txtContrasena = new JPasswordField();
		txtContrasena.setFont(new Font("dialog", 1, 12));
		txtContrasena.setColumns(10);
		GridBagConstraints gbc_txtContrasena = new GridBagConstraints();
		gbc_txtContrasena.gridx = 3;
		gbc_txtContrasena.gridy = 3;
		txtContrasena.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));}
			}
		});
		panel.add(txtContrasena, gbc_txtContrasena);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("dialog", 1, 12));
		txtUsuario.setColumns(10);
		GridBagConstraints gbc_txtUsuario = new GridBagConstraints();
		gbc_txtUsuario.gridx = 3;
		gbc_txtUsuario.gridy = 1;
		txtUsuario.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(Character.isLowerCase(c)) {
					e.setKeyChar(Character.toUpperCase(c));}
			}
		});
		panel.add(txtUsuario, gbc_txtUsuario);

		// -----------------------------BOTONES----------------------------------

		bttAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_bttAceptar = new GridBagConstraints();
		gbc_bttAceptar.gridx = 3;
		gbc_bttAceptar.gridy = 5;
		panel.add(bttAceptar, gbc_bttAceptar);
		bttAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtUsuario.getText() != null) {
					controlador.iniciarSesion(txtUsuario.getText(), txtContrasena.getText());
				}
			}
		});
	}
	
	public void contrasenaErronea() {
		txtContrasena.setText("");
		txtUsuario.setText("");
		lblContrasenaErronea.setVisible(true);
	}
	

	public void ocultarPestaña() {
		window.dispose();
	}
	
	
	public void ejecutar() {
		window.setVisible(true);
	}
}
