package com.example.dao;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Bmi;

@Repository
public class BmiDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	HttpSession session;

	//まっし
	private static final String BMI_PATH = "SELECT * FROM bmi_display WHERE lower_limit <= :bmi AND upper_limit > :bmi";

	public Bmi getBmiPath(Double bmi) {
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("bmi", bmi);
		
		String sql = BMI_PATH;
		List<Bmi> list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Bmi>(Bmi.class));
        return list.isEmpty() ? null : list.get(0);
	}
}