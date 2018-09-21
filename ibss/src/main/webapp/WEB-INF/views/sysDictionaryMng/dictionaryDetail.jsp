<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>

	<head>
		<title>数据字典</title>
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
				<h1 class="title">表单元素</h1>
				<ol class="breadcrumb">
					<li>
						<a href="首页.html">仪表板</a>
					</li>
					<li>
						<a >表单</a>
					</li>
					<li class="active">表单元素</li>
				</ol>
			</div>
			<div class="container-padding">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-title">
								文本框
								<a  class="btn btn-light"><i class="fa fa-mail-reply"></i>返回</a>
								<ul class="panel-tools">
									<li>
										<a class="icon expand-tool"><i class="fa fa-expand"></i></a>
									</li>
								</ul>
							</div>
							<div class="panel-body">
								<form class="form-horizontal">
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">短文本显示</label>
										<div class="col-lg-3 col-xs-3 ">
											<p class="form-control-static">email@example.com</p>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">短文本显示</label>
										<div class="col-lg-3 col-xs-3 ">
											<p class="form-control-static">email@example.com</p>
										</div>
									</div>								
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">长文本显示</label>
										<div class="col-lg-8 col-xs-8">
											<p class="form-control-static">12345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912345678912<br>3456789</p>							
										</div>
									</div>										
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
			<!-- 内容结束 -->
			<!--底开始-->
			<%@ include file="/WEB-INF/include/userFooter.jsp"%>
			<!-- 底结束 -->
	</body>
		<!-- Basic Date Range Picker 基本日期范围选择器 -->
		<script type="text/javascript">
			$(document).ready(function() {
				$('#date-range-picker').daterangepicker({format: 'YYYY-MM-DD' }, function(start, end, label) {
					console.log(start.toISOString(), end.toISOString(), label);
				});
			});
		</script>

		<!-- Basic Single Date Picker 基本单日期选择器 -->
		<script type="text/javascript">
			$(document).ready(function() {
				$('#date-picker').daterangepicker({ singleDatePicker: true,format: 'YYYY-MM-DD' }, function(start, end, label) {
					console.log(start.toISOString(), end.toISOString(), label);
				});
			});
		</script>

		<!-- Date Range and Time Picker 日期范围和时间选择器 -->
		<script type="text/javascript">
			$(document).ready(function() {
				$('#date-range-and-time-picker').daterangepicker({
					timePicker: true,
					timePickerIncrement: 30,
					format: 'HH:mm'
				}, function(start, end, label) {
					console.log(start.toISOString(), end.toISOString(), label);
				});
			});
		</script>
		
		<script type="text/javascript">
			//列表复选框js
			layui.use('form', function() {
				var $ = layui.jquery,
					form = layui.form();
	
				//全选
				form.on('checkbox(allChoose)', function(data) {
					var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');
					child.each(function(index, item) {
						item.checked = data.elem.checked;
					});
					form.render('checkbox');
				});
	
			});
		</script>