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

@WebServlet("/newUser")
public class CreateNewUser extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher rDispatcher = req.getRequestDispatcher("WEB-INF/views/FormForNewUser.html");
		rDispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String surname = req.getParameter("surname");
		User user = new User(name, surname);
		
		//add user to database
		try {
			UserDAO.create(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestDispatcher rDispatcher = req.getRequestDispatcher("index.jsp");
		rDispatcher.forward(req, resp);

	}
}
