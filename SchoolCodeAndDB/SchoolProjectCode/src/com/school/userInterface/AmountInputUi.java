package com.school.userInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import com.school.db.BalancesFinder;
import javax.swing.SwingConstants;
import java.awt.Font;


public class AmountInputUi extends Thread implements ActionListener{
	private JButton Submit,cancel ;
	private JTextField amount;
	private  JFrame f;
	private JPanel p;
	private int value,wait =0;
	private int id ,clas,term=0;
	private String state= null;
	public Thread thread= null;
	private StudentPlofile sp = null;
	private ChooseTermPayer cp= null;
	private FeesStructureUi fs= null;
	private JTextField infoJtext;
	
	public AmountInputUi() {
	}
	
	public void AmountPasser(int student_ID,String state){
		this.id = student_ID;
		this.state =state;
		
		
		f = new JFrame("Amount");
		p = new JPanel();
		
		
		p.setLayout(null);
		JLabel l = new JLabel("Enter Amount");
		l.setBounds(70, 20, 100, 25);
		p.add(l);
		
		amount = new JTextField();
		amount.setHorizontalAlignment(SwingConstants.CENTER);
		
		amount.setBounds(70, 50, 100, 25);
		amount.setDocument(new JTextFieldLimit(5));
		
		//validation
		((AbstractDocument)amount.getDocument()).setDocumentFilter(
	             new IntegerValidator());
		

	
		
		p.add(amount);
		
		Submit  = new JButton("Submit");
		Submit.setBounds(20, 120, 100, 25);
		Submit.addActionListener(this);
		infoJtext = new JTextField();
		infoJtext.setForeground(new Color(0, 0, 205));
		infoJtext.setFont(new Font("Dialog", Font.BOLD, 15));
		infoJtext.setBounds(20, 80, 230, 25);
		infoJtext.setBorder(null);
		
		
		p.add(infoJtext);
		infoJtext.setColumns(10);
		
		
		p.add(Submit);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		cancel.setBounds(130, 120, 100, 25);
	
		p.add(cancel);
		
		f.getContentPane().add(p);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setLocation(550,350);
		
		//f.setLocation(100,100);
		f.setSize(250, 150);
		f.setResizable(false);
		f.setBackground(new Color(245, 255, 250));
		f.setVisible(true);	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		// submit Action----------------------------------------------------------
		if(e.getSource()==Submit){
			
				if(amount.getText().length()==0){
					setAmount(0);
					
				}else if(amount.getText().length()>0){
					
							if(Integer.parseInt(amount.getText())>0){
								
											//-----------------------------------------------------------
										
										int confirm = JOptionPane.showConfirmDialog(null, "Confirm Amount "+amount.getText(),"Confirm Amount",JOptionPane.YES_NO_OPTION);
										if (confirm ==0){
											setAmount(Integer.parseInt(amount.getText()));
											submitAmountFunctionToDB();
											f.dispose();
												
										}
										else {
											amount.setText("");
											
										    }
											//------------------------------------------------------------------
								
								}else if(Integer.parseInt(amount.getText())<=0){
									JOptionPane.showMessageDialog(null, "ENTER A VALID AMOUNT");
									amount.setText("");
									
								}
				}
				
		}// end of Submit Action ------------------------------------------
		
		if(e.getSource()==cancel){
			f.dispose();
		}
		
	}
	private void setAmount(int newValue){
		this.value=newValue;
	}
	
	private int getAmount(){
		return value;
	}
	
	private void submitAmountFunctionToDB(){
		
		
		switch (state){
		
		case "clearAllBalances":
			BalancesFinder bf = new BalancesFinder();
			bf.clearBalances(id, getAmount());
			sp.info.setText("");
			sp.dataSource(id);
			break;
			
		case "payBalanceAndNextTerm":
			payingBalances();
			break;

		case "clearAllBalancesB":
			BalancesFinder bfB = new BalancesFinder();
			bfB.clearBalances(id, getAmount());
			sp.info.setText("");
			sp.dataSource(id);
			
			cp.ct.dispose();
			break;
			
		case "justPayfeeStructure":
			this.wait=1;
			break;
			
		case "viewNormalFeeStructure":
			this.wait=2;
			break;
			
			
		default:break;
	
		}
	
	}
	
