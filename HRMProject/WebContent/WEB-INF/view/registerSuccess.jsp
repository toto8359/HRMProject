<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <p style="color: black;">${authUser.member_name}様、ようこそ。	</p>
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
        	<a  href="logout.do">ログアウトする</a>
        	<!-- 로그아웃 하기 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="employeeInfoManage.do">社員情報管理</a>
        <!-- 사원정보관리 -->
        <div class="left-menu-btn">
        <a class="menu-item" href="printVOE.do">在職証明書の発行</a>
        <!-- 재직증명서 발급 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="listVOEPrintRecord.do">在職証明書発行台帳</a>
        <!-- 재직증명서 발급대장 -->
        </div>
</div>
社員情報の入力が成功しました
<!-- 사원정보 입력에 성공했습니다. -->
<a href="register.do">[社員情報管理に戻る]</a>
<!-- 사원정보 관리로 돌아가기 -->
</body>
</html>