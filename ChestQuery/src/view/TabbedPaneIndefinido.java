package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPaneIndefinido extends JTabbedPane{

	
	private JPanel pnl_agregarIndefinido;
	private JPanel pnl_eliminarIndefinido;
	private JPanel pnl_consultarIndefinido;
	
	
	public TabbedPaneIndefinido() {
		this.agregarPaneles();
		this.agregarComponentes();
	}
	
	private void agregarPaneles() {
		pnl_agregarIndefinido = new JPanel();
		this.addTab("Añadir indefinido",pnl_agregarIndefinido);

		pnl_eliminarIndefinido = new JPanel();
		this.addTab("Eliminar indefinido",pnl_eliminarIndefinido);

		pnl_agregarIndefinido = new JPanel();
		this.addTab("Consultar indefinido",pnl_consultarIndefinido);

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
