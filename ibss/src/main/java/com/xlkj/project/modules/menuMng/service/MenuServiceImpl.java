package com.xlkj.project.modules.menuMng.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.Menu;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.modules.menuMng.repository.IMenuDao;

/** 
 * Project:  ibss                                      
 * Comments: 菜单维护模块业务逻辑层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-23 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Service
@Transactional
public class MenuServiceImpl implements IMenuService {
	
	//日志记录
	Logger logger =  LoggerFactory.getLogger(MenuServiceImpl.class);
	
	@Autowired
	private IMenuDao menuDao;

	/**
	* @Description		前台菜单分页查询
	* @param pageable	Pageable实例	
	* @param menu	Menu实例
	* @return findWithPage	Page<Menu>实例
	*/
	@Override
	public Page<Menu> findWithPage(Pageable pageable, Menu menu) {
		
		return menuDao.findWithPage(pageable, menu);
	}
	
	/**
	* @Description		获取一级菜单列表
	* @return getMenuLevel1List	List<Menu>实例
	*/
	@Override
	public List<Menu> getMenuLevel1List() {
		
		return menuDao.getMenuLevel1List();
	}

	/**
	* @Description		添加操作
	* @param menu	Menu实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean add(Menu menu, UserInfo user) {
		//自动生成菜单主键
		menu.setMenuKey(DbUtils.createId());
		
		return menuDao.add(menu, user);
	}
	
	/**
	* @Description		获取菜单原内容
	* @param menuKey	菜单主键	
	* @return getMenu		Menu实例
	*/
	@Override
	public Menu getMenu(String menuKey) {
		
		return menuDao.getMenu(menuKey);
	}

	/**
	* @Description		修改操作
	* @param menu	Menu实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modify(Menu menu, UserInfo user) {

		return menuDao.modify(menu, user);
	}
	
	/**
	* @Description		获取菜单详细
	* @param menuKey	菜单主键
	* @return detail		Menu实例
	*/
	@Override
	public Menu detail(String menuKey) {
		
		return menuDao.detail(menuKey);
	}
	
	/**
	* @Description		删除操作
	* @param menuKey	菜单主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean delete(String menuKey, UserInfo user) {
		
		return menuDao.delete(menuKey, user);
	}

	/**
	* @Description		菜单名称唯一性校验
	* @param userMenu	Menu实例
	* @return boolean
	*/
	@Override
	public boolean menuNameUnique(Menu menu) {
		
		return menuDao.menuNameUnique(menu);
	}

	@Override
	public int countmeun(String menuKey) {
		
		return menuDao.countmeun(menuKey);
	}
}
