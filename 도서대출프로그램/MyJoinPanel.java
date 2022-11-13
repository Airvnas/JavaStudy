package my.book;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class MyJoinPanel extends JPanel {
	
	JTextField tfName;
	JPasswordField tfPwd;
	JButton btJoin;
	JLabel lb;
	public MyJoinPanel() {
		this.setLayout(null);
		tfName= new JTextField(20);
		tfPwd= new JPasswordField(20);
		
		btJoin = new JButton("Join");
		lb=new JLabel("회원 가입",JLabel.CENTER);
		lb.setFont(new Font("Dialog",Font.BOLD,22));//서체 적용
		
		lb.setBounds(90, 30, 200, 50);
		tfName.setBounds(90, 100, 200, 50);//x y w h
		tfPwd.setBounds (90, 170, 200, 50);//x y w h
		
		btJoin.setBounds(90,240,200,50);
		
		tfName.setBorder(new TitledBorder("Name"));
		tfPwd.setBorder(new TitledBorder("Password"));
		
		this.add(lb);
		this.add(tfName);
		this.add(tfPwd);
		this.add(btJoin);
	}
	public void clearTf() {
		tfName.setText(null);
		tfPwd.setText(null);
		tfName.requestFocus();
	}///////////////////////////////////////////
}
