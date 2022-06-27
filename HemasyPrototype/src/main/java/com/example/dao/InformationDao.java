package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Information;

@Repository
public class InformationDao{
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String SELECT_BY_ALL = "SELECT i.information_id, u.user_name, u.mail, i.title, i.send_at, i.read_flag, i.done_flag FROM infomation i INNER JOIN users u ON i.user_id = u.user_id ORDER BY i.information_id DESC";
	private static final String SELECT_BY_ID = "SELECT i.information_id, u.user_name, u.mail, i.title,i.contents, i.send_at, i.read_flag, i.done_flag FROM infomation i INNER JOIN users u ON i.user_id = u.user_id WHERE i.information_id = :information_id";
	private static final String UPDATE = "UPDATE infomation SET read_flag = :read_flag, done_flag = :done_flag WHERE information_id = :information_id";
	private static final String DELETE = "DELETE FROM infomation WHERE information_id = :information_id";
	private String sql1 = "insert into infomation(user_id, title, contents,send_at, read_flag, done_flag) values(:user_id,:title,:contents, CURRENT_DATE,0,0);";
	
	//たかみね
	public List<Information> findByAll() {
		String sql = SELECT_BY_ALL;

		MapSqlParameterSource param = new MapSqlParameterSource();

		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Information>(Information.class) );
		return list;

	}
	
	public Information findById(Integer information_id) {
		String sql = SELECT_BY_ID;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("information_id", information_id);

		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Information>(Information.class) );
		return list.isEmpty() ? null :list.get(0);

	}
	
	public void update(Integer information_id, Integer read_flag, Integer done_flag) {
    	String sql = UPDATE;
    	
    	MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("information_id", information_id);
        param.addValue("read_flag", read_flag);
        param.addValue("done_flag", done_flag);
        
        jdbcTemplate.update(sql, param);  
    	
    }
	
	public void delete(Integer information_id) {
		String sql = DELETE;
    	
    	MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("information_id", information_id);
        
        jdbcTemplate.update(sql, param);  
  }
	
	//やすなり
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