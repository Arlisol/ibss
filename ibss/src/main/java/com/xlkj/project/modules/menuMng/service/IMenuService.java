package com.xlkj.project.modules.menuMng.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.Menu;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                      
 * Comments: 菜单维护模块业务逻辑层接口                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-23 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface IMenuService {
	//获取菜单信息分页对象
	public Page<Menu> findWithPage(Pageable pageable, Menu menu);
	//获取一级菜单列表
	public List<Menu> getMenuLevel1List();
	//添加操作
	public boolean add(Menu menu, UserInfo user);
	//获取菜单原内容
	public Menu getMenu(String menuKey);
	//修改操作
	public boolean modify(Menu menu, UserInfo user);
	//获取菜单详细
	public Menu detail(String menuKey);
	//删除操作
	public boolean delete(String menuKey, UserInfo user);
	//菜单名称唯一性校验
	public boolean menuNameUnique(Menu menu);
	
    public int countmeun(String menuKey);
}
