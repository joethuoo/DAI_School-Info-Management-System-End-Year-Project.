package com.school.userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

import com.school.db.BalancesFinder;
import com.school.db.DbConnector;



public class ChooseClasssToSubscribe {
	private JTextField yearJtextfield;
	private DbConnector n  = new DbConnector();
	int id =0;
	private JFrame ct ;
	private JButton Configue ;
	private String name,className;
	private StudentPlofile sp = null;
	
	public ChooseClasssToSubscribe(StudentPlofile profile ) {
		this.sp=profile;
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void chooseClasss(int Student_id,String CLASS,String NAME){
		this.id=Student_id;
		this.name =NAME;
		this.className =CLASS;
		
		ct  =new JFrame();
		ct.getContentPane().setBackground(new Color(245, 255, 250));
		ct.setResizable(false);
		ct.setSize(450, 300);
		ct.setLocation(300, 400);
		ct.setTitle("    Choose Class");
		ct.getContentPane().setLayout(null);
		
		JLabel studentnameLabel = new JLabel("Student Name");
		studentnameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		studentnameLabel.setBounds(30, 30, 150, 20);
		ct.getContentPane().add(studentnameLabel);
		
		JTextField studentnameJtextfield = new JTextField();
		studentnameJtextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		studentnameJtextfield.setForeground(new Color(0, 0, 255));
		studentnameJtextfield.setText(this.name);
		studentnameJtextfield.setBorder(null);
		studentnameJtextfield.setBackground(new Color(245, 255, 250));
		studentnameJtextfield.setEditable(false);
		studentnameJtextfield.setBounds(200, 30, 230, 20);
		ct.getContentPane().add(studentnameJtextfield);
		studentnameJtextfield.setColumns(10);
		
		JLabel classLabel = new JLabel("Last Active  Class");
		classLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		classLabel.setBounds(30, 80, 150, 25);
		ct.getContentPane().add(classLabel);
		
		JTextField classJtextfield = new JTextField();
		classJtextfield.setBorder(new LineBorder(new Color(0, 0, 0)));
		classJtextfield.setBackground(new Color(255, 255, 255));
		classJtextfield.setEditable(false);
		classJtextfield.setFont(new Font("Dialog", Font.PLAIN, 14));
		classJtextfield.setForeground(new Color(0, 0, 255));
		classJtextfield.setText(" "+this.className);
		classJtextfield.setBounds(200, 80, 100, 25);
		ct.getContentPane().add(classJtextfield);
		classJtextfield.setColumns(10);
		
		JLabel yearLabel = new JLabel("YEAR");
		yearLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		yearLabel.setBounds(310, 80, 60, 25);
		ct.getContentPane().add(yearLabel);
		
		String yr =Integer.toString(n.getYear(n.getLastClassSubscriptionYear(id)));
		yearJtextfield = new JTextField();
		yearJtextfield.setBounds(370, 80, 60, 25);
		yearJtextfield.setText(" "+yr);
		yearJtextfield.setFont(new Font("Dialog", Font.PLAIN, 14));
		yearJtextfield.setForeground(new Color(0, 0, 255));
		ct.getContentPane().add(yearJtextfield);
		yearJtextfield.setColumns(10);
		
		
		JLabel chooseLabel = new JLabel("Choose Class");
		chooseLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		chooseLabel.setBounds(30, 130, 120, 25);
		ct.getContentPane().add(chooseLabel);
		
		final JComboBox classesCombobox = new JComboBox();
		classesCombobox.setModel(new DefaultComboBoxModel(getClassesComboItems(id)));
		classesCombobox.setBounds(200, 130, 170, 25);
		
		ct.getContentPane().add(classesCombobox);
	
		
		JButton backButton = new JButton("BACK");
	
		backButton.setBounds(130, 210, 110, 25);
		ct.getContentPane().add(backButton);
		
		Configue = new JButton("Subscribe");
		Configue.setBounds(280, 210, 110, 25);
		Configue.setEnabled(false);
		
		ct.getContentPane().add(Configue);
		
        classesCombobox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(classesCombobox.getSelectedItem().toString()!=null){
				Configue.setEnabled(true);
				 }
			}
		});
		
         Configue.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				    String clas =classesCombobox.getSelectedItem().toString();
				    int clasid = n.getClassID(clas);

					int confirm = JOptionPane.showConfirmDialog(null, "CONFIRM CLASS ONCE SUBSCRIBED YOU CANNOT BE ABLE TO CHANGE \n\n"+
					clas,"Confirm CLass",JOptionPane.YES_NO_OPTION);
					if (confirm ==0){
						
							BalancesFinder bf = new BalancesFinder();
							 bf.classSubscribe(id, n.getYearId(), clasid);
							 sp.refresher(id);
							 sp.info.setForeground(Color.GREEN);
							 sp.updateJcombo(id);
							 sp.info.setText("Subscription Successful Thankyou...");
							 ct.dispose();
							
							
					}
					else {
					
					    }
				 
				 
			}
		});
        
         backButton.addActionListener(new ActionListener() {
 			
 			@Override
 			public void actionPerformed(ActionEvent arg0) {
 			ct.dispose();
 			}
 		});
		ct.setVisible(true);
		
	}
	
	
	public String[] getClassesComboItems(int id){
		
		ArrayList<Integer> classes=new ArrayList<Integer>();
		classes = n.getClassesSubscribed(id);
	
		ArrayList<String> c=new ArrayList<String>();
		
		for(int x= 0;x<classes.size();x++){
			c.add(n.getName(classes.get(x)));
		}
		
		ArrayList<String> alllist=new ArrayList<String>();
		alllist=n.classes();
		
		Iterator<String> h = alllist.iterator();
		
		while(h.hasNext()){
			if(c.contains(h.next())){
				h.remove();
			}
		}
		String cl[] = alllist.toArray(new String[alllist.size()]);
		
		return cl;
	}
	
}
