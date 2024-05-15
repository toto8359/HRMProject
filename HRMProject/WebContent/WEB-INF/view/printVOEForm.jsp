<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<a class="menu-item" href="employeeInfoManage.do">사원정보관리</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="printVOE.do">제직증명서 발급</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="listVOEPrintRecord.do">제직증명서 발급대장</a>
		</div>
	</div>

	<!-- 사원정보목록창 -->
	<div class="table-container">
		<div class="page-answer">
			<h3>사원 목록</h3>
		</div>
		<table class="custom-table">
			<!-- custom-table 클래스 추가 -->
			<thead>
				<tr>
					<th>사원번호</th>
					<th>부서</th>
					<th>직급</th>
					<th>이름</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${employeeListPagePart.hasNoEmployeeList()}">
					<tr>
						<td colspan="4">게시글이 없습니다.</td>
					</tr>
				</c:if>
				<c:forEach var="employeeEply"
					items="${employeeListPagePart.content}">
					<tr>
						<td>${employeeEply.employeeNum}</td>
						<td>${employeeEply.employeeEply_depart}</td>
						<td>${employeeEply.employeeEply_position}</td>
						<td><a
							href="printVOE.do?employeeNum=${employeeEply.employeeNum}&pageNo=${employeeListPagePart.currentPage}">
								<c:out value="${employeeEply.employeePsnl_kname}" />
						</a></td>
					</tr>
				</c:forEach>
				<c:if test="${employeeListPagePart.hasEmployeeList()}">
					<tr>
						<td colspan="4"><c:if
								test="${employeeListPage.startPage > 5}">
								<a
									href="printVOE.do?pageNo=${employeeListPagePart.startPage - 5}">[이전]</a>
								<!-- custom-button 클래스 추가 -->
							</c:if> <c:forEach var="pNo" begin="${employeeListPagePart.startPage}"
								end="${employeeListPagePart.endPage}">
								<a href="printVOE.do?pageNo=${pNo}">[${pNo}]</a>
								<!-- custom-button 클래스 추가 -->
							</c:forEach> <c:if
								test="${employeeListPagePart.endPage < employeeListPagePart.totalPages}">
								<a
									href="printVOE.do?pageNo=${employeeListPagePart.startPage + 5}">[다음]</a>
								<!-- custom-button 클래스 추가 -->
							</c:if></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>

	<!-- 사원정보창 -->
	<c:if test="${readInfo}">
		<div class="table-container">
			<div class="page-answer">
				<h3>${infoRequestAll.employeePsnl_kname}님의사원정보</h3>
			</div>
			<table class="custom-table" id="print-table">
				<thead>
					<tr>
						<th>항목</th>
						<th>정보</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>사원번호</td>
						<td>${infoRequestAll.employeeNum}</td>
					</tr>
					<tr>
						<td>국문이름</td>
						<td>${infoRequestAll.employeePsnl_kname}</td>
					</tr>
					<tr>
						<td>영문이름</td>
						<td>${infoRequestAll.employeePsnl_ename}</td>
					</tr>
					<tr>
						<td>내국인 외국인</td>
						<td>${infoRequestAll.employeePsnl_isForeigner}</td>
					</tr>
					<tr>
						<td>주민번호</td>
						<td>${infoRequestAll.employeePsnl_residentNumber}</td>
					</tr>
					<tr>
						<td>주소</td>
						<td>${infoRequestAll.employeePsnl_adress}</td>
					</tr>
					<tr>
						<td>전화번호</td>
						<td>${infoRequestAll.employeePsnl_phoneNumber}</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td>${infoRequestAll.employeePsnl_email}</td>
					</tr>
					<tr>
						<td>SNS계정</td>
						<td>${infoRequestAll.employeePsnl_sns}</td>
					</tr>
					<tr>
						<td>고용 형태</td>
						<td>${infoRequestAll.employeeEply_employType}</td>
					</tr>
					<tr>
						<td>부서</td>
						<td>${infoRequestAll.employeeEply_depart}</td>
					</tr>
					<tr>
						<td>직급</td>
						<td>${infoRequestAll.employeeEply_position}</td>
					</tr>
					<tr>
						<td>입사날짜</td>
						<td>${infoRequestAll.employeeEply_join}</td>
					</tr>
					<tr>
						<td>퇴사날짜</td>
						<td>${infoRequestAll.employeeEply_resignation}</td>
					</tr>
				</tbody>
			</table>
			<div class="div-inline">
				<form action="printVOE.do" method="POST" class="button-form">
						<input type="hidden" name="employeeNumPrint"
							value="${infoRequestAll.employeeNum}" />
						<input type="hidden" name="pageNo"
							value="${employeeListPagePart.currentPage}" />
						<input type="submit" value="인쇄" class="custom-button" onclick="printTable()">
				</form>
			</div>
		</div>
	</c:if>
	
