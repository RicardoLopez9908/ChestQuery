package aplicacion;

import javax.swing.UIManager;

import dao.DAOUsuarios;
import model.ModeloUsuarios;
import view.VistaInicio;

public class ChestQuery {
	
	public static void main(String[] args) {
		DAOUsuarios dao = new DAOUsuarios();
		ModeloUsuarios modelo = new ModeloUsuarios(dao);
		VistaInicio vista = new VistaInicio(modelo);
		vista.ejecutar();
		setLookAndFeel();
	}
	public static void setLookAndFeel() {
	    try {
	      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception e) {
	      System.out.println("Error setting native LAF: " + e);
	    }
	}
}
