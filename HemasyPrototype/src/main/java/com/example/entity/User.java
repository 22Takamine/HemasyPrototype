package com.example.entity;

import java.sql.Date;

public class User {

	
	
	private Integer id;
	private Integer userId;
	private String userName;
	private String mail;
	private String password;
	private Integer sex;

	private Date birth;
	private Double height;
	private Double weight;


	private Date createdAt;
	private Integer goalExerciseTime;
	private Integer goalCalorie;
	private Integer rankFlag;
	private Integer alcoholFlag;
	private Integer smokeFlag;
	private Integer roleId;
	private Integer achievementId;
	private Integer achievementFlag;
	private Double bmi;
	
	public User() {

	}

	public User(String userName, String mail, String password,Integer sex,
			Date birth,Double height,Integer goalExerciseTime,Integer goalCalorie,Integer rankFlag,Integer alcoholFlag,Integer smokeFlag,Integer roleId) {

//			Date birthDate,Integer height,Integer rankFlag,Integer alcoholFlag,Integer smokeFlag,Integer roleId) {

		this.userName = userName;
		this.mail = mail;
		this.password = password;
		this.sex = sex;
		this.birth = birth;
		this.height = height;
		this.goalExerciseTime = goalExerciseTime;
		this.goalCalorie = goalCalorie;
		this.rankFlag = rankFlag;
		this.alcoholFlag = alcoholFlag;
		this.smokeFlag = smokeFlag;
		this.roleId = roleId;
	}
	
	public User(Integer id, Integer userId, String userName, String mail, String password, Integer sex, Date birth, Double height,
			Date createdAt, Integer goalExerciseTime, Integer goalCalorie, Integer rankFlag, Integer alcoholFlag, Integer smokeFlag,
			Integer roleId, Integer achievementId, Integer achievementFlag, Double bmi) {
		this.id = id;
		this.userId = userId;
		this.userName = userName;
		this.mail = mail;
		this.password = password;
		this.sex = sex;
		this.birth = birth;
		this.height = height;
		this.createdAt = createdAt;
		this.goalExerciseTime = goalExerciseTime;
		this.goalCalorie = goalCalorie;
		this.rankFlag = rankFlag;
		this.alcoholFlag = alcoholFlag;
		this.smokeFlag = smokeFlag;
		
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {

		this.roleId = roleId;
//		this.achievementId = achievementId;
//		this.achievementFlag = achievementFlag;
//		this.bmi = bmi;
	}
	
	


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public Integer getGoalExerciseTime() {
		return goalExerciseTime;
	}

	public void setGoalExerciseTime(Integer goalExerciseTime) {
		this.goalExerciseTime = goalExerciseTime;
	}
	
	public Integer getGoalCalorie() {
		return goalCalorie;
	}

	public void setGoalCalorie(Integer goalCalorie) {
		this.goalCalorie = goalCalorie;
	}
	
	public Integer getRankFlag() {
		return rankFlag;
	}

	public void setRankFlag(Integer rankFlag) {
		this.rankFlag = rankFlag;
	}

	public Integer getAlcoholFlag() {
		return alcoholFlag;
	}

	public void setAlcoholFlag(Integer alcoholFlag) {
		this.alcoholFlag = alcoholFlag;
	}

	public Integer getSmokeFlag() {
		return smokeFlag;
	}

	public void setSmokeFlag(Integer smokeFlag) {
		this.smokeFlag = smokeFlag;
	}


	public Double getWeight() {
		return weight;

	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

//	public Double getGoalCalorie() {
//		return goalCalorie;
//	}
//
//	public void setGoalCalorie(Double goalCalorie) {
//		this.goalCalorie = goalCalorie;
//
//	}
	
	public Integer getAchievementId() {
		return achievementId;
	}
	
	public void setAchievementId(Integer achievementId) {
		this.achievementId = achievementId;
	}
	
	public Integer getAchievementFlag() {
		return achievementFlag;
	}
	
	public void setAchievementFlag(Integer achievementFlag) {
		this.achievementFlag = achievementFlag;
	}
	

	//	// 全フィールドが未入力かの判断
	//	public boolean isEmptyCondition() {
	//		return id == null && ParamUtil.isNullOrEmpty(name) && score == null;
	//	}
	
}

