/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.help.cityhelper.servlets;

import com.help.cityhelper.dao.CategoryDao;
import com.help.cityhelper.dao.CityDao;
import com.help.cityhelper.dao.PostDao;
import com.help.cityhelper.dao.UserDao;
import com.help.cityhelper.entities.Category;
import com.help.cityhelper.entities.City;
import com.help.cityhelper.entities.Post;
import com.help.cityhelper.entities.User;
import com.help.cityhelper.helper.FactoryProvider;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.*;
import java.io.*;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author akshita
 */

@MultipartConfig
public class OperationServlet extends HttpServlet implements ServletContextListener {

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
        ServletContext context = getServletContext();
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             HttpSession session = request.getSession(false);
        User user = null;
        if (session != null) {
            user = (User) session.getAttribute("current-user");
        }
  
String op=request.getParameter("operation");
if(op.trim().equals("addcategory"))
{
//            add category
    String title= request.getParameter("catTitle");
          
           Category category =new Category();
           category.setCategoryTitle(title);
           
           //saving data in db
           CategoryDao cd=new CategoryDao(FactoryProvider.getFactory());
           
           //transacting 
int catId=cd.saveCategory(category);
    HttpSession httpsession = request.getSession();
httpsession.setAttribute("message","category "+title+" added successfully with ID:"+catId);
response.sendRedirect("Admin.jsp");
return;
}

else if(op.trim().equals("addcity"))
{
    
    //add city
    
    String name=request.getParameter("cityName");
    int zip=Integer.parseInt(request.getParameter("cityZipcode"));
    //creating city entity obj to access setter methods
    City city=new City();
    city.setcName(name);
    city.setcZipcode(zip);
    
    //saving data
    CityDao cidao=new CityDao(FactoryProvider.getFactory());
    int cityId=cidao.saveCity(city);
    HttpSession httpsession = request.getSession();
httpsession.setAttribute("message","category "+name+" added successfully with ID:"+cityId);
response.sendRedirect("Admin.jsp");
return;
    
}
else if(op.trim().equals("addpost")) {
    
    // Extract parameters from the request
    String title = request.getParameter("pTitle");
    String description = request.getParameter("pDescription");
    int catid = Integer.parseInt(request.getParameter("catId"));
  int cid = Integer.parseInt(request.getParameter("cId"));
    int help = Integer.parseInt(request.getParameter("helpful"));
 Part part = request.getPart("pPhoto");

    // Create a new Post object and set its attributes
    Post p = new Post();
    p.setpName(title);
    p.setpContent(description);
    p.setHelpful(help);
    
 p.setpPic(part.getSubmittedFileName());

    // Fetch the associated Category using CategoryDao
    CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
    Category category = cdao.getCategoryById(catid);
    p.setCategory(category);

    // Fetch the associated City using CityDao
    CityDao cidao = new CityDao(FactoryProvider.getFactory());
    City city = cidao.getCityById(cid);
    p.setCity(city);
//fetching user from session
p.setUser(user);

    // Save the Post using PostDao
    PostDao pdao = new PostDao(FactoryProvider.getFactory());
    pdao.sPost(p);

    // Save the picture to the file system
     String path = getServletContext().getRealPath("img") + File.separator + "posts" + File.separator + part.getSubmittedFileName();
        System.out.println("File path: " + path);

        // Ensure the directory exists
        File uploadDir = new File(getServletContext().getRealPath("img") + File.separator + "posts");
        System.out.println("path is : "+uploadDir);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create directories if they don't exist
        }

        // Save the picture to the file system
        try (FileOutputStream fos = new FileOutputStream(path);
             InputStream is = part.getInputStream()) {

            // Reading data from the InputStream and writing to the FileOutputStream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    




    // Set a success message in the session and redirect to the Admin page
    HttpSession httpsession = request.getSession();
    httpsession.setAttribute("message", "Post added successfully with ID: " + p.getpId());
    if(user.getuName()=="Admin")
    {
         response.sendRedirect("Admin.jsp");
    }
    else{
         response.sendRedirect("Account.jsp");
    }
   
}

else if(op.trim().equals("removecategory"))
{
   int id=Integer.parseInt(request.getParameter("categoryid"));
    CategoryDao abc=new CategoryDao(FactoryProvider.getFactory());
    abc.removeCategoryById(id);
    HttpSession httpsession = request.getSession();
httpsession.setAttribute("message","category removed successfully with ID:"+id);
response.sendRedirect("Admin.jsp");
return;
    
}
else if(op.trim().equals("removecategory"))
{
   int id=Integer.parseInt(request.getParameter("categoryid"));
    CategoryDao abc=new CategoryDao(FactoryProvider.getFactory());
    abc.removeCategoryById(id);
    HttpSession httpsession = request.getSession();
httpsession.setAttribute("message","category removed successfully with ID:"+id);
response.sendRedirect("Admin.jsp");
return;
    
}
else if(op.trim().equals("removecity"))
{
   int id=Integer.parseInt(request.getParameter("cityid"));
    CityDao dom=new CityDao(FactoryProvider.getFactory());
    dom.removeCityById(id);
    HttpSession httpsession = request.getSession();
httpsession.setAttribute("message","city removed successfully with ID:"+id);
response.sendRedirect("Admin.jsp");
return;
}

else if(op.trim().equals("removeUser"))
{
   int id=Integer.parseInt(request.getParameter("userid"));
    UserDao o=new UserDao(FactoryProvider.getFactory());
    o.removeUserById(id);
    HttpSession httpsession = request.getSession();
httpsession.setAttribute("message","user removed successfully with ID:"+id);
response.sendRedirect("Admin.jsp");
return;
}

else if(op.trim().equals("removePost"))
{
   int id=Integer.parseInt(request.getParameter("postid"));
    PostDao io=new PostDao(FactoryProvider.getFactory());
    io.removePostById(id);
    HttpSession httpsession = request.getSession();
httpsession.setAttribute("message","post removed successfully with ID:"+id);
response.sendRedirect("Admin.jsp");
return;
}
else if(op.trim().equals("updatepic"))
{
    
   Part part = request.getPart("pPhoto");
   
    
//                User p=new User(user.getuId());
//                p.setuPic(part.getSubmittedFileName());
int id=user.getuId();
String pic=part.getSubmittedFileName();
                UserDao ud=new UserDao(FactoryProvider.getFactory());
                ud.sProfile(id,pic);
   String path = getServletContext().getRealPath("img") + File.separator + "posts" + File.separator + part.getSubmittedFileName();
        System.out.println("File path: " + path);

        // Ensure the directory exists
        File uploadDir = new File(getServletContext().getRealPath("img") + File.separator + "posts");
        System.out.println("path is : "+uploadDir);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create directories if they don't exist
        }

        // Save the picture to the file system
        try (FileOutputStream fos = new FileOutputStream(path);
             InputStream is = part.getInputStream()) {

            // Reading data from the InputStream and writing to the FileOutputStream
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    HttpSession httpsession = request.getSession();
httpsession.setAttribute("message","profile updated successfully with name:"+user.getuName());
response.sendRedirect("Account.jsp");
return;
}

else if(op.trim().equals("updatebio"))
{
    
   String mybio=request.getParameter("uBio");
int id=user.getuId();
                UserDao ud=new UserDao(FactoryProvider.getFactory());
                ud.sBio(id,mybio);
    HttpSession httpsession = request.getSession();
httpsession.setAttribute("message","bio updated successfully with name:"+user.getuName());
response.sendRedirect("Account.jsp");
return;
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
