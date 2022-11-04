package com.multicamp;
import javax.swing.*;
import oracle.net.aso.a;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;
//Controller
public class MemoHandler implements ActionListener{
	
	MemoApp app;//view
	MemoDAO dao=new MemoDAO();//Model
	public MemoHandler(MemoApp app) {
		this.app=app;
		
		
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o= e.getSource();
		if(o==app.btAdd) {
			//app.setTitle("Add");
			addMemo();
			app.cleartf();
			listMemo();
		}else if(o==app.btList) {
			listMemo();
		}else if (o==app.btDel) {
			deleteMemo();
			listMemo();
		}else if (o==app.btEdit) {
			editMemo();//수정글을 입력 폼에 보여주기
		}else if (o==app.btEditEnd) {
			editMemoEnd();//수정 처리
			listMemo();
		}else if (o==app.btFind) {
			app.subFrame.setLocation(app.getWidth(),0);
			app.subFrame.setVisible(true);
		}else if (o==app.subFrame.btFindEnd||o==app.subFrame.tfKeyword) {
			//app.setTitle("###");
			findMemo();
		}
		
	}//--------------------------------------------
	public void findMemo() {
		//검색 유형 얻어오기
		int type= app.subFrame.comboType.getSelectedIndex();
		System.out.println(type);
		//검색어 얻어오기
		String keyword=app.subFrame.tfKeyword.getText();
		if(keyword==null||keyword.trim().isBlank()) {
			app.showMessage("검색할 키워드를 입력하세요");
			app.subFrame.tfKeyword.requestFocus();
			return;
		}
		try {
		List<MemoVO> arr= dao.findMemo(type,keyword);
		if(arr==null||arr.size()==0) {
			app.showMessage("검색한 글이 없습니다.");
			return;
		}
		//app.showTextArea(arr);
		app.subFrame.showTable(arr);
		
		
		}catch(SQLException e) {
			e.printStackTrace();
			app.showMessage(e.getMessage());
		}
	}
	
	public void editMemo() {//select문 where절
		String idxStr= app.showInput("수정할 글번호를 입력하세요");
		if(idxStr==null)return;
		//글번호(pk)
		try {
		MemoVO vo = dao.selectMemo(Integer.parseInt(idxStr.trim()));
		if(vo==null) {
			app.showMessage(idxStr+"번 글은 존재하지 않아요.");
			return;
		}
		app.setText(vo);
		}catch(SQLException e) {
			e.printStackTrace();
			app.showMessage(e.getMessage());
		}
	}

	public void editMemoEnd() {//update문
		//1.사용자가 입력한 값 얻어오기(idx,작성자,메모내용)
		String idxStr= app.tfIdx.getText();
		String name= app.tfName.getText();
		String msg= app.tfMsg.getText();
		//2.유효성 체크(글번호, 작성자)
		if(idxStr==null||idxStr.trim().equals("")) {
			app.showMessage("글 번호를 입력하세요");
			return;
		}
		if(name==null||name.trim().equals("")) {
			app.showMessage("작성자를 입력하세요");
			return;
		}
		int idx= Integer.parseInt(idxStr);
		//3.1번에서 얻어온 값들을 memovo에 담아주기
		MemoVO vo= new MemoVO(idx,name,msg,null);
		//4.dao의 수정처리 메서드 호출하기
		try {
			int n=dao.updateMemo(vo);
			//5.실행 결과에 따른 메시지 처리 수정 성공/실패
			String str=(n>0)?"글 수정 성공":"글 수정 실패";
			app.showMessage(str);
		}catch(SQLException e) {
			e.printStackTrace();
			app.showMessage(e.getMessage());
		}
	}



	public void deleteMemo() {
		String idxStr= app.showInput("삭제할 글 번호를 입력하세요");
		if(idxStr==null) return;
		try {
		int n = dao.deleteMemo(Integer.parseInt(idxStr.trim()));
		System.out.println(n);
		String str=(n>0)?"글 삭제 성공":"글 삭제 실패";
		app.showMessage(str);
		}catch(Exception e) {
			e.printStackTrace();
			app.showMessage(e.getMessage());
		}
	}



	public void listMemo() {//select문
		try {
			List<MemoVO> arr = dao.selectMemoAll();
			app.setTitle("글 개수:"+arr.size());
			app.showTextArea(arr);
		} catch (SQLException e) {
			e.printStackTrace();
			app.showMessage(e.getMessage());
		}
	}



	public void addMemo() {//insert문
		//app의 tfName,tfMsg에 입력한 값 받아오기
		String name= app.tfName.getText();
		String msg=app.tfMsg.getText();
		//유효성 체크(null,빈문자열 체크)
		if(name==null||name.trim().equals("")) {
			app.showMessage("작성자를 입력해야해요");
			app.tfName.requestFocus();
		}
		if(msg==null||msg.trim().equals("")) {
			app.showMessage("내용을 입력해야해요");
			app.tfMsg.requestFocus();
		}
		//받아온 값을 MemoVO객체에 담아줌
		MemoVO vo= new MemoVO(0,name,msg,null);
		//dao에 insertMemo(vo)호출하고 그 결과값을 받아옴
		try {
			int n= dao.insertMemo(vo);
		//그 결과값에 따른 메시지 처리
			if(n>0)app.setTitle("등록성공");
			else app.showMessage("글 등록 실패");
		}catch(SQLException e) {
			app.showMessage(e.getMessage());
		}
	}
	
	
}












