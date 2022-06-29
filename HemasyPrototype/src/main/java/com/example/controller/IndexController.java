package com.example.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dao.AchievementsDao;
import com.example.dao.BmiDao;
import com.example.dao.ColorDao;
import com.example.dao.InformationDao;
import com.example.dao.ListAndRecordDao;
import com.example.dao.UserDao;
import com.example.entity.Achievements;
import com.example.entity.Bmi;
import com.example.entity.Color;
import com.example.entity.Information;
import com.example.entity.ListAndRecord;
import com.example.entity.Rank;
import com.example.entity.User;
import com.example.form.IndexForm;
import com.example.form.InformationForm;
import com.example.form.ListAndRecordForm;
import com.example.form.UserForm;
import com.example.service.RecordService;


@Controller
public class IndexController {

    @Autowired
    MessageSource messageSource;
    
    @Autowired
	HttpSession session;
    
    @Autowired
    HttpServletRequest request;
    
    @Autowired
    ListAndRecordDao listAndRecordDao;
    
	@Autowired
	InformationDao informationDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	BmiDao bmiDao;
	
	@Autowired
	AchievementsDao achievementsDao;
	
	@Autowired
	ColorDao colorDao;

    @Autowired
    RecordService recordService;

	//最初にここにきて、login画面にいくyu
	@RequestMapping({ "/", "/index" })
	public String index(@ModelAttribute("index") IndexForm form, Model model) {
		return "login";
	}
	
	//ログイン画面から、新規登録画面に遷移
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(@ModelAttribute("index") UserForm form, Model model) {
        return "register";
    }
	
	//新規登録画面で登録ボタンを押した際に、ログイン画面に遷移
	@RequestMapping(value = "/loginBack", method = RequestMethod.POST)
	public String loginBack(@Validated @ModelAttribute("index") UserForm form,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			if(form.getBirth() == null) {
				model.addAttribute("msgBirth","生年月日は必須です");
			}
			return "register";
		}
		if(form.getBirth() == null) {
			model.addAttribute("msgBirth","生年月日は必須です");
			return "register";
		}
		
		
		
		User userName = userDao.findByName(form.getName());
		User userMail = userDao.findByMail(form.getMail());
		if(userName != null) {
			model.addAttribute("msgName","そのユーザー名は使用されています。");
		}
		if(userMail != null) {
			model.addAttribute("msgMail","そのメールアドレスは使用されています。。");
		}
		if(userName != null || userMail != null) {
			return "register";
		}
	

		
		User user = new User(form.getName(),form.getMail(), form.getPassword(),form.getSex(),form.getBirth(),
				form.getHeight(),form.getGoalExerciseTime(),form.getGoalCalorise(),form.getRankFlag(),form.getAlcoholFlag(),form.getSmokeFlag(),form.getRoleId());
		
		userDao.insert(user);
		
		User id = userDao.findIdAndPass(user.getMail(),user.getPassword());
		
		listAndRecordDao.weightInsert(id.getUserId(),form.getWeight());

