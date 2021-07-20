package model;

public class Reporte {
	private String instruccionRealizada;
	private String usuario;
	private int numeroDeUsuario;
	private String fecha;
	
	public Reporte(String instruccionRealizada,String usuario,int numeroDeUsuario, String fecha) {
		this.instruccionRealizada = instruccionRealizada;
		this.numeroDeUsuario = numeroDeUsuario;
		this.usuario = usuario;
		this.fecha=fecha;
	}

	public String getInstruccionRealizada() {
		return instruccionRealizada;
	}

	public String getUsuario() {
		return usuario;
	}

	public int getNumeroDeUsuario() {
		return numeroDeUsuario;
	}
	
	public String getFecha() {
		return this.fecha;
	}
	
	
}
