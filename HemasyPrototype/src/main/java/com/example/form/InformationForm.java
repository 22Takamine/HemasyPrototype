package com.example.form;

import javax.validation.constraints.Size;

//@Data
public class InformationForm {

	private int userId;
	
	@Size(min=1,max=25)
	private String title;
	
	@Size(min=1,max=1000)
	private String contents;
	
	private Integer informationId;
	private Integer readFlag;
	private Integer doneFlag;
	private String keyword;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public Integer getInformationId() {
		return informationId;
	}
	public void setInformationId(Integer informationId) {
		this.informationId = informationId;
	}
	
	public Integer getReadFlag() {
		return readFlag;
	}
	public void setReadFlag(Integer readFlag) {
		this.readFlag = readFlag;
	}
	
	public Integer getDoneFlag() {
		return doneFlag;
	}
	public void setDoneFlag(Integer doneFlag) {
		this.doneFlag = doneFlag;
	}
	public String getKeyword() {
		return this.keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;	
	}
	
}
