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
</script>

</head>
<body>
	<jsp:include page="./nav.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col"></div>
			<div class="col-8">
				<form action="./update_auction_action" method="post"
					enctype="multipart/form-data">
					<input type="hidden" name="auc_idx"value="${data.auctionInfo.auc_idx }"> 
					<p class="text-center title my-5">경매 수정</p>
					<hr>
					<br>
					<div class="row">
						<div class="col-2 mt-2 words">제품명</div>
						<div class="col words">
							경매명: <input type="text" value="${data.auctionInfo.auc_pnm }" name="auc_pnm">
						</div>
					</div>
					
					<div class="row mt-4">
						<div class="col-2 words">작성자</div>
						<div class="col words">${sessionUserData.mbr_nick}</div>
					</div>
					
					<div class="row mt-4">
						<div class="col-2 words">제품설명</div>
						<div class="col words">
							<textarea class="form-control" rows="6" name="auc_pexp">${data.auctionInfo.auc_pexp }</textarea>
						</div>
					</div>
					
					
					<div class="row mt-4">
						<div class="col-2 words">현재 파일</div>
						<div class="col words">
							 ${data.firstImg.i_oriname }
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox" id="inlineRadio1"
								name="deletingFile" value="${data.firstImg.i_idx }"> <label
								class="form-check-label" for="inlineRadio1">삭제</label>
						</div>
						<br>
						<c:if test="${!empty data.imgList }">
							<c:forEach items="${data.imgList }" var="img">
										${img.i_oriname }
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox"
										id="inlineRadio1" name="deletingFile" value="${img.i_idx }">
									<label class="form-check-label" for="inlineRadio1">삭제</label>
								</div>
								<br>
							</c:forEach>
						</c:if>
								
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
					
					<div class="row my-5">
					<div class="col">
						<a href="./auction_list_page" role="button"
							class="btn btn-outline-secondary btn-block">취소</a>
					</div>
					<div class="col"></div>
					<div class="col">
						<input type="submit" value="등록"
							class="btn btn-outline-warning btn-block">
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