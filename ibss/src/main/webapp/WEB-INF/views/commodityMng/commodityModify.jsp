<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>

	<head>
		<title>商品修改</title>
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
				<h1 class="title">商品修改</h1>
				<ol class="breadcrumb">
					<li class="active">商品管理</li>
					<li>
						<a href="${ctx}/commodity/list">商品查询</a>
					</li>
					<li class="active">商品修改</li>
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
								<form class="form-horizontal" id="commodityForm" action="${ctx}/commodity/submitModify/${getCommodity.commodityKey}" method="post" >								 
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">商品名称：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="commodityName" class="form-control" name="commodityName" value="${getCommodity.commodityName}"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">商品类别：</label>
										<div class="col-lg-3 col-xs-3 ">
											<select class="selectpicker"  id="commodityClassKey"  name="commodityClassKey">
												<option value="">--请选择--</option>
										    	<c:forEach var="commodityClassList" items="${commodityClassList}">
											    	<option value="${commodityClassList.commodityClassKey}"
													<c:if test="${commodityClassList.commodityClassKey eq getCommodity.commodityClassKey}">selected="selected"</c:if>>${commodityClassList.commodityClassName} </option>
												</c:forEach>
											</select>
										</div>	
									</div>	
									<div class="form-group" >
										<label class="col-lg-2 col-xs-2 control-label form-label">商品ID：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="commodityID" class="form-control" name="commodityID" placeholder="大写字母+数字" value="${getCommodity.commodityID}"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">单价(元)：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="price" class="form-control" name="price" value="${getCommodity.price}"/>
										</div>	
									</div>	
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">库存：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="stock" class="form-control" name="stock" value="${getCommodity.stock}"/>
										</div>
									</div>																																															
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">备注：</label>
										<div class="col-lg-8 col-xs-8">
											<textarea class="form-control" rows="4" id="remark" name="remark" >${getCommodity.remark}</textarea>
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
		//非负整数验证
		jQuery.validator.addMethod("feifuzhengshu", function(value, element) {
			return this.optional(element) || /^\d+$/.test(value);
		}, "请输入非负整数");
		//非负数验证
		jQuery.validator.addMethod("feifushu", function(value, element) {
			return this.optional(element) || /^\d+(\.{0,1}\d+){0,1}$/.test(value);
		}, "请输入非负数");
		// 选择下拉框格式验证
		jQuery.validator.addMethod("select", function(value, element) {
			var length = value.length;
			return this.optional(element) || (length != 4);
		}, "必选字段");
		return $("#commodityForm").validate({
			rules : {
				commodityName: {
			    	required: true		    	
			    },
			    commodityClassKey: {
			    	select: true			    	
			    },
			    commodityID: {
			    	required: true
			    },
			    price: {
			    	required: true,
			    	feifushu: true
			    },
			    stock: {
			    	required: true,
			    	feifuzhengshu: true
			    }
			}
		});
	});
	</script>