package com.xlkj.project.modules.buyRecord.repository;

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
import com.xlkj.project.domain.BuyRecord;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Comments: 购买记录模块数据持久层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅  
 * Create Date：   2018-6-26 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Repository
public class BuyRecordDaoImpl extends BaseDao implements IBuyRecordDao {
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(BuyRecordDaoImpl.class);
	
	/**
	* @Description		购买记录分页查询
	* @param pageable	Pageable实例	
	* @param buyRecord	BuyRecord实例
	* @param user	UserInfo实例
	* @return findWithPage	Page<BuyRecord>实例
	*/
	@Override
	public Page<BuyRecord> findWithPage(Pageable pageable,BuyRecord buyRecord, UserInfo user) {
		// 初始化查询条件
		String commodityName = buyRecord.getCommodityName();
		String recordDate = buyRecord.getRecordDate();
		String userType = user.getUserType();
		String userKey = user.getUserKey();
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.commodityKey,a.recordCount,DATE_FORMAT(a.recordDate,'%Y-%m-%d %H:%i') AS recordDate,a.cost,b.userName,c.name,d.commodityName ");
		sql.append(" FROM per_buyrecord a ");
		sql.append(" LEFT JOIN sys_user b ON a.userKey=b.userKey ");
		sql.append(" LEFT JOIN sys_userinfo c ON b.userKey=c.userKey ");
		sql.append(" LEFT JOIN per_commodity d ON a.commodityKey=d.commodityKey ");
		sql.append(" WHERE a.deleteFlag=0 AND b.deleteFlag=0 AND c.deleteFlag=0 AND d.deleteFlag=0 ");
		//判断查询条件
		if (userType != null && !"".equals(userType) && userType.equals("2") ) {
			sql.append(" AND a.userKey='"+userKey+"' ");
		}
		if (userType != null && !"".equals(userType) && userType.equals("3") ) {
			sql.append(" AND a.userKey='"+userKey+"' ");
		}
		if (commodityName != null && !"".equals(commodityName)) {
			sql.append(" AND d.commodityName LIKE '"+DbUtils.getFullImplict(commodityName)+"' ");
		}
		if (recordDate != null && !"".equals(recordDate)) {
			sql.append(" AND DATE_FORMAT(a.recordDate,'%Y-%m-%d')='"+recordDate+"' ");
		}
		sql.append(" ORDER BY a.recordDate DESC ");
		//返回一个查询分页对象集合
		return queryForPage(sql.toString(), pageable,new BuyRecordListRowMapper(), null);
	}

	/*创建一个实现 BuyRecord 序列化接口的实现类 MusicRecordListRowMapper*/ 
	public class BuyRecordListRowMapper implements ParameterizedRowMapper<BuyRecord> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	buyRecord	BuyRecord实例
		*/
		@Override
		public BuyRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 BuyRecord 对象
			BuyRecord buyRecord = new BuyRecord();
			buyRecord.setCommodityKey(rs.getString("commodityKey"));
			buyRecord.setRecordCount(rs.getString("recordCount"));
			buyRecord.setRecordDate(rs.getString("recordDate"));
			buyRecord.setCost(rs.getString("cost"));
			buyRecord.setUserName(rs.getString("userName"));
			buyRecord.setName(rs.getString("name"));
			buyRecord.setCommodityName(rs.getString("commodityName"));
			return buyRecord;
		}
	}
}
