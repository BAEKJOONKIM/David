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
<meta charset="UTF-8">
<title>David의 동영상 업로드</title>
<script type="text/javascript">
	function submit_process() {

		var write_content_form = document.getElementById("write_content_form");

		var input_surl = document.getElementById("surl");
		var input_vurl = document.getElementById("vurl");
		var input_title = document.getElementById("wrk_ttl");
		var input_content = document.getElementById("wrk_con");

		if (input_surl.value == "") {
			alert("썸네일을첨부해주세요");
			input_surl.focus();
			return;
		}
		if (input_vurl.value == "") {
			alert("동영상을첨부해주세요");
			input_vurl.focus();
			return;
		}
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

		write_content_form.submit();
	}
</script>
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">
<style>
div.form-group {
	font-family: 'Jua';
	font-size: 20px;
}

div.button-group {
	font-family: 'Jua';
	font-size: 20px;
}

div.title {
	font-family: 'Jua';
	font-size: 20px;
}
</style>
</head>
<body>
	<c:if test="${usertrnrInfo.mbr_trnr == 'F' }">
		<jsp:include page="./nav.jsp"></jsp:include>
		<div>
			<hr class="my-4">
		</div>
		<div class="row">
			<div class="col-2"></div>
			<div class="col text-center">
				<div class="row">
					<div class="col-4">
						<img src="/upload/spring_David_workout_upload/sorry_cat.jpg">
					</div>
					<div class="col">
						<h1 class="my-5 font-weight-normal">잘못된 접근입니다</h1>
						<div class="row my-5"></div>
						<div class="row my-5"></div>
						<div class="row my-5"></div>
						<div class="row my-5"></div>
						<div class="row my-5">
							<div class="col"></div>
							<div class="col"></div>
							<div class="col"></div>
						</div>

					</div>

				</div>
				<h1>트레이너만 접근 가능합니다</h1>
				<br> <a type="button" class="btn btn-warning"
					href="trainer_verification?mbr_idx=${sessionUserData.mbr_idx }">트레이너
					신청하러 가기</a> <a type="button" class="btn btn-warning" href="main_page">메인페이지
					가기</a>
			</div>
			<div class="col-2"></div>
		</div>
		<div>
			<hr class="my-4">
		</div>
	</c:if>
	<c:if test="${usertrnrInfo.mbr_trnr == 'T' }">
		<jsp:include page="./nav.jsp"></jsp:include>
		<div>
			<hr class="my-4">
		</div>
		<div class="row">
			<div class="col"></div>
			<div class="col">
				<div class="title">
					<h1>영상 업로드</h1>
				</div>
				<form id="write_content_form" action="./write_workout_action"
					method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label for="nick">작성자<br>${sessionUserData.mbr_nick }</label>
					</div>
					<div class="form-group">
						<label for="file">썸네일 이미지</label> <input class="form-control"
							type="file" id="surl" name="surl" />
						<p class="help-block">썸네일 이미지 첨부</p>
					</div>
					<div class="form-group">
						<label for="file">동영상</label> <input class="form-control"
							type="file" id="vurl" name="vurl" />
						<p class="help-block">동영상 파일 첨부</p>
					</div>
					<div class="form-group">
						<label for="text">제목</label> <input class="form-control"
							type="text" id="wrk_ttl" name="wrk_ttl" placeholder="제목 입력" />
					</div>
					<div class="form-group">
						<label for="exampleFormControlTextarea1">내용</label>
						<textarea class="form-control" id="wrk_con" rows="3"
							name="wrk_con"></textarea>
					</div>
					<div class="form-group">
						<label for="text">업로드 위치</label> <select
							class="custom-select custom-select-lg mb-3" id="wrk_cat"
							name="wrk_cat">
							<option value="1" selected>등</option>
							<option value="2">어깨</option>
							<option value="3">가슴</option>
							<option value="4">팔</option>
							<option value="5">복부</option>
							<option value="6">하체</option>
							<option value="7">운동기구가이드</option>
						</select>
					</div>


				</form>
				<div class="button-group">
					<button class="btn btn-warning" onclick="submit_process()">업로드</button>
					<a type="reset" href="./workout_page" class="btn btn-danger">취소</a>
				</div>
			</div>
			<div class="col"></div>
		</div>
	</c:if>








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