package com.xlkj.project.modules.commodityClassMng.repository;

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
import com.xlkj.project.domain.CommodityClass;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Comments: 商品类别管理模块数据持久层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅  
 * Create Date：   2018-6-25 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Repository
public class CommodityClassDaoImpl extends BaseDao implements ICommodityClassDao {
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(CommodityClassDaoImpl.class);
	
	/**
	* @Description		商品类别分页查询
	* @param pageable	Pageable实例	
	* @param commodityClass	CommodityClass实例
	* @return findWithPage	Page<CommodityClass>实例
	*/
	@Override
	public Page<CommodityClass> findWithPage(Pageable pageable, CommodityClass commodityClass) {
		// 初始化查询条件
		String commodityClassName = commodityClass.getCommodityClassName();
		String commodityClassID = commodityClass.getCommodityClassID();
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT commodityClassKey,commodityClassName,commodityClassID ");
		sql.append(" FROM per_commodityclass ");
		sql.append(" WHERE deleteFlag=0 ");
		//判断查询条件
		if (commodityClassName != null && !"".equals(commodityClassName)) {
			sql.append(" AND commodityClassName LIKE '"+DbUtils.getFullImplict(commodityClassName)+"' ");
		}
		if (commodityClassID != null && !"".equals(commodityClassID)) {
			sql.append(" AND commodityClassID='"+commodityClassID+"' ");
		}
		sql.append(" ORDER BY createDate DESC ");
		//返回一个查询分页对象集合
		return queryForPage(sql.toString(), pageable,new CommodityClassListRowMapper(), null);
	}

	/*创建一个实现 CommodityClass 序列化接口的实现类CommodityClassListRowMapper*/ 
	public class CommodityClassListRowMapper implements ParameterizedRowMapper<CommodityClass> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	commodityClass	CommodityClass实例
		*/
		@Override
		public CommodityClass mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 CommodityClass 对象
			CommodityClass commodityClass = new CommodityClass();
			commodityClass.setCommodityClassKey(rs.getString("commodityClassKey"));
			commodityClass.setCommodityClassName(rs.getString("commodityClassName"));
			commodityClass.setCommodityClassID(rs.getString("commodityClassID"));
			return commodityClass;
		}
	}
	
	/**
	* @Description		添加操作
	* @param commodityClass	CommodityClass实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean add(CommodityClass commodityClass, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO per_commodityclass ");
		sql.append(" (commodityClassKey,commodityClassName,commodityClassID,remark,createrKey,createDate,deleteFlag) ");
		sql.append(" VALUES (?,?,?,?,?,?,?) ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {DbUtils.createId(),commodityClass.getCommodityClassName(),commodityClass.getCommodityClassID(),commodityClass.getRemark(),user.getUserKey(),DbUtils.getTime(),0};		
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		获取商品类别原内容
	* @param commodityClassKey	商品类别主键	
	* @return getCommodityClass		CommodityClass实例
	*/
	@Override
	public CommodityClass getCommodityClass(String commodityClassKey) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT commodityClassKey,commodityClassName,commodityClassID,remark ");
		sql.append(" FROM per_commodityclass ");
		sql.append(" WHERE deleteFlag=0 AND commodityClassKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {commodityClassKey};
		return queryForObject(sql.toString(), args, new GetCommodityClassRowMapper());
	}

	/*创建一个实现 CommodityClass 序列化接口的实现类 GetCommodityClassRowMapper*/
	public class GetCommodityClassRowMapper implements ParameterizedRowMapper<CommodityClass> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	commodityClass	CommodityClass实例
		*/
		@Override
		public CommodityClass mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 CommodityClass 对象
			CommodityClass commodityClass = new CommodityClass();
			commodityClass.setCommodityClassKey(rs.getString("commodityClassKey"));
			commodityClass.setCommodityClassName(rs.getString("commodityClassName"));
			commodityClass.setCommodityClassID(rs.getString("commodityClassID"));
			commodityClass.setRemark(rs.getString("remark"));
			return commodityClass;
		}
	}
	
	/**
	* @Description		修改操作
	* @param commodityClass	CommodityClass实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modify(CommodityClass commodityClass, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE per_commodityclass SET ");
		sql.append(" commodityClassKey=?,commodityClassName=?,commodityClassID=?,remark=?,modifierKey=?,modifyDate=? ");
		sql.append(" WHERE commodityClassKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {commodityClass.getCommodityClassKey(),commodityClass.getCommodityClassName(),commodityClass.getCommodityClassID(),commodityClass.getRemark(),user.getUserKey(),DbUtils.getTime(),commodityClass.getCommodityClassKey()};
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		删除操作
	* @param commodityClassKey	商品类别主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean delete(String commodityClassKey,UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE per_commodityclass SET ");
		sql.append(" modifierKey=?,modifyDate=?,deleteFlag=? ");
		sql.append(" WHERE commodityClassKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {user.getUserKey(),DbUtils.getTime(),1,commodityClassKey};
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	@Override
	public int countCommodityClass(String commodityClassKey) {
		String sql="SELECT COUNT(commodityKey) FROM per_commodity WHERE deleteFlag=0 AND commodityClassKey=?";
		Object[] args=new Object[]{commodityClassKey};
		return queryForInt(sql, args);
	}
}
