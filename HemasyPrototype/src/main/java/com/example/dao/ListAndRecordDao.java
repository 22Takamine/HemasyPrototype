package com.example.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.CommonRecord;
import com.example.entity.ListAndRecord;
import com.example.entity.Rank;


@Repository
public class ListAndRecordDao {

    @Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

    public int insert(List<CommonRecord> recordList) {
    	for (CommonRecord record : recordList) {
    		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(record);
            jdbcTemplate.update("INSERT INTO lists_and_records (category, type, value1, value2, value3, value4, value5, value6, value7, value8, create_date, user_id) VALUES (:category, :type, :value1, :value2, :value3, :value4, :value5, :value6, :value7, :value8, :createDate, :userId)", paramSource);
    	}
    	
    	return 1;
	}

	public List<CommonRecord> getFoodRecords(int id) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		//ToDo user_id を :user_id にして id を入れる。
		String sql ="""
				select  sum(value2*value3) value2
				,create_date AS create_date
				from lists_and_records
				where category = 2
				and type = 1
				and user_id = 1
				group by create_date
				ORDER by create_date
				LIMIT 7
				""";
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class));
	}
	
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
	
	public List<CommonRecord> getExerciseRecords(int user_id) {
		//ToDouser_id をidからとる
		//value 2 = bmi
		//value 3 = 消費カロリー　
		//value 4 = 体重
		String sql = """
				select
				sum(ROUND(T2.value2/((T1.height/100)*(T1.height/100)), 2)) value2   
				, T2.create_date create_date 
				, sum(ROUND(T2.value2 * T3.value2 * (T3.value3/60) * 1.05, 2)) value3
				, sum(T3.value3) value4
				from users T1	
				Join lists_and_records T2
				ON T1.user_id = T2.user_id
				AND T2.category = 2
				AND T2.type = 5
				JOIN lists_and_records T3 
				ON T1.user_id = T3.user_id
				AND T3.category = 2
				AND T3.type = 2
				AND T2.create_date = T3.create_date
				where T1.user_id =1
				group by T2.create_date
				order by T2.create_date
				LIMIT 7
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}

	public List<CommonRecord> getAlcoholRecords(int id) {
		//ToDo user_id をidからとる
		//alcohol量
		String sql = """
				select
				sum(ROUND(value2*value3*(value4/100), 2)) value2
				,create_date
				from lists_and_records
				where category = 2
				and type = 4
				and user_id = 1
				group by create_date
				ORDER BY create_date
				
				LIMIT 7
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}

	public List<CommonRecord> getSmokeRecords(int id) {
		//ToDo user_id をidからとる
				String sql = """
						select
						sum(value3) value3
						,create_date
						from lists_and_records
						where category = 2
						and type = 3
						and user_id = 2
						group by create_date
						ORDER BY create_date
						LIMIT 7
						""";
				MapSqlParameterSource param = new MapSqlParameterSource();
				return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}

	public List<CommonRecord> getBmiRecords(int id) {
		//value3 BMI
		//valu2 体重
		String sql = """
			select
			sum(ROUND(T2.value2/((T1.height/100)*(T1.height/100)), 2)) value3
			, sum(T2.value2) value2
			, T2.create_date 
			FROM
			users T1
			JOIN lists_and_records T2
			ON T1.user_id = T2.user_id
			AND T2.category = 2
			AND T2.type = 5
			where T1.user_id = 1
			group by T2.create_date
			ORDER BY T2.create_date
			LIMIT 7
			""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	
}