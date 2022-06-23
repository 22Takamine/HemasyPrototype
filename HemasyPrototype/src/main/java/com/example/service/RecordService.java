package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ListAndRecordDao;
import com.example.entity.User;
@Service
public class RecordService {
	@Autowired
	ListAndRecordDao listAndRecord;
	
	
	//セッションからユーザー情報をとって、このメソッドの引数に渡して by kawamitsu
	public void setZeroPastRecords(User user) {
		for(int i = 1 ; i < 6 ; i++) {
			listAndRecord.setZero(user, i);
		}		
	}
}
