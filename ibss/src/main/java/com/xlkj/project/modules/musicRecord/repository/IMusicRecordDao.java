package com.xlkj.project.modules.musicRecord.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.MusicRecord;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                      
 * Comments: 点歌记录模块数据持久层接口                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-26 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface IMusicRecordDao {
	//获取点歌记录信息分页对象
	public Page<MusicRecord> findWithPage(Pageable pageable, MusicRecord musicRecord, UserInfo user);
}
