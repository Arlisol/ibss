package com.xlkj.project.modules.passwdMana.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xlkj.framework.utils.DbUtils;
import com.xlkj.framework.web.base.BaseController;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.passwdMana.service.IPasswdManaService;
@Controller
@RequestMapping("passwdMana")
public class PasswdManaController  extends BaseController{
	@Autowired
	private  IPasswdManaService passwdManaService;
	@RequestMapping("")
	public String toPasswdAlter(Model model,HttpSession session){
		UserInfo user=(UserInfo)session.getAttribute("user");
		model.addAttribute("passwd",DbUtils.ibssDecrypt(user.getPassword()));
		return "passwdMana/passwdMana";
	} 
	
	@RequestMapping("alterPasswd")
	public String alterPasswd(Model model,HttpSession session,String password){
		UserInfo user=(UserInfo)session.getAttribute("user");
		passwdManaService.alterPasswd(user.getUserKey(),DbUtils.ibssEncrypt(password),user.getVersion());
		//清空登录记录
		passwdManaService.cleanSession(session.getId(),user.getUserKey());
		session.invalidate();
		return "login";
	}

}
