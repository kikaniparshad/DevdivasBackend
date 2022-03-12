package servlets;


import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import entities.User;

import helper.ConnectionProvider;
import entities.User;
/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	  PrintWriter out=  response.getWriter();
		 String check = request.getParameter("check");
         if (check == null) 
         {
             out.println("box not checked");
             
         } 
       
         else 
         {
		  String name = request.getParameter("user_name");
          String email = request.getParameter("user_email");
          String password = request.getParameter("user_password");
          String gender = request.getParameter("gender");
          String about = request.getParameter("about");
          
          User user = new User(name, email, password, gender, about);
          
          UserDao dao = new UserDao(ConnectionProvider.getconnection());
          if (dao.saveUser(user)) {
//            save..
                out.println("done");
            } else {
                out.println("error");
            }
         }
        
	}

}
