/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Clases.*;
import bbdd.BaseDatos;
import BDPizzas.*;

@WebServlet(name = "SOpcion", urlPatterns = {"/SOpcion"})
public class SOpcion extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        RequestDispatcher rd = null;
        try {

            Usuario u = (Usuario) request.getSession().getAttribute("usuario");

            String boton=(String)request.getParameter("m");
        if(boton.equals("Modificar")){
            String d=(String)request.getParameter("direccion");
            String c=(String)request.getParameter("cpostal");


            BaseDatos mibase = new BaseDatos(
                    "jdbc:mysql://localhost/pizza", // URL de conexión
                    "root", // Usuario de la base de datos
                    "lfpb10" // Contraseña de la base de datos
            );
            String s=mibase.abrir();
            int res=BDUsuarios.actualizar(d, c, u.getTelefono(), mibase);
            mibase.cerrar();
            System.out.println("Actualizacion exitosa");
            // Mensaje de éxito
            request.setAttribute("mensaje", "Actualizacion exitosa");
            request.setAttribute("tipoMensaje", "exito");
            request.setAttribute("usuario", u);

            rd = request.getRequestDispatcher("/index.jsp");
            rd.forward(request, response);
            }
        else {
            rd = request.getRequestDispatcher("/pedido.jsp");
            rd.forward(request, response);
        }
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
