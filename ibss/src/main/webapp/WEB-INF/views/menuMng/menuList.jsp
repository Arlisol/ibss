<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>
	<head>
		<title>菜单查询</title>
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
				<h1 class="title">菜单查询</h1>
				<ol class="breadcrumb">
					<li class="active">菜单管理</li>
					<li>
						<a href="${ctx}/menu/list">菜单查询</a>
					</li>
				</ol>
			</div>
			<div class="container-padding">
				<div class="row">
					<form class="form-horizontal" id="searchForm" action="${ctx}/menu/list" method="post">
						<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-title">
										<label>查询条件</label>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">菜单名称：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" class="form-control"  name="search_menuName" value="${param.search_menuName}">
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">菜单等级：</label>
										<div class="col-lg-3 col-xs-3 ">
											<tags:wselect key="menuLevel" name="search_menuLevel" value="${param.search_menuLevel}"/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">用户类型：</label>
										<div class="col-lg-3 col-xs-3 ">
											<tags:wselect key="userType" name="search_userType" value="${param.search_userType}"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">父级菜单：</label>
										<div class="col-lg-3 col-xs-3 ">
											<select class="selectpicker"  id="parentMenuKey"  name="search_parentMenuKey">
												<option value="">--请选择--</option>
										    	<c:forEach var="menuLevel1List" items="${menuLevel1List}">
											    	<option value="${menuLevel1List.menuKey}"
													<c:if test="${menuLevel1List.menuKey eq param.search_parentMenuKey}">selected="selected"</c:if>>${menuLevel1List.menuName} </option>
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
									<a href="${ctx}/menu/intoAdd"class="btn btn-light" ><i class="fa fa-plus"></i>新增</a>
								</div>
								
								<div class="panel-body table-responsive layui-form">
									<table class="table table-hover">
										<thead>
											<tr>
												<th>序号</th>
												<th>菜单名称</th>
												<th>菜单等级</th>	
												<th>父级菜单</th>		
												<th>用户类型</th>								
												<th>菜单排序码</th>
												<th><a href="${ctx}/menu/menuLogoList" target="_blank">菜单Logo码</a></th>
												<td>操作</td>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${menuList.content }" var="menuList" varStatus="status">
												<tr>
													<td>${status.count }</td>
													<td>
														<a href="${ctx}/menu/detail/${menuList.menuKey}">${menuList.menuName }</a>
													</td>
													<td>${dict:display('menuLevel',menuList.menuLevel) }</td>
													<td>${menuList.parentMenuName}</td>
													<td>${dict:display('userType',menuList.userType) }</td>
													<td>
														<c:if test="${menuList.menuLevel eq '1'}">${menuList.groupSequence}</c:if>
														<c:if test="${menuList.menuLevel eq '2'}">${menuList.menuSequence}</c:if>
													</td>
													<td>${menuList.menuLogo}</td>
													<td>														
														<a href="${ctx}/menu/intoModify/${menuList.menuKey}"class="btn btn-default "><i class="fa fa-pencil-square"></i>修改</a>
														<a href="javascript:intoDelete('${menuList.menuKey}');"class="btn btn-danger"><i class="fa fa-trash"></i>删除</a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
									
									<!-- 分页组件开始 -->
									<tags:pagination page="${menuList}"/>
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
	function intoDelete(menuKey){
		$.ajax({
			type : 'POST',
			contentType : 'application/json',
			url : '${ctx}/menu/countmenu?menuKey='+menuKey,
			dataType : 'json',	
			success : function(data) {
				if(data.flag>0){
					layerOpen("该菜单已存在子菜单，不能删除！");
				}else{
					layerOpen("是否确认删除该菜单？");
					$(".layui-layer-btn0").click(function(){
						window.location.href="${ctx}/menu/intoDelete/"+menuKey;
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