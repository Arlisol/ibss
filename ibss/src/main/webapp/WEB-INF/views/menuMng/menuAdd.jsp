<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>

	<head>
		<title>菜单新增</title>
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
				<h1 class="title">菜单新增</h1>
				<ol class="breadcrumb">
					<li class="active">菜单管理</li>
					<li>
						<a href="${ctx}/menu/list">菜单查询</a>
					</li>
					<li class="active">菜单新增</li>
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
								<form class="form-horizontal" id="menuForm" action="${ctx}/menu/submitAdd" method="post" >								 
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">菜单名称：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="menuName" class="form-control" name="menuName"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">菜单等级：</label>
										<div class="radio radio-inline">
											<input type="radio" id="one" name="menuLevel" value="1" checked="checked"/>
											<label for="one">一级</label>
										</div>						
										<div class="radio radio-inline">
											<input type="radio" id="two" name="menuLevel" value="2"/>
											<label for="two">二级</label>
										</div>	
									</div>	
									<div class="form-group" id="first">
										<label class="col-lg-2 col-xs-2 control-label form-label">一级菜单排序码：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="groupSequence" class="form-control" name="groupSequence"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">菜单Logo码：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="menuLogo" class="form-control" name="menuLogo"/>
										</div>	
									</div>
									<div class="form-group" id="second">
										<label class="col-lg-2 col-xs-2 control-label form-label">二级菜单排序码：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="menuSequence" class="form-control" name="menuSequence"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">菜单链接：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="menuURL" class="form-control" name="menuURL"/>
										</div>	
									</div>	
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">用户类型：</label>
										<div class="col-lg-3 col-xs-3 ">
											<tags:wselect key="userType" name="userType" value=""/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label" id="third">父级菜单：</label>
										<div class="col-lg-3 col-xs-3 " id="fourth">
											<select class="selectpicker"  id="parentMenuKey"  name="parentMenuKey">
												<option value="">--请选择--</option>
										    	<c:forEach var="menuLevel1List" items="${menuLevel1List}">
											    	<option value="${menuLevel1List.menuKey}">${menuLevel1List.menuName} </option>
												</c:forEach>
											</select>
										</div>
									</div>																																														
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">备注：</label>
										<div class="col-lg-8 col-xs-8">
											<textarea class="form-control" rows="4" id="remark" name="remark" ></textarea>
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
	$(document).ready(function(){
		if(document.getElementById("one").checked){
			$('#first').show();
			$('#second').hide();
			$('#third').hide();
			$('#fourth').hide();
		}
		if(document.getElementById("two").checked){
			$('#first').hide();
			$('#second').show();
			$('#third').show();
			$('#fourth').show();
		} 
	});
	$("input:radio[name='menuLevel']").click(function(){  
		if(document.getElementById("one").checked){
			$('#first').show();
			$('#second').hide();
			$('#third').hide();
			$('#fourth').hide();
		}
		if(document.getElementById("two").checked){
			$('#first').hide();
			$('#second').show();
			$('#third').show();
			$('#fourth').show();
		} 
	});

	<!-- 输入格式校验 -->
	$(document).ready(function() {
		jQuery.validator.addMethod("shuzi", function(value, element) {
			return this.optional(element) || /^[0-9]+$/.test(value);
		}, "请输入数字");
		// 选择下拉框格式验证
		jQuery.validator.addMethod("select", function(value, element) {
			var length = value.length;
			return this.optional(element) || (length != 4);
		}, "必选字段");
		return $("#menuForm").validate({
			rules : {
				menuName: {
			    	required: true,
			    	rangelength: [2,10],
			    	remote : {
						url : "${ctx}/menu/menuNameUnique",
						type : "post",
						dataType : "json",
						data : {
							menuName : function() {
								return $("#menuName").val();
							}
						}
					}
			    },
			    menuLevel: {
			    	required: true
			    },
			    userType: {
			    	required: true
			    },
			    parentMenuKey: {
			    	select: true
			    },
			    groupSequence: {
			    	required: true,
			    	shuzi :true,
			    	rangelength: [1,4]
			    },
			    menuSequence: {
			    	required: true,
			    	shuzi :true,
			    	rangelength: [1,4]
			    },
			    menuURL: {
			    	required: true
			    },
			    menuLogo: {
			    	required: true,
			    	rangelength: [2,50]
			    }
			},
			messages : {
				menuName : {
					remote : "该菜单名称已存在！"
				}					
			}
		});
	});
	</script>