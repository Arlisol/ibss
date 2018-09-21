package com.xlkj.framework.utils.dictionary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.framework.utils.dictionary.dao.DictionaryDao;
import com.xlkj.framework.utils.dictionary.domain.Dictionary;

//Spring Bean的标识.
@Service
// 默认将类中的所有public函数纳入事务管理.
@Transactional(readOnly = true)
@Lazy(false)
public class DictionaryService {

	private DictionaryDao dictionaryDao;
	
	public DictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}
	
	
	@Autowired
	public void setDictionaryDao(DictionaryDao dictionaryDao) {
		this.dictionaryDao = dictionaryDao;
	}

	/**
	 * 查询所有记录方法
	 */
	public List<Dictionary> getAllDictionary() {
		return dictionaryDao.getAll();
	}
}
