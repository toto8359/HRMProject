<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호 변경</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
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
	<!-- 	사용자의 입력 제출시(submit) changePwd.do로 데이터 전송(action) 전송 방식은 post -->
		<form action="changePwd.do" method="post" class="module">
			<div class="page-answer">
				<h3>비밀번호 변경</h3>
			</div>
			<br>
			<p>
				<input type="password" name="curPwd" placeholder='Password'
					class="login-input placeholder-gray">
				<c:if test="${errors.curPwd}">
					<div class="alert alert-error">현재 암호를 입력하세요.</div>
				</c:if>
				<c:if test="${errors.badCurPwd}">
					<div class="alert alert-error">현재 암호가 일치하지 않습니다</div>
				</c:if>
			</p>
			<p>
				<input type="password" name="newPwd" placeholder='New Password'
					class="login-input placeholder-gray">
				<c:if test="${errors.newPwd }">
					<div class="alert alert-error">새 암호를 입력하세요.</div>
				</c:if>
			</p>
			<p>
				<input type="password" name="newPwdConfirm"
					placeholder='Confirm New Password'
					class="login-input placeholder-gray">
				<c:if test="${errors.newPwdConfirm }">
					<div class="alert alert-error">암호 확인을 입력하세요.</div>
				</c:if>
				<c:if test="${errors.passNotMatch }">
					<div class="alert alert-error">새 암호와, 암호확인이 같지 않습니다.</div>
				</c:if>
			</p>
			<div class="Loginbutton-wrapper">
				<input type="submit" value="암호 변경" class="btn btn-block btn-primary">
			</div>
		</form>
	</div>