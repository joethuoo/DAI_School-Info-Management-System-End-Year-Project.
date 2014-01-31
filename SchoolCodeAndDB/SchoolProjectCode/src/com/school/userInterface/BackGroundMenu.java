package com.school.userInterface;


import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JMenu;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import com.school.db.DbConnector;
import javax.swing.UIManager;


public class BackGroundMenu implements ActionListener{
	 JPanel maincenter,mainPanel;
	 JMenuBar menuBar;
	 JMenu  manageusersmenu,editmenu,filemenu, editpricemenu ,reports;
	 JMenuItem logoff;
	 JButton login;
	 JFrame mainFrame ;
	
	 private JLabel username,passwordLabel;
	 private JTextField userNameJtext;
	 private JPasswordField passwordJtxtfield;
	 public static boolean login_Status =false;
	 private DbConnector n = new DbConnector();
	 private JTextField infoJtext;
	 private JLabel datelLabel;
	 private JTextField dateJtext;
	 
	public BackGroundMenu() {
		 n.configureYear();
		 mainBackGroung();
	}

	protected ImageIcon createImageIcon(String path,String description) {
			java.net.URL imgURL = getClass().getResource(path);
					if (imgURL != null) {
						return new ImageIcon(imgURL, description);
					} else {
							System.err.println("Couldn't find file: " + path);
							return null;
						}
			}
	
    /*public static void main(String args[]) {
        new BackGroundMenu();
       
    }*/
    
