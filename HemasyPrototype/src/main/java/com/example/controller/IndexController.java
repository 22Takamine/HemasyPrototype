package com.example.controller;

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

	// 最初にここにきて、login画面にいくよ

	@RequestMapping({ "/", "/index" })
	public String index(@ModelAttribute("index") IndexForm form, Model model) {
		return "login";
	}

	// ログイン成功時にメニュー画面に遷移
	@RequestMapping(value = "/result", params = "login", method = RequestMethod.POST)
	public String login(@Validated @ModelAttribute("index") IndexForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}

		if (form.getMail().equals("1") && form.getPass().equals("1")) {
			return "admin";
		}

		return "menu";

	}

	// ログイン画面から、新規登録画面に遷移
	@RequestMapping(value = "/result", params = "register", method = RequestMethod.POST)
	public String register(@ModelAttribute("index") UserForm form, Model model) {

		return "register";
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

	// マイリスト編集画面から登録ボタンでメニュー画面に遷移
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String registList(@ModelAttribute("index") UserForm form, Model model) {

		return "menu";
	}

	// お問い合わせ画面から登録ボタンでメニュー画面に遷移
	@RequestMapping(value = "/information", method = RequestMethod.POST)
	public String registInformation(@ModelAttribute("information") InformationForm Iform,@ModelAttribute("index") UserForm form, Model model) {
		
		// ここはログイン時にsession保存したユーザー情報を使って、user_idを取得する。
		// User user = session.getAttribute("user",user);
		// int user_id = user.getUserId()

		// ここは仮でuser_idを取得する。
		int user_id = 1;
		
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

	// 戻るボタンを押すと、メニュー画面に遷移
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back(@ModelAttribute("index") UserForm form, Model model) {

		return "menu";
	}

	// アカウント管理で登録ボタンを押すと、メニュー画面に遷移
	@RequestMapping(value = "/accountRegist", method = RequestMethod.POST)
	public String accountRegist(@Validated @ModelAttribute("index") UserForm form, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}

		return "menu";
	}

	// ハンバーガーメニューからアカウント管理へ
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(@ModelAttribute("account") UserForm form, Model model) {

		// ここはログイン時にsession保存したユーザー情報を使って、user_idを取得する。
		// User user = session.getAttribute("user",user);
		// int user_id = user.getUserId()

		// ここは仮でuser_idを取得する。
		int user_id = 1;
		User user = userDao.findById(user_id);
		model.addAttribute("user", user);

		return "account";
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

		// ここはログイン時にsession保存したユーザー情報を使って、user_idを取得する。
		// User user = session.getAttribute("user");
		// int user_id = user.getUserId()

		// ここは仮でuser_idを取得する。
		int user_id = 1;
		// user_id =1が登録した食事リストを取得する
		List<ListAndRecord> foodList = listAndRecordDao.FoodListById(user_id);
		model.addAttribute("foodList", foodList);

		// user_id =1が登録したお酒リストを取得する(今は暫定でuser_idの部分に固定で２を入れている)
		List<ListAndRecord> alcoholList = listAndRecordDao.AlcoholListById(2);
		model.addAttribute("alcoholList", alcoholList);

		return "list";
	}

	// ハンバーガーメニューからお問い合わせへ
	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information(@ModelAttribute("information") InformationForm LRform, Model model) {

		// ここはログイン時にsession保存したユーザー情報を使って、user_idを取得する。
		// User user = session.getAttribute("user");
		// int user_id = user.getUserId();

		// ここは仮でuser_idを取得する。
		int userId = 1;
		model.addAttribute("userId", userId);

		return "information";
	}

	// 管理者ページでロゴをクリックで管理者ページへ
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(@ModelAttribute("index") UserForm form, Model model) {

		return "admin";
	}

	// ハンバーガーメニューから管理者お問い合わせへ
	@RequestMapping(value = "/adminInformation", method = RequestMethod.GET)
	public String adminInformation(@ModelAttribute("index") UserForm form, Model model) {

		return "adminInformation";
	}

	// 対処済みにするを押すと、admin画面に遷移
	@RequestMapping(value = "/process", method = RequestMethod.GET)
	public String process(@ModelAttribute("index") UserForm form, Model model) {

		return "admin";
	}

	// 一覧に戻るボタンを押すと、admin画面に遷移
	@RequestMapping(value = "/backList", method = RequestMethod.GET)
	public String backList(@ModelAttribute("index") UserForm form, Model model) {

		return "admin";
	}

	// ハンバーガーメニューからログアウトへ
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@ModelAttribute("index") UserForm form, Model model) {

		return "logout";
	}

}
