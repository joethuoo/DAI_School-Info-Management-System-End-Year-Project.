package com.school.userInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.school.db.BalancesConfigurer;
import com.school.db.BalancesFinder;
import com.school.db.DbConnector;
import com.school.db.FeesData;

import javax.swing.SwingConstants;


public class ChooseTermPayer implements ActionListener{
	public JFrame ct;
	private JTextField studentnameJtextfield,classJtextfield,statusinfo, amountJtextfiled,credit,message;
	private JButton paybutton,backButton,clearBalance,feeStructure,confirmCreditButton;
	private JCheckBox useAvailableCredit,termOneJcheck,termTwojcheck,termThreeJcheck;
	private int Student_Id,yearID,clasID,termID= 0;
	private String className,name,term,status,value=null;
	Thread thread= null;
	
	private DbConnector n = new DbConnector();
	private BalancesFinder bf = new BalancesFinder();
	//private FeeConfiguration fc  =null;
	private FeesData fd  =null;
	private Connection dbConnection = null;
	private Statement stmt  = null;
	private ResultSet  rs = null;
	private BalancesConfigurer bc = null;
	private PreparedStatement ps =  null;
	private StudentPlofile sp = null;

	
	public  ChooseTermPayer(){
	}
	
	
	public void ChooseTermUi(final int id,String name,final String className,StudentPlofile profile ) {
		this.Student_Id=id;
		this.className =className;
		this.name=name;
		this.yearID = n.getYearId();
		this.clasID = n.getClassID(this.className);	
		this.sp= profile;
		this.bc = new BalancesConfigurer(Student_Id);
	
		
		ct  =new JFrame();
		ct.getContentPane().setBackground(new Color(245, 255, 250));
		ct.setSize(470, 360);
		ct.setLocation(800, 400);
		ct.setResizable(false);
		ct.setTitle("    Fees Payment");
		ct.getContentPane().setLayout(null);
		
		JLabel studentnameLabel = new JLabel("Student Name");
		studentnameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		studentnameLabel.setBounds(30, 30, 150, 20);
		ct.getContentPane().add(studentnameLabel);
		
		studentnameJtextfield = new JTextField();
		studentnameJtextfield.setFont(new Font("Dialog", Font.PLAIN, 13));
		studentnameJtextfield.setForeground(new Color(0, 0, 255));
		studentnameJtextfield.setText(this.name);
		studentnameJtextfield.setBorder(null);
		studentnameJtextfield.setBackground(new Color(245, 255, 250));
		studentnameJtextfield.setEditable(false);
		studentnameJtextfield.setBounds(180, 30, 200, 20);
		ct.getContentPane().add(studentnameJtextfield);
		studentnameJtextfield.setColumns(10);
		
		JLabel classLabel = new JLabel("For Class");
		classLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		classLabel.setBounds(30, 65, 100, 20);
		ct.getContentPane().add(classLabel);
		
		classJtextfield = new JTextField();
		classJtextfield.setBorder(null);
		classJtextfield.setBackground(new Color(245, 255, 250));
		classJtextfield.setEditable(false);
		classJtextfield.setFont(new Font("Dialog", Font.PLAIN, 14));
		classJtextfield.setForeground(new Color(0, 0, 255));
		classJtextfield.setText(this.className);
		classJtextfield.setBounds(180, 65, 120, 20);
		ct.getContentPane().add(classJtextfield);
		classJtextfield.setColumns(10);
		
		JLabel chooseLabel = new JLabel("Choose Term");
		chooseLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		chooseLabel.setBounds(30, 100, 120, 25);
		ct.getContentPane().add(chooseLabel);
		
		termOneJcheck = new JCheckBox("TERM ONE");
		termOneJcheck.addItemListener(new JcheckButtonHandler("TERM ONE"));
		
		statusinfo = new JTextField();
		statusinfo.setBackground(new Color(245, 255, 250));
		statusinfo.setEditable(false);
		statusinfo.setForeground(new Color(0, 255, 0));
		statusinfo.setHorizontalAlignment(SwingConstants.LEFT);
		statusinfo.setFont(new Font("Dialog", Font.BOLD, 18));
		statusinfo.setBorder(null);
		
	
		ct.getContentPane().add(statusinfo);
		statusinfo.setColumns(10);
		termOneJcheck.setBackground(new Color(245, 255, 250));
		termOneJcheck.setBounds(190, 110, 110, 20);
		ct.getContentPane().add(termOneJcheck);
		
		termTwojcheck = new JCheckBox("TERM TWO");
		termTwojcheck.addItemListener(new JcheckButtonHandler("TERM TWO"));
		termTwojcheck.setBackground(new Color(245, 255, 250));
		termTwojcheck.setBounds(190, 140, 110, 20);
		ct.getContentPane().add(termTwojcheck);
		
		termThreeJcheck = new JCheckBox("TERM THREE");
		termThreeJcheck.addItemListener(new JcheckButtonHandler("TERM THREE"));
		termThreeJcheck.setBackground(new Color(245, 255, 250));
		termThreeJcheck.setBounds(190, 170, 110, 20);
		ct.getContentPane().add(termThreeJcheck);
		
		ButtonGroup gr = new ButtonGroup();
		gr.add(termOneJcheck);
		gr.add(termTwojcheck);
		gr.add(termThreeJcheck);
		
		message = new JTextField();
		message.setHorizontalAlignment(SwingConstants.LEFT);
		message.setBackground(new Color(245, 255, 250));
		message.setForeground(new Color(0, 255, 0));
		message.setFont(new Font("Dialog", Font.BOLD, 16));
		message.setEditable(false);
		message.setBorder(null);
		message.setBounds(40, 220, 400, 30);
		message.setColumns(10);
		ct.getContentPane().add(message);
		
		amountJtextfiled = new JTextField();
		amountJtextfiled.setBackground(new Color(245, 255, 250));
		amountJtextfiled.setHorizontalAlignment(SwingConstants.LEFT);
		amountJtextfiled.setForeground(new Color(0, 255, 0));
		amountJtextfiled.setFont(new Font("Dialog", Font.BOLD, 16));
		amountJtextfiled.setEditable(false);
		amountJtextfiled.setBorder(null);
		
		amountJtextfiled.setBounds(40, 250, 380, 30);
		ct.getContentPane().add(amountJtextfiled);
		amountJtextfiled.setColumns(10);
		
				
		
		JPanel decoPanel = new JPanel();
		decoPanel.setBackground(new Color(245, 255, 250));
		decoPanel.setBorder(new LineBorder(new Color(0, 0, 255), 1, true));
		decoPanel.setBounds(180, 100, 255, 110);
		ct.getContentPane().add(decoPanel);
		decoPanel.setLayout(null);
		
		
		clearBalance = new JButton("Clear Balances");
		clearBalance.setBounds(20, 290, 160, 25);
		clearBalance.setVisible(false);
		clearBalance.addActionListener(this);
		ct.getContentPane().add(clearBalance);
		
		feeStructure = new JButton("FEE STRUCTURE");
		feeStructure.setBounds(20, 290, 230, 25);
		feeStructure.setVisible(false);
		feeStructure.addActionListener(this);
		ct.getContentPane().add(feeStructure);
		
		backButton = new JButton("BACK");
		backButton.addActionListener(this);
		backButton.setBounds(180, 290, 110, 25);
		ct.getContentPane().add(backButton);
		
		paybutton = new JButton("PAY");
		paybutton.setBounds(325, 290, 110, 25);
		paybutton.setEnabled(false);
		paybutton.addActionListener(this);
		ct.getContentPane().add(paybutton);
		
		confirmCreditButton = new JButton("Comfirm");
		confirmCreditButton.setBounds(325, 290, 110, 25);
		confirmCreditButton.setVisible(false);
		confirmCreditButton.addActionListener(this);
		ct.getContentPane().add(confirmCreditButton);
		
		useAvailableCredit = new JCheckBox("Use Available Credit ");
		useAvailableCredit.setVisible(false);
		useAvailableCredit.setForeground(new Color(0, 0, 255));
		useAvailableCredit.addItemListener(new UseAvailableCredit());
		useAvailableCredit.setFont(new Font("Dialog", Font.BOLD, 16));
		useAvailableCredit.setBackground(new Color(245, 255, 250));
		useAvailableCredit.setBounds(40, 325, 230, 25);
		ct.getContentPane().add(useAvailableCredit);
		
		credit = new JTextField();
		credit.setBackground(new Color(245, 255, 250));
		credit.setBorder(null);
		credit.setForeground(new Color(0, 255, 0));
		credit.setFont(new Font("Dialog", Font.BOLD, 16));
		credit.setBounds(280, 325, 180, 25);
		ct.getContentPane().add(credit);
		credit.setColumns(10);
		ct.setVisible(true);
		
	}
	
