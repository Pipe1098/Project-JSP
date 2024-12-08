package Servlet;

import BDPizzas.BDPedidos;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Clases.Pedido;
import bbdd.BaseDatos;

@WebServlet(name = "SPedido", urlPatterns = {"/SPedido"})
public class SPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String totalImporte = "";
        RequestDispatcher rd = null;
        boolean exito = false;

        try {
            // Validación de parámetros
            String tipo = request.getParameter("pizza");
            if (tipo == null || tipo.isEmpty()) {
                request.setAttribute("mensaje", "El tipo de pizza no puede estar vacío");
                request.setAttribute("tipoMensaje", "error");
                rd = request.getRequestDispatcher("/pedido.jsp");
                rd.forward(request, response);
                return;
            }

            String[] ingredientesSeleccionados = request.getParameterValues("ingredientes");

            if (ingredientesSeleccionados == null) {
                ingredientesSeleccionados = new String[0];
            }



            String cantidadParam = request.getParameter("c");

            if (cantidadParam == null || cantidadParam.isEmpty()) {
                request.setAttribute("mensaje", "Debe especificar una cantidad");
                request.setAttribute("tipoMensaje", "error");
                rd = request.getRequestDispatcher("/pedido.jsp");
                rd.forward(request, response);
                return;
            }

            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadParam);
                if (cantidad <= 0) {
                    throw new NumberFormatException("Cantidad menor o igual a 0");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("mensaje", "La cantidad debe ser un número mayor a 0");
                request.setAttribute("tipoMensaje", "error");
                rd = request.getRequestDispatcher("/pedido.jsp");
                rd.forward(request, response);
                return;
            }

            Integer idUsuario = (Integer) request.getSession().getAttribute("idUsuario");
            if (idUsuario == null) {
                request.setAttribute("mensaje", "Debe iniciar sesión para realizar un pedido");
                request.setAttribute("tipoMensaje", "error");
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
                return;
            }

            Pedido pedido = new Pedido(idUsuario, tipo, cantidad, "", Arrays.asList(ingredientesSeleccionados));
            totalImporte = pedido.getTotalImporte(tipo, Arrays.toString(ingredientesSeleccionados));
            pedido.setTotal(totalImporte);


            BaseDatos mibase = null;
            try {
                mibase = new BaseDatos("jdbc:mysql://localhost/pizza", "root", "lfpb10");
                mibase.abrir();
                exito = BDPedidos.insertar(pedido, mibase);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (mibase != null) {
                    try {
                        mibase.cerrar();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }


            if (exito) {
                request.setAttribute("t", totalImporte);
                request.setAttribute("mensaje", "Pedido creado con éxito");
                request.setAttribute("tipoMensaje", "exito");
                rd = request.getRequestDispatcher("/factura.jsp");
                rd.forward(request, response);

            } else {
                request.setAttribute("mensaje", "No se pudo guardar el pedido en la base de datos.");
                request.setAttribute("tipoMensaje", "error");
                rd = request.getRequestDispatcher("/pedido.jsp");
                rd.forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Ha ocurrido un error inesperado: " + e.getMessage());
            request.setAttribute("tipoMensaje", "error");
            rd = request.getRequestDispatcher("/error.jsp");

        }

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        // Obtener la opción de ver pedidos
        String verPedidos = request.getParameter("verPedidos");
        if ("true".equals(verPedidos)) {
            // Si el usuario quiere ver los pedidos
            BaseDatos mibase = null;
            try {
                mibase = new BaseDatos("jdbc:mysql://localhost/pizza", "root", "lfpb10");
                mibase.abrir();
                List<Pedido> pedidos = BDPedidos.obtenerPedidosUsuario(idUsuario, mibase);
                request.setAttribute("pedidos", pedidos);
                request.getRequestDispatcher("pedido.jsp").forward(request, response); // Redirigir a la página de pedidos
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("<p>Error al obtener los pedidos: " + e.getMessage() + "</p>");
            } finally {
                if (mibase != null) {
                    try {
                        mibase.cerrar();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para procesar pedidos de pizzas.";
    }
}
