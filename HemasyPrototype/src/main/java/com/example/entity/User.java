package com.example.entity;

import java.sql.Date;

public class User {

	private String userName;
	private String mail;
	private String password;
	private Integer sex;
	private Date birthDate;
	private Integer height;
	private Date createdAt;
	private Integer rankFlag;
	private Integer alcoholFlag;
	private Integer smokeFlag;
	private Integer roleId;
	private Double weight;
	private Integer userId;
	private Double goalCalorie;
	
	public User() {

	}

	public User(String userName, String mail, String password,Integer sex,
			Date birthDate,Integer height,Integer rankFlag,Integer alcoholFlag,Integer smokeFlag,Integer roleId) {
		this.userName = userName;
		this.mail = mail;
		this.password = password;
		this.sex = sex;
		this.birthDate = birthDate;
		this.height = height;
		this.rankFlag = rankFlag;
		this.alcoholFlag = alcoholFlag;
		this.smokeFlag = smokeFlag;
		this.roleId = roleId;
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

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
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

	public Double getGoalCalorie() {
		return goalCalorie;
	}

	public void setGoalCalorie(Double goalCalorie) {
		this.goalCalorie = goalCalorie;
	}

	//	// 全フィールドが未入力かの判断
	//	public boolean isEmptyCondition() {
	//		return id == null && ParamUtil.isNullOrEmpty(name) && score == null;
	//	}
}


