package com.example.controller;

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

import com.example.dao.UserDao;
import com.example.entity.User;
import com.example.form.IndexForm;
import com.example.form.UserForm;

@Controller
public class IndexController {

	@Autowired
	MessageSource messageSource;

	@Autowired
	HttpSession session; 
    
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

		User user = userDao.findIdAndPass(form.getMail(), form.getPass());

    	if(user == null) {
    		
    		return "login";
    	}
    	else if(user.getRole() == 0) {
    		
    		session.setAttribute("user", user);
    		return "account";
    	}
    	else {
    		
    		session.setAttribute("user", user);
    		return "menu";
    	}
    }

	//ログイン画面から、新規登録画面に遷移
	@RequestMapping(value = "/result", params="register", method = RequestMethod.POST)
	public String register(@ModelAttribute("index") UserForm form, Model model) {
    	//ここはログイン時にsession保存したユーザー情報を使って、user_idを取得する。
    	//User user = session.getAttribute("user",user);
    	//int user_id = user.getUserId()
    	
		
		
//    	//ここは仮でuser_idを取得する。
//    	int user_id = 1;
//    	User user = userDao.findById(user_id);
//		model.addAttribute("user", user);
	
        return "register";
    }
	
	@RequestMapping(value = "/loginBack", method = RequestMethod.POST)
	public String loginBack(@Validated @ModelAttribute("index") UserForm form,BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "register";
		}
		
		User user = new User(form.getName(),form.getMail(), form.getPassword(),form.getSex(),form.getBirthDate(),
				form.getHeight(),form.getRankFlag(),form.getAlcoholFlag(),form.getSmokeFlag(),form.getRole());
		
		userDao.insert(user);

		return "login";
    }
    
    //ハンバーガーメニューからリスト編集へ
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("index") UserForm form, Model model) {
    	
    	//ここはログイン時にsession保存したユーザー情報を使って、user_idを取得する。
    	//User user = session.getAttribute("user",user);
    	//int user_id = user.getUserId()
    	
    	//ここは仮でuser_idを取得する。
    	//int user_id = 1;
    	
		User user = new User(form.getName(),form.getMail(), form.getPassword(),form.getSex(),form.getBirthDate(),form.getHeight(),
				form.getRankFlag(),form.getAlcoholFlag(),form.getSmokeFlag(),form.getRole());

		userDao.insert(user);

		return "login";
	}
	
	//記録＆リスト画面に遷移
	@RequestMapping(value = "/record", method = RequestMethod.POST)
	public String record(@ModelAttribute("index") UserForm form, Model model) {

		return "record";
	}

	//統計画面に遷移
	@RequestMapping(value = "/statistics", method = RequestMethod.POST)
	public String statistics(@ModelAttribute("index") UserForm form, Model model) {


		return "statistics";
	}

	//記録画面から登録ボタンでメニュー画面に遷移
    @RequestMapping(value = "/recordRegist", method = RequestMethod.POST)
    public String recordRegist(@ModelAttribute("index") UserForm form, Model model) {


			

    	//メインメニュー画面に戻るときの処理をどうやるのかを周りの人に聞く。
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
