package memo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.*;
import memo.model.*;
import java.util.*;

@WebServlet("/MemoList")
public class MemoListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out= res.getWriter();
		//전체 메모 목록 가져오기
		MemoDAO dao=new MemoDAO();
		try {
			List<MemoVO>arr=dao.selectMemoAll();
			//모델통해서 가져온 데이터를 req에 저장한 뒤, view페이지로 이동한다.
			//==>이때 주의. redirect방식이 아니라 forward방식으로 이동해야한다.
			//req.setAttribute(key,value);
			req.setAttribute("memoArr", arr);
			
		
			
			String viewPage="memo/list.jsp";
			//res.sendRedirect(viewPage);//새로운 요청을 발생시킨다.
			//forward이동
			RequestDispatcher disp=req.getRequestDispatcher(viewPage);
			disp.forward(req, res);//forward 이동할 때 req,res를 가지고간다.
			//redirect는 url을 변경시키고 새로운 request를 발생시킴
			//forward는 url그대로, 서버내부에서 보여주는 페이지를 처리
			//==> 포워드는 url은 MemoList이지만 화면은 list.jsp를 보게됨
			
			
		}catch(Exception e) {
			e.printStackTrace();
			out.println(e.getMessage());
		}
		
		out.close();
	}
}
