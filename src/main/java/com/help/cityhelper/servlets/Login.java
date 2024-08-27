/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.help.cityhelper.servlets;

import com.help.cityhelper.dao.UserDao;
import com.help.cityhelper.entities.User;
import com.help.cityhelper.helper.FactoryProvider;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

/**
 *
 * @author akshita
 */
public class Login extends HttpServlet {

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
            
           String email=request.getParameter("user_email");
            String password=request.getParameter("user_password");
            
            //user obj
            UserDao userdao=new UserDao(FactoryProvider.getFactory());
            User user=userdao.getUserByEmailAndPassword(email, password);
            HttpSession httpsession= request.getSession();
            if(user==null)
            {
                 
              httpsession.setAttribute("message","Try with another credentials !!");
              response.sendRedirect("Login.jsp");
              return;
              
                
            }
            
            else{
                 httpsession.setAttribute("current-user", user);
                
                if(user.getuType().equals("admin"))
                {
                    response.sendRedirect("Admin.jsp");
                    
                    
                }
                else if(user.getuType().equals("normal"))
                {
                    response.sendRedirect("Home.jsp");
                }
                else
                {
                    out.println("Not identified");
                }
               
                
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
