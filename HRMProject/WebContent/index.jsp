<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판 예제</title>
</head>
<body>
<div align="center">
<c:if test="${!empty authUser}">
${authUser.member_name}님,안녕하세요.<br/>
<a href="logout.do">[로그아웃 하기]</a>
<a href="changePwd.do">[암호 변경하기]</a>
<a href="employeeInfoManage.do">[사원정보관리]</a> 
<a href="listEmployeeInfo.do">[사원현황]</a>
<a href="article/list.do">[인사기록카드]</a>
<a href="article/list.do">[제직증명서 발급]</a>
<a href="article/list.do">[제직증명서 발급대장]</a>
</c:if>

<c:if test="${empty authUser}">
<a href="login.do">[로그인 하기]</a>
<a href="join.do">[회원가입 하기]</a>
<a href="findId.do">[ID 찾기]</a>
<a href="findPw.do">[PW 찾기]</a>
</c:if>
</div>

</body>
</html>