<%@page contentType="text/html" pageEncoding="UTF-8" import="Clases.Usuario, javax.servlet.http.HttpSession"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro y Bienvenida</title>
        <link rel="stylesheet" type="text/css" href="CSS/index.css">
        <style>
              body {
                  font-family: 'Arial', sans-serif;
                  margin: 0;
                  padding: 0;
                  display: flex;
                  flex-direction: column;
                  align-items: center;
                  justify-content: center;
                  height: 100vh;
              }

              .centrar {
                  padding: 20px;
                  max-width: 600px;
                  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                  border-radius: 8px;
                  text-align: center;
              }

              table {
                  width: 100%;
                  border-collapse: collapse;
                  margin: 20px 0;
              }

              th, td {
                  padding: 10px;
                  border: 1px solid #ddd;
                  text-align: left;
                  color:#fff;
                  font-weight: bold;
              }

              th {
                  background-color: #ffba08;

                  color: #000;
              }

              td input {
                  width: calc(100% - 20px);
                  padding: 8px;
                  font-size: 14px;
                  border: 1px solid #ddd;
                  border-radius: 4px;
                  box-sizing: border-box;
              }

              td input[disabled] {
                  background-color: #e9ecef;
                  color: #6c757d;
              }

              /* Botones */
              input[type="submit"] {
                  padding: 10px 3px;
                  font-size: 14px;
                  border: none;
                  border-radius: 5px;
                  color: #000;
                  background-color: #ffba08;
                  cursor: pointer;
                  transition: background-color 0.3s;
              }

              input[type="submit"]:hover {
                  background-color: #f48c06;
              }

              /* Respuesta adaptativa */
              @media (max-width: 600px) {
                  .centrar {
                      padding: 10px;
                  }

                  table {
                      font-size: 14px;
                  }
              }

              a {
                color: #f48c06;
                text-decoration: none;
                font-size: 18px;
                margin-top: 8px;
                display: inline-block;
                }
               a:hover {
                  text-decoration: underline;
                  }
        </style>
    </head>
    <body>
        <%! HttpSession sesion; %>
        <%
            Usuario u = (Usuario) request.getAttribute("usuario");
            String t = (String) request.getAttribute("telefono");
            if (u == null) {
        %>
        <!-- Incluir la página Registro.jsp -->
        <jsp:include page="registro.jsp" />
        <%
            } else {
                sesion = request.getSession(true);
                sesion.setAttribute("usuario", u);
        %>
        <h1>Bienvenido</h1>
        <div class="centrar">
            <form action="/pizzeria/SOpcion" method="POST">
                <table>
                    <thead>
                        <tr>
                            <th>Campos</th>
                            <th>Valor</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Nombre</td>
                            <td><input type="text" disabled="true" name="nombre" value="<%= u.getNombre() %>" /></td>
                        </tr>
                        <tr>
                            <td>Dirección</td>
                            <td><input type="text" name="direccion" value="<%= u.getDireccion() %>" /></td>
                        </tr>
                        <tr>
                            <td>Código Postal</td>
                            <td><input type="text" name="cpostal" value="<%= u.getCpostal() %>" /></td>
                        </tr>
                        <tr>
                            <td>Teléfono</td>
                            <td><input type="text" disabled="true" name="telefono" value="<%= u.getTelefono() %>" /></td>
                        </tr>
                        <tr>
                            <td><input type="submit" name="m" value="Modificar" /></td>
                            <td><input type="submit" name="m" value="Pedido" /></td>
                        </tr>
                    </tbody>
                </table>
            </form>
            <a href="/pizzeria/index.jsp">ir al login</a>
        </div>
        <% } %>
    </body>
</html>
