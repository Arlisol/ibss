<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>
	<head>
		<title>音乐库</title>
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
				<h1 class="title">音乐库</h1>
			</div>
			<div class="container-padding">
				<div class="row">
					<form class="form-horizontal" id="searchForm" action="${ctx}/selectMusic/list" method="post">
						<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-title">
										<label>查询条件</label>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">音乐名称：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" class="form-control"  name="search_musicName" value="${param.search_musicName}">
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">歌手：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" class="form-control"  name="search_singer" value="${param.search_singer}">
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">音乐风格：</label>
										<div class="col-lg-3 col-xs-3 ">
											<tags:wselect key="musicStyle" name="search_musicStyle" value="${param.search_musicStyle}"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">语言：</label>
										<div class="col-lg-3 col-xs-3 ">
											<tags:wselect key="language" name="search_language" value="${param.search_language}"/>
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
												<th>音乐名称</th>
												<th>歌手</th>
												<th>专辑</th>	
												<th>音乐风格</th>										
												<th>语言</th>
												<td>操作</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${musicList.content }" var="musicList" varStatus="status">
												<tr>
													<td>${status.count }</td>
													<td>
														<a href="${ctx}/music/detail/${musicList.musicKey}">${musicList.musicName }</a>
													</td>
													<td>${musicList.singer}</td>
													<td>${musicList.album}</td>
													<td>${dict:display('musicStyle',musicList.musicStyle) }</td>
													<td>${dict:display('language',musicList.language) }</td>
													<td>
														<a href="javascript:selectMusic('${musicList.musicKey}');"class="btn btn-success"><i class="fa fa-bell"></i>点歌</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<!-- 分页组件开始 -->
									<tags:pagination page="${musicList}"/>
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
	function selectMusic(musicKey){
		jQuery.ajax({ 
			type: "post", 
			url: '${ctx }/selectMusic/add?musicKey='+musicKey,
			contentType: "application/json",
			dataType: "json",
			success: function (data) { 
				if(data.flag){
					layerOpen("点歌成功！");
				}else{
					layerOpen("点歌失败，请重新点歌！");
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