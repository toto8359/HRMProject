<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
	<div class="login-container">
		<!-- 페이지를 감싸는 컨테이너 -->
		<form action="login.do" method="post" class="module">
			<c:if test="${errors.idOrPwNotMatch}">
				<div class="alert alert-error">아이디와 암호가 일치하지 않습니다.</div>
			</c:if>
			<div class="page-answer">
				<h3>로그인 페이지</h3>
			</div>
			<br>
			<p>
				<input type="text" name="id" value="${param.id}"
					placeholder='ID' class="login-input placeholder-gray" />
				<c:if test="${errors.id}">
					<div class="alert alert-error">ID를 입력하세요.</div>
				</c:if>
			</p>
			<p>
				<input type="password" name="password" placeholder='Password'
					class="login-input placeholder-gray" />
				<c:if test="${errors.password}">
					<div class="alert alert-error">암호를 입력하세요.</div>
				</c:if>
			</p>
			<div class="Loginbutton-wrapper">
				<input type="submit" value="로그인" class="btn btn-block btn-primary" />
			</div>

			<div class="btn-container">
				<!-- 링크를 감싸는 컨테이너 -->
				<a href="findId.do">ID 찾기</a> 
				<a href="findPw.do">PW 찾기</a>
			</div>
		</form>
	</div>

</body>
</html>
