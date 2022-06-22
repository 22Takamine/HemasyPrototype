package com.example.form;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


public class UserForm {


	@NotBlank
	private String name;

	@NotBlank
	@Email
	private String mail;

	@NotBlank
	private String password;


	@NotNull
	private Integer sex;

	@NotNull
	@Past
	private Date birthDate;

	@NotNull
	private Double height;

	@NotNull
	private Double weight;

	private Integer id;
	private Integer achievementId;
	private Integer goalExerciseTime;
	private Integer goalCalorise;
	private Integer rankFlag;
	private Integer smokeFlag;
	private Integer alcoholFlag;
	private Date createdAt;
	private Integer roleId;
	private Double bodyFatPercentage;
	
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

	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	public Integer getAchievementId() {
		return achievementId;
	}
	public void setAchievementId(Integer achievementId) {
		this.achievementId = achievementId;
	}
	public Integer getGoalExerciseTime() {
		return goalExerciseTime;
	}
	public void setGoalExerciseTime(Integer goalExerciseTime) {
		this.goalExerciseTime = goalExerciseTime;
	}
	public Integer getGoalCalorise() {
		return goalCalorise;
	}
	public void setGoalCalorise(Integer goalCalorise) {
		this.goalCalorise = goalCalorise;
	}
	public Integer getRankFlag() {
		return rankFlag;
	}
	public void setRankFlag(Integer rankFlag) {
		this.rankFlag = rankFlag;
	}
	public Integer getSmokeFlag() {
		return smokeFlag;
	}
	public void setSmokeFlag(Integer smokeFlag) {
		this.smokeFlag = smokeFlag;
	}
	public Integer getAlcoholFlag() {
		return alcoholFlag;
	}
	public void setAlcoholFlag(Integer alcoholFlag) {
		this.alcoholFlag = alcoholFlag;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Double getBodyFatPercentage() {
		return bodyFatPercentage;
	}
	public void setBodyFatPercentage(Double bodyFatPercentage) {
		this.bodyFatPercentage = bodyFatPercentage;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    
}