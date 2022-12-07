package com.my.multiweb;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.model.BoardVO;
import com.board.service.BoardService;
import com.common.CommonUtil;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@Log4j
public class BoardController {
	
	@Resource(name="boardServiceImpl")
	private BoardService boardService;
	
	@Inject
	private CommonUtil util;
	
	
	@GetMapping("/write")
	public String boardForm() {
		
		return "board/boardWrite";
	}
	
	@PostMapping("/write")
	public String boardInsert(Model m,@RequestParam("mfilename") MultipartFile mfilename,
						@ModelAttribute BoardVO board,HttpServletRequest req) {
		log.info("board-==="+board);
		//1.파일 업로드 처리
		//업로드 디렉토리 절대경로 얻기
		ServletContext app=req.getServletContext();
		String upDir=app.getRealPath("/resources/board_upload");
		File dir=new File(upDir);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		if(!mfilename.isEmpty()) {//첨부파일이 있다면
			//1)첨부파밀명, 파일크기 알아내자
			String originFname=mfilename.getOriginalFilename();
			long fsize=mfilename.getSize();
			log.info(originFname+">>>"+fsize);
			
			//2)동일한 파일명이 서버에 있을 경우 덮어쓰기를 방지하기 위해 "랜덤한 문자열_원본파일명"==>물리적 파일명 생성
			UUID uuid=UUID.randomUUID();
			String filename=uuid.toString()+"_"+originFname;//물리적 파일명=>실제 업로드시킬 파일명
			log.info("filename==="+filename);//
			//3_1)mode가 edit이고 예전에 첨부했던 파일이 남아있다면 예전파일 삭제처리
			if(board.getMode().equals("edit")&&board.getOld_filename()!=null) {
				//수정모드라면 예전에 업로드했던 파일은 삭제처리
				File delF=new File(upDir,board.getOld_filename());
				if(delF.exists()) {
					boolean b=delF.delete();
					log.info("old File 삭제:"+b);
				}
			}
			
			//3_2)업로드 처리
			try {
				mfilename.transferTo(new File(upDir,filename));
				log.info("upDir=="+upDir);
			} catch (Exception e) {
				log.error("board wirte upload error : "+e);
			}
			//4)BoardVO객체에 filename,originFilename,filesize 세팅
			board.setFilename(filename);
			board.setOriginFilename(originFname);
			board.setFilesize(fsize);
		}
		
		//2.유효성 체크
		if(board.getSubject()==null||board.getName()==null||board.getPasswd()==null||
				board.getSubject().trim().isEmpty()||board.getName().trim().isEmpty()
				||board.getPasswd().trim().isEmpty()) {
			return "redirect:write";
		}
		int n=0;
		String str="",loc="";
		
		
		if("write".equals(board.getMode())) {//글쓰기 모드라면
			n=boardService.insertBoard(board);
			str="글쓰기 ";
		}else if("rewrite".equals(board.getMode())) {//답변 글쓰기라면
			n=this.boardService.rewriteBoard(board);
			str="답변 글쓰기 ";
		}else if("edit".equals(board.getMode())) {//글 수정이라면
			n=boardService.updateBoard(board);
			str="글 수정 "; 
		}
		
		str+=(n>0)?"성공":"실패";
		loc=(n>0)?"list":"javascript:history.back()";
		
		//m.addAttribute("message",str);
		//m.addAttribute("loc",loc);
		
		return util.addMsgLoc(m, str, loc);//msg를 반환
	}//--------------------------------------------------------
	
	@GetMapping("/list")
	public String boardList(Model m) {
		List<BoardVO> arr=boardService.selectBoardAll(null);
		m.addAttribute("boardArr",arr);
		
		return "board/boardList";
	}
	
	@GetMapping("view/{num}")
	public String boardView(Model m,@PathVariable("num") int num) {
		log.info("num==="+num);
		//조회수 증가
		int n=this.boardService.updateReadnum(num);
		BoardVO board=this.boardService.selectBoardByIdx(num);
		
		m.addAttribute("board",board);
		return "board/boardView";
	}//-----------------------------------------------
	
	@PostMapping("/delete")
	public String boardDelete(Model m,
			HttpServletRequest req,
			@RequestParam(defaultValue="0")int num,
			@RequestParam(defaultValue="") String passwd) {
		log.info("num==="+num+",passwd==="+passwd);
		if(num==0||passwd.isEmpty()) {
			return "redirect:list";
		}
		//해당 글 db에서 가져오기
		BoardVO vo= this.boardService.selectBoardByIdx(num);
		if(vo==null) {
			return util.addMsgBack(m, "해당 글은 존재하지 않아요");
		}
		//비밀번호 일치여부 체크해서 일치하면 삭제처리
		String dbPwd=vo.getPasswd();
		if(!dbPwd.equals(passwd)) {
			return util.addMsgBack(m, "비밀번호가 일치하지 않아요");
		}
		//db에서 글 삭제처리
		int n=boardService.deleteBoard(num);
		ServletContext app= req.getServletContext();
		String upDir=app.getRealPath("/resources/board_upload");
		log.info("upDir==="+upDir);
		if(n>0 && vo.getFilename()!=null) {
			File f=new File(upDir,vo.getFilename());
			if(f.exists()) {
				boolean b=f.delete();
				log.info("파일 삭제여부: "+b);
			}
		}
		String str=(n>0)?"글 삭제성공":"삭제 실패";
		String loc=(n>0)?"list":"javascript:history.back()";
		//서버에 업로드한 첨부파일이 있다면 서버에서 삭제처리
		return util.addMsgLoc(m,str,loc);
	}//---------------------------------------------
	
	@PostMapping("/edit")
	public String boardEditForm(Model m, 
			HttpServletRequest req,
			@RequestParam(defaultValue="0")int num,
			@RequestParam(defaultValue="") String passwd){
		if(num==0||passwd.isEmpty()) {
			return "redirect:list";
		}
		BoardVO vo=this.boardService.selectBoardByIdx(num);
		if(vo==null) {
			return util.addMsgBack(m,"해당 글은 없습니다.");
		}
		String dbPwd=vo.getPasswd();
		if(!dbPwd.equals(passwd)) {
			return util.addMsgBack(m,"비밀번호가 일치하지 않아요");
		}
		m.addAttribute("board",vo);
		return "board/boardEdit";
	}//-----------------------------------------
	
	@PostMapping("rewrite")
	public String boardRewrite(Model m,@ModelAttribute BoardVO vo) {
		log.info("vo==="+vo);
		
		
		
		m.addAttribute("num",vo.getNum());//부모글의 글번호
		m.addAttribute("subject",vo.getSubject());//부모글의 제목
		
		return "board/boardRewrite";
	}
	
}/////////////////////////////////////////////////////

















