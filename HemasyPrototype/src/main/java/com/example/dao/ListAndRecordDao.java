package com.example.dao;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.CommonRecord;
import com.example.entity.ListAndRecord;
import com.example.entity.Rank;
import com.example.entity.User;

@Repository
public class ListAndRecordDao {

    @Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
    
    @Autowired
	HttpSession session;

    //りん
    private static final String GET_RECORD = "SELECT * FROM lists_and_records WHERE category = 2 AND (type = 1 OR type = 2 OR type = 4) AND user_id = :userId AND create_date = :createDate";
	private static final String GET_BREAKFAST_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 1 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_LUNCH_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 2 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_DINNER_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 3 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_SNACK_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 1 AND value4 = 4 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_SPORT_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 2 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_ALCOHOL_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 4 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_SMOKE_RECORDS = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 3 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_WEIGHT_RECORD = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 5 AND user_id = :userId AND create_date = :createDate";
	private static final String GET_LATEST_WEIGHT_RECORD = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 5 AND value2 <> 0 AND create_date <= :createDate AND user_id = :userId ORDER BY create_date DESC";
	private static final String INSERT_RECORD = "INSERT INTO lists_and_records (category, type, value1, value2, value3, value4, value5, value6, value7, value8, create_date, user_id) VALUES (:category, :type, :value1, :value2, :value3, :value4, :value5, :value6, :value7, :value8, :createDate, :userId)";
	private static final String DELETE_RECORD_BY_DATE = "DELETE FROM lists_and_records WHERE create_date = :createDate AND user_id = :userId";
	private static final String GET_LISTS = "SELECT * FROM lists_and_records WHERE category = 1 AND type = :type AND (user_id = 1 or user_id = :userId)";
	

	//やすなり
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
	
	//まっし
	private static final String WEIGHT_INSERT = "INSERT INTO lists_and_records (category,type,value2,create_date,user_id)"
			+ "VALUES(2,5,:weight,:Date,:userId)";
	
	private static final String GET_LATEST_WEIGHT_RECORD_M = "SELECT * FROM lists_and_records WHERE category = 2 AND type = 5 AND user_id = :userId AND current_date >= create_date AND value2 <> '0' ORDER BY create_date DESC";
	
	private static final String GET_LATEST_SMOKE_DATE_RECORD = "SELECT 20 - (current_date - create_date) as value2 FROM lists_and_records WHERE category = 2 AND type = 3 AND user_id = :userId AND current_date >= create_date ORDER BY create_date DESC";
	
	private static final String GET_LATEST_ALCOHOL_RECORD = "SELECT sum(value2*value3*value4/100) AS value2 FROM lists_and_records WHERE category = 2 AND type = 4 AND user_id = :userId AND current_date = create_date";
	
	private static final String GET_LATEST_METS_AND_TIME_RECORD = "SELECT sum(value2) AS value2,sum(value3)/60 AS value3 FROM lists_and_records WHERE category = 2 AND type = 2 AND user_id = :userId AND current_date = create_date";

	private static final String GET_LATEST_CALORIE_INTAKE = "SELECT sum(value2 * value3) AS value2 FROM lists_and_records WHERE category = 2 AND type = 1 AND user_id = :userId  AND current_date = create_date";
	
	private static final String GET_LATEST_ALCOHOL_DATE_RECORD = "SELECT current_date - create_date as value2 FROM lists_and_records WHERE category = 2 AND type = 4 AND user_id = :userId AND current_date >= create_date ORDER BY create_date DESC";
	//りん-------------------------------------------------------------------------------------------------------------
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
	
