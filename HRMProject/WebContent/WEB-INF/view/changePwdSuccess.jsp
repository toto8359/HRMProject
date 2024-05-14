<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード変更が完了しました.</title>
<!-- 암호변경 완료 -->
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class="module1">
	<h3>パスワードを変更しました.</h3>
	<!-- 암호를 변경했습니다. -->
</div>
<div class="menu-left">
    <p style="color: black;">${authUser.member_name}様、ようこそ。</p>
	<!-- 님, 안녕하세요. -->
	<div class="left-menu-btn">
		<a class="menu-item" href="/HRMProject">ホーム</a>
		<!-- 홈 -->
	</div>
	<div class="left-menu-btn">
        <a class="menu-item" href="javascript:history.back()">戻る</a>
   		<!-- 뒤로가기 -->
    </div>
        <div class="left-menu-btn">
        	<a  href="login.do">ログアウトする</a>
        	<!-- 로그아웃 하기 -->
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
        <!-- 재직증명서 발급 한자오류-->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="article/list.do">在職証明書発行台帳</a>
        <!-- 재직증명서 발급대장 한자오류-->
        </div>
</div>
<div class="login-container">
	<div class="btn-container1">
		<a href="/HRMProject/">ホーム画面</a>
		<!-- 메인화면 -->
	</div>
</div>
</body>
</html>