<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/include/common.jsp"%>

<head>
<title>用户详细</title>
</head>

<body>
	<!-- loading图片开始-->
	<div class="loading">
		<img src="${ctx}/static/img/loading.gif" alt="loading-img" />
	</div>
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
				<h1 class="title">用户详细</h1>
				<ol class="breadcrumb">
					<li class="active">用户管理</li>
					<li class="active">用户详细</li>
				</ol>
			</div>
		<div class="container-padding">
			<div class="row">
				<div class="col-md-12">
					<div class="panel panel-default">
						<div class="panel-title">
						<a href="javascript:window.history.back(-1)" class="btn btn-light"><i class="fa fa-mail-reply"></i>返回</a>
							<ul class="panel-tools">
								<li><a class="icon expand-tool"><i class="fa fa-expand"></i></a>
								</li>
							</ul>
						</div>
						<div class="panel-body">
							<form class="form-horizontal" id="searchForm" action="${ctx}/userInfo/detail/${userInfo.userKey}" method="post">							
								<!-- /widget-header -->
								<div class="form-group">
									<label class="col-lg-2 col-xs-2 control-label form-label">姓名：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${userInfo.name}</p>
									</div>										
									<label class="col-lg-2 col-xs-2 control-label form-label">性别：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${dict:display('gender',userInfo.gender) }</p>										
									</div>											
								</div>
								<div class="form-group">
									<label class="col-lg-2 col-xs-2 control-label form-label">身份证号：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${userInfo.userName}</p>
									</div>										
									<label class="col-lg-2 col-xs-2 control-label form-label">生日：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${userInfo.birth}</p>										
									</div>											
								</div>
								<div class="form-group">
									<label class="col-lg-2 col-xs-2 control-label form-label">电话：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${userInfo.phone}</p>
									</div>										
									<label class="col-lg-2 col-xs-2 control-label form-label">电子邮箱：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${userInfo.email}</p>										
									</div>											
								</div>
								<div class="form-group">
									<label class="col-lg-2 col-xs-2 control-label form-label">用户类型：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${dict:display('userType',userInfo.userType) }</p>
									</div>								
									<label class="col-lg-2 col-xs-2 control-label form-label">用户ID：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${userInfo.newUserID}</p>
									</div>												
								</div>
								<div class="form-group">
									<label class="col-lg-2 col-xs-2 control-label form-label">余额：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${userInfo.balance}</p>
									</div>
									<label class="col-lg-2 col-xs-2 control-label form-label">密码：</label>
									<div class="col-lg-3 col-xs-3" id="password" style="display:none">
										<p class="form-control-static">${userInfo.password}</p>
									</div>	
									<div class="col-lg-1 col-xs-1" id="passwordButton">
										<button class="btn btn-light" type="button" onclick="getPassWord()"><i class="fa  fa-search"></i>点击查看</button>
									</div>																		
								</div>
								<div class="form-group">
									<label class="col-lg-2 col-xs-2 control-label form-label">备注：</label>
									<div class="col-lg-8 col-xs-8">
										<p class="form-control-static">${userInfo.remark}</p>							
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
<!-- 表单校验区 -->
<script type="text/javascript">

function getPassWord(){  
	document.getElementById("passwordButton").style.display="none";
	document.getElementById("password").style.display="";
}
</script>