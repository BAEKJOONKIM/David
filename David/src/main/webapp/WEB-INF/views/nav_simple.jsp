<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua&display=swap"
	rel="stylesheet">

<style>
a.navbar-brand {
	font-family: 'Do Hyeon';
	font-size: 20px;
}

ul.top-right {
	font-family: 'Jua';
	font-size: 17px;
}
</style>

<nav class="navbar navbar-expand-lg navbar-light bg-warning">
	<a class="navbar-brand" href="main_page"> <img
			src="/upload/spring_David_workout_upload/david_logo.png" width="180"
			height="60">
		</a>


	<ul class="navbar-nav ml-auto top-right">
		<!-- <li class="nav-item"><a class="nav-link" href="./">sitemap <span
				class="sr-only">(current)</span>
		</a></li> -->
		<c:choose>
			<c:when test="${empty sessionUserData }">
				<li class="nav-item"><a class="nav-link" href="./login_page">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="./signup_page">회원가입</a></li>
				<li class="nav-item text-disabled mt-2 ml-2">환영합니다!</li>
			</c:when>
			<c:when test="${!empty sessionManagerData}">
				<li class="nav-item"><a class="nav-link" href="./main_page">메인페이지</a></li>
				<li class="nav-item"><a class="nav-link" href="./manager_page">메니저페이지</a></li>
				<li class="nav-item"><a class="nav-link "
					href="./logout_action">로그아웃</a></li>
				<li class="nav-item"><a class="nav-link " href="./my_page">${sessionManagerData.mbr_nick },
						안녕하세요!</a></li>
			</c:when>
			<c:otherwise>
				<li class="nav-item"><a class="nav-link" href="./main_page">메인페이지</a></li>
				<li class="nav-item"><a class="nav-link " href="./my_page">${sessionUserData.mbr_nick },안녕하세요!</a></li>
				<li class="nav-item"><a class="nav-link "
					href="./logout_action">로그아웃</a></li>
			</c:otherwise>
		</c:choose>
	</ul>

</nav>