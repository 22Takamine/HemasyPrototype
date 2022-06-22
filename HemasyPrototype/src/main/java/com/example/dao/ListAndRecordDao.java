package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.ListAndRecord;
import com.example.entity.Rank;

@Repository
public class ListAndRecordDao {
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	//SQL
	//ユーザーのuser_idを取得して、そのユーザーが登録した食事リストを取得するSQL。
	String sql1 = "SELECT * FROM lists_and_records WHERE user_id = :user_id AND category = 1 AND type = 1;";
	//ユーザーのuser_idを取得して、そのユーザーが登録した食事リストを取得するSQL。
	String sql2 = "SELECT * FROM lists_and_records WHERE user_id = :user_id AND category = 1 AND type = 4;";
	//前日の運動時間算出sql,運動時間降順に並ぶSQL。
	String sql3 = "select user_name, sum_time, achievement_name from (select user_name, SUM(value3) as sum_time, u.achievement_id from lists_and_records as lr join users as u on lr.user_id = u.user_id where category = 2 and type = 2 and create_date = CURRENT_DATE - 1 group by u.user_id) as u join achievement as a on u.achievement_id = a.achievement_id  order by sum_time desc;";
	//前週の運動時間算出sql, 運動時間降順に並ぶSQL。
	String sql4 = "select user_name, sum_time, achievement_name from (\r\n"
			+ "SELECT user_name, SUM(value3) as sum_time, achievement_id\r\n"
			+ "FROM lists_and_records as lr\r\n"
			+ "join users as u\r\n"
			+ "on lr.user_id = u.user_id\r\n"
			+ "WHERE category = 2 and type = 2 and\r\n"
			+ "create_date >= (SELECT (now() + CAST('-' || (extract(dow from now()) + 7) || ' day' AS INTERVAL))::DATE)\r\n"
			+ "AND create_date < (SELECT (now() + CAST('-' || (extract(dow from now())) || ' day' AS INTERVAL))::DATE)\r\n"
			+ "group by u.user_id)\r\n"
			+ "as u\r\n"
			+ "join achievement as a\r\n"
			+ "on u.achievement_id = a.achievement_id\r\n"
			+ "order by sum_time desc;\r\n"
			+ "";
	//称号ランキング
	String sql5 = "select user_name, sum_score, achievement_name from (select user_name, sum_score, achievement_id from users as u join (select user_id, sum(score) as sum_score from achievement_unlock as au join achievement as a on au.achievement_id = a.achievement_id group by user_id) as a on u.user_id = a.user_id) as u join achievement as a on u.achievement_id = a.achievement_id order by sum_score desc;";

	
	//ユーザーのuser_idを取得して、そのユーザーが登録した食事リストを取得する。
	public List<ListAndRecord> FoodListById(int userId) {
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", userId);
		
		var list = jdbcTemplate.query(sql1, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class) );
		return list.isEmpty() ? null :list;
		
	}
	
	//ユーザーのuser_idを取得して、そのユーザーが登録した食事リストを取得する。
	public List<ListAndRecord> AlcoholListById(int userId) {
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", userId);
		
		var list = jdbcTemplate.query(sql2, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class) );
		
		return list.isEmpty() ? null :list;
		
	}
	
	public List<Rank> DayRanking(){
		MapSqlParameterSource param = new MapSqlParameterSource();
		var list = jdbcTemplate.query(sql3, param, new BeanPropertyRowMapper<Rank>(Rank.class) );
		return list.isEmpty() ? null :list;
	}
	
	public List<Rank> WeekRanking(){
		MapSqlParameterSource param = new MapSqlParameterSource();
		var list = jdbcTemplate.query(sql4, param, new BeanPropertyRowMapper<Rank>(Rank.class) );
		return list.isEmpty() ? null :list;
	}
	
	public List<Rank> AchievementRanking(){
		MapSqlParameterSource param = new MapSqlParameterSource();
		var list = jdbcTemplate.query(sql5, param, new BeanPropertyRowMapper<Rank>(Rank.class) );
		return list.isEmpty() ? null :list;
	}
	
	
	
}