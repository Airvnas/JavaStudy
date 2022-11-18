package board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import board.model.BoardDAOMyBatis;
import board.model.BoardVO;
import common.controller.AbstractAction;
import user.model.UserVO;

public class BoardEditAction extends AbstractAction {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session= req.getSession();
		UserVO user=(UserVO)session.getAttribute("loginUser");
		if(user==null) {
			req.setAttribute("msg","로그인해야 글수정이 가능합니다.");
			req.setAttribute("loc","javascript:history.back()");
			this.setViewPage("message.jsp");
			this.setRediret(false);
			return;
		}
		
		ServletContext application=req.getServletContext();
		String upDir=application.getRealPath("/Upload");
		System.out.println("upDir:"+upDir);
		File dir=new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs();//업로드 디렉토리 생성
		}
		
		MultipartRequest mr=null;
		try {
			mr= new MultipartRequest(req, upDir,100*1024*1024,"UTF-8",new DefaultFileRenamePolicy());
			System.out.println("파일 업로드 성공");
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		// 1. num, userid,subject,content, filename 값 받기
		String numStr=mr.getParameter("num");
		String subject=mr.getParameter("subject");
		String userid=user.getUserid();
		String content=mr.getParameter("content");
		//String filename=req.getParameter("filename");
		String filename=mr.getFilesystemName("filename");
		File file=mr.getFile("filename");
		long filesize=0;
		
		if(file!=null) {//첨부한 파일이 있다면
			filesize=file.length();
			//예전에 첨부한 파일명 얻기
			String old_file=mr.getParameter("old_file");
			if(old_file!=null&& !old_file.trim().isEmpty()) {
				//서버에서 예전파일 지우기
				File delFile=new File(upDir,old_file);
				if(delFile!=null) {
					boolean b=delFile.delete();
					System.out.println("파일 삭제 여부:"+b);
				}
			}
			
		}//if-------
		
		// 2. 유효성 체크 (num, subject,userid)
		if(numStr==null||subject==null||userid==null||
				numStr.trim().isEmpty()||subject.trim().isEmpty()||userid.trim().isEmpty()) {
			this.setViewPage("boardList.do");
			this.setRediret(true);
			return;
		}
		int num=Integer.parseInt(numStr.trim());
		//3. 1번에서 받은 값 BoardVO에 담아주기
		BoardVO vo= new BoardVO(num,userid,subject,content,null,filename,filesize);
		BoardDAOMyBatis dao=new BoardDAOMyBatis();
		int n=dao.updateBoard(vo);
		String msg=(n>0)?"글 수정 성공":"글 수정 실패";
		String loc="boardList.do";
		req.setAttribute("msg",msg);
		req.setAttribute("loc", loc);
		this.setViewPage("message.jsp");
		this.setRediret(false);
			
	}

}
