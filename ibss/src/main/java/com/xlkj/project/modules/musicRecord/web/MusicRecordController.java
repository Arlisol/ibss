package com.xlkj.project.modules.musicRecord.web;

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

import com.xlkj.framework.utils.mapper.BeanMapper;
import com.xlkj.framework.web.Servlets;
import com.xlkj.framework.web.base.BaseController;
import com.xlkj.project.domain.MusicRecord;
import com.xlkj.project.domain.Result;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.musicRecord.service.IMusicRecordService;

/** 
 * Project:  ibss                                       
 * Comments: 点歌记录模块控制层                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-26 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Controller
@RequestMapping(value="/musicRecord")
public class MusicRecordController extends BaseController{
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(MusicRecordController.class);
	
	@Autowired
	private IMusicRecordService musicRecordService;
	
	/**
	 *	@Description	点歌记录查询页面
	 *	@param	modelMap	模型实例
	 *	@param pageable	 分页实例
	 *	@param request	 ServletRequest实例
	 *	@param result	 Result实例
	 *	@return String	页面 url
	 */
	@RequestMapping(value="/list")
	public String MenuList(Model model,Pageable pageable, HttpSession session, ServletRequest request, Result result){
		//从请求中取得search_开头的参数及其值，并封装到map中，供后续查询使用
		Map<String,Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		//将一个 MusicRecord 对象转化为一个 JavaBean，MusicRecord.class返回一Class类型的对象，相当于实例化了一个MusicRecord对象
		MusicRecord musicRecord = BeanMapper.convertMap(MusicRecord.class, searchParams);
		//获取用户信息
		UserInfo user=(UserInfo)session.getAttribute("user");
		String userType = user.getUserType();
		//获取点歌记录信息，分页显示
		Page<MusicRecord> musicRecordList = musicRecordService.findWithPage(pageable, musicRecord, user);
		//通过model把userType传到前台页面
		model.addAttribute("userType", userType);
		//通过model把musicRecordList传到前台页面
		model.addAttribute("musicRecordList", musicRecordList);
		//设置提示信息
		setResult(model, result.isStatus(), result.getMessage());
		//跳转到查询页面
		return "musicRecord/musicRecordList";
	}
}
