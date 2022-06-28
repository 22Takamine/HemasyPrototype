package com.example.controller;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.AchievementsDao;
import com.example.dao.ListAndRecordDao;
import com.example.entity.Achievements;
import com.example.entity.CommonRecord;
import com.example.entity.ListAndRecord;
import com.example.service.RecordService;



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

    @Autowired
    RecordService recordService;
    

    @RequestMapping("/getFoodListWeek")
    public List<CommonRecord> getFoodList(@RequestParam("id") int id, @RequestParam("day") Date day, @RequestParam("scope") String scopeStr) {
    	int scope = Integer.parseInt(scopeStr);
    	if (scope == 0) {
    	return listAndRecordDao.getFoodRecordsOfWeek(id, day);
    	}else if (scope == 1) {
    		return listAndRecordDao.getFoodRecordsOfMonth(id,day);
    	}else {
    		return listAndRecordDao.getFoodRecordsOfYear(id,day);
    	}
	}
    
    @RequestMapping("/getExerciseListWeek")
    public List<CommonRecord> getExerciseList(@RequestParam("id") int id, @RequestParam("day") Date day, @RequestParam("scope") String scopeStr) {
    	int scope = Integer.parseInt(scopeStr);
    	if (scope == 0) {
    		return listAndRecordDao.getExerciseRecordsOfWeek(id, day);
    	}else if (scope == 1) {
    		return listAndRecordDao.getExerciseRecordsOfMonth(id,day);
    	}else {
    		return listAndRecordDao.getExerciseRecordsOfYear(id,day);
    	}

    }
    
    @RequestMapping("/getAlcoholListWeek")
    public List<CommonRecord> getAlcoholList(@RequestParam("id") int id, @RequestParam("day") Date day, @RequestParam("scope") String scopeStr) {
    	int scope = Integer.parseInt(scopeStr);
    	if (scope == 0) {
    		return listAndRecordDao.getAlcoholRecordsOfWeek(id, day);
    	}else if (scope == 1) {
    		return listAndRecordDao.getAlcoholRecordsOfMonth(id,day);
    	}else {
    		return listAndRecordDao.getAlcoholRecordsOfYear(id,day);
    	}
    }

    @RequestMapping("/getSmokeListWeek")
    public List<CommonRecord> getSmokeList(@RequestParam("id") int id, @RequestParam("day") Date day, @RequestParam("scope") String scopeStr) {
    	int scope = Integer.parseInt(scopeStr);
    	if (scope == 0) {
    		return listAndRecordDao.getSmokeRecordsOfWeek(id, day);
    	}else if (scope == 1) {
    		return listAndRecordDao.getSmokeRecordsOfMonth(id,day);
    	}else {
    		return listAndRecordDao.getSmokeRecordsOfYear(id,day);
    	}
    }
    
    @RequestMapping("/getBmiListWeek")
    public List<CommonRecord> getBmiList(@RequestParam("id") int id, @RequestParam("day") Date day, @RequestParam("scope") String scopeStr) {
    	int scope = Integer.parseInt(scopeStr);
    	if (scope == 0) {
    		return listAndRecordDao.getBmiRecordsOfWeek(id, day);
    	}else if(scope == 1) {
    		return listAndRecordDao.getBmiRecordsOfMonth(id, day);
    	}else {
    		return listAndRecordDao.getBmiRecordsOfYear(id, day);
    	}
    }

    
    @RequestMapping("/getAchivementName")
    public Achievements getAchievement(@RequestParam("id") int id) {
    	return achievementsDao.findById(id);

    }
    
    @RequestMapping("/getList")
    public List<ListAndRecord> getList(@RequestParam("type") int type) {
    	return listAndRecordDao.getList(type);
    }


}























