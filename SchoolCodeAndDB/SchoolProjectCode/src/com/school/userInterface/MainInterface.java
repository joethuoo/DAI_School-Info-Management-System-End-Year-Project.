package com.school.userInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class MainInterface extends JPanel implements ActionListener{

	JButton registerstudent,viewStudentPlofile, adminstration;

	
	public MainInterface (){
		super();
		setBackground(new Color(245, 255, 250));
		setLayout(null);
		setSize(400, 400);
		setBounds(70, 80, 400, 290);
		//setBounds(100, 150, 400, 300);
		setVisible(true);
		setBorder(new LineBorder(Color.GREEN, 3, true));
		
		registerstudent = new JButton();
		registerstudent.setText("REGISTER    STUDENTS");
		registerstudent.setBounds(80, 30, 230, 25);
		
		registerstudent.addActionListener(this);
		add(registerstudent);
		
		viewStudentPlofile = new JButton();
		viewStudentPlofile.setText("VIEW   STUDENTS  PROFILE");
		viewStudentPlofile.setBounds(80, 120, 230, 25);
		
		viewStudentPlofile.addActionListener(this);
		add(viewStudentPlofile);

		adminstration = new JButton();
		adminstration.setText("FEE  ADMINISTRATION ");
		adminstration.setBounds(80, 200, 230, 25);
		adminstration.addActionListener(this);
		
		add(adminstration);

	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==registerstudent){
			new Registration();
			
		}else if(e.getSource()==viewStudentPlofile){
			new SearchStudent();
			
		}else if(e.getSource()==adminstration){
			
			FeesStructureUi fs = new FeesStructureUi("normal");
			fs.FeesStructureUinterface();
		}
		
	}
		
	}