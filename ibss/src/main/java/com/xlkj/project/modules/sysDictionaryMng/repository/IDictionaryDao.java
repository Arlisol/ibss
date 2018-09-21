package com.xlkj.project.modules.sysDictionaryMng.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.Dictionary;



public interface IDictionaryDao {
	public Page<Dictionary> findWithPage(Pageable pageable, Dictionary dict);
	
	public List<Dictionary> findWithList(Dictionary dict);
	
	public void add(Dictionary dict);
	
	public void modify(Dictionary dict);
	
	public void delete(String id);
	
	public Dictionary getDictionaryById(String id);

	public List<Dictionary> findClist(String cname);

}