	//入力日以前の最新体重取得
	public ListAndRecord getLatestWeightRecord(int userId, Date date) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		param.addValue("createDate", date);
		List<ListAndRecord> list = jdbcTemplate.query(GET_LATEST_WEIGHT_RECORD, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	//リストを渡してレコード挿入(まず日付で全削除)
	public int insertRecord(int userId, List<ListAndRecord> insertRecordList, Date date) {
		if (!insertRecordList.isEmpty()) {
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("userId", userId);
			param.addValue("createDate", date);
			jdbcTemplate.update(DELETE_RECORD_BY_DATE, param);
		}
		for(ListAndRecord listAndRecord: insertRecordList) {
			BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(listAndRecord);
	        jdbcTemplate.update(INSERT_RECORD, paramSource);
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
	
	
	//やすなり-------------------------------------------------------------------------------------------------------------
	public int insert(List<CommonRecord> recordList) {
    	for (CommonRecord record : recordList) {
    		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(record);
            jdbcTemplate.update("INSERT INTO lists_and_records (category, type, value1, value2, value3, value4, value5, value6, value7, value8, create_date, user_id) VALUES (:category, :type, :value1, :value2, :value3, :value4, :value5, :value6, :value7, :value8, :createDate, :userId)", paramSource);
    	}
    	
    	return 1;
	}

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
	
	
	//まっし------------------------------------------------------------------------------------------
	public void weightInsert(Integer userId,Double weight) {
		String sql = WEIGHT_INSERT;

		Date today = new Date();
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("weight",weight);
		param.addValue("Date", today);
		param.addValue("userId",userId);
		
		jdbcTemplate.update(sql, param);

	}	
	
	public ListAndRecord getLatestWeightRecordM(Integer userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		
		String sql = GET_LATEST_WEIGHT_RECORD_M;
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
	
	public ListAndRecord getLatestAlcoholRecord(Integer userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		
		String sql = GET_LATEST_ALCOHOL_RECORD;
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
	public ListAndRecord getLatestAlcoholDateRecord(Integer userId) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		
		String sql = GET_LATEST_ALCOHOL_DATE_RECORD;
		List<ListAndRecord> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<ListAndRecord>(ListAndRecord.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	//かわみつ-------------------------------------------------------------------------------------------------------------
	public List<CommonRecord> getFoodRecordsOfWeek(int id, Date day) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		//ToDo user_id を :user_id にして id を入れる。
		String sql ="""
				select value2, create_day from (
					select  sum(value2*value3) value2
					,create_date AS create_day
					from lists_and_records
					where category = 2
					and type = 1
					and user_id = :user_id
					and create_date <= :day
					group by create_date
					ORDER by create_date desc
					LIMIT 7) a
					order by create_day
				""";
		param.addValue("user_id", id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class));
	}
	
	public List<CommonRecord> getFoodRecordsOfMonth(int id, Date day) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		//ToDo user_id を :user_id にして id を入れる。
		String sql ="""
				select create_date AS create_day
				, sum(value2*value3) value2
				from lists_and_records 
				where left(to_char(create_date, 'YYYY-MM'), 7) = left(:day, 7)
				AND user_id = :user_id
				AND category = 2
				AND type = 1
				group by create_date
				ORDER BY create_date;
				""";
		param.addValue("user_id", id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class));
	}
	
	public List<CommonRecord> getFoodRecordsOfYear(int id, Date day) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		//ToDo user_id を :user_id にして id を入れる。
		String sql ="""
					SELECT
					 to_char(create_date, 'YYYY-MM') AS create_day,
					sum(value2 * value3) value2
					FROM lists_and_records
					where category =2
					AND type = 1
					AND user_id = :user_id
					AND left(to_char(create_date, 'YYYY-MM'), 4) = left(:day, 4)
					GROUP BY to_char(create_date, 'YYYY-MM')
					ORDER BY to_char(create_date, 'YYYY-MM');

				""";
		param.addValue("user_id", id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class));
	}
	
