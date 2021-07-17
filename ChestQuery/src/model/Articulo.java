package model;

public class Articulo {

	private String nombre;
	private long codigo;
	private int cantidad;
	private int posicion;
	private String proveedor;
	private String vencimiento;
	private String detalle;
	
	
	public Articulo(String nombre,long codigo,int cantidad, int posicion, String proveedor, String vencimiento,String detalle) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.cantidad = cantidad;
		this.posicion = posicion;
		this.proveedor = proveedor;
		this.vencimiento = vencimiento;
		this.detalle = detalle;
	}
	

	public String getNombre() {
		return nombre;
	}


	public long getCodigo() {
		return codigo;
	}


	public int getCantidad() {
		return cantidad;
	}


	public int getPosicion() {
		return posicion;
	}


	public String getProveedor() {
		return proveedor;
	}


	public String getVencimiento() {
		return vencimiento;
	}


	public String getDetalle() {
		return detalle;
	}

	public String toString() {
		return nombre + codigo + cantidad + posicion + proveedor + vencimiento + detalle; 
	}
	
}
