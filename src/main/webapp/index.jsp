<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizzería - Validación</title>
        <link rel="stylesheet" type="text/css" href="CSS/index.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>

        <h1>Pizzería</h1>
        <form name="telefono" action="/pizzeria/SValidar" method="POST">
            <label for="telefono">Teléfono:</label>
            <input type="text" id="telefono" name="telefono" size="10" required />
            <input type="submit" value="Entrar" name="Enviar" />
        </form>
        <br>
        <form name="registrar" action="/pizzeria/SRegistrar" method="GET">
            <input type="submit" value="Registrarse como nuevo usuario" />
        </form>



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
