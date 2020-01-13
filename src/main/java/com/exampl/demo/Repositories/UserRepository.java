package com.exampl.demo.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.exampl.demo.dao.Userdao;
import com.exampl.demo.model.User;


@Repository
public class UserRepository implements Userdao{
	 //private static final String SQL_FIND_BY_ID = "SELECT * FROM myUser WHERE ID = :id";
	    private static final String SQL_FIND_ALL = "SELECT * FROM myUser";
	    private static final String SQL_FIND_BY_NAME = "SELECT * FROM myUser WHERE user_id = :user_id";
	    private static final String SQL_FIND_NAME_BY_NAME = "SELECT user_id FROM myUser WHERE user_id = :user_id";
	    private static final String SQL_INSERT = "INSERT INTO myUser (user_id, password, role) values(:user_id, :password ,:role)";
	    //private static final String SQL_DELETE_BY_ID = "DELETE FROM myUser WHERE ID = :id";
	    private static final String SQL_UPDATE_PWD="UPDATE myUser SET password = :newpwd WHERE user_id = :user_id ";
	    private static final BeanPropertyRowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);
	  
	    @Autowired
	    NamedParameterJdbcTemplate jdbcTemplate;

	    
        public User findbyid(String user_id) {
        	User user = null;
        	
        	
	    	try {  
	            final SqlParameterSource paramSource = new MapSqlParameterSource("user_id", user_id);
	            
	            user=jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, paramSource, ROW_MAPPER);
	          }
	        catch (EmptyResultDataAccessException ex) {
	         
	        }
        	
        	return user;
        	
        }
	   
        
	    public int save(User user) {
	    	final SqlParameterSource paramSource1 = new MapSqlParameterSource("user_id", user.getuser_id());
	    	try{jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, paramSource1, ROW_MAPPER);
	    	}
	    	catch (EmptyResultDataAccessException ex) {
	        final SqlParameterSource paramSource = new MapSqlParameterSource()
	        		
	                .addValue("user_id", user.getuser_id())
	                .addValue("password", user.getpassword())
	                .addValue("role", user.getrole());
	        jdbcTemplate.update(SQL_INSERT, paramSource);
		
	        return 1;}
	    	
			return 0;
	    	
	    }
	    public String identify(String user_id) {
	    	User uu=new  User(); 
	    	try {  
		            final SqlParameterSource paramSource = new MapSqlParameterSource("user_id", user_id);
		            
		             uu=jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, paramSource, ROW_MAPPER);
		          }
		        catch (EmptyResultDataAccessException ex) {
		         return ("ERROR");
		        }
			return uu.getpassword();
	    }
	    public String getToken(User user) {
	        String token="";
	        token = JWT.create().withAudience(user.getuser_id() + "").sign(Algorithm.HMAC256(user.getpassword()));
	         
	        return token;
	    }


	public int chanpwd(String user_id,String newpwd) {

		final SqlParameterSource paramSource1 = new MapSqlParameterSource().addValue("user_id",user_id).addValue("newpwd",newpwd);
		try {
			jdbcTemplate.update(SQL_UPDATE_PWD,paramSource1);
		}
		catch(EmptyResultDataAccessException ex){
			return 1;
		};

		return 0;
	}
}