package com.school.db;

public class DataCollector {

}

class Persons {
	private String First_Name = null;
	private String Middle_Name = null;
    private String Surname_Name = null;
	private String geder = "Female";
	 
    void setFirstName(String newValue) {
    	 if (newValue != null && newValue.length() > 0) {
             this.First_Name = newValue;
         }
    }
    void setMiddleName(String newValue) {
    	 if (newValue != null && newValue.length() > 0) {
             this.Middle_Name = newValue;
         }
    }
    void setSurnameName(String newValue) {
    	 if (newValue != null && newValue.length() > 0) {
             this.Surname_Name= newValue;
         }
    }
    void setGeder(String newValue) {
    	 if (newValue != null && newValue.length() > 0) {
             this.geder = newValue;
         }
    }
   
     String getFirstName() {
    	return First_Name;
    }
     String getMiddleName() {
    	return Middle_Name;
    }
     
     String getSurnameName() {
    	return Surname_Name;
    }
    int getGeder() {
    	int sex=0;
    	if(geder.equals("MALE")||geder.equals("Male")){
    		sex=1;
    		
    	}else if(geder.equals("FEMALE")||geder.equals("Female")){
    		sex=2;
    		
    	}
    
    	else{sex=0;
    	System.out.println(sex);}
    	return sex;
    }
}

class Students extends Persons {
	
	   int getStudentsPersonDescription() {
		   final int studesc = 2;
	    	return studesc;
	    }

}

class Guardian extends Persons {
	 private String idNumber = null;
	 private String phoneNumber = null;
	 private String residence = null;
	
	 void setIdNumber(String newValue) {
		 if (newValue != null && newValue.length() > 0) {
             this.idNumber = newValue;
         }
		     
	    }
	 void setPhoneNumber(String newValue) {
		 if (newValue != null && newValue.length() > 0) {
             this.phoneNumber = newValue;
         }
	    }

	 void setResidence(String newValue) {
		 if (newValue != null && newValue.length() > 0) {
             this.residence = newValue;
         }
	    }
	 
	  String getIdNumber() {
	    	return idNumber;
	    }
	  String getPhone() {
	    	return phoneNumber;
	    }
	  String getResidence() {
	    	return residence;
	    }
	  int getGuardianPersonDescription() {
		   final int guarddesc = 3;
	    	return guarddesc;
	    }

}

