package controller;

import javax.swing.table.TableModel;

import model.Categoria;
import model.Modelo;
import model.Usuario;
import view.Vista;
import view.VistaBloqueo;
import view.VistaContacto;
import view.VistaPersonalizarFondo;
import view.VistaPremium;

public class Controlador {

	private Modelo modelo;
	private VistaPersonalizarFondo vistaPersonalizarFondo;
	private Vista vista;
	private VistaPremium vistaPremium;
	private Usuario usuarioAceptado;
	private VistaBloqueo vistaBloqueo;
	private VistaContacto vistaContacto;
	
	public Controlador(Modelo modelo, Vista vista) {
		this.modelo = modelo;
		this.vista = vista;
	}

	// ------------------------------------------------------------

	public void iniciarSesion(String nombre,String contraseña) {
		usuarioAceptado = modelo.iniciarSesion(nombre, contraseña);
		
		if(usuarioAceptado!=null){
			System.out.println("GENIAL!");
			vista.ocultarPestaña();
			vistaPremium = new VistaPremium(this,usuarioAceptado);
			vistaPremium.ejecutar();
		}else {
			System.out.println("NO COINCIDEN EL NOMBRE Y CONTRASEÑA INGRESADOS");
			vista.contrasenaErronea();
			}
	
	}
	

	public void agregarVistaPremium(VistaPremium vistaPremium) {
		this.vistaPremium = vistaPremium;
	}

	public void personalizarFondo() {
		vistaPremium.deshabilitar();
		vistaPersonalizarFondo = new VistaPersonalizarFondo(this);
		vistaPersonalizarFondo.ejecutar();
	}

	public void seleccionarFondo(int diseno) {
		modelo.modificarDiseno(usuarioAceptado,diseno);
		vistaPremium.habilitar();
		vistaPremium.actualizarFondo(diseno);
	}

	public void contacto() {
		vistaPremium.deshabilitar();
		vistaContacto = new VistaContacto(vistaPremium);
		vistaContacto.ejecutar();		
	}
	
	public void bloquearPantalla() {
		vistaPremium.deshabilitar();
		vistaBloqueo = new VistaBloqueo(this);
		vistaBloqueo.ejecutar();
	}
	
	public void desbloquearPantalla(String contraseña) {
		Usuario usuario = modelo.iniciarSesion(usuarioAceptado.getNombre(), contraseña); 
		if(usuario!=null) {
			vistaBloqueo.ocultarPestana();
			vistaPremium.habilitar();
		}else {
			vistaBloqueo.contrasenaErronea();
		}
	}

	public String[][] getDatosTablaUsuarios(){
		return modelo.getUsuarios();
	}

	public boolean borrarUsuario(int numeroDeUsuario) {
		if(numeroDeUsuario != usuarioAceptado.getNumeroDeUsuario()) {
			modelo.borrarUsuario(numeroDeUsuario,this,usuarioAceptado.getNumeroDeUsuario());			
			return true;
		}
			return false;
	}
	
	public void actualizarUsuarioAceptado(Usuario usuarioAceptado) {
		this.usuarioAceptado = usuarioAceptado;
	}
	
	public boolean agregarUsuario(String nombre,String contrasena, String nivelDeAcceso) {
		if(modelo.agregarUsuarioDAO(nombre, contrasena, nivelDeAcceso)) {	
			return true;
		}else {
			return false;
		}
		
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
		if(modelo.actualizarUsuarioCompleto(numeroDeUsuario, nombre, contrasena, nivel, diseno)) {
			return true;
		}else {
			return false;
		}
	}
	
	
}
