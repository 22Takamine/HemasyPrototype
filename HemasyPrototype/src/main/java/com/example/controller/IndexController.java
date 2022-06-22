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

import com.example.form.IndexForm;
import com.example.form.UserForm;



@Controller
public class IndexController {

    @Autowired
    MessageSource messageSource;
    
    @Autowired
	HttpSession session; 
    
    //最初にここにきて、login画面にいく

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
    	
    	if(form.getMail().equals("1") && form.getPass().equals("1")) {
    		return "admin";
    	}
    	
    	return "menu";
    	
    }
    
    //ログイン画面から、新規登録画面に遷移
    @RequestMapping(value = "/result", params="register", method = RequestMethod.POST)
    public String register(@ModelAttribute("index") UserForm form, Model model) {
    	
        return "register";
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
    
    //ハンバーガーメニューからリスト編集へ
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(@ModelAttribute("index") UserForm form, Model model) {

    	
        return "list";
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
