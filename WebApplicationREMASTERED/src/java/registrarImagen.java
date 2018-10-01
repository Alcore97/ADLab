/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.io.File;

/**
 *
 * @author adri
 */
@WebServlet(name = "registrarImagen", urlPatterns = {"/registrarImagen"})
public class registrarImagen extends HttpServlet {

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
            
            String titulo = request.getParameter("titol");
            String desc = request.getParameter("descripcio");
            String pclau = request.getParameter("paraulesclau");
            String autor = request.getParameter("author");
            String data = request.getParameter("creationdate");
            //String nom = request.getParameter("name");
            String nom_image = request.getParameter("imatge");
            java.util.Date d = new java.util.Date();
            
            String path = "C:\\Users\\aleix\\Desktop\\Escritorio\\pro2\\JavaMasterRace\\NetBeans\\WebApplicationREMASTERED\\web\\images";
            
            out.println("Esto es el nombre: "+nom_image);
            
            Class.forName("org.sqlite.JDBC");
            
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\aleix\\Desktop\\Escritorio\\pro2\\JavaMasterRace\\NetBeans\\LIBRERIA.db");
            
            
            PreparedStatement statement = connection.prepareStatement("select MAX(id_imatge) from imatges");
            
             ResultSet rs = statement.executeQuery();
             out.println("Mi id= "+ rs.getInt(1));
             if(rs.next()) {
                 int id_imatge = rs.getInt(1);
                 out.println("Mi id = "+ id_imatge);
             } 
            // out.println("Mi id"+ rs.getInt(1));
             int id_imatge = rs.getInt(1);
             //out.println("Mi id"+ id_imatge);
             
            PreparedStatement statement2 = connection.prepareStatement("insert into imatges values(?,?,?,?,?,?,?,?)");
            
            statement2.setInt(1,++id_imatge);
            statement2.setString(2,titulo);
            statement2.setString(3,desc);
            statement2.setString(4,pclau);
            statement2.setString(5,autor);
            statement2.setString(6,data);
            statement2.setDate(7,d);
            statement2.setString(8,path); 
            
            ResultSet rs2 = statement2.executeQuery();
                    
            
            
            
            
            
            
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