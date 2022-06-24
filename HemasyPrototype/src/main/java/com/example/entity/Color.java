package com.example.entity;

public class Color {

	private Integer colorId;
	private String colorPath;
	private Integer colorLevel;
	private Integer genre;

	public Color() {

	}
	
	public Color(Integer colorId, String colorPath, Integer colorLevel,Integer genre) {
		this.colorId = colorId;
		this.colorPath = colorPath;
		this.colorLevel = colorLevel;
		this.genre = genre;

	}
	public Integer getColorId() {
		return colorId;
	}
	public void setColorId(Integer colorId) {
		this.colorId = colorId;
	}
	public String getColorPath() {
		return colorPath;
	}
	public void setColorPath(String colorPath) {
		this.colorPath = colorPath;
	}
	public Integer getColorLevel() {
		return colorLevel;
	}
	public void setColorLevel(Integer colorLevel) {
		this.colorLevel = colorLevel;
	}
	public Integer getGenre() {
		return genre;
	}
	public void setGenre(Integer genre) {
		this.genre = genre;
	}

}