package com.xlkj.project.modules.passwdMana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.modules.passwdMana.repository.IPasswdManaDao;


@Service
@Transactional
public class PasswdManaServiceImpl implements IPasswdManaService{
	@Autowired
	private  IPasswdManaDao  passwdManaDao;
	@Override
	public void alterPasswd(String userKey, String password,int version) {
		passwdManaDao.alterPasswd(userKey,password,version);
		
	}

	@Override
	public void cleanSession(String sessionId,String userKey) {
		passwdManaDao.cleanSession(sessionId,userKey);
	}
}
