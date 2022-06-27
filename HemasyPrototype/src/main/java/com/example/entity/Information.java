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
	
	private Integer read_flag;
	
	private Integer done_flag;
	
	//userをジョインしたデータを保存
	private String user_name;
	
	private String mail;
	
	

}
