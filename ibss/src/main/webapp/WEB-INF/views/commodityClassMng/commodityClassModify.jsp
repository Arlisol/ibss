<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>

	<head>
		<title>商品类别修改</title>
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
				<h1 class="title">商品类别修改</h1>
				<ol class="breadcrumb">
					<li class="active">商品类别管理</li>
					<li>
						<a href="${ctx}/commodityClass/list">商品类别查询</a>
					</li>
					<li class="active">商品类别修改</li>
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
								<form class="form-horizontal" id="commodityClassForm" action="${ctx}/commodityClass/submitModify/${getCommodityClass.commodityClassKey}" method="post" >								 
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">商品类别名称：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="commodityClassName" class="form-control" name="commodityClassName" value="${getCommodityClass.commodityClassName}"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">商品类别ID：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="commodityClassID" class="form-control" name="commodityClassID" placeholder="大写字母+数字" value="${getCommodityClass.commodityClassID}"/>
										</div>	
									</div>																																																						
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">备注：</label>
										<div class="col-lg-8 col-xs-8">
											<textarea class="form-control" rows="4" id="remark" name="remark" >${getCommodityClass.remark}</textarea>
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
		return $("#commodityClassForm").validate({
			rules : {
				commodityClassName: {
			    	required: true		    	
			    },
			    commodityClassID: {
			    	required: true,			    	
			    }
			}
		});
	});
	</script>