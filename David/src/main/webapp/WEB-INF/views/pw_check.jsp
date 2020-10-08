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
				<form id="pw_check_form" action="./pw_check_action" method="post">
					<div class="row my-5">
						<div class="col text-center">
							<!-- 로그인 로고 -->
							<h1>비밀번호 재확인</h1>
						</div>
					</div>
					<div class="row my-2">
						<!-- id 박스 -->
						<div class="col-2 text-center mt-2">ID :</div>
						<div class="col ">
							<input id="mbr_id" type="text" readonly class="form-control-plaintext" name="mbr_id" value = "${sessionUserData.mbr_id }">
						</div>
					</div>
					<div class="row my-2">
						<!-- pw 박스 -->
						<div class="col-2 text-center mt-2">PW :</div>
						<div class="col">
							<input id="mbr_pw" type="password" class="form-control" name="mbr_pw">
						</div>
					</div>
					<div class="row mt-4">
						<div class="col">
						<button type="button" onClick="submit_process()" class="btn btn-warning btn-block">확인</button>
						</div>
					</div>
				</form>
			</div>
			<div class="col"></div>
		</div>
	</div>


<script>
function submit_process(){
	var mbr_id = document.getElementById("mbr_id");
	var mbr_pw = document.getElementById("mbr_pw");
	
	if(mbr_id.value.length==0){
		alert("로그인하세요");
		return;
	}

	if(mbr_pw.value.length ==0 ){
		alert("비밀번호를 입력해주세요");
		mbr_pw.value = "";
		mbr_pw.focus();
		return;
	}

	/*if(mbr_pw.value != "${sessionUserData.mbr_pw }" ){
		alert("비밀번호를 확인해주세요");
		mbr_pw.value = "";
		mbr_pw.focus();
		return;
	}*/

	pw_check_form.submit();
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