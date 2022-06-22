package com.example.form;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


public class UserForm {
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String mail;
    
    @NotEmpty
    private String pass;
    
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
    private Double bodyFat;
    private Integer achievementId;
    private Integer goalExerciseTime;
    private Integer goalCalorise;
    private Integer rank;
    private Integer smoke;
    private Integer alcohol;

    	
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
    
    public Double getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(Double bodyFat) {
        this.bodyFat = bodyFat;
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
    
    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
    
    public Integer getsmoke() {
        return smoke;
    }

    public void setSmoke(Integer smoke) {
        this.smoke = smoke;
    }
    
    public Integer getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(Integer alcohol) {
        this.alcohol = alcohol;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
    
    
}