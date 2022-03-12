package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.protocol.Message;

import dao.UserDao;
import entities.Massage;
import entities.User;
import helper.ConnectionProvider;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	PrintWriter out=response.getWriter();
		String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");
        
        UserDao dao = new UserDao(ConnectionProvider.getconnection());
        User u = dao.getUserByEmailAndPassword(userEmail, userPassword);

        		 if (u == null) {
                     //login.................
//                     error///
                 //  out.println("Invalid Details..try again");
                     Massage msg = new Massage("Invalid Details ! try with another", "error", "alert-danger");
                     HttpSession s = request.getSession();
                     s.setAttribute("msg", msg);

                    response.sendRedirect("login_page.jsp");
                 } else {
                     //......
//                     login success
                     HttpSession s = request.getSession();
                     s.setAttribute("currentUser", u);
                     response.sendRedirect("Profile.jsp");

                 }
	}

}
