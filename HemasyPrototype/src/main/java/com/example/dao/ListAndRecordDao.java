package com.example.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.ListAndRecord;

@Repository
public class ListAndRecordDao {
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	
	//日付とユーザーIDでアルコール、食事、運動をすべて取得
	public List<ListAndRecord> getRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query("SELECT * FROM lists_and_records WHERE category = 2 AND (type = 1 OR type = 2 OR type = 4) AND user_id = :userId AND create_date = :createDate", param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getBreakfastRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query("SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 1 AND user_id = :userId AND create_date = :createDate", param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getLunchRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query("SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 2 AND user_id = :userId AND create_date = :createDate", param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getDinnerRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query("SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 3 AND user_id = :userId AND create_date = :createDate", param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getSnackRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query("SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 4 AND user_id = :userId AND create_date = :createDate", param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getSportRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query("SELECT * FROM lists_and_records WHERE category = 2 AND type = 2 AND user_id = :userId AND create_date = :createDate", param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getAlcoholRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query("SELECT * FROM lists_and_records WHERE category = 2 AND type = 4 AND user_id = :userId AND create_date = :createDate", param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public ListAndRecord getSmokeRecord(int userId, Date date) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		param.addValue("createDate", date);
		List<ListAndRecord> list = jdbcTemplate.query("SELECT * FROM lists_and_records WHERE category = 2 AND type = 3 AND user_id = :userId AND create_date = :createDate", param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	public ListAndRecord getWeightRecord(int userId, Date date) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		param.addValue("createDate", date);
		List<ListAndRecord> list = jdbcTemplate.query("SELECT * FROM lists_and_records WHERE category = 2 AND type = 5 AND user_id = :userId AND create_date = :createDate", param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	//現在の最新体重取得
	public ListAndRecord getLatestWeightRecord(int userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		List<ListAndRecord> list = jdbcTemplate.query("SELECT * FROM lists_and_records WHERE category = 2 AND type = 5 AND user_id = :userId ORDER BY create_date DESC", param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
}