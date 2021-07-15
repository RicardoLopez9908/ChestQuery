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
				String nombre = usuarios.getProperty("Usuario" + i);
				String contraseña = usuarios.getProperty("Usuario" + i + "Contrasena");
				String categoria = usuarios.getProperty("Usuario" + i + "NivelDeAcceso");
				int diseño = Integer.parseInt(usuarios.getProperty("Usuario" + i + "Diseno"));
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
				modelo.agregarUsuarios(new Usuario(nombre, contraseña, nivelDeAcceso, diseño, i));
			}

		} catch (Exception e) {
			System.out.println("ERROR AL LEER EL ARCHIVO: " + ubicacionInformacion);
			e.printStackTrace();
		}
	}

	public void modificarDiseño(int numeroDeUsuario, int diseno) {
		try {
			usuarios.setProperty("Usuario" + numeroDeUsuario + "Diseno", String.valueOf(diseno));
			FileOutputStream archivo = new FileOutputStream(ubicacionInformacion);
			usuarios.store(archivo, "modificamos diseno de " + usuarios.getProperty("Usuario" + numeroDeUsuario)
					+ "(Usuario" + numeroDeUsuario + ")");
			archivo.close();

		} catch (Exception e) {
			System.out.println("ERROR AL ESCRIBIR EN ARCHIVO: " + ubicacionInformacion);
			e.printStackTrace();
		}
	}

	public void borrarUsuario(int numeroDeUsuario) {
		try {
			int cantidadUsuarios = (Integer.parseInt(usuarios.getProperty("CantidadUsuarios")) )-1;
			
			usuarios.remove("Usuario"+numeroDeUsuario);
			usuarios.remove("Usuario"+numeroDeUsuario+"Contrasena");
			usuarios.remove("Usuario"+numeroDeUsuario+"Diseno");
			usuarios.remove("Usuario"+numeroDeUsuario+"NivelDeAcceso");
			
			usuarios.setProperty("CantidadUsuarios",String.valueOf(cantidadUsuarios) );
			
			for(int i=1 ; i<=(cantidadUsuarios+ 1-numeroDeUsuario ); i++) {
				String nombre = usuarios.getProperty("Usuario"+(numeroDeUsuario+i));
				String contrasena= usuarios.getProperty("Usuario"+(numeroDeUsuario+i) +"Contrasena");
				String diseno= usuarios.getProperty("Usuario"+(numeroDeUsuario+i)+"Diseno");
				String nivelDeAcceso= usuarios.getProperty("Usuario"+(numeroDeUsuario+i)+"NivelDeAcceso");
				usuarios.remove("Usuario"+(numeroDeUsuario+i));
				usuarios.remove("Usuario"+(numeroDeUsuario+i)+"Contrasena");
				usuarios.remove("Usuario"+(numeroDeUsuario+i)+"Diseno");
				usuarios.remove("Usuario"+(numeroDeUsuario+i)+"NivelDeAcceso");
				usuarios.setProperty("Usuario" + (numeroDeUsuario+i-1), nombre);
				usuarios.setProperty("Usuario" + (numeroDeUsuario+i-1)+"Contrasena", contrasena);
				usuarios.setProperty("Usuario" + (numeroDeUsuario+i-1)+"Diseno", diseno);
				usuarios.setProperty("Usuario" + (numeroDeUsuario+i-1)+"NivelDeAcceso", nivelDeAcceso);
			}
			
			FileOutputStream archivo = new FileOutputStream(ubicacionInformacion);
			usuarios.store(archivo, "Eliminamos el usuario numero :" + numeroDeUsuario);
			archivo.close();

		} catch (Exception e) {
			System.out.println("ERROR AL ESCRIBIR EN ARCHIVO: " + ubicacionInformacion);
			e.printStackTrace();
		}
	}

}
