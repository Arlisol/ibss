package com.xlkj.project.modules.userMng.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;
import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Module ID: userMng
 * Comments:   用户管理模块数据持久层实现类                                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅           
 * Create Date：  2018-6-24 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Repository
public class UserDaoImpl extends BaseDao implements IUserDao {

	Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	/**
	* @Description		用户分页查询
	* @param pageable	Pageable实例	
	* @param menu	Menu实例
	* @return findWithPage	Page<UserInfo>实例
	*/
	@Override
	public Page<UserInfo> findWithPage(Pageable pageable, UserInfo userInfo) {
		// 初始化查询条件
		String name = userInfo.getName();
		String gender = userInfo.getGender();
		String userName = userInfo.getUserName();
		String userType = userInfo.getUserType();
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.userKey,a.userName,a.userID,a.userType,b.name,b.gender,b.balance ");
		sql.append(" FROM sys_user a ");
		sql.append(" LEFT JOIN sys_userinfo b ON a.userKey=b.userKey ");
		sql.append(" WHERE a.deleteFlag=0 AND b.deleteFlag=0 AND a.userType!=1 ");
		//判断查询条件
		if (name != null && !"".equals(name)) {
			sql.append(" AND b.name LIKE '"+DbUtils.getFullImplict(name)+"' ");
		}
		if (gender != null && !"".equals(gender)) {
			sql.append(" AND b.gender='"+gender+"' ");
		}
		if (userName != null && !"".equals(userName)) {
			sql.append(" AND a.userName='"+userName+"' ");
		}
		if (userType != null && !"".equals(userType)) {
			sql.append(" AND a.userType='"+userType+"' ");
		}
		sql.append(" ORDER BY a.createDate DESC ");
		//返回一个查询分页对象集合
		return queryForPage(sql.toString(), pageable,new UserInfoListRowMapper(), null);
	}

