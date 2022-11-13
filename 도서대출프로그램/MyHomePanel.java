package my.book;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
	
public class MyHomePanel extends JPanel {
	JPanel pN= new JPanel();
	JTextArea ta = new JTextArea();
	JButton btLogout;
	public MyHomePanel() {
		setLayout(new BorderLayout());
		this.add(pN,BorderLayout.NORTH);
		this.add(new JScrollPane(ta),BorderLayout.CENTER);
		btLogout= new JButton("Logout");
		
		pN.add(btLogout);
	}
	
}
