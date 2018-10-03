<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.ServletException"%>
<%@page import="javax.servlet.annotation.WebServlet"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.sql.*"%>
<%@page import="javax.servlet.http.*"%>
<%@page import="java.util.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Llistar imatges</title>
    </head>
    <body>
                
        <%
            Connection connection = null;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\aleix\\Desktop\\Escritorio\\pro2\\JavaMasterRace\\NetBeans\\LIBRERIA.db");
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("select nom, autor, id_imatge from imatges");
                out.println("<html><body>"
                            + "<table id=1 style='width:50%'>"
                            + "<tr>"
                            + "<th>Nom imatge</th>"
                            + "<th>Link</th>"
                            + "<th>Modificar</th>"
                            + "</tr>");
                while(rs.next()) {
                        int num = rs.getInt("id_imatge");
                        String a = rs.getString("autor");                      
                        
                            
                            out.println("<html><body>"
                            + "<tr>"
                            + "<td style='text-align:center;'>" + rs.getString("nom") + "</td>"
                            + "<td style='text-align:center;'> <a href='.\\images\\" + rs.getString("nom") + "'>Link</a> </td>"
                            + "<td style='text-align:center;'> <a href='.\\modificarImagen.jsp'>Modificar imatge</a> </td>");
                            
                        
                      
                }
            } 
            catch(SQLException e)
        {
          System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }   
        finally
        {
          try
          {
            if(connection != null)
              connection.close();
          }
          catch(SQLException e)
          {
            // connection close failed.
            System.err.println(e.getMessage());
          }
        }
            
            %>
        <h1>Llistat d'imatges</h1>
    </body>
</html>
           
