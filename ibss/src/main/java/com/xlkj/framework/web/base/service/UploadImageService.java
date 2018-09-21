package com.xlkj.framework.web.base.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xlkj.framework.serivce.HttpUploadFile;

@Service
public class UploadImageService {
	
	@Value("${static.source.web}")
	private String url;
	
	@Autowired
	private  HttpUploadFile httpUploadFile;
	
	public String uploadImage(File uploadFile, String path, String originalFileName) throws IOException {
		
		httpUploadFile.post(uploadFile, path);
		
		return url + "/" + path + "/" + originalFileName;
	}

}
