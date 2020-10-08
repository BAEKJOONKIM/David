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
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua&display=swap"
	rel="stylesheet">

<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function submit_process() {

		var write_content_form = document.getElementById("write_content_form");

		var input_title = document.getElementById("title");
		var input_content = document.getElementById("content");
		var input_iurl = document.getElementById("iurl");

		if (input_title.value == "") {
			alert("제목을입력해주세요");
			input_title.focus();
			return;
		}
		if (input_content.value == "") {
			alert("내용을입력해주세요");
			input_content.focus();
			return;
		}
		if (input_iurl.value == "") {
			alert("파일을첨부해주세요");
			input_iurl.focus();
			return;
		}
		write_content_form.submit();
	}
</script>



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
}

input.words {
	/*font-family: 'Jua';*/
	font-size: 15px;
	font-weight: bold;
}

input.form-control {
	font-size: 12px;
}
textarea.form-control {
	font-size: 12px;
}
</style>


</head>
<body>
	<jsp:include page="./nav.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col"></div>
			<div class="col-8">
				<form id="write_content_form" action="./write_content_action"
					method="post" enctype="multipart/form-data">
					<!-- 게시글쓰기로고 -->
					<p class="text-center title my-5">새 게시물</p>
					<hr>
					<br>

					<div class="row">
						<div class="col-2 words">작성자:</div>
						<div class="col words">${sessionUserData.mbr_nick }</div>
					</div>
					<div class="row mt-4">
						<!-- id, box -->
						<div class="col-2 words">제목 :</div>
						<div class="col">
							<input id="title" name="flx_ttl" type="text" class="form-control"
								placeholder="제목을 작성해주세요">
						</div>
					</div>
					<div class="row my-4">
						<!-- id, box -->
						<div class="col-2 words">내용 :</div>
						<div class="col">
							<textarea id="content" class="form-control" name="flx_con" placeholder="내용을 작성해주세요"
								rows="3"></textarea>
						</div>
					</div>
					<div class="row mb-2">
						<div class="col-2"></div>
						<div class="col ml-2">
							<!-- 파일 업로드하기 -->
							<div class="row">
								<label for="iurl" style="font-size: 13px" class="disabled">이미지
									업로드해주세요</label>
							</div>
							<div class="row">
								<input id="iurl" type="file" name="iurl" multiple
									accept="image/*">
							</div>
							<br>
						</div>
					</div>
					<div class="row mt-5">
						<div class="col">
							<a href="./flex_main_page" role="button"
								class="btn btn-outline-secondary btn-block">취소</a>
						</div>
						<div class="col"></div>
						<div class="col">
							<input type="button" class="btn btn-warning btn-block"
								onclick="submit_process()" value="등록">
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