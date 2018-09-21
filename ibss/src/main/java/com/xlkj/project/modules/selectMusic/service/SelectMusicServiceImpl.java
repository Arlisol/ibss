package com.xlkj.project.modules.selectMusic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.selectMusic.repository.ISelectMusicDao;

/** 
 * Project:  ibss                                      
 * Comments: 点歌模块业务逻辑层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-27 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Service
@Transactional
public class SelectMusicServiceImpl implements ISelectMusicService {
	
	//日志记录
	Logger logger =  LoggerFactory.getLogger(SelectMusicServiceImpl.class);
	
	@Autowired
	private ISelectMusicDao selectMusicDao;

	/**
	 *  @Description	添加点歌记录
	 * 	@param	musicKey	音乐主键
	 *	@param	user	UserInfo实例
	 * 	@return	boolean	
	 */
	@Override
	public boolean add(String musicKey, UserInfo user) {
		
		return selectMusicDao.add(musicKey, user);
	}
}
