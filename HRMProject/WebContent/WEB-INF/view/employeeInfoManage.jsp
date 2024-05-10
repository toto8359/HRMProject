<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	[사원정보관리]
	<br />
	<br />
	<br />
	<!-- 안내창 -->
	<c:if test="${!empty employeePsnl_kname}">
		<c:if test="${modifySuccess}">
			사원 ${employeePsnl_kname}의 사원정보 수정을 완료했습니다.
		</c:if>
		<c:if test="${joinSuccess}">
			사원 ${employeePsnl_kname}의 등록을 완료했습니다.
		</c:if>
	</c:if><br/>

	사원목록<br/>
	<!-- 사원정보목록창 -->
	<table border="1">
		<tr>
			<td>사원번호</td>
			<td>부서</td>
			<td>직급</td>
			<td>이름</td>
		</tr>
		<c:if test="${employeeListPagePart.hasNoEmployeeList()}">
			<tr>
				<td colspan="4">게시글이 없습니다.</td>
			</tr>
		</c:if>
		<c:forEach var="employeeEply" items="${employeeListPagePart.content}"><!-- page의 List<employeeEply> content 뜻 -->
			<tr>
				<td>${employeeEply.employeeNum}</td>
				<td>${employeeEply.employeeEply_depart}</td>
				<td>${employeeEply.employeeEply_position}</td>
				<!-- get으로 보내기 -->
				<td>
				<a href="employeeInfoManage.do?employeeNum=${employeeEply.employeeNum}&pageNo=${employeeListPagePart.currentPage}"><!-- 나중에 수정해야함 ,employNum으로-->
						<c:out value="${employeeEply.employeePsnl_kname}" /></a>
				</td>
			</tr>
		</c:forEach>
		<!-- 페이징 -->
		<c:if test="${employeeListPagePart.hasEmployeeList()}">
			<tr>
				<td colspan="4"><c:if test="${employeeListPage.startPage > 5}">
						<a href="employeeInfoManage.do?pageNo=${employeeListPagePart.startPage - 5}">[이전]</a>
					</c:if> <c:forEach var="pNo" begin="${employeeListPagePart.startPage}"
						end="${employeeListPagePart.endPage}">
						<a href="employeeInfoManage.do?pageNo=${pNo}">[${pNo}]</a>
					</c:forEach> <c:if test="${employeeListPagePart.endPage < employeeListPagePart.totalPages}">
						<a href="employeeInfoManage.do?pageNo=${employeeListPagePart.startPage + 5}">[다음]</a>
					</c:if></td>
			</tr>
		</c:if>
	</table>
	<br/>
	
	
	<!-- 등록창 -->
	<!-- 등록 버튼을 누른 적이 없으면 or 등록이 완료되면 -->
	<form action="employeeInfoManage.do" method="get">
		<c:if test="${!registerForm}">
			<input type="hidden" name="registerForm" value="registerForm" />
			<input type="submit" value="등록">
		</c:if>
	</form>
	<!-- 정보입력 후 등록 버튼을 누르면 -->
	<form action="employeeInfoManage.do" method="POST">
		<c:if test="${registerForm}">
			<p>
			<!-- 수정일 경우, 사원번호는 고정 -->
			<c:if test="${modifyInfo}">
				<input type="hidden" name="employeeNum" value="${employeeNumModify}" />
				사원번호: ${employeeNumModify}
			</c:if>
			<!-- 등록일 경우, 사원번호 입력하도록 -->
			<c:if test="${!modifyInfo}">
				사원번호:<br /> <input type="text" name="employeeNum"
					value="${param.employeeNum}">
				<c:if test="${errors.employeeNum}">사원번호를 입력하세요.</c:if>
			</c:if>
			</p>
			<p>
				국문이름:<br /> <input type="text" name="employeePsnl_kname"
					value="${param.employeePsnl_kname}">
				<c:if test="${errors.employeePsnl_kname}">국문이름을 입력하세요.</c:if>
			</p>
			<p>
				영문이름:<br /> <input type="text" name="employeePsnl_ename"
					value="${param.employeePsnl_ename}">
				<c:if test="${errors.employeePsnl_ename}">영문이름을 입력하세요.</c:if>
			</p>
			<p>
				내국인 외국인:<br /> <input type="text" name="employeePsnl_isForeigner"
					value="${param.employeePsnl_isForeigner}">
				<c:if test="${errors.employeePsnl_isForeigner}">내국인 외국인 여부를 입력하세요.</c:if>
			</p>
			<p>
				주민번호:<br /> <input type="text" name="employeePsnl_residentNumber"
					value="${param.employeePsnl_residentNumber}">
				<c:if test="${errors.employeePsnl_residentNumber}">주민번호를 입력하세요.</c:if>
				<c:if test="${errors.duplicateResidentNumber}">이미 사원정보가 존재합니다.</c:if>
			</p>
			<p>
				주소:<br /> <input type="text" name="employeePsnl_adress"
					value="${param.employeePsnl_adress}">
				<c:if test="${errors.employeePsnl_adress}">주소를 입력하세요.</c:if>
			</p>
			<p>
				전화번호:<br /> <input type="text" name="employeePsnl_phoneNumber"
					value="${param.employeePsnl_phoneNumber}">
				<c:if test="${errors.employeePsnl_phoneNumber}">전화번호를 입력하세요.</c:if>
			</p>
			<p>
				이메일:<br /> <input type="text" name="employeePsnl_email"
					value="${param.employeePsnl_email}">
				<c:if test="${errors.employeePsnl_email}">이메일을 입력하세요.</c:if>
			</p>
			<p>
				SNS계정:<br /> <input type="text" name="employeePsnl_sns"
					value="${param.employeePsnl_sns}">
				<c:if test="${errors.employeePsnl_sns}">SNS계정을 입력하세요.</c:if>
			</p>
			<p>
				고용 형태:<br /> <input type="text" name="employeeEply_employType"
					value="${param.employeeEply_employType}">
				<c:if test="${errors.employeeEply_employType}">고용 형태를 입력하세요.</c:if>
			</p>
			<p>
				부서:<br /> <input type="text" name="employeeEply_depart"
					value="${param.employeeEply_depart}">
				<c:if test="${errors.employeeEply_depart}">부서를 입력하세요.</c:if>
			</p>
			<p>
				직급:<br /> <input type="text" name="employeeEply_position"
					value="${param.employeeEply_position}">
				<c:if test="${errors.employeeEply_position}">직급을 입력하세요.</c:if>
			</p>
			<p>
				입사날짜:<br /> <input type="text" name="employeeEply_join"
					value="${param.employeeEply_join}">
				<c:if test="${errors.employeeEply_join}">입사날짜를 입력하세요.</c:if>
			</p>
			<p>
				퇴사날짜:<br /> <input type="text" name="employeeEply_resignation"
					value="${param.employeeEply_resignation}">
			</p>
			<!-- 등록버튼으로 들어왔으면 등록 -->
			<c:if test="${!modifyInfo}">
				<input type="submit" value="등록">
			</c:if>
			<!-- 수정하기 버튼으로 들어왔으면 수정 -->
			<c:if test="${modifyInfo}">
				<input type="hidden" name="modifyInfo" value="modifyInfo" />
				<input type="submit" value="수정">
			</c:if>

		</c:if>
	</form>
	
	<!-- 사원정보창 -->
	<c:if test="${readInfo}">
		<table>
	    <tr>
	        <th>항목</th>
	        <th>정보</th>
	    </tr>
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
		</table>
		
		<!-- 수정하기 버튼을 누르면, 등록창을 띄우고, 등록창에 등록 말고 수정기능의 수정버튼을 띄운다 -->
		<form action="employeeInfoManage.do" method="get">
			<c:if test="${!modifyInfo}">
				<input type="hidden" name="registerForm" value="registerForm" /><!-- 등록창띄우기 -->
				<input type="hidden" name="modifyInfo" value="modifyInfo" /><!-- 수정버튼띄우기 -->
				<input type="hidden" name="employeeNumModify" value="${infoRequestAll.employeeNum}" /><!-- 사원번호 보내기 -->
				<input type="hidden" name="pageNo" value="${employeeListPagePart.currentPage}" /><!-- 현제 페이지 번호 보내기-->
				<input type="submit" value="수정">
			</c:if>
		</form>
	</c:if>
	

</body>
</html>