package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Registrer extends JFrame implements ActionListener {

	public static final  String REGISTRER = "Registrer";
	
	private JLabel labelEmpty1;
	private JLabel labelEmpty2;
	private JLabel labelEmpty3;
	private JLabel labelEmpty4;
	private JLabel labelEmpty5;
	private JLabel labelEmpty6;
	private JLabel labelEmpty7;
	private JLabel labelEmpty8;
	private JLabel labelEmpty9;
	private JLabel labelEmpty10;
	private JLabel labelEmpty11;
	private JLabel labelEmpty12;
	private JLabel labelEmpty13;
	private JLabel labelEmpty14;
	private JLabel labelEmpty15;
	private JLabel labelEmpty16;
	private JLabel labelEmpty17;
	private JLabel labelEmpty18;
	private JLabel labelEmpty19;
	private JLabel labelEmpty20;
	private JLabel labelEmpty21;
	private JLabel labelEmpty22;
	private JLabel labelEmpty23;
	private JLabel labelEmpty24;
	private JLabel labelEmpty25;
	private JLabel labelEmpty26;

	
	private JLabel lbTitle;
	
	private JPlaceholderTextField txtUser;
	private JPlaceholderTextField txtEmail;
	private JPasswordField txtPass;
	
	private JButton butRegistrer; 
	
	private Main_Agario connection;
	
	public Registrer() {
	
		setTitle("Agar.io");
		setSize(333, 333);
		setLocationRelativeTo(null);
		setResizable(false);
		
		
		lbTitle = new JLabel("Sing up now");
		lbTitle.setFont(new java.awt.Font("Calibri", 1, 18));
		
		txtUser = new JPlaceholderTextField("User");
		txtEmail = new JPlaceholderTextField("Email");
		txtPass = new JPasswordField();
		
		labelEmpty1 = new JLabel(" ");
		labelEmpty2 = new JLabel(" ");
		labelEmpty3 = new JLabel(" ");
		labelEmpty4 = new JLabel(" ");
		labelEmpty5 = new JLabel(" ");
		labelEmpty6 = new JLabel(" ");
		labelEmpty7 = new JLabel(" ");
		labelEmpty8 = new JLabel(" ");
		labelEmpty9 = new JLabel(" ");
		labelEmpty10 = new JLabel(" ");
		labelEmpty11 = new JLabel(" ");
		labelEmpty12 = new JLabel(" ");
		labelEmpty13 = new JLabel(" ");
		labelEmpty14 = new JLabel(" ");
		labelEmpty15 = new JLabel(" ");
		labelEmpty16 = new JLabel(" ");
		labelEmpty17 = new JLabel(" ");
		labelEmpty18 = new JLabel(" ");
		labelEmpty19 = new JLabel(" ");
		labelEmpty20 = new JLabel(" ");
		labelEmpty21 = new JLabel(" ");
		labelEmpty22 = new JLabel(" ");
		labelEmpty23 = new JLabel(" ");
		labelEmpty24 = new JLabel(" ");
		labelEmpty25 = new JLabel(" ");
		labelEmpty26 = new JLabel(" ");
		
		
		butRegistrer= new JButton("Sing me up");
		butRegistrer.setActionCommand(REGISTRER);
		butRegistrer.addActionListener(this);
		
		
		setLayout(new GridLayout(11,3));
		
		add(labelEmpty1);
		add(labelEmpty2);
		add(labelEmpty3);
		add(labelEmpty4);
		add(lbTitle);
		add(labelEmpty5);
		add(labelEmpty6);
		add(labelEmpty7);
		add(labelEmpty8);
		add(labelEmpty9);
		add(txtUser);
		add(labelEmpty10);
		add(labelEmpty11);
		add(labelEmpty12);
		add(labelEmpty13);
		add(labelEmpty14);
		add(txtEmail);
		add(labelEmpty15);
		add(labelEmpty16);
		add(labelEmpty17);
		add(labelEmpty18);
		add(labelEmpty19);
		add(txtPass);
		add(labelEmpty20);
		add(labelEmpty21);
		add(labelEmpty22);
		add(labelEmpty23);
		add(labelEmpty24);
		add(butRegistrer);
		add(labelEmpty25);
		add(labelEmpty26);
		
		
	}

	
	public String getRegistrerUser() {
		return txtUser.getText();
	}
	
	public String getRegistrerEmail() {
		return txtEmail.getText();
	}
	
	public String getRegistrerPass() {
		return txtPass.getText();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comand = e.getActionCommand();
		
		if(comand.equals(REGISTRER)) {
			connection.creatAccount(getRegistrerUser(), getRegistrerPass(), getRegistrerEmail());
		}
		
		
	}
	
	
}