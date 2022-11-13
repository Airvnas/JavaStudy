package my.book;

import javax.swing.*;

import my.app2.Member;

import java.awt.*;
import java.awt.event.*;
import java.util.Collection;
import java.util.HashMap;
/**
 * 작성자: 김진엽
 * 작성일: 2022-10-21
 * 버 전: v1.1
 * 회원가입과 로그인 기능을 통해 책 목록에 있는 책을 대여, 반납할 수 있는 도서 대여 프로그램.
 * 여러명의 회원이 동시에 로그인할 수 없으며, 로그인 하지 않고 대여 반납의 작업 수행시, 회원이 대여하지
 * 않은 책에 대해 반납을 하려하면 경고문을 띄우고, 책 목록에 있지 않은 책을 대여 반납하려하면 예외를 발생
 * 시킵니다. 대여목록은 로그인의 여부와 상관없이 모든 회원의 대여목록 확인이 가능합니다.
 * */
public class MyBookApp extends JFrame {
	Container cp;
	JPanel p 	 = new JPanel();
	JPanel northP= new JPanel();
	MyHomePanel 	p1 	 = new MyHomePanel(); //home
	MyJoinPanel 	p2 	 = new MyJoinPanel();
	MyLoginPanel 	p3 	 = new MyLoginPanel();
	MyRentPanel		p4 	 = new MyRentPanel();
	MyReturnPanel 	p5 	 = new MyReturnPanel();
	MyRentListPanel p6 	 = new MyRentListPanel();
	
	CardLayout card;
	JButton bt[] = new JButton[6];
	String label[]= {"Home","Join","Login","Rent","Return","RentList"};
	MyHandler handler = new MyHandler();
	HashMap<String,my.book.Member> usersMap=new HashMap<>();
	Book[] book = {new Book("하얼빈"),new Book("역행자"),new Book("불편한편의점"),
			new Book("불편한편의점2"),new Book("파친코2"),new Book("원씽"),
			new Book("아버지의해방일지"),new Book("잘될수밖에없는너에게"),new Book("심리학이분노에답하다"),
			new Book("나는나를바꾸기로했다"),new Book("도쿄에일리언즈4"),new Book("데뷔못하면죽는병걸림"),
			new Book("나는오래된거리처럼너를사랑하고"),new Book("세상에서가장쉬운본질육아")};
	my.book.Member joinedPeople=new my.book.Member("","");
	String bookList=":: 도서목록 :: \n";
	boolean isLogin2=false;
	public MyBookApp() {
		super("MyBookApp");
		cp = this.getContentPane();
		cp.add(northP,BorderLayout.NORTH);
		cp.add(p,BorderLayout.CENTER);
		p.setLayout(card = new CardLayout());
		// 중앙 패널을 카드레이아웃
		northP.setLayout(new GridLayout(1, 0));
		p.setBackground(Color.green);
		for (int i = 0; i < bt.length; i++) {
			bt[i] = new JButton(label[i]);
			northP.add(bt[i]);
			bt[i].setBackground(Color.white);
			bt[i].addActionListener(handler);
		}
		
		p.add(p1, "Home");
		p.add(p2, "Join");
		p.add(p3, "Login");
		p.add(p4, "Rent");
		p.add(p5, "Return");
		p.add(p6, "RentList");
		
		for(int i=0;i<book.length;i++) {
			bookList+=book[i].getBook()+"\n";
		}
		p1.ta.setText(bookList);
		p1.btLogout.addActionListener(handler);
		p2.btJoin.addActionListener(handler);
		p3.bnLogin.addActionListener(handler);
		p4.btRent.addActionListener(handler);
		p5.btReturn.addActionListener(handler);
		p6.btAll.addActionListener(handler);
		card.show(p, "home");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}//--------------------------------------
	/**버튼을 누르는 위치의 정보를 얻어와 이벤트에 대한 처리를 하는 클래스 
	 * */
	class MyHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			if(o==bt[0]) {//Home
				card.show(p, "Home");
			}else if(o==bt[1]) {//Join
				card.show(p, "Join");
			}else if(o==bt[2]) {//Login
				card.show(p,"Login");
			}else if(o==bt[3]) {//Rent
				card.show(p,"Rent");
			}else if(o==bt[4]) {//Return
				card.show(p,"Return");
			}else if(o==bt[5]) {//RentList
				card.show(p,"RentList");
			}
			
