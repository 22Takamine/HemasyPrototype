package com.example.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.AchievementsDao;
import com.example.dao.ListAndRecordDao;
import com.example.entity.Achievements;



@RestController
public class RecordController {

//    @Autowired
//    ProductService productService;
    @Autowired
    MessageSource messageSource;
    
    @Autowired
    ListAndRecordDao listAndRecordDao;
    
    @Autowired
	HttpSession session; 
    
    @Autowired
	AchievementsDao achievementsDao;
    
    @RequestMapping("/getAchivementName")
    public Achievements getAchievement(@RequestParam("id") int id) {
    	return achievementsDao.findById(id);
    }
    
}
























