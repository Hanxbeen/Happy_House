<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <h1>삭제를 위한 회원 이름을 입력하세요</h1>
   <form method="post" action="${root}/main?action=deleteform" >
      <input  type="text" name="memName" placeholder="회원 이름" style="margin: 10px;" ><br>
      <input type="submit" value="삭제" style="margin: 10px;">
   </form>
</body>
</html>