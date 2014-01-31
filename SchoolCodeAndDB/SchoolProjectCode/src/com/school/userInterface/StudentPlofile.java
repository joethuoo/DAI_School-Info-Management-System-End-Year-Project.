package com.school.userInterface;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.JButton;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.school.db.BalancesFinder;
import com.school.db.DbConnector;
import com.school.db.FeeConfiguration;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;


public class StudentPlofile implements ActionListener {
	
    JFrame spf;
    private JTextField studentJTextfield,fnameTextfield, mNameTextfield,suranmeTextfield,classTextfield,
    				   gfnameTextfield,gmNameTextfield,gsuranmeTextfield, phoneTextfield,gIdTextfield,residenceTextfield;
	 private JButton promoteNextClass, clearbalance,backButton,payfeesButton,cancelButton;
	 private ButtonGroup gr ;
	 private JTable table;
	 private JPanel buttonspanel,selectionPanel;
	 private JCheckBox  clearBalanceJCheck,clearBalanceAndNextTermJcheck,payOnlyJcheckbox;
    
     private String [] columnNames= {"TERM","FEES","MODE","STATUS","BALANCE"};
    
     private DefaultTableModel model = new DefaultTableModel();
	 private JTextField creditAccJtextField;
	 private JTextField balanceJtextField;
	 private FeeConfiguration fc  =null;
	 private  SourceTableData sd =null;
	 private  DbConnector n = new DbConnector();
	 private BalancesFinder bf = new BalancesFinder();
	 private ChooseClasssToSubscribe ccs= new ChooseClasssToSubscribe(this);
	 
	 
	 int year,id=0;
	 
	 private String first,middle,last,className,gfirst,gmiddle,glast,gphone,gId,gRecidence,creditAcc,balAccount,lastQueried,studentName;
	
	 private Connection dbConnection = null;
	 private Statement st = null;
	 private ResultSet rs = null;
	 public JTextField info;
	 private JPanel histroypanel;
	 private JTextField label,datejtextField,defaultJtextFiled,txtYear;
	 @SuppressWarnings("rawtypes")
	public JComboBox classesJcombobox= null;
	 private ClassesData cd = null;
	 
	public StudentPlofile (){
		
	}
	public void executer(int newValue, String text){
		this.id =newValue;
		this.lastQueried = text;
		this.year= n.getLastClassSubscriptionYear(id);
		cd = new ClassesData(id);
		
		profileUI();
		
		
	
	}
	
