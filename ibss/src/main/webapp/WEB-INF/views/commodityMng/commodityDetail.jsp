<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/include/common.jsp"%>

<head>
<title>商品详细</title>
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
				<h1 class="title">商品详细</h1>
				<ol class="breadcrumb">
					<li class="active">商品管理</li>
					<li class="active">商品详细</li>
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
							<form class="form-horizontal" id="searchForm" action="${ctx}/commodity/detail/${commodity.commodityKey}" method="post">							
								<!-- /widget-header -->
								<div class="form-group">
									<label class="col-lg-2 col-xs-2 control-label form-label">商品名称：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${commodity.commodityName}</p>
									</div>										
									<label class="col-lg-2 col-xs-2 control-label form-label">商品类别：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${commodity.commodityClassName}</p>										
									</div>											
								</div>
								<div class="form-group">
									<label class="col-lg-2 col-xs-2 control-label form-label">商品ID：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${commodity.commodityID}</p>
									</div>										
									<label class="col-lg-2 col-xs-2 control-label form-label">单价(元)：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${commodity.price}</p>										
									</div>											
								</div>
								<div class="form-group">
									<label class="col-lg-2 col-xs-2 control-label form-label">库存：</label>
									<div class="col-lg-3 col-xs-3">
										<p class="form-control-static">${commodity.stock}</p>
									</div>																			
								</div>
								<div class="form-group">
									<label class="col-lg-2 col-xs-2 control-label form-label">备注：</label>
									<div class="col-lg-8 col-xs-8">
										<p class="form-control-static">${commodity.remark}</p>							
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
</script>