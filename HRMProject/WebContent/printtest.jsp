<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판 예제</title>
<link rel="stylesheet" type="text/css" href="css/style.css">

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

<!-- 첫 번째 테이블 -->
<div class="table-container">
		<table class="custom-table" id="table1">
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
            <tr>
                <td>001</td>
                <td>영업부</td>
                <td>대리</td>
                <td>홍길동</td>
            </tr>
            <tr>
                <td>002</td>
                <td>인사부</td>
                <td>사원</td>
                <td>이순신</td>
            </tr>
            <tr>
                <td>003</td>
                <td>개발부</td>
                <td>수석</td>
                <td>김철수</td>
            </tr>
        </tbody>
		</table>
	</div>

<!-- 프린트 버튼 -->
<button onclick="printTable()">테이블 프린트</button>

<script>
function printTable() {
    // 테이블 요소와 스타일 요소를 가져옵니다.
    var table = document.getElementById("table1"); // id값이 table1인 테이블을 가져옵니다.
    var styles = document.getElementsByTagName("style"); // 모든 style 태그를 가져옵니다.

    // 스타일 태그들의 내용을 문자열로 합칩니다.
    var stylesHTML = '';
    for (var i = 0; i < styles.length; i++) {
        stylesHTML += styles[i].innerHTML;
    }

    // 새 창을 열고 스타일과 테이블을 새 창에 쓰기합니다.
    var newWin = window.open('about:blank', '_blank'); // about:blank 주소로 새 탭을 엽니다.
    newWin.document.write('<html><head><title>Print Preview</title><style>' + 
    						stylesHTML + '</style></head><body>' + table.outerHTML + '</body></html>'); // 새 탭에 스타일과 테이블을 작성합니다.
    newWin.document.close(); // 문서 작성이 완료되었으므로 문서를 닫습니다.

    // 프린트를 실행합니다.
    newWin.print(); // 프린트 다이얼로그를 엽니다.

 	// 프린트 창이 닫힌 후 새로 열린 탭을 닫도록 지연시간을 설정합니다.
    setTimeout(function() {
        newWin.close(); // 새로 열린 탭을 닫습니다.
    }, 500); // 0.5초 후에 탭을 닫습니다.
}



</script>
</body>
</html>
