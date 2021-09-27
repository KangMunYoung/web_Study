package com.bitcamp.data;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bitcamp.home.CommandService;

public class ViewCommand implements CommandService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			int num = Integer.parseInt(request.getParameter("num"));
			DataDAO dao = new DataDAO();
			dao.hitCount(num);//조회수 증가
			DataVO vo = dao.oneSelect(num);
			request.setAttribute("dataVo", vo);
			
		return "dataView.jsp";
	}

}
