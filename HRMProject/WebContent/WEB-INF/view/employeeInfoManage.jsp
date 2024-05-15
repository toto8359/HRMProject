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
	width: auto; /* 테이블 컨테이너의 너비 조정 *//* テーブルコンテナの幅を調整 */
	margin-right: 5%; /* 테이블 간격 조정 *//* テーブル間の間隔を調整 */
	vertical-align: top; /* 테이블을 상단으로 정렬 */ /* テーブルを上部に揃える */
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
	color: inherit; /* 링크의 색상을 부모 요소인 테이블의 글자색과 동일하게 지정 *//* リンクの色を親要素であるテーブルの文字色と同じにする */
	text-decoration: none; /* 링크의 밑줄 제거 *//* リンクの下線を削除 */
}

.custom-table th {
	background-color: #808080;
}

.custom-table tbody tr:nth-child(even) {
	background-color: #808080;
}

/* 버튼 스타일 */
.button-form {
	margin-top: 10px; /* 버튼 간격을 조절합니다 *//* ボタン間の間隔を調整 */
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
	margin-right: 10px; /* 버튼 간격을 조절합니다 *//* ボタン間の間隔を調整 */
}

.custom-button:hover {
	background-color: #21b0f1;
}

.form-table-spacing {
	margin-top: 50px; /* 원하는 만큼의 간격을 설정하세요 */ /* 希望する間隔を設定 */
}

.form-below-table {
	margin-top: 20px; /* 테이블 아래에 간격을 조정하세요 *//* テーブルの下に間隔を調整 */
}
</style>

