package com.example.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Color;

@Repository
public class ColorDao {

	private static final String GET_SMOKE_COLOR_LEVEL = "SELECT * FROM color WHERE genre = 0 AND color_level = :colorLevel";
	
	private static final String GET_ALCOHOL_COLOR_LEVEL = "SELECT * FROM color WHERE genre = 1 AND color_level = :colorLevel";
	
	private static final String GET_CALORIE_COLOR_LEVEL = "SELECT * FROM color WHERE genre = 2 AND color_level = :colorLevel";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public Color getSmokeColorLevel(Double colorLevel) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("colorLevel", colorLevel);
		
		String sql = GET_SMOKE_COLOR_LEVEL;
		List<Color> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Color>(Color.class));
        return list.isEmpty() ? null : list.get(0);
	}

	public Color getAlcoholColorLevel(Integer colorLevel) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("colorLevel", colorLevel);
		
		String sql = GET_ALCOHOL_COLOR_LEVEL;
		List<Color> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Color>(Color.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	public Color getCalorieColorLevel(Integer colorLevel) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("colorLevel", colorLevel);
		
		String sql = GET_CALORIE_COLOR_LEVEL;
		List<Color> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Color>(Color.class));
        return list.isEmpty() ? null : list.get(0);
	
	}
}