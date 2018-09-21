package com.xlkj.project.modules.commodityClassMng.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.CommodityClass;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                      
 * Comments: 商品类别管理模块数据持久层接口                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-25 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface ICommodityClassDao {
	//获取商品类别信息分页对象
	public Page<CommodityClass> findWithPage(Pageable pageable, CommodityClass commodityClass);
	//添加操作
	public boolean add(CommodityClass commodityClass, UserInfo user);
	//获取商品类别原内容
	public CommodityClass getCommodityClass(String commodityClassKey);
	//修改操作
	public boolean modify(CommodityClass commodityClass,UserInfo user);
	//删除操作
	public boolean delete(String commodityClassKey,UserInfo user);
	
	public int countCommodityClass(String commodityClassKey);
}
