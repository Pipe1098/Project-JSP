<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="bbdd.BaseDatos" %>
<%@page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pedidos</title>
    </head>
    <body>
        <h1>Bienvenidos a Pedidos</h1>
        <div>
            <form action="/pizzeria/SPedido" method="POST">
                <table border="1">
                    <tr>
                        <td>Tipo de pizza</td>
                        <td colspan="2">
                            <select name="pizza">
                                <option value="g">Grande - 10$</option>
                                <option value="m">Mediana - 7$</option>
                                <option value="p">Pequeña - 4$</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Ingredientes</td>
                        <td colspan="2">
                            <!-- Usamos checkboxes para permitir seleccionar múltiples ingredientes -->
                            <input type="checkbox" name="ingredientes" value="TOCINETA" /> Tocineta - 5$ <br />
                            <input type="checkbox" name="ingredientes" value="QUESO" /> Queso - 4$ <br />
                            <input type="checkbox" name="ingredientes" value="PEPERONI" /> Peperoni - 3$ <br />
                            <input type="checkbox" name="ingredientes" value="otro" /> Otro - 1$ <br />
                        </td>
                    </tr>
                    <tr>
                        <td>Cantidad</td>
                        <td><input type="text" size="2" name="c" /></td>
                    </tr>
                </table>
                <input type="submit" name="pedido" value="Pedir" />
            </form>
        </div>
    </body>
</html>
