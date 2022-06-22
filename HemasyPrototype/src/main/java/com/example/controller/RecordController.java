package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ListAndRecordDao;
import com.example.entity.CommonRecord;



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
    
    @RequestMapping("/getFoodList")
    public List<CommonRecord> getFoodList(@RequestParam("id") int id) {
    	return listAndRecordDao.getFoodRecords(id);
    }
    
    @RequestMapping("/getExerciseList")
    public List<CommonRecord> getExerciseList(@RequestParam("id") int id) {
    	return listAndRecordDao.getExerciseRecords(id);
    }
    
    @RequestMapping("/getAlcoholList")
    public List<CommonRecord> getAlcoholList(@RequestParam("id") int id) {
    	return listAndRecordDao.getAlcoholRecords(id);
    }

    @RequestMapping("/getSmokeList")
    public List<CommonRecord> getSmokeList(@RequestParam("id") int id) {
    	return listAndRecordDao.getSmokeRecords(id);
    }
    
    @RequestMapping("/getBmiList")
    public List<CommonRecord> getBmiList(@RequestParam("id") int id) {
    	return listAndRecordDao.getBmiRecords(id);
    }
    
}
























