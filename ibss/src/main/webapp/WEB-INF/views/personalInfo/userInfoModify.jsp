<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>

	<head>
		<title>修改资料</title>
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
				<h1 class="title">个人资料</h1>
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
								<form class="form-horizontal" id="userInfoModifyForm" action="${ctx}/personalInfo/submitModify/${userKey}" method="post" >								 
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">电话：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="phone" class="form-control" name="phone" value="${user.phone}"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">电子邮箱：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="email" class="form-control" name="email" value="${user.email}"/>
										</div>	
									</div>																																																
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">备注：</label>
										<div class="col-lg-8 col-xs-8">
											<textarea class="form-control" rows="4" id="remark" name="remark">${user.remark}</textarea>
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
		return $("#userInfoModifyForm").validate({
			rules : {
				phone : {
					phone : true
				},
				email : {
					email : true
				}
			},
		});
	});
	</script>