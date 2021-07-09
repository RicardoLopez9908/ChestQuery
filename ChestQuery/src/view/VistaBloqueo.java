package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import controller.Controlador;


public class VistaBloqueo {

	private JFrame window;
	private JPanel panel;
	private Controlador controlador;
	private JPasswordField txt_contrasena;
	

	// ----------------------------------------------------
	private final int ALTO = 300;
	private final int ANCHO = 400;
	private final String IMAGEN_ICONO = "/recursos/ChestQueryIcono.jpg";
	private final String IMAGEN_BLOQUEO = "src/recursos/bloqueo.png";

	// ----------------------------------------------------

	public VistaBloqueo(Controlador controlador) {
		this.controlador = controlador;
		this.window = new JFrame("Bloqueo ChestQuery");
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(IMAGEN_ICONO)));
		this.window.setSize(ANCHO, ALTO);
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(3); 
		this.window.setResizable(false);
		this.panel = new JPanel();
		this.window.setContentPane(panel);
		GridLayout gl_panel = new GridLayout(0, 1, 0, 0);
		panel.setLayout(gl_panel);
		this.agregarComponentes();
	}

	private void agregarComponentes() {

		ImageIcon imagenBloqueo = new ImageIcon(IMAGEN_BLOQUEO);
		Image img = imagenBloqueo.getImage();
		img = img.getScaledInstance(35, -1, Image.SCALE_SMOOTH);
		imagenBloqueo = new ImageIcon(img);
		
		JLabel imagen = new JLabel(imagenBloqueo);
		panel.add(imagen);
		
		JLabel lbl_contrasena = new JLabel("Ingrese su contraseña:",SwingConstants.CENTER);
		lbl_contrasena.setFont(new Font("dialog",3,15));
		panel.add(lbl_contrasena);
		
		txt_contrasena = new JPasswordField();
		txt_contrasena.setFont(new Font("dialog",3,18));
		panel.add(txt_contrasena);
		
		
		JButton botonAceptar = new JButton("Aceptar");
		panel.add(botonAceptar);
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.desbloquearPantalla(txt_contrasena.getText());
			}
		});

	}
	
	public void ocultarPestana() {
		window.dispose();
	}
	
	public void contrasenaErronea() {
		txt_contrasena.setText("");
	}

	public void ejecutar() {
		window.setVisible(true);
	}

}
