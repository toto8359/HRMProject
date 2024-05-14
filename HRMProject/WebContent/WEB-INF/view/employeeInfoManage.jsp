<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
    <p style="color: black;">${authUser.member_name}님, 안녕하세요.</p>
	<div class="left-menu-btn">
		<a class="menu-item" href="/HRMProject">HOME</a>
	</div>
	<div class="left-menu-btn">
        <a class="menu-item" href="javascript:history.back()">뒤로가기</a>
    </div>
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

	<!-- 사원정보목록창 -->
<div class="table-container">
	<div class="page-answer">
		<h3>사원 목록</h3>
	</div>
  <table class="custom-table"> <!-- custom-table 클래스 추가 -->
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
      <c:forEach var="employeeEply" items="${employeeListPagePart.content}">
        <tr>
          <td>${employeeEply.employeeNum}</td>
          <td>${employeeEply.employeeEply_depart}</td>
          <td>${employeeEply.employeeEply_position}</td>
          <td>
            <a href="employeeInfoManage.do?employeeNum=${employeeEply.employeeNum}&pageNo=${employeeListPagePart.currentPage}">
              <c:out value="${employeeEply.employeePsnl_kname}" />
            </a>
          </td>
        </tr>
      </c:forEach>
      <c:if test="${employeeListPagePart.hasEmployeeList()}">
        <tr>
          <td colspan="4">
            <c:if test="${employeeListPage.startPage > 5}">
              <a href="employeeInfoManage.do?pageNo=${employeeListPagePart.startPage - 5}">[이전]</a> <!-- custom-button 클래스 추가 -->
            </c:if>
            <c:forEach var="pNo" begin="${employeeListPagePart.startPage}" end="${employeeListPagePart.endPage}">
              <a href="employeeInfoManage.do?pageNo=${pNo}">[${pNo}]</a> <!-- custom-button 클래스 추가 -->
            </c:forEach>
            <c:if test="${employeeListPagePart.endPage < employeeListPagePart.totalPages}">
              <a href="employeeInfoManage.do?pageNo=${employeeListPagePart.startPage + 5}">[다음]</a> <!-- custom-button 클래스 추가 -->
            </c:if>
          </td>
        </tr>
      </c:if>
    </tbody>
  </table>
	<!-- 등록창 -->
	<!-- 등록 버튼을 누른 적이 없으면 or 등록이 완료되면 -->
	<div class="form-below-table">
	<form action="employeeInfoManage.do" method="get">
		<c:if test="${!registerForm}">
			<input type="hidden" name="registerForm" value="registerForm" />
			<input type="submit" value="등록" class="custom-button">
		</c:if>
	</form>
  </div>
