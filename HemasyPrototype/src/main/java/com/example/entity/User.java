package com.example.entity;

import java.sql.Date;

public class User {

	private String user_name;
	private String mail;
	private String password;
	private Integer sex;
	private Date birthDate;
	private Double height;
	private Date createdAt;
	private Integer rankFlag;
	private Integer alcoholFlag;
	private Integer smokeFlag;
	private Integer roleId;
	private Integer id;
	
	public User() {

	}

	public User(String user_name, String mail, String password,Integer sex,
			Date birthDate,Double height,Integer rankFlag,Integer alcoholFlag,Integer smokeFlag,Integer roleId) {
		this.user_name = user_name;
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

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	//	// 全フィールドが未入力かの判断
	//	public boolean isEmptyCondition() {
	//		return id == null && ParamUtil.isNullOrEmpty(name) && score == null;
	//	}
}


