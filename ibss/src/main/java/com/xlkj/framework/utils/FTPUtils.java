package com.xlkj.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.net.URLEncoder;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
/** 
 * CopyRright (c)2013-2014:   大连四校联科技有限公司 
 * Project:  xiaolian                                       
 * Module ID: FTPUtils
 * Comments:                                             
 * JDK version used:      JDK1.7                              
 * Author：        栾喜员                 
 * Create Date：  2013-9-18 
 * Modified By：                                           
 * Modified Date:                                      
 * Why & What is modified      
 * Version: 1.0                       
 */

public class FTPUtils {
	
	//读取配置文件中的系统ip和port
//	private static Properties p = new PropertiesLoader("classpath:/application.development.properties", "classpath:/application.properties")
//	.getProperties();
//	
//	private static String ftpIp = p.getProperty("ftp.ip"); // 服务器ip
//	
//    private static int ftpPort = Integer.parseInt(p.getProperty("ftp.port")); // 服务器端口号
//
//    private static String userName = p.getProperty("ftp.username"); // 登录服务器用户名
//
//    private static String password = p.getProperty("ftp.password"); // 登录服务器密码

//    protected CustomFtpClient ftpClient = null;

//    OutputStream os = null;

//    FileInputStream is = null;
	
    /**
	 * Description: 向FTP服务器上传文件
	 * 
	 * @param path
	 *            FTP服务器保存目录
	 * @param filename
	 *            上传到FTP服务器上的文件名
	 * @param input
	 *            输入流
	 * @return 成功返回true，否则返回false
     * @throws IOException 
     * @throws SocketException 
	 */
    public static boolean uploadFile(String ftpIp, int ftpPort, String userName, String password, String path, String filename, InputStream input) throws SocketException, IOException {
    	boolean success = false;  
        FTPClient ftp = new FTPClient();  
        ftp.connect(ftpIp, ftpPort);// 连接FTP服务器  
        int reply;  
        // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
        ftp.login(userName, password);// 登录  
        // 设置PassiveMode传输  
        ftp.enterLocalPassiveMode();  
        // 设置以二进制流的方式传输  
        ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);  
        ftp.setFileType(FTP.BINARY_FILE_TYPE);  
        reply = ftp.getReplyCode();  
        if (!FTPReply.isPositiveCompletion(reply)) {  
            ftp.disconnect();  
			return success;
        }  
        String filename1 = new String(filename.getBytes("GBK"), "ISO-8859-1");
		String path1 = new String(path.getBytes("GBK"), "ISO-8859-1");
        boolean isChangeWork = ftp.changeWorkingDirectory(path1);  
        if (!isChangeWork) {  
            boolean isMade = ftp.makeDirectory(path1);  
            if (!isMade) {  
                throw new IOException("ftp创建目录失败");  
            }  
            isChangeWork = ftp.changeWorkingDirectory(path1);  
        }  
        ftp.storeFile(filename1, input);  
        ftp.logout();  
        success = true;  
        if (ftp.isConnected()) {  
            try {  
                ftp.disconnect();  
            } catch (IOException ioe) {  
            }  
        }  
        return success;  
	}
    
    /**
	 * Description: 从FTP服务器下载文件
	 * 
	 * @param remotePath
	 *            FTP服务器上的相对路径
	 * @param fileName
	 *            要下载的文件名
	 * @param localPath
	 *            下载后保存到本地的路径
	 * @return
     * @throws IOException 
     * @throws SocketException 
	 */
	public static boolean downloadFile(String ftpIp, int ftpPort, String userName, String password, String path, String fileName, OutputStream outputStream) throws SocketException, IOException {
		boolean success = false;  
        FTPClient ftp = new FTPClient();  
        ftp.connect(ftpIp, ftpPort);// 连接FTP服务器  
        int reply;  
        // 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
        ftp.login(userName, password);// 登录  
        // 设置PassiveMode传输  
        ftp.enterLocalPassiveMode();  
        // 设置以二进制流的方式传输  
        ftp.setFileTransferMode(FTP.STREAM_TRANSFER_MODE);  
        ftp.setFileType(FTP.BINARY_FILE_TYPE);  
        reply = ftp.getReplyCode();  
        if (!FTPReply.isPositiveCompletion(reply)) {  
            ftp.disconnect();  
        }  
        String filename1 = new String(fileName.getBytes("GBK"), "ISO-8859-1");
		String path1 = new String(path.getBytes("GBK"), "ISO-8859-1");
        boolean isChangeWork = ftp.changeWorkingDirectory(path1);  
        if (!isChangeWork) {  
            throw new IOException("ftp目录不存在");  
        }  
        InputStream input = ftp.retrieveFileStream(filename1);  
        int buf = -1;  
        while ((buf = input.read()) != -1) {  
        	outputStream.write(buf);  
        }  
        outputStream.flush();  
        input.close();  
        ftp.logout();  
        if (ftp.isConnected()) {  
            try {  
                ftp.disconnect();  
            } catch (IOException ioe) {  
            }  
        }  
        return success;  
    }  
    
}