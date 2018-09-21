package com.xlkj.framework.utils.dictionary.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;
import com.xlkj.framework.utils.dictionary.domain.Dictionary;

@Repository
public class DictionaryDao extends BaseDao {
	
	/**
	 * 查询所有记录方法
	 */
	public List<Dictionary> getAll(){
		StringBuilder sql = new StringBuilder();
		sql.append(" select GroupCode,GroupLabel,ItemCode,ItemLabel,ItemSequence from sys_dictionary order by ItemSequence ");
		return (List<Dictionary>) query(sql.toString(), null, new DictionaryRowMap());
	}
	
	private class DictionaryRowMap implements ParameterizedRowMapper<Dictionary>{
	    public Dictionary mapRow(ResultSet rs, int arg1) throws SQLException {
	    	Dictionary dictionary=new Dictionary();
	    	dictionary.setFieldId(rs.getString("GroupCode"));
	    	dictionary.setFieldName(rs.getString("GroupLabel"));
	    	dictionary.setCode(rs.getString("ItemCode"));
	    	dictionary.setLabel(rs.getString("ItemLabel"));
	    	dictionary.setSequence(rs.getInt("ItemSequence"));
	        return dictionary;
	    }
	}
	
}
