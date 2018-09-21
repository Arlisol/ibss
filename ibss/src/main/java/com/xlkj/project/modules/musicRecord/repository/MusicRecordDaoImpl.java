package com.xlkj.project.modules.musicRecord.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;
import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.MusicRecord;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Comments: 点歌记录模块数据持久层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅  
 * Create Date：   2018-6-26 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Repository
public class MusicRecordDaoImpl extends BaseDao implements IMusicRecordDao {
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(MusicRecordDaoImpl.class);
	
	/**
	* @Description		点歌记录分页查询
	* @param pageable	Pageable实例	
	* @param musicRecord	MusicRecord实例
	* @param user	UserInfo实例
	* @return findWithPage	Page<MusicRecord>实例
	*/
	@Override
	public Page<MusicRecord> findWithPage(Pageable pageable,MusicRecord musicRecord, UserInfo user) {
		// 初始化查询条件
		String musicName = musicRecord.getMusicName();
		String recordDate = musicRecord.getRecordDate();
		String userType = user.getUserType();
		String userKey = user.getUserKey();
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.musicKey,DATE_FORMAT(a.recordDate,'%Y-%m-%d %H:%i') AS recordDate,b.userName,c.name,d.musicName ");
		sql.append(" FROM per_musicrecord a ");
		sql.append(" LEFT JOIN sys_user b ON a.userKey=b.userKey ");
		sql.append(" LEFT JOIN sys_userinfo c ON b.userKey=c.userKey ");
		sql.append(" LEFT JOIN per_music d ON a.musicKey=d.musicKey ");
		sql.append(" WHERE a.deleteFlag=0 AND b.deleteFlag=0 AND c.deleteFlag=0 AND d.deleteFlag=0 ");
		//判断查询条件
		if (userType != null && !"".equals(userType) && userType.equals("2") ) {
			sql.append(" AND a.userKey='"+userKey+"' ");
		}
		if (userType != null && !"".equals(userType) && userType.equals("3") ) {
			sql.append(" AND a.userKey='"+userKey+"' ");
		}
		if (musicName != null && !"".equals(musicName)) {
			sql.append(" AND d.musicName LIKE '"+DbUtils.getFullImplict(musicName)+"' ");
		}
		if (recordDate != null && !"".equals(recordDate)) {
			sql.append(" AND DATE_FORMAT(a.recordDate,'%Y-%m-%d')='"+recordDate+"' ");
		}
		//返回一个查询分页对象集合
		return queryForPage(sql.toString(), pageable,new MusicRecordListRowMapper(), null);
	}

	/*创建一个实现 MusicRecord 序列化接口的实现类 MusicRecordListRowMapper*/ 
	public class MusicRecordListRowMapper implements ParameterizedRowMapper<MusicRecord> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	musicRecord	MusicRecord实例
		*/
		@Override
		public MusicRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 MusicRecord 对象
			MusicRecord musicRecord = new MusicRecord();
			musicRecord.setMusicKey(rs.getString("musicKey"));
			musicRecord.setRecordDate(rs.getString("recordDate"));
			musicRecord.setUserName(rs.getString("userName"));
			musicRecord.setName(rs.getString("name"));
			musicRecord.setMusicName(rs.getString("musicName"));
			return musicRecord;
		}
	}
}