</div>

	
	<!-- 정보입력 후 등록 버튼을 누르면 -->
	<form action="employeeInfoManage.do" method="POST">
		<c:if test="${registerForm}">
			<p>
			<!-- 수정일 경우, 사원번호는 고정 -->
			<c:if test="${modifyInfo}">
				<label id="employeeNum-label" for="name">employeeNum</label>
				<input type="text" name="employeeNum" class="login-input placeholder-gray" value="${employeeNumModify}" readonly="readonly"/>
			</c:if>
			<!-- 등록일 경우, 사원번호 입력하도록 -->
			<c:if test="${!modifyInfo}">
				<label id="employeeNum-label" for="name">employeeNum</label>
				<input type="text" name="employeeNum" placeholder='employeeNum' class="login-input placeholder-gray" value="${param.employeeNum}">
				<c:if test="${errors.employeeNum}">
					<div class="alert alert-error">사원번호를 입력하세요.</div>
				</c:if>
			</c:if>
			</p>
			<div class="div-inline">
				<div class="input-group">
					<label id="K-name-label" for="name">K-name</label>
					<input type="text" name="employeePsnl_kname" placeholder='employeePsnl_kname' class="login-input placeholder-gray" value="${param.employeePsnl_kname}">
				<c:if test="${errors.employeePsnl_kname}">
					<div class="alert alert-error">국문이름을 입력하세요.</div>
				</c:if>
			</div>
			<div class="input-group">
				<label id="E-name-label" for="name">E-name</label>
				<input type="text" name="employeePsnl_ename" placeholder='employeePsnl_ename' class="login-input placeholder-gray" value="${param.employeePsnl_ename}">
				<c:if test="${errors.employeePsnl_ename}">
					<div class="alert alert-error">영문이름을 입력하세요.</div>
				</c:if>
				</div>
			</div>
			<div class="div-inline">
				<div class="input-group">
   			 		<label id="Foreigner-label" for="employeePsnl_isForeigner">Foreigner</label>
    				<select name="employeePsnl_isForeigner" id="employeePsnl_isForeigner" class="custom-select">
       			 		<option value="내국인" <c:if test="${param.employeePsnl_isForeigner == '내국인'}">selected</c:if>>내국인</option>
       			 		<option value="외국인" <c:if test="${param.employeePsnl_isForeigner == '외국인'}">selected</c:if>>외국인</option>
    				</select>
    				<c:if test="${errors.employeePsnl_isForeigner}">
    					<div class="alert alert-error">내국인 외국인 여부를 입력하세요.</div>
    				</c:if>
    				<c:if test="${errors.duplicateResidentNumber}">
    					<div class="alert alert-error">이미 사원정보가 존재합니다.</div>
    				</c:if>
				</div>
				<div class="input-group">
					<label id="residentNumber" for="employeePsnl_residentNumber">residentNumber</label>
					<input type="text" name="employeePsnl_residentNumber" placeholder='residentNumber' class="login-input placeholder-gray" value="${param.employeePsnl_residentNumber}">
					<c:if test="${errors.employeePsnl_residentNumber}">
						<div class="alert alert-error">주민번호를 입력하세요.</div>
					</c:if>
				</div>
			</div>
			<p>
				<label id="adress" for="employeePsnl_adress">adress</label>
				<input type="text" name="employeePsnl_adress" placeholder='adress' class="login-input placeholder-gray" value="${param.employeePsnl_adress}">
				<c:if test="${errors.employeePsnl_adress}">
					<div class="alert alert-error">주소를 입력하세요.</div>
				</c:if>
			</p>
			<p>
				<label id="phoneNumber" for="employeePsnl_phoneNumber">phoneNumber</label> 
				<input type="text" name="employeePsnl_phoneNumber" placeholder='adress' class="login-input placeholder-gray" value="${param.employeePsnl_phoneNumber}">
				<c:if test="${errors.employeePsnl_phoneNumber}">
					<div class="alert alert-error">전화번호를 입력하세요.</div>
				</c:if>
			</p>
			<div class="div-inline">
				<div class="input-group">
					<label id="email" for="employeePsnl_email">email</label>
					<input type="text" name="employeePsnl_email" placeholder='adress' class="login-input placeholder-gray" value="${param.employeePsnl_email}">
					<c:if test="${errors.employeePsnl_email}">
						<div class="alert alert-error">이메일을 입력하세요.</div>
					</c:if>
				</div>
				<div class="input-group">
					<label id="sns" for="employeePsnl_sns">sns</label>
					<input type="text" name="employeePsnl_sns" placeholder='adress' class="login-input placeholder-gray" value="${param.employeePsnl_sns}">
				<c:if test="${errors.employeePsnl_sns}">
					<div class="alert alert-error">SNS계정을 입력하세요.</div>
				</c:if>
				</div>
			</div>
			<div class="div-inline">
				<div class="input-group">
					<label id="employType" for="employeePsnl_employType">employType</label>
					<select name="employeeEply_employType" id="employeeEply_employType" class="custom-select">
						<option value="정사원" <c:if test="${param.employeeEply_employType == '정사원'}">selected</c:if>>정사원</option>
       					<option value="계약직" <c:if test="${param.employeeEply_employType == '계약직'}">selected</c:if>>계약직</option>
					</select>
					<c:if test="${errors.employeeEply_employType}">
						<div class="alert alert-error">고용 형태를 입력하세요.</div>
					</c:if>
				</div>
		
				<div class="input-group">
					<label id="depart" for="employeePsnl_depart">部署</label>
					<select name="employeeEply_depart" id="employeeEply_depart" class="custom-select">
						<option value="人事部" <c:if test="${param.employeeEply_depart == '人事部'}">selected</c:if>>人事部</option>
       					<option value="営業部" <c:if test="${param.employeeEply_depart == '営業部'}">selected</c:if>>営業部</option>
       					<option value="マーケティング部" <c:if test="${param.employeeEply_depart == 'マーケティング部'}">selected</c:if>>マーケティング部</option>
       					<option value="開発部" <c:if test="${param.employeeEply_depart == '開発部'}">selected</c:if>>開発部</option>
       					<option value="生産部" <c:if test="${param.employeeEply_depart == '生産部'}">selected</c:if>>生産部</option>
       					<option value="会計部" <c:if test="${param.employeeEply_depart == '会計部'}">selected</c:if>>会計部</option>
       					<option value="技術部" <c:if test="${param.employeeEply_depart == '技術部'}">selected</c:if>>技術部</option>
       					<option value="サービス部" <c:if test="${param.employeeEply_depart == 'サービス部'}">selected</c:if>>サービス部</option>
       					<option value="購買部" <c:if test="${param.employeeEply_depart == '購買部'}">selected</c:if>>購買部</option>
       					<option value="経営支援部" <c:if test="${param.employeeEply_depart == '経営支援部'}">selected</c:if>>経営支援部</option>
					</select>
					<c:if test="${errors.employeeEply_depart}">
						<div class="alert alert-error">부서를 입력하세요.</div>
					</c:if>
				</div>
		
				<div class="input-group">
					<label id="position" for="employeePsnl_position">職位</label>
					<select name="employeeEply_position" id="employeeEply_position" class="custom-select">
						<option value="会長" <c:if test="${param.employeeEply_position == '会長'}">selected</c:if>>会長</option>
						<option value="社長" <c:if test="${param.employeeEply_position == '社長'}">selected</c:if>>社長</option>
						<option value="副社長" <c:if test="${param.employeeEply_position == '副社長'}">selected</c:if>>副社長</option>
						<option value="専務" <c:if test="${param.employeeEply_position == '専務'}">selected</c:if>>専務</option>
						<option value="常務" <c:if test="${param.employeeEply_position == '常務'}">selected</c:if>>常務</option>
						<option value="本部長" <c:if test="${param.employeeEply_position == '本部長'}">selected</c:if>>本部長</option>
						<option value="部長" <c:if test="${param.employeeEply_position == '部長'}">selected</c:if>>部長</option>
						<option value="次長" <c:if test="${param.employeeEply_position == '次長'}">selected</c:if>>次長</option>
						<option value="課長" <c:if test="${param.employeeEply_position == '課長'}">selected</c:if>>課長</option>
						<option value="係長" <c:if test="${param.employeeEply_position == '係長'}">selected</c:if>>係長</option>
						<option value="主任" <c:if test="${param.employeeEply_position == '主任'}">selected</c:if>>主任</option>
						<option value="一般社員" <c:if test="${param.employeeEply_depart == '一般社員'}">selected</c:if>>一般社員</option>
					</select>
					<c:if test="${errors.employeeEply_position}">
						<div class="alert alert-error">직급을 입력하세요.</div>
					</c:if>
				</div>
			</div>
			<div class="div-inline">
				<div class="input-group">
					<label id="joinDate" for="employeePsnl_joinDate">joinDate</label>
					<input type="text" name="employeeEply_join" id="employeeEply_join" placeholder='joinDate' class="login-input placeholder-gray" value="${param.employeeEply_join}">
					<c:if test="${errors.employeeEply_join}">
						<div class="alert alert-error">입사날짜를 입력하세요.</div>
					</c:if>
				</div>

				<div class="input-group">
					<label id="resignationDate" for="employeePsnl_resignationDate">resignationDate</label>
					<input type="text" name="employeeEply_resignation" id="employeeEply_resignation" placeholder='resignationDate' class="login-input placeholder-gray" value="${param.employeeEply_resignation}">
					<%-- <c:if test="${errors.employeeEply_resignation}">퇴사날짜를 입력하세요.</c:if> --%>
				</div>
			</div>
			<!-- 등록버튼으로 들어왔으면 등록 -->
			<c:if test="${!modifyInfo}">
				<div class="Loginbutton-wrapper">
					<input type="submit" value="등록" class="btn btn-block btn-primary register-button">
				</div>
			</c:if>
			<!-- 수정하기 버튼으로 들어왔으면 수정 -->
			<c:if test="${modifyInfo}">
				<input type="hidden" name="modifyInfo" value="modifyInfo" />
				<div class="Loginbutton-wrapper">
					<input type="submit" value="수정" class="btn btn-block btn-primary modify-button">
				</div>
			</c:if>

		</c:if>
	</form>
	<!-- 사원정보창 -->
