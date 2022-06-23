package com.example.entity;

import java.io.Serializable;
import java.sql.Date;

import lombok.Data;

@Data
public class User implements Serializable{
	
	private int id;
	private int userId;
	private String userName;
	private String mail;
	private String password;
	private int sex;
	private Date birth;
	private int height;
	private Date createdAt;
	private int goalExerciseTime;
	private int goalCalorie;
	private int rankFlag;
	private int alcoholFlag;
	private int smokeFlag;
	private int roleId;
	private int achievementId;
	private int achievementFlag;
	
	private double bmi;
	
	public User() {
		
	}

	public User(int id, int userId, String userName, String mail, String password, int sex, Date birth, int height,
			Date createdAt, int goalExerciseTime, int goalCalorie, int rankFlag, int alcoholFlag, int smokeFlag,
			int roleId, int achievementId, int achievementFlag, double bmi) {
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
		this.roleId = roleId;
		this.achievementId = achievementId;
		this.achievementFlag = achievementFlag;
		this.bmi = bmi;
	}
	
	
	
}