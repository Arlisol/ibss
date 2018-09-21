package com.xlkj.project.modules.commodityMng.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.Commodity;
import com.xlkj.project.domain.CommodityClass;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                      
 * Comments: 商品管理模块数据持久层接口                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-25 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface ICommodityDao {
	//获取商品信息分页对象
	public Page<Commodity> findWithPage(Pageable pageable, Commodity commodity);
	//获取商品类别列表
	public List<CommodityClass> getCommodityClassList();
	//添加操作
	public boolean add(Commodity commodity, UserInfo user);
	//获取商品原内容
	public Commodity getCommodity(String commodityKey);
	//修改操作
	public boolean modify(Commodity commodity,UserInfo user);
	//删除操作
	public boolean delete(String commodityKey,UserInfo user);
	//获取商品详细
	public Commodity detail(String commodityKey);
}
