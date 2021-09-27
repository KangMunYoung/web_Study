package com.bitcamp.home;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/index.do")
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ServletTest() {
        super();
    }

    //doGet(), doPost()이전에 실행됨.
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init");
	}
	


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet"+request.getParameter("name"));
		
		//접속자에게 웹페이지 보내기
		
		//헤더지정하기
		response.setContentType("text/html; charset=UTF-8");
		
		//클라이언트에게 보낼 output객체 만들기
		PrintWriter pw = response.getWriter();
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<head><title>서블릿으로 홈페이지에 접속하기</title>");
		pw.println("<style>");
		pw.println("body{color:red}");
		pw.println("</style>");
		pw.println("<script>");
		pw.println("document.write('servlet테스트중<br/>')");
		pw.println("</script>");
		pw.println("</head><body>");
		pw.println("<h1>서블릿을 이용한 홈</h1>");
		pw.println("<a href='/webServlet/loginForm.do'><img src='../img/natural.jpg' width='200'/>로그인</a>");
		pw.println("</body></html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
	}

}
