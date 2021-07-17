package model;

public class Proveedor {
	private String nombre;
	private String direccion;
	private int telefono;
	private String mail;
	
	
	public Proveedor(String nombre,String direccion,int telefono,String mail) {
		this.nombre=nombre;
		this.direccion=direccion;
		this.telefono=telefono;
		this.mail=mail;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public int getTelefono() {
		return telefono;
	}
	public String getMail() {
		return mail;
	}

	public String toString() {
		return nombre+direccion+telefono+mail;
	}
	
	
	
}
