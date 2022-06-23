package com.example.controller;


import java.sql.Date;
import java.util.List;

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

import com.example.dao.InformationDao;
import com.example.dao.ListAndRecordDao;
import com.example.dao.UserDao;
import com.example.entity.ListAndRecord;
import com.example.entity.Rank;
import com.example.entity.User;
import com.example.form.IndexForm;
import com.example.form.InformationForm;
import com.example.form.ListAndRecordForm;
import com.example.form.UserForm;

@Controller
public class IndexController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	HttpSession session; 

    @Autowired
	UserDao userDao;

	@Autowired
	ListAndRecordDao listAndRecordDao;
	
	@Autowired
	InformationDao informationDao;

	//最初にここにきて、login画面にいく
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
	
	// 記録＆リスト画面に遷移
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	public String record(@ModelAttribute("index") UserForm form, Model model) {

		return "record";
	}

	// 統計画面に遷移
	@RequestMapping(value = "/statistics", method = RequestMethod.POST)
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
	
	//ハンバーガーメニューからアカウント管理へ
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(@ModelAttribute("index") UserForm form, Model model) {
		
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
    	 
        return "menu";
    }
	
	//戻るボタンを押すと、メニュー画面に遷移
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back(@ModelAttribute("index") UserForm form, Model model) {


		return "menu";
	}
	
	// お問い合わせ画面から登録ボタンでメニュー画面に遷移
	@RequestMapping(value = "/information", method = RequestMethod.POST)
	public String registInformation(@ModelAttribute("information") InformationForm Iform,@ModelAttribute("index") UserForm form, Model model) {
		
		 //ここはログイン時にsession保存したユーザー情報を使って、user_idを取得する。
		 User user = (User)session.getAttribute("user");
		 int user_id = user.getUserId();
		
		String title = Iform.getTitle();
		String contents = Iform.getContents();
		
		System.out.println(title);
		System.out.println(contents);
		
		String result = informationDao.InformationRegister(title, contents, user_id );
		
		if(("正常に登録できました").equals(result)) {
			
			return "menu";
		}else {
			model.addAttribute("msg",result );
			return "information";
		}
		
		// メインメニュー画面に戻るときの処理をどうやるのかを周りの人に聞く。
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
	public String adminInformation(@ModelAttribute("index") UserForm form, Model model) {


		return "adminInformation";
	}

	//対処済みにするを押すと、admin画面に遷移
	@RequestMapping(value = "/process", method = RequestMethod.GET)
	public String process(@ModelAttribute("index") UserForm form, Model model) {


		return "admin";
	}

	//一覧に戻るボタンを押すと、admin画面に遷移
	@RequestMapping(value = "/backList", method = RequestMethod.GET)
	public String backList(@ModelAttribute("index") UserForm form, Model model) {


		return "admin";
	}

	//ハンバーガーメニューからログアウトへ
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@ModelAttribute("index") UserForm form, Model model) {


		return "logout";
	}
}
