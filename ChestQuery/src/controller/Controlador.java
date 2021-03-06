package controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import dao.DAOClientes;
import dao.DAOInventario;
import dao.DAOProveedores;
import dao.DAOReportes;
import model.Articulo;
import model.Categoria;
import model.Cliente;
import model.ModeloClientes;
import model.ModeloInventario;
import model.ModeloProveedores;
import model.ModeloReportes;
import model.ModeloUsuarios;
import model.Proveedor;
import model.Reporte;
import model.Usuario;
import view.VistaBloqueo;
import view.VistaContacto;
import view.VistaInicio;
import view.VistaPersonalizarFondo;
import view.VistaPrincipal;

public class Controlador{

	private ModeloUsuarios modeloUsuarios;
	private ModeloInventario modeloInventario;
	private ModeloClientes modeloClientes;
	private ModeloProveedores modeloProveedores;
	private ModeloReportes modeloReportes;
	private VistaPersonalizarFondo vistaPersonalizarFondo;
	private VistaInicio vista;
	private VistaPrincipal vistaPrincipal;
	private Usuario usuarioAceptado;
	private VistaBloqueo vistaBloqueo;
	private VistaContacto vistaContacto;

	
	public Controlador(ModeloUsuarios modelo, VistaInicio vista) {
		this.modeloUsuarios = modelo;
		this.vista = vista;
		this.modeloReportes = new ModeloReportes(new DAOReportes());
	}

	// ------------------------------------------------------------

	public void iniciarSesion(String nombre,String contraseña) {
		usuarioAceptado = modeloUsuarios.iniciarSesion(nombre, contraseña);
		
		if(usuarioAceptado!=null){
			vista.ocultarPestaña();
			vistaPrincipal = new VistaPrincipal(this,usuarioAceptado);
			vistaPrincipal.ejecutar();
		}else {
			System.out.println("NO COINCIDEN EL NOMBRE Y CONTRASEÑA INGRESADOS");
			vista.contrasenaErronea();
			}
	
	}
	

	public void agregarVistaPremium(VistaPrincipal vistaPrincipal) {
		this.vistaPrincipal = vistaPrincipal;
	}


	public void personalizarFondo() {
		vistaPrincipal.deshabilitar();
		vistaPersonalizarFondo = new VistaPersonalizarFondo(this);
		vistaPersonalizarFondo.ejecutar();
	}

	public void seleccionarFondo(int diseno) {
		modeloUsuarios.modificarDiseno(usuarioAceptado,diseno);
		vistaPrincipal.habilitar();
		vistaPrincipal.actualizarFondo(diseno);
	}

	public void contacto() {
		vistaPrincipal.deshabilitar();
		vistaContacto = new VistaContacto(vistaPrincipal);
		vistaContacto.ejecutar();		
	}
	
	public void bloquearPantalla() {
		vistaPrincipal.deshabilitar();
		vistaBloqueo = new VistaBloqueo(this);
		vistaBloqueo.ejecutar();
	}
	
	public void desbloquearPantalla(String contraseña) {
		Usuario usuario = modeloUsuarios.iniciarSesion(usuarioAceptado.getNombre(), contraseña); 
		if(usuario!=null) {
			vistaBloqueo.ocultarPestana();
			vistaPrincipal.habilitar();
		}else {
			vistaBloqueo.contrasenaErronea();
		}
	}

	public void actualizarUsuarioAceptado(Usuario usuarioAceptado) {
		this.usuarioAceptado = usuarioAceptado;
	}

	
	public void crearModeloInventario() {
			this.modeloInventario = new ModeloInventario(new DAOInventario());
	}
	public void crearModeloClientes() {
		this.modeloClientes = new ModeloClientes(new DAOClientes());
	}
	public void crearModeloProveedores() {
		this.modeloProveedores = new ModeloProveedores(new DAOProveedores());
	}
	
	

	public String[][] getDatosTablaUsuarios(){
		return modeloUsuarios.getUsuarios();
	}
	
	public String[][] getDatosTablaArticulos(){
		return modeloInventario.getArticulos();
	}

	public String[][] getDatosTablaClientes(){
		return modeloClientes.getClientes();
	}

	public String[][] getDatosTablaProveedores(){
		return modeloProveedores.getProveedores();
	}
	
	public String[][] getDatosTablaReportes(){
		return modeloReportes.getReportes();
	}

	
	public boolean borrarUsuario(int numeroDeUsuario) {
		if(numeroDeUsuario != usuarioAceptado.getNumeroDeUsuario()) {
			modeloUsuarios.borrarUsuario(numeroDeUsuario,this,usuarioAceptado.getNumeroDeUsuario());			
			return true;
		}
			return false;
	}
	
	
	public void borrarArticulo(long codigo,String nombre, int posicion, int cantidad, String proveedor, String vencimiento,String detalle ) {
		modeloInventario.borrarArticulo(codigo, nombre, posicion, cantidad, proveedor, vencimiento, detalle);			
		this.agregarReporte("Eliminación de " + cantidad +" "+ nombre + ", de la posición: " + posicion, usuarioAceptado.getNombre(),usuarioAceptado.getNumeroDeUsuario());
	}
	
