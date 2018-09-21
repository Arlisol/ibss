package com.xlkj.framework.persistence.jdbc.support;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.util.Assert;


public class BaseDao{

	Logger logger =  LoggerFactory.getLogger(BaseDao.class);
	/**
	 * JDBC注入
	 */
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	@Autowired
	protected LobHandler lobHandler;
	
	/**
	 * 更新操作
	 */
	public int update(String sql, Object[] args) {

		return jdbcTemplate.update(sql, args);
	}

	/**
	 * 统计记录数
	 */
	public int queryForInt(String sql, Object[] args) {
		return jdbcTemplate.queryForInt(sql, args);
	}

	/**
	 * 查询单个对象
	 */
	public <T> T queryForObject(String sql, Object[] args, ParameterizedRowMapper<T> rowMapper) {
		checkSqlSpecification(sql);
		try {
			return jdbcTemplate.queryForObject(sql, args,rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	/**
	 * 查询对象集合
	 */	
	public <T> List<T> query(String sql, Object[] args, ParameterizedRowMapper<T> rowMapper) {
		checkSqlSpecification(sql);
		return jdbcTemplate.query(sql, args,rowMapper);
	}
	
	/**
	 * 查询对象集合
	 */	
	public <T> List<T> query(String sql,ParameterizedRowMapper<T> rowMapper) {
		checkSqlSpecification(sql);
		return query(sql, null,rowMapper);
	}
	
	/**
	 * 
	 * @param sql
	 * @param args
	 * @param clazz
	 * @return
	 */
	public <T> List<T> query(String sql, Object[] args ,Class<T> clazz) {
		checkSqlSpecification(sql);
		return jdbcTemplate.query(sql,
				BeanPropertyRowMapper.newInstance(clazz), args);
	}

	/**
	 * 
	 * @param sql
	 * @param clazz
	 * @return
	 */
	public <T> List<T> query(String sql, Class<T> clazz) {
		checkSqlSpecification(sql);
		return query(sql,null,clazz);
	}

	/**
	 * 查询分页对象集合
	 */
	public <T> Page<T> queryForPage(String sql, String sqlCount, Pageable pageable, ParameterizedRowMapper<T> rowMapper ,Object[] args) {
		
		checkSqlSpecification(sql);
		//获得总记录数
		int maxCount = queryForInt(sqlCount, args);
		//构建分页sql
		String buildSql = buildSql(sql,pageable);
		//查询出来真实分页数据
		List<T> list = jdbcTemplate.query(buildSql, rowMapper, args);
		//将结果组装成Page对象
		Page<T> showPage = new PageImpl<T>(list, pageable, maxCount);
		
		return showPage;
	}
	
	/**
	 * 查询分页对象集合
	 */
	public <T> Page<T> queryForPage(String sql,Pageable pageable, ParameterizedRowMapper<T> rowMapper ,Object[] args) {
		checkSqlSpecification(sql);
		String countSql = buildCountSql(sql);
		//获得总记录数
		int maxCount = queryForInt(countSql, args);
		//构建分页sql
		String buildSql = buildSql(sql,pageable);
		//查询出来真实分页数据
		List<T> list = jdbcTemplate.query(buildSql, rowMapper, args);
		//将结果组装成Page对象
		Page<T> showPage = new PageImpl<T>(list, pageable, maxCount);
		
		return showPage;
	}
	

	/**
	 * sql:原始的业务sql
	 * sqlCount：用于统计多少条数据得sql
	 * Pageable 分页包装类对象
	 */
	@SuppressWarnings("deprecation")
	public <T> Page<T> queryForPage(String sql, String sqlCount, Pageable pageable,Class<T> clazz,
			Object... args) {
		checkSqlSpecification(sql);
		
		//查询记录总数				
		int count = jdbcTemplate.queryForInt(sqlCount,args);
		//构建分页sql
		String buildSql = buildSql(sql,pageable);
        //分页查询
		List<T> list = jdbcTemplate.query(buildSql,
				BeanPropertyRowMapper.newInstance(clazz), args);
		//将结果组装成Page对象
		Page<T> showPage = new PageImpl<T>(list, pageable, count);

		return showPage;
	}
	
	
	/**
	 * sql:原始的业务sql
	 * Pageable 分页包装类对象
	 */
	@SuppressWarnings("deprecation")
	public <T> Page<T> queryForPage(String sql, Pageable pageable,Class<T> clazz,
			Object... args) {
		checkSqlSpecification(sql);
		
		String countSql = buildCountSql(sql);
		
		//查询记录总数				
		int count = jdbcTemplate.queryForInt(countSql,args);
		//构建分页sql
		String buildSql = buildSql(sql,pageable);
        //分页查询
		List<T> list = jdbcTemplate.query(buildSql,
				BeanPropertyRowMapper.newInstance(clazz), args);
		//将结果组装成Page对象
		Page<T> showPage = new PageImpl<T>(list, pageable, count);

		return showPage;
	}
	 
	/**
	 * 计算分页的时候应该从哪条数据开始查询
     * 
     * 注意：pageNumer从0开始，即第一个pageNumber=0
     * 
     * 根据pageNumber和pagesize计算出数据中哪一条开始查询：针对msyql
     * 
     */
	private int computeSearchStartPoint(int pageNumber,int pageSize){
		int startPoint = 0;
		
		pageNumber = (pageNumber <0) ? 1 : pageNumber;
		pageSize = (pageSize < 1) ? 1 : pageSize;
		
		startPoint = pageNumber * pageSize;
		
		return startPoint;
		
	}
	
	/**
	 * mysql分页语法拼接-不同的数据库，分页的方式不同，目前只针对Mysql
	 * 
	 * 组装成分页的sql语句
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	private String mySql2pageable(int pageNumber,int pageSize){
		
	 String sql = " limit " +computeSearchStartPoint(pageNumber,pageSize)+","+pageSize;
	 
	 return sql;
	}
	
	/**
	 * 针对sqlserver2005以上数据库的分页算法
	 * @param sql
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	private String sqlserver2005ForPageable(String sql,int pageNumber,int pageSize){
		int start = computeSearchStartPoint(pageNumber,pageSize)+1;
		int end =start+pageSize-1;
		String buildsql = "SELECT * FROM (" +sql+")as mainSql WHERE Row >="+start+" and Row <="+end;
//		logger.debug("最终的SQL为：{}",buildsql);
		return buildsql;
	}
	
	/**
	 * 根据不同的数据构建不同的分页sql
	 * @return 
	 * 
	 * TODO:根据不同的方言为添加不同的sql构建策略
	 */
	private String buildSql(String sql,Pageable pageable){
		
		
		String pageableSql = sql;
		//每次都需要链接一下数据库、影响效率
//		String currentDialect = Hibernates.getDialect(jdbcTemplate.getDataSource());
		//获取数据库方言，针对多个数据源可能存在问题
//		String currentDialect = Hibernates.CURRENT_DIALECT;
		//TODO:目前仅针对msyql
//		if( MySQL5InnoDBDialect.class.getName().equals(currentDialect)){
//			
//			pageableSql+= mySql2pageable(pageable.getPageNumber(),pageable.getPageSize());
//		}
//		pageableSql = sqlserver2005ForPageable(sql,pageable.getPageNumber(),pageable.getPageSize());
		
		pageableSql = mysqlForPageable(sql, pageable.getPageNumber(), pageable.getPageSize());
		return pageableSql;
	}
	
	/**
	 * 针对mysql数据库的分页算法
	 * @param sql
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	private String mysqlForPageable(String sql,int pageNumber,int pageSize){
		int start = computeSearchStartPoint(pageNumber,pageSize);
//		int end =start+pageSize-1;
		String buildsql = "SELECT * FROM (" +sql+")as mainSql limit " + start + ", " + pageSize;
//		logger.debug("最终的SQL为：{}",buildsql);
		return buildsql;
	}
	
	/**
	 * 根据sql构建countsql
	 * @param sql
	 * @return
	 */
	private String buildCountSql(String sql){
		
		//return buildSqlServer2005CountSql(sql);
		
		return buildMySqlCountSql(sql);
		
	}
	
	/**
	 * mysql 分页sql的封装
	 * @param sql
	 * @return
	 */
	private String buildMySqlCountSql(String sql){
		return "select Count(1) from ("+sql+")as countSql ";
	}
	
	/**
	 * sqlserver 2005 分页sql的封装
	 * @param sql
	 * @return
	 */
	private String buildSqlServer2005CountSql(String sql){
		return "select Count(1) from ("+sql+")as countSql ";
	}
	
	/**
	 * 检查sql规范
	 * @param sql
	 */
	private void checkSqlSpecification(String sql){
		Assert.hasText(sql, "sql规范检查：sql 必须不能为空");  
//		Assert.doesNotContain(sql,"*","sql规范检查：sql中不能包含\"*\"");
	}
}