	private class UseAvailableCredit implements ItemListener{
	
		public void itemStateChanged(ItemEvent e) {
			
			useAvailableCredit.setText("Enter Amount");
			credit.setText("CREDIT IS : "+bc.getsetStudentCredit());
			paybutton.setVisible(false);
			confirmCreditButton.setVisible(true);
			clearBalance.setEnabled(false);
	
			
			if(e.getStateChange()==ItemEvent.DESELECTED){
				useAvailableCredit.setText("Use Available Credit ");
				credit.setText(null);
				paybutton.setVisible(true);
				confirmCreditButton.setVisible(false);
				clearBalance.setEnabled(true);
				
				if(bf.getAllBalancesCumulative(Student_Id)>0){
					paybutton.setEnabled(false);
				}
				
			}
	
		}
	
	}
	
	private class JcheckButtonHandler implements ItemListener{
		String terms; 
	
		JcheckButtonHandler(String s){
			 terms= s;
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			
			useAvailableCredit.setSelected(false);
			
			term =terms;
			termID = n.getTermID(term);
			
			if(checkTermDoubleEntry(Student_Id, n.getYearId(), n.getClassID(className), n.getTermID(term))==true){
				//----------------------------------------------------------------------------------------
				status=getTermStatus(clasID, termID ,yearID);
				
						//--------------------------------------------------------------------------------
							if(status.equals("PAID")){
									value= "PAID";
									statusinfo.setForeground(new Color(0, 255, 0));
									message.setForeground(new Color(0, 255, 0));
									status(value,term + " IS FULLY PAID....");
									amountJtextfiled.setText(null);
									backButton.setBounds(180, 290, 110, 25);
									clearBalance.setVisible(false);
									paybutton.setVisible(true);
									paybutton.setEnabled(false);
									feeStructure.setVisible(false);
									useAvailableCredit.setVisible(false);
									
							}else if(status.equals("BALANCE")){
								value= "BALANCE ";
								statusinfo.setForeground(new Color(255, 0, 0));
								message.setForeground(new Color(255, 0, 0));
								status(value,"Clear "+ term + " Outstanding Balances..");
								amountJtextfiled.setText("AMOUNT TO PAY IS :  "+bf.getAllBalancesCumulative(Student_Id));
								amountJtextfiled.setForeground(Color.BLUE);
								
								backButton.setBounds(200, 290, 110, 25);
								clearBalance.setVisible(true);
								paybutton.setVisible(false);
								paybutton.setEnabled(false);
								feeStructure.setVisible(false);
								useAvailableCredit.setVisible(true);
								
								if(bc.getsetStudentCredit()<=0){
									useAvailableCredit.setEnabled(false);
									useAvailableCredit.setText("No Available Credit");
								}
							}
				
						//------------------------------------------------------------------------------------
				
			
				
			}else{
				howToPay();
			
			}
			
			if(e.getStateChange()==ItemEvent.DESELECTED){
				term =null;
			}
		}
		
		
		private void howToPay(){
			fd=new FeesData(yearID, clasID, termID);
			if(fd.getFeesTermAmount()<=0){	
				
				status=getTermStatus(clasID, termID ,yearID);
				message.setForeground(Color.BLUE);
				statusinfo.setForeground(new Color(255, 0, 0));
				clearBalance.setVisible(false);
				status(" ","PLEASE CONFIGURE FEES STUCTURE :  ");
				amountJtextfiled.setText("OF  :  "+ term+ " FIRST......!!");
				amountJtextfiled.setForeground(Color.RED);
				feeStructure.setVisible(true);
				backButton.setBounds(290, 290, 110, 25);
				paybutton.setVisible(false);
				useAvailableCredit.setVisible(false);
				
				
			}else if(fd.getFeesTermAmount()>0){	
			
				feeStructure.setVisible(false);
				message.setForeground(new Color(255, 0, 0));
				statusinfo.setForeground(new Color(255, 0, 0));
				clearBalance.setVisible(false);
				status("PAY ","PLEASE PAY FOR :  "+ term);
				
				amountJtextfiled.setText("FEES AMOUNT REQUIRED  IS :  "+ fd.getFeesTermAmount());
				amountJtextfiled.setForeground(Color.GREEN);
				
				backButton.setBounds(180, 290, 110, 25);
			
				paybutton.setVisible(true);
				paybutton.setEnabled(true);
				useAvailableCredit.setVisible(true);

				
				if(bc.getsetStudentCredit()<=0){
					useAvailableCredit.setEnabled(false);
					useAvailableCredit.setText("No Available Credit");
				}
			}
		}
			//--------------------------------------------------------------------------------------
			
