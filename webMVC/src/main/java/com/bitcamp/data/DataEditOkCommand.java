package com.bitcamp.data;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.bitcamp.home.CommandService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class DataEditOkCommand implements CommandService {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//파일업로드 위치
		String path = request.getServletContext().getRealPath("/upload");
		int maxSize = 1024*1024*1024;
		DefaultFileRenamePolicy pol = new DefaultFileRenamePolicy();
		//													경로		파일크기	인코딩		파일리네임
		MultipartRequest mr = new MultipartRequest(request, path, maxSize,"UTF-8",pol);
		
		//업로드 완료
		DataVO vo = new DataVO();
		vo.setNum(Integer.parseInt(mr.getParameter("num")));
		vo.setTitle(mr.getParameter("title"));
		vo.setContent(mr.getParameter("content"));
		vo.setDelfile(mr.getParameter("delfile"));
		
		String newFilename = mr.getFilesystemName("filename");
		vo.setFilename(newFilename);//새로추가된 업로드 파일
		
		//---------------------------------------------
		DataDAO dao = new DataDAO();
		int result = dao.dataUpdate(vo);
		
		if(result > 0) {//레코드 수정완료
			//삭제된 파일 지우기
			if(vo.getDelfile() != null && !vo.getDelfile().equals("")) {
				File f = new File(path, vo.getDelfile());
				f.delete();
			}
		}else {//레코드 수정못함
			if(vo.getFilename() !=null && !vo.getFilename().equals("")) {
				File f = new File(path, vo.getFilename());
				f.delete();
			}
		}
		
		
		
		request.setAttribute("num", vo.getNum());
		request.setAttribute("result", result);
		
		return "dataEditResult.jsp";
	}

}
