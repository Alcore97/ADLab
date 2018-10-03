
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.lang.Object; 
import javax.servlet.http.Part;
/**
 *
 * @author adri
 */
@WebServlet(name = "buscarImagen", urlPatterns = {"/buscarImagen"})
@MultipartConfig
public class buscarImagen extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection connection = null;
        try {
            /* TODO output your page here. You may use following sample code. */
                       
            Class.forName("org.sqlite.JDBC");
            
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\aleix\\Desktop\\Escritorio\\pro2\\JavaMasterRace\\NetBeans\\LIBRERIA.db");
            
            String peticion="select nom,autor from imatges where 1=1";
            //out.println("La meva peticio es: "+  peticion);
            String t = request.getParameter("titol");
            String d = request.getParameter("descripcio");
            String p = request.getParameter("paraulesclau");
            String a = request.getParameter("author");
            String n = request.getParameter("fitxer");
           // out.println("La meva peticio es: "+  peticion +" amb el nom: "+ t +"amb la descripcio: " + d + "amb la paraulaclau: " + p);
           
            boolean b1,b2,b3,b4,b5;
            b1 = b2 = b3 = b4 = b5 = false;
            int cont = 0;         
            if(!t.isEmpty()){
                peticion += " and titol_imatge = ?";
                b1 = true; 
            }
            if(!d.isEmpty()){
                peticion += " and descripcio = ?";
                b2 = true; 
                }
            if(!p.isEmpty()){
                peticion += " and paraula_clau = ?";
                b3 = true; 
                    }
            if(!a.isEmpty()){
                peticion += " and autor = ?";
                b4 = true; 
                        }
            if(!n.isEmpty()){
                 peticion += " and nom = ?";
                 b5 = true; 
                            }
            //out.println("La meva peticio es: "+  peticion);
            //out.println("HOLA");
            PreparedStatement statement = connection.prepareStatement(peticion);
            
           //out.println("HOLA");
           if(b1){
              ++cont;
               statement.setString(cont,t); 
           }
           if(b2){
               ++cont;
                statement.setString(cont,d);
           }
           if(b3){
              ++cont;
               statement.setString(cont,p); 
           }
           if(b4){
               ++cont;
                statement.setString(cont,a);
           }
           if(b5){
              ++cont;
               statement.setString(cont,n); 
           }
          // out.println("HOLA");
            ResultSet rs = statement.executeQuery();
            //out.println("El meu rs.next es: " + rs.next());
            
           if(rs.isAfterLast()) response.sendRedirect("error?tipus=Notrobat");
           else{
                //out.println("HOLA");
                out.println("<html><body>"
                        + "<h1 style='text-align:center;'> LA MEVA CERCA </h1>"
                            + "<table style='width:100%;text-align:center;'>"
                            + "<tr>"
                            + "<th>Nom imatge</th>"
                            + "<th>Link</th>"
                            + "<th>Modificar</th>"
                            + "</tr>");
                //out.println("El meu rs.next es: " + rs.next());
                //if(rs.isAfterLast())
                
               // out.println("El meu rs.next es: " + bool);
                
                while(rs.next()){
                    //out.println("HOLA");
                    String aut = rs.getString("autor");                      
                        
                            out.println("<html><body>"
                            + "<tr>"
                            + "<td style='text-align:center;'>" + rs.getString("nom") + "</td>"
                            + "<td style='text-align:center;'> <a href='.\\images\\" + rs.getString("nom") + "'>Link</a> </td>"
                            + "<td style='text-align:center;'> <a href='.\\modificarImagen.jsp'>Modificar imatge</a> </td>");
                } //mostra les imatges en forma de llista
            }
         out.println("<html> <body>"
                 + "</table>"
                 + "<a href='menu.jsp'> Tornar a menu!</a>"
                 + "</body> </html>");
         
        
           
            
      
        }
        catch(SQLException e)
        {
          System.err.println(e.getMessage());
          out.println("hola");
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
       }
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}