package model;

import java.util.ArrayList;

import dao.DAOInventario;

public class ModeloInventario {
	private ArrayList<Articulo> articulos = new ArrayList<>();
	private DAOInventario dao;

	public ModeloInventario(DAOInventario dao) {
		this.dao = dao;
		this.dao.setModelo(this);
		this.dao.actualizarInformacion();
	}

	public void agregarArticulo(Articulo articulo) {
		articulos.add(articulo);
	}

	public String[][] getArticulos() {
		String[][] lista = new String[articulos.size()][7];
		int i = 0;
		// "Codigo", "Nombre", "Posición", "Cantidad", "Proveedor","Vencimiento",
		// "Detalle"
		for (Articulo articulo : articulos) {
			lista[i][0] = String.valueOf(articulo.getCodigo());
			lista[i][1] = articulo.getNombre();
			lista[i][2] = String.valueOf(articulo.getPosicion());
			lista[i][3] = String.valueOf(articulo.getCantidad());
			lista[i][4] = articulo.getProveedor();
			lista[i][5] = articulo.getVencimiento();
			lista[i][6] = articulo.getDetalle();

			i++;

		}

		return lista;
	}

	public void borrarArticulo(long codigo, String nombre, int posicion, int cantidad, String proveedor,
		String vencimiento, String detalle) {
		dao.borrarArticulo(codigo, nombre, posicion, cantidad, proveedor, vencimiento, detalle);
		articulos.clear();
		dao.actualizarInformacion();
	}

	public void agregarArticuloDAO(String codigo,String nombre,String cantidad,String detalle,String posicion,String proveedor,String vencimiento) {
		dao.agregarArticulo(codigo,nombre,cantidad,detalle,posicion,proveedor,vencimiento);
		articulos.clear();
		dao.actualizarInformacion();
	}
	
	public void actualizarArticulo(Articulo anterioresDatosArticulo,Articulo nuevosDatosArticulo) {
		dao.actualizarArticulo(anterioresDatosArticulo,nuevosDatosArticulo);
		articulos.clear();
		dao.actualizarInformacion();	
	}
	
	
}
