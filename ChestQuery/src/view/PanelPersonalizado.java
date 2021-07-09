package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class PanelPersonalizado extends JPanel {
	private Image image;
	private String direccion;

	public PanelPersonalizado(String direccion) {
		this.direccion = direccion;
	}

	public void cambiarFondo(String direccionNueva) {
		this.direccion = direccionNueva;
	}
	
	@Override
	public void paint(Graphics g) {
		Dimension dimension = getSize();
		image = new ImageIcon(getClass().getResource(direccion)).getImage();
		g.drawImage(image, 0, 0, dimension.width, dimension.height,null);
		setOpaque(false);
		super.paint(g);
	}
	
	

}
