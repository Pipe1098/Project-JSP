
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="CSS/index.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>
        <% String factura=(String)request.getAttribute("t"); %>
        <jsp:include page="/pedido.jsp" />
        <h1>Total importe a pagar <input type="text" name="total" value="<%=factura %>" /></h1>
        <a href="index.jsp">Volver al inicio</a>
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
