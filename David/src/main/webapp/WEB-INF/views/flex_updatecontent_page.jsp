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
p.title {
	font-family: 'Do Hyeon';
	font-size: 40px;
}

div.words {
	/*font-family: 'Jua';*/
	font-size: 15px;
	font-weight: bold;
}

a.words {
	/*font-family: 'Jua';*/
	font-size: 15px;
	font-weight: bold;
	padding-top: 10px;
}

input.words {
	/*font-family: 'Jua';*/
	font-size: 15px;
	font-weight: bold;
}

input.form-control {
	font-size: 14px;
}

textarea.form-control {
	font-size: 14px;
}
</style>


</head>
<body>
	<jsp:include page="./nav.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col"></div>
			<div class="col-8">
				<form action="./update_content_action" method="post"
					enctype="multipart/form-data">
					<p class="text-center title my-5">정보 수정</p>
					<hr>
					<br>
					<div class="row">
						<div class="col-2 words">제목 :</div>
						<div class="col ">
							<input name="flx_ttl" type="text" class="form-control"
								value="${data.my_flexcontentVo.flx_ttl }">
						</div>
					</div>
					<div class="row mt-4">
						<div class="col-2 words">내용 :</div>
						<div class="col">
							<textarea id="content" class="form-control" name="flx_con"
								rows="3"  cols="50">${data.my_flexcontentVo.flx_con }</textarea>
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 words"></div>
						<div class="col">
							<input type="hidden" value="${data.my_flexcontentVo.flx_idx }"
								name="flx_idx">
						</div>
					</div>

					<div class="row my-4">
						<div class="col-2 words">파일 :</div>
						<div class="col">
							<input id="iurl" type="file" name="iurl" multiple
								accept="image/*">
						</div>
					</div>

					<!--  			
					<div class="row">
						<div class="col">파일</div>
						<div class="col">							
							<input type="file" name="iurl" multiple
									accept="image/*">
						</div>
					</div>
					-->
					<br>
					<div class="row mt-5">
						<div class="col">
							<a role="button"
								class="btn btn-outline-secondary btn-block words"
								href="./flex_readcontent_page?flx_idx=${data.my_flexcontentVo.flx_idx }">취소</a>
						</div>
						<div class="col"></div>
						<div class="col">
							<input type="submit" class="btn btn-warning btn-block words"
								value="수정하기">
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