package com.xlkj.project.modules.musicMng.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.xlkj.project.domain.Music;
import com.xlkj.project.domain.UserInfo;

/** 
 * Project:  ibss                                      
 * Comments: 音乐管理模块数据持久层接口                           
 * JDK version used:      JDK1.7                              
 * Author：赵志沅   
 * Create Date：   2018-6-25 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public interface IMusicDao {
	//获取音乐信息分页对象
	public Page<Music> findWithPage(Pageable pageable, Music music);
	//添加操作
	public boolean add(Music music, UserInfo user);
	//获取音乐原内容
	public Music getMusic(String musicKey);
	//修改操作
	public boolean modify(Music music,UserInfo user);
	//删除操作
	public boolean delete(String musicKey,UserInfo user);
	//获取音乐详细
	public Music detail(String musicKey);
}
