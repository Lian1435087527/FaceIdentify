package com.exampl.demo.model;




public class User  {


	    
	    

	    private String user_id;

	    private String password;

		private int role;
	   
	    
	    
	    public  String getuser_id() {
	        return user_id;
	    }

	    public void setuser_id(String user_id) {
	        this.user_id = user_id;
	    }

	    public String getpassword() {
	        return password;
	    }

	    public void setpassword(String password) {
	        this.password= password;
	    }
	    public int getrole() {
	        return role;
	    }

	    public void setrole(int role) {
	         this.role= role;
	    }
	    @Override
	    public String toString() {
	        return "User{" +
	               
	                "user_id='" + user_id + '\'' +
	                ", password='" + password + '\'' +
	                '}';
	    }	    
}