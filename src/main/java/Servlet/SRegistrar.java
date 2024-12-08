package Servlet;

import BDPizzas.BDUsuarios;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Clases.Usuario;
import javax.servlet.RequestDispatcher;
import bbdd.BaseDatos;

import static BDPizzas.BDUsuarios.buscar;

@WebServlet(name = "SRegistrar", urlPatterns = {"/SRegistrar"})
public class SRegistrar extends HttpServlet {

    /**
     * Handles the HTTP GET method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Redirige al formulario de registro
        RequestDispatcher rd = request.getRequestDispatcher("/registro.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP POST method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean exito = false;
        Usuario u = null;
        RequestDispatcher rd;

        try {
            String telefono = request.getParameter("telefono");
            String nombre = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String cpostal = request.getParameter("cpostal");

            u = new Usuario(nombre, direccion, cpostal, telefono);

            BaseDatos mibase = new BaseDatos(
                    "jdbc:mysql://localhost/pizza", // URL de conexión
                    "root", // Usuario de la base de datos
                    "lfpb10" // Contraseña de la base de datos
            );

            mibase.abrir();
            Usuario user= buscar(u.getTelefono(), mibase);
            mibase.cerrar();
            if (user == null) {
                mibase.abrir();
                exito = BDUsuarios.insertar(u, mibase);
                mibase.cerrar();
            }
        } finally {
            if (exito) {
                // Redirige a la página de pedidos si el registro fue exitoso
                request.setAttribute("usuario", u);

                System.out.println("Registro exitoso");
                rd = request.getRequestDispatcher("/pedido.jsp");
                rd.forward(request, response);
            } else {
                // Redirige de vuelta al formulario de registro si falló
                System.out.println("numero de telefono ya se encuentra registrado");
                rd = request.getRequestDispatcher("/registro.jsp");
                rd.forward(request, response);
            }
        }
    }
}
