package my.book;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MyReturnPanel extends JPanel {
	JPanel pN= new JPanel();
	JTextArea ta = new JTextArea();
	JTextField bookName ;
	JButton btReturn;
	JLabel lb;
	public MyReturnPanel() {
		setLayout(new BorderLayout());
		lb=new JLabel("반납",JLabel.CENTER);
		lb.setFont(new Font("Dialog",Font.BOLD,22));//서체 적용
		this.add(pN,BorderLayout.NORTH);
		this.add(new JScrollPane(ta),BorderLayout.CENTER);
		pN.setBackground(Color.GRAY);
		
		bookName=new JTextField(20);
		btReturn =new JButton("Return");
		pN.add(lb);
		pN.add(bookName);
		pN.add(btReturn);
	}
}/////////////////////////////////////////////
