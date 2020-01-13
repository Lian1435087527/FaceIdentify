package com.exampl.demo.dao;



import com.exampl.demo.model.User;

public interface Userdao {
	public int save(User user);
    
	 public String identify(String user_id);
	 public String getToken(User user);
	public int chanpwd(String uid,String newpwd);
}
