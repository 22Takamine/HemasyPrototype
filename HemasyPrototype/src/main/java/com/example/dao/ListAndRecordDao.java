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
	
	private static final String GET_FOOD_RECORD = "SELECT * FROM lists_and_records WHERE category = 2 AND type = :type AND value4 = :value4 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_SPORT_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 2 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_ALCOHOL_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 4 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_SMOKE_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 3 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_WEIGHT_RECORD = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 5 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_LATEST_WEIGHT_RECORD = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 5 AND value2 <> 0 AND create_date <= :createDate AND user_id = :userId ORDER BY create_date DESC";
	private static final String INSERT_LIST_AND_RECORD = "INSERT INTO lists_and_records (category, type, value1, value2, value3, value4, value5, value6, value7, value8, create_date, user_id) VALUES (:category, :type, :value1, :value2, :value3, :value4, :value5, :value6, :value7, :value8, :createDate, :userId)";
	private static final String DELETE_RECORD_BY_DATE = "DELETE FROM lists_and_records WHERE create_date = :createDate AND user_id = :userId";
	private static final String GET_LISTS = "SELECT * FROM lists_and_records WHERE category = 1 AND type = :type AND (user_id = 1 or user_id = :userId)";
	private static final String FIND_LIST_BY_NAME = "SELECT * FROM lists_and_records WHERE category = 1 AND type = :type AND value1 = :value1 AND user_id = :userId";
	private static final String UPDATE_MY_LISTS = "UPDATE lists_and_records SET value2 = :value2, value3 = :value3, value4 = :value4, value5 = :value5 WHERE category = 1 AND type = :type AND value1 = :value1 AND user_id = :userId";
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	HttpSession session;
	
	//日付とユーザーIDで食事をすべて取得
	public List<ListAndRecord> getFoodRecords(int userId, int type, int value4, Date date) {
    	MapSqlParameterSource param = new MapSqlParameterSource();
    	param.addValue("userId", userId);
    	param.addValue("type", type);
    	param.addValue("value4", value4);
    	param.addValue("createDate", date);
		List<ListAndRecord> recordList = jdbcTemplate.query(GET_FOOD_RECORD, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
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
	
	//入力日以前の最新体重取得
	public ListAndRecord getLatestWeightRecord(int userId, Date date) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		param.addValue("createDate", date);
		List<ListAndRecord> list = jdbcTemplate.query(GET_LATEST_WEIGHT_RECORD, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	//リストを渡してレコード挿入(まず日付で全削除)
	public int insertRecord(int userId, List<ListAndRecord> insertRecordsList, Date date) {
		if (!insertRecordsList.isEmpty()) {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("userId", userId);
			param.addValue("createDate", date);
			jdbcTemplate.update(DELETE_RECORD_BY_DATE, param);
		}
		for(ListAndRecord listAndRecord: insertRecordsList) {
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(listAndRecord);
	        jdbcTemplate.update(INSERT_LIST_AND_RECORD, paramSource);
		}
		return 1;
	}
	
	//リストを取得
	public List<ListAndRecord> getList(int type) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("type", type);
		System.out.println("こいつのユーザid" + ((User) session.getAttribute("user")).getUserId());
		param.addValue("userId", ((User) session.getAttribute("user")).getUserId());
		return jdbcTemplate.query(GET_LISTS, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
	}
	
	//マイリストに追加
	public int insertMyList(int userId, List<ListAndRecord> insertListsList) {
		if (!insertListsList.isEmpty()) {
			for (ListAndRecord insertListList: insertListsList) {
				MapSqlParameterSource param = new MapSqlParameterSource();
				param.addValue("type", insertListList.getType());
				param.addValue("value1", insertListList.getValue1());
				param.addValue("userId", userId);
				List<ListAndRecord> list = jdbcTemplate.query(FIND_LIST_BY_NAME, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
				if (list.isEmpty()) {
					param.addValue("category", insertListList.getCategory());
					param.addValue("type", insertListList.getType());
					param.addValue("value1", insertListList.getValue1() + "(ユーザー定義)");
					param.addValue("value2", insertListList.getValue2());
					param.addValue("value3", insertListList.getValue3());
					param.addValue("value4", insertListList.getValue4());
					param.addValue("value5", insertListList.getValue5());
					param.addValue("value6", insertListList.getValue6());
					param.addValue("value7", insertListList.getValue7());
					param.addValue("value8", insertListList.getValue8());
					param.addValue("createDate", insertListList.getCreateDate());
					param.addValue("userId", insertListList.getUserId());
					jdbcTemplate.update(INSERT_LIST_AND_RECORD, param);
				} else {
					BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(insertListList);
			        jdbcTemplate.update(UPDATE_MY_LISTS, paramSource);
				}
			}
		}
		return 1;
	}
}