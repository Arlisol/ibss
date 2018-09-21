package com.xlkj.project.modules.selectCommodity.service;

import com.xlkj.project.domain.BuyRecord;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                      
 * Comments: 购物模块业务逻辑层接口                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-27
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface ISelectCommodityService {
	//添加购物记录
	public boolean add(String buyRecords,BuyRecord buyRecord,UserInfo user);
}
