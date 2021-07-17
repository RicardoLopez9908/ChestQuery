package dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import model.Articulo;
import model.ModeloInventario;



public class DAOInventario {

	
	private String url;
	private String usuario;
	private String clave;	
	private ModeloInventario modelo;
//--------------------------------------------------
	
	private final String UBICACIONDEINFORMACION = "src/recursos/propiedadesConexionInventario.txt";
	
//--------------------------------------------------
	
	
	public DAOInventario() {
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

	public void setModelo(ModeloInventario modelo) {
		this.modelo = modelo;
	}

	public void actualizarInformacion() {
	
		try {
			Connection con = DriverManager.getConnection(url, usuario, clave);

			// Utilizar la conexión para crear el objeto sentencia
			Statement stmt = con.createStatement();

			// Ejecución de la consulta usando el objeto de tipo
			// Statement para obtener el ResultSet
			String consulta = "SELECT * FROM Articulos ORDER BY Codigo";		//obtener datos de tabla
			ResultSet resultado = stmt.executeQuery(consulta);

			// Imprimir fila por fila los resultados
			while (resultado.next()) {
				long codigo=Long.parseLong(resultado.getString("Codigo").trim());
				String nombre=	resultado.getString("Nombre").trim();
				String proveedor=resultado.getString("Proveedor").trim();
				String vencimiento=resultado.getString("Vencimiento").trim();
				String detalle=resultado.getString("Detalle").trim();
				int posicion=Integer.parseInt((resultado.getString("Posicion").trim()));
				int cantidad=Integer.parseInt((resultado.getString("Cantidad").trim()));
				
				modelo.agregarArticulo(new Articulo(nombre,codigo,cantidad,posicion,proveedor,vencimiento,detalle));
				
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


	public void borrarArticulo(long codigo, String nombre, int posicion, int cantidad, String proveedor,
			String vencimiento, String detalle) {
		try {
			Connection con = DriverManager.getConnection(url, usuario, clave);

			// Utilizar la conexión para crear el objeto sentencia
			Statement stmt = con.createStatement();

			// Ejecución de la consulta usando el objeto de tipo
			String consulta = "DELETE FROM Articulos WHERE (Codigo='"+codigo+"') AND "
					+ "(Nombre='"+nombre+"') AND "
					+ "(Posicion='"+posicion+"') AND" 
					+ "(Cantidad='"+cantidad+"') AND" 
					+ "(Proveedor='"+proveedor+"') AND" 
					+ "(Vencimiento='"+vencimiento+"') AND" 
					+ "(Detalle='"+detalle+"');";
			stmt.executeUpdate(consulta);

			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
				
		
	}


	public void agregarArticulo(String codigo,String nombre,String cantidad,String detalle,String posicion,String proveedor,String vencimiento) {
		try {
			Connection con = DriverManager.getConnection(url, usuario, clave);

			// Utilizar la conexión para crear el objeto sentencia
			Statement stmt = con.createStatement();

			//INSERT INTO Articulos VALUES ('1234567891123', 'PURE DE TOMATE X 5 KG', 'EL PEPE','25/03/2022','RECIBIDA 14/12/2020','35','10');
			
			// Ejecución de la consulta usando el objeto de tipo
			String consulta = "INSERT INTO Articulos VALUES ('"+codigo+"', "
					+ "'"+nombre+"', "
					+ "'"+proveedor+"'," 
					+ "'"+vencimiento+"'," 
					+ "'"+detalle+"'," 
					+ "'"+posicion+"'," 
					+ "'"+cantidad+"');";
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
