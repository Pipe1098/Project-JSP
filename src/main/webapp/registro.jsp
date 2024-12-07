<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Usuario</title>
    </head>
    <body>
        <h1>Registro de Nuevo Usuario</h1>
        <form action="/pizzeria/SRegistrar" method="POST">
            <label for="nombre">Nombre:</label><br>
            <input type="text" id="nombre" name="nombre" required /><br><br>

            <label for="direccion">Dirección:</label><br>
            <input type="text" id="direccion" name="direccion" required /><br><br>

            <label for="cpostal">Código Postal:</label><br>
            <input type="text" id="cpostal" name="cpostal" required /><br><br>

            <label for="telefono">Teléfono:</label><br>
            <input type="text" id="telefono" name="telefono" required /><br><br>

            <input type="submit" value="Registrar" />
        </form>
        <br>
        <a href="/pizzeria/index.jsp">Volver a la página principal</a>
    </body>
</html>

