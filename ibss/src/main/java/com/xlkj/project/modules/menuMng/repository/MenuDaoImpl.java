package com.xlkj.project.modules.menuMng.repository;

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
import com.xlkj.project.domain.Menu;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Comments: 菜单维护模块数据持久层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅  
 * Create Date：   2018-6-23 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Repository
public class MenuDaoImpl extends BaseDao implements IMenuDao {
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(MenuDaoImpl.class);
	
	/**
	* @Description		菜单分页查询
	* @param pageable	Pageable实例	
	* @param menu	Menu实例
	* @return findWithPage	Page<Menu>实例
	*/
	@Override
	public Page<Menu> findWithPage(Pageable pageable, Menu menu) {
		// 初始化查询条件
		String menuName = menu.getMenuName();
		String menuLevel = menu.getMenuLevel();
		String userType = menu.getUserType();
		String parentMenuKey = menu.getParentMenuKey();
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT sm.menuKey,sm.menuName,sm.menuLevel,spm.menuName AS parentMenuName, ");
		sql.append(" sm.userType,sm.groupSequence,sm.menuSequence,sm.menuLogo FROM sys_menu sm ");
		sql.append(" LEFT JOIN sys_menu spm ON spm.menuKey=sm.parentMenuKey WHERE sm.deleteFlag=0 ");
		//判断查询条件
		if (menuName != null && !"".equals(menuName)) {
			sql.append(" AND sm.menuName LIKE '"+DbUtils.getFullImplict(menuName)+"' ");
		}
		if (menuLevel != null && !"".equals(menuLevel)) {
			sql.append(" AND sm.menuLevel='"+menuLevel+"' ");
		}
		if (userType != null && !"".equals(userType)) {
			sql.append(" AND sm.userType='"+userType+"' ");
		}
		if (parentMenuKey != null && !"".equals(parentMenuKey)) {
			sql.append(" AND sm.parentMenuKey='"+parentMenuKey+"' ");
		}
		sql.append(" ORDER BY sm.groupSequence,sm.menuSequence ");
		//返回一个查询分页对象集合
		return queryForPage(sql.toString(), pageable,new MenuListRowMapper(), null);
	}

