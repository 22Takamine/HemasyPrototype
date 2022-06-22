package com.example.dao;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.CommonRecord;

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
				ORDER by create_date""";
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class));
	}
	
	
	public List<CommonRecord> getExerciseRecords(int user_id) {
		//ToDouser_id をidからとる
		String sql = """
				select
				ROUND(T2.value2/((T1.height/100)*(T1.height/100)), 2) value2
				, T2.create_date create_date 
				, ROUND(T2.value2 * T3.value2 * (T3.value3/60) * 1.05, 2) value3
				, T3.value3 value4
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
				order by T2.create_date;
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}

	public List<CommonRecord> getAlcoholRecords(int id) {
		//ToDo user_id をidからとる
		String sql = """
				select
				ROUND(value2*value3*(value4/100), 2) value2
				,create_date
				from lists_and_records
				where category = 2
				and type = 4
				and user_id = 2
				ORDER BY create_date;
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}

	public List<CommonRecord> getSmokeRecords(int id) {
		//ToDo user_id をidからとる
				String sql = """
						select
						value3
						,create_date
						from lists_and_records
						where category = 2
						and type = 3
						and user_id = 2
						ORDER BY create_date;
						""";
				MapSqlParameterSource param = new MapSqlParameterSource();
				return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	
   
}