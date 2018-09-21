package com.xlkj.project.modules.userMng.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xlkj.framework.utils.mapper.BeanMapper;
import com.xlkj.framework.web.Servlets;
import com.xlkj.framework.web.base.BaseController;
import com.xlkj.project.domain.Result;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.userMng.service.IUserService;

/** 
 * Project:  ibss                                      
 * Module ID: userMng
 * Comments:   用户管理模块控制层                                         
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅           
 * Create Date：  2018-6-24 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Controller
@RequestMapping(value="/userInfo")
public class UserController extends BaseController{
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private IUserService userService;
	
	/**
	 *	@Description	用户查询页面
	 *	@param	modelMap	模型实例
	 *	@param pageable	 分页实例
	 *	@param request	 ServletRequest实例
	 *	@param result	 Result实例
	 *	@return String	页面 url
	 */
	@RequestMapping(value="/list")
	public String UserInfoList(Model model,Pageable pageable, ServletRequest request, Result result){
		//从请求中取得search_开头的参数及其值，并封装到map中，供后续查询使用
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//将一个 Map 对象转化为一个 JavaBean，UserInfo.class返回一Class类型的对象，相当于实例化了一个UserInfo对象
		UserInfo userInfo = BeanMapper.convertMap(UserInfo.class, searchParams);
		//获取用户信息，分页显示
		Page<UserInfo> userInfoList = userService.findWithPage(pageable, userInfo);
		//通过model把userInfoList传到前台页面
		model.addAttribute("userInfoList", userInfoList);
		//设置提示信息
		setResult(model, result.isStatus(), result.getMessage());
		//跳转到查询页面
		return "userMng/userInfoList";
	}
	
	/**
	 *	@Description	添加页面
	 * 	@param userInfo		UserInfo实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}Add")
	public String Add(Model model, UserInfo userInfo, HttpSession session,
			@PathVariable String doWhat, RedirectAttributes redirectAttributes){
		if(doWhat.equals("into")){
			//跳转到添加页面
			return "userMng/userInfoAdd";
		} else {
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			//执行添加操作
			boolean flag = userService.add(userInfo, user);
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? ADD_SUCCEED : ADD_FAIL);		
			//返回列表页面
			return "redirect:/userInfo/list";
		}	
	}
	
	/**
	 * @Description	修改页面
	 * 	@param userInfo		UserInfo实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param userKey		url截取的用户主键
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}Modify/{userKey}")
	public String Modify(Model model, UserInfo userInfo, HttpSession session,
			@PathVariable String doWhat, @PathVariable String userKey, RedirectAttributes redirectAttributes){
		if(doWhat.equals("into")){
			//查询用户详细
			UserInfo getUserInfo = userService.getUserInfo(userKey);
			//通过model把getUserInfo传到前台页面
			model.addAttribute("getUserInfo", getUserInfo);
			//跳转到修改页面
			return "userMng/userInfoModify";
		} else {
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			userInfo.setUserKey(userKey);
			//执行修改操作
			boolean flag = userService.modify(userInfo, user);
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? MODIFY_SUCCEED : MODIFY_FAIL);		
			//返回列表页面
			return "redirect:/userInfo/list";
		}	
	}
	
	/**
	 *  @Description	详细页面
	 * 	@param userKey	url截取的用户主键
	 *	@param model	Model实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/detail/{userKey}")
	public String Detail(Model model, @PathVariable String userKey){
		//查询详细
		UserInfo userInfo = userService.detail(userKey);	
		//通过model把userInfo传到前台页面
		model.addAttribute("userInfo", userInfo);
		//跳转到详细页面
		return "userMng/userInfoDetail";
	}
	
	/**
	 *  @Description	删除操作
	 * 	@param	userKey		url截取的用户主键
	 *	@param	session	HttpSession实例
	 *	@param	redirectAttributes	RedirectAttributes实例
	 * 	@return	String	页面 url
	 */
	@RequestMapping(value="/intoDelete/{userKey}")
	public String Delete(RedirectAttributes redirectAttributes, @PathVariable String userKey, HttpSession session){
		//获取用户信息
		UserInfo user=(UserInfo)session.getAttribute("user");
		//执行删除操作
		boolean flag = userService.delete(userKey, user);
		//设置操作结果
		setRedirectResult(redirectAttributes, flag, flag ? DELETE_SUCCEED : DELETE_FAIL);		
		//返回列表页面
		return "redirect:/userInfo/list";
	}
	
	/**
	 *  @Description	进入充值页面
	 * 	@param	userKey		url截取的用户主键
	 * 	@return	String	页面 url
	 */
	@RequestMapping(value="/intoRecharge/{userKey}/{userType}")
	public String intoRecharge(Model model,@PathVariable String userKey,@PathVariable String userType){	
		//通过model把userKey传到前台页面
		model.addAttribute("userKey", userKey);
		//通过model把userKey传到前台页面
		model.addAttribute("userType", userType);
		//进入充值页面
		return "userMng/rechargeList";
	}
	
	/**
	 *  @Description	充值操作
	 * 	@param	userInfo		UserInfo实例
	 *	@param	session	HttpSession实例
	 * 	@return	map
	 */
	@RequestMapping(value="/submitRecharge")
	public String Recharge(RedirectAttributes redirectAttributes,UserInfo userInfo, HttpSession session){
		//获取用户信息
		UserInfo user=(UserInfo)session.getAttribute("user");
		//执行充值操作
		boolean flag = userService.recharge(userInfo, user);
		//设置操作结果
		setRedirectResult(redirectAttributes, flag, flag ? RECHARGE_SUCCEED : RECHARGE_FAIL);
		//返回列表页面
		return "redirect:/userInfo/list";
	}
	
	/**
	 *  @Description	用户名唯一性校验
	 *	@param	userInfo		UserInfo实例
	 * 	@return	boolean	
	 */
	@RequestMapping(value="userNameUnique",method=RequestMethod.POST)
	@ResponseBody
	public boolean userNameUnique(UserInfo userInfo) throws IOException {
		//判断用户名是否和原内容相同
		boolean same = userInfo.getUserName().trim().equals(userInfo.getHidUserName());
		//用户名唯一性校验
		return same ? true : userService.userNameUnique(userInfo);
	}
}
