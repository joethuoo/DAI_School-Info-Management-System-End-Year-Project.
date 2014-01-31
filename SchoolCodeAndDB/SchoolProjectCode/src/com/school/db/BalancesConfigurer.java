package com.school.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class BalancesConfigurer {
	
	 Connection dbConnection = null;
	 Statement st = null;
	 ResultSet rs = null;
	 
	 ArrayList<Integer> list = null;
	 ArrayList<Double> amounts = null;
	 double credit= 0;
	 
	 public BalancesConfigurer(int id){
		 getBalancesUniqueID(id);
		 setStudentCredit(id);
	 }
	 
	  private void setYearsUniqueId(ArrayList<Integer> l){
		  this.list = l;
	  }
	  private void setBalancesAmount(ArrayList<Double> l){
		  this.amounts = l;
	  }
	  public  ArrayList<Integer> getYearsUniqueId(){
		  return list;
	  }
	  public ArrayList<Double> getBalancesAmount(){
		  return amounts;
	  }
	  private void setStudentCredit(int id){
		  this.credit= creditGetter(id);
	  }
	  public double getsetStudentCredit(){
		  return credit;
	  }
	  
 private void getBalancesUniqueID(int STUDENT){

	   list = new ArrayList<Integer>();
	   amounts = new ArrayList<Double>();
	   
	   try {
		   this.dbConnection=(Connection) DbConnector.getDBConnection();
		   this.st = (Statement) dbConnection.createStatement();
		   
		   String sql = "select val_id, Student_ID,Status,Amount from Fee_Validator where Status ='BALANCE' AND  Student_ID ="+STUDENT;
		   
		   this.rs=st.executeQuery(sql);
				   while ( rs.next() ) {
		                list.add(rs.getInt("val_id"));
		                amounts.add(rs.getDouble("Amount"));
		             
		            }
				   
	} catch (SQLException e) {e.printStackTrace();}
	  finally{try{dbConnection.close();} catch(SQLException e){}
	  }
	   Collections.sort(list);
	   setYearsUniqueId(list);
	   setBalancesAmount(amounts);
	  		
}

 public double creditGetter(int studdent_ID){
  	double getCredit =0;
  	this.dbConnection = (Connection) DbConnector.getDBConnection();
  	try{
		    	this.st=(Statement) dbConnection.createStatement();
		    	String sql = "select * from StudentAccount where Student_ID ="+studdent_ID;
		    	
		    	this.rs= (ResultSet) st.executeQuery(sql);
		    	rs.next();
		    	getCredit = rs.getDouble("Student_Credit_Acc");
		    	
  	
		    	//System.out.println("Credit Amount is :"+ getCredit);
	    	} catch (SQLException e) { System.out.println(e.getMessage());
	        } finally{try{dbConnection.close();} catch(SQLException e){}}
	    	
  	return getCredit;
  }
 
}

