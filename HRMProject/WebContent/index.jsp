<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판 예제</title>
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
        <p style="color: black;">${authUser.member_name}님, 안녕하세요.</p>
        <div class="left-menu-btn">
        	<a  href="login.do">로그아웃 하기</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="changePwd.do">암호 변경하기</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="employeeInfoManage.do">사원정보관리</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="listEmployeeInfo.do">사원현황</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="article/list.do">인사기록카드</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="article/list.do">제직증명서 발급</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="article/list.do">제직증명서 발급대장</a>
        </div>
    </c:if>
    <c:if test="${empty authUser}">
    	<div class="left-menu-btn">
   		<a class="menu-item" href="login.do">로그인 하기</a>
   		</div>
   		<div class="left-menu-btn">
        <a class="menu-item" href="join.do">회원가입 하기</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="findId.do">ID 찾기</a>
        </div>
        <div class="left-menu-btn">
        <a class="menu-item" href="findPw.do">PW 찾기</a>
        </div>
    </c:if>
</div>
</body>
</html>
