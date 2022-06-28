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

import com.example.dao.ListAndRecordDao;
import com.example.dao.UserDao;
import com.example.entity.ListAndRecord;
import com.example.entity.User;
import com.example.form.IndexForm;
import com.example.form.ListAndRecordForm;
import com.example.form.UserForm;



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
	UserDao userDao;


	//最初にここにきて、login画面にいくよ
	@RequestMapping({ "/", "/index"})
	public String index(@ModelAttribute("index") IndexForm form, Model model) {

		
		return "login";
	}
	//ログイン成功時にメニュー画面に遷移
	@RequestMapping(value = "/result", params="login", method = RequestMethod.POST)
	public String login(@Validated @ModelAttribute("index") IndexForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {	
			return "login";
		}
		
		//仮のデータ送信
		User user = new User(1, 1, "一派ユーザ", "1", "1", 1, Date.valueOf("2022-06-20"), 1.0, Date.valueOf("2022-06-20"), 1, 1, 1, 1, 1, null, null, null, null);
		session.setAttribute("user", user);

		//管理者ページへ
		if(form.getMail().equals("1") && form.getPass().equals("1")) {
			
			List<User> userList = userDao.getAllUser();
			
			List<ListAndRecord> foodList = listAndRecordDao.FoodListById(user.getUserId());
			
			List<ListAndRecord> alcoholList = listAndRecordDao.AlcoholListById(user.getUserId());
			
			List<ListAndRecord> sportList = listAndRecordDao.SportListById(user.getUserId());
			
			System.out.println(sportList.size());
			
			
			session.setAttribute("userList", userList);
			session.setAttribute("foodList", foodList);
			session.setAttribute("alcoholList", alcoholList);
			session.setAttribute("sportList", sportList);
			
			return "admin";
		}

		return "menu";

	}

	//ログイン画面から、新規登録画面に遷移
	@RequestMapping(value = "/result", params="register", method = RequestMethod.POST)
	public String register(@ModelAttribute("index") UserForm form, Model model) {

		return "register";
	}
	
	//メニューから記録＆リスト画面に遷移(当日のデータ)
		@RequestMapping(value = "/record", method = RequestMethod.GET)
		public String record(@ModelAttribute("record") ListAndRecordForm form, Model model) {
			
			User user = (User) session.getAttribute("user");
			
			Date recordDate = Date.valueOf(request.getParameter("recordDate"));
			
			System.out.println(recordDate);

			List<ListAndRecord> breakfastRecordList = listAndRecordDao.getFoodRecords(user.getUserId(), 1, 1, recordDate);

			List<ListAndRecord> lunchRecordList = listAndRecordDao.getFoodRecords(user.getUserId(), 1, 2, recordDate);

			List<ListAndRecord> dinnerRecordList = listAndRecordDao.getFoodRecords(user.getUserId(), 1, 3, recordDate);

			List<ListAndRecord> snackRecordList = listAndRecordDao.getFoodRecords(user.getUserId(), 1, 4, recordDate);

			List<ListAndRecord> sportRecordList = listAndRecordDao.getSportRecords(user.getUserId(), recordDate);

			List<ListAndRecord> alcoholRecordList = listAndRecordDao.getAlcoholRecords(user.getUserId(), recordDate);

			ListAndRecord smokeRecord = listAndRecordDao.getSmokeRecord(user.getUserId(), recordDate);

			ListAndRecord weightRecord = listAndRecordDao.getLatestWeightRecord(user.getUserId(), recordDate);

			System.out.println("えええ" + sportRecordList.size());

			System.out.println(smokeRecord.getValue3());

			System.out.println(dinnerRecordList.size());

			System.out.println(snackRecordList.size());
			
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

	@RequestMapping(value = "/recordCommit", method = RequestMethod.POST)
	public String recordCommit(@ModelAttribute("index") UserForm form, Model model) {

		List<ListAndRecord> recordsList = new ArrayList<ListAndRecord>();
		
		List<ListAndRecord> listsList = new ArrayList<ListAndRecord>();

		//朝食記録追加
		for (int i = 0; request.getParameter("value1Bre" + i) != null; i++) {
			System.out.println(request.getParameter("delBre" + i) + i + "朝食かくにん");
			if (request.getParameter("delBre" + i) != null) {
				System.out.println("けすぞ");
				continue;
			}
			if (request.getParameter("addMyListBre" + i) != null) {
				ListAndRecord foodBreList = new ListAndRecord(0, 1, 1, request.getParameter("value1Bre" + i), Double.parseDouble(request.getParameter("value2Bre" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(foodBreList);
			}
			ListAndRecord breakfastRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Bre" + i), Double.parseDouble(request.getParameter("value2Bre" + i)), Double.parseDouble(request.getParameter("value3Bre" + i)), 1.0, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(breakfastRecord);
			System.out.println(breakfastRecord.getListsAndRecordsId());
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
			System.out.println(request.getParameter("delLun" + i) + i + "昼食かくにん");
			if (request.getParameter("delLun" + i) != null) {
				System.out.println("けすぞ");
				continue;
			}
			if (request.getParameter("addMyListLun" + i) != null) {
				ListAndRecord foodLunList = new ListAndRecord(0, 1, 1, request.getParameter("value1Lun" + i), Double.parseDouble(request.getParameter("value2Lun" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(foodLunList);
			}
			ListAndRecord lunchRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Lun" + i), Double.parseDouble(request.getParameter("value2Lun" + i)), Double.parseDouble(request.getParameter("value3Lun" + i)), 2.0, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(lunchRecord);
			System.out.println(lunchRecord.getListsAndRecordsId());
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
			System.out.println(request.getParameter("delDin" + i) + i + "夕食かくにん");
			if (request.getParameter("delDin" + i) != null) {
				System.out.println("けすぞ");
				continue;
			}
			if (request.getParameter("addMyListDin" + i) != null) {
				ListAndRecord foodDinList = new ListAndRecord(0, 1, 1, request.getParameter("value1Din" + i), Double.parseDouble(request.getParameter("value2Din" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(foodDinList);
			}
			ListAndRecord dinnerRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Din" + i), Double.parseDouble(request.getParameter("value2Din" + i)), Double.parseDouble(request.getParameter("value3Din" + i)), 3.0, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(dinnerRecord);
			System.out.println(dinnerRecord.getListsAndRecordsId());
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
			System.out.println(request.getParameter("delSna" + i) + i + "間食かくにん");
			if (request.getParameter("delSna" + i) != null) {
				System.out.println("けすぞ");
				continue;
			}
			if (request.getParameter("addMyListSna" + i) != null) {
				ListAndRecord foodSnaList = new ListAndRecord(0, 1, 1, request.getParameter("value1Sna" + i), Double.parseDouble(request.getParameter("value2Sna" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(foodSnaList);
			}
			ListAndRecord snackRecord = new ListAndRecord(0, 2, 1, request.getParameter("value1Sna" + i), Double.parseDouble(request.getParameter("value2Sna" + i)), Double.parseDouble(request.getParameter("value3Sna" + i)), 4.0, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(snackRecord);
			System.out.println(snackRecord.getListsAndRecordsId());
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
			ListAndRecord sportRecord = new ListAndRecord(0, 2, 2, request.getParameter("value1Spo" + i), Double.parseDouble(request.getParameter("value2Spo" + i)), Double.parseDouble(request.getParameter("value3Spo" + i)), null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(sportRecord);
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
		ListAndRecord smokeRecord = new ListAndRecord(0, 2, 3, null, null, Double.parseDouble(request.getParameter("value3Smo")), null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
		recordsList.add(smokeRecord);

		//アルコール記録挿入
		for (int i = 0; request.getParameter("value1Alc" + i) != null; i++) {
			System.out.println(request.getParameter("delAlc" + i) + "かくにん");
			if (request.getParameter("delAlc" + i) != null) {
				System.out.println("けすぞ");
				continue;
			}
			if (request.getParameter("addMyListAlc" + i) != null) {
				ListAndRecord alcList = new ListAndRecord(0, 1, 4, request.getParameter("value1Alc" + i), Double.parseDouble(request.getParameter("value2Alc" + i)), null, Double.parseDouble(request.getParameter("value4Alc" + i)), Double.parseDouble(request.getParameter("value5Alc" + i)), null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
				listsList.add(alcList);
			}
			ListAndRecord alcoholRecord = new ListAndRecord(0, 2, 4, request.getParameter("value1Alc" + i), Double.parseDouble(request.getParameter("value2Alc" + i)), Double.parseDouble(request.getParameter("value3Alc" + i)), Double.parseDouble(request.getParameter("value4Alc" + i)), Double.parseDouble(request.getParameter("value5Alc" + i)), null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			recordsList.add(alcoholRecord);
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
		ListAndRecord weightRecord = new ListAndRecord(0, 2, 5, null, Double.parseDouble(request.getParameter("value2Wei")), Double.parseDouble(request.getParameter("value3Wei")), null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
		recordsList.add(weightRecord);

		System.out.println(recordsList.size());
		
		System.out.println(listsList.size());

		listAndRecordDao.insertRecord(((User) session.getAttribute("user")).getUserId(), recordsList, Date.valueOf(request.getParameter("createDate")));
		
		listAndRecordDao.insertMyList(((User) session.getAttribute("user")).getUserId(), listsList);

		return "menu";

	}

	//統計画面に遷移
	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	public String statistics(@ModelAttribute("index") UserForm form, Model model) {
		
		model.addAttribute("statisticsDate", session.getAttribute("statisticsDate"));


		return "statistics";
	}

	//記録画面から登録ボタンでメニュー画面に遷移
	@RequestMapping(value = "/recordRegist", method = RequestMethod.POST)
	public String recordRegist(@ModelAttribute("index") UserForm form, Model model) {


		return "menu";
	}

	//マイリスト編集画面から登録ボタンでメニュー画面に遷移
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String registList(@ModelAttribute("index") UserForm form, Model model) {


		return "menu";
	}

	//お問い合わせ画面から登録ボタンでメニュー画面に遷移
	@RequestMapping(value = "/information", method = RequestMethod.POST)
	public String registInformation(@ModelAttribute("index") UserForm form, Model model) {

		return "menu";
	}

	//戻るボタンを押すと、メニュー画面に遷移
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back(@ModelAttribute("index") UserForm form, Model model) {


		return "menu";
	}

	//アカウント管理で登録ボタンを押すと、メニュー画面に遷移
	@RequestMapping(value = "/accountRegist", method = RequestMethod.POST)
	public String accountRegist(@Validated  @ModelAttribute("index") UserForm form, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "login";
		}

		return "menu";
	}

	//ハンバーガーメニューからアカウント管理へ
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(@ModelAttribute("index") UserForm form, Model model) {


		return "account";
	}

	//ハンバーガーメニューからランキングへ
	@RequestMapping(value = "/rank", method = RequestMethod.GET)
	public String rank(@ModelAttribute("index") UserForm form, Model model) {


		return "rank";
	}

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
	
	
	//マイリスト確定時
	@RequestMapping(value = "/listCommit", method = RequestMethod.POST)
	public String listCommit(@ModelAttribute("index") UserForm form, Model model) {

		List<ListAndRecord> listsList = new ArrayList<ListAndRecord>();

		//食事リスト
		for (int i = 0; request.getParameter("value1Food" + i) != null; i++) {
			System.out.println(request.getParameter("delFood" + i) + i + "食べ物かくにん");
			if (request.getParameter("delFood" + i) != null) {
				System.out.println("けすぞ");
				continue;
			}
			ListAndRecord foodData = new ListAndRecord(0, 1, 1, request.getParameter("value1Food" + i), Double.parseDouble(request.getParameter("value2Food" + i)), null, 1.0, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			listsList.add(foodData);
			System.out.println(foodData.getListsAndRecordsId());
			System.out.println(foodData.getCategory());
			System.out.println(foodData.getType());
			System.out.println(foodData.getValue1());
			System.out.println(foodData.getValue2());
			System.out.println(foodData.getValue3());
			System.out.println(foodData.getValue4());
			System.out.println(foodData.getValue5());
			System.out.println(foodData.getCreateDate());
			System.out.println(foodData.getUserId());
		}

		//アルコールリスト
		for (int i = 0; request.getParameter("value1Alc" + i) != null; i++) {
			System.out.println(request.getParameter("delAlc" + i) + "おさけかくにん");
			if (request.getParameter("delAlc" + i) != null) {
				System.out.println("けすぞ");
				continue;
			}
			ListAndRecord alcoholData = new ListAndRecord(0, 1, 4, request.getParameter("value1Alc" + i), Double.parseDouble(request.getParameter("value2Alc" + i)), null, Double.parseDouble(request.getParameter("value4Alc" + i)), Double.parseDouble(request.getParameter("value5Alc" + i)), null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			listsList.add(alcoholData);
			System.out.println("はじまり" + alcoholData.getListsAndRecordsId());
			System.out.println(alcoholData.getCategory());
			System.out.println(alcoholData.getType());
			System.out.println(alcoholData.getValue1());
			System.out.println(alcoholData.getValue2());
			System.out.println(alcoholData.getValue3());
			System.out.println(alcoholData.getValue4());
			System.out.println(alcoholData.getValue5());
			System.out.println(alcoholData.getCreateDate());
			System.out.println(alcoholData.getUserId());
		}
		
		listAndRecordDao.ediMyList(((User) session.getAttribute("user")).getUserId(), listsList);

		return "menu";

	}
	
	//管理者のリスト管理確定時
	@RequestMapping(value = "/adminListCommit", method = RequestMethod.POST)
	public String adminListCommit(@ModelAttribute("index") UserForm form, Model model) {

		List<ListAndRecord> listsList = new ArrayList<ListAndRecord>();

		//食事リスト
		for (int i = 0; request.getParameter("value1Food" + i) != null; i++) {
			System.out.println(request.getParameter("delFood" + i) + i + "食べ物かくにん");
			if (request.getParameter("delFood" + i) != null) {
				System.out.println("けすぞ");
				continue;
			}
			ListAndRecord foodData = new ListAndRecord(0, 1, 1, request.getParameter("value1Food" + i), Double.parseDouble(request.getParameter("value2Food" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			listsList.add(foodData);
			System.out.println(foodData.getListsAndRecordsId());
			System.out.println(foodData.getCategory());
			System.out.println(foodData.getType());
			System.out.println(foodData.getValue1());
			System.out.println(foodData.getValue2());
			System.out.println(foodData.getValue3());
			System.out.println(foodData.getValue4());
			System.out.println(foodData.getValue5());
			System.out.println(foodData.getCreateDate());
			System.out.println(foodData.getUserId());
		}
		
		//運動リスト
		for (int i = 0; request.getParameter("value1Spo" + i) != null; i++) {
			System.out.println(request.getParameter("delSpo" + i) + i + "運動かくにん");
			if (request.getParameter("delSpo" + i) != null) {
				System.out.println("けすぞ");
				continue;
			}
			ListAndRecord sportData = new ListAndRecord(0, 1, 2, request.getParameter("value1Spo" + i), Double.parseDouble(request.getParameter("value2Spo" + i)), null, null, null, null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			listsList.add(sportData);
			System.out.println(sportData.getListsAndRecordsId());
			System.out.println(sportData.getCategory());
			System.out.println(sportData.getType());
			System.out.println(sportData.getValue1());
			System.out.println(sportData.getValue2());
			System.out.println(sportData.getValue3());
			System.out.println(sportData.getValue4());
			System.out.println(sportData.getValue5());
			System.out.println(sportData.getCreateDate());
			System.out.println(sportData.getUserId());
		}

		//アルコールリスト
		for (int i = 0; request.getParameter("value1Alc" + i) != null; i++) {
			System.out.println(request.getParameter("delAlc" + i) + "おさけかくにん");
			if (request.getParameter("delAlc" + i) != null) {
				System.out.println("けすぞ");
				continue;
			}
			ListAndRecord alcoholData = new ListAndRecord(0, 1, 4, request.getParameter("value1Alc" + i), Double.parseDouble(request.getParameter("value2Alc" + i)), null, Double.parseDouble(request.getParameter("value4Alc" + i)), Double.parseDouble(request.getParameter("value5Alc" + i)), null, null, null, Date.valueOf(request.getParameter("createDate")), ((User) session.getAttribute("user")).getUserId());
			listsList.add(alcoholData);
			System.out.println("はじまり" + alcoholData.getListsAndRecordsId());
			System.out.println(alcoholData.getCategory());
			System.out.println(alcoholData.getType());
			System.out.println(alcoholData.getValue1());
			System.out.println(alcoholData.getValue2());
			System.out.println(alcoholData.getValue3());
			System.out.println(alcoholData.getValue4());
			System.out.println(alcoholData.getValue5());
			System.out.println(alcoholData.getCreateDate());
			System.out.println(alcoholData.getUserId());
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

	//ハンバーガーメニューからお問い合わせへ
	@RequestMapping(value = "/information", method = RequestMethod.GET)
	public String information(@ModelAttribute("index") UserForm form, Model model) {


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
