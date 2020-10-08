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
		<div class="col-2" style="background-color: #FFE499">
			<h1 class="my-2 font-weight-bold" align="center">MY PAGE</h1>
			<div class="row mt-3">
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
							style="width: 215px; background-color: #FEFFAD;">나의 문의내역</a><a
							href="./my_pick_video"
							class="btn btn-outline-warning btn-lg text-dark" id="all"
							style="width: 215px; background-color: #FFFFFF;">찜한 동영상</a>
					</div>
				</div>
			</div>
		</div>
		<div class="col my-5 ">
			<h1 class="mt-3 mb-5 font-weight-bold text-center">나의 문의내역</h1>
			<c:if test="${empty myInquiryList }">
				<div class="row " style="height: 500px">
					<div class="col mt-5 text-center">
						<h5>문의 내역이 없습니다.</h5>
					
					<div class="row">
						<div class="col" align="right">
							<a href="./write_inquiry_page" class="btn btn-outline-warning">문의하기</a>
						</div>
					</div>
					</div>
				</div>
			</c:if>
			<c:if test="${!empty myInquiryList }">
				<div class="row">
					<div class="col" align="left">
						<a href="./write_inquiry_page" class="btn btn-outline-warning">문의하기</a>
					</div>
				</div>
				<div class="row mt-2" style="height: 500px">
					<div class="col">
						<table class="table">
							<thead>
								<tr class="table-warning">
									<th scope="col" width="100px">문의번호</th>
									<th scope="col" width="300px">제목</th>
									<th scope="col" width="150px">작성일</th>
									<th scope="col" width="100px">처리상황</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${myInquiryList }" var="data">
									<tr>
										<th scope="row">${data.inquiryVo.inq_idx }</th>
										<td><a
											href="./read_inquiry?inq_idx=${data.inquiryVo.inq_idx }"
											style="color: black">${data.inquiryVo.inq_ttl }</a></td>
										<td>${data.inquiryVo.inq_idat }</td>
										<td><c:if test="${data.inquiryVo.inq_vrf=='T'}">답변완료</c:if>
											<c:if test="${data.inquiryVo.inq_vrf=='N'}">처리중</c:if></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</c:if>
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