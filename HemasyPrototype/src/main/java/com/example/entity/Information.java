package com.example.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Information {
	
	private int information_id;
	
	private int user_id;
	
	private String title;
	
	private String contents;
	
	private Date send_at;
	
	private int read_flag;
	
	private int done_flag;

}
