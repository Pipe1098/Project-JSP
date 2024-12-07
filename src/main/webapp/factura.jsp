
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% String factura=(String)request.getAttribute("t"); %>
        <jsp:include page="/pedido.jsp" />
        <h1>Total importe a pagar <input type="text" name="total" value="<%=factura %>" /></h1>
        <a href="index.jsp">Volver a logear</a>
        </form>
    </body>
</html>
