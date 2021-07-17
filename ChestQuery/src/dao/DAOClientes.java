package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import model.Cliente;
import model.ModeloClientes;



public class DAOClientes {
	

	private ModeloClientes modelo;
	private String url;
	private String usuario;
	private String clave;
	
//--------------------------------------------------
	private final String UBICACIONDEINFORMACION = "src/recursos/propiedadesConexionClientes.txt";
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
	public void setModelo(ModeloClientes modelo) {
		this.modelo = modelo;
	}

	public void actualizarInformacion() {
	
		try {
			Connection con = DriverManager.getConnection(url, usuario, clave);

			// Utilizar la conexión para crear el objeto sentencia
			Statement stmt = con.createStatement();

			// Ejecución de la consulta usando el objeto de tipo
			// Statement para obtener el ResultSet
			String consulta = "SELECT * FROM Clientes ORDER BY Nombre";		//obtener datos de tabla
			ResultSet resultado = stmt.executeQuery(consulta);

			// Imprimir fila por fila los resultados
			while (resultado.next()) {
				String nombre=resultado.getString("Nombre").trim();
				String direccion=resultado.getString("Direccion").trim();
				long telefono= Long.parseLong(resultado.getString("Telefono").trim());
				String mail=resultado.getString("Mail").trim();
				
				modelo.agregarCliente(new Cliente(nombre,direccion,telefono,mail));	
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


	public void borrarCliente(String nombre,String direccion,long telefono,String mail) {
		try {
			Connection con = DriverManager.getConnection(url, usuario, clave);

			// Utilizar la conexión para crear el objeto sentencia
			Statement stmt = con.createStatement();

			// Ejecución de la consulta usando el objeto de tipo
			String consulta = "DELETE FROM Clientes WHERE (Nombre='"+nombre+"') AND "
					+ "(Direccion='"+direccion+"') AND "
					+ "(Telefono='"+telefono+"') AND" 
					+ "(Mail='"+mail+"');"; 
			stmt.executeUpdate(consulta);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
	}


	public void agregarCliente(String nombre,String direccion,long telefono,String mail) {
		try {
			Connection con = DriverManager.getConnection(url, usuario, clave);

			// Utilizar la conexión para crear el objeto sentencia
			Statement stmt = con.createStatement();

			// Ejecución de la consulta usando el objeto de tipo
			String consulta = "INSERT INTO Clientes VALUES ('"+nombre+"', "
					+ "'"+direccion+"', "
					+ "'"+telefono+"'," 
					+ "'"+mail+"');"; 
			stmt.executeUpdate(consulta);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
	public void actualizarCliente(Cliente anterioresDatosCliente,Cliente nuevosDatosCliente) {
		try {
			Connection con = DriverManager.getConnection(url, usuario, clave);

			// Utilizar la conexión para crear el objeto sentencia
			Statement stmt = con.createStatement();

			//String nombre,String direccion,long telefono,String mail
			
			String consulta = "DELETE FROM Clientes WHERE (Nombre='"+anterioresDatosCliente.getNombre()+"') AND "
					+ "(Direccion='"+anterioresDatosCliente.getDireccion()+"') AND "
					+ "(Telefono='"+anterioresDatosCliente.getTelefono()+"') AND" 
					+ "(Mail='"+anterioresDatosCliente.getMail()+"');";
			stmt.executeUpdate(consulta);

			
			// Ejecución de la consulta usando el objeto de tipo
			consulta = "INSERT INTO Clientes VALUES ('"+nuevosDatosCliente.getNombre()+"', "
					+ "'"+nuevosDatosCliente.getDireccion()+"', "
					+ "'"+nuevosDatosCliente.getTelefono()+"'," 
					+ "'"+nuevosDatosCliente.getMail()+"');";
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