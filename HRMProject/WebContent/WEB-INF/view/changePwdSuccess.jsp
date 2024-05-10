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
<div class="module1">
	<h3>암호를 변경했습니다.</h3>
</div>
<div class="menu-left">
    <p style="color: black;">${authUser.member_name}님, 안녕하세요.</p>
	<div class="left-menu-btn">
		<a class="menu-item" href="/HRMProject">HOME</a>
	</div>
	<div class="left-menu-btn">
        <a class="menu-item" href="javascript:history.back()">뒤로가기</a>
    </div>
        <div class="left-menu-btn">
        	<a  href="login.do">로그아웃 하기</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="employeeInfoManage.do">사원정보관리</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="listEmployeeInfo.do">사원현황</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="article/list.do">인사기록카드</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="article/list.do">제직증명서 발급</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="article/list.do">제직증명서 발급대장</a>
        </div>
</div>
<div class="login-container">
	<div class="btn-container1">
		<a href="/HRMProject/">메인화면</a>
	</div>
</div>
</body>
</html>