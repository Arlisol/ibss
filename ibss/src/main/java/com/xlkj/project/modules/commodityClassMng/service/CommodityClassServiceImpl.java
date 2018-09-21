package com.xlkj.project.modules.commodityClassMng.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.domain.CommodityClass;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.commodityClassMng.repository.ICommodityClassDao;

/** 
 * Project:  ibss                                      
 * Comments: 商品类别管理模块业务逻辑层实现类                           
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
public class CommodityClassServiceImpl implements ICommodityClassService {
	
	//日志记录
	Logger logger =  LoggerFactory.getLogger(CommodityClassServiceImpl.class);
		
	@Autowired
	private ICommodityClassDao commodityClassDao;

	/**
	* @Description		商品类别分页查询
	* @param pageable	Pageable实例	
	* @param commodityClass	CommodityClass实例
	* @return findWithPage	Page<CommodityClass>实例
	*/
	@Override
	public Page<CommodityClass> findWithPage(Pageable pageable, CommodityClass commodityClass) {
		
		return commodityClassDao.findWithPage(pageable, commodityClass);
	}
	
	/**
	* @Description		添加操作
	* @param commodityClass	CommodityClass实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean add(CommodityClass commodityClass, UserInfo user) {
		
		return commodityClassDao.add(commodityClass, user);
	}

	/**
	* @Description		获取商品类别原内容
	* @param commodityClassKey	商品类别主键	
	* @return getCommodityClass		CommodityClass实例
	*/
	@Override
	public CommodityClass getCommodityClass(String commodityClassKey) {
		
		return commodityClassDao.getCommodityClass(commodityClassKey);
	}

	/**
	* @Description		修改操作
	* @param commodityClass	CommodityClass实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modify(CommodityClass commodityClass, UserInfo user) {
		
		return commodityClassDao.modify(commodityClass, user);
	}

	/**
	* @Description		删除操作
	* @param commodityClassKey	商品类别主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean delete(String commodityClassKey, UserInfo user) {
		
		return commodityClassDao.delete(commodityClassKey, user);
	}
	
	@Override
	public int countCommodityClass(String commodityClassKey) {
		
		return commodityClassDao.countCommodityClass(commodityClassKey);
	}
}
