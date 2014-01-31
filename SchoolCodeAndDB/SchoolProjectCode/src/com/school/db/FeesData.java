package com.school.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class FeesData{
	private Connection dbConnection = null;
	private Statement stmt  = null;
	private ResultSet  rs = null;
	private int feeStructureID,year,classID,termID=0;
	private double amount= 0;
	
	public FeesData(int year, int classID,int termID){
		this.year=year;
		this.classID=classID;
		this.termID=termID;
		getDetails(this.year, this.classID, this.termID);
	}
	
	private void getDetails(int YEAR,int CLASS,int TERM){
		// this Method Finds the Amount of the fee configured in the Fee structure Against the Term required
			try{
					this.dbConnection = (Connection) DbConnector.getDBConnection();
					String sql ="select FeesStructureId,Fees_Mode,FeesAmount from Fee_Structure where Yr_ID=" + YEAR+" and Class_Id ="+CLASS+" and Term_Id="+TERM;
					this.stmt =(Statement) dbConnection.createStatement();
					this.rs= (ResultSet) stmt.executeQuery(sql);
					if(rs.next()){
					
					setFeesTermAmount(rs.getDouble("FeesAmount"));
					setFeeStructureID(rs.getInt("FeesStructureId"));
					}else{
						setFeesTermAmount(0);
						setFeeStructureID(0);
					}
					
			}catch (SQLException e){}
			finally{try{dbConnection.close();} catch(SQLException e){}}
		
		
	}
	
	
	public double getFeesTermAmount() {
		return amount;
	}
	public int getFeeStructureID() {
		return feeStructureID;
	}
	
	private void setFeesTermAmount(double amount) {
		this.amount=amount;
	}
	
	private void setFeeStructureID(int feeStructureID) {
		this.feeStructureID = feeStructureID;
	}
	
}

