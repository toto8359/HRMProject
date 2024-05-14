<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録完了</title>
<!-- 회원가입 완료 -->
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
	<h3>${param.member_name}様、会員登録が完了しました。</h3>
	<!-- 님,회원가입에 성공했습니다. -->
</div>

<div class="login-container">
	<div class="btn-container1">
		<a href="/HRMProject/">ホーム画面</a>
		<!-- 메인화면 -->
	</div>
</div>
</body>
</html>