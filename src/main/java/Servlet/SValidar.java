package Servlet;

import BDPizzas.BDUsuarios;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import bbdd.BaseDatos;
import Clases.Usuario;

@WebServlet(name = "SValidar", urlPatterns = {"/SValidar"})
public class SValidar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd;
        BaseDatos mibase = null;

        try {
            // Inicializamos la conexión a la base de datos
            mibase = new BaseDatos(
                    "jdbc:mysql://localhost/pizza", // URL de conexión
                    "root", // Usuario de la base de datos
                    "lfpb10" // Contraseña de la base de datos
            );
            String errorConexion = mibase.abrir();

            if (!errorConexion.isEmpty()) {
                // Si no se puede abrir la base de datos, lanzamos un error
                throw new ServletException("No se pudo abrir la base de datos: " + errorConexion);
            }

            // Obtenemos el parámetro de teléfono enviado desde el formulario
            String telefono = request.getParameter("telefono");
            Usuario usuario = BDUsuarios.buscar(telefono, mibase);

            if (usuario == null) {
                request.setAttribute("error", "Usuario no encontrado para el teléfono: " + telefono);
                rd = request.getRequestDispatcher("/error.jsp");
                rd.forward(request, response);
                return;
            }

            // Guardamos el usuario y teléfono en el request
            request.setAttribute("usuario", usuario);
            request.setAttribute("telefono", telefono);

            // Guardar el id_usuario en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("idUsuario", usuario.getId()); // Suponiendo que el método getId() existe en la clase Usuario

        } catch (Exception e) {
            // Manejo de errores: Redirige a una página de error
            request.setAttribute("error", e.getMessage());
            rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
            return;
        } finally {
            // Cerramos la conexión a la base de datos
            if (mibase != null) {
                mibase.cerrar();
            }
        }

        // Redirigimos a la página JSP para mostrar los resultados
        rd = request.getRequestDispatcher("/mostrar.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para validar usuarios";
    }
}
