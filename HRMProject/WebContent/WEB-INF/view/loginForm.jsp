<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<!-- 로그인 -->
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<script>
    // 뒤로가기 버튼을 클릭했을 때 이전 페이지로 이동
    /* 戻るボタンをクリックした時、前のページに戻る */
    function goBack() {
        window.history.back();
    }
</script>
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
	<div class="login-container">
		<!-- 페이지를 감싸는 컨테이너 -->
		<!-- ページを包むコンテナ -->
		<form action="login.do" method="post" class="module">
			<c:if test="${errors.idOrPwNotMatch}">
				<div class="alert alert-error">>IDとパスワードが一致しません。</div>
				<!-- 아이디와 암호가 일치하지 않습니다. -->
			</c:if>
			<div class="page-answer">
				<h3>ログインページ</h3>
				<!-- 로그인 페이지 -->
			</div>
			<br>
			<p>
				<input type="text" name="id" value="${param.id}"
					placeholder='ID' class="login-input placeholder-gray" />
				<c:if test="${errors.id}">
					<div class="alert alert-error">IDを入力してください。</div>
					<!-- ID를 입력하세요. -->
				</c:if>
			</p>
			<p>
				<input type="password" name="password" placeholder='Password'
					class="login-input placeholder-gray" />
				<c:if test="${errors.password}">
					<div class="alert alert-error">パスワードを入力してください。</div>
					<!-- 암호를 입력하세요. -->
				</c:if>
			</p>
			<div class="Loginbutton-wrapper">
				<input type="submit" value="ログイン" class="btn btn-block btn-primary" />
				<!-- 로그인 -->
			</div>

			<div class="btn-container">
				<!-- 링크를 감싸는 컨테이너 -->
				<!-- リンクを包むコンテナ -->
				<!--  -->
				<a href="findId.do">IDを検索</a> 
				<!-- ID 찾기 -->
				<a href="findPw.do">パスワードを検索</a>
				<!-- PW 찾기 -->
			</div>
		</form>
	</div>

</body>
</html>
