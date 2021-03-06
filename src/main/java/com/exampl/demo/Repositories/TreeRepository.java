package com.exampl.demo.Repositories;

import com.exampl.demo.dao.Treedao;
import com.exampl.demo.model.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class TreeRepository implements Treedao{
    //private static final String SQL_FIND_BY_ID = "SELECT * FROM myUser WHERE ID = :id";
    private static final String SQL_FIND_ALL = "SELECT * FROM my_Tree";
   // private static final String SQL_FIND_BY_NAME = "SELECT * FROM myUser WHERE user_id = :user_id";
    //private static final String SQL_FIND_NAME_BY_NAME = "SELECT user_id FROM myUser WHERE user_id = :user_id";
    private static final String SQL_INSERT = "INSERT INTO my_Tree (t_id, t_name,t_pid) values(:t_id, :t_name ,:t_pid)";
    //private static final String SQL_DELETE_BY_ID = "DELETE FROM myUser WHERE ID = :id";
    private static final String SQL_DELETE = "with temp(t_id,t_name,t_pid,curLevel)\n" + "as\n" + "(\n"
            + "select t_id,t_name,t_pid,1 as level from dbo.my_Tree\n" + "where  t_pid =:t_id\n" + "union all\n"
            + "select a.t_id,a.t_name,a.t_pid, b.curLevel+1from my_Tree a  \n" + "inner join\n" + "temp b\n"
            + "on ( a.t_pid=b.t_id)  \n" + ")\n"
            + "delete from my_Tree where t_id in ( select t_id from temp ) or t_id=:t_id";
    private static final BeanPropertyRowMapper<Tree> ROW_MAPPER = new BeanPropertyRowMapper<>(Tree.class);

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    public List gett() {


        List list = null;

        try {


            list=jdbcTemplate.queryForList(SQL_FIND_ALL, (Map<String, ?>) list);


        }
        catch (EmptyResultDataAccessException ex) {

        }

        return list;
    }

    public int savet(Tree tree) {
        final SqlParameterSource paramSource = new MapSqlParameterSource()

                .addValue("t_id", tree.getTree_id())
                .addValue("t_name", tree.getName())
                .addValue("t_pid", tree.getPid());
        jdbcTemplate.update(SQL_INSERT, paramSource);

        return 1;

    }
    public int deletet(String tree_id) {
        final SqlParameterSource paramSource = new MapSqlParameterSource()

                .addValue("t_id", tree_id);

        jdbcTemplate.update(SQL_DELETE, paramSource);

        return 1;
    }
}
