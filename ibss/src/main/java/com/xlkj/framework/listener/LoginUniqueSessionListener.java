package com.xlkj.framework.listener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.springframework.jdbc.core.JdbcTemplate;
import com.xlkj.framework.spring.SpringContextHolder;

public class LoginUniqueSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		HttpSession session = arg0.getSession();
		String sessionId = session.getId();
		String userKey = (String) session.getAttribute("userKey");
		JdbcTemplate jdbcTemplate = SpringContextHolder.getBean("jdbcTemplate");
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE sys_loginrecord SET loginFlag=1 WHERE userKey=? AND seessionId=? AND loginFlag=0");
		Object[] args =new Object[]{userKey,sessionId};
		jdbcTemplate.update(sql.toString(), args);
	}
}
