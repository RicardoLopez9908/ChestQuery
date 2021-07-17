package controller;


import dao.DAOInventario;
import model.Categoria;
import model.ModeloInventario;
import model.ModeloUsuarios;
import model.Usuario;
import view.VistaInicio;
import view.VistaBloqueo;
import view.VistaContacto;
import view.VistaPersonalizarFondo;
import view.VistaPrincipal;

public class Controlador{

	private ModeloUsuarios modeloUsuarios;
	private ModeloInventario modeloInventario;
	private VistaPersonalizarFondo vistaPersonalizarFondo;
	private VistaInicio vista;
	private VistaPrincipal vistaPrincipal;
	private Usuario usuarioAceptado;
	private VistaBloqueo vistaBloqueo;
	private VistaContacto vistaContacto;

	
	public Controlador(ModeloUsuarios modelo, VistaInicio vista) {
		this.modeloUsuarios = modelo;
		this.vista = vista;
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
	

	public void crearModeloInventario() {
			this.modeloInventario = new ModeloInventario(new DAOInventario());
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

	public String[][] getDatosTablaUsuarios(){
		return modeloUsuarios.getUsuarios();
	}
	
	public String[][] getDatosTablaArticulos(){
		return modeloInventario.getArticulos();
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
	}
	
	
	public void actualizarUsuarioAceptado(Usuario usuarioAceptado) {
		this.usuarioAceptado = usuarioAceptado;
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
	
	
}
