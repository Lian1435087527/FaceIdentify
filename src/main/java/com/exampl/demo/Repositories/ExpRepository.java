package com.exampl.demo.Repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.exampl.demo.model.Exp;



@Repository
public class ExpRepository {
	 //private static final String SQL_FIND_BY_ID = "SELECT * FROM myUser WHERE ID = :id";
	    private static final String SQL_FIND_ALL = "SELECT * FROM Exp";
	    private static final String SQL_FIND_BY_NAME = "SELECT * FROM Exp WHERE exp_id = :exp_id";
	    private static final String SQL_INSERT = "INSERT INTO Exp (user_id,exp_id,exp_vm,,exp_userframe,exp_pippackage,exp_dataset,exp_output) values(:user_id,:exp_id,:exp_vm,,:exp_userframe,:exp_pippackage,:exp_dataset,:exp_output)";
	    //private static final String SQL_DELETE_BY_ID = "DELETE FROM myUser WHERE ID = :id";

	    private static final BeanPropertyRowMapper<Exp> ROW_MAPPER = new BeanPropertyRowMapper<>(Exp.class);
	  
	    @Autowired
	    NamedParameterJdbcTemplate jdbcTemplate;

	    

	   

	    public int save(Exp exp) {
	    	final SqlParameterSource paramSource1 = new MapSqlParameterSource("exp_id", exp.getexp_id());
	    	try{jdbcTemplate.queryForObject(SQL_FIND_BY_NAME, paramSource1, ROW_MAPPER);
	    	}
	    	catch (EmptyResultDataAccessException ex) {
	        final SqlParameterSource paramSource = new MapSqlParameterSource()
	        		
	                .addValue("user_id", exp.getuser_id())
	                .addValue("exp_id", exp.getexp_id()+"_"+exp.getuser_id())
	                .addValue("exp_vm",exp.getexp_vm())
	                .addValue("exp_userframe",exp.getExp_userframe())
	                .addValue("exp_pippackage",exp.getExp_pippackage())
	                .addValue("exp_dataset",exp.getExp_dataset())
	                .addValue("exp_output",exp.getExp_output());

	         jdbcTemplate.update(SQL_INSERT, paramSource);
	         return 1;
	   
	    	}
			return 0;
	    
	    
	}}
