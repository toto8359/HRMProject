<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="join.do" method="POST">
		<p>
			아이디:<br />
			<input type="text" name="member_id" value="${param.member_id}">
			<c:if test="${errors.member_id}">아이디를 입력하세요.</c:if>
			<c:if test="${errors.duplicateId}">이미 사용중인 아이디입니다.</c:if>
		</p>
		<p>
			이름:<br />
			<input type="text" name="member_name" value="${param.member_name}">
			<c:if test="${errors.member_name}">이름을 입력하세요.</c:if>
		</p>
		<p>
			암호:<br />
			<input type="password" name="member_password">
			<c:if test="${errors.member_password}">암호를 입력하세요.</c:if>
		</p>
		<p>
			암호확인:<br />
			<input type="password" name="confirmPassword">
			<c:if test="${errors.confirmPassword}">암호확인을 입력하세요.</c:if>
			<c:if test="${errors.notMatch}">암호와 암호확인이 일치하지 않습니다.</c:if>
		</p>
		<p>
			암호힌트:<br />
			<input type="text" name="member_passwordHint" value="${param.member_passwordHint}">
			<c:if test="${errors.member_name}">암호힌트를 입력하세요.</c:if>
		</p>
		<p>
			암호힌트 정답:<br />
			<input type="text" name="member_passwordHintAnswer" value="${param.member_passwordHintAnswer}">
			<c:if test="${errors.member_name}">암호힌트 정답을 입력하세요.</c:if>
		</p>
		
		<p>
			이메일:<br />
			<input type="text" name="member_email" value="${param.member_email}">
			<c:if test="${errors.member_email}">이메일을 입력하세요.</c:if>
			<c:if test="${errors.duplicateEmail}">이미 사용중인 이메일입니다.</c:if>
		</p>

		<input type="submit" value="가입">
	</form>
</body>
</html>