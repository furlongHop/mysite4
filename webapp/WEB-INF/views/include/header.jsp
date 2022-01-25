<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="header" class="clearfix">
		<h1>
			<!--${pageContext.request.contextPath}: server module path 명(해당 경로명이 바뀔 때마다 적용) -->
			<a href="${pageContext.request.contextPath}/main">MySite</a>
		</h1>

			<c:choose>
			<c:when test="${empty authUser}">
			<!-- 로그인 실패, 로그인 전 -->
			<ul>
				<li><a href="${pageContext.request.contextPath}/user/loginForm" class="btn_s">로그인</a></li>
				<li><a href="${pageContext.request.contextPath}/user/joinForm" class="btn_s">회원가입</a></li>
			</ul>
			</c:when>
			
			<c:when test="${authUser != null}">
			<!-- 로그인 성공 -->
			<ul>
				<li>${sessionScope.authUser.name} 님 안녕하세요^^</li>
				<li><a href="${pageContext.request.contextPath}/user/logout" class="btn_s">로그아웃</a></li>
				<li><a href="${pageContext.request.contextPath}/user/modifyForm" class="btn_s">회원정보수정</a></li>
			</ul>
			</c:when>
		</c:choose>

	</div>
	<!-- //header -->

	<div id="nav">
		<ul class="clearfix">
			<li><a href="">입사지원서</a></li>
			<li><a href="">게시판</a></li>
			<li><a href="">갤러리</a></li>
			<li><a href="${pageContext.request.contextPath}/guestbook/list">방명록</a></li>
		</ul>
	</div>
	<!-- //nav -->

</body>
</html>