<c:if test="${readInfo}">
    <div class="table-container">
    <div class="page-answer">
		<h3>${infoRequestAll.employeePsnl_kname}님의 사원정보</h3>
	</div>
        <table class="custom-table">
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
    <form action="employeeInfoManage.do" method="get" class="button-form">
        <c:if test="${!modifyInfo}">
            <input type="hidden" name="registerForm" value="registerForm" />
            <input type="hidden" name="modifyInfo" value="modifyInfo" />
            <input type="hidden" name="employeeNumModify" value="${infoRequestAll.employeeNum}" />
            <input type="hidden" name="pageNo" value="${employeeListPagePart.currentPage}" />
            <input type="submit" value="수정" class="custom-button">
        </c:if>
    </form>

    <!-- 삭제하기 버튼 -->
    <form action="employeeInfoManage.do" method="POST" class="button-form">
        <input type="hidden" name="employeeNumDelete" value="${infoRequestAll.employeeNum}" />
        <input type="hidden" name="employeeKnameDelete" value="${infoRequestAll.employeePsnl_kname}" />
        <input type="hidden" name="pageNo" value="${employeeListPagePart.currentPage}" />
        <input type="submit" value="삭제" class="custom-button delete-button">
    </form>
    </div>
   </div>

    <!-- 수정하기 버튼을 누르면, 등록창(수정창)을 띄우고, 등록창에 등록 말고 수정기능의 수정버튼을 띄운다 -->
