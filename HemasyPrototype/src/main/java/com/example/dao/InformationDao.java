package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InformationDao{
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private String sql1 = "insert into infomation(user_id, title, contents,send_at, read_flag, done_flag) values(:user_id,:title,:contents, CURRENT_DATE,0,0);";
	
	public String InformationRegister(String title, String contents, int userId) {
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", userId);
		param.addValue("title", title);
		param.addValue("contents", contents);
		
		int result = jdbcTemplate.update(sql1, param);
		
		if(result == 1) {
			return "正常に登録できました";
		}
		
		return "正常に登録できませんでした。";
	}
	
}