<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"
	scope="session" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script type="text/javascript" src="/resource/js/bootstrap.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3 align="center">ADMINISTRAITOR</h3>
	<c:if test="${msg != null }">
		<h5 align="center">${msg}</h5>
	</c:if>
	<div class="container" id="login" align="center">
		<div class="col-lg-6" align="center"
			style="border-style: solid; border-color: black; padding: 10px; margin: 15px;">
			<p>회원 관리</p>
			<a href="${root}/main?action=add" style="margin: 10px;">회원 등록</a> 
			<a href="${root}/main?action=list" style="margin: 10px;">회원 검색</a> 
			<a href="${root}/main?action=delete" style="margin: 10px;">회원 삭제</a>
		</div>
	</div>

</body>
</html>