</c:if>
	
<script>
  $(document).ready(function() {
    $("#employeeEply_join").datepicker({
      dateFormat: "yy-mm-dd" // 필요한 날짜 형식으로 설정
    });
    $("#employeeEply_resignation").datepicker({
      dateFormat: "yy-mm-dd" // 필요한 날짜 형식으로 설정
    });
  });
  
  $(document).ready(function() {
	    // 등록 버튼 클릭 시 알람 표시
	    $(".register-button").click(function() {
	        if (!checkFields()) {
	            var name = $("#employeePsnl_kname").val();
	            showRegisterAlert(name);
	        }
	    });

	    // 수정 버튼 클릭 시 알람 표시
	    $(".modify-button").click(function() {
	        if (!checkFields()) {
	            var name = $("#employeePsnl_kname").val();
	            showModifyAlert(name);
	        }
	    });

	    // 삭제 버튼 클릭 시 알람 표시
	    $(".delete-button").click(function() {
	        var name = $("#employeePsnl_kname").val();
	        showDeleteAlert(name);
	    });
	});

	// 등록 알람 표시 함수
	function showRegisterAlert(name) {
	    alert(name + "님의 정보가 등록되었습니다.");
	}

	// 수정 알람 표시 함수
	function showModifyAlert(name) {
	    alert(name + "님의 정보가 수정되었습니다.");
	}

	// 삭제 알람 표시 함수
	function showDeleteAlert(name) {
	    alert(name + "님의 정보가 삭제되었습니다.");
	}

	// 필드가 비어 있는지 확인하는 함수
	function checkFields() {
    var isEmpty = false;
    $("input[type='text']").not("[name='employeeEply_resignation']").each(function() {
        if ($(this).val() === "") {
            isEmpty = true;
        }
    });
    return isEmpty;
}
  </script>



</body>
</html>