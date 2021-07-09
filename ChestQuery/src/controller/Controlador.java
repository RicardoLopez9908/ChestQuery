package controller;

import model.Modelo;
import model.Usuario;
import view.Vista;
import view.VistaPersonalizarFondo;
import view.VistaPremium;

public class Controlador {

	private Modelo modelo;
	private VistaPersonalizarFondo vistaPersonalizarFondo;
	private Vista vista;
	private VistaPremium vistaPremium;
	private Usuario usuarioAceptado;
	
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
			vistaPremium = new VistaPremium(this);
			vistaPremium.setUsuario(usuarioAceptado);
			vistaPremium.ejecutar();
		}else {
			System.out.println("NO COINCIDEN EL NOMBRE Y CONTRASEÑA INGRESADOS");
			vista.contrasenaErronea();
			}
	
	}
	
	public void setDiseño(int diseño) {
		
	}

	public void agregarVistaPremium(VistaPremium vistaPremium) {
		this.vistaPremium = vistaPremium;
	}

	public void personalizarFondo() {
		vistaPremium.deshabilitar();
		vistaPersonalizarFondo = new VistaPersonalizarFondo(this);
		vistaPersonalizarFondo.ejecutar();
	}

	public void seleccionarFondo(int diseño) {
		modelo.modificarDiseño(usuarioAceptado,diseño);
		vistaPremium.habilitar();
		vistaPremium.actualizarFondo(diseño);
	}

}
