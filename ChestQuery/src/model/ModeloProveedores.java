package model;

import java.util.ArrayList;

import dao.DAOProveedores;

public class ModeloProveedores {
	private ArrayList<Proveedor> proveedores = new ArrayList<>();
	private DAOProveedores dao;

	public ModeloProveedores(DAOProveedores dao) {
		this.dao = dao;
		this.dao.setModelo(this);
		this.dao.actualizarInformacion();
	}

	public void agregarProveedor(Proveedor proveedor ) {
		proveedores.add(proveedor);
	}

	public String[][] getProveedores() {
		String[][] lista = new String[proveedores.size()][4];
		int i = 0;
		//nombre;direccion;telefono;mail
		
		for (Proveedor proveedor: proveedores) {
			lista[i][0] = proveedor.getNombre();
			lista[i][1] = proveedor.getDireccion();
			lista[i][2] = String.valueOf(proveedor.getTelefono());
			lista[i][3] = proveedor.getMail();

			i++;

		}

		return lista;
	}

	public void borrarProveedor(String nombre,String direccion,long telefono,String mail) {
		dao.borrarProveedor(nombre, direccion, telefono, mail);
		proveedores.clear();
		dao.actualizarInformacion();
	}
	
	public void agregarProveedorDAO(String nombre,String direccion,long telefono,String mail) {
		dao.agregarProveedor(nombre, direccion, telefono, mail);
		proveedores.clear();
		dao.actualizarInformacion();
	}
	
	
	
	public void actualizarProveedor(Proveedor anterioresDatosProveedor,Proveedor nuevosDatosProveedor) {
		dao.actualizarProveedor(anterioresDatosProveedor,nuevosDatosProveedor);
		proveedores.clear();
		dao.actualizarInformacion();	
	}
	
	
}
