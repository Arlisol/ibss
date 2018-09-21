package com.xlkj.project.modules.sysDictionaryMng.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.Dictionary;





public interface IDictionaryService {
	//获取分页对象
	public Page<Dictionary> findWithPage(Pageable pageable, Dictionary dict);
	
	//获取list对象
	public List<Dictionary> findWithList(Dictionary dict);
	
	//修改
	public void modify(Dictionary dict);
	
	//添加
	public void add(Dictionary dict);
	
	//删除
	public void delete(String id);
	
	//详情
	public Dictionary detail(String id);
	
	public List<Dictionary> findClist(String cname) ;
	
	

}
