<%@tag import="com.xlkj.framework.utils.dictionary.domain.Dictionary"%>
<%@tag import="com.xlkj.framework.web.Dict"%>
<%@tag import="java.util.List"%>
<%@tag import="com.xlkj.framework.utils.DbUtils"%>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="dict" uri="http://www.summer.org/tags/dict" %>
<%@ attribute name="id" type="java.lang.String" required="false"%>
<%@ attribute name="name" type="java.lang.String" required="true"%>
<%@ attribute name="key" type="java.lang.String" required="true"%>
<%@ attribute name="value" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String sid = id;
String sname = name;
if(id==null){
	sid = sname;
}
request.setAttribute("sid", sid);
request.setAttribute("sname", sname);
String tagValue = " ";
if(value != null && value != "") {
	List<Integer> tag1Position = DbUtils.getTag1(Integer.parseInt(value));
	for(Integer i : tag1Position) {
		tagValue += ((int)Math.pow(2, (i-1)) + " ");
	}
}
%>
<c:set var="tagValue1" value="<%=tagValue %>"></c:set>
<select id="${sid}" name="${name}" multiple="multiple" <c:choose><c:when test="${fn:length(dict:list(key)) >= 5}">size="5"</c:when><c:otherwise>size="${fn:length(dict:list(key))}"</c:otherwise></c:choose>>
	<c:forEach items="${dict:list(key)}" var="dict" varStatus="status">
		<c:set var="code1" value=" ${dict.sequence } "></c:set>
       	<c:choose>
	       	<c:when test="${fn:contains(tagValue1, code1)}">
				<option selected="selected" value="${dict.code}" >${dict.label}</option>
	       	</c:when>
	       	<c:otherwise>
	       		<option value="${dict.code}" >${dict.label}</option>
	       	</c:otherwise>	
      	</c:choose>
	</c:forEach>
</select>