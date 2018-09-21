<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/include/common.jsp"%>

	<head>
		<title>音乐新增</title>
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
				<h1 class="title">音乐新增</h1>
				<ol class="breadcrumb">
					<li class="active">音乐管理</li>
					<li>
						<a href="${ctx}/music/list">音乐查询</a>
					</li>
					<li class="active">音乐新增</li>
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
								<form class="form-horizontal" id="musicForm" action="${ctx}/music/submitAdd" 
								onsubmit="return submitForm()" method="post" >								 
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">音乐名称：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="musicName" class="form-control" name="musicName"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">歌手：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="singer" class="form-control" name="singer"/>
										</div>	
									</div>	
									<div class="form-group" >
										<label class="col-lg-2 col-xs-2 control-label form-label">专辑：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="album" class="form-control" name="album"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">音乐风格：</label>
										<div class="col-lg-3 col-xs-3 ">
											<tags:wselect key="musicStyle" name="musicStyle" value=""/>
										</div>	
									</div>
									<div class="form-group" >
										<label class="col-lg-2 col-xs-2 control-label form-label">发行时间：</label>
										<div class="col-lg-3 col-xs-3 ">
											<div class="input-prepend input-group">
												<span class="add-on input-group-addon"><i class="fa fa-calendar"></i></span>
												<input type="text" id="issueDate" class="form-control" name="issueDate"/>
											</div>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">时长：</label>
										<div class="col-lg-1 col-xs-1 ">
											<input type="text" id="minute" class="form-control" name="minute" placeholder="分"/>											
										</div>
										<div class="col-lg-1 col-xs-1" style="width:10px">
											<p class="form-control-static">:</p>
										</div>
										<div class="col-lg-1 col-xs-1 ">
											<input type="text" id="second" class="form-control" name="second" placeholder="秒"/>
										</div>	
									</div>	
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">填词：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="doWords" class="form-control" name="doWords"/>
										</div>
										<label class="col-lg-2 col-xs-2 control-label form-label">谱曲：</label>
										<div class="col-lg-3 col-xs-3 ">
											<input type="text" id="writeMusic" class="form-control" name="writeMusic"/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-2 col-xs-2 control-label form-label">语言：</label>
										<div class="col-lg-3 col-xs-3 ">
											<tags:wselect key="language" name="language" value=""/>
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
	$(document).ready(function() {
		$('#issueDate').daterangepicker({ 
			singleDatePicker : true,
			format : 'YYYY-MM-DD'
		});
	});
	
	<!-- 表单提交校验 -->
	function submitForm(){  
		//发行时间校验
		if(document.getElementById ("issueDate").value == "" || document.getElementById ("issueDate").value == null){
			layerOpen("请选择发行时间！"); 
			return false;
		}
	}

	<!-- 输入格式校验 -->
	$(document).ready(function() {
		//日期格式验证
		jQuery.validator.addMethod("date", function(value, element) {
			return this.optional(element) || /^((?:19|20)\d\d)-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/.test(value);
		}, "日期格式错误");
		//非负整数验证
		jQuery.validator.addMethod("feifuzhengshu", function(value, element) {
			return this.optional(element) || /^\d+$/.test(value);
		}, "请输入非负整数");
		//小于60的非负整数验证
		jQuery.validator.addMethod("xiaoyu60", function(value, element) {
			return this.optional(element) || /^[1-5]?[0-9]$/.test(value);
		}, "请输入小于60的非负整数");
		return $("#musicForm").validate({
			rules : {
				musicName: {
			    	required: true		    	
			    },
			    musicStyle: {
			    	required: true
			    },
			    singer: {
			    	required: true
			    },
			    album: {
			    	required: true
			    },
			    minute: {
			    	required: true,
			    	feifuzhengshu: true
			    },
			    second: {
			    	required: true,
			    	xiaoyu60: true
			    },
			    issueDate: {
			    	required: true,
			    	date: true
			    },
			    language: {
			    	required: true,
			    },
			}
		});
	});
	</script>