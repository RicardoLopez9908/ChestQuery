package model;

import java.util.ArrayList;

import javax.swing.table.TableModel;

import controller.Controlador;
import dao.DAO;
import view.TabbedPaneUsuarios;
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
				System.out.println("USUARIO: " + usuario.getNombre());
				System.out.println("NIVEL DE ACCESO: " + usuario.getNivelDeAcceso());
				return usuario;
			}
		}
		return null;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}

	public void modificarDiseño(Usuario usuario, int nuevoDiseño) {
		dao.modificarDiseño(usuario.getNumeroDeUsuario(), nuevoDiseño);
	}

	public void agregarUsuarios(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public String[][] getUsuarios() {
		String[][] lista = new String[usuarios.size()][5];
		int i = 0;
		Categoria nivelDeAcceso;
		for (Usuario usuario : usuarios) {
			lista[i][0] = usuario.getNumeroDeUsuario()+"";
			lista[i][1] = usuario.getNombre();
			lista[i][2] = usuario.getContrasena(); 
			nivelDeAcceso = usuario.getNivelDeAcceso();
			switch (nivelDeAcceso) {
			case DEFAULT:
				lista[i][3] = "DEFAULT"; 
				break;
			case MEDIUM:
				lista[i][3] = "MEDIUM"; 
				break;
			case PREMIUM:
				lista[i][3] = "PREMIUM"; 
				break;
			}
			lista[i][4] = usuario.getDiseño() + ""; 
			
			i++;

		}

		return lista;
	}

	public void borrarUsuario(int numeroDeUsuario, Controlador controlador, int numeroUsuarioAceptado) {
		dao.borrarUsuario(numeroDeUsuario);
		usuarios.clear();
		dao.actualizarInformacion();
		controlador.actualizarUsuarioAceptado(usuarios.get(numeroUsuarioAceptado-2));
	}
	
	public boolean agregarUsuarioDAO(String nombre, String contrasena, String nivelDeAcceso) {
		for(Usuario usuario: usuarios) {
			if(usuario.getNombre().equals(nombre)) {
				return false;
			}
		}
		dao.agregarUsuario(nombre,contrasena,nivelDeAcceso);
		usuarios.clear();
		dao.actualizarInformacion();
		return true;
		
	}
	
	
	
	
	
	

}
