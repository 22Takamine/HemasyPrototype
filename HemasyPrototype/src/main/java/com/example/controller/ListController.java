package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ListAndRecordDao;
import com.example.entity.ListAndRecord;



@RestController
public class ListController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	ListAndRecordDao listAndRecordDao;

	@Autowired
	HttpSession session; 
	
	@RequestMapping("/getList")
    public List<ListAndRecord> getList(@RequestParam("type") int type) {
    	return listAndRecordDao.getList(type);
    }

}