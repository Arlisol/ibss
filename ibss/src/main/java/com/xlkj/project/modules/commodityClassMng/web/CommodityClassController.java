package com.xlkj.project.modules.commodityClassMng.web;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xlkj.framework.utils.mapper.BeanMapper;
import com.xlkj.framework.web.Servlets;
import com.xlkj.framework.web.base.BaseController;
import com.xlkj.project.domain.CommodityClass;
import com.xlkj.project.domain.Result;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.commodityClassMng.service.ICommodityClassService;

/** 
 * Project:  ibss                                       
 * Comments: 商品类别管理模块控制层                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-25 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Controller
@RequestMapping(value="/commodityClass")
public class CommodityClassController extends BaseController{
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(CommodityClassController.class);
	
	@Autowired
	private ICommodityClassService commodityClassService;
	
	/**
	 *	@Description	商品类别查询页面
	 *	@param	modelMap	模型实例
	 *	@param pageable	 分页实例
	 *	@param request	 ServletRequest实例
	 *	@param result	 Result实例
	 *	@return String	页面 url
	 */
	@RequestMapping(value="/list")
	public String MenuList(Model model,Pageable pageable, ServletRequest request, Result result){
		//从请求中取得search_开头的参数及其值，并封装到map中，供后续查询使用
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//将一个 CommodityClass 对象转化为一个 JavaBean，CommodityClass.class返回一Class类型的对象，相当于实例化了一个CommodityClass对象
		CommodityClass commodityClass = BeanMapper.convertMap(CommodityClass.class, searchParams);
		//获取商品信息，分页显示
		Page<CommodityClass> commodityClassList = commodityClassService.findWithPage(pageable, commodityClass);	
		//通过model把commodityClassList传到前台页面
		model.addAttribute("commodityClassList", commodityClassList);
		//设置提示信息
		setResult(model, result.isStatus(), result.getMessage());
		//跳转到查询页面
		return "commodityClassMng/commodityClassList";
	}
	
	/**
	 *	@Description	添加页面
	 * 	@param commodityClass	CommodityClass实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}Add")
	public String Add(Model model, CommodityClass commodityClass, HttpSession session,
			@PathVariable String doWhat, RedirectAttributes redirectAttributes){
		if(doWhat.equals("into")){
			//跳转到添加页面
			return "commodityClassMng/commodityClassAdd";
		} else {
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			//执行添加操作
			boolean flag = commodityClassService.add(commodityClass, user);
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? ADD_SUCCEED : ADD_FAIL);		
			//返回列表页面
			return "redirect:/commodityClass/list";
		}	
	}
	
	/**
	 * @Description	修改页面
	 * 	@param commodityClass	CommodityClass实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param commodityClassKey		url截取的商品类别主键
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}Modify/{commodityClassKey}")
	public String Modify(Model model, CommodityClass commodityClass, HttpSession session,
			@PathVariable String doWhat, @PathVariable String commodityClassKey, RedirectAttributes redirectAttributes){
		if(doWhat.equals("into")){
			//查询商品类别详细
			CommodityClass getCommodityClass = commodityClassService.getCommodityClass(commodityClassKey);
			//通过model把getCommodityClass传到前台页面
			model.addAttribute("getCommodityClass", getCommodityClass);
			//跳转到修改页面
			return "commodityClassMng/commodityClassModify";
		} else {
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			commodityClass.setCommodityClassKey(commodityClassKey);
			//执行修改操作
			boolean flag = commodityClassService.modify(commodityClass, user);
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? MODIFY_SUCCEED : MODIFY_FAIL);		
			//返回列表页面
			return "redirect:/commodityClass/list";
		}	
	}
	
	/**
	 *  @Description	删除操作
	 * 	@param	commodityClassKey		url截取的商品类别主键
	 *	@param	session	HttpSession实例
	 *	@param	redirectAttributes	RedirectAttributes实例
	 * 	@return	String	页面 url
	 */
	@RequestMapping(value="/intoDelete/{commodityClassKey}")
	public String Delete(RedirectAttributes redirectAttributes, @PathVariable String commodityClassKey, HttpSession session){
		//获取用户信息
		UserInfo user=(UserInfo)session.getAttribute("user");
		//执行删除操作
		boolean flag = commodityClassService.delete(commodityClassKey, user);
		//设置操作结果
		setRedirectResult(redirectAttributes, flag, flag ? DELETE_SUCCEED : DELETE_FAIL);		
		//返回列表页面
		return "redirect:/commodityClass/list";
	}
	
	@RequestMapping(value="/countCommodityClass")
	@ResponseBody
	public Map<String, Object> countCommodityClass(String commodityClassKey){
		Map<String, Object> map = new HashMap<String, Object>();
		int flag= commodityClassService.countCommodityClass(commodityClassKey);
		map.put("flag", flag);
		return map;
	}
}
