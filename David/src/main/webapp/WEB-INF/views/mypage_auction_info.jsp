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
							style="width: 215px; background-color: #FEFFAD;">경매내역</a> <a
							href="./mypage_inquiry_list"
							class="btn btn-outline-warning btn-lg text-dark" id="all"
							style="width: 215px; background-color: #FFFFFF;">나의 문의내역</a> <a
							href="./my_pick_video"
							class="btn btn-outline-warning btn-lg text-dark" id="all"
							style="width: 215px; background-color: #FFFFFF;">찜한 동영상</a>
					</div>
				</div>
			</div>
		</div>
		<div class="col my-5">
			<h1 class="mt-3 mb-5 font-weight-bold text-center">경매내역</h1>

			<div class="row mt-2">
				<!-- style="height: 500px -->
				<div class="col pd-2 border-top border-button border-warning">
					<h4 class=" my-4 font-weight-bold text-center">등록한 경매 물품</h4>
					<c:if test="${empty auctionInfoMyWritingList }">
						<h6 class="my-5 text-center">등록한 경매 물품이 없습니다.</h6>
					</c:if>
					<c:if test="${!empty auctionInfoMyWritingList }">
						<table class="table ">
							<thead>
								<tr class="table-warning">
									<th scope="col" width="100px">경매글번호</th>
									<th scope="col" width="250px">제품이미지</th>
									<th scope="col" width="250px">제품명</th>
									<th scope="col" width="150px">경매시작일</th>
									<th scope="col" width="100px"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${auctionInfoMyWritingList  }" var="data">
									<tr>
										<th scope="row">${data.auctionInfoVo.auc_idx}</th>
										<td><a
											href="./read_auction_page?auc_idx=${data.auctionInfoVo.auc_idx}">
												<img src="/upload/${data.auctionImgVo.i_imgname}"
												class="mr-3" alt="" width="200px" height="150px">
										</a></td>
										<td><a
											href="./read_auction_page?auc_idx=${data.auctionInfoVo.auc_idx}"
											style="color: black">${data.auctionInfoVo.auc_pnm }</a></td>
										<td>${data.auctionInfoVo.auc_rdat }</td>
										<td><a href="" class="btn btn-outline-danger">삭제</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						</c:if>
				</div>
			</div>

			<div class="row mt-2">
				<!-- style="height: 300px -->
				<div class="col pd-2 border-top border-button border-warning">
					<h4 class=" my-4 font-weight-bold text-center">경매 댓글</h4>
					<c:if test="${empty auctionMyReplyList }">
						<h6 class="my-5 text-center">등록한 경매 물품이 없습니다.</h6>
					</c:if>
					<c:if test="${!empty auctionMyReplyList }">
						<table class="table">
							<thead>
								<tr class="table-warning">
									<th scope="col">댓글번호</th>
									<th scope="col">댓글내용</th>
									<th scope="col">닉네임</th>
									<th scope="col">댓글 작성일</th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<!--  foreach문 수정완료 -->
								<c:forEach items="${auctionMyReplyList }" var="data">
									<tr>
										<th scope="row">${data.auctionReplyVo.arpl_idx }</th>
										<td><a
											href="./read_auction_page?auc_idx=${data.auctionReplyVo.auc_idx}"
											style="color: black">${data.auctionReplyVo.arpl_con }</a></td>
										<td>${data.memberVo.mbr_nick }</td>
										<td>${data.auctionReplyVo.arpl_rdat }</td>

										<td><a
											href="./my_auction_reply_delete?arpl_idx=${data.auctionReplyVo.arpl_idx }"
											class="btn btn-outline-danger">삭제</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>

			<div class="row">
				<div class="col border-top border-button border-warning">
					<h4 class=" my-4 font-weight-bold text-center">낙찰 물품</h4>
					<c:if test="${empty myFlexMyWritingList }">
						<h6 class="mt-5 text-center">낙찰 물품이 없습니다.</h6>
					</c:if>
					<c:if test="${!empty myFlexMyWritingList}">
						<table class="table">
							<thead>
								<tr class="table-warning">
									<th scope="col">낙찰 번호</th>
									<th scope="col">제품이미지</th>
									<th scope="col">제품명</th>
									<th scope="col">낙찰가</th>
									<th scope="col">낙찰일시</th>
								</tr>
							</thead>
							<tbody>
								<!-- foreach문 삽입 -->
								<c:forEach items="${myBidInfoList }" var="data">
									<tr>
										<th scope="row">${data.auctionInfoVo.auc_idx}</th>
										<td><a
											href="./read_auction_page?auc_idx=${data.auctionInfoVo.auc_idx }">
												<img src="/upload/${data.auctionImgVo }" class="mr-3" alt=""
												width="200px" height="150px">
										</a></td>
										<td><a
											href="./read_auction_page?auc_idx=${data.auctionInfoVo.auc_idx }"
											style="color: black">${data.auctionInfoVo.auc_pnm }</a></td>
										<td>${data.bidInfoVo.bid_cst }</td>
										<td>${data.bidInfoVo.bid_dat }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>

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