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

	<!-- 등록창 -->
	<form action="register.do" method="POST">
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