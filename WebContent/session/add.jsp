<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>회원 등록</h1>
 <form method = "post" action= "${root }/main?action=addform">
        <input type="text" name="memName" placeholder="Name"/>
        <input type="email" name="memEmail" placeholder="Email"/>
        <input type="password"name="memPwd"  placeholder="Password"/><br>
        <input type = "submit" class="control-button up" placeholder="Sign Up"/>                
      </form>
</body>
</html>