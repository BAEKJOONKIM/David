<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link href="https://fonts.googleapis.com/css?family=Jua&display=swap" rel="stylesheet">

<style>
div.words {
	font-family: 'Jua';
}

a.words {
	font-family: 'Jua';
}

input.words {
	font-family: 'Jua';
}

</style>


</head>
<body>

	<jsp:include page="./nav.jsp"></jsp:include>

	<div class="container">

		<div class="row">
			<div class="col"></div>
			<div class="col-6">
				<form action="./login_action">
					<div class="row mt-5">
						<div class="col text-center words">
							<!-- 로그인 로고 -->
							<p style="font-size:38px">로그인</p>
						</div>
					</div>
					<div class="row mt-4">
						<!-- id 박스 -->
						<div class="col-3 text-center mt-2 words" style="font-size: 20px">ID
							:</div>
						<div class="col ">
							<input type="text" class="form-control" name="mbr_id">
						</div>
						<div class="col-1"></div>
					</div>
					<div class="row my-2">
						<!-- pw 박스 -->
						<div class="col-3 text-center mt-2 words" style="font-size: 20px">PW
							:</div>
						<div class="col">
							<input type="password" class="form-control" name="mbr_pw">
						</div>
						<div class="col-1"></div>
					</div>
					<div class="row mt-5">
						<div class="col">
							<input class="btn btn-warning btn-block words"
								type="submit" style="font-size: 17px; height: 45px ;padding-top: 10px" value="로그인">
						</div>
						<div class="col">
							<a href="./signup_page"
								class="btn btn-warning btn-block words"
								style="font-size: 17px; height: 45px; padding-top: 10px">회원 가입</a>
						</div>
						<div class="col">
							<a href="./managerlogin_page"
								class="btn btn-outline-secondary btn-block words"
								style="font-size: 17px; height: 45px;padding-top: 10px">관리자로그인</a>
						</div>
					</div>
				</form>
			</div>
			<div class="col"></div>
		</div>
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