package com.school.userInterface;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import com.school.db.DbConnector;

import javax.swing.JButton;



public class SearchStudent{
	 public JFrame f;
	 private JTable table;
	 JPanel panel;
	 private JScrollPane scroll;
	 public  JTextField findjText;
	
	 private DefaultTableModel model = new DefaultTableModel();
	 private JButton cancel,view;
	 public int student_Id = 0;
	 
	 private String[] columnNamess = {"ID","First Name", "Middle Name", "Surname"};
	 private Connection dbConnection = null;
	 private Statement st = null;
	 private ResultSet rs = null; 
	
	 SearchStudent(){
		 searchUserInterface();
	 }
		
	 
	
	 @SuppressWarnings("serial")
	void searchUserInterface(){
		 
		  f = new JFrame("    Search Students");
		  f.getContentPane().setBackground(new Color(245, 255, 250));
		  f.setBackground(new Color(255, 255, 255));
		  f.getContentPane().setLayout(null);
		  panel  = new JPanel();
		  panel.setBorder(new LineBorder(new Color(0, 255, 0), 1, true));
		  panel.setBackground(new Color(240, 248, 255));
		  panel.setBounds(20, 80, 520, 285);
		  
		 
		  model.setColumnIdentifiers(columnNamess);
		 
		  table  = new JTable(){
			  public boolean isCellEditable(int row, int column){
			        return false; }
			  
		  };
		  table.setModel(model);
		  table.setShowGrid(false);
	
		  JLabel firstname = new JLabel("First Name");
		  firstname.setBounds(50, 30, 90, 25);
		  f.getContentPane().add(firstname);
		  
		  findjText = new JTextField();
		  findjText.setBounds(150, 30, 200, 25);
		  f.getContentPane().add(findjText);
		  findjText.setColumns(10);
		  
			//validation
			((AbstractDocument)findjText.getDocument()).setDocumentFilter(
		             new StringValidator());
		  findjText.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
					update();
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		  table.setFont(new Font("Dialog", Font.PLAIN, 15));
		  
		  
		  table.setPreferredScrollableViewportSize(new Dimension(500, 250));
		  table.setFillsViewportHeight(true);
		 
		  table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		  table.getSelectionModel().addListSelectionListener(new RowListener());
		 
		 
		  scroll  = new JScrollPane(table);
		  scroll.setViewportBorder(new LineBorder(new Color(148, 0, 211)));
		  
		  panel.add(scroll);
		 
		  f.getContentPane().add(panel);
		  
		  view = new JButton("View");
		  view.setEnabled(false);
		  view.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				f.dispose();
				StudentPlofile sp = new StudentPlofile();
				sp.executer(student_Id, findjText.getText());
			}
		});
		  
		  view.setBounds(220, 410, 110, 25);
		  f.getContentPane().add(view);
		  
		  cancel = new JButton("Cancel");
		  cancel.setBounds(350, 410, 110, 25);
		  
		  cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				f.dispose();
				new MainInterface();
			}
		});
		  
		  f.getContentPane().add(cancel);
	
		  f.setLocation(40, 180);
		  f.setSize(550,500);
		  f.setResizable(false);
		  f.setVisible(true);
		 defaultData();
	 }
	 
	private void update(){
		 String textvalue = findjText.getText();
		 searchData(textvalue);
	 }
	 public void refresh(String s){
		 String textvalue = s;
		 searchData(textvalue);
	 }
	 
	 
	 private void defaultData(){
		
		 String sql = "select Student_ID, First_Name,Middle_Name,Surname from Person,Student where Person.Per_Desc_ID = 2"
			 		+ " and Person.Person_ID = Student.Person_ID";
		 data(sql);
	 }
	 
	 
	 private void searchData(String text){
		 String textvalue = text;
		 String sql = "select Student_ID, First_Name,Middle_Name,Surname from Person,Student where Person.Per_Desc_ID = 2"
			 		+ " and Person.Person_ID = Student.Person_ID and Person.First_Name like '" + textvalue +"%'";
		 data(sql); 
	 }

	 
	 private void data(String sql){
		 model.setRowCount(0);
		
	
		 int id = 0;
		 String fName,mName, sur = null;
		 
		 
			 try { 
				 
				 this.dbConnection = DbConnector.getDBConnection();
				 this.st = dbConnection.createStatement();
				
				 this.rs=st.executeQuery(sql);
				 
					 while(rs.next()) {
						 id = rs.getInt("Student_ID");
						 fName = rs.getString("First_Name");
						 mName = rs.getString("Middle_Name");
						 sur =  rs.getString("Surname");
				 
						 model.addRow(new Object[]{id,fName, mName, sur});
			
				                       }
					 
	             } 
			 catch(Exception ex){
	            	 JOptionPane.showMessageDialog(null, ex.getMessage(),"Error",
	            	 JOptionPane.ERROR_MESSAGE);
	            	            } finally{try{dbConnection.close();} catch(SQLException e){}}
			
	 }

	 
	  private class RowListener implements ListSelectionListener {
	        public void valueChanged(ListSelectionEvent event) {
	        	 ListSelectionModel lsm = (ListSelectionModel)event.getSource();
	        	 
	        	 if (lsm.isSelectionEmpty()) {
	        		// view.setEnabled(false);
	        	 }else {
	        	int studentId = (int) table.getModel().getValueAt(table.getSelectedRow(), 0);
	        	student_Id =studentId;
	        	view.setEnabled(true);
	        	}
	       
	        }
	        
	    }
	 
	
}





