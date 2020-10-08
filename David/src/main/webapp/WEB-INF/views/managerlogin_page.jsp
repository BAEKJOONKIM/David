<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자로그인</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Jua&display=swap"
	rel="stylesheet">
<style>
p.title {
	font-size:40px;
	font-family: 'Jua';
}

div.words {
	font-family: 'Jua';
}
button.words{
	font-family: 'Jua';
}
</style>
</head>
<body>
	<jsp:include page="./nav_simple.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col-3"></div>
			<div class="col">
			<br>
				<p class="my-5 text-center title">Manager Login</p><hr><br>
				<form action="./managerlogin_action" method="post">

					<div class="form-row mx-auto">
						<div class="form-group col-md-6 words">
							<label for="inputEmail4">ID</label> <input name="mbr_id"
								type="text" class="form-control" id="inputEmail4">
						</div>
						<div class="form-group col-md-6 words ">
							<label for="inputPassword4">Password</label> <input name="mbr_pw"
								type="password" class="form-control" id="inputPassword4">
						</div>
					</div>

					<div class="custom-control custom-radio words">
						<input type="radio" id="customRadio1" name="customRadio"
							class="custom-control-input"> <label
							class="custom-control-label" for="customRadio1">내가
							왕이뜨아ㅏㅏㅏㅏ!!!!</label>
					</div>
					<div class="row">
						<div class="col"></div>
						<div class="col"></div>
						<div class="col-mx-auto">
							<button type="submit" class="btn btn-warning words">LOGIN</button>
						</div>

					</div>
				</form>
			</div>
			<div class="col-3"></div>
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