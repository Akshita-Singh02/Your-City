/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.help.cityhelper.servlets;

import com.help.cityhelper.entities.City;
import com.help.cityhelper.entities.User;
import com.help.cityhelper.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author akshita
 */
public class Register extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
          
       try {
                String userName = request.getParameter("user_name");
                String userEmail = request.getParameter("user_email");
                String userPassword = request.getParameter("user_password");
                int userPhone = Integer.parseInt(request.getParameter("user_phone"));
                int cityId = Integer.parseInt(request.getParameter("cId"));

                // Validation
                if (userName.isEmpty()) {
                    out.println("Enter name");
                    return;
                }

                // Get Hibernate session
                Session hibernateSession = FactoryProvider.getFactory().openSession();
                Transaction tx = hibernateSession.beginTransaction();

                // Fetch City entity
                City city = hibernateSession.get(City.class, cityId);
                if (city == null) {
                    out.println("City not found");
                    tx.rollback();
                    hibernateSession.close();
                    return;
                }

                // Create User object
                User user = new User(userName, city, userPassword, userPhone, "default.png", "normal", userEmail,"null");

                // Save User object
                int userId = (int) hibernateSession.save(user);
                tx.commit();
                hibernateSession.close();

                HttpSession httpsession = request.getSession();
                httpsession.setAttribute("message", "Your account is successfully created with user ID:" + userId);
                 response.sendRedirect("Registration.jsp");
                
            } catch (Exception e) {
                e.printStackTrace();
                HttpSession httpsession = request.getSession();
                httpsession.setAttribute("message", "Error:" + e);
                 response.sendRedirect("Registration.jsp");
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
