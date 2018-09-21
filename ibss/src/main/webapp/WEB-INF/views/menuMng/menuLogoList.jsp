<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>
	<head>
		<title>菜单Logo码列表</title>
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
				<h1 class="title">菜单Logo码列表</h1>
				<ol class="breadcrumb">
					<li class="active">菜单管理</li>
				</ol>
			</div>
			<div class="container-padding">
				<div class="row">
					<form class="form-horizontal" id="searchForm" action="${ctx}/menu/menuLogoList" method="post">
						<div class="col-md-12">
							<div class="panel panel-default">
								<div class="panel-title">
									<label>菜单Logo码列表</label>
								</div>
								
								<div class="panel-body table-responsive layui-form">
									<table class="table table-hover">
										<tr >
											<td><img alt="暂无" src="${ctx}/static/img/menuLogo.jpg"></td>
										</tr>
									</table>																		
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
	</script>