package com.school.userInterface;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.school.db.DbConnector;
import javax.swing.border.LineBorder;



public class FeesStructureUi implements ActionListener{
	
	private DbConnector n = new DbConnector();
	@SuppressWarnings("rawtypes")
	JComboBox classJcombo, termJcombo;
	public JTable tbl;
	private StudentPlofile sp;
	private Connection dbConnection = null;
    private Statement st = null;
	private ResultSet rs = null;
	public SourceData sd=new SourceData();

	private String classes[]= {"BABY CLASS", "PRE UNIT", "NURSERY","STD ONE",
			"STD TWO","STD THREE","STD FOUR","STD FIVE","STD SIX","STD SEVEN","STD EIGHT"};
	public JFrame f;
	private JButton ConfigureJbutton,backJbutton,cancelJbutton;
	private JTextField yearJtextfield;
	private TableColumnModel tcm;
	private String viewMode= null;
	
	public FeesStructureUi(String mode) {
		this.viewMode=mode;
		
	}
	
	public void profileUpdate(StudentPlofile profile){
		this.sp=profile;
		FeesStructureUinterface();
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void FeesStructureUinterface(){
		
		f = new JFrame();
		f.getContentPane().setBackground(new Color(245, 255, 250));
		f.setTitle(" Fees Structure");
		f.setSize(550, 550);
		f.setLocation(40, 180);
		f.setResizable(false);
		f.getContentPane().setLayout(null);
		
		JLabel descriptionLabel = new JLabel("Choose Class To Configure Fees");
		descriptionLabel.setFont(new Font("Dialog", Font.BOLD, 16));
		descriptionLabel.setBounds(50, 10, 300, 25);
		f.getContentPane().add(descriptionLabel);
		
		JLabel yearLabel = new JLabel("Year");
		yearLabel.setForeground(Color.BLUE);
		yearLabel.setBounds(50, 40, 60, 15);
		f.getContentPane().add(yearLabel);
		
		yearJtextfield = new JTextField();
		yearJtextfield.setFont(new Font("Dialog", Font.BOLD, 16));
		yearJtextfield.setBackground(new Color(255, 255, 255));
		yearJtextfield.setEditable(false);
		yearJtextfield.setBounds(50, 55, 50, 25);
		f.getContentPane().add(yearJtextfield);
		yearJtextfield.setColumns(10);
		
		JLabel classJlabel = new JLabel("Class");
		classJlabel.setForeground(Color.BLUE);
		classJlabel.setBounds(120, 40, 70, 15);
		f.getContentPane().add(classJlabel);
		
		classJcombo = new JComboBox();
		classJcombo.setModel(new DefaultComboBoxModel(classes));
		
		classJcombo.setBackground(Color.white);
		classJcombo.setBounds(120, 55, 110, 25);
		f.getContentPane().add(classJcombo);
		
		JLabel termJlabel = new JLabel("Term");
		termJlabel.setForeground(Color.BLUE);
		termJlabel.setBounds(250, 40, 70, 15);
		f.getContentPane().add(termJlabel);
		
		termJcombo = new JComboBox();
		termJcombo.setModel(new DefaultComboBoxModel(new String[] {"TERM ONE", "TERM TWO", "TERM THREE"}));
		termJcombo.setBackground(Color.white);
		termJcombo.setBounds(250, 55, 110, 25);
		f.getContentPane().add(termJcombo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 255), 2, true));
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(50, 90, 470, 380);
		panel.setLayout(null);
		
		
		
		tbl = new JTable(sd.updateData());
		tcm = tbl.getColumnModel();
	  
	    
	    JTableHeader header = tbl.getTableHeader();
	    header.setPreferredSize(new Dimension(100, 25));
	    header.setFont(new Font("Dialog", Font.BOLD, 16));
	    header.setForeground(Color.BLUE);
	    header.setBackground(new Color(192, 192, 192));
	    
		tcm.getColumn(0).setPreferredWidth(115);
		tcm.getColumn(1).setPreferredWidth(110);
		tcm.getColumn(2).setPreferredWidth(110);
		tcm.getColumn(3).setPreferredWidth(110);
		tbl.setRowHeight(30);
		tbl.setFont(new Font("Dialog", Font.BOLD, 12));
		
		    
		    

	    tbl.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tbl.setPreferredScrollableViewportSize(tbl.getPreferredSize());

		JScrollPane sp = new JScrollPane(tbl);
		sp.setLocation(10, 10);
		sp.setSize(450, 360);
		
		panel.add(sp);
		
		
		f.getContentPane().add(panel);
		
		
		backJbutton = new JButton("Back");
		backJbutton.addActionListener(this);
		backJbutton.setBounds(160, 500, 100, 25);
		f.getContentPane().add(backJbutton);
		
		cancelJbutton = new JButton("Cancel");
		
		cancelJbutton.addActionListener(this);
		cancelJbutton.setBounds(300, 500, 100, 25);
		f.getContentPane().add(cancelJbutton);
		
		ConfigureJbutton = new JButton("Configure");
		ConfigureJbutton.setBorder(new LineBorder(new Color(0, 100, 0), 1, true));
	
