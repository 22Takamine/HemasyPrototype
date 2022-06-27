package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public class UserDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	private static final String GET_ALL_USER = "SELECT * FROM users";
	
	public User findById(int userId) {
		String sql = """
						select * from users
						where user_id = :user_id
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", userId);
		
		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class) );
		return list.isEmpty() ? null :list.get(0);
		
	}
	
	//リン作成。すべてのユーザーを取得。管理者ページで利用。
	public List<User> getAllUser() {
		return jdbcTemplate.query(GET_ALL_USER, new BeanPropertyRowMapper<User>(User.class));
	}
}