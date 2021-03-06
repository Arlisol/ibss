package com.xlkj.project.modules.commodityMng.web;

import java.util.List;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xlkj.framework.utils.mapper.BeanMapper;
import com.xlkj.framework.web.Servlets;
import com.xlkj.framework.web.base.BaseController;
import com.xlkj.project.domain.Commodity;
import com.xlkj.project.domain.CommodityClass;
import com.xlkj.project.domain.Result;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.commodityMng.service.ICommodityService;

/** 
 * Project:  ibss                                       
 * Comments: 商品管理模块控制层                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-25 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Controller
@RequestMapping(value="/commodity")
public class CommodityController extends BaseController{
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(CommodityController.class);
	
	@Autowired
	private ICommodityService commodityService;
	
	/**
	 *	@Description	商品查询页面
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
		//将一个 Commodity 对象转化为一个 JavaBean，Commodity.class返回一Class类型的对象，相当于实例化了一个Commodity对象
		Commodity commodity = BeanMapper.convertMap(Commodity.class, searchParams);
		//获取商品类别列表
		List<CommodityClass> commodityClassList = commodityService.getCommodityClassList();
		//获取商品信息，分页显示
		Page<Commodity> commodityList = commodityService.findWithPage(pageable, commodity);	
		//通过model把commodityClassList传到前台页面
		model.addAttribute("commodityClassList", commodityClassList);
		//通过model把commodityList传到前台页面
		model.addAttribute("commodityList", commodityList);
		//设置提示信息
		setResult(model, result.isStatus(), result.getMessage());
		//跳转到查询页面
		return "commodityMng/commodityList";
	}
	
	/**
	 *	@Description	添加页面
	 * 	@param commodity	Commodity实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}Add")
	public String Add(Model model, Commodity commodity, HttpSession session,
			@PathVariable String doWhat, RedirectAttributes redirectAttributes){
		if(doWhat.equals("into")){
			//获取商品类别列表
			List<CommodityClass> commodityClassList = commodityService.getCommodityClassList();
			//通过model把commodityClassList传到前台页面
			model.addAttribute("commodityClassList", commodityClassList);
			//跳转到添加页面
			return "commodityMng/commodityAdd";
		} else {
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			//执行添加操作
			boolean flag = commodityService.add(commodity, user);
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? ADD_SUCCEED : ADD_FAIL);		
			//返回列表页面
			return "redirect:/commodity/list";
		}	
	}
	
	/**
	 * @Description	修改页面
	 * 	@param commodity	Commodity实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param commodityKey		url截取的商品主键
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}Modify/{commodityKey}")
	public String Modify(Model model, Commodity commodity, HttpSession session,
			@PathVariable String doWhat, @PathVariable String commodityKey, RedirectAttributes redirectAttributes){
		if(doWhat.equals("into")){
			//获取商品类别列表
			List<CommodityClass> commodityClassList = commodityService.getCommodityClassList();
			//通过model把commodityClassList传到前台页面
			model.addAttribute("commodityClassList", commodityClassList);
			//查询商品详细
			Commodity getCommodity = commodityService.getCommodity(commodityKey);
			//通过model把getCommodity传到前台页面
			model.addAttribute("getCommodity", getCommodity);
			//跳转到修改页面
			return "commodityMng/commodityModify";
		} else {
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			commodity.setCommodityKey(commodityKey);
			//执行修改操作
			boolean flag = commodityService.modify(commodity, user);
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? MODIFY_SUCCEED : MODIFY_FAIL);		
			//返回列表页面
			return "redirect:/commodity/list";
		}	
	}
	
	/**
	 *  @Description	详细页面
	 * 	@param commodityKey		url截取的商品主键
	 *	@param model	Model实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/detail/{commodityKey}")
	public String Detail(Model model, @PathVariable String commodityKey){
		//查询详细
		Commodity commodity = commodityService.detail(commodityKey);	
		//通过model把commodity传到前台页面
		model.addAttribute("commodity", commodity);
		//跳转到详细页面
		return "commodityMng/commodityDetail";
	}
	
	/**
	 *  @Description	删除操作
	 * 	@param	commodityKey		url截取的商品主键
	 *	@param	session	HttpSession实例
	 *	@param	redirectAttributes	RedirectAttributes实例
	 * 	@return	String	页面 url
	 */
	@RequestMapping(value="/intoDelete/{commodityKey}")
	public String Delete(RedirectAttributes redirectAttributes, @PathVariable String commodityKey, HttpSession session){
		//获取用户信息
		UserInfo user=(UserInfo)session.getAttribute("user");
		//执行删除操作
		boolean flag = commodityService.delete(commodityKey, user);
		//设置操作结果
		setRedirectResult(redirectAttributes, flag, flag ? DELETE_SUCCEED : DELETE_FAIL);		
		//返回列表页面
		return "redirect:/commodity/list";
	}
}
