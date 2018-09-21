<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>

		<title>登录</title>

		<style type="text/css">
			body {
				background: #F5F5F5;
			}
		</style>
	</head>

	<body>

		<div class="login-form">
        	<form action="${ctx }/login/loginCheck" method="post">
				<div class="top">
					<%-- <img src="${ctx}/static/img/kode-icon.png" alt="icon" class="icon"/> --%>
					<h1>网吧服务系统</h1>
				</div>
				<div class="form-area">
        			<c:if test="${!empty message && message != '' }">
        			<div class="group">
						<button class="btn btn-danger btn-block" >${message }</button>
					</div>
					</c:if>
					<div class="group">
						<input type="text" class="form-control" placeholder="用户名"name="userName">
						<i class="fa fa-user"></i>
					</div>
					<div class="group">
						<input id="password"type="password" class="form-control" placeholder="密码" oninput="button(this.value)" name="password">
						<i id="key" class="fa fa-key" onclick="eye()" ></i>
					</div>
					<button type="submit" class="btn btn-default btn-block">登录</button>
				</div>
			</form>
		</div>

	</body>
	<script type="text/javascript">	
	//显示可视按钮	
	function button(password){
		if(password!=null&&password!=''){
			$('#key').attr('class','fa fa-eye');
		}else{
			$('#key').attr('class','fa fa-key');
		}
	}
 	//可视操作
	function eye(){
		var KeyClass=document.getElementById("key").className;
 		if(KeyClass=="fa fa-eye"){
 			$('#key').attr('class','fa fa-eye-slash');
			$('#password').attr('type','text');  
		}else if(KeyClass=="fa fa-eye-slash"){
			$('#key').attr('class','fa fa-eye');
			$('#password').attr('type','password');  
		}
	} 
 	</script>