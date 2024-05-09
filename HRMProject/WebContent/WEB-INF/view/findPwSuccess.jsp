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
비밀번호는 ${member.member_password} 입니다. <br/>
<a href="/HRMProject/">[메인화면으로 돌아가기]</a>
<a href="login.do">[로그인]</a>
</body>
</html>