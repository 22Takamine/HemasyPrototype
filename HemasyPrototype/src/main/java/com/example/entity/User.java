package com.example.entity;

public class User {

	private String user_name;
	private String mail;
	private String password;
	private String sex;
	private String birth;
	private String height;
	private String createdAt;
	private String rankFlag;
	private String alcoholFlag;
	private String smokeFlag;
    private String role;

	public User() {

	}

	public User(String user_name, String mail, String password,String
			sex,String birth,String height,String createdAt,String rankFlag,String alcoholFlag,String smokeFlag,String role) {
		this.user_name = user_name;
		this.mail = mail;
		this.password = password;
		this.sex = sex;
		this.birth = birth;
		this.height = height;
		this.createdAt = createdAt;
		this.rankFlag = rankFlag;
		this.alcoholFlag = alcoholFlag;
		this.smokeFlag = smokeFlag;
		this.role = role;
	}
		
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getRankFlag() {
		return rankFlag;
	}

	public void setRankFlag(String rankFlag) {
		this.rankFlag = rankFlag;
	}

	public String getAlcoholFlag() {
		return alcoholFlag;
	}

	public void setAlcoholFlag(String alcoholFlag) {
		this.alcoholFlag = alcoholFlag;
	}

	public String getSmokeFlag() {
		return smokeFlag;
	}

	public void setSmokeFlag(String smokeFlag) {
		this.smokeFlag = smokeFlag;
	}

//	// 全フィールドが未入力かの判断
//	public boolean isEmptyCondition() {
//		return id == null && ParamUtil.isNullOrEmpty(name) && score == null;
//	}
}