	public void mainBackGroung(){
		 mainFrame = new JFrame(" KAHAWA RISING STAR");
		 mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 mainFrame.setResizable(false);
		 
		 menuBar = new JMenuBar();
		 menuBar.setPreferredSize(new Dimension(0, 40));
		 menuBar.setBackground(new Color(255, 255, 255));
		 mainFrame.getContentPane().add(menuBar, BorderLayout.NORTH);
		 
		 filemenu = new JMenu("File");
		
		 filemenu.setFont(new Font("Dialog", Font.BOLD, 16));
		 filemenu.setEnabled(false);
		 menuBar.add(filemenu);
		 
		 editmenu = new JMenu("Edit");
		 editmenu.setEnabled(false);
		 editmenu.setFont(new Font("Dialog", Font.BOLD, 16));
		 menuBar.add(editmenu);
		 
		 manageusersmenu = new JMenu("Student Profile");
		 manageusersmenu.setEnabled(false);
		 manageusersmenu.setFont(new Font("Dialog", Font.BOLD, 16));
		 menuBar.add(manageusersmenu);
		 
		 editpricemenu = new JMenu("Fees Structure");
		 editpricemenu.setEnabled(false);
		 editpricemenu.setFont(new Font("Dialog", Font.BOLD, 16));
		 menuBar.add(editpricemenu);
		
		
		 reports = new JMenu("Reports");
		 reports.setEnabled(false);
		 reports.setFont(new Font("Dialog", Font.BOLD, 16));
		 menuBar.add(reports);
		 
		 logoff = new JMenuItem(("Logout"));
		 logoff.addActionListener(this);
		 logoff.setEnabled(false);
		 logoff.setFont(new Font("Dialog", Font.BOLD, 16));
		 
		 menuBar.add(logoff);
		
		 
		 
		/* JMenu farmermenu = new JMenu("Farmer");
		 farmermenu.setFont(new Font("Dialog", Font.BOLD, 16));
		 farmermenu.setActionCommand("Farmer");
		 manageusersmenu.add(farmermenu);
		 
		 JMenuItem registerFarmersjmenuitem = new JMenuItem("Register Farmer");
		 registerFarmersjmenuitem.setFont(new Font("Dialog", Font.BOLD, 16));
		 farmermenu.add(registerFarmersjmenuitem);
		 
		 JMenuItem editfarmerplofile = new JMenuItem("Edit Farmer Profile");
		 editfarmerplofile.setFont(new Font("Dialog", Font.BOLD, 16));
		 farmermenu.add(editfarmerplofile);
		 
		 registerFarmersjmenuitem.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		//new FarmerRegister();
		 	}
		 });*/
		 
		
		 maincenter = new JPanel();
		 maincenter.setBorder(new LineBorder(Color.GREEN, 3, true));
		 maincenter.setPreferredSize(new Dimension(15, 10));
		 maincenter.setBackground(new Color(245, 255, 250));
		 mainFrame.getContentPane().add(maincenter, BorderLayout.CENTER);
		 maincenter.setLayout(null);
		 
		 JPanel companylogoPanel = new JPanel();
		 companylogoPanel.setForeground(new Color(50, 205, 50));
		 companylogoPanel.setBorder(new LineBorder(new Color(50, 205, 50), 4, true));
		 companylogoPanel.setBackground(new Color(255, 255, 255));
		 companylogoPanel.setBounds(700, 10, 500, 50);
		 maincenter.add(companylogoPanel);
		 companylogoPanel.setLayout(null);
		 
		 JLabel lblNewLabel = new JLabel("KAHAWA RISING STAR ACADEMY");
		 lblNewLabel.setForeground(new Color(0, 100, 0));
		 lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		 lblNewLabel.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 24));
		 lblNewLabel.setBounds(10, 15, 480, 20);
		 companylogoPanel.add(lblNewLabel);
		 
		 datelLabel = new JLabel("Date :");
		 datelLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		 datelLabel.setBounds(30, 20, 60, 30);
		 maincenter.add(datelLabel);
		 
		 Date dNow = new Date( );
	     SimpleDateFormat ft = new SimpleDateFormat ("E dd MMMM yyyy ");
		 String date=ft.format(dNow);
		 
		 dateJtext = new JTextField();
		 dateJtext.setEditable(false);
		 dateJtext.setBackground(new Color(245, 255, 250));
		 dateJtext.setForeground(new Color(0, 0, 205));
		 dateJtext.setText(date);
		 dateJtext.setBorder(null);

		 dateJtext.setFont(new Font("Bitstream Vera Sans", Font.BOLD, 17));
		 dateJtext.setBounds(90, 23, 200, 25);
		 maincenter.add(dateJtext);
		 dateJtext.setColumns(10);
		 
		
		
		 
		 JPanel decoPanel = new JPanel();
		 decoPanel.setBackground(new Color(255, 255, 255));
		 decoPanel.setBorder(new LineBorder(new Color(138, 43, 226), 2, true));
		 decoPanel.setForeground(new Color(255, 255, 255));
		 decoPanel.setBounds(700, 97, 500, 460);
		 maincenter.add(decoPanel);
		 decoPanel.setLayout(null);
		 
		
		 
		 JLabel imagelabel = new JLabel("School Front Look Photo to be inserted here ");
		 imagelabel.setBackground(UIManager.getColor("Button.background"));
		 imagelabel.setBounds(40, 50, 350, 120);
		 lblNewLabel.setBorder(null);
		 decoPanel.add(imagelabel);
		
		 mainPanel = new JPanel();
		 mainPanel.setBackground(new Color(245, 255, 250));
		 mainPanel.setBorder(null);
		 mainPanel.setBounds(50, 97, 550, 460);
		 
		 mainPanel.add(new LoginPanel());
		 
		 maincenter.add(mainPanel);
		 mainPanel.setLayout(null);
		 
		
		 
		 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		 mainFrame.setSize(screenSize.width, screenSize.height);
		 mainFrame.setVisible(true);
	 }
		
	
		public void setFalse(){
			manageusersmenu.setVisible(false);
			editmenu.setEnabled(false);
			filemenu.setEnabled(false);
			editpricemenu.setEnabled(false);
			reports.setEnabled(false);
			logoff.setEnabled(false);
			//loginpanel.setEnabled(false);
		
			
		}

		public void setTrue(){
			manageusersmenu.setEnabled(true);
			editmenu.setEnabled(true);
			filemenu.setEnabled(true);
			editpricemenu.setEnabled(true);
			reports.setEnabled(true);
			logoff.setEnabled(true);
			//loginpanel.setVisible(false);
		
			 //loginpanel.setVisible(false);
			
		}
		
		private void login(){
			final String usname = userNameJtext.getText().trim();
			@SuppressWarnings("deprecation")
			final String pwd = passwordJtxtfield.getText().trim();
				
				if ((usname!= null && usname.length() > 0)&& (pwd!= null && pwd.length() > 0))
				{
						if(n.login(usname, pwd)==true){
							setTrue();
							mainPanel.removeAll();
							mainPanel.add(new MainInterface());
							mainPanel.repaint();
							mainPanel.revalidate();
						
						
						}
						else{
							infoJtext.setText("Your Username or Password is Incorrect");
							userNameJtext.setText("");
							passwordJtxtfield.setText("");
						    }
					
					
				}else{infoJtext.setText( "Enter Valid Values");}
				
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==login){
				login();
			
			}else  if(e.getSource()==logoff){
				System.out.println("this is logoff ");
			setFalse();
			mainPanel.removeAll();
			mainPanel.add(new LoginPanel());
			mainPanel.repaint();
			mainPanel.revalidate();
			
			}
	}
		
		@SuppressWarnings("serial")
		private class LoginPanel extends JPanel{
		
			public LoginPanel(){
				super();
				 LoginPanel loginpanel = this;
				 loginpanel.setBounds(70, 80, 400, 290);
		
				 loginpanel.setBorder(new LineBorder(new Color(0, 255, 0), 2, true));
				 loginpanel.setBackground(new Color(245, 255, 250));
				 loginpanel.setLayout(null);
				 
				 JLabel lablel = new JLabel("Login Here");
				 lablel.setForeground(Color.BLUE);
				 lablel.setFont(new Font("Bitstream Vera Serif", Font.BOLD, 23));
				 lablel.setBounds(200, 10, 180, 40);
				 loginpanel.add(lablel);
				 
				 username = new JLabel("New label");
				 username.setFont(new Font("Dialog", Font.BOLD, 16));
				 username.setText("User Name : ");
				 username.setBounds(60, 80, 120, 25);
				 loginpanel.add(username);
				 
				 userNameJtext = new JTextField();
				 userNameJtext.setCaretColor(new Color(0, 0, 0));
				 userNameJtext.setBorder(new LineBorder(new Color(0, 100, 0), 1, true));
				 userNameJtext.setForeground(Color.BLUE);
				 userNameJtext.setFont(new Font("Dialog", Font.BOLD, 16));
				 userNameJtext.setBounds(180, 80, 200, 25);
				 loginpanel.add(userNameJtext);
				 userNameJtext.setColumns(10);
				 
				 passwordLabel = new JLabel("Password :");
				 passwordLabel.setFont(new Font("Dialog", Font.BOLD, 16));
				 passwordLabel.setBounds(60, 180, 100, 25);
				 loginpanel.add(passwordLabel);
				 
				 passwordJtxtfield = new JPasswordField();
				 passwordJtxtfield.setFont(new Font("Dialog", Font.PLAIN, 17));
				 passwordJtxtfield.setForeground(Color.RED);
				 passwordJtxtfield.setBounds(180, 180, 200, 25);
				 loginpanel.add(passwordJtxtfield);
				 passwordJtxtfield.setColumns(10);
				 
				 login = new JButton("Login");
				 login.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						login();
						
					}
				});
				 
				 infoJtext = new JTextField();
				 infoJtext.setDisabledTextColor(new Color(255, 0, 0));
				 infoJtext.setEnabled(false);
				 infoJtext.setBackground(new Color(245, 255, 250));
				 infoJtext.setFont(new Font("Dialog", Font.BOLD,16));
				 infoJtext.setBorder(null);
				 infoJtext.setBounds(60, 220, 320, 25);
				 loginpanel.add(infoJtext);
				 infoJtext.setColumns(10);
				 
				 login.setBounds(280, 250, 100, 25);
				 loginpanel.add(login);
			}
		}
}


