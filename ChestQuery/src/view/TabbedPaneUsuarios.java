package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPaneUsuarios extends JTabbedPane{

	
	private JPanel pnl_agregarUsuarios;
	private JPanel pnl_eliminarUsuarios;
	private JPanel pnl_consultarUsuarios;
	
	
	public TabbedPaneUsuarios() {
		this.agregarPaneles();
		this.agregarComponentes();
	}
	
	private void agregarPaneles() {
		pnl_agregarUsuarios = new JPanel();
		this.addTab("Añadir usuario",pnl_agregarUsuarios);

		pnl_eliminarUsuarios = new JPanel();
		this.addTab("Eliminar usuario",pnl_eliminarUsuarios);

		pnl_agregarUsuarios = new JPanel();
		this.addTab("Consultar usuario",pnl_consultarUsuarios);

	}
	
	private void agregarComponentes() {
		
	}
	
	public void mostrar() {
		this.setVisible(true);
	}
	
	public void ocultar() {
		this.setVisible(false);
	}
	
}