	public void clearBalances(int student_ID,String status,StudentPlofile profile){
		this.sp=profile;
		AmountPasser(student_ID, status);
		f.setTitle("Student Cash");
		p.setBackground(new Color(224, 255, 255));
		infoJtext.setText("Enter Student Fees Amount");
		infoJtext.setBackground(new Color(224, 255, 255));
	}
	
	public	void setFeeStructureModeUpdate(int CLASS,int TERM,String STATE,FeesStructureUi feesStructure,StudentPlofile profile){
		this.sp=profile;
		this.clas=CLASS;
		this.term=TERM;
		this.fs=feesStructure;
		this.thread=new Thread(new ThreadProcess(fs));
		
		AmountPasser(id, STATE);
		f.setTitle("Fees Structure Amount");
		p.setBackground(new Color(238, 130, 238));
		infoJtext.setText("Fees Structure Amount");
		infoJtext.setBackground(new Color(238, 130, 238));
		thread.start();
		
	}
	public	void setFeeStructureMode(int CLASS,int TERM,String STATE,FeesStructureUi feesStructure){
		this.clas=CLASS;
		this.term=TERM;
		this.fs=feesStructure;
		this.thread=new Thread(new ThreadProcess(fs));
		
		AmountPasser(id, STATE);
		f.setTitle("Fees Structure Amount");
		p.setBackground(new Color(238, 130, 238));
		infoJtext.setText("Fees Structure Amount");
		infoJtext.setBackground(new Color(238, 130, 238));
		thread.start();
		
	}
	
	public void studentTermFeePayment(int student_ID,int classID,int termID,String status,StudentPlofile profile,ChooseTermPayer chooseTerm){
	
		this.clas=classID;
		this.term=termID;
		this.sp=profile;
		this.cp=chooseTerm;
		
		
	
		AmountPasser(student_ID, status);	
		f.setTitle("Student Cash");
		p.setBackground(new Color(224, 255, 255));
		infoJtext.setText("Enter Student Fees Amount");
		infoJtext.setBackground(new Color(224, 255, 255));
	}
	
	private void payingBalances(){
		
		BalancesFinder bf = new BalancesFinder();
		ChooseTermPayer ct=new ChooseTermPayer();
		
		if(getAmount()>bf.getAllBalancesCumulative(id)){
			
			double balance =bf.getAllBalancesCumulative(id);
			double extraCash  =getAmount()-balance;
			
			bf.clearBalances(id, (int)balance);
			ct.feeCalculator(id, extraCash, clas, term);
			cp.ct.dispose();
			sp.info.setText("");
			sp.dataSource(id);
			
			
			
		}else if(getAmount()<=bf.getAllBalancesCumulative(id)){
			bf.clearBalances(id, getAmount());
			cp.ct.dispose();
			sp.info.setText("");
			sp.dataSource(id);
			
		}
		
	}
	
	private int getClassID(){
		return clas;
	}
	private int getTermID(){
		return term;
	}
	
	private class ThreadProcess implements Runnable{
		public ThreadProcess(FeesStructureUi feesStructure){
			fs=feesStructure;
		}
		@SuppressWarnings("deprecation")
		@Override
		public void run() {
		
			while(true){
				switch(wait){
				case 0:
					break;
					
				case 1:
					fs.setAmount(getClassID(), getTermID(), getAmount());
					fs.f.dispose();
					thread.stop();
					break;
					
				case 2:
					
					fs.setAmount(getClassID(), getTermID(), getAmount());
					fs.refreshData();
					thread.stop();
					break;
					
				default:
					thread.stop();
					break;
				}
			
		}
		
		}
		
	}
}
