<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style>
.table-container {
	display: inline-block;
	width: auto; /* 테이블 컨테이너의 너비 조정 */
	margin-right: 5%; /* 테이블 간격 조정 */
	vertical-align: top; /* 테이블을 상단으로 정렬 */
}

.custom-table {
	width: 100%;
	border-collapse: collapse;
	border: 1px solid #ddd;
}

.custom-table th, .custom-table td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

.custom-table a {
	color: inherit; /* 링크의 색상을 부모 요소인 테이블의 글자색과 동일하게 지정 */
	text-decoration: none; /* 링크의 밑줄 제거 */
}

.custom-table th {
	background-color: #808080;
}

.custom-table tbody tr:nth-child(even) {
	background-color: #808080;
}

/* 버튼 스타일 */
.button-form {
	margin-top: 10px; /* 버튼 간격을 조절합니다 */
}

.custom-button {
	background-color: #098cc8;
	color: white;
	padding: 10px 20px;
	border: none;
	cursor: pointer;
	border-radius: 5px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin-right: 10px; /* 버튼 간격을 조절합니다 */
}

.custom-button:hover {
	background-color: #21b0f1;
}

.form-table-spacing {
	margin-top: 50px; /* 원하는 만큼의 간격을 설정하세요 */
}

.form-below-table {
	margin-top: 20px; /* 테이블 아래에 간격을 조정하세요 */
}
</style>

</head>
<body>


	<div class="menu-left">
		<p style="color: black;">${authUser.member_name}님,안녕하세요.</p>
		<div class="left-menu-btn">
			<a class="menu-item" href="/HRMProject">HOME</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="javascript:history.back()">뒤로가기</a>
		</div>
		<div class="left-menu-btn">
			<a href="logout.do">로그아웃 하기</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="changePwd.do">암호 변경하기</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="listVOEPrintRecord.do">사원정보관리</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="listEmployeeInfo.do">사원현황</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="article/list.do">인사기록카드</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="printVOE.do">제직증명서 발급</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="listVOEPrintRecord.do">제직증명서 발급대장</a>
		</div>
	</div>

	<%-- 	<!-- 안내창 -->
	<c:if test="${!empty employeePsnl_kname}">
		<c:if test="${modifySuccess}">
			사원 ${employeePsnl_kname}의 사원정보 수정을 완료했습니다.
		</c:if>
		<c:if test="${joinSuccess}">
			사원 ${employeePsnl_kname}의 등록을 완료했습니다.
		</c:if>
		<c:if test="${deleteSuccess}">
			사원 ${employeePsnl_kname}의 사원정보 삭제를 완료했습니다.
		</c:if>
	</c:if><br/> --%>

	<!-- 재직증명서 출력대장 -->
	<div class="table-container">
		<div class="page-answer">
			<h3>재직증명서 출력대장</h3>
		</div>
		<table class="custom-table">
			<!-- custom-table 클래스 추가 -->
			<thead>
				<tr>
					<th>재직증명서 번호</th>
					<th>발급일시</th>
					<th>사원번호</th>
					<th>국문성명</th>
					<th>고용형태</th>
					<th>부서</th>
					<th>직급</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${voePrintRecordPage.hasNoVOERecordList()}">
					<tr>
						<td colspan="6">게시글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="voePrintRecordPage"
					items="${voePrintRecordPage.content}">
					<tr>
						<td>${voePrintRecordPage.recordNumber}</td>
						<td>${voePrintRecordPage.printDate}</td>
						<td>${voePrintRecordPage.employeeNum}</td>
						<td>${voePrintRecordPage.employeePsnl_kname}</td>
						<td>${voePrintRecordPage.employeeEply_employType}</td>
						<td>${voePrintRecordPage.employeeEply_depart}</td>
						<td>${voePrintRecordPage.employeeEply_position}</td>
					</tr>
				</c:forEach>
				<c:if test="${voePrintRecordPage.hasVOERecordList()}">
					<tr>
						<td colspan="6"><c:if
								test="${voePrintRecordPage.startPage > 5}">
								<a
									href="listVOEPrintRecord.do?pageNo=${voePrintRecordPage.startPage - 5}">[이전]</a>
								<!-- custom-button 클래스 추가 -->
							</c:if> <c:forEach var="pNo" begin="${voePrintRecordPage.startPage}"
								end="${voePrintRecordPage.endPage}">
								<a href="listVOEPrintRecord.do?pageNo=${pNo}">[${pNo}]</a>
								<!-- custom-button 클래스 추가 -->
							</c:forEach> <c:if
								test="${voePrintRecordPage.endPage < voePrintRecordPage.totalPages}">
								<a
									href="listVOEPrintRecord.do?pageNo=${voePrintRecordPage.startPage + 5}">[다음]</a>
								<!-- custom-button 클래스 추가 -->
							</c:if></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html> 