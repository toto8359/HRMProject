<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>パスワード検索</title>
<!-- 비밀번호 찾기 -->
<link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class="menu-left">
	<div class="left-menu-btn">
		<a class="menu-item" href="/HRMProject">HOME</a>
	</div>
	<div class="left-menu-btn">
        <a class="menu-item" href="javascript:history.back()">戻る</a>
        <!-- 뒤로가기 -->
    </div>
</div>
	<div class="login-container">
		<form action="findPw.do" method="post" class="module">
			<div class="page-answer">
				<h3>パスワード検索</h3>
				<!-- 비밀번호 찾기 -->
				<h4>パスワードを忘れたアカウントのIDを入力してください。</h4>
				<!-- 비밀번호를 찾고자 하는 아이디를 입력해주세요. -->
			</div>
			<p>
				<input type="text" name="member_id" placeholder='ID'
					class="login-input placeholder-gray" value="${param.member_id}" />
				<c:if test="${errors.member_id}">
					<div class="alert alert-error">IDを入力してください。</div>
					<!-- 아이디를 입력해주세요. -->
				</c:if>
				<c:if test="${errors.noIdFound}">
					<div class="alert alert-error">IDが見つかりません。</div>
					<!-- 아이디가 없습니다. -->
				</c:if>
				<br />

				<c:if test="${answerBlank}">
				ヒント: ${member_passwordHint}<br /></br>
				<!-- 힌트 -->
					<input type="text" name="member_passwordHintAnswer"
						placeholder='member_passwordHintAnswer'
						class="login-input placeholder-gray"
						value="${param.member_passwordHintAnswer}" />
					<c:if test="${errors.member_passwordHintAnswer}">
						<div class="alert alert-error">答えを入力してください。</div>
						<!-- 정답을 입력하세요. -->
					</c:if>
					<c:if test="${errors.wrongAnswer}">
						<div class="alert alert-error">答えが違います。</div>
						<!-- 정답이 틀렸습니다. -->
					</c:if>
				</c:if>
			</p>
			<div class="Loginbutton-wrapper">
				<input type="submit" value="検索" class="btn btn-block btn-primary" />
				<!-- 찾기 -->
			</div>
		</form>
	</div>
</body>
</html>