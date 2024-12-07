package Servlet;

import BDPizzas.BDPedidos;
import java.io.IOException;
import java.util.Arrays;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Clases.Pedido;
import bbdd.BaseDatos;

@WebServlet(name = "SPedido", urlPatterns = {"/SPedido"})
public class SPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String totalImporte = ""; // Variable para el total del pedido
        RequestDispatcher rd = null; // Para redirección de páginas
        boolean exito = false; // Indicador de éxito en el guardado del pedido

        try {
            // Validación de parámetro "pizza"
            String tipo = request.getParameter("pizza");
            if (tipo == null || tipo.isEmpty()) {
                throw new IllegalArgumentException("El tipo de pizza no puede estar vacío.");
            }

            String[] ingredientesSeleccionados = request.getParameterValues("ingredientes");

            // Validación de parámetro "c" (cantidad)
            String cantidadParam = request.getParameter("c");
            if (cantidadParam == null || cantidadParam.isEmpty()) {
                throw new IllegalArgumentException("Debe especificar una cantidad.");
            }

            // Conversión de cantidad a entero
            int cantidad = Integer.parseInt(cantidadParam);
            if (cantidad <= 0) {
                throw new IllegalArgumentException("La cantidad debe ser un número positivo.");
            }
            Integer idUsuario = (Integer) request.getSession().getAttribute("idUsuario");

            // Crear un objeto Pedido y calcular el importe total
            Pedido pedido = new Pedido (idUsuario,tipo,cantidad,totalImporte, Arrays.asList(ingredientesSeleccionados));
            totalImporte = pedido.getTotalImporte(tipo, Arrays.asList(ingredientesSeleccionados).toString());
            pedido.setTotal(totalImporte);

            // Guardar el pedido en la base de datos
            BaseDatos mibase = new BaseDatos(
                    "jdbc:mysql://localhost/pizza", // URL de conexión
                    "root", // Usuario de la base de datos
                    "lfpb10" // Contraseña de la base de datos
            );

            String conexionAbierta = mibase.abrir();
            if (conexionAbierta != null) {
                exito = BDPedidos.insertar(pedido, mibase);
                mibase.cerrar();
            }

            // Redirigir a la página de factura si se guarda correctamente
            if (exito) {
                request.setAttribute("t", totalImporte);
                rd = request.getRequestDispatcher("/factura.jsp");
            } else {
                throw new Exception("No se pudo guardar el pedido en la base de datos.");
            }

        } catch (NumberFormatException e) {
            // Error al convertir la cantidad a número
            request.setAttribute("error", "La cantidad debe ser un número válido.");
            rd = request.getRequestDispatcher("/error.jsp");

        } catch (IllegalArgumentException e) {
            // Error de validación de parámetros
            request.setAttribute("error", e.getMessage());
            rd = request.getRequestDispatcher("/error.jsp");

        } catch (Exception e) {
            // Manejo de cualquier otro error inesperado
            request.setAttribute("error", "Ha ocurrido un error: " + e.getMessage());
            rd = request.getRequestDispatcher("/error.jsp");

        } finally {
            // Forward a la página correspondiente
            if (rd != null) {
                rd.forward(request, response);
            }
        }
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
        return "Servlet para procesar pedidos de pizzas.";
    }
}
