package com.school.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class FeeConfiguration{
	private int year,classID,termID=0;
	private String feesMode,status= null;
	private double amount,balance= 0;
	private Connection dbConnection = null;
	private Statement stmt  = null;
	private ResultSet  rs = null;
	
	public FeeConfiguration(int studentID,int year, int classID,int termID){
		this.year=year;
		this.classID=classID;
		this.termID=termID;
		
		getPayMentStatus(studentID, this.year, this.classID, this.termID);
	}
	
	private void setFeesTermAmount(double amount) {
		this.amount=amount;
	}
	private void setFeesMode(String feesMode) {
		this.feesMode = feesMode;
	}
	
	private void setFeesTermStatus(String status) {
		this.status = status;
	}
	
	private void setFeesBalanceAmount(double balance) {
		this.balance = balance;
	}
	
	public String getFeesMode() {
		return feesMode;
	}
	public String getStatus() {
		return status;
	}
	public double getBalance() {
		return balance;
	}
	public double getFeesTermAmount() {
		return amount;
	}
	
	private void getPayMentStatus(int studentID,int year,int classID,int termID){
		// This Method Find the Status of Individual Term Payment
			try{
					this.dbConnection = (Connection) DbConnector.getDBConnection();
					
					
					String sql="select FeesAmount,Fees_Mode, Status, Amount from Fee_Validator,Fee_Structure where Fee_Structure.FeesStructureId = Fee_Validator.FeesStructureId "
							+ "and Student_ID="+studentID+"  and Fee_Validator.Yr_ID ="+year+" AND Fee_Validator.Class_Id="+classID+" and  Fee_Validator.Term_ID  ="+termID;
					
					this.stmt =(Statement) dbConnection.createStatement();
					this.rs= (ResultSet) stmt.executeQuery(sql);
					
					if(rs.next()){
					
					setFeesTermAmount(rs.getDouble("FeesAmount"));
					setFeesMode(rs.getString("Fees_Mode"));
					setFeesTermStatus(rs.getString("Status"));
					setFeesBalanceAmount(rs.getDouble("Amount"));
					
					
					}else{
						
					}
					
			}catch (SQLException e){} 
			finally{try{dbConnection.close();} catch(SQLException e){}}
		
		
	}
	
}
 