	@SuppressWarnings({ "unchecked", "serial", "rawtypes" })
	void profileUI (){
		spf = new JFrame();
		spf.getContentPane().setBackground(new Color(255, 255, 255));
		spf.setBackground(new Color(245, 255, 250));
		spf.setSize(800,600);
		spf.setLocation(20, 160);
		spf.getContentPane().setLayout(null);
		
		JPanel studentpanel = new JPanel();
		studentpanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 0), 1, true), "Students Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 0)));
		studentpanel.setBackground(new Color(245, 255, 250));
		studentpanel.setBounds(20, 20, 320, 260);
		spf.getContentPane().add(studentpanel);
		studentpanel.setLayout(null);
		
		JLabel studentId = new JLabel("Student ID");
		studentId.setBounds(20, 35, 120, 25);
		studentId.setFont(new Font("Dialog", Font.BOLD, 16));
		studentpanel.add(studentId);
		
		studentJTextfield = new JTextField();
		studentJTextfield.setBackground(new Color(255, 255, 255));
		studentJTextfield.setEditable(false);
		studentJTextfield.setBounds(160, 35, 120, 25);
		studentJTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		studentpanel.add(studentJTextfield);
		studentJTextfield.setColumns(10);
		
		JLabel firstNameLabel = new JLabel("First Name");
		firstNameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		firstNameLabel.setBounds(20, 80, 120, 25);
		studentpanel.add(firstNameLabel);
		
		fnameTextfield = new JTextField();
		fnameTextfield.setBackground(new Color(255, 255, 255));
		fnameTextfield.setEditable(false);
		fnameTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		fnameTextfield.setBounds(160, 80, 120, 25);
		studentpanel.add(fnameTextfield);
		fnameTextfield.setColumns(10);
		
		JLabel middleNameLabel = new JLabel("Middle Name");
		middleNameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		middleNameLabel.setBounds(20, 125, 120, 25);
		studentpanel.add(middleNameLabel);
		
		mNameTextfield = new JTextField();
		mNameTextfield.setBackground(new Color(255, 255, 255));
		mNameTextfield.setEditable(false);
		mNameTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		mNameTextfield.setBounds(160, 125, 120, 25);
		studentpanel.add(mNameTextfield);
		mNameTextfield.setColumns(10);
		
		JLabel surnameLabel = new JLabel("Surname");
		surnameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		surnameLabel.setBounds(20, 170, 120, 25);
		studentpanel.add(surnameLabel);
		
		suranmeTextfield = new JTextField();
		suranmeTextfield.setBackground(new Color(255, 255, 255));
		suranmeTextfield.setEditable(false);
		suranmeTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		suranmeTextfield.setBounds(160, 170, 120, 25);
		studentpanel.add(suranmeTextfield);
		suranmeTextfield.setColumns(10);
		
		JLabel classLabel = new JLabel("Active  Class ");
		classLabel.setForeground(new Color(255, 0, 0));
		classLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		classLabel.setBounds(20, 210, 120, 25);
		studentpanel.add(classLabel);
		
		classTextfield = new JTextField();
		classTextfield.setBackground(new Color(255, 255, 255));
		classTextfield.setEditable(false);
		classTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		classTextfield.setBounds(160, 210, 120, 25);
		studentpanel.add(classTextfield);
		classTextfield.setColumns(10);
		
		JPanel gurdianpanel = new JPanel();
		gurdianpanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 255, 0), 1, true), "Guardian details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 0)));
		gurdianpanel.setBackground(new Color(245, 255, 250));
		gurdianpanel.setBounds(20, 300, 320, 265);
		spf.getContentPane().add(gurdianpanel);
		gurdianpanel.setLayout(null);
		
		JLabel gFisrstNanmeLabel = new JLabel("G First Name");
		gFisrstNanmeLabel.setBounds(20, 20, 120, 25);
		gFisrstNanmeLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		gurdianpanel.add(gFisrstNanmeLabel);
		
		gfnameTextfield = new JTextField();
		gfnameTextfield.setBackground(new Color(255, 255, 255));
		gfnameTextfield.setEditable(false);
		gfnameTextfield.setBounds(160, 20, 120, 25);
		gfnameTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		gurdianpanel.add(gfnameTextfield);
		gfnameTextfield.setColumns(10);
		
		JLabel gmiddleNameLabel = new JLabel("G Middle Name");
		gmiddleNameLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		gmiddleNameLabel.setBounds(20, 60, 140, 25);
		gurdianpanel.add(gmiddleNameLabel);
		
		gmNameTextfield = new JTextField();
		gmNameTextfield.setBackground(new Color(255, 255, 255));
		gmNameTextfield.setEditable(false);
		gmNameTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		gmNameTextfield.setBounds(160, 60, 120, 25);
		gurdianpanel.add(gmNameTextfield);
		gmNameTextfield.setColumns(10);
		
		JLabel gSurname = new JLabel("G Surname");
		gSurname.setFont(new Font("Dialog", Font.BOLD, 16));
		gSurname.setBounds(20, 100, 120, 25);
		gurdianpanel.add(gSurname);
		
		gsuranmeTextfield = new JTextField();
		gsuranmeTextfield.setBackground(new Color(255, 255, 255));
		gsuranmeTextfield.setEditable(false);
		gsuranmeTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		gsuranmeTextfield.setBounds(160, 100, 120, 25);
		gurdianpanel.add(gsuranmeTextfield);
		gsuranmeTextfield.setColumns(10);
		
		JLabel gPhoneNumberLabel = new JLabel("Phone No");
		gPhoneNumberLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		gPhoneNumberLabel.setBounds(20, 140, 120, 25);
		gurdianpanel.add(gPhoneNumberLabel);
		
		phoneTextfield = new JTextField();
		phoneTextfield.setBackground(new Color(255, 255, 255));
		phoneTextfield.setEditable(false);
		phoneTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		phoneTextfield.setBounds(160, 140, 120, 25);
		gurdianpanel.add(phoneTextfield);
		phoneTextfield.setColumns(10);
		
		
		JLabel gIdLabel = new JLabel("G ID Number");
		gIdLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		gIdLabel.setBounds(20, 180, 120, 25);
		gurdianpanel.add(gIdLabel);
		
		gIdTextfield = new JTextField();
		gIdTextfield .setBackground(new Color(255, 255, 255));
		gIdTextfield .setEditable(false);
		gIdTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		gIdTextfield.setBounds(160, 180, 120, 25);
		gurdianpanel.add(gIdTextfield);
		gIdTextfield.setColumns(10);
		
		JLabel gResidencelabel = new JLabel("Residence ");
		gResidencelabel.setFont(new Font("Dialog", Font.BOLD, 16));
		gResidencelabel.setBounds(20, 220, 120, 25);
		gurdianpanel.add(gResidencelabel);
		
		residenceTextfield = new JTextField();
		residenceTextfield .setBackground(new Color(255, 255, 255));
		residenceTextfield .setEditable(false);
		residenceTextfield.setFont(new Font("Dialog", Font.PLAIN, 16));
		residenceTextfield.setBounds(160, 220, 120, 25);
		gurdianpanel.add(residenceTextfield);
		residenceTextfield.setColumns(10);
		
		
		
		histroypanel = new JPanel();
		histroypanel.setBorder(new LineBorder(new Color(0, 255, 0), 1, true));
		histroypanel.setBackground(new Color(245, 255, 250));
		histroypanel.setBounds(360, 20, 420, 260);
		
		
		  model.setColumnIdentifiers(columnNames);
			 
		  table  = new JTable(){
			  public boolean isCellEditable(int row, int column){
			        return false; }
			  
		  };
		  table.setFont(new Font("Dialog", Font.BOLD, 14));
		  table.setModel(model);
		  table.setShowGrid(true);
		  
		  TableColumnModel  tcm = table.getColumnModel();
		  
		  JTableHeader header = table.getTableHeader();
		    header.setPreferredSize(new Dimension(100, 25));
		  
		  tcm.getColumn(0).setPreferredWidth(100);
		  table.setRowHeight(30);
		
		  
		  
			
		
		
		histroypanel.setLayout(null);
		
		JLabel datelabel = new JLabel("Date");
		datelabel.setFont(new Font("Bitstream Vera Serif", Font.BOLD | Font.ITALIC, 16));
		datelabel.setBounds(220, 10, 60, 25);
		histroypanel.add(datelabel);
		
		 Date dNow = new Date( );
	     SimpleDateFormat ft = new SimpleDateFormat ("E dd MMMM yyyy ");
		 String date=ft.format(dNow);
		 
		datejtextField = new JTextField();
		datejtextField.setFont(new Font("Dialog", Font.BOLD, 16));
		datejtextField.setBorder(null);
		datejtextField.setBackground(new Color(245, 255, 250));
		datejtextField.setBounds(280, 10, 127, 25);
		datejtextField.setText(date);
		histroypanel.add(datejtextField);
		datejtextField.setColumns(10);
		
		JLabel messageLabel = new JLabel("Select History For Previous Classes");
		messageLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		messageLabel.setBounds(20, 180, 300, 25);
		histroypanel.add(messageLabel);
		
		defaultJtextFiled = new JTextField();
		defaultJtextFiled.setBorder(null);
		defaultJtextFiled.setBackground(new Color(245, 255, 250));
		defaultJtextFiled.setForeground(new Color(0, 0, 255));
		defaultJtextFiled.setFont(new Font("Dialog", Font.BOLD, 12));
		defaultJtextFiled.setBounds(310, 160, 100, 20);
		histroypanel.add(defaultJtextFiled);
		defaultJtextFiled.setColumns(10);
		
		txtYear = new JTextField();
		txtYear.setBorder(null);
		txtYear.setFont(new Font("Dialog", Font.BOLD, 14));
		txtYear.setBackground(new Color(245, 255, 250));
		txtYear.setBounds(230, 210, 114, 25);
		histroypanel.add(txtYear);
		txtYear.setColumns(10);
		
		
		
		classesJcombobox = new JComboBox(cd.classesSubscribed());
		classesJcombobox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(classesJcombobox.getSelectedItem().toString()!=null){
				String y = classesJcombobox.getSelectedItem().toString();
				int year =cd.getYearId(n.getClassID(y));
				data(y, year);
				 }
			}
		});
		classesJcombobox.setBounds(20, 210, 200, 25);
		histroypanel.add(classesJcombobox);
		JScrollPane scrollPane = new JScrollPane(table);
		//scrollPane.setViewportBorder(null);
		scrollPane.setForeground(new Color(245, 255, 250));
		scrollPane.setBackground(new Color(245, 255, 250));
		scrollPane.setBorder(null);
		scrollPane.setBounds(10, 40, 400, 120);
		histroypanel.add(scrollPane);
		
		spf.getContentPane().add(histroypanel);
		
		buttonspanel = new JPanel();
		
		buttonspanel.setBorder(new LineBorder(new Color(0, 255, 0), 1, true));
		buttonspanel.setBackground(new Color(245, 255, 250));
		buttonspanel.setBounds(360, 300, 420, 265);
		buttonspanel.setLayout(null);
		
		
		selectionPanel = new JPanel();
		
		selectionPanel.setBorder(new LineBorder(new Color(0, 255, 0), 1, true));
		selectionPanel.setBackground(new Color(245, 255, 250));
		selectionPanel.setBounds(360, 300, 420, 265);
		selectionPanel.setLayout(null);
		
		JLabel studentaccountlabel = new JLabel("STUDENT ACCOUNT INFORMATION");
		studentaccountlabel.setHorizontalAlignment(SwingConstants.CENTER);
		studentaccountlabel.setFont(new Font("Dialog", Font.PLAIN, 13));
		studentaccountlabel.setBounds(20, 10, 350, 25);
		buttonspanel.add(studentaccountlabel);
		
		JLabel creditAccountLabel = new JLabel("CREDIT  ACCOUNT");
		creditAccountLabel.setForeground(new Color(0, 0, 205));
		creditAccountLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		creditAccountLabel.setBounds(45, 35, 180, 25);
		buttonspanel.add(creditAccountLabel);
		
		JLabel balanceAccountLabel = new JLabel("BALANCE ACCOUNT");
		balanceAccountLabel.setForeground(new Color(255, 0, 0));
		balanceAccountLabel.setFont(new Font("Dialog", Font.BOLD, 14));
		balanceAccountLabel.setBounds(230, 35, 160, 25);
		buttonspanel.add(balanceAccountLabel);
		
		creditAccJtextField = new JTextField();
		creditAccJtextField.setForeground(new Color(0, 255, 0));
		creditAccJtextField.setFont(new Font("Dialog", Font.BOLD, 16));
		creditAccJtextField.setHorizontalAlignment(SwingConstants.CENTER);
		creditAccJtextField.setBackground(new Color(255, 255, 255));
		creditAccJtextField.setEditable(false);
		creditAccJtextField.setBounds(90, 60, 90, 25);
		creditAccJtextField.setColumns(10);
		
		buttonspanel.add(creditAccJtextField);
		
		
		balanceJtextField = new JTextField();
		balanceJtextField.setHorizontalAlignment(SwingConstants.CENTER);
		balanceJtextField.setForeground(new Color(255, 0, 0));
		balanceJtextField.setFont(new Font("Dialog", Font.BOLD, 16));
		balanceJtextField.setBackground(new Color(255, 255, 255));
		balanceJtextField.setEditable(false);
		balanceJtextField.setBounds(260, 60, 90, 25);
		balanceJtextField.setColumns(10);
		buttonspanel.add(balanceJtextField);
		
		
		clearbalance = new JButton("Clear Balances");
		clearbalance.setFont(new Font("Dialog", Font.BOLD, 16));
		clearbalance.setHorizontalAlignment(SwingConstants.LEFT);
		clearbalance.setBounds(25, 140, 170, 25);
		
		clearbalance.addActionListener(this);
		
		info = new JTextField();
		info.setBorder(null);
		info.setEditable(false);
		info.setBackground(new Color(245, 255, 250));
		info.setForeground(Color.GREEN);
		info.setFont(new Font("Dialog", Font.BOLD, 17));
		info.setBounds(25, 95, 385, 30);
		buttonspanel.add(info);
		info.setColumns(10);
		
		buttonspanel.add(clearbalance);
		
		payfeesButton = new JButton("Pay Term Fees");
		payfeesButton.setFont(new Font("Dialog", Font.BOLD, 16));
		payfeesButton.addActionListener(this);
		
		payfeesButton.setBounds(220, 140, 170, 25);
		buttonspanel.add(payfeesButton);
		
		backButton = new JButton("Back");
		backButton.setFont(new Font("Dialog", Font.BOLD, 16));
		backButton.addActionListener(this);
		
		promoteNextClass = new JButton("Promote Next Class");
		promoteNextClass.setFont(new Font("Dialog", Font.BOLD, 16));
		promoteNextClass.setBounds(140, 180, 250, 25);
		promoteNextClass.addActionListener(this);
		
		promoteNextClass.setEnabled(false);
		
		buttonspanel.add(promoteNextClass);
		
		label = new JTextField();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 16));
		label.setBackground(new Color(255, 255, 255));
		label.setBounds(25, 180, 100, 27);
		buttonspanel.add(label);
		label.setColumns(10);
		
		backButton.setBounds(25, 225, 130, 25);
		buttonspanel.add(backButton);
		
		cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Dialog", Font.BOLD, 16));
		cancelButton .setBounds(260, 225, 130, 25);
		cancelButton.addActionListener(this);
		
		buttonspanel.add(cancelButton);
		spf.getContentPane().add(buttonspanel);
		
		
		clearBalanceJCheck = new JCheckBox("Clear Balances Only");
		clearBalanceJCheck.addItemListener(new ChooseWhatToDo(this));
		
		clearBalanceJCheck.setBackground(new Color(245, 255, 250));
		clearBalanceJCheck.setForeground(new Color(0, 0, 205));
		clearBalanceJCheck.setFont(new Font("Dialog", Font.BOLD, 14));
		clearBalanceJCheck.setBounds(40, 50, 300, 25);
		selectionPanel.add(clearBalanceJCheck);
		
		clearBalanceAndNextTermJcheck = new JCheckBox("Pay Balance and Next Term");
		clearBalanceAndNextTermJcheck.addItemListener(new ChooseWhatToDo(this));
		
		clearBalanceAndNextTermJcheck.setBackground(new Color(245, 255, 250));
		clearBalanceAndNextTermJcheck.setForeground(new Color(0, 0, 205));
		clearBalanceAndNextTermJcheck.setFont(new Font("Dialog", Font.BOLD, 14));
		clearBalanceAndNextTermJcheck.setBounds(40, 100, 300, 25);
		selectionPanel.add(clearBalanceAndNextTermJcheck);
	
		
		payOnlyJcheckbox = new JCheckBox("Pay Next Term");
		payOnlyJcheckbox.addItemListener(new ChooseWhatToDo(this));
		
		payOnlyJcheckbox.setBackground(new Color(245, 255, 250));
		payOnlyJcheckbox.setForeground(new Color(0, 0, 205));
		payOnlyJcheckbox.setFont(new Font("Dialog", Font.BOLD, 14));
		payOnlyJcheckbox.setBounds(40, 150, 300, 25);
		
		gr = new ButtonGroup();
		gr.add(clearBalanceJCheck);
		gr.add(clearBalanceAndNextTermJcheck);
		gr.add(payOnlyJcheckbox);
		selectionPanel.add(payOnlyJcheckbox);
		
		
	
		
		spf.setResizable(false);
		spf.setVisible(true);
		
		dataSource(id);
		//data();
	}
	
	 private void data(String clas,int yr){
		
		 sd = new SourceTableData(clas,yr);
		 sd.setData();
		 
		 
		 if(yr==n.getYearId()){
			 defaultJtextFiled.setText("Current Year");
				txtYear.setText("Year  "+n.getYear(n.getYearId()));
		 }else{
			 defaultJtextFiled.setText(""); 
			 txtYear.setText("Year  "+n.getYear(yr));
		 }
	 }
	
	
	 private class SourceTableData{
		 String feesConfigured,mode,status,balance= null;
		 int year,class_id,termID=0;
	
		 
		 
		public SourceTableData(String className,int yr){
			
			this.year=yr;
			this.class_id =n.getClassID(className);
			
		}
		private void setData(){
			 model.setRowCount(0);
			 String term[]={"TERM ONE","TERM TWO","TERM THREE"};
	
			 for(int i=0;i<3;i++){
				 this.termID = n.getTermID(term[i]);
				 fc=new FeeConfiguration(id,year, class_id, termID);
				 setFeesConfigured();
				 setFeesMode();
				 setFeesStatus();
				 setFeesBalance();
				 
				 model.addRow(new Object[]{term[i],getFeesConfigured(), getFeesMode(), getFeesStatus(),getFeesBalance()});
			 }
			 System.out.println();
		
		}
		
		
		public String getFeesConfigured(){
			return feesConfigured;
		}
		public String getFeesMode(){
			return mode;
		}
		
		public String getFeesStatus(){
			return status;
		}
		public String getFeesBalance(){
			return balance;
		}
		private void setFeesConfigured(){
			if(fc.getFeesTermAmount()<=0){
				this.feesConfigured="";
				
			}else{
				this.feesConfigured=Double.toString(fc.getFeesTermAmount());
			}
			
		}
		private void setFeesMode(){
			this.mode=fc.getFeesMode();
					
		}
		private void setFeesStatus(){
			this.status=fc.getStatus();
					
		}
		private void setFeesBalance(){
				if(fc.getBalance()<=0){
					balance="";
				}else{
					balance=Double.toString(fc.getBalance());
				}
					
		}
	 }
	 
	 
    @Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==clearbalance){
			
			BalancesFinder bf = new BalancesFinder();
			
			if (bf.getAllBalancesCumulative(id)<=0){
				info.setForeground(Color.GREEN);
				info.setText("No Outstanding Balances ");
			}else{
				info.setForeground(Color.RED);
				info.setText("The Outstanding Balance is "+bf.getAllBalancesCumulative(id));
				AmountInputUi amountsUi = new AmountInputUi();
				amountsUi.clearBalances(id,"clearAllBalances",this);
			}
		}else if(e.getSource()==backButton){
			back();
					
		}else if(e.getSource()==promoteNextClass){
				if(ccs.getClassesComboItems(id).length<=0){
					info.setForeground(Color.GREEN);
					info.setText("No Further Class To Subscribe TO ");
			
				}else{
				
					ChooseClasssToSubscribe selectClass = new ChooseClasssToSubscribe(this);
					selectClass.chooseClasss(id, className, studentName);
				}
		}else if(e.getSource()== payfeesButton){
				pay();	
			
		}else if (e.getSource()==cancelButton){
			spf.dispose();
			@SuppressWarnings("unused")
			MainInterface main = new MainInterface();
		}
		
	}

    private void pay(){
    	
    	if(bf.getAllBalancesCumulative(id)<=0){
    		promoteStudentFirst();
        	
    	}else{
    		info.setForeground(Color.RED);
    		info.setText("Clear Outstanding Balance of "+bf.getAllBalancesCumulative(id));
    		
    		buttonspanel.remove(cancelButton);
			spf.getContentPane().remove(buttonspanel);
			selectionPanel.add(cancelButton);
			cancelButton.setBounds(260, 210, 120, 25);
			spf.getContentPane().add(selectionPanel);
			spf.getContentPane().repaint();
			spf.getContentPane().revalidate();
    			 
    	}
    	
    	
    }
    ///////////////Inner Class
    
    private class ChooseWhatToDo implements ItemListener{
		StudentPlofile sp=null;
	
		
		public ChooseWhatToDo(StudentPlofile p){
			 this.sp=p;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			
			if(e.getSource()==clearBalanceJCheck){
				
				if(clearBalanceJCheck.isSelected()){
					//-------------------------------------------------------------------
					AmountInputUi amountsUi = new AmountInputUi();
		  			amountsUi.clearBalances(id,"clearAllBalances",sp);
		  			//------------------------------------------------------------------
					returnNormal();
				}
				
				
			}else if(e.getSource()==clearBalanceAndNextTermJcheck){
				
				if(clearBalanceAndNextTermJcheck.isSelected()){
				//-------------------------------------------
				
						if(year!=n.getYearId()){
				  			promoteStudentFirst();
				
			  			}else{
			  		 	ChooseTermPayer chooseTermPlayer = new ChooseTermPayer();
			  		 	chooseTermPlayer.ChooseTermUi(id,studentName,className,sp);
			  			}
				
				//--------------------------------------------
				
				returnNormal();
				}
				
			}else if(e.getSource()==payOnlyJcheckbox){
				
				if(payOnlyJcheckbox.isSelected()){
				//----------------------------------------------------------------------------
				
						if(year!=n.getYearId()){
			  				//-----------------------------------------------------
					  				if(ccs.getClassesComboItems(id).length<=0){
					  					if(bf.getAllBalancesCumulative(id)>0){
					  						payOldBalancesOption();
					  					}else{
					  						info.setForeground(Color.GREEN);
					  						info.setText("No Further Class To Subscribe TO ");
					  						
					  					}
					  				}
					  		//----------------------------------------------------------------	
					  				else {
					  					promoteStudentFirst();
					  				}
			  				
	  				}else {
	  					ChooseTermPayer ctp = new ChooseTermPayer();
	    			  	ctp.ChooseTermUi(id,studentName,className,sp);
	  			
	  				}
				
				
				//----------------------------------------------------------------------------
				returnNormal();
				}
			}
		
		}
		private void returnNormal(){
			gr.clearSelection();
			selectionPanel.remove(cancelButton);
			spf.getContentPane().remove(selectionPanel);
			buttonspanel.add(cancelButton);
			cancelButton .setBounds(260, 225, 130, 25);
			
			spf.getContentPane().add(buttonspanel);
			spf.getContentPane().repaint();
			spf.getContentPane().revalidate();
		}
		
    }
    ////End of Inner Class
    
    
    
    private void promoteStudentFirst(){
    	
    	
    	
    	if(ccs.getClassesComboItems(id).length<=0){
			payOldBalancesOption();
			
		}else{
			//---------------------------------------------------------
			if(year!=n.getYearId()){
				info.setForeground(Color.RED);
				info.setText("Please Subscribe to Next Class First !!!");
				ccs.chooseClasss(id, className, studentName);}
			else{
				ChooseTermPayer ctp = new ChooseTermPayer();
			  	ctp.ChooseTermUi(id,studentName,className,this);
			}
			//----------------------------------------------------
			
		}
    	
    	
    }
    private void payOldBalancesOption(){
    	
    //-------------------------------------------------------------------
	    	if(bf.getAllBalancesCumulative(id)>0){
	    	
	    	 String[] choices = {"PAY OLD BALANCES","CANCEL"};
	    	  int response  = JOptionPane.showOptionDialog(null, studentName+ " Has no Further Classes to Subscribe \nOnly Pay Old Balances", 
							  "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, choices, "CANCEL");
					  switch(response){
					  
					  	case 0:	AmountInputUi amountsUi = new AmountInputUi();
			  					amountsUi.clearBalances(id,"clearAllBalances",this);
						  break;
						  
					  	case 1:
					  		break;
					  	default:
					  		break;
					  }
	    	}
    	//--------------------------------------------------------------------
	    	else{

	    		//---------------------------------------------------------
				if(year!=n.getYearId()){
					info.setForeground(Color.GREEN);
					//info.setFont(new Font("Dialog", Font.BOLD, 17));
					info.setText("No Balance and Can't Subscribe Further");
					}
				else{
					ChooseTermPayer ctp = new ChooseTermPayer();
				  	ctp.ChooseTermUi(id,studentName,className,this);
				}
				//----------------------------------------------------
	    	
	    	}
    }
    
	private void back(){
    	spf.dispose();
		SearchStudent ss = new SearchStudent();
		ss.refresh(lastQueried);
		ss.findjText.setText(lastQueried);
    }
	
	public void refresher(int student_ID){
		this.id=student_ID;
		this.year=n.getLastClassSubscriptionYear(id);
		dataSource(id);
	}
	
	
	public void dataSource(int x){
		this.className = n.getName(getCurrentClass(id));
		
		 if(year!=n.getYearId()){
			 label.setForeground(new Color(255, 0, 0));
			 label.setText("IN ACTIVE");
			 promoteNextClass.setEnabled(true);
			 
			 
			 data(className, cd.getYearId(n.getClassID(className)));
			 
		 }else if(year==n.getYearId()){
			 label.setForeground(new Color(0, 255, 0));
			 label.setText("ACTIVE");
			 promoteNextClass.setEnabled(false);
			 
			 defaultJtextFiled.setText("Current Year");
			 txtYear.setText("Year  "+n.getYear(n.getYearId()));
			 sd = new SourceTableData(className,n.getYearId());
			 sd.setData();
		 }
		
			try {
				
				 this.dbConnection = (Connection) DbConnector.getDBConnection();
				 this.st = (Statement) dbConnection.createStatement();
				 String sql ="select First_Name,Middle_Name,Surname from Person where Person_ID = (select Person_ID from Student where Student_ID =" + x + ")";
	
			
				 this.rs=st.executeQuery(sql);
			     rs.next();
				 this.first =rs.getString("First_Name");
				 this.middle =rs.getString("Middle_Name");
				 this.last =rs.getString("Surname");
				 this.studentName  = first+" "+middle+" "+last;
		}
		
		catch(SQLException e){ e.printStackTrace();}
		finally{try{dbConnection.close();} catch(SQLException e){}
		}
		
	     getStudentIndex(id);
		 fnameTextfield.setText(first);
		 mNameTextfield.setText(middle);
		 suranmeTextfield.setText(last);
		 
		 classTextfield.setText(className);
			
		 getGurdianNames(id);
		 getGuardianContacts(id);
		 getAccountDetails(id);
		
		
	}
	
	
	private void getStudentIndex(int x){
		String idex = null;
		String sql ="select Student_INDEX from Student where Student_ID = " + x ;
		try {
			this.dbConnection = (Connection) DbConnector.getDBConnection();
			this.st = (Statement) dbConnection.createStatement();
			
			this.rs=st.executeQuery(sql);
			rs.next();
			idex =rs.getString("Student_INDEX");
		     }
		catch(SQLException e){ e.printStackTrace();}
		finally{try{dbConnection.close();} catch(SQLException e){}
		
		}
	
		 studentJTextfield.setText(idex);
		
		
	}

	private void getAccountDetails(int x){
		
		String sql ="select Student_Credit_Acc,Student_Balance_Acc from StudentAccount where Student_ID= " + x ;
		try {
			this.dbConnection = (Connection) DbConnector.getDBConnection();
			this.st = (Statement) dbConnection.createStatement();
			
			this.rs=st.executeQuery(sql);
			rs.next();
			 
			creditAcc = Double.toString(rs.getDouble("Student_Credit_Acc"));
			balAccount = Double.toString(rs.getDouble("Student_Balance_Acc"));
				
		     }
		catch(SQLException e){ e.printStackTrace();}
		finally{try{dbConnection.close();} catch(SQLException e){}
		
		}
		creditAccJtextField.setText(creditAcc);
		balanceJtextField.setText(balAccount);
		
	}
	
	private int getCurrentClass(int x){
		 int currentclass = 0;
		 int yr =this.year;
		 String sql = "select Class_Id from Class_Subscription where Yr_Id =" +yr+ " and Student_ID =" +x;
		
		 try {
			 this.dbConnection = (Connection) DbConnector.getDBConnection();
			 this.st = (Statement) dbConnection.createStatement();
			
			 this.rs=st.executeQuery(sql);
			 rs.next();
			 currentclass = rs.getInt("Class_Id");
		} catch (SQLException e) {e.printStackTrace();}
		 finally{try{dbConnection.close();} catch(SQLException e){}
		 
		 }
		 
		return currentclass;
	 }
 
	private void getGurdianNames(int x){
			 String sql = "select First_Name,Middle_Name,Surname from Person where Person_ID = (select Person_ID from Guardian where Guardian_ID = (select Guardian_ID from Student where Student_ID ="+ x + "))";
			
			 try {
				 this.dbConnection = (Connection) DbConnector.getDBConnection();
				 this.st = (Statement) dbConnection.createStatement();
				
				 this.rs=st.executeQuery(sql);
				 rs.next();
				 gfirst =rs.getString("First_Name");
				 gmiddle =rs.getString("Middle_Name");
				 glast =rs.getString("Surname");
		
			} catch (SQLException e) {e.printStackTrace();}
			 finally{try{dbConnection.close();} catch(SQLException e){}
			 
			 }
		
		 gfnameTextfield.setText(gfirst);
		 gmNameTextfield.setText(gmiddle);
		 gsuranmeTextfield.setText(glast);
	 }

	private void getGuardianContacts(int x){
		String sql = "select Guardian_ID_No,Guardian_Phone,Guardian_Residence from Guardian_Contact where Guardian_ID = (select Guardian_ID from Student where Student_ID ="+ x + ")";
			
			 try {
				 this.dbConnection = (Connection) DbConnector.getDBConnection();
				 this.st = (Statement) dbConnection.createStatement();
				
				 this.rs=st.executeQuery(sql);
				  rs.next();
				  gId =rs.getString("Guardian_ID_No");
				  gphone =rs.getString("Guardian_Phone");
				  gRecidence =rs.getString("Guardian_Residence");
		
			} catch (SQLException e) {e.printStackTrace();}
			 finally{try{dbConnection.close();} catch(SQLException e){}
			 }
		
		 phoneTextfield.setText(gphone);
		 gIdTextfield.setText(gId);
		 residenceTextfield.setText(gRecidence);
		 
	 }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void updateJcombo(int id){
		 ClassesData cd = new ClassesData(id);
		 classesJcombobox = new JComboBox(cd.classesSubscribed());
	}
	
	private class ClassesData{
		private Connection dbConnection = null;
		private Statement stmt  = null;
		private ResultSet  rs = null;
		int studentID =0;
		
		public ClassesData(int student_Id){
			this.studentID=student_Id;
		
		}
		
		 public String [] classesSubscribed(){
			 ArrayList<Integer> classes=new ArrayList<Integer>();
				classes = n.getClassesSubscribed(studentID);
				
				Set<Integer> set = new HashSet<Integer>(classes);
				 ArrayList<Integer> list=new ArrayList<Integer>();
				 
				 list.addAll(set);
				 Collections.sort(list,Collections.reverseOrder());
				 String []clas =new String[list.size()];
				 
				 for(int i=0;i<list.size();i++){
						clas[i]=n.getName(list.get(i));
					
					}
				 
			return clas;
		 }
		 
		 public int getYearId(int classid){
				// This Method Find the Status of Individual Term Payment
			 int yearID=0;
					try{
							this.dbConnection = (Connection) DbConnector.getDBConnection();
							
						
							String sql="select Yr_ID from Class_Subscription where  Student_ID ="+studentID+" and Class_ID ="+classid;
							
							this.stmt =(Statement) dbConnection.createStatement();
							this.rs= (ResultSet) stmt.executeQuery(sql);
							
							rs.next();
							yearID=rs.getInt("Yr_ID");
					}catch (SQLException e){} 
					finally{try{dbConnection.close();} catch(SQLException e){}}
					return yearID;
			}
		 
		 
		 
	}
}



	  

