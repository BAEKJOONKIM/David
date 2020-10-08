<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">


<script type="text/javascript">
	function submit_process() {

		var search_form = document.getElementById("search_form");

		var input_text = document.getElementById("text");

		if (input_text.value == "") {
			alert("검색내용을입력해주세요");
			input_title.focus();
			return;
		}

		search_form.submit();
	}
</script>

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

tr.tb_head {
	font-family: 'Do Hyeon';
}

a.button {
	font-family: 'Do Hyeon';
	font-size: 19px;
}

div.head {
	font-family: 'Do Hyeon';
	font-size: 50px;
	padding-top: 10px;
}
</style>



</head>
<body style="background-color: #FFF6D3">
	<jsp:include page="./nav.jsp"></jsp:include>

	<div class="container">
		<div class="row bg-white">
			<div class="col">
				<div class="row head">
					<div class="col text-center p-4 rounded">
						<p>MY FLEX</p>
					</div>
				</div>

				<form id="search_form" action="./flex_main_page" method="post">
					<div class="row mb-4">
						<div class="col-2"></div>
						<div class="col searchtype">
							<select class="form-control" id="searchType" name="searchType"
								style="font-size: 13px; width: 100px">

								<option value="title" class="dropdown-item my-1"
									style="font-size: 13px" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">제목</option>

								<option value="content" class="dropdown-item mb-1"
									style="font-size: 13px" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">내용</option>

								<option value="nick" class="dropdown-item"
									style="font-size: 13px" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">작성자</option>
							</select>
						</div>

						<div class="col-5 searchbar p-0">
							<input id="text" type="text" class="form-control text-center"
								style="font-size: 13px; height: 35px" placeholder="검색내용을 입력해주세요"
								aria-label="search" name="searchData">
						</div>

						<div class="col search">
							<c:choose>
								<c:when test="${!empty searchData}">
									<a href="./flex_main_page" class="btn btn-warning"
										style="width: 90px; font-size: 14px" role="button">전체내용</a>
								</c:when>
								<c:otherwise>
									<button class="btn btn-outline-warning "
										style="width: 80px; font-size: 14px" type="submit">검색</button>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="col-2"></div>
					</div>
				</form>

				<hr>
				<br>



				<div class="row row-cols-1 row-cols-md-4">
					<c:forEach items="${page }" var="data">
						<div class="col mb-4">
							<div class="card">
								<img src="/upload/${data.my_flexcontentVo.flx_iurl }"
									class="card-img-top" style="height: 150px;">
								<div class="card-body">
									<h5 class="card-title text-truncate">
										<a
											href="./flex_readcontent_page?flx_idx=${data.my_flexcontentVo.flx_idx }">${data.my_flexcontentVo.flx_ttl }</a>
										<a class="text-danger">[${data.replyvo }]</a>

										<c:if test="${!empty data.newContent }">
											<span class="badge badge-danger">New</span>
										</c:if>
									</h5>
									<p class="card-nick">${data.memberVo.mbr_nick }</p>
									<p class="card-like">좋아요 : ${data.likevo }개</p>
									<p class="card-rcnt">조회수 : ${data.my_flexcontentVo.flx_rcnt }회</p>
									<p class="card-fdat">${data.my_flexcontentVo.flx_fdat }</p>
								</div>
							</div>
						</div>
					</c:forEach>
					<c:forEach items="${searchList }" var="data">
						<div class="col mb-4">
							<div class="card">
								<img src="/upload/${data.my_flexcontentVo.flx_iurl }"
									class="card-img-top" style="height: 150px;">
								<div class="card-body">
									<h5 class="card-title text-truncate">
										<a
											href="./flex_readcontent_page?flx_idx=${data.my_flexcontentVo.flx_idx }">${data.my_flexcontentVo.flx_ttl }</a>
									</h5>
									<p class="card-nick">${data.memberVo.mbr_nick }</p>
									<p class="card-rcnt">${data.my_flexcontentVo.flx_rcnt }회</p>
									<p class="card-fdat">${data.my_flexcontentVo.flx_fdat }</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>


				<div class="row mt-4">
					<div class="col"></div>
					<div class="col-auto">

						<nav aria-label="Page navigation example">
							<ul class="pagination">

								<li class="page-item"><a class="page-link"
									href="./flex_main_page?flx_page=1">1</a></li>

								<li class="page-item"><a class="page-link"
									href="./flex_main_page?flx_page=2">2</a></li>
								<li class="page-item"><a class="page-link"
									href="./flex_main_page?flx_page=3">3</a></li>
								<li class="page-item"><a class="page-link"
									href="./flex_main_page?flx_page=4">4</a></li>
								<li class="page-item"><a class="page-link"
									href="./flex_main_page?flx_page=5">5</a></li>

							</ul>
						</nav>
					</div>
					<div class="col"></div>
				</div>

				<div class="row mt-5">
					<div class="col"></div>
					<div class="col"></div>
					<div class="col"></div>
					<div class="col-2">
						<c:if test="${!empty sessionUserData }">
							<a class="btn btn-warning btn-block button"
								href="./flex_writecontent_page" role="button">글쓰기</a>
						</c:if>
					</div>

				</div>
				<br><br><br>
			</div>
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