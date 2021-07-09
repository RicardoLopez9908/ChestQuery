package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class VistaContacto {

	private JFrame window;
	private JPanel panel;
	private VistaPremium vistaPremium;

	// ----------------------------------------------------
	private final int ALTO = 200;
	private final int ANCHO = 300;
	private final String IMAGEN_ICONO = "/recursos/ChestQueryIcono.jpg";

	// ----------------------------------------------------

	public VistaContacto(VistaPremium vistaPremium) {
		this.vistaPremium = vistaPremium;
		this.window = new JFrame("E-mail de contacto");
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(IMAGEN_ICONO)));
		this.window.setSize(ANCHO, ALTO);
		this.window.setLocationRelativeTo(null);
		this.window.setDefaultCloseOperation(0); 
		this.window.setResizable(false);
		this.panel = new JPanel();
		this.window.setContentPane(panel);
		GridLayout gl_panel = new GridLayout(0, 1, 0, 0);
		panel.setLayout(gl_panel);
		this.agregarComponentes();
	}

	private void agregarComponentes() {

		JLabel contacto = new JLabel("ricky.990870@gmail.com",SwingConstants.CENTER);
		contacto.setFont(new Font("dialog",3,18));
		panel.add(contacto);
		
		
		JButton botonAceptar = new JButton("Aceptar");
		panel.add(botonAceptar);
		botonAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vistaPremium.habilitar();
				window.dispose();
			}
		});

	}

	public void ejecutar() {
		window.setVisible(true);
	}

}
