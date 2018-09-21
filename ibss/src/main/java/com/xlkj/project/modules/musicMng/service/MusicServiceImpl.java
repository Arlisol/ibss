package com.xlkj.project.modules.musicMng.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.domain.Music;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.musicMng.repository.IMusicDao;

/** 
 * Project:  ibss                                      
 * Comments: 音乐管理模块业务逻辑层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-25 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Service
@Transactional
public class MusicServiceImpl implements IMusicService {
	
	//日志记录
	Logger logger =  LoggerFactory.getLogger(MusicServiceImpl.class);
		
	@Autowired
	private IMusicDao musicDao;

	/**
	* @Description		音乐分页查询
	* @param pageable	Pageable实例	
	* @param music	Music实例
	* @return findWithPage	Page<Music>实例
	*/
	@Override
	public Page<Music> findWithPage(Pageable pageable, Music music) {
		
		return musicDao.findWithPage(pageable, music);
	}

	/**
	* @Description		添加操作
	* @param music	Music实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean add(Music music, UserInfo user) {
		
		return musicDao.add(music, user);
	}

	/**
	* @Description		获取音乐原内容
	* @param musicKey	音乐主键	
	* @return getMusic		Music实例
	*/
	@Override
	public Music getMusic(String musicKey) {
		
		return musicDao.getMusic(musicKey);
	}

	/**
	* @Description		修改操作
	* @param music	Music实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modify(Music music, UserInfo user) {
		
		return musicDao.modify(music, user);
	}

	/**
	* @Description		删除操作
	* @param musicKey	音乐主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean delete(String musicKey, UserInfo user) {
		
		return musicDao.delete(musicKey, user);
	}

	/**
	* @Description		获取音乐详细
	* @param musicKey	音乐主键
	* @return detail		Music实例
	*/
	@Override
	public Music detail(String musicKey) {
		
		return musicDao.detail(musicKey);
	}
}
