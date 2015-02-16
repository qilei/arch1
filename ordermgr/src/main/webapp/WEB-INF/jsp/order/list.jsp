<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/static/css/application.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/static/js/application.js"></script>
<script src="${pageContext.request.contextPath}/static/js/jquery-1.11.0.js"></script>
</head>
<body>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="myTag" tagdir="/WEB-INF/tags" %>


<table  width="100%" border="1" cellpadding="0" cellspacing="1" class="tableLine DoubleColorTable" >
	<tr>
		<td colspan="6">
			<a href="${pageContext.request.contextPath}/order/toQuery">转到查询</a>
			&nbsp;&nbsp;
			<a href="${pageContext.request.contextPath}/order/toAdd">转到新增</a>
		</td>
	</tr>
		<tr>
			<td colspan=${5+1} align="center" class="tableLineBg">主订单列表</td>
		</tr>
		<tr>
			<td>客户编号</td>
	<td>下订单时间</td>
	<td>总金额</td>
	<td>节省金额</td>
	<td>状态</td>
	
			<td>操作</td>
		</tr>
			
		<c:forEach var="m" items="${pagedList}">
		  <tr>
			<td>${m.customerUuid}</td>
	<td>${m.orderTime}</td>
	<td>${m.totalMoney}</td>
	<td>${m.saveMoney }</td>
	<td>${m.state}</td>
	<td>
	<a href="${pageContext.request.contextPath}/order/toUpdate/${m.uuid}">修改 </a> |
	<a href="${pageContext.request.contextPath}/order/toDelete/${m.uuid}">删除</a>
</td>

		   </tr>
		</c:forEach>
				
	    <tr>
			<td colspan=${5+1} align="center">
                <myTag:page pagedList="${pagedList}"></myTag:page>
			</td>
		</tr>
	</table>
</body>
</html>
