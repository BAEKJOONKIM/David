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
<title>운동합시다</title>
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">
<style>
div.searchtype {
	font-family: 'Noto Sans KR';
}

div.search {
	font-family: 'Noto Sans KR';
}

div.searchbar {
	font-family: 'Noto Sans KR';
}

div.btn-group-vertical {
	font-family: 'Jua';
	font-size: 20px;
}

div.input-group {
	font-family: 'Jua';
	font-size: 20px;
}

div.container {
	font-family: 'Jua';
	font-size: 20px;
}

p.card-text {
	font-family: 'Jua';
	font-size: 18px;
}

a.title {
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

	<br>
	<div class="row">

		<div class="col-3">
			<div class="btn-group-vertical float-right">
				<a href="workout_page?wrk_cat=0" class="btn btn-warning btn-lg"
					id="all">전체동영상</a> <a href="workout_page?wrk_cat=1&wrk_cp=1"
					class="btn btn-warning btn-lg" id="back">등</a> <a
					href="workout_page?wrk_cat=2&wrk_cp=1"
					class="btn btn-warning btn-lg" id="shoulder">어깨</a> <a
					href="workout_page?wrk_cat=3&wrk_cp=1"
					class="btn btn-warning btn-lg" id="chest">가슴</a> <a
					href="workout_page?wrk_cat=4&wrk_cp=1"
					class="btn btn-warning btn-lg" id="arm">팔</a> <a
					href="workout_page?wrk_cat=5&wrk_cp=1"
					class="btn btn-warning btn-lg" id="abdomen">복부</a> <a
					href="workout_page?wrk_cat=6&wrk_cp=1"
					class="btn btn-warning btn-lg" id="lowerbody">하체</a> <a
					href="workout_page?wrk_cat=7&wrk_cp=1"
					class="btn btn-warning btn-lg" id="sportsequipment">운동기구 가이드</a>
			</div>
		</div>
		<div class="col-7">
			<form action="./workout_page" method="get">
				<div class="row mb-4">

					<div class="col-2 searchtype">
						<select class="form-control " id="searchType" name="searchType"
							style="font-size: 13px;">

							<option value="t" class="dropdown-item my-1"
								style="font-size: 13px;" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">제목</option>

							<option value="c" class="dropdown-item mb-1"
								style="font-size: 13px;" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">내용</option>

							<option value="w" class="dropdown-item" style="font-size: 13px;"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">작성자</option>

							<option value="tc" class="dropdown-item" style="font-size: 13px;"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">제목+내용</option>
						</select>
					</div>
					<div class="col-8 searchbar">
						<input type="hidden" id="wrk_cp" name="wrk_cp" value="1" /> <input
							type="text" class="form-control text-center "
							style="font-size: 13px; height: 35px" placeholder="검색어를 입력하세요"
							aria-label="Recipient's username"
							aria-describedby="button-addon2" name="result">
					</div>

					<div class="col search">
						<button class="btn btn-warning "
							style="width: 80px; font-size: 14px" type="submit">검색</button>
					</div>
				</div>
			</form>
			<div>
				<hr class="my-4">
			</div>
			<div class="tab-content" id="v-pills-tabContent">
				<div class="tab-pane fade show active" id="v-pills-all"
					role="tabpanel" aria-labelledby="v-pills-all-tab">
					<div class="row">

						<c:forEach items="${dataList }" var="data">
							<div class="col-4">
								<div class="card " style="width: 21rem;">
									<a href="./read_workout_page?wrk_idx=${data.WRK_IDX }"><img
										src="/upload/${data.WRK_SURL }" class="card-img-top" alt="..."
										style="width: 20.5rem; height: 11.5rem;"></a>
									<div class="card-body ">
										<a class="title"
											href="./read_workout_page?wrk_idx=${data.WRK_IDX }">${data.WRK_TTL }</a>
										<p class="card-text">${data.MBR_NICK }<br> 조회수
											${data.WRK_RCNT }회 | 좋아요 ${data.WRK_LCNT }개
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
						<c:forEach items="${searchList }" var="data">
							<div class="col-4">
								<div class="card " style="width: 21rem;">
									<a
										href="./read_workout_page?wrk_idx=${data.workOutVo.wrk_idx }"><img
										src="/upload/${data.workOutVo.wrk_surl }" class="card-img-top"
										alt="..." style="width: 20.5rem; height: 11.5rem;"></a>
									<div class="card-body ">
										<a class="title"
											href="./read_workout_page?wrk_idx=${data.workOutVo.wrk_idx }">${data.workOutVo.wrk_ttl }</a>
										<p class="card-text">${data.memberVo.mbr_nick }<br>
											조회수 ${data.workOutVo.wrk_rcnt }회 | 좋아요
											${data.workOutVo.wrk_lcnt }개
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
						<c:forEach items="${pageList }" var="data">
							<div class="col-4">
								<div class="card " style="width: 21rem;">
									<a
										href="./read_workout_page?wrk_idx=${data.workOutVo.wrk_idx }"><img
										src="/upload/${data.workOutVo.wrk_surl }" class="card-img-top"
										alt="..." style="width: 20.5rem; height: 11.5rem;"></a>
									<div class="card-body ">
										<a class="title"
											href="./read_workout_page?wrk_idx=${data.workOutVo.wrk_idx }">${data.workOutVo.wrk_ttl }</a>
										<p class="card-text">${data.memberVo.mbr_nick }<br>
											조회수 ${data.workOutVo.wrk_rcnt }회 | 좋아요
											${data.workOutVo.wrk_lcnt }개

										</p>
									</div>
								</div>
							</div>
						</c:forEach>
						<c:forEach items="${categoryList }" var="data">
							<div class="col-4">
								<div class="card" style="width: 21rem;">
									<a
										href="./read_workout_page?wrk_idx=${data.workOutVo.wrk_idx }"><img
										src="/upload/${data.workOutVo.wrk_surl }" class="card-img-top"
										alt="..." style="width: 20.5rem; height: 11.5rem;"></a>
									<div class="card-body ">
										<a class="title"
											href="./read_workout_page?wrk_idx=${data.workOutVo.wrk_idx }">${data.workOutVo.wrk_ttl }</a>
										<p class="card-text">${data.memberVo.mbr_nick }<br>
											조회수 ${data.workOutVo.wrk_rcnt }회 | 좋아요
											${data.workOutVo.wrk_lcnt }개

										</p>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>

			</div>
			<div>
				<hr class="my-4">
			</div>
			<div class="container" style="text-align: right;">
				<c:if test="${usertrnrInfo.mbr_trnr == 'T' }">
					<a class="btn btn-warning" href="./write_workout_page"
						role="button">글쓰기</a>
				</c:if>
			</div>
			<nav aria-label="Page navigation example">
				<ul class="pagination justify-content-center">

					<li class="page-item"><a class="page-link"
						href="workout_page?wrk_cp=1&searchType=${searchType}&result=${result}&wrk_cat=${wrk_cat}">1</a></li>

					<c:if test="${workOutCount >= 7 }">
						<li class="page-item"><a class="page-link"
							href="workout_page?wrk_cp=2&searchType=${searchType}&result=${result}&wrk_cat=${wrk_cat}">2</a></li>
					</c:if>
					<c:if test="${workOutCount >= 13 }">
						<li class="page-item"><a class="page-link"
							href="workout_page?wrk_cp=3&searchType=${searchType}&result=${result}&wrk_cat=${wrk_cat}">3</a></li>
					</c:if>
					<c:if test="${workOutCount >= 19 }">
						<li class="page-item"><a class="page-link"
							href="workout_page?wrk_cp=4&searchType=${searchType}&result=${result}&wrk_cat=${wrk_cat}">4</a></li>
					</c:if>
					<c:if test="${workOutCount >= 25 }">
						<li class="page-item"><a class="page-link"
							href="workout_page?wrk_cp=5&searchType=${searchType}&result=${result}&wrk_cat=${wrk_cat}">5</a></li>
					</c:if>


				</ul>

			</nav>
		</div>

		<div class="col"></div>
	</div>

	<div>
		<hr class="my-4">
			<div class="col" style="text-align:center;">
				<a href="./flex_main_page" class="mr-2 text-dark font-weight-bold">FLEX</a>
				<a href="./board_main_page" class="mr-2 text-dark font-weight-bold">자유게시판</a>
				<a href="./auction_list_page"
					class="mr-2 text-dark font-weight-bold">경매</a> <a
					href="./workout_page" class="mr-2 text-dark font-weight-bold">워크아웃</a>
				<a href=".customer_center" class="mr-2 text-dark font-weight-bold">고객센터</a>
				<p>Designed and built with all the love by our lovely DAVID team
					members.</p>
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