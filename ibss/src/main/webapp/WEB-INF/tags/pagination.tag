<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="page" type="org.springframework.data.domain.Page"
	required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int current = page.getNumber();
	int total = page.getTotalPages();
	if(current > total-1) {
		current = total-1;
	}
	request.setAttribute("current", current);
	request.setAttribute("total", total);
%>
<div class="col-md-12">
	<div class="panel-body">
		<nav>
			<ul class="pagination ">
				<li>
					<span aria-hidden="true">第${current+1}页 </span>
					<input type="hidden" id="pageHidden" name="page.page" value="${current+1}"> 
				</li>
				<li>
					<span aria-hidden="true">共${total}页 </span>
					<input type="hidden" id="pageTotalHidden" name="page.totalPages" value="${total}">
				</li>
				<li>
					<span aria-hidden="true">本页${page.numberOfElements}条 </span>
				</li>
				<li>
					<span aria-hidden="true">共${page.totalElements}条 </span>
				</li>
			</ul>
			<ul class="pagination ">
				<li>				
					<%	if (page.hasPreviousPage()) {	%>
								<a href="#"  aria-hidden="true" onclick="pageSummbit(1)">首页</a>
					<%	} else {	%>
								<span aria-hidden="true">首页</span>
					<%	}	%>
				</li>
				<li>				
					<%	if (page.hasPreviousPage()) {	%>
								<a aria-hidden="true" href="#" onclick="pageSummbit(${current});">上一页</a>
					<%	} else {	%>
								<span aria-hidden="true">上一页</span>
					<%	}	%>
				</li>
				<li>				
					<%	if (page.hasNextPage()) {	%>
								<a aria-hidden="true" href="#"onclick="pageSummbit(${current+2});">下一页</a>
					<%	} else {	%>
								<span aria-hidden="true">下一页</span>
					<%	}	%>
				</li>
				<li>				
					<%	if (page.hasNextPage()) {	%>
								<a href="#"onclick="pageSummbit(${page.totalPages});">末页</a>
					<%	} else {	%>
								<span aria-hidden="true">末页</span>
					<%	}	%>
				</li>
			</ul>
			<ul class="pagination ">		
				<li>
					<input  style="position: relative;float: left;padding: 6px 12px;margin-left: -1px;line-height: 1.42857143;
						color: #337ab7;text-decoration: none;background-color: #fff;border: 1px solid #ddd;width:65px;height:32.4px;"
						id="goToPageNumber" name="goToPageNumber" type="text"  /> 
				<li>
				<li>
					<a aria-hidden="true" href="#" onclick="goToPageSummbit()">GO</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
<script type="text/javascript">
function pageSummbit(goToPageNumber){
	$("#pageHidden").val(goToPageNumber);
	$("#searchForm").submit();
}
function goToPageSummbit(){
    var goToPageNumber = $("#goToPageNumber").val();
    var totalPages = $("#pageTotalHidden").val();
    if(parseInt(goToPageNumber) > parseInt(totalPages)) {
    	$("#pageHidden").val(totalPages);
    } else {
    	$("#pageHidden").val(goToPageNumber);
    }
	$("#searchForm").submit();
}
</script>