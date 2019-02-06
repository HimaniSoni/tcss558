/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Himani Soni
 */
public class Demo extends HttpServlet {

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
        //String name;
        //name = request.getParameter("name");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Demo</title>");            
            out.println("</head>");
            out.println("<body>");
            //out.println("<h1>Servlet Demo at " + name + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            //processRequest(request, response);
            response.setContentType("text/html;charset=UTF-8");
            String name;
            name = request.getParameter("name");
            int balance;
            balance =  Integer.parseInt(request.getParameter("balance"));
            //int money;
            //money =  Integer.parseInt(request.getParameter("money"));
            //int mbalance;
            //mbalance =  Integer.parseInt(request.getParameter("mbalance"));
            
            try (PrintWriter out = response.getWriter()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String connectionURL;
                connectionURL = "jdbc:mysql://node207144-env-0889003.j.layershift.co.uk/demoDBJelastic";
                //ConnectionURL, username and password should be specified in getConnection()
                try {
                    Connection conn;
                    conn = DriverManager.getConnection(connectionURL, "tcss558", "ORamb85zNe");
                    System.out.println("Connect successfully ! ");
                    PreparedStatement ps;
                    ps = conn.prepareStatement("insert into TABLEDEMO(name,balance) values(?,?)");
                        ps.setString(1, name);
                        ps.setInt(2, balance);

                        synchronized (this) 
                        {
                            int i = ps.executeUpdate();
                            if (i <= 0) {
                                out.print("You are not registered...");
                            } else {
                                out.print("You are successfully registered...");
                            }
                        }
                    /*
                    if("".equals(money))
                    {
                        
                    }else{
                        PreparedStatement ps = conn.prepareStatement("update TABLEDEMO SET balance=? WHERE money=?");
                        ps.setInt(1, mbalance);
                        ps.setInt(2, money);

                        synchronized (this) 
                        {
                            int i = ps.executeUpdate();
                            if (i <= 0) {
                                out.print("Record not updated...");
                            } else {
                                out.print("Record updated successfully...");
                            }
                        }
                        
                    }
                    */
                   
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println("Connect failed ! ");
                    System.out.println(ex);
                }
                out.close();
            
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
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
