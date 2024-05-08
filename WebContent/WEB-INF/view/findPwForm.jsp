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
[비밀번호 찾기]<br/>
비밀번호를 찾고자 하는 아이디를 입력해주세요.
	<form action="findPw.do" method="post">
		<p>
			아이디:<input type="text" name="member_id" value="${param.member_id}" />
			<c:if test="${errors.member_id}">아이디를 입력해주세요.</c:if>
			<c:if test="${errors.noIdFound}">아이디가 없습니다.</c:if><br/>

			<c:if test="${answerBlank}">
				힌트: ${member_passwordHint}<br/>
				정답:<input type="text" name="member_passwordHintAnswer" value="${param.member_passwordHintAnswer}" />
				<c:if test="${errors.member_passwordHintAnswer}">정답을 입력하세요.</c:if>
				<c:if test="${errors.wrongAnswer}">정답이 틀렸습니다.</c:if>
			</c:if>
		</p>
		<input type="submit" value="찾기" />
	</form>
</body>
</html>