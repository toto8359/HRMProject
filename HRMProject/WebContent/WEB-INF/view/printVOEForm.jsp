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
			<a class="menu-item" href="employeeInfoManage.do">社員情報管理</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="printVOE.do">在職証明書の発行</a>
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="listVOEPrintRecord.do">在職証明書発行台帳</a>
		</div>
	</div>

	<!-- 사원정보목록창 -->
	<!-- 社員情報リスト -->
	<div class="table-container">
		<div class="page-answer">
			<h3>社員リスト</h3>
		</div>
		<table class="custom-table">
			<!-- custom-table 클래스 추가 -->
			<thead>
				<tr>
					<th>社員番号</th>
					<th>部署</th>
					<th>職位</th>
					<th>名前</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${employeeListPagePart.hasNoEmployeeList()}">
					<tr>
						<td colspan="4">投稿がありません</td>
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
									href="printVOE.do?pageNo=${employeeListPagePart.startPage - 5}">[前へ]</a>
								<!-- custom-button 클래스 추가 -->
							</c:if> <c:forEach var="pNo" begin="${employeeListPagePart.startPage}"
								end="${employeeListPagePart.endPage}">
								<a href="printVOE.do?pageNo=${pNo}">[${pNo}]</a>
								<!-- custom-button 클래스 추가 -->
							</c:forEach> <c:if
								test="${employeeListPagePart.endPage < employeeListPagePart.totalPages}">
								<a
									href="printVOE.do?pageNo=${employeeListPagePart.startPage + 5}">[次へ]</a>
								<!-- custom-button 클래스 추가 -->
							</c:if></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>

	<!-- 사원정보창 -->
	<!-- 社員情報ウィンドウ -->
	<c:if test="${readInfo}">
		<div class="table-container">
			<div class="page-answer">
				<h3>${infoRequestAll.employeePsnl_kname}様の社員情報</h3>
			</div>
			<table class="custom-table" id="print-table">
				<thead>
					<tr>
						<th>項目</th>
						<th>情報</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>社員番号</td>
						<td>${infoRequestAll.employeeNum}</td>
					</tr>
					<tr>
						<td>韓国名</td>
						<td>${infoRequestAll.employeePsnl_kname}</td>
					</tr>
					<tr>
						<td>英文名</td>
						<td>${infoRequestAll.employeePsnl_ename}</td>
					</tr>
					<tr>
						<td>内国人 外国人</td>
						<td>${infoRequestAll.employeePsnl_isForeigner}</td>
					</tr>
					<tr>
						<td>マイナンバー</td>
						<td>${infoRequestAll.employeePsnl_residentNumber}</td>
					</tr>
					<tr>
						<td>住所</td>
						<td>${infoRequestAll.employeePsnl_adress}</td>
					</tr>
					<tr>
						<td>電話番号</td>
						<td>${infoRequestAll.employeePsnl_phoneNumber}</td>
					</tr>
					<tr>
						<td>メール</td>
						<td>${infoRequestAll.employeePsnl_email}</td>
					</tr>
					<tr>
						<td>SNSアカウント</td>
						<td>${infoRequestAll.employeePsnl_sns}</td>
					</tr>
					<tr>
						<td>雇用形態</td>
						<td>${infoRequestAll.employeeEply_employType}</td>
					</tr>
					<tr>
						<td>部署</td>
						<td>${infoRequestAll.employeeEply_depart}</td>
					</tr>
					<tr>
						<td>職位</td>
						<td>${infoRequestAll.employeeEply_position}</td>
					</tr>
					<tr>
						<td>入社日</td>
						<td>${infoRequestAll.employeeEply_join}</td>
					</tr>
					<tr>
						<td>退職日</td>
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
						<input type="submit" value="プリント." class="custom-button" onclick="printTable()">
				</form>
			</div>
		</div>
	</c:if>
	
