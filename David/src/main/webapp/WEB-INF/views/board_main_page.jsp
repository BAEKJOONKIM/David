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

<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">

<meta charset="UTF-8">
<title>Insert title here</title>


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
						<p>자유 게시판</p>
					</div>
				</div>
				<form action="./board_main_page" method="get">
					<div class="row mb-4">
						<div class="col-2"></div>
						<div class="col  searchtype ">
							<select class="form-control " id="searchType" name="searchType"
								style="font-size: 13px; width: 100px">

								<option value="title" class="dropdown-item my-1"
									style="font-size: 13px;" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">제목</option>

								<option value="content" class="dropdown-item mb-1"
									style="font-size: 13px;" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">내용</option>

								<option value="nick" class="dropdown-item"
									style="font-size: 13px;" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false">작성자</option>
							</select>
						</div>

						<div class="col-5  searchbar p-0">
							<input name="search" class="form-control text-center "
								style="font-size: 13px; height: 35px" type="search"
								placeholder="검색내용을 입력해주세요" aria-label="Search">
						</div>
						<div class="col search">
							<c:choose>
								<c:when test="${!empty search}">
									<a href="./board_main_page" class="btn btn-warning"
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

				<div class="row mx-auto">
					<table class="table table-hover">
						<thead>
							<tr class="tb_head">
								<th scope="col" width="70" style="text-align: center">번호</th>
								<th scope="col" width="250" style="text-align: center">제목</th>
								<th scope="col" width="250" style="text-align: center">글쓴이</th>
								<th scope="col" width="350" style="text-align: center">작성일</th>
								<th scope="col" width="100" style="text-align: center">조회수</th>
								<th scope="col" width="100" style="text-align: center">좋아요수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${dataList}" var="data">
								<tr>
									<th scope="row" style="text-align: center">${data.boardVo.brd_idx }</th>
									<td style="text-align: center"><a
										href="./board_read_page?brd_idx=${data.boardVo.brd_idx}">${data.boardVo.brd_ttl }</a></td>
									<td style="text-align: center">${data.memberVo.mbr_nick}</td>
									<td style="text-align: center">${data.boardVo.brd_bdat}</td>
									<td style="text-align: center">${data.boardVo.brd_rcnt }</td>
									<td style="text-align: center">${data.voLike}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<br>
				</div>
				<br> <br> <br>
				<div class="row mt-5">
					<div class="col"></div>
					<div class="col"></div>
					<div class="col"></div>
					<div class="col-2">
						<c:if test="${!empty sessionUserData }">
							<a class="btn btn-warning btn-block button"
								href="./board_write_page" role="button">글쓰기</a>
						</c:if>
					</div>

				</div>
				<br>
				<br>
				<br>
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