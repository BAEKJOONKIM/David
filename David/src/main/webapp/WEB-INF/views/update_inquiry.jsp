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
</head>
<body>
	
	<jsp:include page="./nav.jsp"></jsp:include>

	<div class="row">
		<div class="col"></div>
		<div class="col-8">
			<form action="update_inquiry_action?inq_idx=${inquiry.inquiryVo.inq_idx }" method="post">
				<div class="row">
					<div class="col">
						<h1>문의수정</h1>
					</div>
				</div>
				<div class="row mt-3">
					<div class="col-2">작성자</div>
					<div class="col">${user.mbr_nick }</div>
				</div>
				<div class="row my-3">
					<div class="col-2 mt-2">제목</div>
					<div class="col">
						<input type="text" class="form-control" name="inq_ttl" value="${inquiry.inquiryVo.inq_ttl }">
					</div>
				</div>
				<div class="row">
					<div class="col-2 mt-5">내용</div>
					<div class="col">
						<textarea name="inq_con" class="form-control" rows="7" cols="40">${inquiry.inquiryVo.inq_con }</textarea>
						<!--  <input type="hidden" name="inq_idx" value="${inquiry.inquiryVo.inq_idx }"> -->
					</div>
				</div>
				<div class="row mt-4">
					<div class="col"></div>
					<div class="col">
						<input type="reset" value="취소" onClick="history.back();"
							class="btn btn-outline-danger">

						<button type="submit" class="btn btn-warning">제출</button>
					</div>
					<div class="col"></div>
				</div>
			</form>
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