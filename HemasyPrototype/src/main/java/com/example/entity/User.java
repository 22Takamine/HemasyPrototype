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
	
}