package dao;

import java.io.FileInputStream;
import java.util.Properties;



public class DAOClientes {
//--------------------------------------------------
	private final String UBICACIONDEINFORMACION = "src/recursos/propiedadesConexionClientes.txt";
	private String url;
	private String usuario;
	private String clave;
//--------------------------------------------------
	
	
	public DAOClientes() {
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
}

