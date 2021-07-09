package view;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class TabbedPaneClientes extends JTabbedPane{

	
	private JPanel pnl_agregarCliente;
	private JPanel pnl_eliminarCliente;
	private JPanel pnl_consultarCliente;
	
	
	public TabbedPaneClientes() {
		this.agregarPaneles();
		this.agregarComponentes();
	}
	
	private void agregarPaneles() {
		pnl_agregarCliente = new JPanel();
		this.addTab("Añadir cliente",pnl_agregarCliente);

		pnl_eliminarCliente = new JPanel();
		this.addTab("Eliminar cliente",pnl_eliminarCliente);

		pnl_agregarCliente = new JPanel();
		this.addTab("Consultar cliente",pnl_consultarCliente);

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
