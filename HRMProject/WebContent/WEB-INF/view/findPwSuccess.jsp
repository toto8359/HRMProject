<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

	<div class="module1">
		<h3>비밀번호는 ${member.member_password} 입니다.</h3>
	</div>

	<div class="login-container">
		<div class="btn-container1">
			<a href="/HRMProject/">메인화면으로 돌아가기</a> 
			<a href="login.do">로그인</a>
		</div>
	</div>
</body>
</html>