<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">

<script src="https://kit.fontawesome.com/52e12d5aa9.js"
	crossorigin="anonymous"></script>


<style type="text/css">
div.sc {
	border-color: #ffdb4d;
}

.shoppingcart {
	color: #ffdb4d;
}

div.wk {
	border-color: #ffdb4d;
}

.sport {
	color: #ffdb4d;
}

div.cc {
	border-color: #ffdb4d;
}

.customer {
	color: #ffdb4d;
}

div.mp {
	border-color: #ffdb4d;
}

.mypage {
	color: #ffdb4d;
}

div.bd {
	border-color: #ffdb4d;
}

.board {
	color: #ffdb4d;
}

div.ab {
	border-color: #ffdb4d;
}

.about {
	color: #ffdb4d;
}

p.words {
	font-family: 'Noto Sans KR';
	font-weight: light;
	font-size: 13px;
}

h5.words {
	font-family: 'Do Hyeon';
}

button.words {
	font-family: 'Do Hyeon';
}

div.card {
	height: 220px;
	width: 350px;
}

a.words {
	font-family: 'Do Hyeon';
}
</style>

</head>
<body>

	<jsp:include page="./nav_simple.jsp"></jsp:include>

	<div class="container bg-white">
		<!-- <div class="row justify-content-center">
		<img src="./resources/david_logo.PNG" style="height:220px;width:350px; margin-top:30px">
		</div>
		<hr> -->
		<br>
		<div class="row my-5 mx-auto ">
			<div class="card  ab mr-4">
				<a href="#">
					<div class="card-header">
						<i class="fas fa-handshake fa-3x about"></i>
					</div>
				</a>
				<div class="card-body">
					<h5 class="card-title words">알아갑시다</h5>
					<p class="card-text words">우리 홈페이지에 대해서 알아봅시다</p>
				</div>
			</div>

			<div class="card   wk  mr-4">
				<a href="./workout_page">
					<div class="card-header">
						<i class="fas fa-fire-alt fa-3x sport"></i>
					</div>
				</a>
				<div class="card-body">
					<h5 class="card-title words">운동합시다</h5>
					<p class="card-text words">운동의 전문가들이 직접 올린 영상을 보고 집에서도 운동하세요!</p>
				</div>
			</div>

			<div class="card  sc">
				<a href="./auction_list_page"><div class="card-header">
						<i class="fas fa-shopping-cart fa-3x shoppingcart"></i>
					</div> </a>
				<div class="card-body">
					<h5 class="card-title words">쇼핑합시다</h5>
					<p class="card-text words">필요한운동기구들이 있으신가요? 아니면 더 자신에게 맞는 운동기구를
						찾으셔서 필요 없어진 기구가 있으신가요?</p>
				</div>
			</div>
		</div>
		<div class="row mb-3  mx-auto">
			<div class="card  bd  mr-4">
				
					<div class="card-header">
						<a href="./flex_main_page"><i class="fas fa-money-check-alt fa-3x board"></i></a>
						<a href="./board_main_page"><i class="fas fa-comments fa-3x board ml-4"></i></a>
					</div>
				
				<div class="card-body">
					<h5 class="card-title words">소통합시다</h5>
					<p class="card-text words">My Flex에서 당신의 운동을 뽐내보세요! 운동 말고도 하고
						싶은 얘기가 있다면 소통하세요!</p>
				</div>
			</div>

			<div class="card  cc  mr-4">
				<a href="./customer_center">
					<div class="card-header">
						<i class="fas fa-bullhorn fa-3x customer"></i>
					</div>
				</a>
				<div class="card-body">
					<h5 class="card-title words">고객센터</h5>
					<p class="card-text words">궁금한 것이 있다면 여기를 눌러주세요!</p>
				</div>
			</div>



			<div class="card mp">
				<a href="./mypage_history">
					<div class="card-header">
						<i class="fas fa-user fa-3x mypage"></i>
					</div>
				</a>
				<div class="card-body">
					<h5 class="card-title words">마이페이지</h5>
					<p class="card-text words">로그인하고 본인의 정보를 관리하세요!</p>
				</div>
			</div>
		</div>
		<br> <br>
		<c:if test="${empty sessionUserData }">
			<div class="row">
				<div class="col"></div>
				<div class="col">
					<a href="./login_page" role="button"
						class="btn btn-warning btn-block words"
						style="height: 50px; padding-top: 10px; font-size: 18px">로그인</a>
				</div>
				<div class="col"></div>
				<div class="col">
					<a href="./signup_page" role="button"
						class="btn btn-warning btn-block words"
						style="height: 50px; padding-top: 10px; font-size: 18px">회원가입</a>
				</div>
				<div class="col"></div>
			</div>
		</c:if>

		<br> <br> <br>
		<hr>
		<div class="row mx-auto">
			<div class="col">
				<a href="./flex_main_page" class="mr-2 text-dark font-weight-bold">FLEX</a>
				<a href="./board_main_page" class="mr-2 text-dark font-weight-bold">자유게시판</a>
				<a href="./auction_list_page"
					class="mr-2 text-dark font-weight-bold">경매</a> <a
					href="./workout_page" class="mr-2 text-dark font-weight-bold">워크아웃</a>
				<a href="./customer_center" class="mr-2 text-dark font-weight-bold">고객센터</a>
				<p>Designed and built with all the love by our lovely DAVID team
					members.</p>
			</div>
			<div class="col-4">
				<img src="./resources/fake_QRCode.gif"
					style="width: 60px; height: 60px" class="float-right mr-auto">
				<img src="./resources/fake_QRCode.gif"
					style="width: 60px; height: 60px" class="float-right">
			</div>
		</div>
		<br>
		<br>

	</div>



	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
		integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>
</body>
</html>