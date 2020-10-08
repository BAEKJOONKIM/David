<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고글 모음</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">
<style>
p.title {
	font-family: 'Do Hyeon';
	font-size: 45px;
}

div.words {
	font-family: 'Noto Sans KR';
	font-size: 15px;
}

div.search {
	font-family: 'Do Hyeon';
	font-size: 14px;
}

a.button {
	font-family: 'Do Hyeon';
	font-size: 19px;
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
					<div class="col-10">
						<p class="text-center my-5 title">신고글 리스트</p>

						<form method="get" action="./reportlist_page">
							<div class="form-group row justify-content-end">
								<div class="w100 searchtype" style="padding-right: 10px">
									<select class="form-control form-control-sm" name="searchType"
										id="searchType">
										<option value="mbr_nick">닉네임</option>
										<option value="rpt_con">카테고리</option>
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
											<a href="./reportlist_page" class="btn btn-warning"
												style="padding: 3px 10px 3px 10px;" role="button">전체내용</a>
										</c:when>
										<c:otherwise>
											<input class="btn btn-outline-warning" type="submit"
												value="검색" style="padding: 3px 10px 3px 10px;"></input>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</form>


						<hr>
						<div class="row">
							<!-- table row -->
							<div class="col words">
								<table class="table table-hover">
									<thead>
										<tr>
											<th scope="col">#</th>
											<th scope="col" style="width: 200px">카테고리</th>
											<th scope="col" style="width: 220px">작성자</th>
											<th scope="col" style="width: 330px">작성일</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<c:forEach items="${reportlist}" var="reportlist1">
												<tr>
													<th scope="row">${reportlist1.RPT_IDX}</th>
													<td>${reportlist1.RPT_CON}</td>
													<td><a
														href="./read_report_page?rpt_idx=${reportlist1.RPT_IDX}">${reportlist1.MBR_NICK }</a>
														<span class="badge badge-warning">New</span></td>
													<td>${reportlist1.RPT_RDAT}</td>
												</tr>
											</c:forEach>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<br> <br> <br>
						<hr>
						<div class="row mt-3">
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