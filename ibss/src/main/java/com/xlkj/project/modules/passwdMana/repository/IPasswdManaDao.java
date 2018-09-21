package com.xlkj.project.modules.passwdMana.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface IPasswdManaDao {

	void alterPasswd(String userKey, String password,int version);

	public void cleanSession(String sessionId,String userKey);
}
