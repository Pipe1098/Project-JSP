package bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class prueba {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/pizza";
        String user = "root";
        String password = "lfpb10";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa.");
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }




}
