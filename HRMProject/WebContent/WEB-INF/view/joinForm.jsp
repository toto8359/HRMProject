<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員登録フォーム</title>
<!-- 회원가입 양식 -->
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
	<div class="login-container">
		<form action="join.do" method="POST" class="module">
			<div class="page-answer">
				<h3>会員登録ページ</h3>
				<!-- 회원가입 페이지 -->
			</div>
			<br>
			<p>
				<input type="text" name="member_id" placeholder='ID'
					class="login-input placeholder-gray" value="${param.member_id}">
				<c:if test="${errors.member_id}">
					<div class="alert alert-error">IDを入力してください。</div>
					<!-- 아이디를 입력하세요. -->
				</c:if>
				<c:if test="${errors.duplicateId}">
					<div class="alert alert-error">既に使用されているIDです。</div>
					<!-- 이미 사용중인 아이디입니다. -->
				</c:if>
			</p>
			<p>
				<input type="text" name="member_name" placeholder='Name'
					class="login-input placeholder-gray" value="${param.member_name}">
				<c:if test="${errors.member_name}">
					<div class="alert alert-error">名前を入力してください。</div>
					<!-- 이름을 입력하세요. -->
				</c:if>
			</p>
			<p>
				<input type="password" name="member_password"
					class="login-input placeholder-gray" placeholder='Password'>
				<c:if test="${errors.member_password}">
					<div class="alert alert-error">パスワードを入力してください。</div>
					<!-- 암호를 입력하세요. -->
				</c:if>
			</p>
			<p>
				<input type="password" name="confirmPassword"
					class="login-input placeholder-gray" placeholder='Password Confirm'>
				<c:if test="${errors.confirmPassword}">
					<div class="alert alert-error">パスワード確認を入力してください。</div>
					<!-- 암호확인을 입력하세요. -->
				</c:if>
				<c:if test="${errors.notMatch}">
					<div class="alert alert-error">パスワードとパスワード確認が一致しません。</div>
					<!-- 암호와 암호확인이 일치하지 않습니다. -->
				</c:if>
			</p>
			<p>
				<input type="text" name="member_passwordHint"
					class="login-input placeholder-gray" placeholder='Password Hint'
					value="${param.member_passwordHint}">
				<c:if test="${errors.member_name}">
					<div class="alert alert-error">パスワードのヒントを入力してください。</div>
					<!-- 암호힌트를 입력하세요. -->
				</c:if>
			</p>
			<p>
				<input type="text" name="member_passwordHintAnswer"
					class="login-input placeholder-gray" placeholder='Password Answer'
					value="${param.member_passwordHintAnswer}">
				<c:if test="${errors.member_name}">
					<div class="alert alert-error">パスワードのヒントの回答を入力してください。</div>
					<!-- 암호힌트 정답을 입력하세요. -->
				</c:if>
			</p>

			<p>
				<input type="text" name="member_email"
					class="login-input placeholder-gray" placeholder='Email'
					value="${param.member_email}">
				<c:if test="${errors.member_email}">
					<div class="alert alert-error">メールアドレスを入力してください。</div>
					<!-- 이메일을 입력하세요. -->
				</c:if>
				<c:if test="${errors.duplicateEmail}">
					<div class="alert alert-error">既に使用されているメールアドレスです。</div>
					<!-- 이미 사용중인 이메일입니다. -->
				</c:if>
			</p>
			<div class="Loginbutton-wrapper">
				<input type="submit" value="Join" class="btn btn-block btn-primary">
			</div>
		</form>
	</div>


</body>
</html>