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
아이디는 ${member.member_id} 입니다. <br/>
<a href="/board/">[메인화면으로 돌아가기]</a>
<a href="login.do">[로그인]</a>
<a href="findPw.do">[비밀번호 찾기]</a>
</body>
</html>