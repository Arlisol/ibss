<%@ page contentType="text/html;charset=UTF-8"%>
<!--头部开始 -->
<div id="top" class="clearfix" style="background: #208eff;">

	<!-- 头部名称开始 -->
	<div class="applogo">
		<a href="#" class="logo">网吧服务系统</a>
	</div>
	<!-- 头部名称结束 -->

	<!-- 左侧边栏的显示和隐藏按钮 -->
	<a  class="sidebar-open-button"><i class="fa fa-bars"></i></a>
	<a  class="sidebar-open-button-mobile"><i class="fa fa-bars"></i></a>
	<!-- 左侧边栏的显示和隐藏按钮 -->

	<!-- 头部菜单 -->
	<ul class="topmenu">
		<li>
			<a >上次登录时间：${sessionScope.user.lastLoginTime}</a>
		</li>
	</ul>
	<!-- 头部菜单 -->
	
	<!-- 头部右侧 -->
	<ul class="top-right">
		<li class="dropdown link">
			<a  data-toggle="dropdown" class="dropdown-toggle profilebox">
				 <%-- <c:choose>
					<c:when test="${sessionScope.user.icon == '' || empty sessionScope.user.icon }"> 
						<img src="${ctx}/static/img/profileimg.png" alt="img" />
					 </c:when>
					<c:otherwise>
						<img src="${ctx}/${sessionScope.user.icon}" alt="img" />
					</c:otherwise>
				</c:choose>  --%>
				<b>${sessionScope.user.name}</b>
				<span class="caret"></span>
			</a>
			<ul class="dropdown-menu dropdown-menu-list dropdown-menu-right">
				<li>
					<a href="${ctx }/personalInfo/getUserInfo/${sessionScope.user.userKey}"><i class="fa falist fa-info-circle"></i>个人资料</a>
				</li>
				<li>
					<a href="${ctx }/personalInfo/intoModify/${sessionScope.user.userKey}"><i class="fa falist fa-question-circle"></i>修改资料</a>
				</li>
				<li>
					<a href="${ctx }/passwdMana"><i class="fa falist fa-lock"></i>修改密码</a>
				</li>
				<li>
					<a href="${ctx }/login/logout"><i class="fa falist fa-power-off"></i> 退出</a>
				</li>
			</ul>
		</li>

	</ul>
	<!-- 头部右侧 -->

</div>
<!--头部开始 -->