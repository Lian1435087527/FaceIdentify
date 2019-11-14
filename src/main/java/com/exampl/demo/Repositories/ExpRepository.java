package com.exampl.demo.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.exampl.demo.model.Exp;



//@Repository
/*public class ExpRepository {
	 //private static final String SQL_FIND_BY_ID = "SELECT * FROM myUser WHERE ID = :id";
	    private static final String SQL_FIND_ALL = "SELECT * FROM myUser";
	    private static final String SQL_FIND_BY_NAME = "SELECT * FROM myUser WHERE user_id = :user_id";
	    private static final String SQL_INSERT = "INSERT INTO myUser (user_id, password, role) values(:user_id, :password ,:role)";
	    //private static final String SQL_DELETE_BY_ID = "DELETE FROM myUser WHERE ID = :id";

	    private static final BeanPropertyRowMapper<Exp> ROW_MAPPER = new BeanPropertyRowMapper<>(Exp.class);
	  
	    @Autowired
	    NamedParameterJdbcTemplate jdbcTemplate;

	    

	    public Iterable<Exp> findAll() {
	        return jdbcTemplate.query(SQL_FIND_ALL, ROW_MAPPER);
	    }

	    public int save(Exp exp) {
	        final SqlParameterSource paramSource = new MapSqlParameterSource()
	        		
	                .addValue("user_id", exp.getexp_id())
	                .addValue("password", exp)
	                .addValue("role", user.getrole());

	        return jdbcTemplate.update(SQL_INSERT, paramSource);
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

	    
	}*/