	/*创建一个实现 Menu 序列化接口的实现类 MenuListRowMapper*/ 
	public class MenuListRowMapper implements ParameterizedRowMapper<Menu> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	menuList	Menu实例
		*/
		@Override
		public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Menu 对象
			Menu menuList = new Menu();
			menuList.setMenuKey(rs.getString("menuKey"));
			menuList.setMenuName(rs.getString("menuName"));
			menuList.setMenuLevel(rs.getString("menuLevel"));
			menuList.setParentMenuName(rs.getString("parentMenuName"));
			menuList.setUserType(rs.getString("userType"));
			menuList.setGroupSequence(rs.getString("groupSequence"));
			menuList.setMenuSequence(rs.getString("menuSequence"));
			menuList.setMenuLogo(rs.getString("menuLogo"));
			return menuList;
		}
	}
	
	/**
	* @Description		获取一级菜单列表
	* @return getMenuLevel1List	List<Menu>实例
	*/
	@Override
	public List<Menu> getMenuLevel1List() {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT menuKey,menuName FROM sys_menu "); 
		sql.append(" WHERE deleteFlag=0 AND menuLevel=1 ");
		return query(sql.toString(), new MenuLevel1ListRowMapper());
	}

	/*创建一个实现 Menu 序列化接口的实现类 MenuLevel1ListRowMapper*/
	public class MenuLevel1ListRowMapper implements ParameterizedRowMapper<Menu> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	menuLevel1List		Menu实例
		*/
		@Override
		public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Menu 对象
			Menu menuLevel1List = new Menu();
			menuLevel1List.setMenuKey(rs.getString("menuKey"));
			menuLevel1List.setMenuName(rs.getString("menuName"));
			return menuLevel1List;
		}
	}
	
	/**
	* @Description		菜单添加操作
	* @param menu	Menu实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean add(Menu menu, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO sys_menu ");
		sql.append(" (menuKey,menuName,menuLevel,parentMenuKey,groupSequence,menuSequence,userType,menuURL,remark,menuLogo,createrKey,createDate,deleteFlag) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		//初始化一个包含条件参数的Object对象
		Object[] args = null;
		if(menu.getMenuLevel().equals("1")){
			args = new Object[] {menu.getMenuKey(),menu.getMenuName().trim(),menu.getMenuLevel(),null,menu.getGroupSequence(),null,menu.getUserType(),null,menu.getRemark(),menu.getMenuLogo(),user.getUserKey(),DbUtils.getTime(),0};
		}
		if(menu.getMenuLevel().equals("2")){
			args = new Object[] {menu.getMenuKey(),menu.getMenuName().trim(),menu.getMenuLevel(),menu.getParentMenuKey(),null,menu.getMenuSequence(),menu.getUserType(),menu.getMenuURL(),menu.getRemark(),null,user.getUserKey(),DbUtils.getTime(),0};
		}
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		获取菜单原内容
	* @param menuKey	菜单主键	
	* @return getMenu		Menu实例
	*/
	@Override
	public Menu getMenu(String menuKey) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT menuKey,menuName,menuLevel,parentMenuKey,groupSequence,menuSequence,menuURL,userType,remark,menuLogo ");
		sql.append(" FROM sys_menu ");
		sql.append(" WHERE deleteFlag=0 AND menuKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {menuKey};
		return queryForObject(sql.toString(), args, new GetMenuRowMapper());
	}

	/*创建一个实现 Menu 序列化接口的实现类 GetMenuRowMapper*/
	public class GetMenuRowMapper implements ParameterizedRowMapper<Menu> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	menu	Menu实例
		*/
		@Override
		public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Menu 对象
			Menu menu = new Menu();
			menu.setMenuKey(rs.getString("menuKey"));
			menu.setMenuName(rs.getString("menuName"));
			menu.setMenuLevel(rs.getString("menuLevel"));
			menu.setParentMenuKey(rs.getString("parentMenuKey"));
			menu.setGroupSequence(rs.getString("groupSequence"));
			menu.setMenuSequence(rs.getString("menuSequence"));
			menu.setMenuURL(rs.getString("menuURL"));
			menu.setUserType(rs.getString("userType"));
			menu.setRemark(rs.getString("remark"));
			menu.setMenuLogo(rs.getString("menuLogo"));
			return menu;
		}
	}
	
	/**
	* @Description		修改操作
	* @param menu	Menu实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modify(Menu menu, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE sys_menu SET ");
		sql.append(" menuName=?,menuLevel=?,parentMenuKey=?,groupSequence=?,menuSequence=?,menuURL=?,remark=?,menuLogo=?,modifierKey=?,modifyDate=? ");
		sql.append(" WHERE menuKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = null;
		if(menu.getMenuLevel().equals("1")){
			args = new Object[] {menu.getMenuName().trim(),menu.getMenuLevel(),null,menu.getGroupSequence(),null,null,menu.getRemark(),menu.getMenuLogo(),user.getUserKey(),DbUtils.getTime(),menu.getMenuKey()};
		}
		if(menu.getMenuLevel().equals("2")){
			args = new Object[] {menu.getMenuName().trim(),menu.getMenuLevel(),menu.getParentMenuKey(),null,menu.getMenuSequence(),menu.getMenuURL(),menu.getRemark(),null,user.getUserKey(),DbUtils.getTime(),menu.getMenuKey()};
		}
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		获取菜单详细
	* @param menuKey	菜单主键
	* @return detail		Menu实例
	*/
	@Override
	public Menu detail(String menuKey) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.menuKey,a.menuName,a.menuLevel,a.groupSequence,a.menuSequence,a.menuURL,a.userType,a.remark,a.menuLogo,b.menuName AS parentMenuName ");
		sql.append(" FROM sys_menu a ");
		sql.append(" LEFT JOIN sys_menu b ON a.parentMenuKey=b.menuKey ");
		sql.append(" WHERE a.deleteFlag=0 AND a.menuKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {menuKey};
		return queryForObject(sql.toString(), args, new DetailRowMapper());
	}

	/*创建一个实现 Menu 序列化接口的实现类 DetailRowMapper*/
	public class DetailRowMapper implements ParameterizedRowMapper<Menu> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	menu	Menu实例
		*/
		@Override
		public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Menu 对象
			Menu menu = new Menu();
			menu.setMenuName(rs.getString("menuName"));
			menu.setMenuLevel(rs.getString("menuLevel"));
			menu.setParentMenuName(rs.getString("parentMenuName"));
			menu.setGroupSequence(rs.getString("groupSequence"));
			menu.setMenuSequence(rs.getString("menuSequence"));
			menu.setMenuURL(rs.getString("menuURL"));
			menu.setUserType(rs.getString("userType"));
			menu.setRemark(DbUtils.ToBr(rs.getString("remark"),101));
			menu.setMenuLogo(rs.getString("menuLogo"));
			return menu;
		}
	}
	
	/**
	* @Description		删除操作
	* @param menuKey	菜单主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean delete(String menuKey,UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE sys_menu SET ");
		sql.append(" modifierKey=?,modifyDate=?,deleteFlag=? ");
		sql.append(" WHERE menuKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {user.getUserKey(),DbUtils.getTime(),1,menuKey};
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		菜单名称唯一性校验
	* @param menu	Menu实例
	* @return boolean
	*/
	@Override
	public boolean menuNameUnique(Menu menu) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT COUNT(menuName) FROM sys_menu WHERE menuName=? AND deleteFlag=0 ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {menu.getMenuName().trim()};
		return queryForInt(sql.toString(), args)>0 ? false : true;
	}

	@Override
	public int countmeun(String menuKey) {
		String sql="SELECT COUNT(menuKey) FROM sys_menu WHERE deleteFlag=0 AND menuLevel=2 AND parentMenuKey=?";
		Object[] args=new Object[]{menuKey};
		return queryForInt(sql, args);
	}
}
