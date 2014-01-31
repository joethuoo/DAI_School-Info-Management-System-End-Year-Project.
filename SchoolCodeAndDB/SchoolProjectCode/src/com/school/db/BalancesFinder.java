package com.school.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class BalancesFinder {
	
	private Connection dbConnection = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement ps  = null;
	private DbConnector n  = new DbConnector();

	public BalancesFinder() {
	}	
	
	public void clearBalances(int id,int CASH){
		
	 BalancesConfigurer bc = new BalancesConfigurer(id);
	 
	 int yr =n.getYearId();
	 double totalBalance =getAllBalancesCumulative(id);	 
	 double oldCredit = bc.getsetStudentCredit();
	 double cashReceived =CASH;
	 double cash = cashReceived+oldCredit;
	 double calculated = cash;
	 double amountCommited =0;
	 
	 try{
			
		 this.dbConnection=(Connection) DbConnector.getDBConnection();
	
		  dbConnection.setAutoCommit(false);
		 
	 			for(int x= 0;x<bc.getYearsUniqueId().size();x++){
			 
						 if(cash>0){
							 cash-=bc.getBalancesAmount().get(x);
							 calculated = calculated-bc.getBalancesAmount().get(x);
						 
											//-----------------------------------------------------------------------
											 	if(calculated>0){
											 		 amountCommited += bc.getBalancesAmount().get(x);
											 		 
											 		
											 	}else if(calculated<=0){
											 		amountCommited += bc.getBalancesAmount().get(x)-(-calculated);
											 		
											 	}
											 //----------------------------------------------------------------------
											 	
									calculated=cash;
									//--------------------------------------------------------
									if(calculated>=0){
										payBalances("PAID", 0, bc.getYearsUniqueId().get(x));
										
									}else if(calculated<0){
										payBalances("BALANCE", calculated-calculated-calculated, bc.getYearsUniqueId().get(x));
									}
									//--------------------------------------------------------
								 if(cash<=0){
									 break;
								 }
								
				 		}
			
	 			}  // end of for loop
		 //---------------------------------------------------------------
		 double finalBalance =totalBalance-amountCommited;
		 
		//--------------------------------------------------------------------------------------------		 
				 if(calculated>0){
					studentAccount(calculated, 0, id);
				 }else if(calculated==0){
					 
					 studentAccount(calculated, finalBalance, id);
				 }else if(calculated<0){
					studentAccount(0, finalBalance, id);
				 }
		//----------------------------------------------------------------------------------------------	
					 if (cashReceived<=0){
					 }else{
						 paymentsHistory(id, yr, cashReceived);
					 }
		 
	 dbConnection.commit();
	 
	 } catch (SQLException e) { System.out.println(e.getMessage());
	      try {dbConnection.rollback();} catch (SQLException e1) { System.out.println(e1.getMessage());}
	      
	 }finally{try{dbConnection.close();} catch(SQLException e){}}
			 
	}
	
	public double getAllBalancesCumulative(int id){
		//Finds ALL OUTSTANDIzA	NG Balances of All Previous Years
		double totalBalance=0;
		
			 ArrayList<Integer> yearsSubscribed = new ArrayList<Integer>();
			 yearsSubscribed=n.getYearsSubscribed(id);
			 
			 for(int i= 0;i<yearsSubscribed.size();i++){
				for(int x= 1;x<=3;x++){
					String sql="SELECT Amount from Fee_Validator where Status ='BALANCE' and Student_ID ="+id+" and Yr_ID = "+yearsSubscribed.get(i)+" and Term_Id= "+x;
					totalBalance+=findAllPreviousBalances(sql);
				}
			 }
		return totalBalance;
	}
	  
	private double findAllPreviousBalances(String sql){
		 double balanceAmaount =0;
		try{
			 this.dbConnection=(Connection) DbConnector.getDBConnection();
			 this.st = (Statement) dbConnection.createStatement();
			 this.rs=st.executeQuery(sql);
			
			 if(rs.next()){
			 balanceAmaount =rs.getDouble("Amount");
			 }	else{
				 balanceAmaount =0;
			 }
			 
	        } catch (SQLException e) { System.out.println(e.getMessage());
              }finally{try{dbConnection.close();} catch(SQLException e){}
            
            }
		return balanceAmaount;
	}
	 
	public void classSubscribe(int STUDENT,int YEAR, int CLASS){
		
		try{
			
			 this.dbConnection=(Connection) DbConnector.getDBConnection();
		
			 dbConnection.setAutoCommit(false);
			 
			 String sql ="insert into Class_Subscription (Student_ID,Yr_ID,Class_ID) values (?,?,?)";
			 this.ps= (PreparedStatement) dbConnection.prepareStatement(sql);
		      
		     ps.setInt(1, STUDENT);  
		     ps.setInt(2, YEAR); 
		     ps.setInt(3, CLASS);  
		    
			 ps.executeUpdate();
		
			 dbConnection.commit();
			 
	        } catch (SQLException e) { System.out.println(e.getMessage());
	          try {dbConnection.rollback();} catch (SQLException e1) { System.out.println(e1.getMessage());}
              }finally{try{dbConnection.close();} catch(SQLException e){}
            
            }
	}

	
	private  PreparedStatement payBalances(String STATUS ,double balance, int val_id) throws SQLException{ 
			//This Prepared Statement posts Record to Student Account Table;
		    
		     
				String sql ="update Fee_Validator set Status =?,Amount =? where val_id = ?";
				ps=(PreparedStatement) dbConnection.prepareStatement(sql);
				ps.setString(1, STATUS);
				ps.setDouble(2, balance);
				ps.setInt(3, val_id);
				ps.executeUpdate();
				
				return ps;	
			}

	private PreparedStatement studentAccount(double credit ,double balance, int studentid) throws SQLException{ 
			//This Prepared Statement posts Record to Student Account Table;
		    
		     
				String sql ="update StudentAccount set Student_Credit_Acc =?,Student_Balance_Acc =? where Student_ID = ?";
				this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
				ps.setDouble(1, credit);
				ps.setDouble(2, balance);
				ps.setInt(3, studentid);
				ps.executeUpdate();
				
				return ps;	
			}
	 
	 
	private PreparedStatement paymentsHistory(int studentID,int YEAR,double AMOUNT) throws SQLException{ 
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


