package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPaneInventario extends JTabbedPane{

	
	private JPanel pnl_agregarArticulo;
	private JPanel pnl_eliminarArticulo;
	private JPanel pnl_consultarArticulo;
	
	
	public TabbedPaneInventario() {
		this.agregarPaneles();
		this.agregarComponentes();
	}
	
	private void agregarPaneles() {
		pnl_agregarArticulo = new JPanel();
		this.addTab("Agregar articulo",pnl_agregarArticulo);

		pnl_eliminarArticulo = new JPanel();
		this.addTab("Eliminar articulo",pnl_eliminarArticulo);

		pnl_agregarArticulo = new JPanel();
		this.addTab("Consultar articulo",pnl_consultarArticulo);

	}
	
	private void agregarComponentes() {
		
	}
	
	public void mostrar() {
		this.setVisible(true);
	}
	
	public void ocultar() {
		this.disable();
		this.setVisible(false);
	}
	
}
