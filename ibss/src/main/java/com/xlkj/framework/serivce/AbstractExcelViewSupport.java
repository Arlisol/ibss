package com.xlkj.framework.serivce;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public abstract class AbstractExcelViewSupport extends AbstractExcelView {
	

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String fileName = java.net.URLEncoder.encode(getFileName(), "UTF-8");  
		
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);

		HSSFSheet sheet = buildExcelHeader(model,workbook);
		
		buildExcelContent (model,sheet);
	}
	
	/**
	 * 构建excel内容
	 * @param model
	 * @param sheet
	 */
	
	protected abstract void buildExcelContent(Map<String, Object > model, HSSFSheet sheet);

	/**
	 * 构建excel的表头
	 * @param model
	 * @param workbook
	 * @return
	 */
	protected abstract HSSFSheet buildExcelHeader(Map<String, Object> model,
			HSSFWorkbook workbook);

	/**设置导出的文件名*/
	protected abstract String getFileName();
	
	
	
}