	/*创建一个实现 UserInfo 序列化接口的实现类 UserInfoListRowMapper*/ 
	public class UserInfoListRowMapper implements ParameterizedRowMapper<UserInfo> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	userInfo	UserInfo实例
		*/
		@Override
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Menu 对象
			UserInfo userInfo = new UserInfo();
			userInfo.setUserKey(rs.getString("userKey"));
			userInfo.setUserName(rs.getString("userName"));
			userInfo.setNewUserID(String.format("%04d", rs.getInt("userID")));
			userInfo.setUserType(rs.getString("userType"));
			userInfo.setName(rs.getString("name"));
			userInfo.setGender(rs.getString("gender"));
			userInfo.setBalance(rs.getString("balance"));
			return userInfo;
		}
	}
	
	/**
	* @Description		查询最大用户ID
	* @return int
	*/
	@Override
	public int maxUserID() {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT MAX(userID) FROM sys_user ");
		return queryForInt(sql.toString(),null);
	}
	
	/**
	* @Description		用户添加操作
	* @param userInfo	UserInfo实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean addUser(UserInfo userInfo, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO sys_user ");
		sql.append(" (userKey,userName,userID,password,userType,createrKey,createDate,deleteFlag,version) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?) ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {userInfo.getUserKey(),userInfo.getUserName().trim(),userInfo.getUserID(),userInfo.getPassword(),userInfo.getUserType(),user.getUserKey(),DbUtils.getTime(),0,0};
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		用户信息添加操作
	* @param userInfo	UserInfo实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean addUserInfo(UserInfo userInfo, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO sys_userinfo ");
		sql.append(" (userInfoKey,userKey,name,gender,birth,phone,email,balance,remark,createrKey,createDate,deleteFlag) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?) ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {DbUtils.createId(),userInfo.getUserKey(),userInfo.getName(),userInfo.getGender(),userInfo.getBirth(),userInfo.getPhone(),userInfo.getEmail(),userInfo.getBalance(),userInfo.getRemark(),user.getUserKey(),DbUtils.getTime(),0};
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		获取用户原内容
	* @param userKey	用户主键	
	* @return getUserInfo		UserInfo实例
	*/
	@Override
	public UserInfo getUserInfo(String userKey) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.userKey,a.userName,a.userType,b.name,b.gender,b.birth,b.phone,b.email,b.remark ");
		sql.append(" FROM sys_user a ");
		sql.append(" LEFT JOIN sys_userinfo b ON a.userKey=b.userKey ");
		sql.append(" WHERE a.deleteFlag=0 AND b.deleteFlag=0 AND a.userKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {userKey};
		return queryForObject(sql.toString(), args, new GetUserInfoRowMapper());
	}

	/*创建一个实现 UserInfo 序列化接口的实现类 GetUserInfoRowMapper*/
	public class GetUserInfoRowMapper implements ParameterizedRowMapper<UserInfo> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	userInfo	UserInfo实例
		*/
		@Override
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 UserInfo 对象
			UserInfo userInfo = new UserInfo();
			userInfo.setUserKey(rs.getString("userKey"));
			userInfo.setUserName(rs.getString("userName"));
			userInfo.setUserType(rs.getString("userType"));
			userInfo.setName(rs.getString("name"));
			userInfo.setGender(rs.getString("gender"));
			userInfo.setBirth(rs.getString("birth"));
			userInfo.setPhone(rs.getString("phone"));
			userInfo.setEmail(rs.getString("email"));
			userInfo.setRemark(rs.getString("remark"));
			return userInfo;
		}
	}
	
	/**
	* @Description		用户修改操作
	* @param userInfo	UserInfo实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modifyUser(UserInfo userInfo, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE sys_user SET ");
		sql.append(" userName=?,userType=?,modifierKey=?,modifyDate=? ");
		sql.append(" WHERE userKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {userInfo.getUserName().trim(),userInfo.getUserType(),user.getUserKey(),DbUtils.getTime(),userInfo.getUserKey()};		
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		用户信息修改操作
	* @param userInfo	UserInfo实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modifyUserInfo(UserInfo userInfo, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE sys_userinfo SET ");
		sql.append(" name=?,gender=?,birth=?,phone=?,email=?,remark=?,modifierKey=?,modifyDate=? ");
		sql.append(" WHERE userKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {userInfo.getName(),userInfo.getGender(),userInfo.getBirth(),userInfo.getPhone(),userInfo.getEmail(),userInfo.getRemark(),user.getUserKey(),DbUtils.getTime(),userInfo.getUserKey()};		
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		获取用户详细
	* @param userKey	用户主键
	* @return detail		UserInfo实例
	*/
	@Override
	public UserInfo detail(String userKey) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT a.userKey,a.userName,a.userID,a.password,a.userType,b.name,b.gender,b.birth,b.phone,b.email,b.balance,b.remark ");
		sql.append(" FROM sys_user a ");
		sql.append(" LEFT JOIN sys_userinfo b ON a.userKey=b.userKey ");
		sql.append(" WHERE a.deleteFlag=0 AND b.deleteFlag=0 AND a.userKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {userKey};
		return queryForObject(sql.toString(), args, new DetailRowMapper());
	}

	/*创建一个实现 UserInfo 序列化接口的实现类 DetailRowMapper*/
	public class DetailRowMapper implements ParameterizedRowMapper<UserInfo> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	userInfo	UserInfo实例
		*/
		@Override
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 UserInfo 对象
			UserInfo userInfo = new UserInfo();
			userInfo.setUserKey(rs.getString("userKey"));
			userInfo.setUserName(rs.getString("userName"));
			userInfo.setNewUserID(String.format("%04d", rs.getInt("userID")));
			userInfo.setPassword(DbUtils.ibssDecrypt(rs.getString("password")));
			userInfo.setUserType(rs.getString("userType"));
			userInfo.setName(rs.getString("name"));
			userInfo.setGender(rs.getString("gender"));
			userInfo.setBirth(rs.getString("birth"));
			userInfo.setPhone(rs.getString("phone"));
			userInfo.setEmail(rs.getString("email"));
			userInfo.setBalance(rs.getString("balance"));
			userInfo.setRemark(DbUtils.ToBr(rs.getString("remark"),101));
			return userInfo;
		}
	}
	
	/**
	* @Description		用户删除操作
	* @param userKey	用户主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean deleteUser(String userKey,UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE sys_user SET ");
		sql.append(" modifierKey=?,modifyDate=?,deleteFlag=? ");
		sql.append(" WHERE userKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {user.getUserKey(),DbUtils.getTime(),1,userKey};
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		用户信息删除操作
	* @param userKey	用户主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean deleteUserInfo(String userKey,UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE sys_userinfo SET ");
		sql.append(" modifierKey=?,modifyDate=?,deleteFlag=? ");
		sql.append(" WHERE userKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {user.getUserKey(),DbUtils.getTime(),1,userKey};
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		充值操作
	* @param userInfo	UserInfo实例
	* @param user	UserInfo实例	
	* @return boolean
	*/
	@Override
	public boolean recharge(UserInfo userInfo, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE sys_userinfo SET ");
		sql.append(" balance=?,modifierKey=?,modifyDate=? ");
		sql.append(" WHERE userKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {userInfo.getBalance(),user.getUserKey(),DbUtils.getTime(),userInfo.getUserKey()};
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		充值操作
	* @param userKey	用户主键	
	* @return boolean
	*/
	@Override
	public int getBalance(String userKey) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT balance FROM sys_userinfo ");
		sql.append(" WHERE userKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {userKey};
		return queryForInt(sql.toString(), args);
	}
	
	/**
	* @Description		用户名唯一性校验
	* @param userInfo	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean userNameUnique(UserInfo userInfo) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT COUNT(userName) FROM sys_user WHERE userName=? AND deleteFlag=0 ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {userInfo.getUserName().trim()};
		return queryForInt(sql.toString(), args)>0 ? false : true;
	}
}
