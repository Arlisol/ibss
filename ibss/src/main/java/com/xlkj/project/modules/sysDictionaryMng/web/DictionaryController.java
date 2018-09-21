package com.xlkj.project.modules.sysDictionaryMng.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
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
import com.xlkj.project.domain.Dictionary;
import com.xlkj.project.domain.Result;
import com.xlkj.project.modules.sysDictionaryMng.service.IDictionaryService;
/** 
 * CopyRright (c)2015-2016:   大连校联科技有限公司 
 * Project:  xiaolian                                       
 * Comments: 数据字典管理模块                            
 * JDK version used:      JDK1.7                              
 * Author：曹尧   
 * Create Date：  2015年5月20日
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Controller
@RequestMapping(value="/dictionary")
public class DictionaryController extends BaseController{
	Logger logger = LoggerFactory.getLogger(DictionaryController.class);//日志记录
	@Autowired
	private IDictionaryService dictService;
	/**
	 *  @Description: 默认显示列表界面
	 * 	@param modelMap	模型实例
	 * 	@param pageable	分页实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="")
	public String dictList(Model model,Pageable pageable,ServletRequest request,Result result){
		
		
		
		//从请求中取得search_开头的参数及其值，并封装到map中，供后续查询使用
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		//将一个 Map 对象转化为一个 JavaBean，User.class返回一Class类型的对象，相当于实例化了一个User对象
		Dictionary dict = BeanMapper.convertMap(Dictionary.class, searchParams);
		
		//获取用户的所有信息，分页显示
		Page<Dictionary> dictList = dictService.findWithPage(pageable, dict);
		//通过model把userList传到前台页面
		model.addAttribute("dictList", dictList);
		
		//设置提示信息
		setResult(model, result.isStatus(), result.getMessage());
		return "sysDictionaryMng/dicitonaryList";
	}
	
	/**
	 *  @Description: 添加页面
	 * 	@param dict    
	 * 	@param doWhat	匹配操作
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}add")
	public String add(Model model,Dictionary dict,@PathVariable String doWhat,RedirectAttributes redirectAttributes){
		
		if(doWhat.equals("into")){
			return "sysDictionaryMng/dictionaryAdd";
		} else {
			
			//执行添加操作
			dictService.add(dict);
			
			//设置操作结果
			setRedirectResult(redirectAttributes, true, ADD_SUCCEED);
			
			//返回列表页面
			return "redirect:/dictionary";
		}
		
	}
	/**
	 *  @Description: 修改页面
	 * 	@param key   修改记录标识
	 * 	@param doWhat	匹配操作
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/intoModify")
	public String modify(){
				
			return "sysDictionaryMng/dictionaryDetail";
			
	}
	
	/**
	 *  @Description: 删除页面
	 * 	@param key   删除记录标识
	 * 	@param
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/delete/{key}")
	public String delete(@PathVariable String key,RedirectAttributes redirectAttributes){
		
		//执行删除操作
		dictService.delete(key);
		//设置提示语
		setRedirectResult(redirectAttributes, true, DELETE_SUCCEED);
		return "redirect:/dictionary";
	}
	
	@RequestMapping("/getName")
	@ResponseBody
	public Map<String, Object> customerJudge(String cname) throws UnsupportedEncodingException {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		// url 中文参数转码
		cname = new String(cname.getBytes("ISO-8859-1"), "utf8");  
		//获取联动表单内容
		List<Dictionary> clist = dictService.findClist(cname);
		modelMap.put("clist",clist);
		return modelMap;
	}
	
	/**
	 *	@Description	添加页面
	 * 	@param customer		Customer实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/submitAdd")
	public String Add(Model model,String consults, String sex, RedirectAttributes redirectAttributes){
			System.out.println("客户主键集合"+consults);
			System.out.println("咨询师主键"+sex);
			//执行添加操作
			boolean flag = true;
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? ADD_SUCCEED : ADD_FAIL);		
			//返回列表页面
			return "redirect:/dictionary";
			
	}
}
