package com.example.dao;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Achievements;

@Repository

public class AchievementsDao {

	private static final String SELECT_BY_ALL = "SELECT * FROM achievement ORDER BY achievement_id";
	private static final String SELECT_BY_ALL2 = "select T1.* from achievement T1 join achievement_unlock T2 ON T1.achievement_id = T2.achievement_id Where T2.user_id = :userId";
	private static final String SELECT_BY_ID = "SELECT * FROM achievement WHERE achievement_id = :achievement_id";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Achievements> findByAll2() {
		String sql = SELECT_BY_ALL;

		MapSqlParameterSource param = new MapSqlParameterSource();

		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Achievements>(Achievements.class) );
		return list;

	}
	
	public List<Achievements> findByAll(Integer userId) {
		String sql = SELECT_BY_ALL2;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("userId", userId);
		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Achievements>(Achievements.class) );
		return list;

	}
	
	
	public Achievements findById(Integer achievement_id) {
		String sql = SELECT_BY_ID;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("achievement_id", achievement_id);

		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Achievements>(Achievements.class) );
		return list.isEmpty() ? null :list.get(0);

	}
	
	public List<Achievements> getFoodDayAchievement() {
    	String sql = """
    			select * from achievement where major_divisions = 1 and sub_divisions = 1
    			""";
    	MapSqlParameterSource param = new MapSqlParameterSource();
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Achievements>(Achievements.class));
    }
    
    public List<Achievements> getExerciseDayAchievement() {
    	String sql = """
    			select * from achievement where major_divisions = 2 and sub_divisions = 1
    			""";
    	MapSqlParameterSource param = new MapSqlParameterSource();
		return jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Achievements>(Achievements.class));
    }

	public void unlock(int achievementId, int userId) {
		// TODO 自動生成されたメソッド・スタブ
		String sql ="insert into achievement_unlock (achievement_id, user_id) values(:ACVID, :user_id)";
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("ACVID", achievementId);
		param.addValue("user_id", userId);
		jdbcTemplate.update(sql, param);
	}
}