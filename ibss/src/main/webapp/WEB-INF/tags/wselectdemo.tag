<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="dict" uri="http://www.summer.org/tags/dict" %>
<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ attribute name="key" type="java.lang.String" required="true"%>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String sid = id;
String sname = name;
if(id==null){
	sid = sname;
}
request.setAttribute("sid", sid);
request.setAttribute("sname", sname);
 %>
<select class="selectpicker"  id="${sid}" name="${name}" style="color:#97999a">
	<c:forEach items="${dict:list(key)}" var="dict" varStatus="status">
		<c:choose>
		 	<c:when test="${status.first eq true}">
				<option value="" disabled selected>员工属性</option>
				<c:choose>
			       	<c:when test="${dict.code eq value}"> 
						<option selected="selected" value="${dict.code}" >${dict.label}</option>
			       	</c:when>
			       	<c:otherwise>	
			       		<option value="${dict.code}" >${dict.label}</option>
			       	</c:otherwise>	
	       		</c:choose>
			</c:when>
	       	<c:otherwise>	
		       	<c:choose>
			       	<c:when test="${dict.code eq value}"> 
						<option selected="selected" value="${dict.code}" >${dict.label}</option>
			       	</c:when>
			       	<c:otherwise>	
			       		<option value="${dict.code}" >${dict.label}</option>
			       	</c:otherwise>	
	       		</c:choose>
	       	</c:otherwise>
		</c:choose>
	</c:forEach>
</select>