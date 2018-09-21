<%@tag pageEncoding="UTF-8"%>
<%@ attribute name="items" type="java.lang.String" required="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach items="${items}" var="item">
<c:set var="target" value="${item}" />
<script type="text/javascript" src="${applicationScope[target]}"></script>
</c:forEach>
