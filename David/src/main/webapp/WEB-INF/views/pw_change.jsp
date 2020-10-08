<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
<jsp:include page="./nav.jsp"></jsp:include>

<div class="container">
	<div class="row">
		<div class="col"></div>
		<div class="col-5">
		<form id="pw_change_form" action="./pw_change_action" method="post">
			<div class="row my-5">
				<h1>비밀번호 변경</h1>
			</div>
			<div class="row">
				<div class="col-4 mt-2">현재 비밀번호</div>
				<div class="col">
					<input id="pw" type="password" class="form-control" name="curr_pw">
				</div>
			</div>
			<div class="row my-2">
				<div class="col-4 mt-2">새 비밀번호</div>
				<div class="col">
					<input id="npw" type="password" class="form-control" name="new_pw">
				</div>
			</div>
				<div class="row my-2">
				<div class="col-4 mt-2">새 비밀번호 확인</div>
				<div class="col">
					<input id="npw_co" type="password" class="form-control" name="new_pw_co">
				</div>
			</div>
			
			<div class="row my-2">
				<div class="col"></div>
				<div class="col" align="right">
					<a href="./mypage_update" class="btn btn-outline-danger" >취소</a>
					<button type="button" onClick="submit_process()" class="btn btn-warning">비밀번호 수정</button>
				</div>
			</div>
		</form>
		</div>
		<div class="col"></div>
	</div>
</div>

<script>
function submit_process(){
	//alert("안녕하세요");
	var pw = document.getElementById("pw");
	var npw = document.getElementById("npw");
	var npw_co = document.getElementById("npw_co");

	if(npw.value != npw_co.value){
		alert("새 비밀번호와 비밀번호 확인이 일치하지 않습니다.");
		input_npw_co.value = "";
		input_npw_co.focus();
		return;
	}
	
	pw_change_form.submit();
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