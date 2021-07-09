package model;

import java.util.ArrayList;

import dao.DAO;
import view.Vista;
import view.VistaPremium;

public class Modelo {
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private DAO dao;
	private Vista vista;
	
	public Modelo(DAO dao) {
		this.dao = dao;
		this.dao.setModelo(this);
		this.dao.actualizarInformacion();
	}
	
	
	public Usuario iniciarSesion(String nombre, String contraseña) {
		for (Usuario usuario : usuarios) {
				if (usuario.compararNombre(nombre) && usuario.compararContraseña(contraseña)) {
					System.out.println("USUARIO: "  + usuario.getNombre());
					System.out.println("NIVEL DE ACCESO: "  + usuario.getNivelDeAcceso());
					return usuario;
				}
			}
		return null;
	}
	
	
	public void setVista(Vista vista) {
		this.vista = vista;
	}

	
	public void modificarDiseño(Usuario usuario,int nuevoDiseño) {
		dao.modificarDiseño(usuario.getNumeroDeUsuario(), nuevoDiseño);
	}
	

	public void agregarUsuarios(Usuario usuario) {
		usuarios.add(usuario);
	}

}
