package com.xlkj.project.modules.sysDictionaryMng.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xlkj.project.domain.Dictionary;
import com.xlkj.project.modules.sysDictionaryMng.repository.IDictionaryDao;



@Service
@Transactional
public class DictionaryServiceImpl implements IDictionaryService {
	
	@Autowired
	private IDictionaryDao dictDao;

	@Override
	public Page<Dictionary> findWithPage(Pageable pageable, Dictionary dict) {
		return dictDao.findWithPage(pageable, dict);
	}

	@Override
	public List<Dictionary> findWithList(Dictionary dict) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modify(Dictionary dict) {
		// TODO Auto-generated method stub
		dictDao.modify(dict);
	}

	@Override
	public void add(Dictionary dict) {
		// TODO Auto-generated method stub
		dictDao.add(dict);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		dictDao.delete(id);
	}

	@Override
	public Dictionary detail(String id) {
		// TODO Auto-generated method stub
		return dictDao.getDictionaryById(id);
	}

	@Override
	public List<Dictionary> findClist(String cname) {
		return dictDao.findClist(cname);
	}




}
