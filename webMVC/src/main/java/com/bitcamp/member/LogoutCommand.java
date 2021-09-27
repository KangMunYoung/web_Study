package com.bitcamp.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class LogoutCommand implements CommandService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//·Î±×¾Æ¿ô
		HttpSession session = request.getSession();
		session.invalidate();
		return "/index.jsp";
	}

}
