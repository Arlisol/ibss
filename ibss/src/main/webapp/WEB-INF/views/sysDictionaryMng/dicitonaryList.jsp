<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="dict" uri="http://www.summer.org/tags/dict" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>基本列表</title>
		<link href="${ctx}/static/css/bootstrap.css" rel="stylesheet" />
		<link href="${ctx}/static/css/font-awesome.min.css" rel="stylesheet" />
		<link href="${ctx}/static/css/datatables.css" rel="stylesheet" />
		<link href="${ctx}/static/js/layer-v3.0.3/layui/css/layui.css" rel="stylesheet" />
		<link href="${ctx}/static/css/style.css" rel="stylesheet" />
		<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
		<script src="${ctx}/static/js/bootstrap/bootstrap.min.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/plugins.js"></script>
		<script type="text/javascript" src="${ctx}/static/js/layer-v3.0.3/layui/layui.js"></script>
				<%--jquery-valildtion --%>
		<script src="${ctx}/static/js/jquery-validation/1.10.0/jquery.validate.min.js"></script>
		<script src="${ctx}/static/js/jquery-validation/1.10.0/jquery.metadata.js"></script>
		<script src="${ctx}/static/js/jquery-validation/1.10.0/messages_bs_zh.js"></script>
		<%-- <script src="${ctx}/static/js/jquery-validation/1.10.0/additional-methods.js"></script> --%>
		<link href="${ctx}/static/js/jquery-validation/1.10.0/validate.css" rel="stylesheet" type="text/css" />
	</head>

	<body>
		<!-- loading图片开始-->
		<div class="loading"><img src="${ctx}/static/img/loading.gif" alt="loading-img" /></div>
		<!--  loading图片结束 -->

		<!-- 头部开始 -->
		<%@ include file="/WEB-INF/include/userNavbar.jsp"%>
		<!--头部结束 -->

		<!-- 左侧边栏开始 -->
		<%@ include file="/WEB-INF/include/userLeft.jsp"%>
		<!-- 左侧边栏结束 -->

		<!-- 内容开始 -->
		<div class="content">
			<div class="page-header">
				<h1 class="title">系统管理</h1>
				<ol class="breadcrumb">
					<li>
						<a href="首页.html">数据字典</a>
					</li>
					<li>
						<a >列表</a>
					</li>
					<li class="active">基本列表</li>
				</ol>
			</div>
			<div class="container-padding">
				<div class="row">
					<form class="form-inline" id="searchForm" action="${ctx}/dictionary" method="post">
						<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-title">
										查询条件
									</div>
									<div class="form-group">
										<label for="input002" class="form-label">文本框</label>
										<input type="text" class="form-control" id="input002">
									</div>
									<div class="form-group">
										<label class="form-label">日期选择器</label>
										<!--<div class="controls">-->
										<div class="input-prepend input-group">
											<span class="add-on input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" id="date-picker" class="form-control" value="03/18/2015 " />
										</div>
										<!--</div>-->
									</div>
									<div class="form-group">
										<label class="form-label">日期范围选择器</label>
										<!--<div class="controls">-->
										<div class="input-prepend input-group">
											<span class="add-on input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" id="date-range-picker" class="form-control" value="03/18/2015 - 03/23/2015" />
										</div>
										<!--</div>-->
									</div>
									<div class="form-group">
										<label class="form-label">日期范围和时间选择器</label>
										<!--<div class="controls">-->
										<div class="input-prepend input-group">
											<span class="add-on input-group-addon"><i class="fa fa-calendar"></i></span>
											<input type="text" id="date-range-and-time-picker" class="form-control" value="08/01/2015 1:00 PM - 08/01/2015 1:30 PM" >
										</div>
										<!--</div>-->
									</div>
									<div class="form-group">
										<label class="form-label">下拉框</label>
										<div class="layui-input-inline">
											<select name="interest" lay-filter="aihao">
												<option value=""></option>
												<option value="0">写作</option>
												<option value="1" selected>阅读</option>
												<option value="2">游戏</option>
												<option value="3">音乐</option>
												<option value="4">旅行</option>
											</select>
										</div>
	
									</div>
									<div class="form-group">
											<a  class="btn btn-light"><i class="fa fa-search"></i>查询</a>
									</div>
								</div>
							</div>
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-title">
									查询结果
									<a  class="btn btn-light"><i class="fa fa-mail-reply"></i>返回</a>
									<a href="javascript:layerOpen()" class="btn btn-light"><i class="fa fa-plus"></i>批量分配</a>
									<a  class="btn btn-light" ><i class="fa fa-trash"></i>批量删除</a>
								</div>
								<div class="panel-body table-responsive layui-form">
									<table class="table table-hover">
										<thead>
											<tr>
												<td class="text-center">
													<input type="checkbox"  lay-skin="primary" lay-filter="allChoose">
												</td>
												<th>域名</th>
												<th>域值</th>
												<th>项编码</th>
												<th>项值</th>
												<th>项序号</th>
												<th>备注</th>
												<td> 操作</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${dictList.content}" var="dictionary" varStatus="status">
												<tr>
													<td class="text-center">
														<input type="checkbox" id="${dictionary.groupCode}"lay-filter="filter" lay-skin="primary" value="${dictionary.groupCode}">
													</td>
														<td><a href="${ctx}/dictionary/intoModify">${dictionary.groupCode}</a></td>
														<td>${dictionary.groupLabel}</td>
														<td>${dictionary.itemCode}</td>
														<td>${dictionary.itemLabel}</td>
														<td>${dictionary.itemSequence}</td>
														<td>${dictionary.remark}</td>
													<td>
														<a href="${ctx}/dictionary/intoadd"class="btn btn-default "><i class="fa fa-pencil-square"></i>编辑</a>
														<a href="javascript:;" class="btn btn-danger"><i class="fa fa-trash"></i>删除</a>
														<a href="javascript:layerSelect('${dictionary.groupCode}')" class="btn btn-light"><i class="fa fa-plus"></i>分配</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									<!-- 分页组件开始 -->
									<tags:pagination page="${dictList}"/>
									<!-- 分页组件开始 -->
								</div>
							</div>
						</div>									
					</form>		
				</div>
			</div>
		</div>
			<!-- 内容结束 -->
			
			<!--底开始-->
			<%@ include file="/WEB-INF/include/userFooter.jsp"%>
			<!-- 底结束 -->
	</body>
	<script type="text/javascript">
	<!-- Basic Date Range Picker 基本日期范围选择器 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#date-range-picker').daterangepicker(null, function(start, end, label) {
				console.log(start.toISOString(), end.toISOString(), label);
			});
		});
	<!-- Basic Single Date Picker 基本单日期选择器 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#date-picker').daterangepicker({ singleDatePicker: true }, function(start, end, label) {
				console.log(start.toISOString(), end.toISOString(), label);
			});
		});
	<!-- Date Range and Time Picker 日期范围和时间选择器 -->
	<script type="text/javascript">
		$(document).ready(function() {
			$('#date-range-and-time-picker').daterangepicker({
				timePicker: true,
				timePickerIncrement: 30,
				format: 'MM/DD/YYYY h:mm A'
			}, function(start, end, label) {
				console.log(start.toISOString(), end.toISOString(), label);
			});
		});
		<!-- checkbox全选事件 -->
		var consults = "";
		layui.use('form', function() {
			var $ = layui.jquery,
				form = layui.form();
			//全选
			form.on('checkbox(allChoose)', function(data) {
				var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
				var checklist = "";
				child.each(function(index, item) {
					item.checked = data.elem.checked;
					if(item.checked){
						checklist += item.value + ",";
					}	
				});
				consults = checklist;
				checklist="";
				form.render('checkbox');
			}); 
			form.on('checkbox(filter)', function(data){
				var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
				var checklist = "";
				child.each(function(index, item) {
					if(item.checked){
						checklist += item.value + ",";
					}	
				});
				consults = checklist;
				checklist="";
			});
		}); 
		<!-- 表单弹出层 -->
		function layerOpen() {
			if(consults == ""){
				layer.open({
					type: 1,
					area: ['400px', '200px'],
					btn: ['确认', '关闭'],
					shadeClose: true, //点击遮罩关闭
					content: '<div style="padding:20px;">请先选择客户！</div>'
				});	
			}else{
				//弹出事件
				layer.open({
					type: 1,
					area: ['600px', '300px'],
					btn: ['确认', '关闭'],
					shadeClose: true,
					//表单显示内容
					content: '<div style="padding:20px;">'							
									+'<div class="panel-body">'
										+'<form class="form-horizontal" id="customerForm"action="${ctx}/dictionary/submitAdd" onsubmit="return submitForm();" method="post" >'
											+'<div class="form-group">'
												+'<label class="col-lg-2 col-xs-2 control-label form-label">查询条件：</label>'
												+'<div class="col-lg-4 col-xs-4 ">'
													+'<input type="text" class="form-control" id="customerName" name="customerName" placeholder="请输入姓名关键字"oninput="getName(this.value)">'
													+'<input type="hidden" id="consults" name="consults"  value="'+consults+'">'
												+'</div>'
											+'</div>'
											+'<div id="consult" class="form-group">'
												+'	<c:forEach items="${dictList.content}" var="dictionary" varStatus="status">'
						         						+'<div class="radio radio-inline">'
							        							+'<input type="radio" id="${dictionary.dictionaryKey}" name="sex" value="${dictionary.dictionaryKey}">'
							       			 					+'<label for="${dictionary.dictionaryKey}">${dictionary.groupLabel}</label>'
							        					+'</div>'
							        			+'</c:forEach>'
						         			+'</div>'																																														
										+'</form>'
									+'</div>'
								+'</div>'
					});
				$('.layui-layer-title').html("选择咨询师");
				$('.layui-layer-btn0').click(function(){
					$('#customerForm').submit();
				});
			}
		}
		function layerSelect(consult) {
			//弹出事件
			layer.open({
				type: 1,
				area: ['600px', '300px'],
				btn: ['确认', '关闭'],
				shadeClose: true,
				//表单显示内容
				content: '<div style="padding:20px;">'							
								+'<div class="panel-body">'
									+'<form class="form-horizontal" id="customerForm"action="${ctx}/dictionary/submitAdd" onsubmit="return submitForm();" method="post" >'
										+'<div class="form-group">'
											+'<label class="col-lg-2 col-xs-2 control-label form-label">查询条件：</label>'
											+'<div class="col-lg-4 col-xs-4 ">'
												+'<input type="text" class="form-control" id="customerName" name="customerName" placeholder="请输入姓名关键字"oninput="getName(this.value)">'
												+'<input type="hidden" id="consults" name="consults"  value="'+consult+'">'
											+'</div>'
										+'</div>'
										+'<div id="consult" class="form-group">'
											+'	<c:forEach items="${dictList.content}" var="dictionary" varStatus="status">'
					         						+'<div class="radio radio-inline">'
						        							+'<input type="radio" id="${dictionary.dictionaryKey}" name="sex" value="${dictionary.dictionaryKey}">'
						       			 					+'<label for="${dictionary.dictionaryKey}">${dictionary.groupLabel}</label>'
						        					+'</div>'
						        			+'</c:forEach>'
					         			+'</div>'																																														
									+'</form>'
								+'</div>'
							+'</div>'
				});
			$('.layui-layer-title').html("选择咨询师");
			$('.layui-layer-btn0').click(function(){
				$('#customerForm').submit();
			}); 
		}
		
		<!-- 查询条件二级联动 -->
		function getName(customerName){  
			$.ajax({ 
				type: "post", 
				url: "${ctx}/dictionary/getName?cname="+customerName, 
				contentType: "application/json",
				dataType: "json",
				success: function (data) { 
					//更新联动内容 
					var consult = $("#consult");
					consult.html(""); 
					var options="";
			        for(var i=0;i<data.clist.length;i++){
			        	options +=  "<div class='radio radio-inline'>";
			         	options +=   "<input type='radio' id='"+data.clist[i].dictionaryKey+"' name='sex' value='"+data.clist[i].dictionaryKey+"'>";
			         	options +=   "<label for='"+data.clist[i].dictionaryKey+"'>"+data.clist[i].groupLabel+"</label>";
			         	options +=    "</div>"; 
			       	}  			
			        consult.html(options); 
				}	        
			});	
		}
		
		<!-- 表单弹出层校验 -->
		function submitForm() {
			var subjectList = document.getElementsByName ("sex");
			var checkedCount=0;  
			for(var i=0;i<subjectList.length;i++){  
				if (subjectList[i].checked){  
					checkedCount++;  
				}  
			}
			if(checkedCount==0){
				layer.open({
					type: 1,
					area: ['400px', '200px'],
					btn: ['确认', '关闭'],
					shadeClose: true, //点击遮罩关闭
					content: '<div style="padding:20px;">请选择咨询师！</div>'
				});
				return false;
			}
		}
	</script>