<script>
function printTable() {
    // 테이블 요소와 스타일 요소를 가져옵니다.
    var table = document.getElementById("print-table"); // id값이 print-table인 테이블을 가져옵니다.
    var styles = document.createElement('style'); // 스타일 요소를 생성합니다.

    // 테이블을 적용할 스타일을 추가합니다.
    styles.innerHTML = `
        .tg  {border-collapse:collapse;border-spacing:0;}
        .tg td{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            overflow:hidden;padding:10px 5px;word-break:normal;}
        .tg th{border-color:black;border-style:solid;border-width:1px;font-family:Arial, sans-serif;font-size:14px;
            font-weight:normal;overflow:hidden;padding:10px 5px;word-break:normal;}
        .tg .tg-9wq8{border-color:inherit;text-align:center;vertical-align:middle}
        .tg .tg-7ofv{border-color:inherit;font-size:24px;text-align:center;vertical-align:middle}
        .tg .tg-nrix{text-align:center;vertical-align:middle}
    `;

    // 새 창을 열고 스타일과 테이블을 새 창에 쓰기합니다.
    var newWin = window.open('about:blank', '_blank'); // about:blank 주소로 새 탭을 엽니다.
    newWin.document.write('<html><head><title>Print Preview</title></head><body>'); // 새 탭에 문서 시작을 작성합니다.
    newWin.document.write('<style>' + styles.innerHTML + '</style>'); // 스타일을 작성합니다.
    newWin.document.write('<table class="tg" style="undefined;table-layout: fixed; width: 564px">'); // 테이블을 시작합니다.
    newWin.document.write('<colgroup>'); // 열 그룹을 시작합니다.

    // 열 너비를 정의합니다.
    newWin.document.write('<col style="width: 67px">');
    newWin.document.write('<col style="width: 70px">');
    newWin.document.write('<col style="width: 79px">');
    newWin.document.write('<col style="width: 109px">');
    newWin.document.write('<col style="width: 95px">');
    newWin.document.write('<col style="width: 144px">');

    newWin.document.write('</colgroup>'); // 열 그룹을 닫습니다.
    newWin.document.write('<thead>'); // 머리말을 시작합니다.
    newWin.document.write('<tr>'); // 행을 시작합니다.
    newWin.document.write('<th class="tg-7ofv" colspan="6"><span style="font-weight:bold">재 직 증 명 서</span></th>'); // 셀을 작성합니다.
    newWin.document.write('</tr>'); // 행을 닫습니다.
    newWin.document.write('</thead>'); // 머리말을 닫습니다.
    newWin.document.write('<tbody>'); // 몸통을 시작합니다.
    
    // 테이블 내용을 작성합니다.
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-9wq8" rowspan="3">인적사항</td>');
    newWin.document.write('<td class="tg-nrix" rowspan="2">성&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;명</td>');
    newWin.document.write('<td class="tg-nrix">국문</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeePsnl_kname}</td>');
    newWin.document.write('<td class="tg-nrix" rowspan="2">주민등록번호<br><br>(생년월일)</td>');
    newWin.document.write('<td class="tg-nrix" rowspan="2">${infoRequestAll.employeePsnl_residentNumber}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix">영문</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeePsnl_ename}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix">주   &nbsp;&nbsp;&nbsp; 소</td>');
    newWin.document.write('<td class="tg-nrix" colspan="4">${infoRequestAll.employeePsnl_adress}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix" rowspan="3">재직사항</td>');
    newWin.document.write('<td class="tg-nrix">회&nbsp;&nbsp;사&nbsp;&nbsp;명</td>');
    newWin.document.write('<td class="tg-nrix" colspan="2">(주) 헥사곤</td>');
    newWin.document.write('<td class="tg-nrix">사  원  번  호</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeeNum}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix">부  &nbsp;&nbsp;  서</td>');
    newWin.document.write('<td class="tg-nrix" colspan="2">${infoRequestAll.employeeEply_depart}</td>');
    newWin.document.write('<td class="tg-nrix">직    위</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeeEply_position}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix">입&nbsp;&nbsp;사&nbsp;&nbsp;일</td>');
    newWin.document.write('<td class="tg-nrix" colspan="2">${infoRequestAll.employeeEply_join}</td>');
    newWin.document.write('<td class="tg-nrix">퇴   사   일</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeeEply_resignation}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix" colspan="6" rowspan="2">상기인은 위와 같이 당사에 재직하고 있음을 증명합니다.<br><br><br><br><br><br><br>${dateToday}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix">발급부서</td>');
    newWin.document.write('<td class="tg-nrix" colspan="3">${infoRequestAll.employeeEply_depart}</td>');
    newWin.document.write('<td class="tg-nrix">연락처</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeePsnl_phoneNumber}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix" colspan="6">(주) 헥사곤  <span style="font-weight:bold">대표 이사  O O O&nbsp;&nbsp;(인)</span></td>');
    newWin.document.write('</tr>');


    newWin.document.write('</tbody>'); // 몸통을 닫습니다.
    newWin.document.write('</table>'); // 테이블을 닫습니다.
    newWin.document.write('</body></html>'); // 문서를 닫습니다.
    newWin.document.close(); // 문서 작성이 완료되었으므로 문서를 닫습니다.

    // 프린트를 실행합니다.
    newWin.print(); // 프린트 다이얼로그를 엽니다.

    // 프린트 창이 닫힌 후 새로 열린 탭을 닫도록 지연시간을 설정합니다.
    //작동안됨... 이유 찾는중...
    setTimeout(function() {
        newWin.close(); // 새로 열린 탭을 닫습니다.
    }, 2000); // 2초 후에 탭을 닫습니다.
}

</script>


</body>
</html>