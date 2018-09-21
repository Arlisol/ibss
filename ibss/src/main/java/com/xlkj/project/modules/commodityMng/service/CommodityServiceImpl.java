package com.xlkj.project.modules.commodityMng.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.domain.Commodity;
import com.xlkj.project.domain.CommodityClass;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.commodityMng.repository.ICommodityDao;

/** 
 * Project:  ibss                                      
 * Comments: 商品管理模块业务逻辑层实现类                           
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
public class CommodityServiceImpl implements ICommodityService {
	
	//日志记录
	Logger logger =  LoggerFactory.getLogger(CommodityServiceImpl.class);
		
	@Autowired
	private ICommodityDao commodityDao;

	/**
	* @Description		商品分页查询
	* @param pageable	Pageable实例	
	* @param commodity	Commodity实例
	* @return findWithPage	Page<Commodity>实例
	*/
	@Override
	public Page<Commodity> findWithPage(Pageable pageable, Commodity commodity) {
		
		return commodityDao.findWithPage(pageable, commodity);
	}

	/**
	* @Description		获取商品类别列表
	* @return getCommodityClassList	List<CommodityClass>实例
	*/
	@Override
	public List<CommodityClass> getCommodityClassList() {
		
		return commodityDao.getCommodityClassList();
	}
	
	/**
	* @Description		添加操作
	* @param commodity	Commodity实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean add(Commodity commodity, UserInfo user) {
		
		return commodityDao.add(commodity, user);
	}

	/**
	* @Description		获取商品原内容
	* @param commodityKey	商品主键	
	* @return getCommodity		Commodity实例
	*/
	@Override
	public Commodity getCommodity(String commodityKey) {
		
		return commodityDao.getCommodity(commodityKey);
	}

	/**
	* @Description		修改操作
	* @param commodity	Commodity实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modify(Commodity commodity, UserInfo user) {
		
		return commodityDao.modify(commodity, user);
	}

	/**
	* @Description		删除操作
	* @param commodityKey	商品主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean delete(String commodityKey, UserInfo user) {
		
		return commodityDao.delete(commodityKey, user);
	}

	/**
	* @Description		获取商品详细
	* @param commodityKey	商品主键
	* @return detail		Music实例
	*/
	@Override
	public Commodity detail(String commodityKey) {
		
		return commodityDao.detail(commodityKey);
	}
}
