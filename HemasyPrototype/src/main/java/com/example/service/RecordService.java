package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AchievementsDao;
import com.example.dao.ListAndRecordDao;
import com.example.entity.Achievements;
@Service
public class RecordService {
	@Autowired
	ListAndRecordDao listAndRecord;
	@Autowired
	AchievementsDao achievementDao;
	
	//セッションからユーザー情報をとって、このメソッドの引数に渡して by kawamitsu
	public void setZeroPastRecords(int user_id) {
		for(int i = 1 ; i < 6 ; i++) {
			listAndRecord.setZero(user_id, i);
		}		
	}
	
	public void achievementUnlock(int user_id) {
		try {
			achievementDao.unlock(0,user_id);
		}catch(Exception e) {
			
		}
    	int userFoodListSize = listAndRecord.getUserFoodListSize(user_id);
    	int userExerciseListSize = listAndRecord.getUserExerciseListSize(user_id);
    	List<Achievements> foodACVList = achievementDao.getFoodDayAchievement();
    	List<Achievements> exerciseACVList = achievementDao.getExerciseDayAchievement();
    	for(int i = 0; i < foodACVList.size(); i++) {
    		int a = foodACVList.get(i).getConditionFormula();
        	if (a <= userFoodListSize) {
        		try{
        			achievementDao.unlock(foodACVList.get(i).getAchievementId(),user_id);
        		}catch(Exception e){
        			
        		}
        	}
    	}
    	for(int i = 0; i < foodACVList.size(); i++) {
    		int a = exerciseACVList.get(i).getConditionFormula();
        	if (a <= userExerciseListSize) {
        		try{
        			achievementDao.unlock(exerciseACVList.get(i).getAchievementId(),user_id);
        		}catch(Exception e) {
        			
        		}
        	}
    	}
    	
    }
}
