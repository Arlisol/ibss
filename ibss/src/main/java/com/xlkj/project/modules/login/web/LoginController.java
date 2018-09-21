package com.xlkj.project.modules.login.web;

/** 
 * Project:  ibss                                      
 * Module ID: login
 * Comments:   登录模块控制层                                         
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅           
 * Create Date：  2018-6-23 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Maps;
import com.xlkj.framework.serivce.BusinessLogger;
import com.xlkj.framework.utils.DbUtils;
import com.xlkj.framework.utils.IPAndMACUtils;
import com.xlkj.framework.web.base.BaseController;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.domain.LoginRecord;
import com.xlkj.project.domain.Menu;
import com.xlkj.project.modules.login.service.ILoginService;


@Controller
@RequestMapping(value = "login")
public class LoginController extends BaseController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	BusinessLogger businessLogger = new BusinessLogger();
	
	@Autowired
	private ILoginService loginService;
	/**
	 * @Description: 登录相关校验
	 * @param model
	 * @param user
	 * @return String
	 */
	@RequestMapping("loginCheck")
	public String loginCheck(Model model, UserInfo userInfo, HttpSession session, HttpServletRequest request) {
		
		//获取用户信息
		UserInfo currentUser = loginService.findUserInfo(userInfo);
		
		//判断用户是否存在
		if (currentUser == null) {
			//提示消息
			model.addAttribute("message", "用户名或密码错误");
			return "login";
		} else {			
			// 判断是否已经登陆,如果登陆是否ip相同,通过userKey判断
			String seessionId = session.getId();
			String ipAddr = IPAndMACUtils.getIpAddr(request);
			String userKey = currentUser.getUserKey();
			String userType = currentUser.getUserType();
			LoginRecord loginRecord = new LoginRecord();
			loginRecord.setUserKey(userKey);
			loginRecord.setIpAddr(ipAddr);
			loginRecord.setSeesionId(seessionId);
			//存储登陆状态
			if(!loginService.setSession(loginRecord)){
				//提示消息
				model.addAttribute("message", "登录记录存入失败");
				return "login";		
			}
			session.setAttribute("user", currentUser);
			if(!userType.equals("1") && currentUser.getVersion()==0){
				//提示消息
				model.addAttribute("message", "初次登陆必须修改密码");
				model.addAttribute("passwd",DbUtils.ibssDecrypt(currentUser.getPassword()));
				return "passwdMana/passwdMana";
			}else{			
				//获取一级菜单列表
				List<Menu> menuList = loginService.getMenuList(userType);
				String menuKeys=null;
				if(menuList!=null&&menuList.size()!=0){
					for (Menu menuKeyList : menuList) {
						menuKeys=menuKeys+",'"+menuKeyList.getMenuKey()+"'";
					}
					menuKeys=menuKeys.replace("null,", "");
				}
				//获取二级菜单列表
				if(menuList!=null&&menuList.size()!=0){
					for (Menu subMenuList : menuList) {
						subMenuList.setSubMenuList(loginService.getSubMenuList(userType,subMenuList.getMenuKey()));
					}
					session.setAttribute("menuList", menuList);
				}
				//获取错误消息数
				int errorCount = loginService.errorCount(currentUser.getUserKey());
				session.setAttribute("errorCount", errorCount);
				//登陆时间注入				
				loginService.lastLoginTimeUpdate(currentUser.getUserKey());				
				return "weclome";
			} 			
		}
	}
	/**
	 * @Description: 用户登出
	 * @param session
	 * @return String
	 */
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		logger.info("controller: the method logout invoked");

		UserInfo currentUser=(UserInfo)session.getAttribute("user");
		if (currentUser != null) {
			// 业务日志
			if (businessLogger != null) {
				Map<String, String> map = Maps.newHashMap();
				map.put("userKey", currentUser.getUserKey());
				map.put("登录名", currentUser.getUserName());
				map.put("登出时间", DbUtils.getTime());
				businessLogger.log("logout: 用户登出",
						"operator: " + currentUser.getUserKey(), map);
			}
		}
		//清空登录记录
		loginService.cleanSession(session.getId(),currentUser.getUserKey());
		session.invalidate();
		return "redirect:/";
	}
}
