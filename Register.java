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
 * Servlet implementation class Register
 */
@WebServlet("/adduser")
public class Register extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		
		User u = new User(username,password,email);
		
		UserDaoImpl udaoi = new UserDaoImpl();
		
		int x = udaoi.insertUser(u);
		
		if(x==0) {
			resp.sendRedirect("failure.html");
		}
		else {
			resp.sendRedirect("success.html");
		}
		
	}

}
