package model;

import java.util.ArrayList;

import dao.DAOClientes;

public class ModeloClientes {
	private ArrayList<Cliente> clientes = new ArrayList<>();
	private DAOClientes dao;

	public ModeloClientes(DAOClientes dao) {
		this.dao = dao;
		this.dao.setModelo(this);
		this.dao.actualizarInformacion();
	}

	public void agregarCliente(Cliente cliente ) {
		clientes.add(cliente);
	}

	public String[][] getClientes() {
		String[][] lista = new String[clientes.size()][4];
		int i = 0;
		//nombre;direccion;telefono;mail
		
		for (Cliente cliente: clientes) {
			lista[i][0] = cliente.getNombre();
			lista[i][1] = cliente.getDireccion();
			lista[i][2] = String.valueOf(cliente.getTelefono());
			lista[i][3] = cliente.getMail();

			i++;

		}

		return lista;
	}

	public void borrarCliente(String nombre,String direccion,long telefono,String mail) {
		dao.borrarCliente(nombre, direccion, telefono, mail);
		clientes.clear();
		dao.actualizarInformacion();
	}
	
	public void agregarClienteDAO(String nombre,String direccion,long telefono,String mail) {
		dao.agregarCliente(nombre, direccion, telefono, mail);
		clientes.clear();
		dao.actualizarInformacion();
	}
	
	
	
	public void actualizarCliente(Cliente anterioresDatosCliente,Cliente nuevosDatosCliente) {
		dao.actualizarCliente(anterioresDatosCliente,nuevosDatosCliente);
		clientes.clear();
		dao.actualizarInformacion();	
	}
	
	
}
