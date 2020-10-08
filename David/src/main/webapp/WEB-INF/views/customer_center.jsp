<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">

<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div.head {
	font-family: 'Do Hyeon';
	font-size: 50px;
	padding-top: 10px;
}

a.linkbutton {
	font-family: 'Do Hyeon';
	font-size: 18px;
	padding:20px 20px 20px 20px;
}
</style>

</head>

<body>

	<jsp:include page="./nav.jsp"></jsp:include>



	<div class="row">
		<div class="col">
			<br> <br> <br> <br> <br> <br><br>
			<div class="btn-group-vertical float-right" role="group">
				<a href="#" class="linkbutton  btn-warning btn border border-dark" role="button">자주
					묻는 질문</a>
					 <a href="./write_inquiry_page" class="linkbutton btn-warning  btn border border-dark"
					role="button ">1대1문의</a>
					 <a href="./mypage_inquiry_list"
					class="linkbutton btn-warning btn border border-dark" role="button">나의 문의</a>
			</div>
		</div>
		<div class="col-7">
			<div class="row">
				<div class="col head">
					<p class=" my-4 text-center ">고객센터</p>
				</div>
			</div>
			<hr>
			<div class="row">
				<table class="table  text-center">
					<thead>
						<tr>
							<th scope="col" style="width:100px">#</th>
							<th scope="col" style="width:400px">제목</th>
							<th scope="col" style="width:400px">작성일</th>
						</tr>
					</thead>
					<tbody>
						<!-- 자주 묻는 질문 글 -->
				</table>
			</div>
		</div>
		<div class="col"></div>
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