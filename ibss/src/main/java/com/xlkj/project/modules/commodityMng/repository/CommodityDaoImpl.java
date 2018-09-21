package com.xlkj.project.modules.commodityMng.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;
import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.Commodity;
import com.xlkj.project.domain.CommodityClass;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Comments: 商品管理模块数据持久层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅  
 * Create Date：   2018-6-25 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Repository
public class CommodityDaoImpl extends BaseDao implements ICommodityDao {
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(CommodityDaoImpl.class);
	
	/**
	* @Description		商品分页查询
	* @param pageable	Pageable实例	
	* @param commodity	Commodity实例
	* @return findWithPage	Page<Commodity>实例
	*/
	@Override
	public Page<Commodity> findWithPage(Pageable pageable, Commodity commodity) {
		// 初始化查询条件
		String commodityName = commodity.getCommodityName();
		String commodityClassKey = commodity.getCommodityClassKey();
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.commodityKey,a.commodityName,a.commodityID,a.price,a.stock,b.commodityClassName ");
		sql.append(" FROM per_commodity a ");
		sql.append(" LEFT JOIN per_commodityclass b ON a.commodityClassKey=b.commodityClassKey ");
		sql.append(" WHERE a.deleteFlag=0 AND b.deleteFlag=0 ");
		//判断查询条件
		if (commodityName != null && !"".equals(commodityName)) {
			sql.append(" AND a.commodityName LIKE '"+DbUtils.getFullImplict(commodityName)+"' ");
		}
		if (commodityClassKey != null && !"".equals(commodityClassKey)) {
			sql.append(" AND a.commodityClassKey='"+commodityClassKey+"' ");
		}
		sql.append(" ORDER BY a.createDate DESC ");
		//返回一个查询分页对象集合
		return queryForPage(sql.toString(), pageable,new CommodityListRowMapper(), null);
	}

