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
@WebServlet(name = "registrarImagen", urlPatterns = {"/registrarImagen"})
@MultipartConfig
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
      private final static Logger LOGGER = 
            Logger.getLogger(registrarImagen.class.getCanonicalName());
    private String getFileName(final Part part) {
    final String partHeader = part.getHeader("content-disposition");
    LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
    for (String content : part.getHeader("content-disposition").split(";")) {
        if (content.trim().startsWith("filename")) {
            return content.substring(
                    content.indexOf('=') + 1).trim().replace("\"", "");
        }
    }
    return null;
}
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        Connection connection = null;
        OutputStream out = null;
        InputStream filecontent = null;
        try {
            /* TODO output your page here. You may use following sample code. */
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\aleix\\Desktop\\Escritorio\\pro2\\JavaMasterRace\\NetBeans\\LIBRERIA.db");
            final String path = "C:\\Users\\aleix\\Desktop\\Escritorio\\pro2\\JavaMasterRace\\NetBeans\\WebApplicationREMASTERED\\web\\images";
            final Part filePart = request.getPart("imatge");
            final String fileName = getFileName(filePart);
             
     
            out = new FileOutputStream(new File(path + File.separator + fileName));
            filecontent = filePart.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            //writer.println("New file " + fileName + " created at " + path);
            LOGGER.log(Level.INFO, "File{0}being uploaded to {1}", 
                    new Object[]{fileName, path});

            String titulo = request.getParameter("titol");
            String desc = request.getParameter("descripcio");
            String pclau = request.getParameter("paraulesclau");
            String autor = request.getParameter("author");
            String data = request.getParameter("creationdate");
            java.util.Date d = new java.util.Date();
            //out2.println("El nom es: " + titulo);
            PreparedStatement statement = connection.prepareStatement("select MAX(id_imatge) from imatges");
         
            ResultSet rs2 = statement.executeQuery();
            int id_imatge = 0; 
            
            if(rs2.next()) id_imatge = rs2.getInt(1);            
            else response.sendRedirect("error?tipus=tornamenu");
            System.out.println("El id es: " + id_imatge);
             
            PreparedStatement statement2 = connection.prepareStatement("insert into imatges values(?,?,?,?,?,?,?,?)");
            
            System.out.println("El id es: " + id_imatge);    
            statement2.setInt(1,++id_imatge);
            statement2.setString(2,titulo);
            statement2.setString(3,desc);
            statement2.setString(4,pclau);
            statement2.setString(5,autor);
            statement2.setString(6,data);
            statement2.setString(7,d.toString());
            statement2.setString(8,fileName); 
            //writer.println("El id es: " + id_imatge);
            statement2.executeUpdate();
            //writer.println("El id es: " + id_imatge);
           // response.sendRedirect("error?tipus=tornamenu");
            
            writer.println("<html> <body> "
                    + "<h2>L'imatge s'ha pujat satisfactoriament</h2> "
                    + "<br>"
                    + "<a href='menu.jsp'>Tornar al menu</a>"
                    + "<br> "
                    + "<a href='registrarImagen.jsp'>Registrar otra imagen</a>"
                    + "</body></html>");

        }
        catch(SQLException e){
          System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.getMessage());
         }
            catch (FileNotFoundException fne) {
                writer.println("You either did not specify a file to upload or are "
                + "trying to upload a file to a protected or nonexistent "
                + "location.");
                writer.println("<br/> ERROR: " + fne.getMessage());

                LOGGER.log(Level.SEVERE, "Problems during file upload. Error: {0}", 
                new Object[]{fne.getMessage()});
            }
        finally
        {
          try
          {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
            if (writer != null) {
                writer.close();
            }
            if(connection != null){
              connection.close();
            }
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