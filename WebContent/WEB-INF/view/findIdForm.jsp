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
[아이디 찾기]<br/>
회원가입할 때 입력한 이메일을 입력해주세요.
	<form action="findId.do" method="post">
		<p>
			이메일:<input type="text" name="member_email" value="${param.member_email}" />
			<c:if test="${errors.member_email}">이메일을 입력하세요.</c:if>
			<c:if test="${errors.member_id}">입력한 이메일 정보를 가진 아이디가 없습니다.</c:if>
		</p>
		<input type="submit" value="찾기" />
	</form>
<br/>
</body>
</html>