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
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<div class="row my-5">
			<div class="col"></div>
			<div class="col-8">
				<form id="trainer_verification_form"
					action="./trainer_verification_action" method="post"
					enctype="multipart/form-data">
					<div class="row my-5">
						<div class="col">
							<h1>트레이너 인증</h1>
						</div>
					</div>
					<div class="row my-2">
						<div class="col-2 mt-2">아이디</div>
						<div class="col">
							<input type="text" readonly class="form-control-plaintext"
								value="${usertrnrInfo.mbr_id}">
						</div>
					</div>
					<div class="row my-2">
						<div class="col-2 mt-2">닉네임</div>
						<div class="col">
							<input type="text" readonly class="form-control-plaintext"
								value="${usertrnrInfo.mbr_nick}">
						</div>
					</div>
					<div class="row mt-3">
						<div class="col-2 mt-2">자격증 사진</div>
						<div class="col">
							<input class="form-control" type="file" id="file"
								name="upload_file" multiple="multiple" accept="image/*">
							<p class="help-block">자격증 사진을 첨부해주세요.</p>
						</div>
						<div class="col"></div>
					</div>
					<div class="row mt-5">
						<div class="col justify-content_center">
							<a href="./mypage_update"
								class="btn btn-outline-danger text-center btn-block">취소</a>
						</div>
						<div class="col"></div>
						<div class="col justify-content_center">

							<c:choose>
								<c:when test="${userVrfInfo.mbr_vrf=='거절'}">
									<button type="button" onClick="submit_process()"
										class="btn btn-warning text-center btn-block">재신청하기</button>
								</c:when>
								<c:otherwise>
									<button type="button" onClick="submit_process()"
										class="btn btn-warning text-center btn-block">제출</button>
								</c:otherwise>

							</c:choose>

						</div>
					</div>
				</form>
			</div>
			<div class="col"></div>
		</div>
	</div>

	<script>
		function submit_process() {
			var file = document.getElementById("file");

			if (file.value.length == 0) {
				alert("사진을 첨부해주세요.");
				file.focus();
				return;
			}

			trainer_verification_form.submit();
		}
	</script>

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