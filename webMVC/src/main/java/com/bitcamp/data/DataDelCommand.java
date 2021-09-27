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
		System.out.println("datadel ���Դپƾƾ�");
		int num = Integer.parseInt(request.getParameter("num"));
		
		//������ ���ڵ��� ���ϸ��� ����
		DataDAO dao = new DataDAO();
		String dbfile = dao.getDbFile(num);
		System.out.println("num �ʸ� ����"+num);
		
		HttpSession session = request.getSession();
		String userid = (String)session.getAttribute("userid");
		
		int cnt = dao.dataDelete(num, userid);
		System.out.println("cnt �� ����"+cnt);
		if(cnt > 0) {//���� -> ���ϻ���
			
			String path = request.getServletContext().getRealPath("/upload");
			File f = new File(path, dbfile);
			f.delete();
			System.out.println("���������� �ϴ� ok"+path);
		}
		request.setAttribute("cnt", cnt);
		request.setAttribute("num", num);
		return "delOk.jsp";
	}

}
