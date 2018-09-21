package com.xlkj.project.modules.musicMng.web;

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
import com.xlkj.project.domain.Music;
import com.xlkj.project.domain.Result;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.musicMng.service.IMusicService;

/** 
 * Project:  ibss                                       
 * Comments: 音乐管理模块控制层                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-25 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Controller
@RequestMapping(value="/music")
public class MusicController extends BaseController{
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(MusicController.class);
	
	@Autowired
	private IMusicService musicService;
	
	/**
	 *	@Description	音乐查询页面
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
		//将一个 Music 对象转化为一个 JavaBean，Music.class返回一Class类型的对象，相当于实例化了一个Music对象
		Music music = BeanMapper.convertMap(Music.class, searchParams);
		//获取音乐信息，分页显示
		Page<Music> musicList = musicService.findWithPage(pageable, music);	
		//通过model把musicList传到前台页面
		model.addAttribute("musicList", musicList);
		//设置提示信息
		setResult(model, result.isStatus(), result.getMessage());
		//跳转到查询页面
		return "musicMng/musicList";
	}
	
	/**
	 *	@Description	添加页面
	 * 	@param music	Music实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}Add")
	public String Add(Model model, Music music, HttpSession session,
			@PathVariable String doWhat, RedirectAttributes redirectAttributes){
		if(doWhat.equals("into")){
			//跳转到添加页面
			return "musicMng/musicAdd";
		} else {
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			//执行添加操作
			boolean flag = musicService.add(music, user);
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? ADD_SUCCEED : ADD_FAIL);		
			//返回列表页面
			return "redirect:/music/list";
		}	
	}
	
	/**
	 * @Description	修改页面
	 * 	@param music	Music实例
	 * 	@param model    Model实例
	 *	@param session	HttpSession实例
	 *	@param doWhat	匹配操作
	 *	@param musicKey		url截取的音乐主键
	 *	@param redirectAttributes	RedirectAttributes实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/{doWhat}Modify/{musicKey}")
	public String Modify(Model model, Music music, HttpSession session,
			@PathVariable String doWhat, @PathVariable String musicKey, RedirectAttributes redirectAttributes){
		if(doWhat.equals("into")){
			//查询音乐详细
			Music getMusic = musicService.getMusic(musicKey);
			//通过model把getMusic传到前台页面
			model.addAttribute("getMusic", getMusic);
			//跳转到修改页面
			return "musicMng/musicModify";
		} else {
			//获取用户信息
			UserInfo user=(UserInfo)session.getAttribute("user");
			music.setMusicKey(musicKey);
			//执行修改操作
			boolean flag = musicService.modify(music, user);
			//设置操作结果
			setRedirectResult(redirectAttributes, flag, flag ? MODIFY_SUCCEED : MODIFY_FAIL);		
			//返回列表页面
			return "redirect:/music/list";
		}	
	}
	
	/**
	 *  @Description	详细页面
	 * 	@param musicKey	url截取的音乐主键
	 *	@param model	Model实例
	 * 	@return String	页面 url
	 */
	@RequestMapping(value="/detail/{musicKey}")
	public String Detail(Model model, @PathVariable String musicKey){
		//查询详细
		Music music = musicService.detail(musicKey);	
		//通过model把music传到前台页面
		model.addAttribute("music", music);
		//跳转到详细页面
		return "musicMng/musicDetail";
	}
	
	/**
	 *  @Description	删除操作
	 * 	@param	musicKey		url截取的音乐主键
	 *	@param	session	HttpSession实例
	 *	@param	redirectAttributes	RedirectAttributes实例
	 * 	@return	String	页面 url
	 */
	@RequestMapping(value="/intoDelete/{musicKey}")
	public String Delete(RedirectAttributes redirectAttributes, @PathVariable String musicKey, HttpSession session){
		//获取用户信息
		UserInfo user=(UserInfo)session.getAttribute("user");
		//执行删除操作
		boolean flag = musicService.delete(musicKey, user);
		//设置操作结果
		setRedirectResult(redirectAttributes, flag, flag ? DELETE_SUCCEED : DELETE_FAIL);		
		//返回列表页面
		return "redirect:/music/list";
	}
}
