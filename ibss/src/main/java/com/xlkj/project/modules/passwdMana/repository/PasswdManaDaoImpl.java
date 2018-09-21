package com.xlkj.project.modules.passwdMana.repository;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;

@Repository
public class PasswdManaDaoImpl extends BaseDao implements IPasswdManaDao{

	@Override
	public void alterPasswd(String userKey, String password,int version) {
		version++;
		StringBuffer sql=new StringBuffer("UPDATE sys_user SET PASSWORD=?,version=? WHERE userKey=?");
		Object[] args=new Object[]{password,version,userKey};
		this.update(sql.toString(), args);
		
	}

	@Override
	public void cleanSession(String sessionId,String userKey) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE sys_loginrecord SET loginFlag=1 WHERE userKey=? AND seessionId=? AND loginFlag=0");
		Object[] args =new Object[]{userKey,sessionId};
		update(sql.toString(), args);
	}
}
