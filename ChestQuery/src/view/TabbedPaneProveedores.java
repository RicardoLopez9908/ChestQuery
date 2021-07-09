package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPaneProveedores extends JTabbedPane{

	
	private JPanel pnl_agregarProveedor;
	private JPanel pnl_eliminarProveedor;
	private JPanel pnl_consultarProveedor;
	
	
	public TabbedPaneProveedores() {
		this.agregarPaneles();
		this.agregarComponentes();
	}
	
	private void agregarPaneles() {
		pnl_agregarProveedor = new JPanel();
		this.addTab("Añadir proveedor",pnl_agregarProveedor);

		pnl_eliminarProveedor = new JPanel();
		this.addTab("Eliminar proveedor",pnl_eliminarProveedor);

		pnl_agregarProveedor = new JPanel();
		this.addTab("Consultar proveedor",pnl_consultarProveedor);

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
