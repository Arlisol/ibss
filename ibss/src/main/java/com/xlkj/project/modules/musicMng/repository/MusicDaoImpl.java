package com.xlkj.project.modules.musicMng.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.stereotype.Repository;

import com.xlkj.framework.persistence.jdbc.support.BaseDao;
import com.xlkj.framework.utils.DbUtils;
import com.xlkj.project.domain.Music;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                       
 * Comments: 音乐管理模块数据持久层实现类                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅  
 * Create Date：   2018-6-25 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

@Repository
public class MusicDaoImpl extends BaseDao implements IMusicDao {
	
	//日志记录
	Logger logger = LoggerFactory.getLogger(MusicDaoImpl.class);
	
	/**
	* @Description		音乐分页查询
	* @param pageable	Pageable实例	
	* @param music	Music实例
	* @return findWithPage	Page<Music>实例
	*/
	@Override
	public Page<Music> findWithPage(Pageable pageable, Music music) {
		// 初始化查询条件
		String musicName = music.getMusicName();
		String singer = music.getSinger();
		String musicStyle = music.getMusicStyle();
		String language = music.getLanguage();
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT musicKey,musicName,singer,album,musicStyle,language ");
		sql.append(" FROM per_music ");
		sql.append(" WHERE deleteFlag=0 ");
		//判断查询条件
		if (musicName != null && !"".equals(musicName)) {
			sql.append(" AND musicName LIKE '"+DbUtils.getFullImplict(musicName)+"' ");
		}
		if (singer != null && !"".equals(singer)) {
			sql.append(" AND singer LIKE '"+DbUtils.getFullImplict(singer)+"' ");
		}
		if (musicStyle != null && !"".equals(musicStyle)) {
			sql.append(" AND musicStyle='"+musicStyle+"' ");
		}
		if (language != null && !"".equals(language)) {
			sql.append(" AND language='"+language+"' ");
		}
		sql.append(" ORDER BY issueDate DESC ");
		//返回一个查询分页对象集合
		return queryForPage(sql.toString(), pageable,new MusicListRowMapper(), null);
	}

