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

            mibase = new BaseDatos(
                    "jdbc:mysql://localhost/pizza",
                    "root",
                    "lfpb10"
            );
            String errorConexion = mibase.abrir();

            if (!errorConexion.isEmpty()) {

                throw new ServletException("No se pudo abrir la base de datos: " + errorConexion);
            }


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
            session.setAttribute("idUsuario", usuario.getId());

        } catch (Exception e) {

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
