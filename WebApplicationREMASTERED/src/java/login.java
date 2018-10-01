
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

/**
 *
 * @author adri
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

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
            
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            
            statement.executeUpdate("drop table if exists usuaris");
            statement.executeUpdate("drop table if exists imatges");
                                
            statement.executeUpdate("create table usuaris (id_usuario string primary key, password string)");
            statement.executeUpdate("insert into usuaris values('adri','chuf')");
            statement.executeUpdate("insert into usuaris values('aleix','cusetes1719')");

            statement.executeUpdate("create table imatges (id_imatge integer ,titol_imatge string primary key, descripcio string,"
                    + "paraula_clau string, autor string, data_creacio string, data_pujada string, nom string)");
            statement.executeUpdate("insert into imatges  values('1','1', '22', 'IBE','BCN','09:00','SVQ','10:25')");
            
            String u = request.getParameter("user");
            String p = request.getParameter("pass");
            
            PreparedStatement statement2 = connection.prepareStatement("select * from usuaris where id_usuario = ? and password = ?");
            statement2.setString(1,u);
            statement2.setString(2,p);
            
            ResultSet rs = statement2.executeQuery();
            
            if(!rs.next()) response.sendRedirect("error.jsp");
            else response.sendRedirect("menu.jsp");
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