package view;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Controlador;

public class VistaPersonalizarFondo {

	private JFrame window;
	private JPanel panel;
	private Controlador controlador;

	// ----------------------------------------------------
	private final int ALTO = 400;
	private final int ANCHO = 500;
	private final String IMAGEN_ICONO = "/recursos/ChestQueryIcono.jpg";
	private String direccionImagenBotones = "src/recursos/FONDO_BOTONES.jpg";
	private String direccionImagenBotones1 = "src/recursos/FONDO_BOTONES1.jpg";
	private String direccionImagenBotones2 = "src/recursos/FONDO_BOTONES2.jpg";
	private String direccionImagenBotones3 = "src/recursos/FONDO_BOTONES3.jpg";
	private String direccionImagenBotones4 = "src/recursos/FONDO_BOTONES4.jpg";
	private String direccionImagenBotones5 = "src/recursos/FONDO_BOTONES5.jpg";

	// ----------------------------------------------------

	public VistaPersonalizarFondo(Controlador controlador) {
		this.controlador = controlador;
		this.window = new JFrame("Opciones de personalización");
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(IMAGEN_ICONO)));
		this.window.setSize(ANCHO, ALTO);
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(0); // Para definir EXIT_ON_CLOSE
		this.window.setResizable(false);
		this.panel = new JPanel();
		this.window.setContentPane(panel);
		this.panel.setLayout(new GridLayout());
		this.agregarComponentes();
	}

	private void agregarComponentes() {

		JButton boton0 = new JButton();
		ImageIcon icon0 = new ImageIcon(direccionImagenBotones);
		Image img = icon0.getImage();
		Image newimg = img.getScaledInstance(83, -1, Image.SCALE_SMOOTH);
		icon0 = new ImageIcon(newimg);
		boton0.setIcon(icon0);
		panel.add(boton0);
		boton0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.seleccionarFondo(0);
				window.dispose();
			}
		});

		JButton boton1 = new JButton();
		ImageIcon icon1 = new ImageIcon(direccionImagenBotones1);
		img = icon1.getImage();
		newimg = img.getScaledInstance(83, -1,Image.SCALE_SMOOTH);
		icon1 = new ImageIcon(newimg);
		boton1.setIcon(icon1);
		panel.add(boton1);
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.seleccionarFondo(1);
				window.dispose();
			}
		});
		JButton boton2 = new JButton();
		ImageIcon icon2 = new ImageIcon(direccionImagenBotones2);
		img = icon2.getImage();
		newimg = img.getScaledInstance(83, -1,Image.SCALE_SMOOTH);
		icon2 = new ImageIcon(newimg);
		boton2.setIcon(icon2);
		panel.add(boton2);
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.seleccionarFondo(2);
				window.dispose();
			}
		});

		JButton boton3 = new JButton();
		ImageIcon icon3 = new ImageIcon(direccionImagenBotones3);
		img = icon3.getImage();
		newimg = img.getScaledInstance(83, -1, Image.SCALE_SMOOTH);
		icon3 = new ImageIcon(newimg);
		boton3.setIcon(icon3);
		panel.add(boton3);
		boton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.seleccionarFondo(3);
				window.dispose();
			}
		});

		JButton boton4 = new JButton();
		ImageIcon icon4 = new ImageIcon(direccionImagenBotones4);
		img = icon4.getImage();
		newimg = img.getScaledInstance(83, -1, Image.SCALE_SMOOTH);
		icon4 = new ImageIcon(newimg);
		boton4.setIcon(icon4);
		panel.add(boton4);
		boton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.seleccionarFondo(4);
				window.dispose();
			}
		});

		JButton boton5 = new JButton();
		ImageIcon icon5 = new ImageIcon(direccionImagenBotones5);
		img = icon5.getImage();
		newimg = img.getScaledInstance(83, -1, Image.SCALE_SMOOTH);
		icon5 = new ImageIcon(newimg);
		boton5.setIcon(icon5);
		panel.add(boton5);
		boton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.seleccionarFondo(5);
				window.dispose();
			}
		});

	}

	public void ejecutar() {
		window.setVisible(true);
	}

}
