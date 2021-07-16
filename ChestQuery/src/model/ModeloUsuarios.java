package model;

import java.util.ArrayList;


import controller.Controlador;
import dao.DAOUsuarios;

public class ModeloUsuarios {
	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private DAOUsuarios dao;

	public ModeloUsuarios(DAOUsuarios dao) {
		this.dao = dao;
		this.dao.setModelo(this);
		this.dao.actualizarInformacion();
	}

	public Usuario iniciarSesion(String nombre, String contraseña) {
		for (Usuario usuario : usuarios) {
			if (usuario.compararNombre(nombre) && usuario.compararContraseña(contraseña)) {
				return usuario;
			}
		}
		return null;
	}

	public void modificarDiseno(Usuario usuario, int nuevoDiseño) {
		dao.modificarDisenoUsuario(usuario.getNumeroDeUsuario(), nuevoDiseño);
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
		if(numeroDeUsuario<numeroUsuarioAceptado) {
			controlador.actualizarUsuarioAceptado(usuarios.get(numeroUsuarioAceptado-2));		
		}
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
	
	public boolean actualizarUsuarioCompleto(int numeroDeUsuario,String nombre,String contrasena,Categoria nivelDeAcceso,int diseno) {
		if(!(usuarios.get(numeroDeUsuario-1).getNombre().equals(nombre))) {
			for(Usuario usuario:usuarios) {
				if(usuario.getNombre().equals(nombre)) 
					return false;	
			}
			dao.modificarNombreUsuario(numeroDeUsuario, nombre);
		}
		if(!(usuarios.get(numeroDeUsuario-1).getContrasena().equals(contrasena))) {
			dao.modificarContrasenaUsuario(numeroDeUsuario, contrasena);
		}
		if(!(usuarios.get(numeroDeUsuario-1).getNivelDeAcceso().equals(nivelDeAcceso))) {
			dao.modificarNiveDeAccesoUsuario(numeroDeUsuario, nivelDeAcceso);	
		}
		if(usuarios.get(numeroDeUsuario-1).getDiseño()!=diseno) {
			dao.modificarDisenoUsuario(numeroDeUsuario, diseno);
		}
		usuarios.clear();
		dao.actualizarInformacion();
		return true;
	}
	
	
	
	
	
	

}
