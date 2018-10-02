<%-- 
    Document   : menu
    Created on : 25-sep-2018, 18:01:54
    Author     : adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menu</title>
    </head>
    <body>
        <%if(request.getParameter("page").equals("registerOK")){
            out.println("<h1>Menu Principal</h1>");
}%>
        <h1>Menu Principal</h1>
        <br>
        <br>
        <br>
        <a href="registrarImagen.jsp"><h2>Registrar una imagen</h2></a>
        <%--<a href="modificarImagen.jsp"><h1>Modificar una imagen</h1></a>--%>
        <br>
        <br>
        <a href="buscarImagen.jsp"><h2>Buscar una imagen</h2></a>
        <br>
        <br>
        <a href="list.jsp"><h2>Listar imagenes</h2></a>
        <br>
        <br>      
    </body>
</html>