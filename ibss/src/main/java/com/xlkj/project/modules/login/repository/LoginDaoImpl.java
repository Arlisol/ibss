package com.xlkj.project.modules.login.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;
import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.LoginRecord;
import com.xlkj.project.domain.UserInfo;
import com.xlkj.project.domain.Menu;

/** 
 * Project:  ibss                                       
 * Module ID: login
 * Comments:   登录模块数据持久层实现类                                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅           
 * Create Date：  2018-6-23 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Repository
public class LoginDaoImpl extends BaseDao implements ILoginDao {

	Logger logger = LoggerFactory.getLogger(LoginDaoImpl.class);
	
	@Override
	public UserInfo findUser(UserInfo userInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT userKey,password,userType,lastLoginTime ");
		sql.append(" FROM sys_user "); 
		sql.append(" WHERE userName=? AND password=? AND deleteFlag=0 ");
		Object[] args = { userInfo.getUserName(),DbUtils.ibssEncrypt(userInfo.getPassword())};
		return queryForObject(sql.toString(), args,new AdminRowMapper());	
	}

	public class AdminRowMapper implements ParameterizedRowMapper<UserInfo> {
       	@Override
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
       		UserInfo user = new UserInfo();
       		user.setUserKey(rs.getString("userKey"));
       		user.setPassword(rs.getString("password"));
       		user.setUserType(rs.getString("userType"));
       		user.setLastLoginTime(rs.getString("lastLoginTime"));
			return user;
		}
	}
	
	/**
	 * 获取用户信息
	 * 
	 * @Description:
	 * @param user
	 * @return UserBean
	 */
	public UserInfo findUserInfo(UserInfo userInfo) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.userKey,a.password,a.userType,DATE_FORMAT(a.lastLoginTime,'%Y年%c月%e日 %k:%i') AS lastLoginTime,a.version,b.name  ");
		sql.append(" FROM sys_user a  ");
		sql.append(" LEFT JOIN sys_userinfo b ON a.userKey=b.userKey "); 
		sql.append(" WHERE a.userName=? AND a.password=? AND a.deleteFlag=0 AND b.deleteFlag=0 ");
		Object[] args = { userInfo.getUserName(),DbUtils.ibssEncrypt(userInfo.getPassword())};
		return queryForObject(sql.toString(), args, new UserRowMapper());		
	}
	
	public class UserRowMapper implements ParameterizedRowMapper<UserInfo> {
       	@Override
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
       		UserInfo user = new UserInfo();
       		user.setUserKey(rs.getString("userKey"));
       		user.setPassword(rs.getString("password"));
       		user.setUserType(rs.getString("userType"));
       		user.setLastLoginTime(rs.getString("lastLoginTime"));
       		user.setVersion(rs.getInt("version"));
       		user.setName(rs.getString("name"));
			return user;
		}
	}
	
	/**
	 * 更新登录时间
	 * 
	 * @param user
	 */
	public void lastLoginTimeUpdate(String userKey) {
		String sql = " update sys_user set lastLoginTime = ?  where userKey = ? ";
		Object[] args = { DbUtils.getTime(), userKey};
		update(sql, args);
	}
	
	/**
	 * 获取一级菜单
	 * 
	 * @Description:
	 * @param roleKeys
	 * @return List<Menu>
	 */
	@Override
	public List<Menu> getMenuList(String userType) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT menuKey,menuName,menuLogo FROM sys_menu ");
		sql.append(" WHERE menuLevel=1 AND userType=? AND deleteFlag=0 ");	
		Object[] args = {userType};
		return query(sql.toString(),args,new MenuRowMapper());
	}
	
	public class MenuRowMapper implements ParameterizedRowMapper<Menu>{

		@Override
		public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
			Menu menu = new Menu();
			menu.setMenuKey(rs.getString("menuKey"));
			menu.setMenuName(rs.getString("menuName"));
			menu.setMenuLogo(rs.getString("menuLogo"));
			return menu;
		}
	}
	
	/**
	 * 获取二级菜单
	 * 
	 * @Description:
	 * @param userKey 
	 * @param parentMenuKey
	 * @return Menu
	 */
	@Override
	public List<Menu> getSubMenuList(String userType,String menuKey) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT menuName,menuURL FROM sys_menu ");
		sql.append(" WHERE menuLevel=2 AND userType=? AND parentMenuKey=? AND deleteFlag=0 ");
		sql.append(" ORDER BY menuSequence ");
		Object[] args = {userType,menuKey};
		return query(sql.toString(),args,new SubMenuListRowMapper());
	}
	
	public class SubMenuListRowMapper implements ParameterizedRowMapper<Menu>{

		@Override
		public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
			Menu menu = new Menu();
			menu.setMenuName(rs.getString("menuName"));
			menu.setMenuURL(rs.getString("menuURL"));			
			return menu;
		}
	}
	
	@Override
	public int errorCount(String userKey) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(messageKey) FROM sys_message WHERE userKey=? AND result!='ok' AND deleteFlag=0 ");
		Object[] args =new Object[]{userKey};
		return queryForInt(sql.toString(), args);
	}

	@Override
	public int isLogined(LoginRecord loginRecord) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(loginRecordKey) FROM sys_loginrecord ");
		sql.append(" WHERE userKey=? AND seessionId=? AND loginFlag=0 ");
		Object[] args =new Object[]{loginRecord.getUserKey(),loginRecord.getSeesionId()};
		return queryForInt(sql.toString(), args);
	}

	@Override
	public boolean setSession(LoginRecord loginRecord) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO sys_loginrecord(loginRecordKey, ");
		sql.append(" userKey,ipAddr,seessionId)VALUES (?,?,?,?) ");
		Object[] args =new Object[]{DbUtils.createId(),loginRecord.getUserKey(),
				loginRecord.getIpAddr(),loginRecord.getSeesionId()};
		return update(sql.toString(), args) > 0 ? true : false;
	}

	@Override
	public void cleanSession(String sessionId,String userKey) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE sys_loginrecord SET loginFlag=1 WHERE userKey=? AND seessionId=? AND loginFlag=0");
		Object[] args =new Object[]{userKey,sessionId};
		update(sql.toString(), args);
	}
}
