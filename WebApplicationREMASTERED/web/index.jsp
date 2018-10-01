<%-- 
    Document   : index
    Created on : 25-sep-2018, 17:46:32
    Author     : adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Benvinguts a la pagina </h1>
        <h3>Introduiu les dades per entrar</h3>
        <br>
        <%--HOLA OSTIA
        <a href="/WebApplicationREMASTERED/login">Potser te importancia...</a>--%>
        <form name="form1" action="/WebApplicationREMASTERED/login" method="post">
            <div>Username</div>
            <input type ="text" name="user" placeholder="Enter username">
            
            <div>Password</div>
            <input type ="password" name ="pass" placeholder ="Enter password">
            
            <input type ="submit" value="Login">
        </form>
    </body>
</html>