		ConfigureJbutton.setFont(new Font("Dialog", Font.BOLD, 14));
		
		
		ConfigureJbutton.addActionListener(this);
		ConfigureJbutton.setBounds(380, 55, 123, 25);
		f.getContentPane().add(ConfigureJbutton);
		
		
		f.setVisible(true);
		sourceData();
	
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ConfigureJbutton){
			
			if(getFeesDoubleEntry()==false){
		
				String clas =(String) classJcombo.getSelectedItem();
				String term =(String) termJcombo.getSelectedItem();
				AmountInputUi amountInput = new AmountInputUi();
				//--------------------------------------------------------------------------
				if(viewMode.equals("normal")){
				amountInput.setFeeStructureMode(n.getClassID(clas), n.getTermID(term), "viewNormalFeeStructure",this);
				
				}else if(viewMode.equals("passAsYouPay")){
					amountInput.setFeeStructureModeUpdate(n.getClassID(clas), n.getTermID(term), "justPayfeeStructure",this,sp);
				}
				//----------------------------------------------------------------------------
			
				
			} else{
				JOptionPane.showMessageDialog(null, "THIS HAS ALREADY BEEN CONFIGURED");
			}
			
		}else if(e.getSource()==backJbutton){
			f.dispose();
			new MainInterface();
			
		}else if(e.getSource()==cancelJbutton){
			f.dispose();
			new MainInterface();
		}
		
	}
	

	
	public  void   setAmount(int CLASS_ID,int TERM_ID,int AMOUNT){
	
		submitAmount("Normal",CLASS_ID,TERM_ID,AMOUNT);
		
	}
	public void refreshData(){
		SourceData s=new SourceData();
		tbl = new JTable(s.updateData());
	}
	private void sourceData(){
		yearJtextfield.setText(Integer.toString(n.year()));
		
	 }
	
	
	 public boolean getFeesDoubleEntry(){
		 
		int cl = n.getClassID((String)classJcombo.getSelectedItem());
		int tm = n.getTermID((String)termJcombo.getSelectedItem());
		int yr = n.getYearId();
		
		
		  
		   
		   try {
			   this.dbConnection = (Connection) DbConnector.getDBConnection();
				this.st = (Statement) dbConnection.createStatement();
				String sql = "select * from Fee_Structure where Yr_Id ="+yr+" and Class_Id ="+cl+" and Term_Id = "+tm;
			    this.rs=st.executeQuery(sql);
					
			    if(rs.next()==true){
						return true;
					}else {
						return false;
					}
					
		       } catch (SQLException e) {e.printStackTrace();}
		return true;
		 
	   }
	 
//-------------------------------INNER CLASS
@SuppressWarnings("serial")
private class SourceData extends AbstractTableModel {
	
	private Connection dbConnection = null;
	private Statement st = null;
	private ResultSet rs = null;
	
	protected String[] columnNames = { "Class", "Term One", "Term Two",
    "Term Three" };

	DbConnector n = new DbConnector();
	public Object data[][]= new Object[11][4];
	
	public SourceData (){
		//dataSourcer();
	}
// Implementation of TableModel interface
	public int getRowCount() {
	  return data.length;
	}
	
	public int getColumnCount() {
	  return columnNames.length;
	}

	public Object getValueAt(int row, int column) {
	  return data[row][column];
	}
	
	
	
	public String getColumnName(int column) {
	  return columnNames[column];
	}

	public TableModel updateData(){
		dataSourcer();
		return sd;
	}
	  
	  Object dataSourcer(){
			int yr =n.getYearId();
			//this.data = new Object[3][4];
			
			
			//Inserting Values from The Database into the Array
				for(int i =1,f=0;i<=11;i++,f++){
					
					for(int e =1;e<=3;e++){
				     String sql = "select FeesAmount from Fee_Structure where Yr_ID = "+yr+" and Class_Id ="+i+" and Term_Id = "+e;
				     	
				           if (amountGeter(sql)==0){ data[f][e]=null; }
				           else{data[f][e]=amountGeter(sql);}
					     }
				   }
				
				
				for(int y =0,z=0;y<11;y++){
					 data[y][z]=classes[y];
				}
				
				return data;	
		}
	  
	  
	  int amountGeter(String sql){
		     int amount=0;
				try{
					this.dbConnection = (Connection) DbConnector.getDBConnection();
					this.st = (Statement) dbConnection.createStatement();
				    this.rs=st.executeQuery(sql);
				    
					if(rs.next()){
					amount=(int) rs.getDouble("FeesAmount");
					}else {
						
					}
					
				}catch (SQLException e ){}
				 finally{try{dbConnection.close();} catch(SQLException e){}}
				return amount;
			}
	  
	} //------------------------------------END OF Inner Class
	
	
	private void submitAmount(String mode,int clas,int term,double Amount){
		
			DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd");
			Date day = new Date();
			String date= dateFormat.format(day);
			PreparedStatement ps =null;
			try{
				
		    this.dbConnection=(Connection) DbConnector.getDBConnection();
		    String sql ="insert into Fee_Structure(Fees_Mode ,Yr_ID,Class_Id,Term_Id,FeesAmount,Entry_Time) values (?,?,?,?,?,?)";
		    
			
			ps=(PreparedStatement) dbConnection.prepareStatement(sql);
			ps.setString(1, mode);
			ps.setInt(2, n.getYearId());
			ps.setInt(3, clas);
			ps.setInt(4, term);
			ps.setDouble(5, Amount);
			ps.setString(6, date);
			ps.executeUpdate();
		
			}catch(SQLException e){e.printStackTrace();}
		    finally{try{dbConnection.close();} catch(SQLException e){}}
		
	}

	
	
}
