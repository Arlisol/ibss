<%@ page contentType="text/html;charset=UTF-8"%>
<!-- 左侧边栏开始 -->
<div class="sidebar clearfix">
	<%-- <ul class="sidebar-panel nav">
		<li>
			<a href="#"><span class="icon color6"><i class="fa fa-envelope-o"></i></span>收信箱<c:if test="${sessionScope.errorCount > 0}"><span class="label label-danger">${sessionScope.errorCount}</span></c:if></a>
		</li>
	</ul> --%>
	<c:if test="${sessionScope.menuList!=null}">
		<ul class="sidebar-panel nav">
			<li class="sidetitle">菜单</li>
			<c:forEach items="${sessionScope.menuList }" var="menuList">
				<li>
					<a id="${menuList.menuKey }"  onclick="UpToggle(this.id)"><span class="icon color7"><i class="fa ${menuList.menuLogo }"></i></span>${menuList.menuName }<span class="caret"></span></a>
					<ul id="ul-${menuList.menuKey }">
						<c:forEach items="${menuList.subMenuList }" var="subMenuList">			
							<li>
								<a href="${subMenuList.menuURL }">${subMenuList.menuName }</a>
							</li>
						</c:forEach>
					</ul>
				</li>
			</c:forEach>				
		</ul>
	</c:if>
</div>
<!-- 左侧边栏结束 -->
<script type="text/javascript">	
/* Sidebar Menu*/
 function UpToggle(id){
	   if ($('#'+id).attr('class') != 'active'){
     $('#ul-'+id).slideUp();
     $('#'+id).next().slideToggle();
     $('#ul-'+id+' a').removeClass('active');
     $('#'+id).addClass('active');
   }else{
	 $('#ul-'+id).slideToggle();
	 $('#'+id).next().slideUp();
	 $('#ul-'+id+' a').addClass('active');
	 $('#'+id).removeClass('active');
   } 
}
</script>