	/*创建一个实现 Music 序列化接口的实现类 MusicListRowMapper*/ 
	public class MusicListRowMapper implements ParameterizedRowMapper<Music> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	music	Music实例
		*/
		@Override
		public Music mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Music 对象
			Music music = new Music();
			music.setMusicKey(rs.getString("musicKey"));
			music.setMusicName(rs.getString("musicName"));
			music.setSinger(rs.getString("singer"));
			music.setAlbum(rs.getString("album"));
			music.setMusicStyle(rs.getString("musicStyle"));
			music.setLanguage(rs.getString("language"));
			return music;
		}
	}
	
	/**
	* @Description		添加操作
	* @param music	Music实例	
	* @param user	UserInfo实例
	* @return boolean
	*/
	@Override
	public boolean add(Music music, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" INSERT INTO per_music ");
		sql.append(" (musicKey,musicName,singer,album,musicStyle,minute,second,issueDate,language,doWords,writeMusic,remark,createrKey,createDate,deleteFlag) ");
		sql.append(" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {DbUtils.createId(),music.getMusicName(),music.getSinger(),music.getAlbum(),music.getMusicStyle(),music.getMinute(),music.getSecond(),
				music.getIssueDate(),music.getLanguage(),music.getDoWords(),music.getWriteMusic(),music.getRemark(),user.getUserKey(),DbUtils.getTime(),0};		
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		获取音乐原内容
	* @param musicKey	音乐主键	
	* @return getMusic		Music实例
	*/
	@Override
	public Music getMusic(String musicKey) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT musicKey,musicName,singer,album,musicStyle,minute,second,issueDate,language,doWords,writeMusic,remark ");
		sql.append(" FROM per_music ");
		sql.append(" WHERE deleteFlag=0 AND musicKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {musicKey};
		return queryForObject(sql.toString(), args, new GetMusicRowMapper());
	}

	/*创建一个实现 Music 序列化接口的实现类 GetMusicRowMapper*/
	public class GetMusicRowMapper implements ParameterizedRowMapper<Music> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	music	Music实例
		*/
		@Override
		public Music mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Music 对象
			Music music = new Music();
			music.setMusicKey(rs.getString("musicKey"));
			music.setMusicName(rs.getString("musicName"));
			music.setSinger(rs.getString("singer"));
			music.setAlbum(rs.getString("album"));
			music.setMusicStyle(rs.getString("musicStyle"));
			music.setMinute(rs.getString("minute"));
			music.setSecond(rs.getString("second"));
			music.setIssueDate(rs.getString("issueDate"));
			music.setLanguage(rs.getString("language"));
			music.setDoWords(rs.getString("doWords"));
			music.setWriteMusic(rs.getString("writeMusic"));
			music.setRemark(rs.getString("remark"));
			return music;
		}
	}
	
	/**
	* @Description		修改操作
	* @param music	Music实例
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean modify(Music music, UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE per_music SET ");
		sql.append(" musicName=?,singer=?,album=?,musicStyle=?,minute=?,second=?,issueDate=?,language=?,doWords=?,writeMusic=?,remark=?,modifierKey=?,modifyDate=? ");
		sql.append(" WHERE musicKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {music.getMusicName(),music.getSinger(),music.getAlbum(),music.getMusicStyle(),music.getMinute(),music.getSecond(),
				music.getIssueDate(),music.getLanguage(),music.getDoWords(),music.getWriteMusic(),music.getRemark(),user.getUserKey(),DbUtils.getTime(),music.getMusicKey()};
		return update(sql.toString(), args)>0 ? true : false;
	}
	
	/**
	* @Description		获取音乐详细
	* @param musicKey	音乐主键
	* @return detail		Music实例
	*/
	@Override
	public Music detail(String musicKey) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT musicKey,musicName,singer,album,musicStyle,minute,second,issueDate,language,doWords,writeMusic,remark ");
		sql.append(" FROM per_music ");
		sql.append(" WHERE deleteFlag=0 AND musicKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {musicKey};
		return queryForObject(sql.toString(), args, new DetailRowMapper());
	}

	/*创建一个实现 Music 序列化接口的实现类 DetailRowMapper*/
	public class DetailRowMapper implements ParameterizedRowMapper<Music> {
		
		/**
		* @Description		SQL结果集序列化封装至bean
		* @param rs	ResultSet实例
		* @param rowNum	int值
		* @throws SQLException	数据库异常
		* @return	music	Music实例
		*/
		@Override
		public Music mapRow(ResultSet rs, int rowNum) throws SQLException {
			// 实例化一个 Music 对象
			Music music = new Music();
			music.setMusicKey(rs.getString("musicKey"));
			music.setMusicName(rs.getString("musicName"));
			music.setSinger(rs.getString("singer"));
			music.setAlbum(rs.getString("album"));
			music.setMusicStyle(rs.getString("musicStyle"));
			music.setMinute(rs.getString("minute"));
			music.setSecond(rs.getString("second"));
			music.setIssueDate(rs.getString("issueDate"));
			music.setLanguage(rs.getString("language"));
			music.setDoWords(rs.getString("doWords"));
			music.setWriteMusic(rs.getString("writeMusic"));
			music.setRemark(DbUtils.ToBr(rs.getString("remark"),101));
			return music;
		}
	}
	
	/**
	* @Description		删除操作
	* @param musicKey	音乐主键
	* @param user	UserInfo对象	
	* @return boolean
	*/
	@Override
	public boolean delete(String musicKey,UserInfo user) {
		//实例化一个 StringBuffer 对象
		StringBuffer sql = new StringBuffer();
		sql.append(" UPDATE per_music SET ");
		sql.append(" modifierKey=?,modifyDate=?,deleteFlag=? ");
		sql.append(" WHERE musicKey=? ");
		//初始化一个包含条件参数的Object对象
		Object[] args = new Object[] {user.getUserKey(),DbUtils.getTime(),1,musicKey};
		return update(sql.toString(), args)>0 ? true : false;
	}
}
