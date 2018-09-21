package com.xlkj.project.modules.musicRecord.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.domain.MusicRecord;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.musicRecord.repository.IMusicRecordDao;

/** 
 * Project:  ibss                                      
 * Comments: 点歌记录模块业务逻辑层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-26 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Service
@Transactional
public class MusicRecordServiceImpl implements IMusicRecordService {
	
	//日志记录
	Logger logger =  LoggerFactory.getLogger(MusicRecordServiceImpl.class);
		
	@Autowired
	private IMusicRecordDao musicRecordDao;
	
	/**
	* @Description		点歌记录分页查询
	* @param pageable	Pageable实例	
	* @param MusicRecord	MusicRecord实例
	* @param user	UserInfo实例
	* @return findWithPage	Page<MusicRecord>实例
	*/
	@Override
	public Page<MusicRecord> findWithPage(Pageable pageable,MusicRecord musicRecord, UserInfo user) {
		
		return musicRecordDao.findWithPage(pageable, musicRecord, user);
	}
}
