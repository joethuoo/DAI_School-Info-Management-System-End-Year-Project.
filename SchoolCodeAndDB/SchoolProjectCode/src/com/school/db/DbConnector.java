package com.school.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.mysql.jdbc.PreparedStatement;

public class DbConnector {

	static final String driver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/SchoolDB";
	static final String user = "root";
	static final String pwd =  "@pp0line";
	
	String sql;
  
   
   private Connection dbConnection = null;
   private Statement st = null;
   private ResultSet rs = null;
   private PreparedStatement ps  = null;
	
	public DbConnector(){
		 configureYear();
	}
	
	public static Connection getDBConnection() {
		 
		Connection dbConnection = null;
 
		try {
 
			Class.forName(driver);
 
		} catch (ClassNotFoundException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		try {
 
			dbConnection = DriverManager.getConnection(url, user,
					pwd);
			return dbConnection;
 
		} catch (SQLException e) {
 
			System.out.println(e.getMessage());
 
		}
 
		return dbConnection;
 
	}
		
	public  int getClassID(String className) {
	
		 int classID = 0;
	    	if(className.equals("BABY CLASS")){
	    		classID=1;
	    	
	    	}else if(className.equals("PRE UNIT")){
	    		classID=2;
	    		
	    	}else if(className.equals("NURSERY")){
	    		classID=3;
	    		
	    	}else if(className.equals("STD ONE")){
	    		classID=4;
	    		
	    	}else if(className.equals("STD TWO")){
	    		classID=5;
	    		
	    	}else if(className.equals("STD THREE")){
	    		classID=6;
	    		
	    	}else if(className.equals("STD FOUR")){
	    		classID=7;
	    		
	    	}else if(className.equals("STD FIVE")){
	    		classID=8;
	    		
	    	}else if(className.equals("STD SIX")){
	    		classID=9;
	    		
	    	}else if(className.equals("STD SEVEN")){
	    		classID=10;
	    		
	    	}else if(className.equals("STD EIGHT")){
	    		classID=11;
	    		
	    	}else{classID=0;}
	    	
	    	return classID;
		
		
		}
	
	public String getName(int classID) {
		 String className = null;
		 
	    	if(classID==1){
	    		className= "BABY CLASS";
	    		
	    	}else if(classID==2){
	    		className= "PRE UNIT";
	    		
	    	}else if(classID==3){
	    		className= "NURSERY";
	    		
	    	}else if(classID==4){
	    		className= "STD ONE";
	    		
	    	}else if(classID==5){
	    		className= "STD TWO";
	    		
	    	}else if(classID==6){
	    		className= "STD THREE";
	    		
	    	}else if(classID==7){
	    		className= "STD FOUR";
	    		
	    	}else if(classID==8){
	    		className= "STD FIVE";
	    		
	    	}else if(classID==9){
	    		className= "STD SIX";
	    		
	    	}else if(classID==10){
	    		className= "STD SEVEN";
	    		
	    	}else if(classID==11){
	    		className= "STD EIGHT";
	    	}
	    	
	    	
	    	return className;
	    }
	
	public ArrayList<String> classes(){
		ArrayList<String>classes = new ArrayList<String>();
		classes.add("BABY CLASS");
		classes.add("PRE UNIT");
		classes.add("NURSERY");
		classes.add("STD ONE");
		classes.add("STD TWO");
		classes.add("STD THREE");
		classes.add("STD FOUR");
		classes.add("STD FIVE");
		classes.add("STD SIX");
		classes.add("STD SEVEN");
		classes.add("STD EIGHT");
		
		
		return classes;
	}
	public  int getTermID(String TERM) {
		  int term =0;
				if(TERM.equals("TERM ONE"))
				{
					term =1;
				}
				else if(TERM.equals("TERM TWO"))
				{
					term =2;
				}
				else if(TERM.equals("TERM THREE"))
				{
					term =3;
				}
			
			
			return term;
		}
	
	 void configureAcademicYear(int currentYear){  
		
    	try {
    		this.dbConnection = getDBConnection();
    		String sql = "Insert into Academic_YEAR (YEAR) values(?)";
    		this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
    		
    		ps.setInt(1, currentYear);
    		ps.executeUpdate();	
		   } catch (Exception e) {System.out.println("Error " +e.getMessage());}
    	     finally{try{dbConnection.close();} catch(SQLException e){}
    	     }
	   }
	

	private int academicYearLastRowId(){
		   int last= 0;
		   try {
				this.dbConnection = getDBConnection();
				this.st = dbConnection.createStatement();
				String sql = "select * from Academic_YEAR";
			
				this.rs=st.executeQuery(sql);
				
				rs.last();
				last = rs.getInt("Yr_ID");
				
		   }  catch (SQLException e) {e.printStackTrace();}
		      finally{try{dbConnection.close();} catch(SQLException e){}
		   }
		  return last;
	   }
	
	
	private int academicYearTableLastRow(){
		//This method returns the last value of the year configured in Academic Table
		   int validator= 0;
		   try {
				this.dbConnection = getDBConnection();
				this.st = dbConnection.createStatement();
				String sql = "select * from Academic_YEAR";
			
				this.rs=st.executeQuery(sql);
					
					if(rs.last()== true){
					rs.getInt("Yr_ID");
					validator= 1;
					}
					else{
					validator= 0;
					//System.out.println("Value of Last is " +last);
					}
		} catch (SQLException e) {e.printStackTrace();}
		  finally{try{dbConnection.close();} catch(SQLException e){}}
		  return validator;
	   }
	
	private int []findExactYear(){
			int [] noOfYears =new  int[academicYearLastRowId()];
		    try {
		    	int i =0;
		    	this.dbConnection = getDBConnection();
				this.st = dbConnection.createStatement();
				String sql = "select * from Academic_YEAR";
		    	
				this.rs=st.executeQuery(sql);
							while(rs.next()){
						      rs.getInt("Yr_ID");
						      int  yearFound  = rs.getInt("YEAR");
						      noOfYears [i]=yearFound;
						      i++;
							  }
							
		    } catch (SQLException e) {	e.printStackTrace();}
		    finally{try{dbConnection.close();} catch(SQLException e){}
		    
		    }
			return noOfYears;
		}
	  
	private int compare(){
		  int currentYear = year();
		  int foundOrNot=0; 
		  for (int i=0; i<academicYearLastRowId();i++){
			  
				 if(currentYear==findExactYear()[i]){
					 foundOrNot +=1;
			      return foundOrNot;
				 }
		  }
		  
		return foundOrNot;
	  }
	  
	public int year(){
		Calendar cal=Calendar.getInstance();
		int currentyear=cal.get(Calendar.YEAR); 
		return currentyear;
	}
	
	public int date(){
		Calendar cal=Calendar.getInstance();
		int date=cal.get(Calendar.YEAR);
		return date;
	}
	  
	public void configureYear(){
		 if(academicYearTableLastRow()==0){
			 configureAcademicYear(year());
			 
		 }
		 else if(compare()==0){
			 configureAcademicYear(year());
		
		 }
	}
	
	public int getYearId(){
		   int y= year();
		   String yn = Integer.toString(y);
		   int yearId =0;
		   try {
			   this.dbConnection = getDBConnection();
			   this.st = dbConnection.createStatement();
			   String sql = "select Yr_ID from Academic_YEAR where Year ="+ yn;
			   
			   this.rs=st.executeQuery(sql);
					   while ( rs.next() ) {
			                yearId = rs.getInt("Yr_ID");
			            }
					   
		} catch (SQLException e) {e.printStackTrace();}
		  finally{try{dbConnection.close();} catch(SQLException e){}
		  
		  }
		  
		  return yearId;
	}
	
	
	public int getYear(int YEAR_ID){
		 
		   int year=0;
		   try {
			   this.dbConnection = getDBConnection();
			   this.st = dbConnection.createStatement();
			   String sql = "select YEAR from Academic_YEAR where Yr_ID ="+ YEAR_ID;
			   
			   this.rs=st.executeQuery(sql);
					   while ( rs.next() ) {
			                year = rs.getInt("YEAR");
			            }
					   
		} catch (SQLException e) {e.printStackTrace();}
		  finally{try{dbConnection.close();} catch(SQLException e){}
		  
		  }
		  
		  return year;
	}
	
	
	
	public ArrayList<Integer> getYearsSubscribed(int STUDENT){
		 
		   
		   ArrayList<Integer> list = new ArrayList<Integer>();
		   try {
			   this.dbConnection = getDBConnection();
			   this.st = dbConnection.createStatement();
			   String sql = "select Yr_ID from Class_Subscription where Student_ID ="+ STUDENT;
			   
			   this.rs=st.executeQuery(sql);
					   while ( rs.next() ) {
						   
			                list.add(rs.getInt("Yr_ID"));
			            }
					   
		} catch (SQLException e) {e.printStackTrace();}
		  finally{try{dbConnection.close();} catch(SQLException e){}
		  }
		   Collections.sort(list);
		
		  return list;
	}

	
	public ArrayList<Integer> getClassesSubscribed(int STUDENT){

		   ArrayList<Integer> list = new ArrayList<Integer>();
		   try {
			   this.dbConnection = getDBConnection();
			   this.st = dbConnection.createStatement();
			   String sql = "select Class_ID from Class_Subscription where Student_ID ="+ STUDENT;
			   
			   this.rs=st.executeQuery(sql);
					   while ( rs.next() ) {
			                list.add(rs.getInt("Class_ID"));
			             
			            }
					   
		} catch (SQLException e) {e.printStackTrace();}
		  finally{try{dbConnection.close();} catch(SQLException e){}
		  }
		   Collections.sort(list);		
		  return list;
	}
	
	
	public boolean login(String username,String password){
		//This Method Validates the Password
		 String usname = null;
		 String pwd = null;
		 try {
		   this.dbConnection = getDBConnection();
		   this.st = dbConnection.createStatement();
		   String sql = "select * from Login where Staff_ID = 1";
		   this.rs=st.executeQuery(sql);
		   
				if(rs.next()==true){
				      usname  = rs.getString("Username");
				      pwd  = rs.getString("password");
							      
						 if(usname.equals(username)&& pwd.equals(password)){
							return true;}
						 else{ return false;}
				     }
				else {return false;}
				      
				    } catch (SQLException e) {e.printStackTrace();}
		              finally{try{dbConnection.close();} catch(SQLException e){}
		              
		              }
	    return true;
	}
	
	public void indexGeneratorConfigurator(){
		   try {
				this.dbConnection = getDBConnection();
				dbConnection.setAutoCommit(false);
				
				indexGenerator();
				
				dbConnection.commit();
				 
		   }catch (SQLException e){try {dbConnection.rollback();
		   } catch (SQLException e1) {System.out.println(e1.getMessage());}}
			finally{try{dbConnection.close();} catch(SQLException e){}}
		  
		
	   }
	
	
	public int checkIndexGenerator(){
		//This method Checks Whether Index Generstor is Configured 
		   int validator= 0;
		   try {
				this.dbConnection = getDBConnection();
				this.st = dbConnection.createStatement();
				String sql = "select * from Index_Generator";
			
				this.rs=st.executeQuery(sql);
					
					if(rs.last()== true){
						validator= 1;
					}
					else{
					validator= 0;
					}
		} catch (SQLException e) {e.printStackTrace();}
		  finally{try{dbConnection.close();} catch(SQLException e){}}
		   
		  return validator;
	   }
	
	
	

	public int getLastClassSubscriptionYear(int STUDENT){
		//This method Checks Whether the Last Class Subscribed;
		   int max= 0;
		   
		   try {
				this.dbConnection = (Connection) DbConnector.getDBConnection();
				this.st = (Statement) dbConnection.createStatement();
				
				String sql = "SELECT MAX(Yr_ID) from Class_Subscription where Student_ID ="+STUDENT;
			
				this.rs=st.executeQuery(sql);
					
					if(rs.next()){
					max= rs.getInt("MAX(Yr_ID)");
					}else{
						max=0;
					}
					
					
		} catch (SQLException e) {e.printStackTrace();}
		  finally{try{dbConnection.close();} catch(SQLException e){}}
		   
		  return max;
	   }
	
	public int getFirstClassSubscription(int STUDENT){
		//This method Checks Whether the Last Class Subscribed;
		   int min= 0;
		   
		   try {
				this.dbConnection = (Connection) DbConnector.getDBConnection();
				this.st = (Statement) dbConnection.createStatement();
				
				String sql = "SELECT MIN(Yr_ID) from Class_Subscription where Student_ID ="+STUDENT;
			
				this.rs=st.executeQuery(sql);
					
					rs.next();
					min= rs.getInt("MIN(Yr_ID)");
					
					
		} catch (SQLException e) {e.printStackTrace();}
		  finally{try{dbConnection.close();} catch(SQLException e){}}
		   
		  return min;
	   }
	
	
	 private PreparedStatement indexGenerator() throws SQLException{ 
			//This Method Registers The Values  of contacts Related to Guardian Into the Contacts Table
			 
				String sql ="insert into Index_Generator (Auto_id,Person_ID,Student_ID,Guardian_ID) values (?,?,?,?)";
				this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
				ps.setInt(1, 1);
				ps.setInt(2, 1);
				ps.setInt(3, 1);
				ps.setInt(4, 1);
				ps.executeUpdate();
				
				return ps;	
			}	
	
	 
	 
	 
	 
	
	}


