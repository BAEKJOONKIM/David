<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>

<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap"
	rel="stylesheet">

<style>
p.title {
	font-family: 'Do Hyeon';
	font-size: 45px;
}

div.words {
	/*font-family: 'Do Hyeon';*/
	font-size: 17px;
	font-weight: bold;
}

a.words {
	/*font-family: 'Do Hyeon';*/
	font-size: 17px;
	font-weight: bold;
}

input.words {
	/*font-family: 'Do Hyeon';*/
	font-size: 17px;
	font-weight: bold;
}

button.words {
	/*font-family: 'Do Hyeon';*/
	font-size: 18px;
	font-weight: bold;
}

</style>


</head>
<body>

	<jsp:include page="./nav.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col " ></div>
			<div class="col-8" >
				<form action="write_inquiry_action" method="post">

					<p class="my-5 text-center title">문의하기</p>
					<hr>
					<br>
					<div class="row ">
						<div class="col-2 words">작성자:</div>
						<div class="col words">${user.mbr_nick }</div>
					</div>
					<div class="row mt-4">
						<div class="col-2 words">제목:</div>
						<div class="col">
							<input type="text" class="form-control" name="inq_ttl" placeholder="제목을 작성해주세요">
						</div>
					</div>
					<div class="row my-4">
						<div class="col-2 words">내용:</div>
						<div class="col">
							<textarea name="inq_con" class="form-control" rows="7" cols="40" placeholder="내용을 작성해주세요"></textarea>
						</div>
					</div>
					<br>
					<div class="row mt-5">
						<div class="col">
							<input type="reset" value="취소" onClick="history.back();" style="height:50px "
								class="btn btn-outline-secondary btn-block words">
						</div>
						<div class="col"></div>
						<div class="col">
							<button type="submit" class="btn btn-warning btn-block words" style="height:50px ">제출</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col"></div>
		</div>
			<br><br><br>
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

