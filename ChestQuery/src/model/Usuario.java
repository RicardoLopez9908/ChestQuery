package model;

public class Usuario {
	
	private String nombre;
	private String contraseña;
	private Categoria nivelDeAcceso;
	private int diseño;
	private int numeroDeUsuario;
	
	
	
	public Usuario(String nombre, String contraseña, Categoria nivelDeAcceso, int diseño, int numeroDeUsuario) {
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.nivelDeAcceso = nivelDeAcceso;
		this.diseño = diseño;
		this.numeroDeUsuario = numeroDeUsuario;
	}

	
	//-------------------------------------------------------------------------
	
	public boolean compararContraseña(String contraseña) {
		return this.contraseña.equals(contraseña);
	}
	
	public boolean compararNombre(String nombre) {
		return this.nombre.equals(nombre);
	}
	
	public Categoria getNivelDeAcceso() {
		return this.nivelDeAcceso;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public int getDiseño() {
		return this.diseño;
	}
	
	public int getNumeroDeUsuario() {
		return numeroDeUsuario;
	}
	
}
