package com.example.dao;


import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.CommonRecord;
import com.example.entity.User;

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
	
	public void setZero(User user, int type) {
		String sql = """
			insert into lists_and_records (create_date, value2, value3, value4, value5, value6, value7, category, type, user_id)
			select * from (
			with recursive Dummy(i) as 
			(select cast(now() as date) i
			union all
			select cast(i + cast('-1 days ' as interval) as date) from Dummy where i > cast(:created_date as date)) 
			select i as days, 0 value2, 0 value3, 0 value4, 0 value5, 0 value6, 0 value7, 2 category, :type type, :user_id user_id from Dummy
			
			except
			
			select
			create_date days, 0 value2, 0 value3, 0 value4, 0 value5, 0 value6, 0 value7, 2 category, :type type, :user_id user_id
			from
			lists_and_records 
			where
			category = 2 
			and type = :type
			and user_id = :user_id
			order by days) c;
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		int id = user.getUserId();
		Date date = user.getCreatedAt();
		
		param.addValue("type", type);
		param.addValue("user_id", id);
		param.addValue("created_date", date);
		jdbcTemplate.update(sql, param);
	}
	

}