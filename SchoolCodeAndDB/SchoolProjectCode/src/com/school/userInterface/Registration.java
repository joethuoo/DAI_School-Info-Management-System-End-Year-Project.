package com.school.userInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import com.school.db.Register;

import java.awt.Font;

public class Registration implements ActionListener{
	JFrame f;
	private JTextField guardianFnameJtextfield;
	private JTextField guardianMnameJetextField;
	private JTextField guardianSurnameJtextField;
	private JTextField idJtextfield;

	private JTextField phoneJtextField;
	private JTextField residenceJtextfield;
	private JTextField studentFnameJtextfield;
	private JTextField studentMnameJetextField;
	private JTextField studentSurnameJtextField;
	private JTextField infomationJtext;
	private JButton backbutton,saveButton;
	@SuppressWarnings("rawtypes")
	private JComboBox studentGederjcombo ,classJcombobox,gurdianGederCombobox;
	private String [] sex={"Male", "Female"};

	
	
	 public static void main(String[] args) {
		new Registration();
		 }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	Registration (){
		f = new JFrame();
		f.setBackground(new Color(245, 255, 250));
		f.getContentPane().setBackground(new Color(245, 255, 250));
		f.setResizable(false);
		f.getContentPane().setLayout(null);
		
		JLabel studentJlabel = new JLabel("Student First Name");
		studentJlabel.setBounds(30, 30, 140, 25);
		f.getContentPane().add(studentJlabel);
		
	    studentFnameJtextfield = new JTextField();
	    studentFnameJtextfield.setForeground(new Color(0, 0, 205));
	    studentFnameJtextfield.setFont(new Font("Dialog", Font.BOLD, 16));
	    
	  //validation
		((AbstractDocument)studentFnameJtextfield.getDocument()).setDocumentFilter(
	             new StringValidator());
		
		studentFnameJtextfield.setDocument(new JTextFieldLimit(15));
		studentFnameJtextfield.setBounds(200, 30, 150, 25);
		f.getContentPane().add(studentFnameJtextfield);
		studentFnameJtextfield.setColumns(10);
		
		JLabel studentMiddlenameLabel = new JLabel("Student Middle Name");
		studentMiddlenameLabel.setBounds(30, 80, 160, 25);
		f.getContentPane().add(studentMiddlenameLabel);
		
		studentMnameJetextField = new JTextField();
		studentMnameJetextField.setForeground(new Color(0, 0, 205));
		studentMnameJetextField.setFont(new Font("Dialog", Font.BOLD, 16));
		
		
		  //validation
			((AbstractDocument)studentMnameJetextField.getDocument()).setDocumentFilter(
		             new StringValidator());
		studentMnameJetextField.setForeground(new Color(0, 0, 205));
		studentMnameJetextField.setBounds(200, 80, 150, 25);
		f.getContentPane().add(studentMnameJetextField);
		studentMnameJetextField.setColumns(10);
		
		JLabel studentSurnameLabel = new JLabel("Students Surname");
		studentSurnameLabel.setBounds(30, 130, 140, 25);
		f.getContentPane().add(studentSurnameLabel);
		
		studentSurnameJtextField = new JTextField();
		studentSurnameJtextField.setForeground(new Color(0, 0, 205));
		studentSurnameJtextField.setFont(new Font("Dialog",  Font.BOLD, 16));
		
		  //validation
		((AbstractDocument)studentSurnameJtextField.getDocument()).setDocumentFilter(
	             new StringValidator());
		
		studentSurnameJtextField.setBounds(200, 130, 150, 25);
		f.getContentPane().add(studentSurnameJtextField);
		studentSurnameJtextField.setColumns(10);
		
		JLabel classSubscriptionLabel = new JLabel("Class Subscription");
		classSubscriptionLabel.setBounds(390, 30, 140, 25);
		f.getContentPane().add(classSubscriptionLabel);
		
		JLabel studentGenderlabel = new JLabel("Student Gender");
		studentGenderlabel.setBounds(390, 100, 120, 25);
		f.getContentPane().add(studentGenderlabel);
		
		classJcombobox = new JComboBox();
		classJcombobox.setModel(new DefaultComboBoxModel(new String[] {"BABY CLASS", "PRE UNIT", "NURSERY","STD ONE",
				"STD TWO","STD THREE","STD FOUR","STD FIVE","STD SIX","STD SEVEN","STD EIGHT"}));
		classJcombobox.setBounds(540, 30, 130, 25);
		f.getContentPane().add(classJcombobox);
		
		JRadioButton femaleRadioGroup = new JRadioButton("Female");
	
		femaleRadioGroup.setBounds(420, 130, 90, 25);		
		
		studentGederjcombo = new JComboBox();
		studentGederjcombo.setModel(new DefaultComboBoxModel(sex));
		studentGederjcombo.setBounds(540, 100, 130, 25);
		f.getContentPane().add(studentGederjcombo);
		
		
		
		JLabel guardianJlabel = new JLabel("Guardian First Name");
		guardianJlabel.setBounds(30, 220, 150, 25);
		f.getContentPane().add(guardianJlabel);
		
		guardianFnameJtextfield = new JTextField();
		guardianFnameJtextfield.setForeground(new Color(0, 0, 205));
		guardianFnameJtextfield.setFont(new Font("Dialog", Font.BOLD, 16));
		  //validation
		((AbstractDocument)guardianFnameJtextfield.getDocument()).setDocumentFilter(
	             new StringValidator());
		
		guardianFnameJtextfield.setBounds(200, 220, 150, 25);
		f.getContentPane().add(guardianFnameJtextfield);
		guardianFnameJtextfield.setColumns(10);
		
		JLabel guardianMiddlenameLabel = new JLabel("Guardian Middle Name");
		guardianMiddlenameLabel.setBounds(30, 270, 160, 25);
		f.getContentPane().add(guardianMiddlenameLabel);
		
		guardianMnameJetextField = new JTextField();
		guardianMnameJetextField.setFont(new Font("Dialog",  Font.BOLD, 16));
		guardianMnameJetextField.setForeground(new Color(0, 0, 205));
		  //validation
				((AbstractDocument)guardianMnameJetextField.getDocument()).setDocumentFilter(
			             new StringValidator());
				
		guardianMnameJetextField.setBounds(200, 270, 150, 25);
		f.getContentPane().add(guardianMnameJetextField);
		guardianMnameJetextField.setColumns(10);
		
		JLabel guardianSurnameLabel = new JLabel("Guardian Surname");
		guardianSurnameLabel.setBounds(30, 320, 140, 25);
		f.getContentPane().add(guardianSurnameLabel);
		
		guardianSurnameJtextField = new JTextField();
		guardianSurnameJtextField.setFont(new Font("Dialog", Font.BOLD, 16));
		guardianSurnameJtextField.setForeground(new Color(0, 0, 205));
		
		  //validation
				((AbstractDocument)guardianSurnameJtextField.getDocument()).setDocumentFilter(
			             new StringValidator());
		
		guardianSurnameJtextField.setBounds(200, 320, 150, 25);
		f.getContentPane().add(guardianSurnameJtextField);
		guardianSurnameJtextField.setColumns(10);
		
		JLabel idLabel = new JLabel("ID No");
		idLabel.setBounds(30, 380, 60, 25);
		f.getContentPane().add(idLabel);
		
		idJtextfield = new JTextField();
		idJtextfield.setForeground(new Color(0, 0, 205));
		idJtextfield.setFont(new Font("Dialog", Font.BOLD, 16));
		
		  //validation
				((AbstractDocument)idJtextfield.getDocument()).setDocumentFilter(
			             new IntegerValidator());
		
		idJtextfield.setBounds(200, 380, 150, 25);
		f.getContentPane().add(idJtextfield);
		idJtextfield.setColumns(10);
		
		JLabel gurdianGeder = new JLabel("GuardianGeder");
		gurdianGeder.setBounds(390, 220, 130, 25);
		f.getContentPane().add(gurdianGeder);
		
		gurdianGederCombobox = new JComboBox();
		gurdianGederCombobox.setModel(new DefaultComboBoxModel(sex));
		
	
		gurdianGederCombobox.setBounds(540, 220, 130, 25);
		f.getContentPane().add(gurdianGederCombobox);
		
		JLabel gurdianPhnonelabel = new JLabel("Phone");
		gurdianPhnonelabel.setBounds(390, 270, 70, 25);
		f.getContentPane().add(gurdianPhnonelabel);
		
		phoneJtextField = new JTextField();
		phoneJtextField.setForeground(new Color(0, 0, 205));
		phoneJtextField.setFont(new Font("Dialog",  Font.BOLD, 16));
		
		  //validation
		((AbstractDocument)phoneJtextField.getDocument()).setDocumentFilter(
	             new IntegerValidator());
		
		phoneJtextField.setBounds(540, 270, 130, 25);
		f.getContentPane().add(phoneJtextField);
		phoneJtextField.setColumns(10);
		
		JLabel residenceLabel = new JLabel("Residence");
		residenceLabel.setBounds(390, 320, 90, 25);
		f.getContentPane().add(residenceLabel);
		
		residenceJtextfield = new JTextField();
		residenceJtextfield.setForeground(new Color(0, 0, 205));
		residenceJtextfield.setFont(new Font("Dialog", Font.BOLD, 16));
		
		  //validation
		((AbstractDocument)residenceJtextfield.getDocument()).setDocumentFilter(
	             new StringValidator());
		
		residenceJtextfield.setBounds(540, 320, 130, 25);
		f.getContentPane().add(residenceJtextfield);
		residenceJtextfield.setColumns(10);
		
		saveButton = new JButton("Submit");
		
		saveButton.addActionListener(this);
		
		infomationJtext = new JTextField();
		infomationJtext.setBorder(null);
		infomationJtext.setFont(new Font("Dialog", Font.BOLD, 16));
		infomationJtext.setBackground(new Color(245, 255, 250));
		infomationJtext.setForeground(new Color(255, 0, 0));
		infomationJtext.setBounds(200, 440, 490, 30);
		f.getContentPane().add(infomationJtext);
		infomationJtext.setColumns(10);
		
		
		saveButton.setBounds(560, 500, 110, 25);
		f.getContentPane().add(saveButton);
		
		backbutton = new JButton("BACK");
		
		backbutton.addActionListener(this);
		
		backbutton.setBounds(400, 500, 120, 25);
		f.getContentPane().add(backbutton);
		JRadioButton femaleRadioGroup1 = new JRadioButton("Female");
		femaleRadioGroup1.setBounds(420, 130, 90, 25);
		f.setSize(717, 550);
		f.setLocation(40, 180);	
		f.setVisible(true);
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==backbutton){
			f.dispose();
			
		}else if(e.getSource()==saveButton){
			submitData();
		}
		
	}
	
	private void submitData(){
		 String  sFname=studentFnameJtextfield.getText();
		   String  sMname=studentMnameJetextField.getText();
		   String  sSurname=studentSurnameJtextField.getText();
		   String  gFname=guardianFnameJtextfield.getText();
		   String  gMname=guardianMnameJetextField.getText();
		   String  gSurname=guardianSurnameJtextField.getText();
		   String  gIDNO=idJtextfield.getText();
		   String  gPhone=phoneJtextField.getText();
		   String  gresidence=residenceJtextfield.getText();
		   
		   
		   final String studentFname = sFname.trim().toUpperCase();
		   final String studentMname = sMname.trim();
		   final String studentLname = sSurname.trim();
		   final String studentGender = (String) studentGederjcombo.getSelectedItem();
		   final String studentclass = (String) classJcombobox.getSelectedItem();
		   
		   final String guardianFname = gFname.trim();
		   final String guardianMname= gMname.trim();
		   final String guardianLname= gSurname.trim();
		   final String guardianGender = (String) gurdianGederCombobox.getSelectedItem();
		   final String guardianIDNo = gIDNO.trim();
		   final String guardianPhone = gPhone.trim();
		   final String guardianResidence = gresidence.trim();
		   
		  
		   
		   if(studentFname.length()<1 || studentMname.length()<1 || studentLname.length()<1 || guardianFname.length()<1
				   || guardianMname.length()<1 || guardianLname.length()<1 || guardianIDNo.length()<1 ||
				   guardianPhone.length()<1 || guardianResidence.length()<1){
			   
			   infomationJtext.setText("Some of Your Values Fields  Are Empty");
			
		   }
		   
		   else if(studentFname.length()>20 || studentMname.length()>20 || studentLname.length()>20 || guardianFname.length()>20
				   || guardianMname.length()>20 || guardianLname.length()>20 || guardianIDNo.length()>20 ||
				   guardianPhone.length()>10 || guardianResidence.length()>20){
			   
			  infomationJtext.setText("Some of Your Values Are out of Required Range");
		   }
		   
		   else {  
			  Register r= new Register(studentFname, studentMname, studentLname,
					   studentGender, studentclass, guardianFname,
					   guardianMname, guardianLname, guardianGender, 
					   guardianIDNo, guardianPhone, guardianResidence);
			   f.dispose();
			   r.submit();
	}
	}
}
