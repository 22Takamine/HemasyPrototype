package com.example.dao;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public class UserDao {
	
	private static final String SELECT_BY_PRODUCT_ID = "SELECT * FROM users WHERE user_id = :user_id";
	private static final String UPDATE = "UPDATE users SET user_name = :name, mail = :mail, password = :pass, sex = :sex, birth = :birth, height = :height, goal_exercise_time = :time, goal_calorie = :calorise, rank_flag = :rank, alcohol_flag = :alcohol, smoke_flag = :smoke, achievement_id = :achievement WHERE user_id = :id";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public User findById(int userId) {
		String sql = SELECT_BY_PRODUCT_ID;
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", userId);
		
		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class) );
		return list.isEmpty() ? null :list.get(0);
		
	}
	
	
	public void update(Integer id, String name, String mail, String pass, Integer sex, Date birth, Double height, Integer achievement, Integer time, Integer calorise, Integer rank, Integer smoke, Integer alcohol) {
    	String sql = UPDATE;
    	
    	MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        param.addValue("name", name);
        param.addValue("mail", mail);
        param.addValue("pass", pass);
        param.addValue("sex", sex);
        param.addValue("birth", birth);
        param.addValue("height", height);
        param.addValue("achievement", achievement);
        param.addValue("time", time);
        param.addValue("calorise", calorise);
        param.addValue("rank", rank);
        param.addValue("smoke", smoke);
        param.addValue("alcohol", alcohol);
        
        jdbcTemplate.update(sql, param);  
    	
    }
}