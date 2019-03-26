package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

	
	public static final String LOGIN = "Login";
	public static final String GO_REGISTRER = "Go registrer";
	
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
	
	
	
	private JLabel lbTitle;
	private JLabel lbTextRegistrer;
	
	
	private JTextField txtEmail;
	private JPasswordField txtPass;
	
	private JButton butLogin;
	private JButton butPanelRegistrar;
	
	
	public Login() {
		
		setTitle("Agar.io");
		setSize(333, 333);
		setLocationRelativeTo(null);
		setResizable(false);
		
		lbTitle = new JLabel("Sing in");
		lbTextRegistrer = new JLabel("Don't have account?");
		
		txtEmail = new JTextField();
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
		

		
		butLogin = new JButton("Login"); 
		butLogin.setActionCommand(LOGIN);
		butLogin.addActionListener(this);
		
		butPanelRegistrar = new JButton("Registrer"); 
		butPanelRegistrar.setActionCommand(GO_REGISTRER);
		butPanelRegistrar.addActionListener(this);
	
	
		setLayout(new BorderLayout());

		JPanel auxLogin = new JPanel();
		auxLogin.setLayout(new GridLayout(9,3));
		auxLogin.add(labelEmpty1);
		auxLogin.add(labelEmpty2);
		auxLogin.add(labelEmpty3);
		auxLogin.add(labelEmpty4);
		auxLogin.add(lbTitle);
		auxLogin.add(labelEmpty5);
		auxLogin.add(labelEmpty6);
		auxLogin.add(labelEmpty7);
		auxLogin.add(labelEmpty8);
		auxLogin.add(labelEmpty9);
		auxLogin.add(txtEmail);
		auxLogin.add(labelEmpty10);
		auxLogin.add(labelEmpty11);
		auxLogin.add(labelEmpty12);
		auxLogin.add(labelEmpty13);
		auxLogin.add(labelEmpty14);
		auxLogin.add(txtPass);
		auxLogin.add(labelEmpty15);
		auxLogin.add(labelEmpty16);
		auxLogin.add(labelEmpty17);
		auxLogin.add(labelEmpty18);
		auxLogin.add(labelEmpty19);
		auxLogin.add(butLogin);
		auxLogin.add(labelEmpty20);
		auxLogin.add(labelEmpty21);
		auxLogin.add(labelEmpty22);
		
		JPanel auxRegistrer = new JPanel();
		auxRegistrer.setLayout(new GridLayout(1,2));
		auxRegistrer.add(lbTextRegistrer);
		auxRegistrer.add(butPanelRegistrar);
		
		add(auxLogin, BorderLayout.CENTER);
		add(auxRegistrer, BorderLayout.SOUTH);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comand = e.getActionCommand();
		
		if(comand.equals(LOGIN)) {
			
		}else if(comand.equals(GO_REGISTRER)) {
			
		}
		
		
	}
	
	
}
