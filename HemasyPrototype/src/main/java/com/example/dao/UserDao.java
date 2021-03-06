
package com.example.dao;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.User;

@Repository
public class UserDao {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private static final String SELECT_BY_ID = "SELECT * FROM users WHERE user_id = :user_id";
	private static final String SELECT_BY_NAME = "SELECT * FROM users WHERE user_name = :name";
	private static final String SELECT_BY_MAIL = "SELECT * FROM users WHERE mail = :mail";
	private static final String SELECT_ID_AND_PASS = "SELECT * FROM users WHERE mail = :mail and password = :password";
	private static final String INSERT = "INSERT INTO users(user_name,mail,password,sex,birth,height,goal_exercise_time,goal_calorie,created_at,rank_flag,alcohol_flag,smoke_flag,role_id,achievement_id,achievement_flag)"
			+ " VALUES(:user_name, :mail,:password,:sex,:birth,:height,:goal_exercise_time,:goalCalorie,:createdAt,:rankFlag,:alcoholFlag,:smokeFlag,1,1,0)";
	private static final String UPDATE = "UPDATE users SET user_name = :name, mail = :mail, password = :pass, sex = :sex, birth = :birth, height = :height, goal_exercise_time = :time, goal_calorie = :calorise, rank_flag = :rank, alcohol_flag = :alcohol, smoke_flag = :smoke, achievement_id = :achievement WHERE user_id = :id";
	private static final String GET_ALL_USER = "SELECT * FROM users";

	public User findById(int userId) {
		String sql = SELECT_BY_ID;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_id", userId);

		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class) );
		return list.isEmpty() ? null :list.get(0);

	}
	
	public User findByName(String name) {
		String sql = SELECT_BY_NAME;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("name", name);

		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class) );
		return list.isEmpty() ? null :list.get(0);

	}
	
	public User findByMail(String mail) {
		String sql = SELECT_BY_MAIL;

		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("mail", mail);

		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class) );
		return list.isEmpty() ? null :list.get(0);

	}
	
	public User findIdAndPass(String mail,String password) {

		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("mail", mail);
		param.addValue("password", password);

		String sql = SELECT_ID_AND_PASS;
		List<User>resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));

		return resultList.isEmpty() ? null:resultList.get(0);
	}

	public User findUser(Integer id) {
		
		MapSqlParameterSource param = new MapSqlParameterSource();

		param.addValue("userId", id);
		
		String sql = SELECT_BY_ID;
		List<User>resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<User>(User.class));

		return resultList.isEmpty() ? null:resultList.get(0);
	}
	
	public void insert(User user) {
		String sql = INSERT;

		Date today = new Date();

	
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("user_name", user.getUserName());
		param.addValue("mail", user.getMail());
		param.addValue("password", user.getPassword());
		param.addValue("sex", user.getSex());
		param.addValue("birth", user.getBirth());
		param.addValue("height", user.getHeight());
		param.addValue("goal_exercise_time",30);
		param.addValue("goalCalorie", Math.ceil((user.getHeight()/100 * user.getHeight())/100 *22)*30);
		param.addValue("createdAt", today);
		param.addValue("rankFlag", user.getRankFlag());
		param.addValue("alcoholFlag", user.getAlcoholFlag());
		param.addValue("smokeFlag", user.getSmokeFlag());

		jdbcTemplate.update(sql, param);

		return;
	}
	
	public void update(Integer id, String name, String mail, String pass, Integer sex, Date birth, Double height, Integer achievement, Integer time, Integer calorise, Integer rank, Integer smoke, Integer alcohol) {
    	String sql = UPDATE;
    	
    	MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", id);
        param.addValue("name", name);
        param.addValue("mail", mail);
        param.addValue("pass", pass);
        param.addValue("sex", sex);
        param.addValue("birth", birth);
        param.addValue("height", height);
        param.addValue("achievement", achievement);
        param.addValue("time", time);
        param.addValue("calorise", calorise);
        param.addValue("rank", rank);
        param.addValue("smoke", smoke);
        param.addValue("alcohol", alcohol);
        jdbcTemplate.update(sql, param);  
    	
    }

	//?????????????????????????????????????????????????????????????????????????????????
	public List<User> getAllUser() {
		return jdbcTemplate.query(GET_ALL_USER, new BeanPropertyRowMapper<User>(User.class));
	}

}

