package com.example.form;

//@Data
public class InformationForm {

	private int userId;
	private String title;
	private String contents;
	
	private Integer informationId;
	private Integer readFlag;
	private Integer doneFlag;
	
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
	
}