	public void borrarCliente(String nombre,String direccion,long telefono,String mail) {
		modeloClientes.borrarCliente( nombre, direccion, telefono, mail);			
		this.agregarReporte("Eliminación del cliente " + nombre + ", con el mail: " + mail, usuarioAceptado.getNombre(),usuarioAceptado.getNumeroDeUsuario());
	}
	
	public void borrarProveedor(String nombre,String direccion,long telefono,String mail) {
		modeloProveedores.borrarProveedor( nombre, direccion, telefono, mail);			
		this.agregarReporte("Eliminación del proveedor " + nombre + ", con el mail: " + mail, usuarioAceptado.getNombre(),usuarioAceptado.getNumeroDeUsuario());
		}
	
	
	public boolean agregarUsuario(String nombre,String contrasena, String nivelDeAcceso) {
		if(modeloUsuarios.agregarUsuarioDAO(nombre, contrasena, nivelDeAcceso)) {	
			return true;
		}else {
			return false;
		}
		
	}
	
	public void agregarArticulo(String codigo,String nombre,String cantidad,String detalle,String posicion,String proveedor,String vencimiento) {
		modeloInventario.agregarArticuloDAO(codigo,nombre,cantidad,detalle,posicion,proveedor,vencimiento);
		this.agregarReporte("Agregación de " + cantidad +" "+ nombre + ", en la posición: " + posicion, usuarioAceptado.getNombre(),usuarioAceptado.getNumeroDeUsuario());
	}
	
	public void agregarCliente(String nombre,String direccion,long telefono,String mail) {
		modeloClientes.agregarClienteDAO( nombre, direccion, telefono, mail);
		this.agregarReporte("Agregación del cliente " + nombre + ", con el mail: " + mail, usuarioAceptado.getNombre(),usuarioAceptado.getNumeroDeUsuario());
	}
	
	public void agregarProveedor(String nombre,String direccion,long telefono,String mail) {
		modeloProveedores.agregarProveedorDAO( nombre, direccion, telefono, mail);
		this.agregarReporte("Agregación del proveedor " + nombre + ", con el mail: " + mail, usuarioAceptado.getNombre(),usuarioAceptado.getNumeroDeUsuario());
	}
	
	
	public boolean actualizarUsuarioCompleto(int numeroDeUsuario,String nombre,String contrasena,String nivelDeAcceso, int diseno) {
		Categoria nivel = null;
		switch(nivelDeAcceso) {
		case "DEFAULT":
			nivel = Categoria.DEFAULT;
			break;
		case "MEDIUM":
			nivel = Categoria.MEDIUM;
			break;
		case "PREMIUM":
			nivel = Categoria.PREMIUM;
			break;
		}
		if(modeloUsuarios.actualizarUsuarioCompleto(numeroDeUsuario, nombre, contrasena, nivel, diseno)) {
			return true;
		}else {
			return false;
		}
	}
	
	public void actualizarArticulo(Articulo anterioresDatosArticulo,Articulo nuevosDatosArticulo) {
		modeloInventario.actualizarArticulo(anterioresDatosArticulo,nuevosDatosArticulo);
		this.agregarReporte("Modificación de " + anterioresDatosArticulo.getCantidad() +" "+ anterioresDatosArticulo.getNombre() + ", en la posición: " + anterioresDatosArticulo.getPosicion(), usuarioAceptado.getNombre(),usuarioAceptado.getNumeroDeUsuario());
	}
	
	public void actualizarCliente(Cliente anterioresDatosCliente,Cliente nuevosDatosCliente) {
		modeloClientes.actualizarCliente(anterioresDatosCliente,nuevosDatosCliente);
		this.agregarReporte("Modificación del cliente " + anterioresDatosCliente.getNombre() + ", con el mail: " + anterioresDatosCliente.getMail(), usuarioAceptado.getNombre(),usuarioAceptado.getNumeroDeUsuario());
	}
	
	public void actualizarProveedor(Proveedor anterioresDatosProveedor,Proveedor nuevosDatosProveedor) {
		modeloProveedores.actualizarProveedor(anterioresDatosProveedor,nuevosDatosProveedor);
		this.agregarReporte("Modificación del proveedor " + anterioresDatosProveedor.getNombre() + ", con el mail: " + anterioresDatosProveedor.getMail(), usuarioAceptado.getNombre(),usuarioAceptado.getNumeroDeUsuario());
	}
	
	
	
	private void agregarReporte(String instruccionRealizada,String usuario,int numeroDeUsuario) {
		DateTimeFormatter dtf  = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String fecha = dtf.format(LocalDateTime.now());
		modeloReportes.agregarReporteDAO(instruccionRealizada,usuario,numeroDeUsuario,fecha);
	}
	
	
	
	
}
