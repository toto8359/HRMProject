<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IDを検索する</title>
<!-- 아이디 찾기 -->
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
		<form action="findId.do" method="post" class="module">
			<div class="page-answer">
				<h3>IDを検索する</h3>
				<!-- ID찾기 -->
				<h4>会員登録時に入力したメールを入力してください。</h4>
				<!-- 회원가입할 때 입력한 Email을 입력해주세요. -->
			</div>
			<p>
				<input type="text" name="member_email" placeholder='Email'
					class="login-input placeholder-gray" value="${param.member_email}" />
				<c:if test="${errors.member_email}">
					<div class="alert alert-error">メールアドレスを入力してください。</div>
					<!-- 이메일을 입력하세요. -->
				</c:if>
				<c:if test="${errors.member_id}">
					<div class="alert alert-error">入力されたメールアドレス情報を持つIDはありません。</div>
					<!-- 입력한 이메일 정보를 가진 아이디가 없습니다. -->
				</c:if>
			</p>
			<div class="Loginbutton-wrapper">
				<input type="submit" value="検索" class="btn btn-block btn-primary" />
				<!-- 찾기 -->
			</div>
		</form>
	<br />
</body>
</html>