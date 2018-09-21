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
								<ul class="panel-tools">
									<li>
										<a class="icon expand-tool"><i class="fa fa-expand"></i></a>
									</li>
								</ul>
							</div>
							<div class="panel-body">
								<form id="textForm"class="form-horizontal">
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">文本显示</label>
										<div class="col-lg-3 col-xs-3 ">
											<p class="form-control-static">email@example.com</p>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">短文本</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" class="form-control" id="input002">
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">只读文本</label>
										<div class="col-lg-3 col-xs-3">
											<input class="form-control" type="text" placeholder="Readonly input here…" readonly>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">下拉选择</label>
										<div class="col-lg-3 col-xs-3">
											<select class="selectpicker" name="dfd">
												<option>Themeforest</option>
												<option>Codecanyon</option>
												<option>Photodune</option>
												<option>Graphicriver</option>
												<option>Activeden</option>
											</select>
										</div>										
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">日期选择</label>
										<div class="col-lg-3 col-xs-3">
											<div class="input-prepend input-group">
												<span class="add-on input-group-addon"><i class="fa fa-calendar"></i></span>
												<input type="text" id="date-time-picker" class="form-control" value="03/18/2015" />
											</div>										
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">日期范围</label>
										<div class="col-lg-3 col-xs-3">
											<div class="input-prepend input-group">
												<span class="add-on input-group-addon"><i class="fa fa-calendar"></i></span>
												<input type="text" id="date-range-picker" class="form-control" value="03/18/2015 - 03/23/2015" />
											</div>										
										</div>
									</div>									
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">时间范围</label>
										<div class="col-lg-4 col-xs-4">
											<div class="input-prepend input-group">
												<span class="add-on input-group-addon"><i class="fa fa-calendar"></i></span>
												<input type="text" id="date-range-and-time-picker" class="form-control" value="08/01/2015 1:00 PM - 08/01/2015 1:30 PM" class="span4" />
											</div>										
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">单选框</label>
										<div class="col-lg-8 col-xs-8">
											<div class="radio radio-inline">
												<input type="radio" id="inlineRadio1" name="radioInline" value="option1">
												<label for="inlineRadio1">内联一</label>
											</div>						
											<div class="radio radio-inline">
												<input type="radio" id="inlineRadio2" name="radioInline" value="option1">
												<label for="inlineRadio2">内联二</label>
											</div>		
											<div class="radio radio-inline">
												<input type="radio" id="inlineRadio3" name="radioInline" value="option1">
												<label for="inlineRadio3">内联三</label>
											</div>		
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">多选框</label>
										<div class="col-lg-8 col-xs-8">
											<div class="checkbox checkbox-inline">
												<input type="checkbox" id="inlineCheckbox1" name="selected" value="option1">
												<label for="inlineCheckbox1">内联1</label>
											</div>									
											<div class="checkbox checkbox-inline">
												<input type="checkbox" id="inlineCheckbox3" name="selected" value="option1">
												<label for="inlineCheckbox3">内联2</label>
											</div>									
											<div class="checkbox checkbox-inline">
												<input type="checkbox" id="inlineCheckbox2" name="selected" value="option1">
												<label for="inlineCheckbox2">内联3</label>
											</div>		
											<div class="checkbox checkbox-inline">
												<input type="checkbox" id="controlAll" onclick="selectAll()">
												<label for="controlAll">全选</label>
											</div>																																										
										</div>
									</div>	
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">长文本</label>
										<div class="col-lg-8 col-xs-8">
											<input type="text" class="form-control" id="input0044">									
										</div>
									</div>										
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">多行文本</label>
										<div class="col-lg-8 col-xs-8">
											<textarea class="form-control" rows="4" id="textarea1" placeholder="Type your message..."></textarea>
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">文本编辑器</label>
										<div class="col-lg-8 col-xs-8">
											<textarea class="form-control" id="demo" style="display: none"></textarea>
										</div>
									</div>
									<p>
									</p>
									<div class="form-group">
										<label class="col-lg-4 col-xs-4 control-label form-label"></label>
										<div class="col-lg-1 col-xs-1">
											<button id="publish"class="btn btn-default "><i class="fa fa-check"></i>确定</button>
										</div>
										<label class="col-lg-1 col-xs-1 control-label form-label"></label>
										<div class="col-lg-4 col-xs-4 ">
										<a href="javascript:;" class="btn btn-light"><i class="fa fa-mail-forward"></i>返回</a>
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
		<!-- Date Picker 日期选择器 -->
		<script type="text/javascript">
			$(document).ready(function() {
				$('#date-picker').daterangepicker({ 
					singleDatePicker : true,
					format : 'YYYY-MM-DD'
				});
			});
		</script>
		<!-- DateTime Picker 日期时间选择器 -->
		<script type="text/javascript">
			$(document).ready(function() {
				$('#datetime-picker').daterangepicker({ 
					singleDatePicker : true,
					timePicker : true,
					format : 'YYYY-MM-DD HH:mm',
					timePickerIncrement : 1,
					timePicker12Hour : false,
				});
			});
		</script>
		<!-- 全选或反选复选框 -->
		<script type="text/javascript">
		function selectAll(){
			var checklist = document.getElementsByName ("selected");
			if(document.getElementById("controlAll").checked){
				for(var i=0;i<checklist.length;i++){
					checklist[i].checked = 1;
				} 
			 }else{
				for(var j=0;j<checklist.length;j++){
			     checklist[j].checked = 0;
				}
			}
		}
		<!-- 富文本编辑器 -->
			var layedit;
			var layeditIndex;
			layui.use('layedit', function(){
				layedit = layui.layedit;
				//图片插入
				layedit.set({
					uploadImage: {
						url: '${ctx}/uploadImage',
						type: 'post'
					}
				});
				layeditIndex = layedit.build('demo');
			});
			/* //同步超文本编辑内容至textarea	
			layedit.sync(layeditIndex); */
			<!-- 输入格式校验 -->
			$(document).ready(function() {
				// 手机号码格式验证
				jQuery.validator.addMethod("phone", function(value, element) {
					var length = value.length;
					return this.optional(element) || (length == 11 && /^(((1[0-9][0-9]{1})|(15[0-9]{1}))+\d{8})$/.test(value));
				}, "手机号码格式错误");
				//邮箱格式验证
				jQuery.validator.addMethod("email", function(value, element) {
					return this.optional(element) || /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value);
				}, "邮箱格式错误");
				//验证汉字
				jQuery.validator.addMethod("chinese", function(value, element) {  
					var tel = /^[\u4e00-\u9fa5]+$/;  
					return this.optional(element) || (tel.test(value));  
				}, "请输入汉字");
				// 选择下拉框格式验证
				jQuery.validator.addMethod("select", function(value, element) {
					var length = value.length;
					return this.optional(element) || (length != 4);
				}, "必选字段");
				// 日期格式验证
				jQuery.validator.addMethod("date", function(value, element) {
					return this.optional(element) || /^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/.test(value);
				}, "日期格式错误");
				//日期格式验证
				jQuery.validator.addMethod("datetime", function(value, element) {
					return this.optional(element) || /^\d{4}[-]([0][1-9]|(1[0-2]))[-]([1-9]|([012]\d)|(3[01]))([ \t\n\x0B\f\r])(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))$/.test(value);
				}, "日期时间格式错误");
				return $("#customerForm").validate({
					rules : {
						customerName : {
							required : true,
							rangelength : [ 2, 50 ]
						},
						nowGrade : {
							required : true,
							select : true
						},
						parentName : {
							required : true,
							rangelength : [ 2, 50 ]
						},
						school : {
							required : true,
							rangelength : [ 2, 200 ]
						},
						relationship : {
							required : true,
							rangelength : [ 2, 50 ],
							chinese : true
						},
						phoneNumber : {
							required : true,
							phone : true
						},
						email : {
							required : true,
							rangelength : [ 7, 50 ],
							email : true
						},
						sourceType : {
							required : true,
							select : true
						},
						provinceKey : {
							required : true,
							select : true
						},
						cityKey : {
							required : true,
							select : true
						},
						areaKey : {
							required : true,
							select : true
						},
						village : {
							required : true,
							rangelength : [ 2, 50 ]
						},
						address : {
							required : true,
							rangelength : [ 2, 200 ]
						},
						registerDate : {
							required : true,
							date : true
						}
					}
				});
			});
		</script>