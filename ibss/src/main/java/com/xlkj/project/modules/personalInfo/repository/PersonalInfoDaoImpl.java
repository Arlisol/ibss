package com.xlkj.project.modules.personalInfo.repository;

/** 
 * Project:  ibss                                       
 * Module ID: personalInfo
 * Comments:   个人资料模块数据持久层实现类                                          
 * JDK version used:      JDK1.7                              
 * Author：       赵志沅           
 * Create Date：  2018-6-23 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;
import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.UserInfo;

@Repository
public class PersonalInfoDaoImpl extends BaseDao implements IPersonalInfoDao {
	
	public class userInfoRowMapper implements ParameterizedRowMapper<UserInfo>{
		@Override
		public UserInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
			UserInfo user=new UserInfo();
			user.setUserName(rs.getString("userName"));
			user.setUserID(rs.getInt("userID"));
			user.setPassword(rs.getString("password"));
			user.setUserType(rs.getString("userType"));
			user.setCreateDate(rs.getString("createDate"));
			user.setName(rs.getString("name"));
			user.setGender(rs.getString("gender"));
			user.setBirth(rs.getString("birth"));
			user.setPhone(rs.getString("phone"));
			user.setEmail(rs.getString("email"));
			user.setBalance(rs.getString("balance"));
			user.setRemark(DbUtils.ToBr(rs.getString("remark"),101));
			return user;
		}
	}
	@Override
	public UserInfo getUserInfo(String userKey) {
		StringBuffer sql=new StringBuffer();
		sql.append(" SELECT a.userName,a.userID,a.password,a.userType,DATE_FORMAT(a.createDate,'%Y-%c-%e') AS createDate,b.name,b.gender,b.birth,b.phone,b.email,b.balance,b.remark ");
		sql.append(" FROM sys_user a ");
		sql.append(" LEFT JOIN sys_userinfo b ON a.userKey=b.userKey ");
		sql.append(" WHERE a.userKey=? ");
		Object[] args=new Object[]{userKey};
		return queryForObject(sql.toString(), args, new userInfoRowMapper());
	}

	@Override
	public boolean userInfoModify(UserInfo userInfo,UserInfo user) {
		StringBuffer sql=new StringBuffer();
		sql.append(" UPDATE sys_userinfo SET phone=?,email=?,remark=?,modifierKey=?,modifyDate=? WHERE userKey=? ");
		Object[] args=new Object[]{userInfo.getPhone(),userInfo.getEmail(),userInfo.getRemark(),user.getUserKey(),DbUtils.getTime(),userInfo.getUserKey()};
		return update(sql.toString(), args) > 0 ? true : false;
	}
}
