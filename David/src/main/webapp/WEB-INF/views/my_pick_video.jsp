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
	<jsp:include page="./nav.jsp"></jsp:include>
	<div class="row">
		<div class="col-2" style="background-color: #FFE499; height: 1000px;">
			<h1 class="my-2 font-weight-bold" align="center">MY PAGE</h1>
			<div class="row mt-3" style="height: auto;">
				<div class="col">
					<div class="btn-group-vertical float-right">
						<a href="./mypage_history"
							class="btn btn-outline-warning btn-lg text-dark" id="all"
							style="width: 215px; background-color: #FFFFFF;">나의 활동</a> <a
							href="./pw_check"
							class="btn btn-outline-warning btn-lg text-dark" id="all"
							style="width: 215px; background-color: #FFFFFF;">회원정보</a> <a
							href="./mypage_auction_info"
							class="btn btn-outline-warning btn-lg text-dark" id="all"
							style="width: 215px; background-color: #FFFFFF;">경매내역</a> <a
							href="./mypage_inquiry_list"
							class="btn btn-outline-warning btn-lg text-dark" id="all"
							style="width: 215px; background-color: #FFFFFF;">나의 문의내역</a> <a
							href="./my_pick_video"
							class="btn btn-outline-warning btn-lg text-dark" id="all"
							style="width: 215px; background-color: #FEFFAD;">찜한 동영상</a>
					</div>
				</div>
			</div>
		</div>
		<div class="col my-5">
			<h4 class="mt-3 mb-5 font-weight-bold text-center">찜한 동영상</h4>

			<c:if test="${empty workOutPickList}">
				<h5 class="mt-3 mb-5 text-center">찜한 동영상이 없습니다.</h5>
			</c:if>

			<c:forEach items="${workOutPickList }" var="data">
				<div class="row mt-3">
					<div class="col">
						<div class="card border-warning mb-3" style="max-width: 540px;">
							<div class="row no-gutters">
								<div class="col-md-4">
									<a
										href="./read_workout_page?wrk_idx=${data.workOutVo.wrk_idx }">
										<img src="/upload/${data.workOutVo.wrk_surl }"
										class="card-img" alt="">
									</a>
								</div>
								<div class="col-md-8">
									<a href="./my_pick_delete?wrk_idx=${data.workOutVo.wrk_idx }">
										<button type="button" class="close" aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</a>
									<div class="card-body ">
										<h5 class="card-title">
											<a
												href="./read_workout_page?wrk_idx=${data.workOutVo.wrk_idx }"
												class="card-link text-warning">${data.workOutVo.wrk_ttl }</a>
										</h5>
										<p class="card-text">${data.memberVo.mbr_nick }</p>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
		<div class="col-2" style="background-color: #FFE499"></div>
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