	/*创建一个实现 Commodity 序列化接口的实现类CommodityListRowMapper*/ 
	public class CommodityListRowMapper implements ParameterizedRowMapper<Commodity> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	commodity	Commodity实例
		*/
		@Override
		public Commodity mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Commodity 对象
			Commodity commodity = new Commodity();
			commodity.setCommodityKey(rs.getString("commodityKey"));
			commodity.setCommodityName(rs.getString("commodityName"));
			commodity.setCommodityID(rs.getString("commodityID"));
			commodity.setPrice(rs.getString("price"));
			commodity.setStock(rs.getString("stock"));
			commodity.setCommodityClassName(rs.getString("commodityClassName"));
			return commodity;
		}
	}
	
	/**
	* @Description		获取商品类别列表
	* @return getCommodityClassList	List<CommodityClass>实例
	*/
	@Override
	public List<CommodityClass> getCommodityClassList() {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT commodityClassKey,CONCAT(IFNULL(commodityClassID,''),'-',IFNULL(commodityClassName,'')) AS commodityClassName FROM per_commodityclass "); 
		sql.append(" WHERE deleteFlag=0 ORDER BY commodityClassID ");
		return query(sql.toString(), new CommodityClassListRowMapper());
	}

	/*创建一个实现 CommodityClass 序列化接口的实现类 CommodityClassListRowMapper*/
	public class CommodityClassListRowMapper implements ParameterizedRowMapper<CommodityClass> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	commodityClass		CommodityClass实例
		*/
		@Override
		public CommodityClass mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 CommodityClass 对象
			CommodityClass commodityClass = new CommodityClass();
			commodityClass.setCommodityClassKey(rs.getString("commodityClassKey"));
			commodityClass.setCommodityClassName(rs.getString("commodityClassName"));
			return commodityClass;
		}
	}
	
	/**
	* @Description		添加操作
	* @param commodity	Commodity实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean add(Commodity commodity, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO per_commodity ");
		sql.append(" (commodityKey,commodityClassKey,commodityName,commodityID,price,stock,remark,createrKey,createDate,deleteFlag) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?) ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {DbUtils.createId(),commodity.getCommodityClassKey(),commodity.getCommodityName(),commodity.getCommodityID(),
				commodity.getPrice(),commodity.getStock(),commodity.getRemark(),user.getUserKey(),DbUtils.getTime(),0};		
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		获取商品原内容
	* @param commodityKey	商品主键	
	* @return getCommodity		Commodity实例
	*/
	@Override
	public Commodity getCommodity(String commodityKey) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT commodityKey,commodityClassKey,commodityName,commodityID,price,stock,remark ");
		sql.append(" FROM per_commodity ");
		sql.append(" WHERE deleteFlag=0 AND commodityKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {commodityKey};
		return queryForObject(sql.toString(), args, new GetCommodityRowMapper());
	}

	/*创建一个实现 Commodity 序列化接口的实现类 GetCommodityRowMapper*/
	public class GetCommodityRowMapper implements ParameterizedRowMapper<Commodity> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	commodity	Commodity实例
		*/
		@Override
		public Commodity mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Commodity 对象
			Commodity commodity = new Commodity();
			commodity.setCommodityKey(rs.getString("commodityKey"));
			commodity.setCommodityClassKey(rs.getString("commodityClassKey"));
			commodity.setCommodityName(rs.getString("commodityName"));
			commodity.setCommodityID(rs.getString("commodityID"));
			commodity.setPrice(rs.getString("price"));
			commodity.setStock(rs.getString("stock"));
			commodity.setRemark(rs.getString("remark"));
			return commodity;
		}
	}
	
	/**
	* @Description		修改操作
	* @param commodity	Commodity实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modify(Commodity commodity, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE per_commodity SET ");
		sql.append(" commodityClassKey=?,commodityName=?,commodityID=?,price=?,stock=?,remark=?,modifierKey=?,modifyDate=? ");
		sql.append(" WHERE commodityKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {commodity.getCommodityClassKey(),commodity.getCommodityName(),commodity.getCommodityID(),
				commodity.getPrice(),commodity.getStock(),commodity.getRemark(),user.getUserKey(),DbUtils.getTime(),commodity.getCommodityKey()};
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		获取商品详细
	* @param commodityKey	商品主键
	* @return detail		Commodity实例
	*/
	@Override
	public Commodity detail(String commodityKey) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.commodityKey,a.commodityName,a.commodityID,a.price,a.stock,a.remark,b.commodityClassName ");
		sql.append(" FROM per_commodity a ");
		sql.append(" LEFT JOIN per_commodityclass b ON a.commodityClassKey=b.commodityClassKey ");
		sql.append(" WHERE a.deleteFlag=0 AND b.deleteFlag=0 AND a.commodityKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {commodityKey};
		return queryForObject(sql.toString(), args, new DetailRowMapper());
	}

	/*创建一个实现 Commodity 序列化接口的实现类 DetailRowMapper*/
	public class DetailRowMapper implements ParameterizedRowMapper<Commodity> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	commodity	Commodity实例
		*/
		@Override
		public Commodity mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Commodity 对象
			Commodity commodity = new Commodity();
			commodity.setCommodityKey(rs.getString("commodityKey"));
			commodity.setCommodityName(rs.getString("commodityName"));
			commodity.setCommodityID(rs.getString("commodityID"));
			commodity.setPrice(rs.getString("price"));
			commodity.setStock(rs.getString("stock"));
			commodity.setRemark(DbUtils.ToBr(rs.getString("remark"),101));
			commodity.setCommodityClassName(rs.getString("commodityClassName"));
			return commodity;
		}
	}
	
	/**
	* @Description		删除操作
	* @param commodityKey	商品主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean delete(String commodityKey,UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE per_commodity SET ");
		sql.append(" modifierKey=?,modifyDate=?,deleteFlag=? ");
		sql.append(" WHERE commodityKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {user.getUserKey(),DbUtils.getTime(),1,commodityKey};
		return update(sql.toString(), args)>0 ? true : false;
	}
}
