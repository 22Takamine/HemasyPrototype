package com.example.form;

import javax.validation.constraints.NotEmpty;


public class UserForm {

	@NotEmpty
	private String name;

	@NotEmpty
	private String mail;

	@NotEmpty
	private String pass;

	@NotEmpty
	private String sex;

	@NotEmpty
	private String birthDate;

	@NotEmpty
	private String height;

	@NotEmpty
	private String weight;

	private String bodyFat;
	private String achievementId;
	private String goalExerciseTime;
	private String goalCalorise;
	private String rankFlag;
	private String smokeFlag;
	private String alcoholFlag;
	private String createdAt;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBodyFat() {
		return bodyFat;
	}

	public void setBodyFat(String bodyFat) {
		this.bodyFat = bodyFat;
	}

	public String getAchievementId() {
		return achievementId;
	}

	public void setAchievementId(String achievementId) {
		this.achievementId = achievementId;
	}

	public String getGoalExerciseTime() {
		return goalExerciseTime;
	}

	public void setGoalExerciseTime(String goalExerciseTime) {
		this.goalExerciseTime = goalExerciseTime;
	}

	public String getGoalCalorise() {
		return goalCalorise;
	}

	public void setGoalCalorise(String goalCalorise) {
		this.goalCalorise = goalCalorise;
	}

	public String getRankFlag() {
		return rankFlag;
	}

	public void setRankFlag(String rankFlag) {
		this.rankFlag = rankFlag;
	}

	public String getSmokeFlag() {
		return smokeFlag;
	}

	public void setSmokeFlag(String smokeFlag) {
		this.smokeFlag = smokeFlag;
	}

	public String getAlcoholFlag() {
		return alcoholFlag;
	}

	public void setAlcoholFlag(String alcoholFlag) {
		this.alcoholFlag = alcoholFlag;
	}

}