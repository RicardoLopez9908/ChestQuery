package model;

import java.util.ArrayList;

import dao.DAOReportes;

public class ModeloReportes {
	private ArrayList<Reporte> reportes = new ArrayList<>();
	private DAOReportes dao;

	public ModeloReportes(DAOReportes dao) {
		this.dao = dao;
		this.dao.setModelo(this);
		this.dao.actualizarInformacion();
	}

	public void agregarReporte(Reporte reporte ) {
		reportes.add(reporte);
	}

	public String[][] getReportes() {
		String[][] lista = new String[reportes.size()][4];
		int i = 0;
		//instruccion;usuario;numero;fecha
		
		for (Reporte reporte: reportes) {
			lista[i][0] = reporte.getInstruccionRealizada();
			lista[i][1] = reporte.getUsuario();
			lista[i][2] = String.valueOf(reporte.getNumeroDeUsuario());
			lista[i][3] = reporte.getFecha();

			i++;
		}
		return lista;
	}

	
	public void agregarReporteDAO(String instruccionRealizada,String usuario,int numeroDeUsuario, String fecha) {
		dao.agregarReporte(instruccionRealizada,usuario,numeroDeUsuario,fecha);
		reportes.clear();
		dao.actualizarInformacion();
	}
	
	
}
