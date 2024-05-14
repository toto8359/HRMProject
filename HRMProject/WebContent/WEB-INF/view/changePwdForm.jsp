<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更</title>
<!-- 암호변경 -->
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class="menu-left">
    <p style="color: black;">${authUser.member_name}様、ようこそ。</p>
    <!-- ~님, 안녕하세요. -->
	<div class="left-menu-btn">
		<a class="menu-item" href="/HRMProject">ホーム</a>
		<!-- HOME -->
	</div>
	<div class="left-menu-btn">
        <a class="menu-item" href="javascript:history.back()">戻る</a>
        <!-- 뒤로가기 -->
    </div>
        <div class="left-menu-btn">
        	<a  href="login.do">ログアウト</a>
        	<!-- 로그아웃 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="employeeInfoManage.do">社員情報管理</a>
        <!-- 사원정보관리 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="listEmployeeInfo.do">社員の現況</a>
        <!-- 사원현황 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="article/list.do">人事記録カード</a>
       <!-- 인사기록카드 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="article/list.do">在職証明書の発行</a>
        <!-- 재직증명서 발급 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="article/list.do">在職証明書発行台帳</a>
        <!-- 재직증명서 발급대장 -->
        </div>
</div>
	<div class="login-container">
	<!-- 	사용자의 입력 제출시(submit) changePwd.do로 데이터 전송(action) 전송 방식은 post -->
		<form action="changePwd.do" method="post" class="module">
			<div class="page-answer">
				<h3>パスワードを変更</h3>
				<!-- 비밀번호 변경 -->
			</div>
			<br>
			<p>
				<input type="password" name="curPwd" placeholder='Password'
					class="login-input placeholder-gray">
				<c:if test="${errors.curPwd}">
					<div class="alert alert-error">現在のパスワードを入力してください。</div>
					<!-- 현재 암호를 입력하세요. -->
				</c:if>
				<c:if test="${errors.badCurPwd}">
					<div class="alert alert-error">現在のパスワードが一致しません。</div>
					<!-- 현재 암호가 일치하지 않습니다. -->
				</c:if>
			</p>
			<p>
				<input type="password" name="newPwd" placeholder='New Password'
					class="login-input placeholder-gray">
				<c:if test="${errors.newPwd }">
					<div class="alert alert-error">新しいパスワードを入力してください。</div>
					<!-- 새 암호를 입력하세요. -->
				</c:if>
			</p>
			<p>
				<input type="password" name="newPwdConfirm"
					placeholder='Confirm New Password'
					class="login-input placeholder-gray">
				<c:if test="${errors.newPwdConfirm }">
					<div class="alert alert-error">パスワードを確認してください。</div>
					<!-- 암호 확인을 입력하세요. -->
				</c:if>
				<c:if test="${errors.passNotMatch }">
					<div class="alert alert-error">新しいパスワードと確認用パスワードが一致しません。</div>
					<!-- 새 암호와, 암호확인이 같지 않습니다. -->
				</c:if>
			</p>
			<div class="Loginbutton-wrapper">
				<input type="submit" value="パスワードを変更" class="btn btn-block btn-primary">
				<!-- 암호 변경 -->
			</div>
		</form>
	</div>