<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<script>
	function setting_date() {
		var today = new Date();
		var dd = String(today.getDate()).padStart(2, '0');
		var mm = String(today.getMonth() + 1).padStart(2, '0');
		var yyyy = today.getFullYear();
		today = yyyy + "-" + mm + "-" + dd;

		document.getElementById("sdate").setAttribute("min", today);
		document.getElementById("edate").setAttribute("min", today);

	}
	
	function submit() {
		var write_auction_form = document.getElementById("write_auction_form");

		var pnm = document.getElementById("pnm");
		var pexp = document.getElementById("pexp");
		var file = document.getElementById("file");
		var lcst = document.getElementById("lcst");
		var hcst = document.getElementById("hcst");
		var sdate = document.getElementById("sdate");
		var stime = document.getElementById("stime");
		var edate = document.getElementById("edate");
		var etime = document.getElementById("etime");

		if (pnm.value == "") {
			alert("경매품명을 입력해주세요");
			pnm.focus();
			return;
		}
		if (pexp.value == "") {
			alert("경매내용을 입력해주세요");
			pexp.focus();
			return;
		}
		if (file.value == "") {
			alert("사진을 첨부해주세요");
			return;
		}
		if (lcst.value == "") {
			alert("최저가를 입력해주세요");
			lcst.focus();
			return;
		}
		if (hcst.value != "" && hcst.value < lcst.value) {
			alert("최고가를 최저가보다 높게 입력해주세요");
			hcst.focus();
			return;
		}
		if (sdate.value == "") {
			alert("경매 시작 날짜를 입력해주세요");
			sdate.focus();
			return;
		}
		if (stime.value == "") {
			alert("경매 시작 시간을 입력해주세요");
			stime.focus();
			return;
		}
		if (edate.value == "") {
			alert("경매 종료 날짜를 입력해주세요");
			edate.focus();
			return;
		}
		if (etime.value == "") {
			alert("경매 종료 시간을 입력해주세요");
			etime.focus();
			return;
		}
		write_auction_form.submit();
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
<body onload="setting_date()">
	<jsp:include page="./nav.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col"></div>
			<div class="col-8">
				<form action="./write_auction_action" method="post"
					enctype="multipart/form-data" id="write_auction_form">
					<p class="text-center title my-5">경매 등록</p>
					<hr>
					<br>

					<div class="row">
						<div class="col-2 mt-2 words">제품명</div>
						<div class="col words">
							<input type="text" class="form-control" name="auc_pnm" id="pnm"
								placeholder="제품명을 작성해주세요">
						</div>
					</div>

					<div class="row mt-4">
						<div class="col-2 words">작성자</div>
						<div class="col words">${sessionUserData.mbr_nick}</div>
					</div>

					<div class="row mt-4">
						<div class="col-2 words">제품설명</div>
						<div class="col words">
							<textarea class="form-control" id="pexp" rows="6" name="auc_pexp"></textarea>
						</div>
					</div>

					<div class="row mt-4">
						<div class="col-2 words"></div>
						<div class="col">
							<input id="file" type="file" name="uploadImg" multiple="multiple">
						</div>
					</div>
					<div class="row ">
						<div class="col-2"></div>
						<div class="col ml-2">
							<label for="iurl" style="font-size: 13px" class="disabled">이미지
								업로드해주세요.(필수)</label>
						</div>
					</div>

					<div class="row mt-4">
						<div class="col-2 words">경매 최저가</div>
						<div class="col">
							<input type="number" class="form-control" name="auc_lcst"
								id="lcst">
						</div>
						<div class="col">원</div>
					</div>

					<div class="row mt-2">
						<div class="col-2 words">경매 최고가</div>
						<div class="col">
							<input type="number" class="form-control" name="auc_hcst"
								id="hcst">
						</div>
						<div class="col">원</div>
					</div>

					<div class="row mt-4">
						<div class="col-2 mt-2 words">경매 시작일시</div>
						<div class="col mt-2">
							<input type="date" name="auc_sdate" id="sdate" min="" max="" required> <input type="time"
								name="auc_stime" id="stime">
						</div>
					</div>

					<div class="row mt-4">
						<div class="col-2 mt-2 words">경매 종료일시</div>
						<div class="col mt-2">
							<input type="date" name="auc_edate" id="edate" min="" max=""> <input type="time"
								name="auc_etime" id="etime">
						</div>
					</div>
				</form>
				<div class="row my-5">
					<div class="col">
						<a href="./auction_list_page" role="button"
							class="btn btn-outline-secondary btn-block">취소</a>
					</div>
					<div class="col"></div>
					<div class="col">
						<input onClick="submit()" type="button" value="등록"
							class="btn btn-outline-warning btn-block">
					</div>
				</div>
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