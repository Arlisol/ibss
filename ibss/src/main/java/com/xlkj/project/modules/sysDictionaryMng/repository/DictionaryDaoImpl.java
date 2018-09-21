package com.xlkj.project.modules.sysDictionaryMng.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;
import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.Dictionary;


/**
 * CopyRright (c)2015-2016: 大连校联科技有限公司 
 * Project: xiaolian
 *  Module ID: Dictionary
 * Comments: 数据字典管理模块的数据持久层 
 * JDK version used: JDK1.7
 *  Author： 曹尧
 *  Create Date：2015-5-20
 *  Modified By： Modified Date: Why & What is modified Version: 1.0
 */

@Repository
public class DictionaryDaoImpl extends BaseDao implements IDictionaryDao {

	public class DictionaryRowMapper implements
			ParameterizedRowMapper<Dictionary> {

		@Override
		public Dictionary mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			Dictionary dict = new Dictionary();
			dict.setDictionaryKey(rs.getString("DictionaryKey"));
			dict.setGroupCode(rs.getString("GroupCode"));
			dict.setGroupLabel(rs.getString("GroupLabel"));
			dict.setItemCode(rs.getString("ItemCode"));
			dict.setItemLabel(rs.getString("ItemLabel"));
			dict.setItemSequence(rs.getString("ItemSequence"));
			dict.setRemark(rs.getString("Remark"));
			return dict;
		}

	}

	@Override
	public Page<Dictionary> findWithPage(Pageable pageable, Dictionary dict) {
		// TODO Auto-generated method stub
		// 查询所有记录
		StringBuffer sql = new StringBuffer();
		String groupLabel = dict.getGroupLabel();
		String groupCode=dict.getGroupCode();
		sql.append(" select * ");
		sql.append(" from sys_dictionary ");
		sql.append(" where 1 = 1 ");
		if (groupLabel != null && !"".equals(groupLabel)) {
			sql.append(" and groupLabel like '"
					+ DbUtils.getFullImplict(groupLabel) + "' ");
		}
		if (groupCode != null && !"".equals(groupCode)) {
			sql.append(" and groupCode like '"
					+ DbUtils.getFullImplict(groupCode) + "' ");
		}
		sql.append(" order by groupCode");
		return queryForPage(sql.toString(), pageable,
				new DictionaryRowMapper(), null);
	}

	@Override
	public List<Dictionary> findClist(String cname) {
		// TODO Auto-generated method stub
		// 查询所有记录
		StringBuffer sql = new StringBuffer();
		sql.append(" select DictionaryKey,groupLabel ");
		sql.append(" from sys_dictionary ");
		if (cname != null && !"".equals(cname)) {
			sql.append(" where groupLabel like '"
					+ DbUtils.getFullImplict(cname) + "' ");
		}
		sql.append(" order by groupCode");
		return query(sql.toString(), null, new DictRowMapper());
	}
	
	public class DictRowMapper implements
	ParameterizedRowMapper<Dictionary> {

@Override
public Dictionary mapRow(ResultSet rs, int rowNum) throws SQLException {
	// TODO Auto-generated method stub
	Dictionary dict = new Dictionary();
	dict.setDictionaryKey(rs.getString("DictionaryKey"));
	dict.setGroupLabel(rs.getString("groupLabel"));
	return dict;
}

}
	@Override
	public List<Dictionary> findWithList(Dictionary dict) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Dictionary dict) {
		// TODO Auto-generated method stub
		String sql = "insert into sys_dictionary values(?,?,?,?,?,?,?)";
		Object[] args = new Object[] { DbUtils.createId(), dict.getGroupCode(),
				dict.getGroupLabel(), dict.getItemCode(), dict.getItemLabel(),
				dict.getItemSequence(), dict.getRemark() };
		update(sql, args);
	}

	@Override
	public void modify(Dictionary dict) {
		// TODO Auto-generated method stub
		String sql = "update sys_dictionary set " + "GroupCode=?,"
				+ "GroupLabel=?," + "ItemCode=?, " + "ItemLabel=?, "
				+ "ItemSequence=?," + "Remark=? " + "where DictionaryKey=?";
		Object[] args = new Object[] { dict.getGroupCode(),
				dict.getGroupLabel(), dict.getItemCode(), dict.getItemLabel(),
				dict.getItemSequence(), dict.getRemark(),
				dict.getDictionaryKey() };
		update(sql, args);

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		String sql = "delete from sys_dictionary where DictionaryKey=?";
		Object[] args = new Object[] { id };
		update(sql, args);

	}

	@Override
	public Dictionary getDictionaryById(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from sys_dictionary where DictionaryKey=?";
		Object[] args = new Object[] { id };
		return queryForObject(sql, args, new DictionaryRowMapper());
	}

}
