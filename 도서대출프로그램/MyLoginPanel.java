package my.book;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class MyLoginPanel extends JPanel {
	JTextField tfName;
	JPasswordField tfPwd;
	JButton bnLogin;
	JLabel lb;
	//x,y좌표 지정해서 붙이려면 기본레이아웃을 해제해야 한다.
	//대신 컴포넌트의 사이즈와 x,y좌표를 수동으로 지정해야 한다
	public MyLoginPanel() {
		this.setLayout(null);
		lb=new JLabel("로그인",JLabel.CENTER);
		lb.setFont(new Font("Dialog",Font.BOLD,22));//서체 적용
		
		lb.setBounds(90, 30, 200, 50);
		tfName=new JTextField(20);
		tfPwd=new JPasswordField(20);
		bnLogin=new JButton("Login");
		tfName.setSize(200,50);//w,h
		tfName.setLocation(90, 100);//x,y
		tfPwd.setBounds(90,170, 200,50);
					//x,y w, h
		bnLogin.setBounds(90,240,200,50);
		
		tfName.setBorder(new TitledBorder("Name"));
		tfPwd.setBorder(new TitledBorder("Password"));
		this.add(lb);
		this.add(tfName);
		this.add(tfPwd);
		this.add(bnLogin);
	}
}