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
	width: auto;
	margin-right: 5%;
	vertical-align: top; 
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
	color: inherit;
	text-decoration: none;
}

.custom-table th {
	background-color: #808080;
}

.custom-table tbody tr:nth-child(even) {
	background-color: #808080;
}

/* 버튼 스타일 */
.button-form {
	margin-top: 10px;
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
	margin-right: 10px; 
}

.custom-button:hover {
	background-color: #21b0f1;
}

.form-table-spacing {
	margin-top: 50px;
}

.form-below-table {
	margin-top: 20px; 
}
</style>

</head>
<body>


	<div class="menu-left">
		<p style="color: black;">${authUser.member_name}様、ようこそ。</p>
		<div class="left-menu-btn">
			<a class="menu-item" href="/HRMProject">ホーム</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="javascript:history.back()">戻る</a>
		</div>
		<div class="left-menu-btn">
			<a href="logout.do">ログアウトする</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="changePwd.do">パスワードを変更する</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="listVOEPrintRecord.do">社員情報管理</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="printVOE.do">在職証明書の発行</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="listVOEPrintRecord.do">在職証明書発行台帳</a>
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
			<h3>在職証明書出力台帳</h3>
		</div>
		<table class="custom-table">
			<!-- custom-table 클래스 추가 -->
			<thead>
				<tr>
					<th>在職証明書番号</th>
					<th>発行日時</th>
					<th>社員番号</th>
					<th>국문성명</th>
					<th>国文声明</th>
					<th>部署</th>
					<th>職級</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${voePrintRecordPage.hasNoVOERecordList()}">
					<tr>
						<td colspan="7">投稿がありません。</td>
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
						<td colspan="7"><c:if
								test="${voePrintRecordPage.startPage > 5}">
								<a
									href="listVOEPrintRecord.do?pageNo=${voePrintRecordPage.startPage - 5}">[前へ]</a>
								<!-- custom-button 클래스 추가 -->
							</c:if> <c:forEach var="pNo" begin="${voePrintRecordPage.startPage}"
								end="${voePrintRecordPage.endPage}">
								<a href="listVOEPrintRecord.do?pageNo=${pNo}">[${pNo}]</a>
								<!-- custom-button 클래스 추가 -->
							</c:forEach> <c:if
								test="${voePrintRecordPage.endPage < voePrintRecordPage.totalPages}">
								<a
									href="listVOEPrintRecord.do?pageNo=${voePrintRecordPage.startPage + 5}">[次へ]</a>
								<!-- custom-button 클래스 추가 -->
							</c:if></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
</body>
</html> 