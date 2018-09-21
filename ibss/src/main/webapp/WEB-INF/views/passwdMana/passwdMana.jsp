<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
	<head>

		<title>修改密码</title>

		<style type="text/css">
			body {
				background: #F5F5F5;
			}
		</style>
	</head>

	<body>

		<div class="login-form">
			<form id="passwordForm" action="${ctx}/passwdMana/alterPasswd"
				method="post" onsubmit="return checkInfo('${passwd}');">
				<div class="top">
					<h1>修改密码</h1>
				</div>
				<div class="form-area">
        			<c:if test="${!empty message && message != '' }">
        			<div class="group">
						<button class="btn btn-danger btn-block" >${message }</button>
					</div>
					</c:if>
					<div class="group">
						<input id="password1"type="password" class="form-control" placeholder="原密码" 
						oninput="button1(this.value)" name="unPassword"  >
						<i id="key1" class="fa fa-key" onclick="eye1()" ></i>
					</div>					
					<div class="group">
						<input id="password2"type="password" class="form-control" placeholder="新密码" 
						oninput="button2(this.value)" name="password" >
						<i id="key2" class="fa fa-key" onclick="eye2()" ></i>
					</div>
					<div class="group">
						<input id="password3"type="password" class="form-control" placeholder="确认密码" 
						oninput="button3(this.value)" name="suPassword" >
						<i id="key3" class="fa fa-key" onclick="eye3()" ></i>
					</div>
					<div class="group">
					<button type="submit" class="btn btn-default btn-block">确认</button>
					<input type="button"onClick="javascript :history.back(-1);" class="btn btn-light btn-block" value="返回">
					</div>
				</div>
			</form>
		</div>

	</body>
	<script type="text/javascript">	
	//显示可视按钮1	
	function button1(password){
		if(password!=null&&password!=''){
			$('#key1').attr('class','fa fa-eye');
		}else{
			$('#key1').attr('class','fa fa-key');
		}
	}
 	//可视操作1
	function eye1(){
		var KeyClass=document.getElementById("key1").className;
 		if(KeyClass=="fa fa-eye"){
 			$('#key1').attr('class','fa fa-eye-slash');
			$('#password1').attr('type','text');  
		}else if(KeyClass=="fa fa-eye-slash"){
			$('#key1').attr('class','fa fa-eye');
			$('#password1').attr('type','password');  
		}
	} 
	//显示可视按钮2	
	function button2(password){
		if(password!=null&&password!=''){
			$('#key2').attr('class','fa fa-eye');
		}else{
			$('#key2').attr('class','fa fa-key');
		}
	}
 	//可视操作2
	function eye2(){
		var KeyClass=document.getElementById("key2").className;
 		if(KeyClass=="fa fa-eye"){
 			$('#key2').attr('class','fa fa-eye-slash');
			$('#password2').attr('type','text');  
		}else if(KeyClass=="fa fa-eye-slash"){
			$('#key2').attr('class','fa fa-eye');
			$('#password2').attr('type','password');  
		}
	} 
	//显示可视按钮3
	function button3(password){
		if(password!=null&&password!=''){
			$('#key3').attr('class','fa fa-eye');
		}else{
			$('#key3').attr('class','fa fa-key');
		}
	}
 	//可视操作3
	function eye3(){
		var KeyClass=document.getElementById("key3").className;
 		if(KeyClass=="fa fa-eye"){
 			$('#key3').attr('class','fa fa-eye-slash');
			$('#password3').attr('type','text');  
		}else if(KeyClass=="fa fa-eye-slash"){
			$('#key3').attr('class','fa fa-eye');
			$('#password3').attr('type','password');  
		}
	} 
 	//校验密码
	function checkInfo(passwd) {
		var unPassword = document.getElementById("password1").value;
		var password = document.getElementById("password2").value;
		var suPassword = document.getElementById("password3").value;
		if (passwd != unPassword) {
			layerOpen("原密码有误，请重新填写!");
			return false;
		}
		if (password != suPassword) {
			layerOpen("新密码与确认密码应该相同!");
			return false;
		}
	}
 	
 	//密码格式校验
 	$(document).ready(function() {
		//英文字母数字
		jQuery.validator.addMethod("alnum", function(value, element) {
			return this.optional(element) || /^[a-zA-Z0-9]+$/.test(value);
		}, "只能包括英文字母和数字");
		$("#passwordForm").validate({ 
			rules: { 
				unPassword: {
					rangelength : [ 1, 20 ],
					alnum : true,
					required : true
				},
				password: {
					rangelength : [ 1, 20 ],
					alnum : true,
					required : true
				},
				suPassword: {
					rangelength : [ 1, 20 ],
					alnum : true,
					required : true
				}
			}
		});
	}); 
	//弹出框
	function layerOpen(contents) {
		layer.open({
			type: 1,
			area: ['400px', '200px'],
			btn: ['确认', '关闭'],
			shadeClose: true, //点击遮罩关闭
			content: '<div style="padding:20px;">'+contents+'</div>'
		});
	}
 	</script>