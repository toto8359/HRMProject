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
		<form action="findId.do" method="post" class="module">
			<div class="page-answer">
				<h3>ID 찾기</h3>
				<h4>회원가입할 때 입력한 Email을 입력해주세요.</h4>
			</div>
			<p>
				<input type="text" name="member_email" placeholder='Email'
					class="login-input placeholder-gray" value="${param.member_email}" />
				<c:if test="${errors.member_email}">
					<div class="alert alert-error">이메일을 입력하세요.</div>
				</c:if>
				<c:if test="${errors.member_id}">
					<div class="alert alert-error">입력한 이메일 정보를 가진 아이디가 없습니다.</div>
				</c:if>
			</p>
			<div class="Loginbutton-wrapper">
				<input type="submit" value="찾기" class="btn btn-block btn-primary" />
			</div>
		</form>
	</div>
	<br />
</body>
</html>