package com.school.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Register {
	
	private  Connection dbConnection = null;
	private PreparedStatement ps = null;
	private Statement st =  null;
	private ResultSet rs =  null;
	private DbConnector n  = new DbConnector();
	private Students newstudent;
	private Guardian newguardian;
	private String className=  null;
	 
	 public Register( String Students_fname ,String Students_mname,String Students_Sname,String Students_geder
				,String Students_className,String Guardian_fname,String Guardian_mname,String Guardian_sname,
				String Guardian_geder,String Guardian_IDnO,String Guardian_phone,String Guardian_residence){
		 	this.className= Students_className;
		 	
			newstudent = new Students();
			newstudent.setFirstName(Students_fname);
			newstudent.setMiddleName(Students_mname);
			newstudent.setSurnameName(Students_Sname);
			newstudent.setGeder(Students_geder);
			
			newguardian = new Guardian();
			newguardian.setFirstName(Guardian_fname);
			newguardian.setMiddleName(Guardian_mname);
			newguardian.setSurnameName(Guardian_sname);
			newguardian.setGeder(Guardian_geder);
			newguardian.setIdNumber(Guardian_IDnO);
			newguardian.setPhoneNumber(Guardian_phone);
			newguardian.setResidence(Guardian_residence); 
	 }
		
		public void submit (){	 
			 if(n.checkIndexGenerator()==0){
				n.indexGeneratorConfigurator();
			 }
		 
		 try{
	
		 this.dbConnection=(Connection) DbConnector.getDBConnection();
		 this.st =(Statement) dbConnection.createStatement();
		 String sql ="Select Person_ID,Student_ID,Guardian_ID from Index_Generator where Auto_id = 1";
		 
		 this.rs = st.executeQuery(sql);
		 rs.next();
		 int personID =rs.getInt("Person_ID");
		 int studentID =rs.getInt("Student_ID");
		 int guardianID =rs.getInt("Guardian_ID");
		 
		
		 
		 
		 final int studentPersonId = personID;
		 final int guardianPersonId = personID+1;
		 final int studentUniqueId = studentID;
		 final int guardianUniqueId = guardianID;
		 final String studentIndex =preceedingZeros(studentUniqueId, 4);
		 
		 
		 System.out.println(studentIndex);
		
		
		 
		
			 dbConnection.setAutoCommit(false);
			 
			 persons(studentPersonId, newstudent.getFirstName(), newstudent.getMiddleName(), newstudent.getSurnameName(),
					 newstudent.getGeder(), newstudent.getStudentsPersonDescription());
				 
			 
			 persons(guardianPersonId, newguardian.getFirstName(), newguardian.getMiddleName(), newguardian.getSurnameName(),
					 newguardian.getGeder(), newguardian.getGuardianPersonDescription());
		
			 guardianRegister(guardianUniqueId, guardianPersonId);
			 
			 contactsRegister(guardianUniqueId, newguardian.getIdNumber(), newguardian.getPhone(), newguardian.getResidence());
			 
			 studentRegister(studentUniqueId, studentIndex, studentPersonId, guardianUniqueId);
			 
			 classSubscription(studentUniqueId, n.getYearId(), n.getClassID(className));
			 
			 studentAccountCreator(studentUniqueId);
			 
			 indexGeneratorUpdater(guardianPersonId+1, studentUniqueId+1, guardianUniqueId+1);
			 
			 dbConnection.commit();
			 JOptionPane.showMessageDialog(null, "Student Registered Succesfully");
			 
			 
	        } catch (SQLException e) { System.out.println(e.getMessage());
	          try {dbConnection.rollback();} catch (SQLException e1) { System.out.println(e1.getMessage());}
              }finally{try{dbConnection.close();} catch(SQLException e){}
            
            }
		 }
	 
	 
	 protected  String preceedingZeros(int num, int digits) {
	        String output = Integer.toString(num);
	        while (output.length() < digits) output = "0" + output;
	        String index =  "KRS-A-"+output;
	        return index;
	    }
	  
	 
	 protected PreparedStatement persons(int person_id,String fname,String mname,String surname,int geder,int personDesc) throws SQLException{
		
		String sql ="insert into Person (Person_ID,First_Name, Middle_Name,Surname,Gender_ID,Per_Desc_ID) values (?,?,?,?,?,?)";
		
		
		this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
		ps.setInt(1, person_id);
		ps.setString(2, fname);
		ps.setString(3, mname);
		ps.setString(4, surname);
		ps.setInt(5, geder);
		ps.setInt(6, personDesc);
		ps.executeUpdate();
		ps.getLastInsertID();
		return ps;
	}
	
	 protected PreparedStatement studentRegister(int studentId,String Student_Idex,int person_ID, int guardian_id) throws SQLException{ 
		//This Method Registers The Value of Persons ID related to Student to the Database in the Guardian Table
		 
			String sql ="insert into Student (Student_ID,Student_INDEX,Person_ID,Guardian_ID) values (?,?,?,?)";
			this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
			ps.setInt(1, studentId);
			ps.setString(2, Student_Idex);
			ps.setInt(3, person_ID);
			ps.setInt(4, guardian_id);
			ps.executeUpdate();
			return ps;	
		}
		
	 protected PreparedStatement guardianRegister(int guardian_id, int person_Id) throws SQLException{ 
			//This Method Registers The Value of Persons ID related to Guardian to the Database in the Guardian Table
			 
				String sql ="insert into Guardian (Guardian_ID,Person_ID) values (?,?)";
				this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
				ps.setInt(1, guardian_id);
				ps.setInt(2, person_Id);
				ps.executeUpdate();
				
				return ps;	
			}
	
	 protected PreparedStatement contactsRegister(int guardianid,String guardianIDNO,String guardianPhone,String guardianResidence) throws SQLException{ 
			//This Method Registers The Values  of contacts Related to Guardian Into the Contacts Table
			 
				String sql ="insert into Guardian_Contact (Guardian_ID,Guardian_ID_No,Guardian_Phone,Guardian_Residence) values (?,?,?,?)";
				this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
				ps.setInt(1, guardianid);
				ps.setString(2, guardianIDNO);
				ps.setString(3, guardianPhone);
				ps.setString(4, guardianResidence);
				ps.executeUpdate();
				
				return ps;	
			}
	
  protected PreparedStatement classSubscription(int studentID,int yearID,int classID) throws SQLException{ 
			//This Method Registers The Values  of contacts Related to Guardian Into the Contacts Table
			 
				String sql ="insert into Class_Subscription (Student_ID,Yr_ID,Class_ID) values (?,?,?)";
				this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
				ps.setInt(1, studentID);
				ps.setInt(2, yearID);
				ps.setInt(3, classID);
				ps.executeUpdate();
				
				return ps;	
			}
	 
	 protected PreparedStatement studentAccountCreator(int studentID) throws SQLException{ 
			//This Method Registers The Values  of contacts Related to Guardian Into the Contacts Table
			 
				String sql ="insert into StudentAccount (Student_ID,Student_Credit_Acc,Student_Balance_Acc) values (?,?,?)";
				this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
				ps.setInt(1, studentID);
				ps.setDouble(2, 0);
				ps.setDouble(3, 0);
				ps.executeUpdate();
				
				return ps;	
			}	
	 
	 protected PreparedStatement indexGeneratorUpdater(int personid ,int studentid,int gurdianid) throws SQLException{ 
			//This Method Registers The Values  of contacts Related to Guardian Into the Contacts Table
			 
				String sql ="update Index_Generator set Person_ID=?,Student_ID=?,Guardian_ID=? WHERE Auto_id = ?";
				
				this.ps=(PreparedStatement) dbConnection.prepareStatement(sql);
				ps.setInt(1, personid);
				ps.setInt(2, studentid);
				ps.setInt(3, gurdianid);
				ps.setInt(4, 1);
			
		
				ps.executeUpdate();
		
				return ps;	
			}

}