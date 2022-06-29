package com.example.entity;

import lombok.Data;

@Data
public class Bmi {

	private Integer bmiId;
	private Double lowerLimit;
	private Double upperLimit;
	private String bmiName;
	private String imgPath;
	
	public Bmi() {
		
	}
	
	public Bmi(Integer bmiId, Double lowerLimit ,Double upperLimit, String bmiName, String imgPath) {
		this.bmiId = bmiId;
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.bmiName = bmiName;
		this.imgPath = imgPath;
	}	
}
