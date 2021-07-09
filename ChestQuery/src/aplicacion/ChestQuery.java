package aplicacion;

import dao.DAO;
import model.Modelo;
import view.Vista;

public class ChestQuery {
	
	public static void main(String[] args) {
		DAO dao = new DAO();
		Modelo modelo = new Modelo(dao);
		Vista vista = new Vista(modelo);
		vista.ejecutar();
	}
}
