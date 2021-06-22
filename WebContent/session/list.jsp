<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h2>회원 목록</h2>
		<table class="table">
			<thead>
				<tr>
					<th>회원 이름</th>
					<th>이메일</th>
					<th>패스워드</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${member}" varStatus="status">
					<c:if test="${(status.index % 2) eq 0}">
						<tr class="success">
							<td>${member.memName}</td>
							<td>${member.memEmail}</td>
							<td>${member.memPwd}</td>
						</tr>
					</c:if>
					
					<c:if test="${(status.index % 2) eq 1}">
						<tr class="info">
							<td>${member.memName}</td>
							<td>${member.memEmail}</td>
							<td>${member.memPwd}</td>
						</tr>
					</c:if>
				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>