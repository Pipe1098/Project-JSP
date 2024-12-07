package BDPizzas;

import Clases.Pedido;
import Clases.Usuario;
import bbdd.BaseDatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public class BDPedidos {

	// Método para buscar un pedido por ID
	public static Pedido buscar(int idPedido, BaseDatos BBDD) {
		Pedido pedido = null;
		String query = "SELECT * FROM pedido WHERE id_pedido = ?";
		try (Connection c = BBDD.getConexion();
			 PreparedStatement ps = c.prepareStatement(query)) {
			if (c == null) {
				throw new SQLException("Conexión a la base de datos es nula.");
			}

			ps.setInt(1, idPedido);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					pedido = new Pedido(
							rs.getInt("id_usuario"),
							rs.getString("tamanoPizza"),
							rs.getInt("cantidad"),
							rs.getString("totalPagar"),
                            Collections.singletonList(rs.getString("ingredientes"))

					);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error al buscar el pedido en la base de datos", e);
		}
		return pedido;
	}

	// Método para insertar un nuevo pedido
	public static boolean insertar(Pedido pedido, BaseDatos BBDD) {
		String query = "INSERT INTO pedidos (id_usuario, tamanoPizza, cantidad, totalPagar, ingredientes) VALUES (?, ?, ?, ?, ?)";
		try (Connection c = BBDD.getConexion();
			 PreparedStatement ps = c.prepareStatement(query)) {
			ps.setInt(1, pedido.getIdUsuario());
			ps.setString(2, pedido.getTamanoPizza());
			ps.setInt(3, pedido.getCantidad());
			ps.setString(4, pedido.getTotal());
			ps.setString(5, pedido.getIngredientes());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Método para actualizar un pedido por ID
	public static int actualizar(int idPedido, String tamanoPizza, int cantidad, BaseDatos BBDD) {
		String query = "UPDATE pedido SET tamanoPizza = ?, cantidad = ? WHERE id_pedido = ?";
		try (Connection c = BBDD.getConexion();
			 PreparedStatement ps = c.prepareStatement(query)) {
			ps.setString(1, tamanoPizza);
			ps.setInt(2, cantidad);
			ps.setInt(3, idPedido);
			return ps.executeUpdate(); // Devuelve el número de filas afectadas
		} catch (SQLException e) {
			e.printStackTrace();
			return -2;
		}
	}

	// Método para eliminar un pedido por ID
	public static boolean eliminar(int idPedido, BaseDatos BBDD) {
		String query = "DELETE FROM pedido WHERE id_pedido = ?";
		try (Connection c = BBDD.getConexion();
			 PreparedStatement ps = c.prepareStatement(query)) {
			ps.setInt(1, idPedido);
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0; // Devuelve true si se eliminó al menos una fila
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}


}
