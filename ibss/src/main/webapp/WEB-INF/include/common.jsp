<%--
这里是共通的头文件，每个内容页面必须引入  
其中Jquery以及默认样式相关的文件，内容页面不需要引入

其他第三方类库需要需要采用如下步骤引入：
1. 统一命名
2. 采用tags:wjs或tags.wcss标签引入
说明：统一命名为全局唯一。避免js和css冲突，可以这样命名:zTree.js、zTree.css
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="dict" uri="http://www.summer.org/tags/dict" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
		<meta charset="utf-8">
		<!-- 优先使用 IE 最新版本和 Chrome -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<!-- 启用360浏览器的极速模式(webkit) -->
		<meta name="renderer" content="webkit">
		<!-- 避免IE使用兼容模式 -->
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- 为移动设备添加 viewport -->
		<!--<meta name="viewport" content="initial-scale=1, maximum-scale=3, minimum-scale=1, user-scalable=no">-->
		<meta name="viewport" content="width=device-width, initial-scale=1">

		<!-- ========== Css Files ========== -->
		<!--<link href="${ctx}/static/css/root.css" rel="stylesheet">-->

		<link href="${ctx}/static/css/bootstrap.css" rel="stylesheet" />
		<link href="${ctx}/static/css/font-awesome.min.css" rel="stylesheet" />

		<!--时间插件的css-->
		<link href="${ctx}/static/css/datatables.css" rel="stylesheet" />

		<!--弹窗css样式-->
		<link href="${ctx}/static/js/layer-v3.0.3/layui/css/layui.css" rel="stylesheet" />
		<link href="${ctx}/static/css/style.css" rel="stylesheet" />

		<!-- ================================================
			jQuery Library
		================================================ -->
		<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>

		<!-- ================================================
			Bootstrap Core JavaScript File
		================================================ -->
		<script src="${ctx}/static/js/bootstrap/bootstrap.min.js"></script>

		<!-- ================================================
			Plugin.js - 一些特定的JS代码的插件设置  是一个用来为页面上的元素产生阴影效果的 jQuery 插件。
		================================================ -->
		<script type="text/javascript" src="${ctx}/static/js/plugins.js"></script>

		<!-- ================================================
			layer 弹窗
		================================================ -->
		<script type="text/javascript" src="${ctx}/static/js/layer-v3.0.3/layui/layui.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/layer-v3.0.3/layui/lay/dest/layui.all.js"></script>
		<!-- ================================================
		Bootstrap 日期处理插件 对jQuery，bootstrap框架的引用，以及日期处理用的moment.js,在加上这个插件的js和css文件
		================================================ -->
		<script type="text/javascript" src="${ctx}/static/js/moment/moment.min.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/date-range-picker/daterangepicker.js"></script>
		
		<%--jquery-valildtion --%>
		<script src="${ctx}/static/js/jquery-validation/1.10.0/jquery.validate.min.js"></script>
		<script src="${ctx}/static/js/jquery-validation/1.10.0/jquery.metadata.js"></script>
		<script src="${ctx}/static/js/jquery-validation/1.10.0/messages_bs_zh.js"></script>
		<%-- <script src="${ctx}/static/js/jquery-validation/1.10.0/additional-methods.js"></script> --%>
		<link href="${ctx}/static/js/jquery-validation/1.10.0/validate.css" rel="stylesheet" type="text/css" />
		

