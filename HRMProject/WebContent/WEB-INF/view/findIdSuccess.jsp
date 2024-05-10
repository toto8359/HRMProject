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
<div class="menu-left">
	<div class="left-menu-btn">
		<a class="menu-item" href="/HRMProject">HOME</a>
	</div>
	<div class="left-menu-btn">
        <a class="menu-item" href="javascript:history.back()">뒤로가기</a>
    </div>
</div>
	<div class="module1">
		<h3>아이디는 ${member.member_id} 입니다.</h3>
	</div>
	<div class="login-container">
		<div class="btn-container1">
			<a href="/HRMProject/">메인화면으로 돌아가기</a>
			<a href="login.do">로그인</a>
			<a href="findPw.do">비밀번호 찾기</a>
		</div>
	</div>
</body>
</html>