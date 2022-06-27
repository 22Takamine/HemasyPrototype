package com.example.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class ListAndRecord {

	private int listsAndRecordsId;
	private int category;
	private int type;
	private String value1;
	private Integer value2;
	private Integer value3;
	private Integer value4;
	private Integer value5;
	private Integer value6;
	private Integer value7;
	private Integer value8;
	private Date createDate;
	private int userId;
	

	public ListAndRecord() {
		
	}
	
	
	public ListAndRecord(int listsAndRecordsId, int category, int type, String value1, Integer value2, Integer value3,
			Integer value4, Integer value5, Integer value6, Integer value7, Integer value8, Date createDate,
			int userId) {
		this.listsAndRecordsId = listsAndRecordsId;
		this.category = category;
		this.type = type;
		this.value1 = value1;
		this.value2 = value2;
		this.value3 = value3;
		this.value4 = value4;
		this.value5 = value5;
		this.value6 = value6;
		this.value7 = value7;
		this.value8 = value8;
		this.createDate = createDate;
		this.userId = userId;
	}
	
}
