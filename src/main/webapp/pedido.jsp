<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.List" %>
<%@page import="Clases.Pedido" %>
<%
    if (session == null || session.getAttribute("usuario") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pedidos</title>
        <link rel="stylesheet" type="text/css" href="CSS/index.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f9;
                color: #333;
            }

            form {
                max-width: 600px;
                margin: 20px auto;
                padding: 20px;
                background: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin: 20px auto;
                background: #fff;
                border-radius: 8px;
                overflow: hidden;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            }

            table th, table td {
                padding: 10px;
                text-align: center;
                border: 1px solid #ddd;
            }

            table th {
                background-color: #ffba08;
                color: white;
            }

            table tr:nth-child(even) {
                background-color: #f9f9f9;
            }

            input[type="submit"], select {
                background-color: #ffba08;
                color: white;
                border: none;
                border-radius: 4px;
                padding: 10px 20px;
                cursor: pointer;
                font-size: 16px;
                transition: background-color 0.3s;
            }

            input[type="submit"]:hover, select:hover {
                background-color: #f48c06;
            }

            input[type="text"], select {
                padding: 8px;
                width: 100%;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            div {
                margin-bottom: 20px;
            }

            .alert {
                max-width: 600px;
                margin: 10px auto;
                text-align: center;
            }

            p {
                text-align: center;
            }
            .checkbox-container {
                display: flex;
                flex-direction: column;
                align-items: flex-start; /* Alinea los elementos a la izquierda */
                gap: 5px; /* Espaciado entre los checkboxes */
            }

            .checkbox-container input[type="checkbox"] {
                margin-right: 10px; /* Espaciado entre el checkbox y la etiqueta */
            }

            .checkbox-container label {
                display: flex;
                align-items: center;
                font-size: 14px;
            }
            a {
                color: #ffba08;
                text-decoration: none;
                font-size: 14px;
                margin-top: 10px;
                display: inline-block;
            }
            a:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <h1>Bienvenidos a Pedidos</h1>

        <!-- Formulario para hacer un pedido -->
        <div>
            <form action="/pizzeria/SPedido" method="POST">
                <h2>Realizar Pedido</h2>
                <table>
                    <tr>
                        <td>Tipo de pizza</td>
                        <td>
                            <select name="pizza">
                                <option value="g">Grande - 10$</option>
                                <option value="m">Mediana - 7$</option>
                                <option value="p">Pequeña - 4$</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Ingredientes</td>
                        <td class="checkbox-container">
                            <input type="checkbox" name="ingredientes" value="Tocineta" /> Tocineta - 5$ <br />
                            <input type="checkbox" name="ingredientes" value="Queso" /> Queso - 4$ <br />
                            <input type="checkbox" name="ingredientes" value="Peperoni" /> Peperoni - 3$ <br />
                            <input type="checkbox" name="ingredientes" value="Otro" /> Otro - 1$ <br />
                        </td>
                    </tr>
                    <tr>
                        <td>Cantidad</td>
                        <td><input type="text" size="2" name="c" /></td>
                    </tr>
                </table>
                <input type="submit" value="Pedir" />

                <div style="display: flex; gap: 10px; margin-top: 10px;">
                    <a href="/pizzeria/mostrar.jsp" class="btn">Regresar</a>
                </div>


            </form>

        </div>

        <!-- Formulario para ver pedidos -->
        <div>
            <form action="/pizzeria/SPedido" method="GET">
                <input type="hidden" name="verPedidos" value="true" />
                <input type="submit" value="Ver mis pedidos" />
            </form>
        </div>

        <!-- Tabla de pedidos -->
        <div>

            <%
                List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");
                if (pedidos != null && !pedidos.isEmpty()) {
            %>
                <table>
                    <tr>
                        <th>Tipo</th>
                        <th>Ingredientes</th>
                        <th>Cantidad</th>
                        <th>Total</th>
                        <th>Fecha</th>
                    </tr>
                    <%
                        for (Pedido pedido : pedidos) {
                    %>
                        <tr>
                            <td><%= pedido.getTamanoPizza() %></td>
                            <td><%= pedido.getIngredientes() %></td>
                            <td><%= pedido.getCantidad() %></td>
                            <td><%= pedido.getTotal() %></td>
                            <td><%= pedido.getFecha() %></td>
                        </tr>
                    <%
                        }
                    %>
                </table>
            <%
                } else {
            %>
                <p>No tienes pedidos registrados.</p>
            <%
                }
            %>
        </div>

        <!-- Script para mostrar mensajes -->
        <script>
            const mensaje = "<%= request.getAttribute("mensaje") != null ? request.getAttribute("mensaje") : "" %>";
            const tipo = "<%= request.getAttribute("tipoMensaje") != null ? request.getAttribute("tipoMensaje") : "" %>";

            if (mensaje && tipo) {
                Swal.fire({
                    icon: tipo === "exito" ? 'success' : 'error',
                    title: tipo === "exito" ? '¡Éxito!' : 'Error',
                    text: mensaje,
                });
            }
        </script>
    </body>
</html>
