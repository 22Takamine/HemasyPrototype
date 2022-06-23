package com.example.dao;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.ListAndRecord;
import com.example.entity.User;

@Repository
public class ListAndRecordDao {
	
	private static final String GET_RECORD = "SELECT * FROM lists_and_records WHERE category = 2 AND (type = 1 OR type = 2 OR type = 4) AND user_id = :userId AND create_date = :createDate";
	private static final String GET_BREAKFAST_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 1 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_LUNCH_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 2 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_DINNER_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 3 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_SNACK_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 4 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_SPORT_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 2 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_ALCOHOL_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 4 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_SMOKE_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 3 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_WEIGHT_RECORD = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 5 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_LATEST_SMOKE_RECORD = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 3 AND user_id = :userId ORDER BY create_date DESC";
	private static final String GET_LATEST_WEIGHT_RECORD = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 5 AND user_id = :userId ORDER BY create_date DESC";
	private static final String INSERT_RECORD = "INSERT INTO lists_and_records (category, type, value1, value2, value3, value4, value5, value6, value7, value8, create_date, user_id) VALUES (:category, :type, :value1, :value2, :value3, :value4, :value5, :value6, :value7, :value8, :createDate, :userId)";
	private static final String DELETE_RECORD_BY_DATE = "DELETE FROM lists_and_records WHERE create_date = :createDate AND user_id = :userID";
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	HttpSession session;
	
	//日付とユーザーIDでアルコール、食事、運動をすべて取得
	public List<ListAndRecord> getRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query(GET_RECORD, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getBreakfastRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query(GET_BREAKFAST_RECORDS, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getLunchRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query(GET_LUNCH_RECORDS, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getDinnerRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query(GET_DINNER_RECORDS, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getSnackRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query(GET_SNACK_RECORDS, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getSportRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query(GET_SPORT_RECORDS, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public List<ListAndRecord> getAlcoholRecords(int userId, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query(GET_ALCOHOL_RECORDS, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
    	return recordList;
    }
	
	public ListAndRecord getSmokeRecord(int userId, Date date) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		param.addValue("createDate", date);
		List<ListAndRecord> list = jdbcTemplate.query(GET_SMOKE_RECORDS, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	public ListAndRecord getWeightRecord(int userId, Date date) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		param.addValue("createDate", date);
		List<ListAndRecord> list = jdbcTemplate.query(GET_WEIGHT_RECORD, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	//現在の最新たばこ取得
		public ListAndRecord getLatestSmokeRecord(int userId) {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("userId", userId);
			List<ListAndRecord> list = jdbcTemplate.query(GET_LATEST_SMOKE_RECORD, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
	        return list.isEmpty() ? null : list.get(0);
		}
	
	//現在の最新体重取得
	public ListAndRecord getLatestWeightRecord(int userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		List<ListAndRecord> list = jdbcTemplate.query(GET_LATEST_WEIGHT_RECORD, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	//リストを渡してレコード挿入(まず日付で全削除)
	public int insertRecord(List<ListAndRecord> insertRecordList) {
		if (!insertRecordList.isEmpty()) {
			MapSqlParameterSource param = new MapSqlParameterSource();
			Date deleteDate = insertRecordList.get(0).getCreateDate();
			param.addValue("createDate", deleteDate);
			param.addValue("userId", ((User) session.getAttribute("user")).getUserId());
			jdbcTemplate.update(DELETE_RECORD_BY_DATE, param);
		}
		for(ListAndRecord listAndRecord: insertRecordList) {
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(listAndRecord);
	        jdbcTemplate.update(INSERT_RECORD, paramSource);
		}
		return 1;
	}
}