		return "login";

    }
	
	//ログイン成功時にメニュー画面に遷移
	@RequestMapping(value = "/result", method = RequestMethod.POST)
	public String login(@Validated @ModelAttribute("index") IndexForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {	
			return "login";
		}
		
		User user = userDao.findIdAndPass(form.getMail(), form.getPassword());
    	if(user == null) {
    		model.addAttribute("msg","メールアドレスまたはパスワードが間違っています。");
    		return "login";
    	}
    	else if(user.getRoleId() == 0) {
    		List<User> userList = userDao.getAllUser();
			
			List<ListAndRecord> foodList = listAndRecordDao.FoodListById(user.getUserId());
			
			List<ListAndRecord> alcoholList = listAndRecordDao.AlcoholListById(user.getUserId());
			
			List<ListAndRecord> sportList = listAndRecordDao.SportListById(user.getUserId());
			
			
			
			session.setAttribute("userList", userList);
			session.setAttribute("foodList", foodList);
			session.setAttribute("alcoholList", alcoholList);
			session.setAttribute("sportList", sportList);
    		session.setAttribute("user", user);

    		return "admin";
    	}
    	else {
    		//session.setAttribute("user", user);
    		recordService.setZeroPastRecords(user.getUserId());
    		Double smokeLevel = listAndRecordDao.getLatestSmokeDateRecord(user.getUserId());
    		ListAndRecord userAlcohol = listAndRecordDao.getLatestAlcoholRecord(user.getUserId());
    		ListAndRecord userMetsAndTime = listAndRecordDao.getLatestMetsAndTimeRecord(user.getUserId());
    		ListAndRecord userCalorieIntake = listAndRecordDao.getLatestCalorieIntake(user.getUserId());
    		ListAndRecord userWeight = listAndRecordDao.getLatestWeightRecordM(user.getUserId());
    		ListAndRecord userAlcoholDate = listAndRecordDao.getLatestAlcoholDateRecord(user.getUserId());

    		Integer alcoholLevel;
    		Double CaloriesBurned = userWeight.getValue2() * userMetsAndTime.getValue2() * userMetsAndTime.getValue3() * 1.05;
    		Double height = (double) (user.getHeight()/100.0);
    		Double bmi = (double) (userWeight.getValue2()/(height*height));
    		Integer calorieLevel = (int) (Math.ceil(userCalorieIntake.getValue2() - CaloriesBurned)/user.getGoalCalorie()*10);
    		int goalCalorie = user.getGoalCalorie();
    		
    		if(calorieLevel <= 0) {
    			calorieLevel = 1;
    		}
    		else if(calorieLevel >= 12) {
    			calorieLevel = 11;
    		}
    		if(smokeLevel <= 0) {
    			smokeLevel = 1.0;
    		}
    		if(userAlcohol.getValue2() >= 20) {
    			alcoholLevel = 2;
    		}
    		else {
    			alcoholLevel = 1;
    		}


    		Color SmokeColorLevel = colorDao.getSmokeColorLevel(smokeLevel);
    		Color AlcoholColorLevel = colorDao.getAlcoholColorLevel(alcoholLevel);
    		Color CalorieColorLevel = colorDao.getCalorieColorLevel(calorieLevel);
    		bmi = Math.floor(bmi * 10)/10;
    		Bmi bmipath = bmiDao.getBmiPath(bmi);
    		//計算したbmiをsessionに保存
    		session.setAttribute("bmiValue",bmi);

    		//ツールチップに表示する項目をsessionに保存する
    		System.out.println(smokeLevel);
    		String smokeMessage = "";
    		if (smokeLevel == 1.0) {
    			smokeMessage = "あなたの肺はきれいです";
    		} else {
    			smokeMessage = "禁煙"+(20 - smokeLevel) +"日目です";
    		}
    		session.setAttribute("lungWord", smokeMessage);
    		session.setAttribute("livarWord", "禁酒"+userAlcoholDate.getValue2()+"日目です。");
    		session.setAttribute("stomachGoalkcal", "目標摂取カロリーは"+goalCalorie+"Kcalです。");
    		session.setAttribute("stomachInputKcal", "摂取カロリーは"+ userCalorieIntake.getValue2()+"Kcalです。");
    		session.setAttribute("stomachOutputKcal", "消費カロリーは" + CaloriesBurned + "Kcalです。" );
    		recordService.achievementUnlock(user.getUserId());
    		
    		session.setAttribute("lungImg","../../" + SmokeColorLevel.getColorPath());
    		session.setAttribute("livarImg","../../" + AlcoholColorLevel.getColorPath());
    		session.setAttribute("stomachImg",CalorieColorLevel.getColorPath());
    		session.setAttribute("bmiImg",bmipath.getImgPath());
    		session.setAttribute("user", user);
    		
    		return "menu";
		}
    }

	//メニューから記録＆リスト画面に遷移(当日のデータ)
	@RequestMapping(value = "/record", method = RequestMethod.GET)
	public String record(@ModelAttribute("record") ListAndRecordForm form, Model model) {
		
		User user = (User) session.getAttribute("user");
		
		Date recordDate = Date.valueOf(request.getParameter("recordDate"));
		
		List<ListAndRecord> breakfastRecordList = listAndRecordDao.getFoodRecords(user.getUserId(), 1, 1, recordDate);

		List<ListAndRecord> lunchRecordList = listAndRecordDao.getFoodRecords(user.getUserId(), 1, 2, recordDate);

		List<ListAndRecord> dinnerRecordList = listAndRecordDao.getFoodRecords(user.getUserId(), 1, 3, recordDate);

		List<ListAndRecord> snackRecordList = listAndRecordDao.getFoodRecords(user.getUserId(), 1, 4, recordDate);

		List<ListAndRecord> sportRecordList = listAndRecordDao.getSportRecords(user.getUserId(), recordDate);

		List<ListAndRecord> alcoholRecordList = listAndRecordDao.getAlcoholRecords(user.getUserId(), recordDate);

		ListAndRecord smokeRecord = listAndRecordDao.getSmokeRecord(user.getUserId(), recordDate);

		ListAndRecord weightRecord = listAndRecordDao.getLatestWeightRecord(user.getUserId(), recordDate);

		
		model.addAttribute("dataDate", recordDate);
		model.addAttribute("breakfastRecordList", breakfastRecordList);
		model.addAttribute("lunchRecordList", lunchRecordList);
		model.addAttribute("dinnerRecordList", dinnerRecordList);
		model.addAttribute("snackRecordList", snackRecordList);
		model.addAttribute("sportRecordList", sportRecordList);
		model.addAttribute("alcoholRecordList", alcoholRecordList);
		model.addAttribute("smokeRecord", smokeRecord);
		model.addAttribute("weightRecord", weightRecord);

		return "record";
	}
    
    //記録画面から確定を押した際にメニュー画面に飛ぶ
	@RequestMapping(value = "/recordCommit", method = RequestMethod.POST)
	public String recordCommit(@ModelAttribute("index") UserForm form, Model model) {

		List<ListAndRecord> recordsList = new ArrayList<ListAndRecord>();
		
		List<ListAndRecord> listsList = new ArrayList<ListAndRecord>();

		//朝食記録追加
		for (int i = 0; request.getParameter("value1Bre" + i) != null; i++) {
			if (request.getParameter("delBre" + i) != null) {
				continue;
			}
			if (request.getParameter("addMyListBre" + i) != null) {
				ListAndRecord foodBreList = new ListAndRecord(0, 1, 1, request.getParameter("value1Bre" + i), Double.parseDouble(request.getParameter("value2Bre" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(foodBreList);
			}
			ListAndRecord breakfastRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Bre" + i), Double.parseDouble(request.getParameter("value2Bre" + i)), Double.parseDouble(request.getParameter("value3Bre" + i)), 1.0, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(breakfastRecord);
		}

		//昼食記録追加
		for (int i = 0; request.getParameter("value1Lun" + i) != null; i++) {
			if (request.getParameter("delLun" + i) != null) {
				continue;
			}
			if (request.getParameter("addMyListLun" + i) != null) {
				ListAndRecord foodLunList = new ListAndRecord(0, 1, 1, request.getParameter("value1Lun" + i), Double.parseDouble(request.getParameter("value2Lun" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(foodLunList);
			}
			ListAndRecord lunchRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Lun" + i), Double.parseDouble(request.getParameter("value2Lun" + i)), Double.parseDouble(request.getParameter("value3Lun" + i)), 2.0, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(lunchRecord);
		}


		//夕食記録追加
		for (int i = 0; request.getParameter("value1Din" + i) != null; i++) {
			if (request.getParameter("delDin" + i) != null) {
				continue;
			}
			if (request.getParameter("addMyListDin" + i) != null) {
				ListAndRecord foodDinList = new ListAndRecord(0, 1, 1, request.getParameter("value1Din" + i), Double.parseDouble(request.getParameter("value2Din" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(foodDinList);
			}
			ListAndRecord dinnerRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Din" + i), Double.parseDouble(request.getParameter("value2Din" + i)), Double.parseDouble(request.getParameter("value3Din" + i)), 3.0, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(dinnerRecord);
		}

		//間食記録追加
		for (int i = 0; request.getParameter("value1Sna" + i) != null; i++) {
			if (request.getParameter("delSna" + i) != null) {
				continue;
			}
			if (request.getParameter("addMyListSna" + i) != null) {
				ListAndRecord foodSnaList = new ListAndRecord(0, 1, 1, request.getParameter("value1Sna" + i), Double.parseDouble(request.getParameter("value2Sna" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(foodSnaList);
			}
			ListAndRecord snackRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Sna" + i), Double.parseDouble(request.getParameter("value2Sna" + i)), Double.parseDouble(request.getParameter("value3Sna" + i)), 4.0, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(snackRecord);
		}

		//運動記録追加
		for (int i = 0; request.getParameter("value1Spo" + i) != null; i++) {
			if (request.getParameter("delSpo" + i) != null) {
				continue;
			}
			ListAndRecord sportRecord = new ListAndRecord(0, 2, 2, request.getParameter("value1Spo" + i), Double.parseDouble(request.getParameter("value2Spo" + i)), Double.parseDouble(request.getParameter("value3Spo" + i)), null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(sportRecord);
		}

		//たばこ記録挿入
		ListAndRecord smokeRecord = new ListAndRecord(0, 2, 3, null, null, Double.parseDouble(request.getParameter("value3Smo")), null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
		recordsList.add(smokeRecord);

		//アルコール記録挿入
		for (int i = 0; request.getParameter("value1Alc" + i) != null; i++) {
			if (request.getParameter("delAlc" + i) != null) {
				continue;
			}
			if (request.getParameter("addMyListAlc" + i) != null) {
				ListAndRecord alcList = new ListAndRecord(0, 1, 4, request.getParameter("value1Alc" + i), Double.parseDouble(request.getParameter("value2Alc" + i)), null, Double.parseDouble(request.getParameter("value4Alc" + i)), Double.parseDouble(request.getParameter("value5Alc" + i)), null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(alcList);
			}
			ListAndRecord alcoholRecord = new ListAndRecord(0, 2, 4, request.getParameter("value1Alc" + i), Double.parseDouble(request.getParameter("value2Alc" + i)), Double.parseDouble(request.getParameter("value3Alc" + i)), Double.parseDouble(request.getParameter("value4Alc" + i)), Double.parseDouble(request.getParameter("value5Alc" + i)), null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(alcoholRecord);
		}

		//体重記録挿入
		ListAndRecord weightRecord = new ListAndRecord(0, 2, 5, null, Double.parseDouble(request.getParameter("value2Wei")), Double.parseDouble(request.getParameter("value3Wei")), null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
		recordsList.add(weightRecord);

		listAndRecordDao.insertRecord(((User) session.getAttribute("user")).getUserId(), recordsList, Date.valueOf(request.getParameter("createDate")));
		
		listAndRecordDao.insertMyList(((User) session.getAttribute("user")).getUserId(), listsList);
		
		
		User user = (User) session.getAttribute("user");
		recordService.setZeroPastRecords(user.getUserId());
		Double smokeLevel = listAndRecordDao.getLatestSmokeDateRecord(user.getUserId());
		ListAndRecord userAlcohol = listAndRecordDao.getLatestAlcoholRecord(user.getUserId());
		ListAndRecord userMetsAndTime = listAndRecordDao.getLatestMetsAndTimeRecord(user.getUserId());
		ListAndRecord userCalorieIntake = listAndRecordDao.getLatestCalorieIntake(user.getUserId());
		ListAndRecord userWeight = listAndRecordDao.getLatestWeightRecordM(user.getUserId());
		ListAndRecord userAlcoholDate = listAndRecordDao.getLatestAlcoholDateRecord(user.getUserId());
		
		Integer alcoholLevel;
		Double CaloriesBurned = userWeight.getValue2() * userMetsAndTime.getValue2() * userMetsAndTime.getValue3() * 1.05;
		Double height = (double) (user.getHeight()/100.0);
		Double bmi = (double) (userWeight.getValue2()/(height*height));
		Integer calorieLevel = (int) (Math.ceil(userCalorieIntake.getValue2() - CaloriesBurned)/user.getGoalCalorie()*10);
		
		if(calorieLevel <= 0) {
			calorieLevel = 1;
		}
		else if(calorieLevel >= 12) {
			calorieLevel = 11;
		}
		if(smokeLevel <= 0) {
			smokeLevel = 1.0;
		}
		if(userAlcohol.getValue2() >= 20) {
			alcoholLevel = 2;
		}
		else {
			alcoholLevel = 1;
		}

		Color SmokeColorLevel = colorDao.getSmokeColorLevel(smokeLevel);
		Color AlcoholColorLevel = colorDao.getAlcoholColorLevel(alcoholLevel);
		Color CalorieColorLevel = colorDao.getCalorieColorLevel(calorieLevel);
		bmi = Math.floor(bmi * 10)/10;
		Bmi bmipath = bmiDao.getBmiPath(bmi);
		//計算したbmiをsessionに保存
		session.setAttribute("bmiValue",bmi);

		//ツールチップに表示する項目をsessionに保存する
		System.out.println(smokeLevel);
		String smokeMessage = "";
		if (smokeLevel == 1.0) {
			smokeMessage = "あなたの肺はきれいです";
		} else {
			smokeMessage = "禁煙"+(20 - smokeLevel) +"日目です";
		}
		session.setAttribute("lungWord", smokeMessage);
		session.setAttribute("livarWord", "禁酒"+userAlcoholDate.getValue2()+"日目です。");
		session.setAttribute("stomachGoalkcal", "目標摂取カロリーは"+user.getGoalCalorie()+"Kcalです。");
		session.setAttribute("stomachInputKcal", "摂取カロリーは"+ userCalorieIntake.getValue2()+"Kcalです。");
		session.setAttribute("stomachOutputKcal", "消費カロリーは" + CaloriesBurned + "Kcalです。" );
		
		session.setAttribute("lungImg","../../" + SmokeColorLevel.getColorPath());
		session.setAttribute("livarImg","../../" + AlcoholColorLevel.getColorPath());
		session.setAttribute("stomachImg",CalorieColorLevel.getColorPath());
		session.setAttribute("bmiImg",bmipath.getImgPath());
		recordService.achievementUnlock(user.getUserId());
		session.setAttribute("calorieColorPath", CalorieColorLevel.getColorPath());
		session.setAttribute("smokeColorPath", SmokeColorLevel.getColorPath());
		session.setAttribute("alcoholColorPath", AlcoholColorLevel.getColorPath());
		session.setAttribute("user", user);
		
		
		return "menu";

	}
    
    //統計画面に遷移（ゆうちゃんへ、GETにしてね）
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics(@ModelAttribute("index") UserForm form, Model model) {
    	
    	
    	Date statisticsDate = Date.valueOf(request.getParameter("statisticsDate"));
    	
    	model.addAttribute("statisticsDate", statisticsDate);
        return "statistics";
    }

  //マイリスト確定時
  	@RequestMapping(value = "/listCommit", method = RequestMethod.POST)
  	public String listCommit(@ModelAttribute("index") UserForm form, Model model) {

  		List<ListAndRecord> listsList = new ArrayList<ListAndRecord>();

  		//食事リスト
  		for (int i = 0; request.getParameter("value1Food" + i) != null; i++) {
  			if (request.getParameter("delFood" + i) != null) {
  				continue;
  			}
  			ListAndRecord foodData = new ListAndRecord(0, 1, 1, request.getParameter("value1Food" + i), Double.parseDouble(request.getParameter("value2Food" + i)), null, 1.0, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
  			listsList.add(foodData);
  		}

  		//アルコールリスト
  		for (int i = 0; request.getParameter("value1Alc" + i) != null; i++) {
  			if (request.getParameter("delAlc" + i) != null) {
  				continue;
  			}
  			ListAndRecord alcoholData = new ListAndRecord(0, 1, 4, request.getParameter("value1Alc" + i), Double.parseDouble(request.getParameter("value2Alc" + i)), null, Double.parseDouble(request.getParameter("value4Alc" + i)), Double.parseDouble(request.getParameter("value5Alc" + i)), null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
  			listsList.add(alcoholData);
  		}
  		
  		listAndRecordDao.ediMyList(((User) session.getAttribute("user")).getUserId(), listsList);

  		return "menu";

  	}

	//マイリスト編集画面から登録ボタンでメニュー画面に遷移
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String registList(@ModelAttribute("index") UserForm form, Model model) {

		return "menu";
	}

	// お問い合わせ画面から登録ボタンでメニュー画面に遷移
	@RequestMapping(value = "/information", method = RequestMethod.POST)
	public String registInformation(@ModelAttribute("information") InformationForm Iform, @ModelAttribute("index") UserForm form, Model model) {
		
		// ここはログイン時にsession保存したユーザー情報を使って、user_idを取得する。
		User user = (User) session.getAttribute("user");
		int user_id = user.getUserId();

		// ここは仮でuser_idを取得する。
		//int user_id = 1;
		
		String title = Iform.getTitle();
		String contents = Iform.getContents();
		
		
		String result = informationDao.InformationRegister(title, contents, user_id );
		
		if(("正常に登録できました").equals(result)) {
			
			/*
			 * ユーザーの状態に対応した画像のパスをsessionに保存しているからそれを取ってきて、
			 * メインメニューページに表示する。今は画像をベタ打ちで入力している状態
			 */
				//肺の画像をsessionから取得する
				//String lungImg = (String) session.getAttribute("lungImg");
				//肝臓の画像をsessionから取得する
				//String livarImg = (String) session.getAttribute("livarImg");
				//胃の画像をsessionから取得する
				//String stomachImg = (String) session.getAttribute("stomachImg");
				//BMIの画像をsessionから取得する
				//String bmiImg = (String) session.getAttribute("bmiImg");
//				
//				String lungImg = "../../images/lung.png";
//				String livarImg = "../../images/livar0.png" ;
//				String stomachImg = "../../images/stomach.png" ;
//				String bmiImg = "../../images/bmi3.png";
				
				//ここはセッションに保存されているユーザーのBMIを取得してビュー側に値を渡す。
//				double bmiValue = 22.4;
//	    		model.addAttribute("bmiValue",bmiValue);
//				
//				model.addAttribute("lungImg",lungImg );
//				model.addAttribute("livarImg",livarImg );
//				model.addAttribute("stomachImg",stomachImg );
//				model.addAttribute("bmiImg",bmiImg );

				return "menu";
			
		}else {
			model.addAttribute("msg",result );
			return "information";
		}
		
		// メインメニュー画面に戻るときの処理をどうやるのかを周りの人に聞く。
	}

	// 戻るボタンを押すと、メニュー画面に遷移
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back(@ModelAttribute("index") UserForm form, Model model) {
		User user = (User) session.getAttribute("user");
		if(user.getRoleId() == 0) {
			return "admin";
		}
		return "menu";
	}
	
	//ハンバーガーメニューからアカウント管理へ
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(@ModelAttribute("index") UserForm form, Model model) {
		User user = (User) session.getAttribute("user");
		List<Achievements> achievementList = achievementsDao.findByAll(user.getUserId());
		session.setAttribute("achievementsList",achievementList);
		model.addAttribute("achievementName",achievementsDao.findById(user.getAchievementId()).getAchievementName());
		return "account";
	}
		
	//アカウント管理で登録ボタンを押すと、メニュー画面に遷移
    @RequestMapping(value = "/accountRegist", method = RequestMethod.POST)
    public String accountRegist(@Validated  @ModelAttribute("index") UserForm form, BindingResult bindingResult, Model model) {
    	if (bindingResult.hasErrors()) {
    		return "account";
        }
    	
    	
    	Integer id = form.getUserId();
    	User user = userDao.findById(id);
    	String name = form.getName();
    	String mail = form.getMail();
    	String pass = form.getPassword();
    	Integer sex = form.getSex();
    	Date birthDate = (Date) user.getBirth();
    	Double height = form.getHeight();
    	Integer achievementId = form.getAchievementId();
    	Integer time = form.getGoalExerciseTime();
    	Integer calorise = form.getGoalCalorise();
    	Integer rank = form.getRankFlag();
    	Integer smoke = form.getSmokeFlag();
    	Integer alcohol = form.getAlcoholFlag();
    	
    	userDao.update(id, name, mail, pass, sex, birthDate, height, achievementId, time, calorise, rank, smoke, alcohol);
    	
    	user = userDao.findById(id);
    	

		ListAndRecord userMetsAndTime = listAndRecordDao.getLatestMetsAndTimeRecord(user.getUserId());
		ListAndRecord userCalorieIntake = listAndRecordDao.getLatestCalorieIntake(user.getUserId());
    	
    	ListAndRecord userWeight = listAndRecordDao.getLatestWeightRecordM(user.getUserId());
    	Double CaloriesBurned = userWeight.getValue2() * userMetsAndTime.getValue2() * userMetsAndTime.getValue3() * 1.05;
    	Double height1 = (double) (user.getHeight()/100.0);
    	Double bmi = (double) (userWeight.getValue2()/(height1*height1));
    	Integer calorieLevel = (int) (Math.ceil(userCalorieIntake.getValue2() - CaloriesBurned)/user.getGoalCalorie()*10);
    	int goalCalorie = user.getGoalCalorie();

    	if(calorieLevel <= 0) {
    		calorieLevel = 1;
    	}else if(calorieLevel >= 12) {
    		calorieLevel = 11;
    	}

    	Color CalorieColorLevel = colorDao.getCalorieColorLevel(calorieLevel);


    	bmi = Math.floor(bmi * 10)/10;
    	Bmi bmipath = bmiDao.getBmiPath(bmi);
    	//計算したbmiをsessionに保存
    	session.setAttribute("bmiValue",bmi);


    	session.setAttribute("stomachGoalkcal", "目標摂取カロリーは"+goalCalorie+"Kcalです。");
    	session.setAttribute("stomachInputKcal", "摂取カロリーは"+ userCalorieIntake.getValue2()+"Kcalです。");
    	session.setAttribute("stomachOutputKcal", "消費カロリーは" + CaloriesBurned + "Kcalです。" );

    	session.setAttribute("stomachImg",CalorieColorLevel.getColorPath());
    	session.setAttribute("bmiImg",bmipath.getImgPath());

    	
    	
    	session.setAttribute("user", user);
        return "menu";
    }

	// ハンバーガーメニューからランキングへ
	@RequestMapping(value = "/rank", method = RequestMethod.GET)
	public String rank(@ModelAttribute("index") ListAndRecordForm form, Model model) {
		// 日ごとの合計運動時間を取得して、運動時間が多い順に並べる。
		List<Rank> DayRanking = listAndRecordDao.DayRanking();
		model.addAttribute("DayRanking", DayRanking);
		// 週ごとの合計運動時間を取得して、運動時間が多い順に並べる。
		List<Rank> WeekRanking = listAndRecordDao.WeekRanking();
		model.addAttribute("WeekRanking", WeekRanking);
		// ユーザーごとの獲得している称号の合計ポイントを取得して、ポイントが多い順に並べる。
		List<Rank> AchievementRanking = listAndRecordDao.AchievementRanking();
		model.addAttribute("AchievementRanking", AchievementRanking);


		return "rank";
	}
	// ハンバーガーメニューからリスト編集へ
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("index") ListAndRecordForm form, Model model) {
		
		User user = (User)session.getAttribute("user");
		int user_id = user.getUserId();
		
		// user_id =1が登録した食事リストを取得する
		List<ListAndRecord> foodList = listAndRecordDao.FoodListById(user_id);
		if (foodList != null) {
			for (ListAndRecord foodData : foodList) {
				foodData.setValue1(foodData.getValue1().substring(0, foodData.getValue1().length()-8));
			}
		}
		model.addAttribute("foodList", foodList);

		// user_id =1が登録したお酒リストを取得する
		List<ListAndRecord> alcoholList = listAndRecordDao.AlcoholListById(user_id);
		if (alcoholList != null) {
			for (ListAndRecord alcoholData : alcoholList) {
				alcoholData.setValue1(alcoholData.getValue1().substring(0, alcoholData.getValue1().length()-8));
			}
		}
		model.addAttribute("alcoholList", alcoholList);
		return "list";
	}

	// ハンバーガーメニューからお問い合わせへ
	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information(@ModelAttribute("information") InformationForm LRform, Model model) {

		return "information";
	}

	//管理者ページでロゴをクリックで管理者ページへ
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(@ModelAttribute("index") UserForm form, Model model) {


		return "admin";
	}
	
	//ハンバーガーメニューから管理者お問い合わせへ
	@RequestMapping(value = "/adminInformation", method = RequestMethod.GET)
	public String adminInformation(@ModelAttribute("index") InformationForm form, Model model) {
		List<Information> infoList =informationDao.findByAll();
		session.setAttribute("infoList",infoList);

		return "adminInformation";
	}
	
	//管理者のリスト管理確定時
		@RequestMapping(value = "/adminListCommit", method = RequestMethod.POST)
		public String adminListCommit(@ModelAttribute("index") UserForm form, Model model) {

			List<ListAndRecord> listsList = new ArrayList<ListAndRecord>();

			//食事リスト
			for (int i = 0; request.getParameter("value1Food" + i) != null; i++) {
				if (request.getParameter("delFood" + i) != null) {
					continue;
				}
				ListAndRecord foodData = new ListAndRecord(0, 1, 1, request.getParameter("value1Food" + i), Double.parseDouble(request.getParameter("value2Food" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(foodData);
			}
			
			//運動リスト
			for (int i = 0; request.getParameter("value1Spo" + i) != null; i++) {
				if (request.getParameter("delSpo" + i) != null) {
					continue;
				}
				ListAndRecord sportData = new ListAndRecord(0, 1, 2, request.getParameter("value1Spo" + i), Double.parseDouble(request.getParameter("value2Spo" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(sportData);
			}

			//アルコールリスト
			for (int i = 0; request.getParameter("value1Alc" + i) != null; i++) {
				if (request.getParameter("delAlc" + i) != null) {
					continue;
				}
				ListAndRecord alcoholData = new ListAndRecord(0, 1, 4, request.getParameter("value1Alc" + i), Double.parseDouble(request.getParameter("value2Alc" + i)), null, Double.parseDouble(request.getParameter("value4Alc" + i)), Double.parseDouble(request.getParameter("value5Alc" + i)), null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(alcoholData);
			}
			
			listAndRecordDao.editAdminList(((User) session.getAttribute("user")).getUserId(), listsList);
			
			List<User> userList = userDao.getAllUser();
			
			List<ListAndRecord> foodList = listAndRecordDao.FoodListById(((User) session.getAttribute("user")).getUserId());
			
			List<ListAndRecord> alcoholList = listAndRecordDao.AlcoholListById(((User) session.getAttribute("user")).getUserId());
			
			List<ListAndRecord> sportList = listAndRecordDao.SportListById(((User) session.getAttribute("user")).getUserId());
			
			session.setAttribute("userList", userList);
			session.setAttribute("foodList", foodList);
			session.setAttribute("alcoholList", alcoholList);
			session.setAttribute("sportList", sportList);
			

			return "admin";

		}
	
	//管理者お問い合わせの検索
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String adminSearch(@ModelAttribute("index") InformationForm form, Model model) {
		List<Information> infoList =informationDao.findBySearch(form.getKeyword());
		session.setAttribute("infoList",infoList);
		return "adminInformation";
	}
	
	//管理者お問い合わせで既読にする
	@RequestMapping(value = "/alreadyRead", method = RequestMethod.GET)
	public String adminRead(@ModelAttribute("index") InformationForm form, Model model) {
		informationDao.updateAll();
		List<Information> infoList =informationDao.findByAll();
		session.setAttribute("infoList",infoList);
		
		return "adminInformation";
	}
	
	//管理者お問い合わせから管理者お問い合わせ詳細へ
	@RequestMapping(value = "/informationDetail", method = RequestMethod.POST)
	public String informationDetail(@ModelAttribute("index") InformationForm form, Model model) {
		Integer informationId = form.getInformationId();
		Information information = informationDao.findById(informationId);
		model.addAttribute("information",information);
		return "informationDetail";
	}

	//対処済みにするを押すと、admin画面に遷移
	@RequestMapping(value = "/process", method = RequestMethod.GET)
	public String process(@ModelAttribute("index") InformationForm form, Model model) {
		Integer informationId = form.getInformationId();
		
		informationDao.update(informationId, 1, 1);
		
		List<Information> infoList =informationDao.findByAll();
		session.setAttribute("infoList",infoList);

		return "adminInformation";
	}
	
	//一覧に戻るボタンを押すと、informationDetail画面に遷移
	@RequestMapping(value = "/backList", method = RequestMethod.GET)
	public String backList(@ModelAttribute("index") InformationForm form, Model model) {
		Integer informationId = form.getInformationId();
		Integer doneFlag = form.getDoneFlag();
		
		informationDao.update(informationId, 1, doneFlag);
		
		
		List<Information> infoList =informationDao.findByAll();
		session.setAttribute("infoList",infoList);

		return "adminInformation";
	}

//	//戻るボタンを押すと、admin画面に遷移
//	@RequestMapping(value = "/backAdmin", method = RequestMethod.POST)
//	public String backAdmin(@ModelAttribute("index") UserForm form, Model model) {
//		
//
//		return "admin";
//	}

	//ハンバーガーメニューからログアウトへ
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@ModelAttribute("index") UserForm form, Model model) {
		session.invalidate();

		return "logout";
	}
}
