package com.xlkj.project.modules.menuMng.web;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.xlkj.framework.utils.mapper.BeanMapper;
import com.xlkj.framework.web.Servlets;
import com.xlkj.framework.web.base.BaseController;
import com.xlkj.project.domain.Menu;
import com.xlkj.project.domain.Result;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.menuMng.service.IMenuService;

/** 
 * Project:  ibss                                       
 * Comments: 菜单维护模块控制层                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-23 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Controller
@RequestMapping(value="/menu")
public class MenuController extends BaseController{
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private IMenuService menuService;
	
	/**
	 *	@Description	菜单查询页面
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
		//将一个 Map 对象转化为一个 JavaBean，Menu.class返回一Class类型的对象，相当于实例化了一个Menu对象
		Menu menu = BeanMapper.convertMap(Menu.class, searchParams);
		//获取菜单信息，分页显示
		Page<Menu> menuList = menuService.findWithPage(pageable, menu);	
		//通过model把menuList传到前台页面
		model.addAttribute("menuList", menuList);
		//获取一级菜单列表
		List<Menu> menuLevel1List = menuService.getMenuLevel1List();	
		//通过model把menuLevel1List传到前台页面
		model.addAttribute("menuLevel1List", menuLevel1List);
		//设置提示信息
		setResult(model, result.isStatus(), result.getMessage());
		//跳转到查询页面
		return "menuMng/menuList";
	}
	
	/**
	 *	@Description	添加页面
	 * 	@param menu		Menu实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}Add")
	public String Add(Model model, Menu menu, HttpSession session,
			@PathVariable String doWhat, RedirectAttributes redirectAttributes){
		if(doWhat.equals("into")){
			//获取一级菜单列表
			List<Menu> menuLevel1List = menuService.getMenuLevel1List();	
			//通过model把menuLevel1List传到前台页面
			model.addAttribute("menuLevel1List", menuLevel1List);
			//跳转到添加页面
			return "menuMng/menuAdd";
		} else {
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			//执行添加操作
			boolean flag = menuService.add(menu, user);
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? ADD_SUCCEED : ADD_FAIL);		
			//返回列表页面
			return "redirect:/menu/list";
		}	
	}
	
	/**
	 * @Description	修改页面
	 * 	@param menu		Menu实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param menuKey		url截取的菜单主键
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}Modify/{menuKey}")
	public String Modify(Model model, Menu menu, HttpSession session,
			@PathVariable String doWhat, @PathVariable String menuKey, RedirectAttributes redirectAttributes){
		if(doWhat.equals("into")){
			//获取一级菜单列表
			List<Menu> menuLevel1List = menuService.getMenuLevel1List();	
			//通过model把menuLevel1List传到前台页面
			model.addAttribute("menuLevel1List", menuLevel1List);
			//查询菜单详细
			Menu getMenu = menuService.getMenu(menuKey);	
			//通过model把getMenu传到前台页面
			model.addAttribute("getMenu", getMenu);
			//跳转到修改页面
			return "menuMng/menuModify";
		} else {
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			menu.setMenuKey(menuKey);
			//执行修改操作
			boolean flag = menuService.modify(menu, user);
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? MODIFY_SUCCEED : MODIFY_FAIL);		
			//返回列表页面
			return "redirect:/menu/list";
		}	
	}
	
	/**
	 *  @Description	详细页面
	 * 	@param menuKey	url截取的菜单主键
	 *	@param model	Model实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/detail/{menuKey}")
	public String Detail(Model model, @PathVariable String menuKey){
		//查询详细
		Menu menu = menuService.detail(menuKey);	
		//通过model把menu传到前台页面
		model.addAttribute("menu", menu);
		//跳转到详细页面
		return "menuMng/menuDetail";
	}
	
	/**
	 *  @Description	菜单logo码列表
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/menuLogoList")
	public String MenuLogoList(){
		return "menuMng/menuLogoList";
	}
	
	/**
	 *  @Description	删除操作
	 * 	@param	menuKey		url截取的菜单主键
	 *	@param	session	HttpSession实例
	 *	@param	redirectAttributes	RedirectAttributes实例
	 * 	@return	String	页面 url
	 */
	@RequestMapping(value="/intoDelete/{menuKey}")
	public String Delete(RedirectAttributes redirectAttributes, @PathVariable String menuKey, HttpSession session){
		//获取用户信息
		UserInfo user=(UserInfo)session.getAttribute("user");
		//执行删除操作
		boolean flag = menuService.delete(menuKey, user);
		//设置操作结果
		setRedirectResult(redirectAttributes, flag, flag ? DELETE_SUCCEED : DELETE_FAIL);		
		//返回列表页面
		return "redirect:/menu/list";
	}
	
	/**
	 *  @Description	菜单名称唯一性校验
	 *	@param	menu		Menu实例
	 * 	@return	boolean	
	 */
	@RequestMapping(value="menuNameUnique",method=RequestMethod.POST)
	@ResponseBody
	public boolean menuNameUnique(Menu menu) throws IOException {
		//判断菜单名是否和原内容相同
		boolean same = menu.getMenuName().trim().equals(menu.getHidMenuName());
		//菜单名称唯一性校验
		return same ? true : menuService.menuNameUnique(menu);
	}
	
	@RequestMapping(value="/countmenu")
	@ResponseBody
	public Map<String, Object> countmenu(String menuKey){
		Map<String, Object> map = new HashMap<String, Object>();
		int flag=menuService.countmeun(menuKey);
		map.put("flag", flag);
		return map;
	}
}
