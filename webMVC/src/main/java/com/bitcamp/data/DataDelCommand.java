package com.bitcamp.data;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bitcamp.home.CommandService;

public class DataDelCommand implements CommandService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("datadel 들어왔다아아아");
		int num = Integer.parseInt(request.getParameter("num"));
		
		//삭제할 레코드의 파일명을 선택
		DataDAO dao = new DataDAO();
		String dbfile = dao.getDbFile(num);
		System.out.println("num 너를 보자"+num);
		
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		
		int cnt = dao.dataDelete(num, userid);
		System.out.println("cnt 넌 뭐니"+cnt);
		if(cnt > 0) {//삭제 -> 파일삭제
			
			String path = request.getServletContext().getRealPath("/upload");
			File f = new File(path, dbfile);
			f.delete();
			System.out.println("뭔지모르지만 일단 ok"+path);
		}
		request.setAttribute("cnt", cnt);
		request.setAttribute("num", num);
		return "delOk.jsp";
	}

}
