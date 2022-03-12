package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LikeDao;
import helper.ConnectionProvider;

/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		   String operation = request.getParameter("operation");
           int uid = Integer.parseInt(request.getParameter("uid"));
           int pid = Integer.parseInt(request.getParameter("pid"));

//           out.println("data from server");
//           out.println(operation);
//           out.println(uid);
//           out.println(pid);
           LikeDao ldao = new LikeDao(ConnectionProvider.getconnection());
           if (operation.equals("like")) {
               boolean f=ldao.insertLike(pid, uid);
             
           }
       }
		
		
		
		
	}


