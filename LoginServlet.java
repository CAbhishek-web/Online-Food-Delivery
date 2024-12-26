package com.tap.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.foodapp.DaoImpl.UserDaoImpl;
import com.foodapp.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		UserDaoImpl daoi = new UserDaoImpl();
		
		User us = daoi.fetchUserById(username, password);
		
		if(us!=null) {
			resp.sendRedirect("home.html");
		}
		else {
			resp.sendRedirect("register.html");
		}
	}
}