			if(o==p1.btLogout) {
				Logout();
			}else if(o==p2.btJoin) {
				joinMember();
			}else if(o==p3.bnLogin) {
				loginMember();
			}else if(o==p4.btRent) {
				try {
					p4.ta.setText(null);
					rentBook();
				} catch (NoExistBookException e1) {
					e1.printStackTrace();
				}
			}else if(o==p5.btReturn) {
				try {
					returnBook();
				} catch (NoExistBookException e1) {
					e1.printStackTrace();
				}
			}else if(o==p6.btAll) {
				showList();
			}
			
		}//--------------------------------------
	}
		/**(로그인)
		 * 로그인 패널에서 입력한 아이디,비밀번호와 HashMap에 저장된 회원정보를 비교하여 일치한다면
		 * 로그인 정보를 변경하고 아니라면 알림문을 띄움, 입력하지않거나 공백이 있을경우도 알림문을 띄움*/
		public void loginMember() {
			String name = p3.tfName.getText();
			char[] ch = p3.tfPwd.getPassword();
			String pwd = new String(ch);
			if(name==null||ch==null|| name.trim().equals("")
					||ch.length<=0) {
				showMsg("이름,비밀번호는 반드시 입력해야해요");
				p3.tfName.requestFocus();
				return;
			}
			if(usersMap.containsKey(name)&& isLogin2==false) {
				joinedPeople = usersMap.get(name);
				String realPwd=joinedPeople.getPwd();
				if(realPwd.equals(pwd)) {
					showMsg("로그인 되었습니다,");
					joinedPeople.setLogin(true);
					isLogin2=true;
					card.show(p, "Home");
				}else showMsg("비밀번호가 틀렸습니다.");
			}else showMsg("없는 아이디이거나 이미 로그인 되었습니다.");
		}//--------------------------------------
		/**(로그아웃)
		 * 로그인 상태일 경우 로그아웃*/
		public void Logout() {
			if(joinedPeople.isLogin()&&isLogin2) {
				joinedPeople.setLogin(false);
				isLogin2=false;
				showMsg(joinedPeople.getName()+"님 로그아웃 되었습니다.");
			}
		}
		/** (회원가입)
		 * 아이디와 비밀번호를 Member객체에 저장하고, HashMap에 아이디와 Member객체를 key, value값으로 저장 */
		public void joinMember() {
			String name = p2.tfName.getText();
			char[] ch = p2.tfPwd.getPassword();
			String pwd = new String(ch);
			if(name==null||ch==null|| name.trim().equals("")
					||ch.length<=0) {
				showMsg("이름,비밀번호는 반드시 입력해야해요");
				p2.tfName.requestFocus();
				return;
			}
			my.book.Member mem = new my.book.Member(name,pwd);
			usersMap.put(name, mem);
			showMsg("회원가입 처리 되었습니다. 로그인하세요");
			card.show(p, "Login");
		}//--------------------------------------
		
		 // @throws NoExistBookException 
		String a="";
		/** (대여)
		 * MyRent패널에서 입력받은 책의 이름이 홈 목록에 있는 책인지 확인하고, 로그인 여부를 확인하여 대여해줌
		 * 책의 상태를 이미 대여된 상태로 바꿈*/
		public void rentBook() throws NoExistBookException {
			String bookN=p4.bookName.getText();
			p4.bookName.setText(null);
			boolean isExs=false; //책이 목록에 존재하는가?
			if(joinedPeople.isLogin()&&isLogin2) {
				for(int i=0; i<book.length;i++) {
					if(book[i].getBook().equals(bookN)) {
						isExs=true;
						if(book[i].isRent()) {//inRent? yes, already
							showMsg(book[i].getBook()+"는(은) 이미 대여된 책입니다.");
						}else {
							showMsg(book[i].getBook()+"을(를) 대여했습니다.");
							joinedPeople.setList(bookN);
							book[i].setRent(true);//대여됨
							p4.ta.setText(null);
							a+=bookN+"\n";
							p4.ta.setText(a);
						}
					}
				}if(!isExs) {
						showMsg(bookN+"책이 존재하지 않아요");
						throw new NoExistBookException(bookN+"책이 존재하지 않아요");
						}
			}else showMsg("로그인하세요");
			
		}//--------------------------------------
		/** (반납)
		 * 로그인 여부를 확인하고, 해당 멤버가 반납하려는 책을 빌렸는지 확인한 후,이미 빌렸다면 책의 상태를 
		 * 빌릴수 있도록 바꾸고 멤버의 대여목록에서 책을 삭제함*/
		 // @throws NoExistBookException 
		public void returnBook() throws NoExistBookException {
			String bookN=p5.bookName.getText();
			p5.bookName.setText(null);
			boolean isExs=false; //책이 목록에 존재하는가?
			if(joinedPeople.isLogin()&&isLogin2) {
				for(int i=0; i<book.length;i++) {
					if(book[i].getBook().equals(bookN)) {
						isExs=true;
						if(joinedPeople.isList(bookN)) {
							showMsg(book[i].getBook()+"는(은) 반납되었습니다.");
							joinedPeople.removeList(bookN);
							book[i].setRent(false);
						}else {
							showMsg(joinedPeople.getName()+"님은"+book[i].getBook()+"을(를) 대여하지 않았습니다.");
						}
					}
				}if(!isExs) 
					throw new NoExistBookException(bookN+"책이 존재하지 않아요");
			}else showMsg("로그인하세요");
		}
		
		/**(대여 목록)
		 * 모든 회원의 정보를 가져와 회원들의 대여한 도서를 출력하는 메서드*/
		public void showList() {
			int cnt=0;
			Collection<my.book.Member>col = usersMap.values();
			if(col==null||col.size()==0) {
				p6.ta.setText("::대여한 도서가 없습니다.::");
				return;
			}
			String info = "Name     BookList\n";
			info+="=====================================\n";
			for(my.book.Member user:col) {
				info=info+user.getName()+": ";
				for(String s:user.getList()) {
					info+=s;
				}info+="\n";
			}
			showMsg(info);
		}
		/**메시지를 띄워주는 메서드
		 * */
		public void showMsg(String msg) {
			JOptionPane.showMessageDialog(p3, msg,"alert",
					JOptionPane.WARNING_MESSAGE);
		}//----------------------------------
	
	
	public static void main(String[] args) {
		MyBookApp my = new MyBookApp();
		my.setBounds(900, 100, 500, 500);
		//x   y   w   h
		my.setVisible(true);
	}//---------------------------------------

}/////////////////////////////////////