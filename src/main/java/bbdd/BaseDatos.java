package bbdd;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDatos {
    private String url;
    private String user;
    private String password;
    private Connection conexion;

    // Constructor para inicializar los parámetros de conexión
    public BaseDatos(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    // Método para abrir la conexión
    public String abrir() {
        try {
            // Cargar el driver JDBC para MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
            conexion = DriverManager.getConnection(url, user, password);
            return ""; // Retorna vacío si no hay error
        } catch (ClassNotFoundException e) {
            return "Driver JDBC no encontrado: " + e.getMessage();
        } catch (SQLException e) {
            return "Error al conectar con la base de datos: " + e.getMessage();
        }
    }

    // Método para obtener la conexión
    public Connection getConexion() {
        return conexion;
    }

    // Método para cerrar la conexión
    public void cerrar() {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}
