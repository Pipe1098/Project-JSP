package BDPizzas;

import bbdd.BaseDatos;
import java.sql.*;
import Clases.Usuario;

public class BDUsuarios {

	public static Usuario buscar(String telefono, BaseDatos BBDD) {
		Usuario usuario = null;
		String query = "SELECT * FROM usuarios WHERE telefono = ?";
		try (Connection c = BBDD.getConexion();
			 PreparedStatement ps = c.prepareStatement(query)) {
			if (c == null) {
				throw new SQLException("Conexión a la base de datos es nula.");
			}

			ps.setString(1, telefono);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					usuario = new Usuario(
							rs.getInt("id_usuario"),
							rs.getString("nombre"),
							rs.getString("direccion"),
							rs.getString("cpostal"),
							rs.getString("telefono")
					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al buscar el usuario en la base de datos", e);
		}
		return usuario;
	}


	public static boolean insertar(Usuario usuario, BaseDatos BBDD) {
		String query = "INSERT INTO usuarios (nombre, direccion, cpostal, telefono) VALUES (?, ?, ?, ?)";
		try (Connection c = BBDD.getConexion();
			 PreparedStatement ps = c.prepareStatement(query)) {
			ps.setString(1, usuario.getNombre());
			ps.setString(2, usuario.getDireccion());
			ps.setString(3, usuario.getCpostal());
			ps.setString(4, usuario.getTelefono());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static int actualizar(String direccion, String telefono, BaseDatos BBDD) {
		String query = "UPDATE usuarios SET direccion = ? WHERE telefono = ?";
		try (Connection c = BBDD.getConexion();
			 PreparedStatement ps = c.prepareStatement(query)) {
			ps.setString(1, direccion);
			ps.setString(2, telefono);
			return ps.executeUpdate(); // Devuelve el número de filas afectadas
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		}
	}



}
