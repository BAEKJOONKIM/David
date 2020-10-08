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
<title>회원가입</title>
<script type="text/javascript">
	var has_confirm_id = false;

	function id_check_action() {

		var id = document.getElementById("id").value;
		var possible = document.getElementById("possible");
		var impossible = document.getElementById("impossible");
		//AJAX 호출... 코드 시작
		var xmlhttp = new XMLHttpRequest();

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { //성공적으로 응답이 온 시점에 작동....

				if (xmlhttp.responseText == 'true') {
					//사용 불가능
					has_confirm_id = false;

					impossible.setAttribute('class', 'alert alert-danger');
					possible
							.setAttribute('class', 'alert alert-success d-none');
				} else {
					has_confirm_id = true;

					impossible.setAttribute('class',
							'alert alert-danger d-none');
					possible.setAttribute('class', 'alert alert-success');
				}
			}
		};

		//xmlhttp.open("get", "./login_page?m_id="+id, true);
		xmlhttp.open("post", "./id_check_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send("mbr_id=" + id);
	}
	
	function send_Checkmail() {
		var email = document.getElementById("email").value;
		var possible = document.getElementById("possible");
		var impossible = document.getElementById("impossible");
		//AJAX 호출... 코드 시작
		var xmlhttp = new XMLHttpRequest();

		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) { //성공적으로 응답이 온 시점에 작동....

				
			}
		};

		//xmlhttp.open("get", "./login_page?m_id="+id, true);
		xmlhttp.open("post", "./confirm_email_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send();
	}

	function signup_action() {

		var sign_up_form = document.getElementById("sign_up_form");

		var input_id = document.getElementById("id");
		var input_password = document.getElementById("password");
		var input_passwordCheck = document.getElementById("passwordCheck");
		var input_name = document.getElementById("name");
		var input_cellPhone = document.getElementById("cellPhone");
		var input_email = document.getElementById("email");
		var input_nickName = document.getElementById("nickName");

		if (input_id.value == "") {
			alert("ID를 입력해주세요");
			input_id.focus();
			return;
		}
		if (input_password.value == "") {
			alert("비밀번호를 입력해주세요");
			input_password.focus();
			return;
		}
		if (input_password.value != input_passwordCheck.value) {
			alert("비밀번호를 확인해주세요");
			input_passwordCheck.focus();
			return;
		}
		if (input_name.value == "") {
			alert("이름을 입력해주세요");
			input_name.focus();
			return;
		}
		if (input_cellPhone.value == "") {
			alert("전화번호를 입력해주세요");
			input_cellPhone.focus();
			return;
		}
		if (input_email.value == "") {
			alert("이메일을 입력해주세요");
			input_email.focus();
			return;
		}
		if (input_nickName.value == "") {
			alert("닉네임을 입력해주세요");
			input_nickName.focus();
			return;
		}

		if (!has_confirm_id) {
			alert("아이디 존재 여부 확인을 해주세요");
			input_id.focus();
			return;
		}

		sign_up_from.submit();
	}
</script>
</script>
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">
<style>
div.title {
	font-family: 'Jua';
	font-size: 20px;
}

div.form-group {
	font-family: 'Jua';
	font-size: 20px;
}

div.form-check {
	font-family: 'Jua';
	font-size: 20px;
}

label.sex {
	font-family: 'Jua';
	font-size: 20px;
}

div.button-group {
	font-family: 'Jua';
	font-size: 20px;
}
</style>
</head>
<body>
	<jsp:include page="./nav.jsp"></jsp:include>
	<div>
		<hr class="my-4">
	</div>
	<div class="row">
		<div class="col"></div>
		<div class="col">


			<div class="title">
				<h1>회원가입</h1>
			</div>
			<form id="sign_up_from" action="./signup_action" method="post">
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputEmail4">ID</label> <input name="mbr_id"
							type="text" class="form-control" id="id">
					</div>
					<div class="form-group col-md-6">
						<label for="inputEmail4"></label> <input type="button"
							id="idCheckButton" onclick="id_check_action()"
							class="btn btn-warning form-control" value="아이디 중복확인">
					</div>

				</div>
				<div id="possible" class="alert alert-success d-none" role="alert">
					사용 가능한 아이디입니다</div>
				<div id="impossible" class="alert alert-danger d-none" role="alert">
					사용 불가능한 아이디입니다</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputPassword4">비밀번호</label> <input name="mbr_pw"
							type="password" class="form-control" id="password">
					</div>
					<div class="form-group col-md-6">
						<label for="inputPassword4">비밀번호확인</label> <input name="pw_check"
							type="password" class="form-control" id="passwordCheck">
					</div>
				</div>

				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputEmail4">이름</label> <input name="mbr_name"
							type="text" class="form-control" id="name">
					</div>
					<div class="form-group col-md-6">
						<label for="inputPassword4">핸드폰 번호</label> <input name="mbr_nmbr"
							type="text" class="form-control" id="cellPhone"
							placeholder="숫자만 입력해주세요">
					</div>
				</div>
				<div class="form-group">
					<label for="exampleInputEmail1">이메일</label> <input name="mbr_emil"
						type="email" class="form-control" id="email"
						aria-describedby="emailHelp" placeholder="이메일을 정확히 입력해주세요">
					<small id="emails" class="form-text text-muted">이메일은
						본인확인용으로 이용됩니다</small>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<label for="inputEmail4">닉네임</label> <input name="mbr_nick"
							type="text" class="form-control" id="nickName">
					</div>
					<label class="sex" for="inputsex">성별</label>
					<div class="form-check form-check-inline">
						<input name="mbr_sex" class="form-check-input" type="radio"
							name="inlineRadioOptions" id="sexM" value="M" checked> <label
							class="form-check-label" for="inlineRadio1">남성</label>
					</div>
					<div class="form-check form-check-inline">
						<input name="mbr_sex" class="form-check-input" type="radio"
							name="inlineRadioOptions" id="sexF" value="F"> <label
							class="form-check-label" for="inlineRadio2">여성</label>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="inputState">년</label> <select name="mbr_year"
							id="year" class="form-control">
							<option>1990</option>
							<option>1991</option>
							<option>1992</option>
							<option>1993</option>
							<option>1994</option>
							<option selected>1995</option>
							<option>1996</option>
							<option>1997</option>
							<option>1998</option>
							<option>1999</option>
						</select>
					</div>
					<div class="form-group col-md-4">
						<label for="inputState">월</label> <select name="mbr_month"
							id="month" class="form-control">
							<option selected>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
						</select>
					</div>
					<div class="form-group col-md-4">
						<label for="inputState">일</label> <select name="mbr_day" id="day"
							class="form-control">
							<option selected>1</option>
							<option>2</option>
							<option>3</option>
							<option>4</option>
							<option>5</option>
							<option>6</option>
							<option>7</option>
							<option>8</option>
							<option>9</option>
							<option>10</option>
							<option>11</option>
							<option>12</option>
							<option>13</option>
							<option>14</option>
							<option>15</option>
							<option>16</option>
							<option>17</option>
							<option>18</option>
							<option>19</option>
							<option>20</option>
							<option>21</option>
							<option>22</option>
							<option>23</option>
							<option>24</option>
							<option>25</option>
							<option>26</option>
							<option>27</option>
							<option>28</option>
							<option>29</option>
							<option>30</option>
							<option>31</option>
						</select>
					</div>
				</div>


			</form>

			<div class="button-group">
			<button id="signUpButton" onclick="signup_action()"
				class="btn btn-warning">완료</button>
			<a href="./login_page" role="button" class="btn btn-danger">취소</a>
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