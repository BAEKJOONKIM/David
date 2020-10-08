<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트레이너인증 신청글 모음</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">


<c:url var="getAplListURL" value="./trnr_applicationlist_page"></c:url>

<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">
<style>
p.title {
	font-family: 'Do Hyeon';
	font-size: 45px;
}

table.words {
	font-family: 'Noto Sans KR';
	font-size: 15px;
}

a.button {
	font-family: 'Do Hyeon';
	font-size: 19px;
}

div.searchtype {
	font-family: 'Do Hyeon';
	font-size: 14px;
}

div.search {
	font-family: 'Do Hyeon';
	font-size: 14px;
}

</style>




</head>
<body>
	<jsp:include page="./nav_simple.jsp"></jsp:include>
	<c:choose>
		<c:when test="${empty sessionManagerData}">
			<jsp:include page="./cannot_read_page.jsp"></jsp:include>
		</c:when>

		<c:otherwise>


			<div class="container">
				<div class="row">
					<div class="col"></div>
					<div class="col-10 py-5">
						<p class="text-center my-5 title">트레이너 신청리스트</p>
						<!-- search{s} -->

						<div class="row ">
							<div class="col-2">
								<nav aria-label="Page navigation example">
									<ul class="pagination">
										<li class="page-item"><a class="page-link" href="#"
											aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
										</a></li>
										<c:forEach begin="1" end="${pageNum+1}" varStatus="status">
											<li class="page-item"><a class="page-link"
												href="./trnr_applicationlist_page?r_num=${status.index }">${status.index }</a></li>
										</c:forEach>

										<li class="page-item"><a class="page-link" href="#"
											aria-label="Next"> <span aria-hidden="true">&raquo;</span>
										</a></li>
									</ul>
								</nav>
							</div>

							<div class="col"></div>
							<div class="col">
								<form method="get" action="./trnr_applicationlist_page">
									<div class="form-group row justify-content-end">
										<div class="w100 searchtype" style="padding-right: 10px">
											<select class="form-control form-control-sm"
												name="searchType" id="searchType">
												<option value="mbr_nick">닉네임</option>
												<option value="mbr_vrf">처리상황</option>
											</select>
										</div>

										<div class="w300" style="padding-right: 10px">
											<input type="text" class="form-control form-control-sm"
												name="keyword" id="keyword">
										</div>

										<div class="search">
											<!-- <input class="btn btn-sm btn-warning " type="submit"
												value="검색"> -->

											<c:choose>
												<c:when test="${!empty keyword}">
													<a href="./trnr_applicationlist_page?r_num=1" class="btn btn-warning" style="padding: 3px 10px 3px 10px;"
														role="button">전체내용</a>
												</c:when>
												<c:otherwise>
													<input class="btn btn-outline-warning" type="submit" value="검색" style="padding: 3px 10px 3px 10px;"></input>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</form>
							</div>

						</div>


						<!-- search{e} -->

						<hr>
						<div class="row mb-5">
							<!-- table row -->
							<div class="col">
								<table class="table table-hover words ">
									<thead class="table-warning">
										<tr>
											<th scope="col">#</th>
											<th scope="col" style="width: 200px">회원번호</th>
											<th scope="col" style="width: 200px">신청자</th>
											<th scope="col" style="width: 250px">신청일시</th>
											<th scope="col" style="width: 200px">처리상황</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<c:forEach items="${trnrAplList}" var="trnrApl">
												<tr>
													<th scope="row">${trnrApl.TRN_IDX}</th>
													<td>${trnrApl.MBR_IDX }</td>
													<td><a
														href="./read_trnrAplDetail_page?trn_idx=${trnrApl.TRN_IDX}">${trnrApl.MBR_NICK}</a></td>
													<td>${trnrApl.TRN_VDAT}</td>
													<td><c:choose>
															<c:when test="${trnrApl.MBR_VRF== '승인'}">승인</c:when>
															<c:when test="${trnrApl.MBR_VRF== '거절'}">거절</c:when>
															<c:when test="${trnrApl.APL_COUNT >=2}">${trnrApl.APL_COUNT}번째 신청</c:when>
															<c:otherwise>대기</c:otherwise>
														</c:choose></td>
												</tr>
											</c:forEach>
										</tr>
									</tbody>
								</table>


							</div>
						</div>
						<br>
						<hr>
						<br>
						<div class="row">
							<div class="col"></div>
							<div class="col"></div>
							<div class="col">
								<a href="./manager_page?r_num=1"
									class="btn btn-warning btn-block button" role="button">메인화면으로</a>
							</div>
						</div>

					</div>
					<div class="col"></div>

				</div>
			</div>
		</c:otherwise>
	</c:choose>









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