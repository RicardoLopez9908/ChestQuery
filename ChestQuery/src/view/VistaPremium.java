package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import controller.Controlador;
import model.Usuario;

public class VistaPremium {

	private Controlador controlador;
	private JFrame window;
	private JSplitPane panel;

	private PanelPersonalizado panel_1;
	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;

	private JPanel panel_2;

	// ----------------------------------------------------------
	private final int ALTO = 800;
	private final int ANCHO = 1000;
	private final String IMAGEN_ICONO = "/recursos/ChestQueryIcono.jpg";
	private String direccionImagenBotones = "/recursos/FONDO_BOTONES.jpg";
	private String direccionImagenBotones1 = "/recursos/FONDO_BOTONES1.jpg";
	private String direccionImagenBotones2 = "/recursos/FONDO_BOTONES2.jpg";
	private String direccionImagenBotones3 = "/recursos/FONDO_BOTONES3.jpg";
	private String direccionImagenBotones4 = "/recursos/FONDO_BOTONES4.jpg";
	private String direccionImagenBotones5 = "/recursos/FONDO_BOTONES5.jpg";

	// ----------------------------------------------------------

	public VistaPremium(Controlador controlador) {
		this.controlador = controlador;
		this.controlador.agregarVistaPremium(this);
		window = new JFrame("ChestQuery");
		window.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(IMAGEN_ICONO)));
		window.setSize(ANCHO, ALTO);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(3); // Para definir EXIT_ON_CLOSE
		panel = new JSplitPane();
		window.setContentPane(panel);
		this.agregarPanelesSecunadarios();
		this.agregarComponentesPanel1();
	}

	private void agregarPanelesSecunadarios() {
		panel_1 = new PanelPersonalizado(direccionImagenBotones);
		GridLayout gl_panel_1 = new GridLayout(0, 1, 0, 0);
		panel_1.setLayout(gl_panel_1);
		panel.setLeftComponent(panel_1);

		panel_2 = new JPanel();
		CardLayout cd_panel_2 = new CardLayout();
		panel_2.setLayout(cd_panel_2);
		panel.setRightComponent(panel_2);
}

	private void asignarPanelPrincipal(JTabbedPane panel) {
		panel_2.removeAll();
		panel_2.add(panel);
		panel_2.repaint();
		panel_2.revalidate();	
	}
	
	private void agregarComponentesPanel1() {

		// -----------------------------BOTONES--------------------------

		boton1 = new JButton("Inventario");
		boton1.setFont(new Font("dialog", 3, 15));
		boton1.setFocusPainted(false);
		boton1.setContentAreaFilled(false);
		boton1.setBorderPainted(true);
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabbedPaneInventario nuevoPanel = new TabbedPaneInventario(); 
				asignarPanelPrincipal(nuevoPanel);
			}
		});
		panel_1.add(boton1);

		boton2 = new JButton("Clientes");
		boton2.setFont(new Font("dialog", 3, 15));
		boton2.setFocusPainted(false);
		boton2.setContentAreaFilled(false);
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabbedPaneClientes nuevoPanel = new TabbedPaneClientes(); 
				asignarPanelPrincipal(nuevoPanel);
			}
		});
		panel_1.add(boton2);

		boton3 = new JButton("Proveedores");
		boton3.setFont(new Font("dialog", 3, 15));
		boton3.setFocusPainted(false);
		boton3.setContentAreaFilled(false);
		boton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabbedPaneProveedores nuevoPanel = new TabbedPaneProveedores(); 
				asignarPanelPrincipal(nuevoPanel);
			}
		});
		panel_1.add(boton3);

		boton4 = new JButton("Usuarios");
		boton4.setFont(new Font("dialog", 3, 15));
		boton4.setFocusPainted(false);
		boton4.setContentAreaFilled(false);
		boton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabbedPaneUsuarios nuevoPanel = new TabbedPaneUsuarios(controlador);
				asignarPanelPrincipal(nuevoPanel);
			}
		});
		panel_1.add(boton4);

		boton5 = new JButton("indefinido");
		boton5.setFont(new Font("dialog", 3, 15));
		boton5.setFocusPainted(false);
		boton5.setContentAreaFilled(false);
		boton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabbedPaneIndefinido nuevoPanel = new TabbedPaneIndefinido(); 
				asignarPanelPrincipal(nuevoPanel);
			}
		});
		panel_1.add(boton5);

		// ------------------------BARRA DE MENU---------------------------------

		JMenuBar menuBar = new JMenuBar();
		window.setJMenuBar(menuBar);

		JMenu mnAjustes = new JMenu("Ajustes");
		menuBar.add(mnAjustes);

		JMenuItem item1 = new JMenuItem("Personalizar fondo");
		item1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.personalizarFondo();
			}
		});

		mnAjustes.add(item1);

		JMenuItem itemBloqueo = new JMenuItem("Bloquear pantalla");
		itemBloqueo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.bloquearPantalla();
			}
		});mnAjustes.add(itemBloqueo);

		JMenuItem item3 = new JMenuItem("3");
		mnAjustes.add(item3);

		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem itemContacto = new JMenuItem("Contacto");
		itemContacto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.contacto();
			}
		});
		mnAyuda.add(itemContacto);


	}

	public void actualizarFondo(int tipo) {
		switch (tipo) {
		case 0:
			panel_1.cambiarFondo(direccionImagenBotones);
			boton1.setForeground(Color.BLACK);
			boton2.setForeground(Color.BLACK);
			boton3.setForeground(Color.BLACK);
			boton4.setForeground(Color.BLACK);
			boton5.setForeground(Color.BLACK);
			controlador.setDiseño(0);
			break;
		case 1:
			panel_1.cambiarFondo(direccionImagenBotones1);
			boton1.setForeground(Color.WHITE);
			boton2.setForeground(Color.WHITE);
			boton3.setForeground(Color.WHITE);
			boton4.setForeground(Color.WHITE);
			boton5.setForeground(Color.WHITE);
			controlador.setDiseño(1);
			break;
		case 2:
			panel_1.cambiarFondo(direccionImagenBotones2);
			boton1.setForeground(Color.BLACK);
			boton2.setForeground(Color.BLACK);
			boton3.setForeground(Color.BLACK);
			boton4.setForeground(Color.BLACK);
			boton5.setForeground(Color.BLACK);
			controlador.setDiseño(2);
			break;
		case 3:
			panel_1.cambiarFondo(direccionImagenBotones3);
			boton1.setForeground(Color.BLACK);
			boton2.setForeground(Color.BLACK);
			boton3.setForeground(Color.BLACK);
			boton4.setForeground(Color.BLACK);
			boton5.setForeground(Color.BLACK);
			controlador.setDiseño(3);
			break;
		case 4:
			panel_1.cambiarFondo(direccionImagenBotones4);
			boton1.setForeground(Color.BLACK);
			boton2.setForeground(Color.BLACK);
			boton3.setForeground(Color.BLACK);
			boton4.setForeground(Color.BLACK);
			boton5.setForeground(Color.BLACK);
			controlador.setDiseño(4);
			break;
		case 5:
			panel_1.cambiarFondo(direccionImagenBotones5);
			boton1.setForeground(Color.WHITE);
			boton2.setForeground(Color.WHITE);
			boton3.setForeground(Color.WHITE);
			boton4.setForeground(Color.WHITE);
			boton5.setForeground(Color.WHITE);
			controlador.setDiseño(5);
			break;
		default:
			break;
		}
	}

	public void setUsuario(Usuario usuario) {
		this.actualizarFondo(usuario.getDiseño());
	}

	public void ejecutar() {
		window.setVisible(true);
	}

	public void deshabilitar() {
		window.disable();
		window.setVisible(false);
	}

	public void habilitar() {
		window.enable();
		window.setVisible(true);
	}

}
