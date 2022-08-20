package controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import models.User;
@WebServlet("/userProfile")
public class UserProfile extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("id"));
		String action = req.getParameter("action");
		RequestDispatcher requestDispatcher;
		
		//get user from DB
		if(action == null) {
			try {
				requestDispatcher = req.getRequestDispatcher("WEB-INF/views/UserProfile.jsp");
				User user = UserDAO.getUser(userId);
				req.setAttribute("user", user);
				requestDispatcher.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//form for update
		if(action.equals("update")) {
			doPut(req, resp);
		}
		
		//delete
		if(action.equals("delete")) {
			doDelete(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		User user = new User(id,name,surname);
		
		try {
			UserDAO.updateUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
		requestDispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int userId = Integer.parseInt(req.getParameter("id"));
		RequestDispatcher requestDispatcher;
		
		try {
			requestDispatcher = req.getRequestDispatcher("WEB-INF/views/FormForUpdate.jsp");
			User user = UserDAO.getUser(userId);
			req.setAttribute("user", user);
			requestDispatcher.forward(req, resp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		try {
			UserDAO.deleteUser(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
		requestDispatcher.forward(req, resp);
	}
	
}