	public List<CommonRecord> getExerciseRecordsOfWeek(int user_id, Date day) {
		//ToDouser_id をidからとる
		//value 2 = bmi
		//value 3 = 消費カロリー　
		//value 4 = 体重
		String sql = """
				select value2, create_day, value3, value4 from(
					select
					sum(ROUND(T2.value2/((T1.height/100)*(T1.height/100)), 2)) value2   
					, T2.create_date AS create_day
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
					where T1.user_id = 2
					and T2.create_date <= '2022-06-20'
					group by create_day	
					order by create_day desc
					
					LIMIT 7) c
				order by create_day
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	
	public List<CommonRecord> getExerciseRecordsOfMonth(int user_id, Date day) {
		//ToDouser_id をidからとる
		//value 2 = bmi
		//value 3 = 消費カロリー　
		//value 4 = 体重
		String sql = """
				select value3, value4,  create_date AS create_day from(
				    select
				    ROUND(T1.value2 * T2.value2 * (T2.value3/60) * 1.05, 2) value3
				    ,T2.value3 value4
				    , T1.create_date create_date
				    from lists_and_records T1
				    join lists_and_records T2
				    ON T2.create_date = T1.create_date
				    AND T2.category = 2
				    AND T2.type = 2
				    AND T1.category = 2
				    AND T1.type= 5
				    Where T2.user_id = 2
				    and left(to_char(T2.create_date, 'YYYY-MM'), 7) = left('2022-06-02', 7)
				    order by to_char(T1.create_date, 'YYYY-MM') desc)c 
				order by create_day
				

				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	
	public List<CommonRecord> getExerciseRecordsOfYear(int id, Date day) {
		//ToDouser_id をidからとる
		//value 2 = bmi
		//value 3 = 消費カロリー　
		//value 4 = 体重
		String sql = """
				select value3, value4,  left(create_date, 7) AS create_day from(
				    select
				    sum(ROUND(T1.value2 * T2.value2 * (T2.value3/60) * 1.05, 2)) value3
				    ,sum(T2.value3) value4
				    , to_char(T1.create_date, 'YYYY-MM') create_date
				    from lists_and_records T1
				    join lists_and_records T2
				    ON T2.create_date = T1.create_date
				    AND T2.category = 2
				    AND T2.type = 2
				    AND T1.category = 2
				    AND T1.type= 5
				    Where T2.user_id = 2
				    and left(to_char(T2.create_date, 'YYYY-MM'), 4) <= left('2022-02-02', 4)
				    group by to_char(T1.create_date, 'YYYY-MM')
				    order by to_char(T1.create_date, 'YYYY-MM') desc)c 
				order by create_date
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	

	public List<CommonRecord> getAlcoholRecordsOfWeek(int user_id, Date day) {
		//ToDo user_id をidからとる
		//alcohol量
		String sql = """
				select value2, create_date AS create_day from (
					select	
					sum(ROUND(value2*value3*(value4/100), 2)) value2
					,create_date
					from lists_and_records
					where category = 2
					and type = 4
					and user_id = :user_id
					and create_date <= :day
					group by create_date
					ORDER BY create_date desc
					LIMIT 7) c
				order by create_day
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	
	public List<CommonRecord> getAlcoholRecordsOfMonth(int user_id, Date day) {
		//ToDo user_id をidからとる
		//alcohol量
		String sql = """
				select value2, create_date AS create_day from (
					select	
					sum(ROUND(value2*value3*(value4/100), 2)) value2
					,create_date AS create_date
					from lists_and_records
					where category = 2
					and type = 4
					and user_id = 2 
					and left(to_char(create_date, 'YYYY-MM'), 7) = left('2022-06-02', 7)
					group by create_date
					ORDER BY create_date desc) c
				order by create_date
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	
	public List<CommonRecord> getAlcoholRecordsOfYear(int user_id, Date day) {
		//ToDo user_id をidからとる
		//alcohol量
		String sql = """
				select value2, create_date AS create_day from (
					select	
					sum(ROUND(value2*value3*(value4/100), 2)) value2
					,to_char(create_date, 'YYYY-MM') AS create_date
					from lists_and_records
					where category = 2
					and type = 4
					and user_id = 2 
					and left(to_char(create_date, 'YYYY-MM'), 4) = left('2022-06-02', 4)
					group by to_char(create_date, 'YYYY-MM')
					ORDER BY create_date desc) c
				order by create_day
               
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	

	public List<CommonRecord> getSmokeRecordsOfWeek(int user_id, Date day) {
		//ToDo user_id をidからとる
		String sql = """
			select value3, create_date AS create_day from(
				select
				sum(value3) value3
				,create_date
				from lists_and_records
				where category = 2
				and type = 3
				and user_id = :user_id
				and create_date <= :day
				group by create_date
				ORDER BY create_date desc
				LIMIT 7)c
			order by create_date
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	
	public List<CommonRecord> getSmokeRecordsOfMonth(int user_id, Date day) {
		//ToDo user_id をidからとる
		String sql = """
			select value3, create_date AS create_day from(
				select
				sum(value3) value3
				,create_date
				from lists_and_records
				where category = 2
				and type = 3
				and user_id = 2
				and left(to_char(create_date, 'YYYY-MM'), 7) = left('2022-06-02', 7)
				group by create_date
				ORDER BY create_date desc)c
			order by create_date
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	
	public List<CommonRecord> getSmokeRecordsOfYear(int user_id, Date day) {
		//ToDo user_id をidからとる
		String sql = """
			select value3, create_date AS create_day from(
				select
				sum(value3) value3
				,to_char(create_date, 'YYYY-MM') create_date
				from lists_and_records
				where category = 2
				and type = 3
				and user_id = 2
				AND left(to_char(create_date, 'YYYY-MM'), 4) = left('2022-04-23', 4)
				group by to_char(create_date, 'YYYY-MM')
				ORDER BY to_char(create_date, 'YYYY-MM') desc)c
			order by create_date
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}

	public List<CommonRecord> getBmiRecordsOfWeek(int user_id, Date day) {
		//value3 BMI
		//valu2 体重
		String sql = """
			select value3, value2, create_date from(
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
				and create_date <= :day
				where T1.user_id = :user_id
				group by T2.create_date
				ORDER BY T2.create_date desc
				LIMIT 7) c
			order by create_date
			""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	
	public List<CommonRecord> getBmiRecordsOfMonth(int user_id, Date day) {
		//value3 BMI
		//valu2 体重
		String sql = """
			select value3, value2, create_date from(
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
				and create_date <= :day
				where T1.user_id = :user_id
				group by T2.create_date
				ORDER BY T2.create_date desc
				LIMIT 7) c
			order by create_date
			""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", user_id);
		param.addValue("day", day);
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<CommonRecord>(CommonRecord.class) );
	}
	

	public void setZero(int user_id, int type) {
		String sql = """
			insert into lists_and_records (create_date, value2, value3, value4, value5, value6, value7, category, type, user_id)
			select * from (
			with recursive Dummy(i) as 
			(select cast(to_char(now(), 'YYYY') || '-12-31' as date) i
			union all
			select cast(i + cast('-1 days ' as interval) as date) from Dummy where i > cast('2022-01-01' as date))
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
		
		param.addValue("type", type);
		param.addValue("user_id", user_id);
		jdbcTemplate.update(sql, param);
	}

}