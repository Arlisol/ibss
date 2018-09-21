package com.xlkj.project.modules.selectCommodity.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;
import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.BuyRecord;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Comments: 购物模块数据持久层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅  
 * Create Date：   2018-6-27 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Repository
public class SelectCommodityDaoImpl extends BaseDao implements ISelectCommodityDao {
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(SelectCommodityDaoImpl.class);
	
	/**
	 *  @Description	添加购物记录
	 * 	@param	buyRecord	BuyRecord实例
	 *	@param	user	UserInfo实例
	 * 	@return	boolean	
	 */
	@Override
	public boolean add(BuyRecord buyRecord, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO per_buyrecord ");
		sql.append(" (buyRecordKey,userKey,commodityKey,recordCount,recordDate,cost,createrKey,createDate,deleteFlag) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?) ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {DbUtils.createId(),user.getUserKey(),buyRecord.getCommodityKey(),buyRecord.getRecordCount(),DbUtils.getTime(),buyRecord.getCost(),user.getUserKey(),DbUtils.getTime(),0};
		return update(sql.toString(), args)>0 ? true : false;
	}
}
