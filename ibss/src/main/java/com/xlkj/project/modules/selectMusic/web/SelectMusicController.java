package com.xlkj.project.modules.selectMusic.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xlkj.framework.utils.mapper.BeanMapper;
import com.xlkj.framework.web.Servlets;
import com.xlkj.framework.web.base.BaseController;
import com.xlkj.project.domain.Music;
import com.xlkj.project.domain.Result;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.musicMng.service.IMusicService;
import com.xlkj.project.modules.selectMusic.service.ISelectMusicService;

/** 
 * Project:  ibss                                       
 * Comments: 点歌模块控制层                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-27 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Controller
@RequestMapping(value="/selectMusic")
public class SelectMusicController extends BaseController{
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(SelectMusicController.class);
	
	@Autowired
	private ISelectMusicService selectMusicService;
	
	@Autowired
	private IMusicService musicService;
	
	/**
	 *	@Description	点歌查询页面
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
		//将一个 Map 对象转化为一个 JavaBean，Music.class返回一Music类型的对象，相当于实例化了一个Music对象
		Music music = BeanMapper.convertMap(Music.class, searchParams);
		//获取音乐信息，分页显示
		Page<Music> musicList = musicService.findWithPage(pageable, music);
		//通过model把musicList传到前台页面
		model.addAttribute("musicList", musicList);
		//设置提示信息
		setResult(model, result.isStatus(), result.getMessage());
		//跳转到查询页面
		return "selectMusic/selectMusicList";
	}
	
	/**
	 *  @Description	点歌操作
	 * 	@param	musicKey	音乐主键
	 *	@param	session	HttpSession实例
	 * 	@return	modelMap	
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public Map<String,Object> add(String musicKey,HttpSession session){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		//获取用户信息
		UserInfo user=(UserInfo)session.getAttribute("user");
		boolean flag = selectMusicService.add(musicKey, user);
		modelMap.put("flag", flag);		
		return modelMap;
	}
}
