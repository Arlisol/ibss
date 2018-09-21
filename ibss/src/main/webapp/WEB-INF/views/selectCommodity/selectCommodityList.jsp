<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>
	<head>
		<title>商品库</title>
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
				<h1 class="title">商品库</h1>
			</div>
			<div class="container-padding">
				<div class="row">
					<form class="form-horizontal" id="searchForm" action="${ctx}/selectCommodity/list" method="post">
						<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-title">
										<label>查询条件</label>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">商品名称：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" class="form-control"  name="search_commodityName" value="${param.search_commodityName}">
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">商品类别：</label>
										<div class="col-lg-3 col-xs-3 ">
											<select class="selectpicker"  id="commodityClassKey"  name="search_commodityClassKey">
												<option value="">--请选择--</option>
										    	<c:forEach var="commodityClassList" items="${commodityClassList}">
											    	<option value="${commodityClassList.commodityClassKey}"
													<c:if test="${commodityClassList.commodityClassKey eq param.search_commodityClassKey}">selected="selected"</c:if>>${commodityClassList.commodityClassName} </option>
												</c:forEach>
											</select>
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
								</div>
								
								<div class="panel-body table-responsive layui-form">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>商品名称</th>
												<th>商品类别</th>
												<th>商品ID</th>	
												<th>单价(元)</th>										
												<th>库存</th>
												<th>购买数量</th>
												<td>操作</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${commodityList.content }" var="commodityList" varStatus="status">
												<tr>
													<td>${status.count }</td>
													<td>
														<a href="${ctx}/commodity/detail/${commodityList.commodityKey}">${commodityList.commodityName }</a>
													</td>
													<td>${commodityList.commodityClassName}</td>
													<td>${commodityList.commodityID}</td>
													<td>${commodityList.price}</td>
													<td>${commodityList.stock}</td>
													<td>
														<button class="btn btn-light" type="button" style="width:52px;height:38px" onclick="minusCount(${status.count})"><i class="fa fa-minus"></i></button>
														<input type="text" class="form-control" id="${status.count}" name="count" style="width:200px;text-align:center" readonly="readonly" value="1">
														<button class="btn btn-light" type="button" style="width:52px;height:38px" onclick="addCount(${status.count},${commodityList.stock})"><i class="fa fa-plus"></i></button>
													</td>
													<td>
														<a href="javascript:selectCommodity('${status.count}','${commodityList.commodityKey}','${commodityList.price}');"class="btn btn-success"><i class="fa fa-shopping-cart"></i>购买</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<!-- 分页组件开始 -->
									<tags:pagination page="${commodityList}"/>
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
	function addCount(id,stock){
		var stockNum = parseInt(stock);
		var count = document.getElementById (id).value;
		var countNum = parseInt(count);
		countNum++;
		if(countNum>=stockNum){
			countNum=stockNum;
		}
		var newCount = String(countNum);
		document.getElementById(id).value = newCount; 
	}
	
	function minusCount(id){
		var count = document.getElementById (id).value;
		var countNum = parseInt(count);
		countNum--;
		if(countNum<=1){
			countNum=1;
		}
		var newCount = String(countNum);
		document.getElementById(id).value = newCount; 
	}
	
	function selectCommodity(id,commodityKey,price){
		layerOpen("您确认要购买此商品吗？");
		$(".layui-layer-btn0").click(function(){
			var recordCount = document.getElementById (id).value;
			var buyRecords = commodityKey+","+recordCount+","+price;
			jQuery.ajax({ 
				type: "post", 
				url: '${ctx }/selectCommodity/add?buyRecords='+buyRecords,
				contentType: "application/json",
				dataType: "json",
				success: function (data) { 
					if(data.flag){
						layerOpen("购买成功！");
					}else{
						layerOpen("购买失败，请重新购买！");
					}
				}	        
			})
		})
	}
	
	//弹出框
	function layerOpen(contents) {
		layer.open({
			type: 1,
			offset: ['390px', '760px'],
			area: ['400px', '200px'],
			btn: ['确认', '关闭'],
			shadeClose: true, //点击遮罩关闭
			content: '<div style="padding:20px;">'+contents+'</div>'
		});
	}
	</script>