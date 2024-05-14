<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IDの検索完了</title>
<!-- 아이디 찾기 완료 -->
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>

<body>
<div class="menu-left">
	<div class="left-menu-btn">
		<a class="menu-item" href="/HRMProject">ホーム</a>
		<!-- 홈 -->
	</div>
	<div class="left-menu-btn">
        <a class="menu-item" href="javascript:history.back()">戻る</a>
        <!-- 뒤로가기 -->
    </div>
</div>
	<div class="module1">
		<h3>아이디는 ${member.member_id} です。</h3>
		<!-- 입니다. -->
	</div>
	<div class="login-container">
		<div class="btn-container1">
			<a href="/HRMProject/">メイン画面へ戻る</a>
			<!-- 메인화면으로 돌아가기 -->
			<a href="login.do">ログイン</a>
			<!-- 로그인 -->
			<a href="findPw.do">パスワード検索</a>
			<!-- 비밀번호 찾기 -->
		</div>
	</div>
</body>
</html>