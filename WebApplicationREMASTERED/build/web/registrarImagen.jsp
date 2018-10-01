<%-- 
    Document   : registrarImagen
    Created on : 26-sep-2018, 14:56:48
    Author     : adri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar imagen</title>
    </head>
    <body>
        <h1>Registrar una imagen</h1> <br>
        
        <form method ="post" action="/WebApplicationREMASTERED/registrarImagen" enctype="multipart/form-data">
            Titol
            <input type="text" name="titol" placeholder="Titol de la foto">
            
            <div>Descripcio</div>
            <input type ="text" name="descripcio" placeholder="Descripció breu de la foto">
            
            <div>Paraules clau</div>
            <input type="text" name="paraulesclau" placeholder="Paraules clau de la foto">
            
            <div>Autor</div>
            <input type ="text" name="author" placeholder="Autor de la foto">
            
            <div>Data creacio</div>
            <input type="date" name="creationdate">
            
            <div>Fitxer</div>
            <input type="file" name="imatge">
            <input type="submit" name="Submit" value="Upload">
            
                     
        </form>
    </body>
</html>