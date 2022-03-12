package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;



import dao.UserDao;
import entities.Massage;
import entities.User;
import helper.ConnectionProvider;
import helper.Helper;

/**
 * Servlet implementation class EditServlet
 */
@MultipartConfig
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		
//      fetch all data
      String userEmail = request.getParameter("user_email");
      String userName = request.getParameter("user_name");
      String userPassword = request.getParameter("user_password");
      String userAbout = request.getParameter("user_about");
      Part part = request.getPart("image");

      String imageName = part.getSubmittedFileName();

      //get the user from the session...
		
		
		 HttpSession s = request.getSession();
         User user = (User) s.getAttribute("currentUser");
         
        System.out.println(user);
         user.setEmail(userEmail);
         user.setName(userName);
         user.setPassword(userPassword);
         user.setAbout(userAbout);
         String oldFile = user.getProfile();

         user.setProfile(imageName);
         System.out.println(user);
         //update database....
         UserDao userDao = new UserDao(ConnectionProvider.getconnection());

         boolean ans = userDao.updateUser(user);
         if (ans) {

             String path = request.getRealPath("/") + "pics" + File.separator + user.getProfile();

             //start of photo work
             //delete code
             @SuppressWarnings("deprecation")
			String pathOldFile = request.getRealPath("/") + "pics" + File.separator + oldFile;

             if (!oldFile.equals("default.png")) {
                 Helper.deleteFile(pathOldFile);
             }

             if (Helper.saveFile(part.getInputStream(), path)) {
                 out.println("Profile updated...");
                 Massage msg = new Massage("Profile details updated...", "success", "alert-success");
                 s.setAttribute("msg", msg);

             } else {
                 //////////////
                 Massage msg = new Massage("Something went wrong..", "error", "alert-danger");
                 s.setAttribute("msg", msg);
             }

             //end of phots work
         } else {
             out.println("not updated..");
             Massage msg = new Massage("Something went wrong..", "error", "alert-danger");
             s.setAttribute("msg", msg);

         }

         response.sendRedirect("profile.jsp");

         out.println("</body>");
         out.println("</html>");
     }
 

		
		
		
		
		
		
	}


