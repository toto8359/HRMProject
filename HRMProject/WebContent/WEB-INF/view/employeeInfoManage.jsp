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
	<c:if test="${!empty employeePsnl_kname}">사원 ${employeePsnl_kname}의 등록을 완료했습니다.</c:if>

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
				<td><a
					href="read.do?no=${employeeEply.employeeNum}&pageNo=${employeeListPage.currentPage}"><!-- 나중에 수정해야함 ,employNum으로-->
						<c:out value="${employeeEply.employeePsnl_kname}" /><!-- 이름 클릭으로 바꿔야 할 듯, InfoRequest로 이름 같이 받아서 -->
				</a></td>
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

	<!-- 등록창 -->
	<form action="employeeInfoManage.do" method="POST">
		<!-- 등록 버튼을 누른 적이 없으면 or 등록이 완료되면 -->
		<c:if test="${!registerForm}">
			<input type="hidden" name="registerForm" value="registerForm" />
			<input type="submit" value="등록">
		</c:if>

		<!-- 등록 버튼을 누르면 -->
		<c:if test="${registerForm}">
			<p>
				사원번호:<br /> <input type="text" name="employeeNum"
					value="${param.employeeNum}">
				<c:if test="${errors.employeeNum}">사원번호를 입력하세요.</c:if>
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
				<c:if test="${errors.employeeEply_resignation}">퇴사날짜를 입력하세요.</c:if>
			</p>
			<input type="submit" value="등록">
		</c:if>
	</form>

</body>
</html>