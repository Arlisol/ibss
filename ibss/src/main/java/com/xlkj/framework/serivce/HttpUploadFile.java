package com.xlkj.framework.serivce;

import java.io.File;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;

/**
 * CopyRight (c) 2015 : 56Team
 * Project : http-upload-demo
 * Created by 2016/2/14.
 * Module : http upload
 * Comments : upload file to a http server
 * JDK Version : 1.7
 *
 * @author : xfdheyqkf
 * Version : 1.0
 */
@Service
public class HttpUploadFile {
	@Value("${static.source.url}")
	private  String url;
	
	public void post(File file, String dir) throws IOException {
    	FileBody body = null;
        
        if (!Strings.isNullOrEmpty(dir)) {
        	String fileName = dir + "#" + file.getName();
            
            File newFile = new File(fileName);
            file.renameTo(newFile);
            
            file = newFile;
        }
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (file != null) {
            body = new FileBody(file);
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addPart("file", body);
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        try {
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
                System.out.println("Response content length: " + resEntity.getContentLength());
            }
            EntityUtils.consume(resEntity);
        } finally {
            response.close();
            httpClient.close();
        }
    }
}
