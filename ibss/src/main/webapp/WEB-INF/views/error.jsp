<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setStatus(HttpServletResponse.SC_OK);%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<!-- 优先使用 IE 最新版本和 Chrome -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<!-- 启用360浏览器的极速模式(webkit) -->
		<meta name="renderer" content="webkit">
		<!-- 避免IE使用兼容模式 -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>500</title>
		<!-- ========== Css Files ========== -->
		<link href="${ctx}/static/css/bootstrap.css" rel="stylesheet" />
		<link href="${ctx}/static/css/font-awesome.min.css" rel="stylesheet" />
		<link href="${ctx}/static/css/style.css" rel="stylesheet" />
		<style type="text/css">
			body {
				background: #F5F5F5;
			}
		</style>
	</head>
	<body>
		<div class="error-pages">
			<img src="${ctx}/static/img/500.png" alt="500" class="icon" width="400" height="260"/>
			<h1>内部服务器错误</h1>
			<h4>有些事情严重错误 请稍后再试。</h4>
			<div class="bottom-links">
				<a href="javascript:history.back(-1);" class="btn btn-default">返回</a>
				<a href="${ctx}" class="btn btn-light">首页</a>
			</div>
		</div>
	</body>
</html>