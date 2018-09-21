package com.xlkj.framework.utils;  
  
import java.util.Date;  
import java.util.Properties;  
import javax.mail.Authenticator;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.PasswordAuthentication;  
import javax.mail.Session;  
import javax.mail.Transport;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeMessage;  
/** 
 * CopyRright (c)2015-2016:   校联科技有限公司 
 * Project:  itoi                                    
 * Module ID: 
 * Comments:   自动发送邮件工具类                                   
 * JDK version used:      JDK1.7                              
 * Author：       曹尧                
 * Create Date：  2015-11-17
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */
public class SendEmail {  
      
    public static final String HOST = "smtp.ym.163.com";  
    public static final String PROTOCOL = "smtp";     
    public static final int PORT = 25;  
    public static final String FROM = "service@itedu-g.com";//发件人的email  
    public static final String PWD = "service";//发件人密码  
      
      
    private static Session getSession() {  
        Properties props = new Properties();  
        props.put("mail.smtp.host", HOST);//设置服务器地址  
        props.put("mail.store.protocol" , PROTOCOL);//设置协议  
        props.put("mail.smtp.port", PORT);//设置端口  
        props.put("mail.smtp.auth" , true);  
          
        Authenticator authenticator = new Authenticator() {  
  
            @Override  
            protected PasswordAuthentication getPasswordAuthentication() {  
                return new PasswordAuthentication(FROM, PWD);  
            }  
              
        };  
        Session session = Session.getDefaultInstance(props , authenticator);  
          
        return session;  
    }  
      
    public static void send(String toEmail , String content) {  
        Session session = getSession();  
        try {   
            Message msg = new MimeMessage(session);  
            msg.setFrom(new InternetAddress(FROM));  
            InternetAddress[] address = {new InternetAddress(toEmail)};  
            msg.setRecipients(Message.RecipientType.TO, address);  
            msg.setSubject("密码找回邮件通知");  
            msg.setSentDate(new Date());  
            msg.setContent(content , "text/html;charset=utf-8");  
            //Send the message  
            Transport.send(msg);  
        }  
        catch (MessagingException mex) {  
            mex.printStackTrace();  
        }  
    }  
  
}  