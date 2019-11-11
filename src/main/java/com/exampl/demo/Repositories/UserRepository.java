package com.exampl.demo.Repositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.exampl.demo.model.User;


@Repository
public class UserRepository {
	 private static final String SQL_FIND_BY_ID = "SELECT * FROM myUser WHERE ID = :id";
	    private static final String SQL_FIND_ALL = "SELECT * FROM myUser";
	    private static final String SQL_FIND_BY_NAME = "SELECT * FROM myUser WHERE NAME = :name";
	    private static final String SQL_INSERT = "INSERT INTO myUser (id ,name, password) values(:id, :name, :password)";
	    private static final String SQL_DELETE_BY_ID = "DELETE FROM myUser WHERE ID = :id";

	    private static final BeanPropertyRowMapper<User> ROW_MAPPER = new BeanPropertyRowMapper<>(User.class);
	  
	    @Autowired
	    NamedParameterJdbcTemplate jdbcTemplate;

	    public User findById(String id) {
	        try {
	            final SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
	            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, paramSource, ROW_MAPPER);
	        }
	        catch (EmptyResultDataAccessException ex) {
	            return null;
	        }
	    }

	    public Iterable<User> findAll() {
	        return jdbcTemplate.query(SQL_FIND_ALL, ROW_MAPPER);
	    }

	    public int save(User user) {
	        final SqlParameterSource paramSource = new MapSqlParameterSource()
	        		.addValue("id",user.getId())
	                .addValue("name", user.getName())
	                .addValue("password", user.getpassword());

	        return jdbcTemplate.update(SQL_INSERT, paramSource);
	    }
	    public String identify(String name,String password) {
	    	 try {  
		            final SqlParameterSource paramSource = new MapSqlParameterSource("id", name);
		            User uu=new  User();
		             uu=jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, paramSource, ROW_MAPPER);
		             if(uu.getpassword()==password) {
		            	 return("pass");
		             }
		             else {return("denify");}
		        }
		        catch (EmptyResultDataAccessException ex) {
		            return ("ERROR");
		        }
	    }

	    public void deleteById(Integer id) {
	        final SqlParameterSource paramSource = new MapSqlParameterSource("id", id);
	        jdbcTemplate.update(SQL_DELETE_BY_ID, paramSource);
	    }
	}