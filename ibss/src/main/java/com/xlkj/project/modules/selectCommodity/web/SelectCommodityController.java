package com.xlkj.project.modules.selectCommodity.web;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlkj.framework.utils.mapper.BeanMapper;
import com.xlkj.framework.web.Servlets;
import com.xlkj.framework.web.base.BaseController;
import com.xlkj.project.domain.BuyRecord;
import com.xlkj.project.domain.Commodity;
import com.xlkj.project.domain.CommodityClass;
import com.xlkj.project.domain.Result;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.commodityMng.service.ICommodityService;
import com.xlkj.project.modules.selectCommodity.service.ISelectCommodityService;

/** 
 * Project:  ibss                                       
 * Comments: 购物模块控制层                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-27 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Controller
@RequestMapping(value="/selectCommodity")
public class SelectCommodityController extends BaseController{
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(SelectCommodityController.class);
	
	@Autowired
	private ISelectCommodityService selectCommodityService;
	
	@Autowired
	private ICommodityService commodityService;
	
	/**
	 *	@Description	购物查询页面
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
		//将一个 Map 对象转化为一个 JavaBean，Commodity.class返回一Commodity类型的对象，相当于实例化了一个Commodity对象
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
		return "selectCommodity/selectCommodityList";
	}
	
	/**
	 *  @Description	购物操作
	 * 	@param	buyRecords	信息拼接字符串
	 *	@param	session	HttpSession实例
	 * 	@return	modelMap	
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(String buyRecords,BuyRecord buyRecord,HttpSession session){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		//获取用户信息
		UserInfo user=(UserInfo)session.getAttribute("user");
		boolean flag = selectCommodityService.add(buyRecords,buyRecord,user);
		modelMap.put("flag", flag);		
		return modelMap;
	}
}