<script>
function printTable() {
    // 테이블 요소와 스타일 요소를 가져온다
    //テーブル要素とスタイル要素をインポートする
    var table = document.getElementById("print-table"); // id값이 print-table인 테이블을 가져온다	//id値がprint-tableであるテーブルを取得する
    var styles = document.createElement('style'); // 스타일 요소를 생성	//スタイル要素を作成

    // 테이블을 적용할 스타일을 추가
    //テーブルを適用するスタイルを追加
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

    // 새 창을 열고 스타일과 테이블을 새 창에 쓰기
    //新しいウィンドウを開き、スタイルとテーブルを新しいウィンドウに書き込む
    var newWin = window.open('about:blank', '_blank'); // about:blank 주소로 새 탭열기	//アドレスに新しいタブを開く
    newWin.document.write('<html><head><title>Print Preview</title></head><body>'); // 새 탭에 문서 시작을 작성	//新しいタブにドキュメントの開始を作成
    newWin.document.write('<style>' + styles.innerHTML + '</style>'); // 스타일을 작성	//スタイルを作成
    newWin.document.write('<table class="tg" style="undefined;table-layout: fixed; width: 564px">'); // 테이블을 시작	//テーブルをスタート
    newWin.document.write('<colgroup>'); 

    // 열 너비를 정의
    //列の幅を定義
    newWin.document.write('<col style="width: 67px">');
    newWin.document.write('<col style="width: 70px">');
    newWin.document.write('<col style="width: 79px">');
    newWin.document.write('<col style="width: 109px">');
    newWin.document.write('<col style="width: 95px">');
    newWin.document.write('<col style="width: 144px">');

    newWin.document.write('</colgroup>'); // 열 그룹을 닫는다	//列グループを閉じる
    newWin.document.write('<thead>'); // 머리말을 시작	//おしゃべりスタート
    newWin.document.write('<tr>'); // 행을 시작	//行始め
    newWin.document.write('<th class="tg-7ofv" colspan="6"><span style="font-weight:bold">在 職 証 明 書</span></th>');
    newWin.document.write('</tr>'); // 행을 닫는다.	//行を閉じる
    newWin.document.write('</thead>'); 
    newWin.document.write('<tbody>'); 
    
    // 테이블 내용을 작성
    //テーブルの内容を作成
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-9wq8" rowspan="3">人的事項</td>');
    newWin.document.write('<td class="tg-nrix" rowspan="2">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</td>');
    newWin.document.write('<td class="tg-nrix">韓国</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeePsnl_kname}</td>');
    newWin.document.write('<td class="tg-nrix" rowspan="2">マイナンバー<br><br>(生年月日)</td>');
    newWin.document.write('<td class="tg-nrix" rowspan="2">${infoRequestAll.employeePsnl_residentNumber}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix">英文</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeePsnl_ename}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix">住   &nbsp;&nbsp;&nbsp; 所</td>');
    newWin.document.write('<td class="tg-nrix" colspan="4">${infoRequestAll.employeePsnl_adress}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix" rowspan="3">在職事項</td>');
    newWin.document.write('<td class="tg-nrix">회&nbsp;&nbsp;사&nbsp;&nbsp;명</td>');
    newWin.document.write('<td class="tg-nrix" colspan="2">株式会社 ヘキサゴン</td>');
    newWin.document.write('<td class="tg-nrix">社  員  番  号</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeeNum}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix">部  &nbsp;&nbsp;  署</td>');
    newWin.document.write('<td class="tg-nrix" colspan="2">${infoRequestAll.employeeEply_depart}</td>');
    newWin.document.write('<td class="tg-nrix">職    位</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeeEply_position}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix">入   社   日</td>');
    newWin.document.write('<td class="tg-nrix" colspan="2">${infoRequestAll.employeeEply_join}</td>');
    newWin.document.write('<td class="tg-nrix">退   職   日</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeeEply_resignation}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix" colspan="6" rowspan="2">上記の者は、上記のとおり当社に在職していることを証明します。<br><br><br><br><br><br><br>${dateToday}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix">発行部署</td>');
    newWin.document.write('<td class="tg-nrix" colspan="3">${infoRequestAll.employeeEply_depart}</td>');
    newWin.document.write('<td class="tg-nrix">電話番号</td>');
    newWin.document.write('<td class="tg-nrix">${infoRequestAll.employeePsnl_phoneNumber}</td>');
    newWin.document.write('</tr>');
    newWin.document.write('<tr>');
    newWin.document.write('<td class="tg-nrix" colspan="6">株式会社 ヘキサゴン  <span style="font-weight:bold">代表取締役  O O O&nbsp;&nbsp;(印)</span></td>');
    newWin.document.write('</tr>');


    newWin.document.write('</tbody>'); 
    newWin.document.write('</table>'); 
    newWin.document.write('</body></html>');
    newWin.document.close(); // 문서 작성이 완료되었으므로 문서를 닫는다	//文書作成が完了したので文書を閉じる

    // 프린트를 실행
    //プリントを実行
    newWin.print();

    setTimeout(function() {
        newWin.close(); // 새로 열린 탭을 닫는다	//新しく開いたタブを閉じる
    }, 2000); 
}

</script>


</body>
</html>