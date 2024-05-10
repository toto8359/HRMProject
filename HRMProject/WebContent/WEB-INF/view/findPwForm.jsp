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
	<div class="login-container">
		<form action="findPw.do" method="post" class="module">
			<div class="page-answer">
				<h3>비밀번호 찾기</h3>
				<h4>비밀번호를 찾고자 하는 아이디를 입력해주세요.</h4>
			</div>
			<p>
				<input type="text" name="member_id" placeholder='ID'
					class="login-input placeholder-gray" value="${param.member_id}" />
				<c:if test="${errors.member_id}">
					<div class="alert alert-error">아이디를 입력해주세요.</div>
				</c:if>
				<c:if test="${errors.noIdFound}">
					<div class="alert alert-error">아이디가 없습니다.</div>
				</c:if>
				<br />

				<c:if test="${answerBlank}">
				힌트: ${member_passwordHint}<br /></br>
					<input type="text" name="member_passwordHintAnswer"
						placeholder='member_passwordHintAnswer'
						class="login-input placeholder-gray"
						value="${param.member_passwordHintAnswer}" />
					<c:if test="${errors.member_passwordHintAnswer}">
						<div class="alert alert-error">정답을 입력하세요.</div>
					</c:if>
					<c:if test="${errors.wrongAnswer}">
						<div class="alert alert-error">정답이 틀렸습니다.</div>
					</c:if>
				</c:if>
			</p>
			<div class="Loginbutton-wrapper">
				<input type="submit" value="찾기" class="btn btn-block btn-primary" />
			</div>
		</form>
	</div>
</body>
</html>