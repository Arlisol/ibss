<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>

	<head>
		<title>用户修改</title>
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
				<h1 class="title">用户修改</h1>
				<ol class="breadcrumb">
					<li class="active">用户管理</li>
					<li>
						<a href="${ctx}/userInfo/list">用户查询</a>
					</li>
					<li class="active">用户修改</li>
				</ol>
			</div>
			<div class="container-padding">
				<div class="row">
					<div class="col-md-12">
						<div class="panel panel-default">
							<div class="panel-title">
								<ul class="panel-tools">
									<li>
										<a class="icon expand-tool"><i class="fa fa-expand"></i></a>
									</li>
								</ul>
							</div>
							<div class="panel-body">
								<form class="form-horizontal" id="userInfoForm" action="${ctx}/userInfo/submitModify/${getUserInfo.userKey}" 
								onsubmit="return submitForm()" method="post" >								 
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">姓名：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="name" class="form-control" name="name" value="${getUserInfo.name}"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">性别：</label>
										<div class="radio radio-inline">
											<input type="radio" id="male" name="gender" value="0" <c:if test="${getUserInfo.gender eq '0'}">checked="checked"</c:if>/>
											<label for="male">男</label>
										</div>						
										<div class="radio radio-inline">
											<input type="radio" id="female" name="gender" value="1" <c:if test="${getUserInfo.gender eq '1'}">checked="checked"</c:if>/>
											<label for="female">女</label>
										</div>	
									</div>	
									<div class="form-group" >
										<label class="col-lg-2 col-xs-2 control-label form-label">身份证号：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="userName" class="form-control" name="userName" value="${getUserInfo.userName}"/>
											<input type="hidden" id="hidUserName" class="form-control" value="${getUserInfo.userName}"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">生日：</label>
										<div class="col-lg-3 col-xs-3 ">
											<div class="input-prepend input-group">
												<span class="add-on input-group-addon"><i class="fa fa-calendar"></i></span>
												<input type="text" id="birth" class="form-control" name="birth" value="${getUserInfo.birth}"/>
											</div>	
										</div>	
									</div>
									<div class="form-group" >
										<label class="col-lg-2 col-xs-2 control-label form-label">电话：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="phone" class="form-control" name="phone" value="${getUserInfo.phone}"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">电子邮箱：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="email" class="form-control" name="email" value="${getUserInfo.email}"/>
										</div>	
									</div>	
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">用户类型：</label>
										<div class="col-lg-3 col-xs-3 ">
											<tags:wselect key="userType" name="userType" value="${getUserInfo.userType}"/>
										</div>
									</div>																																														
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">备注：</label>
										<div class="col-lg-8 col-xs-8">
											<textarea class="form-control" rows="4" id="remark" name="remark" >${getUserInfo.remark}</textarea>
										</div>
									</div>																																																				
									<div class="form-group">
										<label class="col-lg-4 col-xs-4 control-label form-label"></label>
										<div class="col-lg-1 col-xs-1">
											<button class="btn btn-default "type="submit"  ><i class="fa  fa-check"></i>确定</button>
										</div>
										<label class="col-lg-1 col-xs-1 control-label form-label"></label>
										<div class="col-lg-4 col-xs-4 ">
											<a href="javascript:window.history.back(-1) "class="btn btn-light"><i class="fa  fa-mail-forward"></i>返回</a>
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
	<script type="text/javascript">
	$(document).ready(function() {
		$('#birth').daterangepicker({ 
			singleDatePicker : true,
			format : 'YYYY-MM-DD'
		});
	});
	
	<!-- 表单提交校验 -->
	function submitForm(){  
		//采购时间校验
		if(document.getElementById ("birth").value == "" || document.getElementById ("birth").value == null){
			layerOpen("请选择生日！"); 
			return false;
		}
	}

	<!-- 输入格式校验 -->
	$(document).ready(function() {
		//日期格式验证
		jQuery.validator.addMethod("date", function(value, element) {
			return this.optional(element) || /^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/.test(value);
		}, "日期格式错误");
		//验证身份证号
		jQuery.validator.addMethod("idCard", function(value, element) {  
			var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
			return this.optional(element) || (reg.test(value));  
		}, "身份证号格式错误");
		// 手机号码格式验证
		jQuery.validator.addMethod("phone", function(value, element) {
			var length = value.length;
			return this.optional(element) || (length == 11 && /^(((1[0-9][0-9]{1})|(15[0-9]{1}))+\d{8})$/.test(value));
		}, "手机号码格式错误");
		//邮箱格式验证
		jQuery.validator.addMethod("email", function(value, element) {
			return this.optional(element) || /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/.test(value);
		}, "邮箱格式错误");
		return $("#userInfoForm").validate({
			rules : {
				userName: {
			    	required: true,
			    	idCard: true,
			    	remote : {
						url : "${ctx}/userInfo/userNameUnique",
						type : "post",
						dataType : "json",
						data : {
							userName : function() {
								return $("#userName").val();
							},
							hidUserName : function(){
								return $("#hidUserName").val();
							}
						}
					}
			    },
			    name: {
			    	required: true
			    },
			    birth: {
			    	date: true
			    },
			    phone: {
			    	required: true,
			    	phone: true
			    },
			    email: {
			    	required: true,
			    	email: true
			    },
			    userType: {
			    	required: true,
			    }			   
			},
			messages : {
				userName : {
					remote : "该身份证号已存在！"
				}					
			}
		});
	});
	</script>