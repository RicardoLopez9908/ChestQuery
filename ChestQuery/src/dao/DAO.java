package dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import model.Categoria;
import model.Modelo;
import model.Usuario;

/* 		DAO (Data Access Object) es un patr�n de dise�o cuya
*       funci�n es abstraer el acceso y manejo de datos para el resto de la
*       aplicaci�n.
*/
public class DAO {

	private Modelo modelo;

	private final String ubicacionInformacion = "src/recursos/usuarios.txt";

	private Properties usuarios;
	// --------------------------------------------------------------

	
	public DAO() {
		try {
			FileInputStream archivo = new FileInputStream(ubicacionInformacion);
			usuarios = new Properties();
			usuarios.load(archivo);
			archivo.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void actualizarInformacion() {
		try {

			int cantidadUsuarios = Integer.parseInt(usuarios.getProperty("CantidadUsuarios"));
			
			for (int i = 1; i <= cantidadUsuarios; i++) {
				String nombre = usuarios.getProperty("Usuario"+i);
				String contraseña = usuarios.getProperty("Usuario"+i+"Contrasena");
				String categoria = usuarios.getProperty("Usuario"+i+"NivelDeAcceso");
				int diseño = Integer.parseInt(usuarios.getProperty("Usuario"+i+"Diseno"));
				Categoria nivelDeAcceso = null;
				switch (categoria) {
				case "DEFAULT":
					nivelDeAcceso = Categoria.DEFAULT;
					break;
				case "MEDIUM":
					nivelDeAcceso = Categoria.MEDIUM;
					break;
				case "PREMIUM":
					nivelDeAcceso = Categoria.PREMIUM;
					break;
				default:
					nivelDeAcceso = Categoria.DEFAULT;
					break;
				}
				modelo.agregarUsuarios(new Usuario(nombre, contraseña, nivelDeAcceso, diseño,i));
			}

		} catch (Exception e) {
			System.out.println("ERROR AL LEER EL ARCHIVO: " + ubicacionInformacion);
			e.printStackTrace();
		}
	}

	public void modificarDiseño(int numeroDeUsuario, int diseno) {
		try {
			usuarios.setProperty("Usuario"+ numeroDeUsuario+ "Diseno",String.valueOf(diseno));
			FileOutputStream archivo = new FileOutputStream(ubicacionInformacion);
			usuarios.store(archivo, "modificamos diseno de "+ usuarios.getProperty("Usuario"+numeroDeUsuario)
					+ "(Usuario"+numeroDeUsuario+")");
			archivo.close();
			
		} catch (Exception e) {
			System.out.println("ERROR AL ESCRIBIR EN ARCHIVO: " + ubicacionInformacion);
			e.printStackTrace();
		}
	}

}
