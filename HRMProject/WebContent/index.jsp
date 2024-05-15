<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メインページ</title>
<!-- 메인페이지 -->
<link rel="stylesheet" type="text/css" href="css/style.css">

</head>
<body>
<script>
    // 뒤로가기 버튼을 클릭했을 때 이전 페이지로 이동
    function goBack() {
        window.history.back();
    }
</script>

<div class="menu-left">
    <c:if test="${not empty authUser}">
        <p style="color: black;">${authUser.member_name}様、ようこそ。</p>
        <!-- 님, 환영합니다 -->
        <div class="left-menu-btn">
        	<a  href="logout.do">ログアウトする</a>
        	<!-- 로그아웃 하기 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="changePwd.do">パスワードを変更する</a>
        <!-- 암호 변경하기 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="employeeInfoManage.do">社員情報管理</a>
        <!-- 사원정보관리 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="printVOE.do">在職証明書の発行</a>
        <!-- 재직증명서 발급 한자안보임-->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="listVOEPrintRecord.do">在職証明書発行台帳</a>
        <!-- 재직증명서 발급대장 한자 안보이고 깨짐-->
        </div>
    </c:if>
    <c:if test="${empty authUser}">
    	<div class="left-menu-btn">
   		<a class="menu-item" href="login.do">ログインする</a>
   		<!-- 로그인 하기 -->
   		</div>
   		<div class="left-menu-btn">
        <a class="menu-item" href="join.do">会員登録する</a>
        <!-- 회원가입 하기 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="findId.do">ID検索</a>
        <!-- ID 찾기 -->
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="findPw.do">PW検索</a>
        <!-- PW 찾기 -->
        </div>
    </c:if>
</div>
</body> 
</html>
