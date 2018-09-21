<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>

	<head>
		<title>充值</title>
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
				<h1 class="title">充值</h1>
				<ol class="breadcrumb">
					<li class="active">用户管理</li>
					<li>
						<a href="${ctx}/userInfo/list">用户查询</a>
					</li>
					<li class="active">充值</li>
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
								<form class="form-horizontal" id="userInfoForm" action="${ctx}/userInfo/submitRecharge" method="post" >	
									<input type="hidden" class="form-control" name="userKey" value="${userKey}"/>
									<input type="hidden" id="userType" class="form-control" name="userType" value="${userType}"/>							 
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">充值金额：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="rechargeSum" class="form-control" name="rechargeSum" oninput="getPresentSum(this.value)"/>
										</div>
										<c:if test="${userType eq '3'}">
										<label class="col-lg-2 col-xs-2 control-label form-label">赠送金额：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="presentSum" class="form-control" name="presentSum" readonly="readonly" value="0"/>
										</div>
										</c:if>
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
	function getPresentSum(rechargeSum){
		var userType = document.getElementById ("userType").value;
		var intRechargeSum = parseInt(rechargeSum);
		var presentSum = 0;
		if(userType == 3 && intRechargeSum >= 20){
			presentSum = intRechargeSum*0.5;
			document.getElementById ("presentSum").value = presentSum;
		}else{
			document.getElementById ("presentSum").value = 0;
		}
	}

	<!-- 输入格式校验 -->
	$(document).ready(function() {
		//正整数验证
		jQuery.validator.addMethod("zhengzhengshu", function(value, element) {
			return this.optional(element) || /^[1-9]\d*$/.test(value);
		}, "请输入正整数");
		return $("#userInfoForm").validate({
			rules : {
				rechargeSum: {
			    	required: true,
			    	zhengzhengshu: true
			    }
			}
		});
	});
	</script>