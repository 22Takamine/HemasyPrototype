package com.example.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class ListAndRecord {

	private int listsAndRecordsId;
	private int category;
	private int type;
	private String value1;
	private Double value2;
	private Double value3;
	private Double value4;
	private Double value5;
	private Double value6;
	private Double value7;
	private Double value8;
	private Date createDate;
	private int userId;
	

	public ListAndRecord() {
		
	}
	

	
	public ListAndRecord(int listsAndRecordsId, int category, int type, String value1, Double value2, Double value3,
			Double value4, Double value5, Double value6, Double value7, Double value8, Date createDate,
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
