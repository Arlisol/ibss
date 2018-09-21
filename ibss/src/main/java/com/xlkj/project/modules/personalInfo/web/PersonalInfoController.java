package com.xlkj.project.modules.personalInfo.web;

/** 
 * Project:  ibss                                      
 * Module ID: personalInfo
 * Comments:   个人资料模块控制层                                         
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅           
 * Create Date：  2018-6-23 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xlkj.framework.web.base.BaseController;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.personalInfo.service.IPersonalInfoService;


@Controller
@RequestMapping("personalInfo")
public class PersonalInfoController extends BaseController{
	
	@Autowired
	private IPersonalInfoService personalInfoService;
	
	@RequestMapping(value="/getUserInfo/{userKey}")
	public String detail(Model model,@PathVariable String userKey){
		UserInfo userInfo = personalInfoService.getUserInfo(userKey);
		String newUserID = String.format("%04d", userInfo.getUserID());
		userInfo.setNewUserID(newUserID);
    	//通过model把userInfo传到前台页面
		model.addAttribute("userInfo", userInfo);
		return "personalInfo/userInfo";
	}
	
	@RequestMapping(value="/{doWhat}Modify/{userKey}")
	public String modify(Model model,@PathVariable String doWhat,@PathVariable String userKey,UserInfo userInfo,RedirectAttributes redirectAttributes,HttpSession session){
		if(doWhat.equals("into")){
			UserInfo user = personalInfoService.getUserInfo(userKey);
	    	//通过model把userInfo传到前台页面
			model.addAttribute("user", user);
			model.addAttribute("userKey", userKey);
			return "personalInfo/userInfoModify";
		}else{
			userInfo.setUserKey(userKey);
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			boolean flag =personalInfoService.userInfoModify(userInfo,user);
			setRedirectResult(redirectAttributes, flag, flag ? MODIFY_SUCCEED : MODIFY_FAIL);
			return "redirect:/dictionary";
		}
	}
}
