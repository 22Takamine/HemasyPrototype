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
import com.example.dao.InformationDao;
import com.example.dao.ListAndRecordDao;
import com.example.dao.UserDao;
import com.example.entity.Achievements;
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
	AchievementsDao achievementsDao;

    @Autowired
    RecordService recordService;



	//最初にここにきて、login画面にいくyu
	@RequestMapping({ "/", "/index" })
	public String index(@ModelAttribute("index") IndexForm form, Model model) {
		return "login";
	}
	
	//ログイン成功時にメニュー画面に遷移
	@RequestMapping(value = "/result", params="login", method = RequestMethod.POST)
	public String login(@Validated @ModelAttribute("index") IndexForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {	
			return "login";
		}
		
		
		User user = userDao.findIdAndPass(form.getMail(), form.getPassword());


    	if(user == null) {
    		model.addAttribute("msg","メールアドレスまたはパスワードが間違っています");
    		return "login";
    	}
    	else if(user.getRoleId() == 0) {
    		
    		session.setAttribute("user", user);

    		return "admin";
    	}
    	else {
    		
    		session.setAttribute("user", user);
    		
    		//以下にDBから取得してきた画像のパスを入れる。
    		//（まっしーへ　ここに取ってきた画像のパスを入れるまでお願いします。）
    		
    		String lungImg = "../../images/lung.png";
    		String livarImg = "../../images/livar0.png" ;
    		String stomachImg = "../../images/stomach.png" ;
    		String bmiImg = "../../images/bmi3.png" ;
    		
    		//ここでsessionに画像のパスを保存する。
    		//session.setAttribute("lungImg", lungImg);
    		//session.setAttribute("livarImg", livarImg);
    		//session.setAttribute("stomachImg", stomachImg);
    		//session.setAttribute("bmiImg", bmiImg);
    		
    		//計算したbmiをsessionに保存
    		//session.setAttribute("bmiValue", bmiValue);
    		
    		double bmiValue = 22.4;
    		model.addAttribute("bmiValue",bmiValue);
    		
    		
    		String lungWord = "禁煙○○日目です";
    		String livarWord = "禁酒○○日目です。" ;
    		String stomachGoalkcal = "目標摂取カロリーは○○Kcalです。" ;
    		String stomachInputKcal = "摂取カロリーは○○Kcalです。" ;
    		String stomachOutputKcal = "消費カロリーは○○Kcalです。" ;
    		
    		//ツールチップに表示する項目をsessionに保存する
    		//session.setAttribute("lungWord", lungWord);
    		//session.setAttribute("livarWord", livarWord);
    		//session.setAttribute("stomachGoalkcal", stomachGoalkcal);
    		//session.setAttribute("stomachInputKcal", stomachInputKcal);
    		//session.setAttribute("stomachOutputKcal", stomachOutputKcal);
    		
    		model.addAttribute("lungImg",lungImg );
    		model.addAttribute("livarImg",livarImg );
    		model.addAttribute("stomachImg",stomachImg );
    		model.addAttribute("bmiImg",bmiImg );
    		
    		model.addAttribute("lungWord", lungWord);
    		model.addAttribute("livarWord", livarWord);
    		model.addAttribute("stomachGoalkcal", stomachGoalkcal);
    		model.addAttribute("stomachInputKcal", stomachInputKcal);
    		model.addAttribute("stomachOutputKcal", stomachOutputKcal);
    		
    		return "menu";
    	}
    }
	
	//ログイン画面から、新規登録画面に遷移
	@RequestMapping(value = "/result", params="register", method = RequestMethod.POST)
	public String register(@ModelAttribute("index") UserForm form, Model model) {
    	
        return "register";
    }
	
	//新規登録画面で登録ボタンを押した際に、ログイン画面に遷移
	@RequestMapping(value = "/loginBack", method = RequestMethod.POST)
	public String loginBack(@Validated @ModelAttribute("index") UserForm form,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		
		User user = new User(form.getName(),form.getMail(), form.getPassword(),form.getSex(),form.getBirthDate(),
				form.getHeight(),form.getGoalExerciseTime(),form.getGoalCalorise(),form.getRankFlag(),form.getAlcoholFlag(),form.getSmokeFlag(),form.getRoleId());
		
		userDao.insert(user);

		return "login";
    }
	
	//記録＆リスト画面に遷移(ゆうちゃんへGETにしてね)
	@RequestMapping(value = "/record", method = RequestMethod.GET)
	public String record(@ModelAttribute("record") ListAndRecordForm form, Model model) {
		
		List<ListAndRecord> breakfastRecordList = listAndRecordDao.getBreakfastRecords(2, Date.valueOf("2022-06-20"));
		
		List<ListAndRecord> lunchRecordList = listAndRecordDao.getLunchRecords(2, Date.valueOf("2022-06-20"));
		
		List<ListAndRecord> dinnerRecordList = listAndRecordDao.getDinnerRecords(2, Date.valueOf("2022-06-20"));
		
		List<ListAndRecord> snackRecordList = listAndRecordDao.getSnackRecords(2, Date.valueOf("2022-06-20"));
		
		List<ListAndRecord> sportRecordList = listAndRecordDao.getSportRecords(2, Date.valueOf("2022-06-20"));
		
		List<ListAndRecord> alcoholRecordList = listAndRecordDao.getAlcoholRecords(2, Date.valueOf("2022-06-20"));
		
		ListAndRecord latestSmokeRecord = listAndRecordDao.getSmokeRecord(2, Date.valueOf("2022-6-20"));
		
		ListAndRecord latestWeightRecord = listAndRecordDao.getLatestWeightRecord(2);
		
		System.out.println(Date.valueOf("2022-06-20"));
		
		System.out.println("えええ" + sportRecordList.size());
		
		/*
		 * System.out.println(latestSmokeRecord.getValue3());
		 */		
		System.out.println(dinnerRecordList.size());
		
		System.out.println(snackRecordList.size());
		
		model.addAttribute("breakfastRecordList", breakfastRecordList);
		model.addAttribute("lunchRecordList", lunchRecordList);
		model.addAttribute("dinnerRecordList", dinnerRecordList);
		model.addAttribute("snackRecordList", snackRecordList);
		model.addAttribute("sportRecordList", sportRecordList);
		model.addAttribute("alcoholRecordList", alcoholRecordList);
		model.addAttribute("smokeRecord", latestSmokeRecord);
		model.addAttribute("weightRecord", latestWeightRecord);
		
	    return "record";

	}
    
    
    @RequestMapping(value = "/recordCommit", method = RequestMethod.POST)
    public String recordCommit(@ModelAttribute("index") UserForm form, Model model) {
    	
    	List<ListAndRecord> listAndRecordLists = new ArrayList<ListAndRecord>();
    	
    	//朝食記録追加
    	for (int i = 0; request.getParameter("value1Bre" + i) != null; i++) {
    		System.out.println(request.getParameter("delBre" + i) + "かくにん");
    		if (request.getParameter("delBre" + i) != null) {
    			System.out.println("けすぞ");
    			continue;
    		}
    		ListAndRecord breakfastRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Bre" + i), Integer.parseInt(request.getParameter("value2Bre" + i)), Integer.parseInt(request.getParameter("value3Bre" + i)), 1, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
    		listAndRecordLists.add(breakfastRecord);
    		System.out.println("はじまり" + breakfastRecord.getListsAndRecordsId());
    		System.out.println(breakfastRecord.getCategory());
    		System.out.println(breakfastRecord.getType());
    		System.out.println(breakfastRecord.getValue1());
    		System.out.println(breakfastRecord.getValue2());
    		System.out.println(breakfastRecord.getValue3());
    		System.out.println(breakfastRecord.getValue4());
    		System.out.println(breakfastRecord.getValue5());
    		System.out.println(breakfastRecord.getCreateDate());
    		System.out.println(breakfastRecord.getUserId());
    	}
    	
    	//昼食記録追加
    	for (int i = 0; request.getParameter("value1Lun" + i) != null; i++) {
    		System.out.println(request.getParameter("delLun" + i) + "かくにん");
    		if (request.getParameter("delLun" + i) != null) {
    			System.out.println("けすぞ");
    			continue;
    		}
    		ListAndRecord lunchRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Lun" + i), Integer.parseInt(request.getParameter("value2Lun" + i)), Integer.parseInt(request.getParameter("value3Lun" + i)), 2, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
    		listAndRecordLists.add(lunchRecord);
    		System.out.println("はじまり" + lunchRecord.getListsAndRecordsId());
    		System.out.println(lunchRecord.getCategory());
    		System.out.println(lunchRecord.getType());
    		System.out.println(lunchRecord.getValue1());
    		System.out.println(lunchRecord.getValue2());
    		System.out.println(lunchRecord.getValue3());
    		System.out.println(lunchRecord.getValue4());
    		System.out.println(lunchRecord.getValue5());
    		System.out.println(lunchRecord.getCreateDate());
    		System.out.println(lunchRecord.getUserId());
    	}
    	
    	
    	//夕食記録追加
    	for (int i = 0; request.getParameter("value1Din" + i) != null; i++) {
    		System.out.println(request.getParameter("delDin" + i) + "かくにん");
    		if (request.getParameter("delDin" + i) != null) {
    			System.out.println("けすぞ");
    			continue;
    		}
    		ListAndRecord dinnerRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Din" + i), Integer.parseInt(request.getParameter("value2Din" + i)), Integer.parseInt(request.getParameter("value3Din" + i)), 3, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
    		listAndRecordLists.add(dinnerRecord);
    		System.out.println("はじまり" + dinnerRecord.getListsAndRecordsId());
    		System.out.println(dinnerRecord.getCategory());
    		System.out.println(dinnerRecord.getType());
    		System.out.println(dinnerRecord.getValue1());
    		System.out.println(dinnerRecord.getValue2());
    		System.out.println(dinnerRecord.getValue3());
    		System.out.println(dinnerRecord.getValue4());
    		System.out.println(dinnerRecord.getValue5());
    		System.out.println(dinnerRecord.getCreateDate());
    		System.out.println(dinnerRecord.getUserId());
    	}
    	
    	//間食記録追加
    	for (int i = 0; request.getParameter("value1Sna" + i) != null; i++) {
    		System.out.println(request.getParameter("delSna" + i) + "かくにん");
    		if (request.getParameter("delSna" + i) != null) {
    			System.out.println("けすぞ");
    			continue;
    		}
    		ListAndRecord snackRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Sna" + i), Integer.parseInt(request.getParameter("value2Sna" + i)), Integer.parseInt(request.getParameter("value3Sna" + i)), 4, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
    		listAndRecordLists.add(snackRecord);
    		System.out.println("はじまり" + snackRecord.getListsAndRecordsId());
    		System.out.println(snackRecord.getCategory());
    		System.out.println(snackRecord.getType());
    		System.out.println(snackRecord.getValue1());
    		System.out.println(snackRecord.getValue2());
    		System.out.println(snackRecord.getValue3());
    		System.out.println(snackRecord.getValue4());
    		System.out.println(snackRecord.getValue5());
    		System.out.println(snackRecord.getCreateDate());
    		System.out.println(snackRecord.getUserId());
    	}
    	
    	//運動記録追加
    	for (int i = 0; request.getParameter("value1Spo" + i) != null; i++) {
    		System.out.println(request.getParameter("delSpo" + i) + "かくにん");
    		if (request.getParameter("delSpo" + i) != null) {
    			System.out.println("けすぞ");
    			continue;
    		}
    		ListAndRecord sportRecord = new ListAndRecord(0, 2, 2, request.getParameter("value1Spo" + i), Integer.parseInt(request.getParameter("value2Spo" + i)), Integer.parseInt(request.getParameter("value3Spo" + i)), null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
    		listAndRecordLists.add(sportRecord);
    		System.out.println("はじまり" + sportRecord.getListsAndRecordsId());
    		System.out.println(sportRecord.getCategory());
    		System.out.println(sportRecord.getType());
    		System.out.println(sportRecord.getValue1());
    		System.out.println(sportRecord.getValue2());
    		System.out.println(sportRecord.getValue3());
    		System.out.println(sportRecord.getValue4());
    		System.out.println(sportRecord.getValue5());
    		System.out.println(sportRecord.getCreateDate());
    		System.out.println(sportRecord.getUserId());
    	}
    	
    	//たばこ記録挿入
    	ListAndRecord smokeRecord = new ListAndRecord(0, 2, 3, null, null, Integer.parseInt(request.getParameter("value3Smo")), null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
    	listAndRecordLists.add(smokeRecord);
    	
    	//アルコール記録挿入
    	for (int i = 0; request.getParameter("value1Alc" + i) != null; i++) {
    		System.out.println(request.getParameter("delAlc" + i) + "かくにん");
    		if (request.getParameter("delAlc" + i) != null) {
    			System.out.println("けすぞ");
    			continue;
    		}
    		ListAndRecord alcoholRecord = new ListAndRecord(0, 2, 4, request.getParameter("value1Alc" + i), Integer.parseInt(request.getParameter("value2Alc" + i)), Integer.parseInt(request.getParameter("value3Alc" + i)), Integer.parseInt(request.getParameter("value4Alc" + i)), Integer.parseInt(request.getParameter("value5Alc" + i)), null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
    		listAndRecordLists.add(alcoholRecord);
    		System.out.println("はじまり" + alcoholRecord.getListsAndRecordsId());
    		System.out.println(alcoholRecord.getCategory());
    		System.out.println(alcoholRecord.getType());
    		System.out.println(alcoholRecord.getValue1());
    		System.out.println(alcoholRecord.getValue2());
    		System.out.println(alcoholRecord.getValue3());
    		System.out.println(alcoholRecord.getValue4());
    		System.out.println(alcoholRecord.getValue5());
    		System.out.println(alcoholRecord.getCreateDate());
    		System.out.println(alcoholRecord.getUserId());
    	}
    	
    	//体重記録挿入
    	System.out.println(request.getParameter("value2Wei") + "aaa" +  request.getParameter("value3Wei"));
    	ListAndRecord weightRecord = new ListAndRecord(0, 2, 5, null, Integer.parseInt(request.getParameter("value2Wei")), Integer.parseInt(request.getParameter("value3Wei")), null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
    	listAndRecordLists.add(weightRecord);
    	
    	System.out.println(listAndRecordLists.size());
    	
//    	listAndRecordDao.insertRecord(listAndRecordLists);
    	
//    	System.out.println(request.getParameter("value1Bre0"));
//    	System.out.println(request.getParameter("value1Bre1"));
//    	System.out.println(request.getParameter("countBre0"));
//    	System.out.println(request.getParameter("countBre1"));
//    	System.out.println(request.getParameter("countBre"));
    	
    	//実装時に使う(川満さんに聞いてね)
//    	int userId = 1;
//    	User user = userDao.findById(userId);
//    	recordService.setZeroPastRecords(user);
//    	
    	return "menu";
    	
    }
    
    //統計画面に遷移（ゆうちゃんへ、GETにしてね）
    @RequestMapping(value = "/statistics", method = RequestMethod.GET)
    public String statistics(@ModelAttribute("index") UserForm form, Model model) {
    	
        return "statistics";
    }

	// 記録画面から登録ボタンでメニュー画面に遷移
	@RequestMapping(value = "/recordRegist", method = RequestMethod.POST)
	public String recordRegist(@ModelAttribute("index") UserForm form, Model model) {

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
		
		System.out.println(title);
		System.out.println(contents);
		
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
				
				String lungImg = "../../images/lung.png";
				String livarImg = "../../images/livar0.png" ;
				String stomachImg = "../../images/stomach.png" ;
				String bmiImg = "../../images/bmi3.png";
				
				//ここはセッションに保存されているユーザーのBMIを取得してビュー側に値を渡す。
				double bmiValue = 22.4;
	    		model.addAttribute("bmiValue",bmiValue);
				
				model.addAttribute("lungImg",lungImg );
				model.addAttribute("livarImg",livarImg );
				model.addAttribute("stomachImg",stomachImg );
				model.addAttribute("bmiImg",bmiImg );

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

		return "menu";
	}
	
	//ハンバーガーメニューからアカウント管理へ
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(@ModelAttribute("index") UserForm form, Model model) {
		User user = (User) session.getAttribute("user");
		List<Achievements> achievementList = achievementsDao.findByAll();
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
    	String name = form.getName();
    	String mail = form.getMail();
    	String pass = form.getPassword();
    	Integer sex = form.getSex();
    	Date birthDate = form.getBirthDate();
    	Double height = form.getHeight();
    	Integer achievementId = form.getAchievementId();
    	Integer time = form.getGoalExerciseTime();
    	Integer calorise = form.getGoalCalorise();
    	Integer rank = form.getRankFlag();
    	Integer smoke = form.getSmokeFlag();
    	Integer alcohol = form.getAlcoholFlag();
    	
    	userDao.update(id, name, mail, pass, sex, birthDate, height, achievementId, time, calorise, rank, smoke, alcohol);
    	User user = userDao.findById(id);
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
		model.addAttribute("foodList", foodList);

		// user_id =1が登録したお酒リストを取得する(今は暫定でuser_idの部分に固定で２を入れている)
		List<ListAndRecord> alcoholList = listAndRecordDao.AlcoholListById(user_id);
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

	//戻るボタンを押すと、admin画面に遷移
	@RequestMapping(value = "/backAdmin", method = RequestMethod.POST)
	public String backAdmin(@ModelAttribute("index") UserForm form, Model model) {
		

		return "admin";
	}

	//ハンバーガーメニューからログアウトへ
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@ModelAttribute("index") UserForm form, Model model) {


		return "logout";
	}
}
