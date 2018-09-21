<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>
	<head>
		<title>商品类别查询</title>
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
				<h1 class="title">商品类别查询</h1>
				<ol class="breadcrumb">
					<li class="active">商品类别管理</li>
					<li>
						<a href="${ctx}/commodityClass/list">商品类别查询</a>
					</li>
				</ol>
			</div>
			<div class="container-padding">
				<div class="row">
					<form class="form-horizontal" id="searchForm" action="${ctx}/commodityClass/list" method="post">
						<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-title">
										<label>查询条件</label>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">商品类别名称：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" class="form-control"  name="search_commodityClassName" value="${param.search_commodityClassName}">
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">商品类别ID：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" class="form-control"  name="search_commodityClassID" value="${param.search_commodityClassID}">
										</div>
									</div>									
									<div class="form-group">
										<label class="col-lg-10 col-xs-10 control-label form-label"></label>
										<div class="col-lg-1 col-xs-1">
											<button class="btn btn-light "type="submit"  ><i class="fa  fa-search"></i>查询</button>
										</div>
									</div>
								</div>
						</div>
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-title">
									<label>查询结果</label>
									<a href="${ctx}/commodityClass/intoAdd"class="btn btn-light" ><i class="fa fa-plus"></i>新增</a>
								</div>
								
								<div class="panel-body table-responsive layui-form">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>商品类别名称</th>
												<th>商品类别ID</th>
												<td>操作</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${commodityClassList.content }" var="commodityClassList" varStatus="status">
												<tr>
													<td>${status.count }</td>
													<td>${commodityClassList.commodityClassName }</td>
													<td>${commodityClassList.commodityClassID}</td>
													<td>
														<a href="${ctx}/commodityClass/intoModify/${commodityClassList.commodityClassKey}"class="btn btn-default "><i class="fa fa-pencil-square"></i>修改</a>
														<a href="javascript:intoDelete('${commodityClassList.commodityClassKey}');"class="btn btn-danger"><i class="fa fa-trash"></i>删除</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<!-- 分页组件开始 -->
									<tags:pagination page="${commodityClassList}"/>
									<!-- 分页组件开始 -->
									
								</div>
							</div>
						</div>								
					</form>		
				</div>
			</div>
		</div>
			<!-- 内容结束 -->
			
			<!--底开始-->
			<%@ include file="/WEB-INF/include/userFooter.jsp"%>
			<!-- 底结束 -->
	</body>
	<script type="text/javascript">
	function intoDelete(commodityClassKey){
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '${ctx}/commodityClass/countCommodityClass?commodityClassKey='+commodityClassKey,
			dataType : 'json',	
			success : function(data) {
				if(data.flag>0){
					layerOpen("存在商品属于该商品类别，不能删除！");
				}else{
					layerOpen("是否确认删除该商品类别？");
					$(".layui-layer-btn0").click(function(){
						window.location.href="${ctx}/commodityClass/intoDelete/"+commodityClassKey;
					});
				}
			}
	    });
	}
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