			private void status(String status,String info){
				
				
				switch(term){
				case "TERM ONE":	
									statusinfo.setBounds(305, 108, 120, 25);
									statusinfo.setText(status);
									message.setText(info);
					break;
					
				case "TERM TWO":	
									statusinfo.setBounds(305, 138, 120, 25);
									statusinfo.setText(status);
									message.setText(info);
					break;
					
				case "TERM THREE":	
									statusinfo.setBounds(305, 168, 120, 25);
									statusinfo.setText(status);
									message.setText(info);
					break;
					
				default : break;
				}
			}
	}
	//----------------------------------------------------END OF JCHECK Handlerer
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==backButton){
				ct.dispose();
				
		}else if(e.getSource()==feeStructure){
				configureFeeStructure();	
				
		}else if(e.getSource()==paybutton){
				payMethod();
			
		}else if(e.getSource()==clearBalance){
			clearBalances();
		}else if(e.getSource()==confirmCreditButton){
			payWithCredit();
		}
	}
	
	private void payWithCredit(){
	
		double credit  =bc.getsetStudentCredit();
		this.bc= new BalancesConfigurer(Student_Id);
		double balance =bf.getAllBalancesCumulative(Student_Id);
		double cash =balance-credit;
		double extraCash  =credit-balance;
		
		
		if(credit>bf.getAllBalancesCumulative(Student_Id)){
			bf.clearBalances(Student_Id, (int)cash);
			this.bc = new BalancesConfigurer(Student_Id);
			feeCalculator(Student_Id, extraCash, clasID, termID);
			sp.info.setText("");
			sp.dataSource(Student_Id);
			ct.dispose();
			
			
		}else if(credit<=bf.getAllBalancesCumulative(Student_Id)){
			bf.clearBalances(Student_Id, 0);
			sp.info.setText("");
			sp.dataSource(Student_Id);
			ct.dispose();
			
		}
		
		
		
		
		
	}
	private void clearBalances(){
		BalancesFinder bf = new BalancesFinder();
		
		if (bf.getAllBalancesCumulative(Student_Id)<=0){
			message.setForeground(Color.GREEN);
			message.setText("No Outstanding Balances ");
		}else{
			message.setForeground(Color.RED);
			message.setText("The Outstanding Balance is "+bf.getAllBalancesCumulative(Student_Id));
			AmountInputUi amountsUi = new AmountInputUi();
			amountsUi.studentTermFeePayment(Student_Id, clasID, termID,"clearAllBalancesB",sp,this);
		}
	}
	
	
	private void configureFeeStructure(){
		this.termID = n.getTermID(term);
		ct.dispose();
		
		FeesStructureUi fs= new FeesStructureUi("passAsYouPay");
		fs.profileUpdate(sp);
		fs.classJcombo.setSelectedIndex(clasID-1);
		fs.termJcombo.setSelectedIndex(termID-1);
		
		this.thread= new Thread(new WaitFeeToConfigured(yearID, clasID, termID));
		thread.start();
	}
	
	
	private void payMethod(){
			this.termID = n.getTermID(term);
			AmountInputUi au= new AmountInputUi();
			au.studentTermFeePayment(Student_Id, clasID, termID, "payBalanceAndNextTerm",sp,this);
			
		
	}
	
 	private class WaitFeeToConfigured implements Runnable{
		int year,clas,termID= 0;
		
		public WaitFeeToConfigured(int yr,int cl,int trm){
			this.year=yr;
			this.clas=cl;
			this.termID=trm;
		}
		@SuppressWarnings("deprecation")
		public void run() {
		int whether = 0;
		double value= 0;
		
		
		
			while(true){
				fd=new FeesData(year, clas, termID);
				value = fd.getFeesTermAmount();
				
				if(value<=0){
					whether=0;
				}else if (value>0){
					whether=1;
				}
				
				switch(whether){
				case 0:
					break;
				
				case 1: String[] choices = {"Continue","Cancel"};
				
				 int response  = JOptionPane.showOptionDialog(null, "Fees Amount of "+className+" and of "+term+ " Have been Configured Successfully\n"
					 		+ "\nContinue With Student Fees and Balance Payment", 
							  "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, "Cancel");
				 			//-----------------------------------------INNER SWITCH
				 			switch(response){
				 			case 0: 	ChooseTermUi(Student_Id, name, className,sp);
				 							if(termID==1){
				 									termOneJcheck.setSelected(true);
				 							}else if(termID==2){
				 									termTwojcheck.setSelected(true);
					 						
					 						}else if(termID==3){
					 								termThreeJcheck.setSelected(true);
					 						}
				 						thread.stop();
				 				break;
				 				
				 			case 1:
				 						thread.stop();
				 				break;
				 				
				 			default:break;
				 			}
				 			
				 			//-----------------------------------------END OF INNER SWITCH
					break;
					
				default: thread.stop();
				break;
				
				}
				
			}
			
		}
		
	}
	
	public void feeCalculator( int STUDENT,double CASH,int clas,int term){
		this.yearID = n.getYearId();
		this.clasID = clas;
		this.termID = term;
		this.bc = new BalancesConfigurer(STUDENT);
		fd=new FeesData(yearID, clasID, termID);
		double studentCredit =bc.getsetStudentCredit()+CASH;
	
		
		double amountTopay =fd.getFeesTermAmount();
		double calculatedAmount =studentCredit -amountTopay;
		this.bf = new BalancesFinder();
		double balance =bf.getAllBalancesCumulative(STUDENT);
	
		//---IF THE CALCULATED AMOUNT IS LESS THAN CONFIGURED FEE AMOUNT------------------------
			if(calculatedAmount<0){
				try{
				calculatedAmount=calculatedAmount-calculatedAmount-calculatedAmount;
				dbConnection = (Connection) DbConnector.getDBConnection();
				dbConnection.setAutoCommit(false);
				
				termSubscription(STUDENT,yearID , clasID, termID, "BALANCE", calculatedAmount,fd.getFeeStructureID());
				AccountHandler(0, calculatedAmount+balance, STUDENT);
				paymentHistory(STUDENT, yearID, CASH);
				
				dbConnection.commit();
				
				}catch (SQLException e){try {
					dbConnection.rollback();} catch (SQLException e1) {System.out.println(e1.getMessage());}}
				finally{try{dbConnection.close();} catch(SQLException e){}}
			}
			
			//---IF THE CALCULATED AMOUNT IS EQUAL TO CONFIGURED FEE AMOUNT------------------------
			else if (calculatedAmount== 0){
				try{
				dbConnection = (Connection) DbConnector.getDBConnection();
				dbConnection.setAutoCommit(false);
				
				termSubscription(STUDENT, yearID, clasID, termID, "PAID", 0,fd.getFeeStructureID());
				AccountHandler(calculatedAmount, calculatedAmount+balance, STUDENT);
				
				paymentHistory(STUDENT, yearID, CASH);
				
				dbConnection.commit();
				
				}catch (SQLException e){try {
					dbConnection.rollback();} catch (SQLException e1) {e1.printStackTrace();}}
				finally{try{dbConnection.close();} catch(SQLException e){}}
			}
			
			//---IF THE CALCULATED AMOUNT IS GREATER THAN CONFIGURED FEE AMOUNT------------------------
			else if (calculatedAmount>0){
				try{
					dbConnection = (Connection) DbConnector.getDBConnection();
					dbConnection.setAutoCommit(false);
					
					termSubscription(STUDENT, yearID, clasID, termID, "PAID", 0,fd.getFeeStructureID());
					AccountHandler(calculatedAmount, 0+balance, STUDENT);
					paymentHistory(STUDENT, yearID, CASH);
					
					dbConnection.commit();
					
					}catch (SQLException e){try {
						dbConnection.rollback();} catch (SQLException e1) {e1.printStackTrace();}}
					finally{try{dbConnection.close();} catch(SQLException e){}}
				
			}
	}
	
	
	
	
	
	private boolean checkTermDoubleEntry(int studentID,int YEAR,int CLASS,int TERM){
		
		try{
			dbConnection = (Connection) DbConnector.getDBConnection();
			
			String sql= "select val_id from Fee_Validator where Student_ID = "+studentID+ "  and Yr_ID = "+YEAR+ " and Class_Id = "+CLASS + " and Term_Id = "+TERM;
			
			stmt =(Statement) dbConnection.createStatement();
			rs= (ResultSet) stmt.executeQuery(sql);
			
				if(rs.next()==true){
						//int t =rs.getInt("val_id");
		
						return true;
				}else{
				return false;
				}
			
		}catch (SQLException e){}
		finally{try{dbConnection.close();} catch(SQLException e){e.printStackTrace();}}
		//System.out.println(t);
		return false;
	}
	
	private String getTermStatus(int CLASS,int TERM,int YEAR){
	
		String status=null;
		try{
			dbConnection = (Connection) DbConnector.getDBConnection();
			
			String sql= "select Status FROM Fee_Validator  where Student_ID = "+Student_Id+" and Yr_ID = "+YEAR+ " and Class_Id = "+CLASS + " and Term_Id = "+TERM;
			
			stmt =(Statement) dbConnection.createStatement();
			rs= (ResultSet) stmt.executeQuery(sql);
			
			if(rs.next()){
				//-----------------------------------------------------------------
					if(rs.getString("Status").equals("PAID")){
						status ="PAID";
						
					}else if(rs.getString("Status").equals("BALANCE")){
						status ="BALANCE";
					}
				//-------------------------------------------------------------
			}else{
				status = "null";
			}
			
		}catch (SQLException e){}
		finally{try{dbConnection.close();} catch(SQLException e){e.printStackTrace();}}
		
		return status;
	}


	

	
	
	
	
	 private PreparedStatement termSubscription(int studentID,int YEAR,int classID, int termID,String status,double AMOUNT,int fees_Structure_ID) throws SQLException{ 
			//This Method Registers The Values  of contacts Related to Guardian Into the Contacts Table
			 	
				String sql ="insert into Fee_Validator (Student_ID,Yr_ID,Class_Id,Term_Id,Status,Amount,FeesStructureId) values (?,?,?,?,?,?,?)";
				
				this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
				ps.setInt(1, studentID);
				ps.setInt(2, YEAR);
				ps.setInt(3, classID);
				ps.setInt(4, termID);
				ps.setString(5, status);
				ps.setDouble(6, AMOUNT);
				ps.setInt(7, fees_Structure_ID);
				ps.executeUpdate();
				
				return ps;	
			}

	 
	private  PreparedStatement AccountHandler(double credit ,double balance, int studentid) throws SQLException{ 
			//This Prepared Statement posts Record to Student Account Table;
		    
		     
				String sql ="update StudentAccount set Student_Credit_Acc =?,Student_Balance_Acc =? where Student_ID = ?";
				ps=(PreparedStatement) dbConnection.prepareStatement(sql);
				ps.setDouble(1, credit);
				ps.setDouble(2, balance);
				ps.setInt(3, studentid);
				ps.executeUpdate();
				
				return ps;	
			}
	 
	private PreparedStatement paymentHistory(int studentID,int YEAR,double AMOUNT) throws SQLException{ 
		//This Method Registers The Values  of contacts Related to Guardian Into the Contacts Table
		 	
			String sql ="insert into Student_Payment_History(Student_ID,Yr_ID,Amount) values (?,?,?)";
			
			this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
			ps.setInt(1, studentID);
			ps.setInt(2, YEAR);
			ps.setDouble(3, AMOUNT);
			ps.executeUpdate();
			
			return ps;	
		}

	
	
}
