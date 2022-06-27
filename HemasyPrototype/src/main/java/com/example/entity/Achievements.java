package com.example.entity;

import lombok.Data;

@Data
public class Achievements {


	private Integer achievementId;
	private String achievementName;
	private String requirementToGet;
	private Integer conditionFormula;
	private Integer score;
	private Integer majorDivisions;
	private Integer subDivisions;
	
}
