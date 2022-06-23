package com.example.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Message {
	
	private int message_id;
	
	private String message_text;
	
	private Date message_date;

}
