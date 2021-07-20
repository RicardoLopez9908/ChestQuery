package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import model.ModeloReportes;
import model.Reporte;

public class DAOReportes {
	

	private ModeloReportes modelo;
	private String url;
	private String usuario;
	private String clave;
	
//--------------------------------------------------
	private final String UBICACIONDEINFORMACION = "src/recursos/propiedadesConexionReportes.txt";
//--------------------------------------------------
	
	
	public DAOReportes() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(UBICACIONDEINFORMACION));
			url= properties.getProperty("UrlJDBC");
			usuario = properties.getProperty("UsuarioJDBC");
			clave = properties.getProperty("ClaveJDBC");
			Class.forName(properties.getProperty("DriverJDBC"));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public void setModelo(ModeloReportes modelo) {
		this.modelo = modelo;
	}

	public void actualizarInformacion() {
	
		try {
			Connection con = DriverManager.getConnection(url, usuario, clave);

			// Utilizar la conexión para crear el objeto sentencia
			Statement stmt = con.createStatement();

			// Ejecución de la consulta usando el objeto de tipo
			// Statement para obtener el ResultSet
			String consulta = "SELECT * FROM Reportes";		//obtener datos de tabla
			ResultSet resultado = stmt.executeQuery(consulta);

			// Imprimir fila por fila los resultados
			while (resultado.next()) {
				String instruccionRealizada=resultado.getString("Instruccion").trim();
				String usuario=resultado.getString("Usuario").trim();
				int numeroDeUsuario= Integer.parseInt(resultado.getString("NumeroDeUsuario").trim());
				String fecha=resultado.getString("Fecha").trim();
				
				modelo.agregarReporte(new Reporte(instruccionRealizada,usuario,numeroDeUsuario,fecha));	
			}
			resultado.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}


	public void agregarReporte(String instruccionRealizada,String usuario,int numeroDeUsuario,String fecha) {
		try {
			Connection con = DriverManager.getConnection(url, this.usuario, clave);

			// Utilizar la conexión para crear el objeto sentencia
			Statement stmt = con.createStatement();

			// Ejecución de la consulta usando el objeto de tipo
			String consulta = "INSERT INTO Reportes VALUES ('"+instruccionRealizada+"', "
					+ "'"+usuario+"', "
					+ "'"+numeroDeUsuario+"', "
					+ "'"+fecha+"');"; 
			stmt.executeUpdate(consulta);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}