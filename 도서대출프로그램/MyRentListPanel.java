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

public class MyRentListPanel extends JPanel {
	JPanel pN= new JPanel();
	JTextArea ta = new JTextArea();
	JButton btAll;
	JLabel lb;
	public MyRentListPanel() {
		lb=new JLabel("대여목록",JLabel.CENTER);
		lb.setFont(new Font("Dialog",Font.BOLD,22));//서체 적용
		
		setLayout(new BorderLayout());
		this.add(pN,BorderLayout.NORTH);
		this.add(new JScrollPane(ta),BorderLayout.CENTER);
		pN.setBackground(Color.gray);
		
		btAll =new JButton("Show All Members List");
		pN.add(lb);
		pN.add(btAll);
	}

	

}/////////////////////////////////////////////
