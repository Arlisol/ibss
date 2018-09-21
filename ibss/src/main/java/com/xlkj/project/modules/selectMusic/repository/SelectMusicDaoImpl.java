package com.xlkj.project.modules.selectMusic.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;
import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Comments: 点歌模块数据持久层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅  
 * Create Date：   2018-6-27 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Repository
public class SelectMusicDaoImpl extends BaseDao implements ISelectMusicDao {
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(SelectMusicDaoImpl.class);
	
	/**
	 *  @Description	添加点歌记录
	 * 	@param	musicKey	音乐主键
	 *	@param	user	UserInfo实例
	 * 	@return	boolean	
	 */
	@Override
	public boolean add(String musicKey, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO per_musicrecord ");
		sql.append(" (musicRecordKey,userKey,musicKey,recordDate,createrKey,createDate,deleteFlag) ");
		sql.append(" VALUES (?,?,?,?,?,?,?) ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {DbUtils.createId(),user.getUserKey(),musicKey,DbUtils.getTime(),user.getUserKey(),DbUtils.getTime(),0};
		return update(sql.toString(), args)>0 ? true : false;
	}
}
