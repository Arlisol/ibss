package com.xlkj.project.modules.passwdMana.service;

public interface IPasswdManaService {

	void alterPasswd(String userKey, String xiaolianEncrypt,int version);

	public void cleanSession(String sessionId,String userKey);
}
