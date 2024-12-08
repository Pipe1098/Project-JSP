<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Usuario</title>
        <link rel="stylesheet" type="text/css" href="CSS/index.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f9f9f9;
                margin: 0;
                padding: 0;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
            }

            form {
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 8px;
                padding: 20px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                width: 300px;
            }
            label {
                font-weight: bold;
                color: #555;
            }
            input[type="text"], input[type="submit"] {
                width: 100%;
                padding: 8px;
                margin-top: 5px;
                margin-bottom: 15px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            input[type="submit"] {
                background-color: #ffba08;
                color: white;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s ease;
            }
            input[type="submit"]:hover {
                background-color: #f48c06;
            }
            a {
                color: #f48c06;
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
        <h1>Registro de Nuevo Usuario</h1>
        <form action="/pizzeria/SRegistrar" method="POST">
            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required />

            <label for="direccion">Dirección:</label>
            <input type="text" id="direccion" name="direccion" required />

            <label for="cpostal">Código Postal:</label>
            <input type="text" id="cpostal" name="cpostal" required />

            <label for="telefono">Teléfono:</label>
            <input type="text" id="telefono" name="telefono" required />

            <input type="submit" value="Registrar" />
        </form>
        <a href="/pizzeria/index.jsp">ir al login</a>

        <script>
            const mensaje = "<%= request.getAttribute("mensaje") %>";
            const tipo = "<%= request.getAttribute("tipoMensaje") %>";

            if (tipo === "exito") {
                Swal.fire({
                    icon: 'success',
                    title: '¡Éxito!',
                    text: mensaje,
                });
            } else if (tipo === "error") {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: mensaje,
                });
            }
        </script>
    </body>
</html>
