<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="page" uri="http://zhou.hao.hui"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'showlist.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>

	<body>
		<table>
			<tr>
				<td>
					id
				</td>
				<td>
					name
				</td>
			</tr>
			
			<c:forEach var="pageData" items="${findAll}">
				<tr>

					<td>
						${pageData.id }
					</td>
					<td>
						${pageData.name }
					</td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="2">
			<page:pager pageSize="${pager.pageSize }" pageNo="${pager.pageNow }" url="${pageContext.request.contextPath}/ShowList?method=show" recordCount="${pager.rowCount}"/>
			</td>
			</tr>
		</table>
		
		
		
	</body>
</html>
