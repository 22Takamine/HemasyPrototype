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
	private static final String SELECT_BY_ID = "SELECT * FROM achievement WHERE achievement_id = :achievement_id";

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Achievements> findByAll() {
		String sql = SELECT_BY_ALL;

		MapSqlParameterSource param = new MapSqlParameterSource();

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
}