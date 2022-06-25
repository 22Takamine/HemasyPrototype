package com.example.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.ListAndRecord;

@Repository
public class ListAndRecordDao {
	private static final String WEIGHT_INSERT = "INSERT INTO lists_and_records (category,type,value2,create_date,user_id)"
			+ "VALUES(2,5,:weight,:Date,:userId)";
	
	private static final String GET_LATEST_WEIGHT_RECORD = "SELECT current_date - create_date as value2 FROM lists_and_records WHERE category = 2 AND type = 5 AND user_id = :userId ORDER BY create_date DESC";
	
	private static final String GET_LATEST_SMOKE_DATE_RECORD = "SELECT current_date - create_date as value2 FROM lists_and_records WHERE category = 2 AND type = 3 AND user_id = :userId AND value3 <> '0' ORDER BY create_date DESC";
	
	private static final String GET_LATEST_ALCOHOL_DATE_RECORD = "SELECT sum(value2*value3*value4/100) AS value2 FROM lists_and_records WHERE category = 2 AND type = 4 AND user_id = :userId AND value2 <> '0' AND current_date = create_date";
	
	private static final String GET_LATEST_METS_AND_TIME_RECORD = "SELECT sum(value2) AS value2,sum(value3)/60 AS value3 FROM lists_and_records WHERE category = 2 AND type = 2 AND user_id = :userId AND value2 <> '0' AND current_date = create_date";

	private static final String GET_LATEST_CALORIE_INTAKE = "SELECT sum(value2 * value3) AS value2 FROM lists_and_records WHERE category = 2 AND type = 1 AND user_id = :userId AND value2 <> '0' AND current_date = create_date";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public void weightInsert(Integer userId,Double weight) {
		String sql = WEIGHT_INSERT;

		Date today = new Date();
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("weight",weight);
		param.addValue("Date", today);
		param.addValue("userId",userId);
		
		jdbcTemplate.update(sql, param);

		return;
	}	
	
	public ListAndRecord getLatestWeightRecord(Integer userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		
		String sql = GET_LATEST_WEIGHT_RECORD;
		List<ListAndRecord> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	public ListAndRecord getLatestSmokeDateRecord(Integer userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		
		String sql = GET_LATEST_SMOKE_DATE_RECORD;
		List<ListAndRecord> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	public ListAndRecord getLatestAlcoholDateRecord(Integer userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		
		String sql = GET_LATEST_ALCOHOL_DATE_RECORD;
		List<ListAndRecord> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	public ListAndRecord getLatestMetsAndTimeRecord(Integer userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		
		String sql = GET_LATEST_METS_AND_TIME_RECORD;
		List<ListAndRecord> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	public ListAndRecord getLatestCalorieIntake(Integer userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		
		String sql = GET_LATEST_CALORIE_INTAKE;
		List<ListAndRecord> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
}