package com.memo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.memo.model.MemoDAO;
import com.memo.model.MemoVO;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class MemoController {
	//by type으로 주입한다. MemoDAO타입의 객체가 있으면 주입한다.
	@Autowired
	private MemoDAO memoDao;
	
	@RequestMapping(value="/memo",method=RequestMethod.GET)
	public String memoForm() {
		return "memo/input";
	}
	//html input name과 VO객체의 property명이 같고
	//@ModelAttribute 어노테이션을 붙여주면 파라미터 값을 자동으로 VO객체에 넣어준다.
	
	@RequestMapping(value="/memo",method=RequestMethod.POST)
	public String memoInsert(Model model,@ModelAttribute("memo") MemoVO memo) {
		//System.out.println("memo====="+memo);
		log.info("memo>>>"+memo);
		
		int n=memoDao.insertMemo(memo);
		String str=(n>0)?"등록성공":"등록실패";
		String loc=(n>0)?"memoList":"javascript:history.back()";
		
		model.addAttribute("message",str);
		model.addAttribute("loc",loc);
		return "msg";
		//"/WEB-INF/views/msg.jsp"
	}
	@RequestMapping("/memoList")
	public String memoList(Model model) {
		int totalCount=memoDao.getTotalCount();
		List<MemoVO>arr=memoDao.listMemo();
		
		model.addAttribute("memoArr",arr);
		model.addAttribute("totalCount",totalCount);
		return "memo/list";
		 //"/WEB-INF/views/memo/list.jsp"
	}
	
	@RequestMapping("/memoDel")
	public String memoDelete(Model model,@RequestParam(defaultValue = "0") int idx) {
		log.info("idx==="+idx);//idx파라미터 값이 넘어오지않으면 디폴트값 0으로 준다.
		if(idx==0) {
			return "redirect:memoList";//redirect방식으로 이동
		}
		int n=memoDao.deleteMemo(idx);
		String str=(n>0)?"삭제성공":"삭제실패";
		String loc=(n>0)?"memoList":"javascript:history.back()";
		model.addAttribute("message",str);
		model.addAttribute("loc",loc);
		return "msg";
	}
	@RequestMapping(value="/memoEdit",method=RequestMethod.GET)
	public String memoEditForm(Model model,@RequestParam(defaultValue = "0") int idx) {
		log.info("EditIdx==="+idx);
		if(idx==0) {
			return "redirect:memoList";
		}
		MemoVO memo=memoDao.getMemo(idx); 
		//log.info("Edit==="+memo.toString());
		model.addAttribute("memo",memo);
		return "memo/edit";
		//"/WEB-INF/views/memo/edit.jsp"
	}
	
	@RequestMapping(value="/memoEdit",method=RequestMethod.POST)
	public String memoEditEnd(Model model,@ModelAttribute("memo") MemoVO memo) {
		if(memo==null) {
			return "redirect:memoList";
		}
		int n=memoDao.updateMemo(memo);
		
		String str=(n>0)?"수정성공":"수정실패";
		String loc=(n>0)?"memoList":"javascript:history.back()";
		model.addAttribute("message",str);
		model.addAttribute("loc",loc);
		return "msg";
		//return "memo/edit";
	}
	
	
	
	
	
	
	
}