</head>
<body>


	<div class="menu-left">
		<p style="color: black;">${authUser.member_name}様、ようこそ。</p>
		<!-- 님,안녕하세요. -->
		<div class="left-menu-btn">
			<a class="menu-item" href="/HRMProject">ホーム</a>
			<!-- HOME -->
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="javascript:history.back()">戻る</a>
			<!-- 뒤로가기 -->
		</div>
		<div class="left-menu-btn">
			<a href="logout.do">ログアウトする</a>
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
			<!-- 재직증명서 발급 -->
		</div>
		<div class="left-menu-btn">
			<a class="menu-item" href="listVOEPrintRecord.do">在職証明書発行台帳</a>
			<!-- 재직증명서 발급대장 -->
		</div>
	</div>

	<%-- 	<!-- 안내창 -->
	<c:if test="${!empty employeePsnl_kname}">
		<c:if test="${modifySuccess}">
			사원 ${employeePsnl_kname}의 사원정보 수정을 완료했습니다.
			社員 ${employeePsnl_kname}の社員情報修正が完了しました。
		</c:if>
		<c:if test="${joinSuccess}">
			사원 ${employeePsnl_kname}의 등록을 완료했습니다.
			社員 ${employeePsnl_kname}の登録が完了しました。
		</c:if>
		<c:if test="${deleteSuccess}">
			사원 ${employeePsnl_kname}의 사원정보 삭제를 완료했습니다.
			社員 ${employeePsnl_kname}の社員情報削除が完了しました。
		</c:if>
	</c:if><br/> --%>

	<!-- 사원정보목록창 -->
	<!-- 社員情報リスト -->
	<div class="table-container">
		<div class="page-answer">
			<h3>社員リスト</h3>
			<!-- 사원 목록 -->
		</div>
		<table class="custom-table">
			<!-- custom-table 클래스 추가 -->
			<thead>
				<tr>
					<th>社員番号<</th>
					<!-- 사원번호 -->
					<th>部署</th>
					<!-- 부서 -->
					<th>職位</th>
					<!-- 직급 -->
					<th>名前</th>
					<!-- 이름 -->
				</tr>
			</thead>
			<tbody>
				<c:if test="${employeeListPagePart.hasNoEmployeeList()}">
					<tr>
						<td colspan="4">投稿がありません</td>
						<!-- 게시글이 없습니다. -->
					</tr>
				</c:if>
				<c:forEach var="employeeEply"
					items="${employeeListPagePart.content}">
					<tr>
						<td>${employeeEply.employeeNum}</td>
						<td>${employeeEply.employeeEply_depart}</td>
						<td>${employeeEply.employeeEply_position}</td>
						<td><a
							href="employeeInfoManage.do?employeeNum=${employeeEply.employeeNum}&pageNo=${employeeListPagePart.currentPage}">
								<c:out value="${employeeEply.employeePsnl_kname}" />
						</a></td>
					</tr>
				</c:forEach>
				<c:if test="${employeeListPagePart.hasEmployeeList()}">
					<tr>
						<td colspan="4"><c:if
								test="${employeeListPage.startPage > 5}">
								<a
									href="employeeInfoManage.do?pageNo=${employeeListPagePart.startPage - 5}">[前へ]</a>
									<!-- 이전 -->
								<!-- custom-button 클래스 추가 -->
								<!-- custom-button クラスを追加 -->
							</c:if> <c:forEach var="pNo" begin="${employeeListPagePart.startPage}"
								end="${employeeListPagePart.endPage}">
								<a href="employeeInfoManage.do?pageNo=${pNo}">[${pNo}]</a>
								<!-- custom-button 클래스 추가 -->
								<!-- custom-button クラスを追加 -->
							</c:forEach> <c:if
								test="${employeeListPagePart.endPage < employeeListPagePart.totalPages}">
								<a
									href="employeeInfoManage.do?pageNo=${employeeListPagePart.startPage + 5}">[次へ]</a>
									<!-- 다음 -->
								<!-- custom-button 클래스 추가 -->
								<!-- custom-button クラスを追加 -->
							</c:if></td>
					</tr>
				</c:if>
			</tbody>
		</table>
		<!-- 등록창 -->
		<!-- 登録フォーム -->
		<!-- 등록 버튼을 누른 적이 없으면 or 등록이 완료되면 -->
		<!-- 登録ボタンを押したことがない場合 or 登録が完了した場合 -->
		<div class="form-below-table">
			<form action="employeeInfoManage.do" method="get">
				<c:if test="${!registerForm}">
					<input type="hidden" name="registerForm" value="registerForm" />
					<input type="submit" value="登録" class="custom-button">
					<!-- 등록 -->
				</c:if>
			</form>
		</div>
	</div>


	<!-- 정보입력 후 등록 버튼을 누르면 -->
	<!-- 情報入力後に登録ボタンを押した場合 -->
	<form action="employeeInfoManage.do" method="POST">
		<c:if test="${registerForm}">
			<p>
				<!-- 수정일 경우, 사원번호는 고정 -->
				<!-- 修正の場合、社員番号は固定 -->
				<c:if test="${modifyInfo}">
					<label id="employeeNum-label" for="name">社員番号</label>
					<!-- employeeNum -->
					<input type="text" name="employeeNum"
						class="login-input placeholder-gray" value="${employeeNumModify}"
						readonly="readonly" />
				</c:if>
				<!-- 등록일 경우, 사원번호 입력하도록 -->
				<!-- 登録の場合、社員番号を入力するように -->
				<c:if test="${!modifyInfo}">
					<label id="employeeNum-label" for="name">社員番号</label>
					<!-- employeeNum -->
					<input type="text" name="employeeNum" placeholder='社員番号'
						class="login-input placeholder-gray" value="${param.employeeNum}">
					<c:if test="${errors.employeeNum}">
						<div class="alert alert-error">社員番号を入力してください。</div>
						<!-- 사원번호를 입력하세요. -->
					</c:if>
				</c:if>
			</p>
			<div class="div-inline">
				<div class="input-group">
					<label id="K-name-label" for="name">韓国語名</label> 
					<!-- K-name -->
					<input
						type="text" name="employeePsnl_kname"
						placeholder='韓国語名'
						class="login-input placeholder-gray"
						value="${param.employeePsnl_kname}"
						id="employeePsnl_kname">
					<c:if test="${errors.employeePsnl_kname}">
						<div class="alert alert-error">韓国語名を入力してください。</div>
						<!-- 국문이름을 입력하세요. -->
					</c:if>
				</div>
				<div class="input-group">
					<label id="E-name-label" for="name">英語名</label> 
					<!-- E-name -->
					<input
						type="text" name="employeePsnl_ename"
						placeholder='英語名'
						class="login-input placeholder-gray"
						value="${param.employeePsnl_ename}"
						id="employeePsnl_ename">
					<c:if test="${errors.employeePsnl_ename}">
						<div class="alert alert-error">英語名を入力してください。</div>
						<!-- 영문이름을 입력하세요. -->
					</c:if>
				</div>
			</div>
			<div class="div-inline">
				<div class="input-group">
					<label id="Foreigner-label" for="employeePsnl_isForeigner">国籍</label>
					<!-- Foreigner -->
					<select name="employeePsnl_isForeigner"
						id="employeePsnl_isForeigner" class="custom-select">
						<option value="韓国人"
							<c:if test="${param.employeePsnl_isForeigner == '韓国人'}">selected</c:if>>韓国人</option>
							<!-- 내국인 -->
						<option value="外国人"
							<c:if test="${param.employeePsnl_isForeigner == '外国人'}">selected</c:if>>外国人</option>
							<!-- 외국인 -->
					</select>
					<c:if test="${errors.employeePsnl_isForeigner}">
						<div class="alert alert-error">韓国人または外国人を入力してください。</div>
						<!-- 내국인 외국인 여부를 입력하세요. -->
					</c:if>
					<c:if test="${errors.duplicateResidentNumber}">
						<div class="alert alert-error">すでに社員情報が存在します。</div>
						<!-- 이미 사원정보가 존재합니다. -->
					</c:if>
				</div>
				<div class="input-group">
					<label id="residentNumber" for="employeePsnl_residentNumber">マイナンバー</label>
					<!-- residentNumber -->
					<input type="text" name="employeePsnl_residentNumber"
						placeholder='マイナンバー' class="login-input placeholder-gray"
						value="${param.employeePsnl_residentNumber}">
					<c:if test="${errors.employeePsnl_residentNumber}">
						<div class="alert alert-error">マイナンバーを入力してください。</div>
						<!-- 주민번호를 입력하세요. -->
					</c:if>
				</div>
			</div>
			<p>
				<label id="adress" for="employeePsnl_adress">住所</label> <input
					type="text" name="employeePsnl_adress" placeholder='住所'
					class="login-input placeholder-gray"
					value="${param.employeePsnl_adress}">
				<c:if test="${errors.employeePsnl_adress}">
					<div class="alert alert-error">住所を入力してください。</div>
					<!-- 주소를 입력하세요. -->
				</c:if>
			</p>
			<p>
				<label id="phoneNumber" for="employeePsnl_phoneNumber">電話番号</label>
				<input type="text" name="employeePsnl_phoneNumber"
					placeholder='電話番号' class="login-input placeholder-gray"
					value="${param.employeePsnl_phoneNumber}">
				<c:if test="${errors.employeePsnl_phoneNumber}">
					<div class="alert alert-error">電話番号を入力してください。</div>
					<!-- 전화번호를 입력하세요. -->
				</c:if>
			</p>
			<div class="div-inline">
				<div class="input-group">
					<label id="email" for="employeePsnl_email">メール</label> <input
						type="text" name="employeePsnl_email" placeholder='email'
						class="login-input placeholder-gray"
						value="${param.employeePsnl_email}">
					<c:if test="${errors.employeePsnl_email}">
						<div class="alert alert-error">メールを入力してください。</div>
						<!-- 이메일을 입력하세요. -->
					</c:if>
				</div>
				<div class="input-group">
					<label id="sns" for="employeePsnl_sns">SNS</label> <input
						type="text" name="employeePsnl_sns" placeholder='adress'
						class="login-input placeholder-gray"
						value="${param.employeePsnl_sns}">
					<c:if test="${errors.employeePsnl_sns}">
						<div class="alert alert-error">SNSアカウントを入力してください。</div>
						<!-- SNS계정을 입력하세요. -->
					</c:if>
				</div>
			</div>
			<div class="div-inline">
				<div class="input-group">
					<label id="employType" for="employeePsnl_employType">雇用形態</label>
					<!-- employType -->
					<select name="employeeEply_employType" id="employeeEply_employType"
						class="custom-select">
						<option value="正社員"
							<c:if test="${param.employeeEply_employType == '正社員'}">selected</c:if>>正社員</option>
							<!-- 정사원 -->
						<option value="契約社員"
							<c:if test="${param.employeeEply_employType == '契約社員'}">selected</c:if>>契約社員</option>
							<!-- 계약직 -->
					</select>
					<c:if test="${errors.employeeEply_employType}">
						<div class="alert alert-error">雇用形態を入力してください。</div>
						<!-- 고용 형태를 입력하세요. -->
					</c:if>
				</div>

				<div class="input-group">
					<label id="depart" for="employeePsnl_depart">部署</label> <select
						name="employeeEply_depart" id="employeeEply_depart"
						class="custom-select">
						<option value="人事部"
							<c:if test="${param.employeeEply_depart == '人事部'}">selected</c:if>>人事部</option>
						<option value="営業部"
							<c:if test="${param.employeeEply_depart == '営業部'}">selected</c:if>>営業部</option>
						<option value="マーケティング部"
							<c:if test="${param.employeeEply_depart == 'マーケティング部'}">selected</c:if>>マーケティング部</option>
						<option value="開発部"
							<c:if test="${param.employeeEply_depart == '開発部'}">selected</c:if>>開発部</option>
						<option value="生産部"
							<c:if test="${param.employeeEply_depart == '生産部'}">selected</c:if>>生産部</option>
						<option value="会計部"
							<c:if test="${param.employeeEply_depart == '会計部'}">selected</c:if>>会計部</option>
						<option value="技術部"
							<c:if test="${param.employeeEply_depart == '技術部'}">selected</c:if>>技術部</option>
						<option value="サービス部"
							<c:if test="${param.employeeEply_depart == 'サービス部'}">selected</c:if>>サービス部</option>
						<option value="購買部"
							<c:if test="${param.employeeEply_depart == '購買部'}">selected</c:if>>購買部</option>
						<option value="経営支援部"
							<c:if test="${param.employeeEply_depart == '経営支援部'}">selected</c:if>>経営支援部</option>
					</select>
					<c:if test="${errors.employeeEply_depart}">
						<div class="alert alert-error">部署を入力してください。</div>
						<!-- 부서를 입력하세요. -->
					</c:if>
				</div>

				<div class="input-group">
					<label id="position" for="employeePsnl_position">職位</label> <select
						name="employeeEply_position" id="employeeEply_position"
						class="custom-select">
						<option value="会長"
							<c:if test="${param.employeeEply_position == '会長'}">selected</c:if>>会長</option>
						<option value="社長"
							<c:if test="${param.employeeEply_position == '社長'}">selected</c:if>>社長</option>
						<option value="副社長"
							<c:if test="${param.employeeEply_position == '副社長'}">selected</c:if>>副社長</option>
						<option value="専務"
							<c:if test="${param.employeeEply_position == '専務'}">selected</c:if>>専務</option>
						<option value="常務"
							<c:if test="${param.employeeEply_position == '常務'}">selected</c:if>>常務</option>
						<option value="本部長"
							<c:if test="${param.employeeEply_position == '本部長'}">selected</c:if>>本部長</option>
						<option value="部長"
							<c:if test="${param.employeeEply_position == '部長'}">selected</c:if>>部長</option>
						<option value="次長"
							<c:if test="${param.employeeEply_position == '次長'}">selected</c:if>>次長</option>
						<option value="課長"
							<c:if test="${param.employeeEply_position == '課長'}">selected</c:if>>課長</option>
						<option value="係長"
							<c:if test="${param.employeeEply_position == '係長'}">selected</c:if>>係長</option>
						<option value="主任"
							<c:if test="${param.employeeEply_position == '主任'}">selected</c:if>>主任</option>
						<option value="一般社員"
							<c:if test="${param.employeeEply_depart == '一般社員'}">selected</c:if>>一般社員</option>
					</select>
					<c:if test="${errors.employeeEply_position}">
						<div class="alert alert-error">職位を入力してください。</div>
						<!-- 직급을 입력하세요. -->
					</c:if>
				</div>
			</div>
			<div class="div-inline">
				<div class="input-group">
					<label id="joinDate" for="employeePsnl_joinDate">joinDate</label>
					<!-- joinDate -->
					 <input
						type="text" name="employeeEply_join" id="employeeEply_join"
						placeholder='入社日' class="login-input placeholder-gray"
						value="${param.employeeEply_join}">
					<c:if test="${errors.employeeEply_join}">
						<div class="alert alert-error">入社日を入力してください。</div>
						<!-- 입사날짜를 입력하세요. -->
					</c:if>
				</div>

				<div class="input-group">
					<label id="resignationDate" for="employeePsnl_resignationDate">退職日</label>
					<input type="text" name="employeeEply_resignation"
						id="employeeEply_resignation" placeholder='退職日'
						class="login-input placeholder-gray"
						value="${param.employeeEply_resignation}">
					<%-- <c:if test="${errors.employeeEply_resignation}">퇴사날짜를 입력하세요.</c:if> --%>
					<!-- 退職日を入力してください。 -->
				</div>
			</div>
			<!-- 등록버튼으로 들어왔으면 등록 -->
			<!-- 登録ボタンで入力した場合は登録 -->
			<c:if test="${!modifyInfo}">
				<div class="Loginbutton-wrapper">
					<input type="submit" value="登録"
						class="btn btn-block btn-primary register-button">
						<!-- 등록 -->
				</div>
			</c:if>
			<!-- 수정하기 버튼으로 들어왔으면 수정 -->
			<!-- 更新ボタンで入力した場合は更新 -->
			<c:if test="${modifyInfo}">
				<input type="hidden" name="modifyInfo" value="modifyInfo" />
				<div class="Loginbutton-wrapper">
					<input type="submit" value="修整"
						class="btn btn-block btn-primary modify-button">
						<!-- 수정 -->
				</div>
			</c:if>

		</c:if>
	</form>
	<!-- 사원정보창 -->
	<!-- 社員情報ウィンドウ -->
	<c:if test="${readInfo}">
		<div class="table-container">
			<div class="page-answer">
				<h3>${infoRequestAll.employeePsnl_kname}様の社員情報</h3>
				<!-- 님의사원정보 -->
			</div>
			<table class="custom-table">
				<thead>
					<tr>
						<th>項目</th>
						<!-- 항목 -->
						<th>情報</th>
						<!-- 정보 -->
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>社員番号</td>
						<!-- 사원번호 -->
						<td>${infoRequestAll.employeeNum}</td>
					</tr>
					<tr>
						<td>韓国名</td>
						<!-- 국문이름 -->
						<td class="employeePsnl_kname">${infoRequestAll.employeePsnl_kname}</td>
					</tr>
					<tr>
						<td>英文名</td>
						<!-- 영문이름 -->
						<td>${infoRequestAll.employeePsnl_ename}</td>
					</tr>
					<tr>
						<td>韓国人 外国人</td>
						<!-- 내국인 외국인 -->
						<td>${infoRequestAll.employeePsnl_isForeigner}</td>
					</tr>
					<tr>
						<td>マイナンバー</td>
						<!-- 주민번호 -->
						<td>${infoRequestAll.employeePsnl_residentNumber}</td>
					</tr>
					<tr>
						<td>住所</td>
						<!-- 주소 -->
						<td>${infoRequestAll.employeePsnl_adress}</td>
					</tr>
					<tr>
						<td>電話番号</td>
						<!-- 전화번호 -->
						<td>${infoRequestAll.employeePsnl_phoneNumber}</td>
					</tr>
					<tr>
						<td>メール</td>
						<!-- 이메일 -->
						<td>${infoRequestAll.employeePsnl_email}</td>
					</tr>
					<tr>
						<td>SNSアカウント</td>
						<!-- SNS계정 -->
						<td>${infoRequestAll.employeePsnl_sns}</td>
					</tr>
					<tr>
						<td>雇用形態</td>
						<!-- 고용 형태 -->
						<td>${infoRequestAll.employeeEply_employType}</td>
					</tr>
					<tr>
						<td>部署</td>
						<!-- 부서 -->
						<td>${infoRequestAll.employeeEply_depart}</td>
					</tr>
					<tr>
						<td>職位</td>
						<!-- 직급 -->
						<td>${infoRequestAll.employeeEply_position}</td>
					</tr>
					<tr>
						<td>入社日</td>
						<!-- 입사날짜 -->
						<td>${infoRequestAll.employeeEply_join}</td>
					</tr>
					<tr>
						<td>退職日</td>
						<!-- 퇴사날짜 -->
						<td>${infoRequestAll.employeeEply_resignation}</td>
					</tr>
				</tbody>
			</table>
				<div class="div-inline">
					<form action="employeeInfoManage.do" method="get"
						class="button-form">
						<c:if test="${!modifyInfo}">
							<input type="hidden" name="registerForm" value="registerForm" />
							<input type="hidden" name="modifyInfo" value="modifyInfo" />
							<input type="hidden" name="employeeNumModify"
								value="${infoRequestAll.employeeNum}" />
							<input type="hidden" name="pageNo"
								value="${employeeListPagePart.currentPage}" />
							<input type="submit" value="修整" class="custom-button">
							<!-- 수정 -->
						</c:if>
					</form>

				<!-- 삭제하기 버튼 -->
					<form action="employeeInfoManage.do" method="POST"
						class="button-form">
						<input type="hidden" name="employeeNumDelete"
							value="${infoRequestAll.employeeNum}" /> <input type="hidden"
							name="employeeKnameDelete"
							value="${infoRequestAll.employeePsnl_kname}" /> <input
							type="hidden" name="pageNo"
							value="${employeeListPagePart.currentPage}" /> <input
							type="submit" value="削除" class="custom-button delete-button">
							<!-- 삭제 -->
					</form>
				</div>
		</div>
		<!-- 수정하기 버튼을 누르면, 등록창(수정창)을 띄우고, 등록창에 등록 말고 수정기능의 수정버튼을 띄운다 -->
		<!-- 修正するボタンを押すと、登録ウィンドウを表示し、登録ウィンドウに登録せずに修正機能の修正ボタンを表示する-->
	</c:if>

	<script>
		$(document).ready(function() {
			$("#employeeEply_join").datepicker({
				dateFormat : "yy-mm-dd" // 필요한 날짜 형식으로 설정// 必要な日付形式に設定
			});
			$("#employeeEply_resignation").datepicker({
				dateFormat : "yy-mm-dd" // 필요한 날짜 형식으로 설정// 必要な日付形式に設定
			});
		});

 		$(document).ready(function() {
			// 등록 버튼 클릭 시 알람 표시
			 // 登録ボタンクリック時にアラートを表示
			$(".register-button").click(function() {
				if (!checkFields()) {
					var name = $("#employeePsnl_kname").val();
					showRegisterAlert(name);
				}
			});

			// 수정 버튼 클릭 시 알람 표시
			// 修整ボタンクリック時にアラートを表示
			$(".modify-button").click(function() {
				if (!checkFields()) {
					var name = $("#employeePsnl_kname").val();
					showModifyAlert(name);
				}
			});

			// 삭제 버튼 클릭 시 알람 표시
			// 削除ボタンクリック時にアラートを表示
			$(".delete-button").click(function() {
				var name = $(this).closest('.table-container').find('.employeePsnl_kname').text();
				showDeleteAlert(name);
			});
		});

		// 등록 알람 표시 함수
		// 登録アラート表示関数
		function showRegisterAlert(name) {
			alert(name + "様の情報が登録されました。");
			/* 님의 정보가 등록되었습니다. */
			location.reload();
		}

		// 수정 알람 표시 함수
		// 修整アラート表示関数
		function showModifyAlert(name) {
			alert(name + "様の情報が修整されました。");
			/* 님의 정보가 수정되었습니다. */
			location.reload();
		}

		// 삭제 알람 표시 함수
		// 削除アラート表示関数
		function showDeleteAlert(name) {
			alert(name + "様の情報が削除されました。");
			/* 님의 정보가 삭제되었습니다. */
			location.reload();
		}

		// 필드가 비어 있는지 확인하는 함수
		// フィールドが空かどうかをチェックする関数
		function checkFields() {
			var isEmpty = false;
			$("input[type='text']").not("[name='employeeEply_resignation']")
					.each(function() {
						if ($(this).val() === "") {
							isEmpty = true; 
						}
					});
			return isEmpty;
		}
	</script>
</body>
</html> 