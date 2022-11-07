package memo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import memo.model.*;


@WebServlet("/MemoDelete")
public class MemoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		MemoDeleteServlet작성하기
//		1. 삭제할 글 번호 받아오기
		
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out= res.getWriter();
		String numStr=req.getParameter("idx");
//		2. 유효성 체크 (idx값이 null이거나 빈문자열)
//		=> redirect로 MemoList로 이동
		if(numStr==null||numStr.trim().isEmpty()) {
			res.sendRedirect("MemoList");
		}
		int idx=Integer.parseInt(numStr);
//		3. MemoDAO의 deleteMemo(idx)호출
		MemoDAO dao= new MemoDAO();
		try {
			int n=dao.deleteMemo(idx);
			if(n>0) {
				String str="글 삭제 성공";
				res.sendRedirect("MemoList");
				//out.println(str);
				System.out.println(idx+"번 "+str);
			} 
			else {
				String str="글 삭제 실패";
				res.sendRedirect("MemoList");
				System.out.println(idx+"번 "+str);
			}
			
		} catch (Exception e) {
			out.println(e.getMessage());
		}
//		4. 그 결과에 따라 메시지 처리 및 페이지 이동
//		삭제 성공==> MemoList
//		삭제 실패==> MemoList
		out.close();
	}

}
