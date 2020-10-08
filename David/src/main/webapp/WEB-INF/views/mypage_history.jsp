<!-- 페이지이름수정 -->
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
							style="width: 215px; background-color: #FEFFAD;">나의 활동</a> <a
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
							style="width: 215px; background-color: #FFFFFF;">찜한 동영상</a>
					</div>
				</div>
			</div>
		</div>

		<div class="col my-5">
			<h4 class="mt-3 mb-5 font-weight-bold text-center">나의 활동</h4>
			<div class="row">
				<div class="col border-top border-button border-warning">
					<h4 class="text-left my-4 font-weight-bold">출석현황</h4>
					<div class="row">
						<div class="col">
							<table class="table table-borderless">
								<thead>
									<tr class="table-warning">
										<th scope="col">출석일시</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${attendanceList }" var="data">
										<tr>
											<td>${data.attendanceVo.att_date}</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col border-top border-warning">
					<h3 class="text-center my-4 font-weight-bold">자유게시판</h3>
					<div class="row">

						<div class="col pd-2 border-button border-right border-warning">
							<h5 class="text-center mt-4 font-weight-bold">내가 쓴 글</h5>
							<c:if test="${empty boardMyWritingList }">
								<h6 class="my-5 text-center">등록한 게시글이 없습니다.</h6>
							</c:if>
							<c:if test="${!empty boardMyWritingList }">
								<div align="right">
									<a href="./my_board_detail_list"
										style="font-size: 12px; color: black">더보기</a>
								</div>
								<table class="table table-borderless" style="font-size: 12px">
									<thead>
										<tr class="table-warning">
											<th scope="col">글번호</th>
											<th scope="col">제목</th>
											<th scope="col">작성자</th>
											<th scope="col" width="100px">작성일</th>
											<th scope="col">조회수</th>
										</tr>
									</thead>
									<tbody>
										<!-- foreach문 수정완료 -->
										<c:forEach items="${boardMyWritingList }" var="data">
											<tr>
												<th scope="row">${data.boardVo.brd_idx }</th>
												<td><a
													href="./board_read_page?brd_idx=${data.boardVo.brd_idx }"
													style="color: black">${data.boardVo.brd_ttl }</a></td>
												<td>${data.memberVo.mbr_nick }</td>
												<td>${data.boardVo.brd_bdat }</td>
												<td>${data.boardVo.brd_rcnt }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
						</div>

						<div class="col pd-2 border-button border-warning">
							<h5 class="text-center mt-4 font-weight-bold">댓글</h5>
							<c:if test="${empty boardMyReplyList }">
								<h6 class="my-5 text-center">등록한 댓글이 없습니다.</h6>
							</c:if>
							<c:if test="${!empty boardMyReplyList }">
								<div align="right">
									<a href="./my_board_reply_detail"
										style="font-size: 12px; color: black">더보기</a>
								</div>
								<table class="table table-borderless" style="font-size: 12px">
									<thead>
										<tr class="table-warning">
											<th scope="col">댓글번호</th>
											<th scope="col">댓글내용</th>
											<th scope="col">작성자</th>
											<th scope="col">댓글 작성일</th>
										</tr>
									</thead>
									<tbody>
										<!-- foreach문 수정완료 -->
										<c:forEach items="${boardMyReplyList }" var="data">
											<tr>
												<th scope="row">${data.boardReplyVo.brpl_idx }</th>
												<td><a
													href="./board_read_page?brd_idx=${data.boardReplyVo.brd_idx }"
													style="color: black">${data.boardReplyVo.brpl_con }</a></td>
												<td>${data.memberVo.mbr_nick }</td>
												<td>${data.boardReplyVo.brpl_rdat }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
						</div>

					</div>
				</div>
			</div>

			<div class="row">
				<div class="col border-top  border-warning">
					<h3 class="text-center my-4 font-weight-bold">My Flex</h3>
					<div class="row">

						<div class="col pd-2 border-button border-right border-warning">
							<h5 class="text-center mt-4 font-weight-bold">내가 쓴 글</h5>
							<c:if test="${empty myFlexMyWritingList }">
								<h6 class="my-5 text-center">등록한 게시글이 없습니다.</h6>
							</c:if>
							<c:if test="${!empty myFlexMyWritingList}">
								<table class="table table-borderless" style="font-size: 12px">
									<div align="right">
										<a href="./my_flex_detail_list"
											style="font-size: 12px; color: black">더보기</a>
									</div>
									<thead>
										<tr class="table-warning">
											<th scope="col">글번호</th>
											<th scope="col">제목</th>
											<th scope="col">작성자</th>
											<th scope="col">작성일</th>
											<th scope="col">조회수</th>
										</tr>
									</thead>
									<tbody>
										<!-- foreach문 수정완료 -->
										<c:forEach items="${myFlexMyWritingList }" var="data">
											<tr>
												<th scope="row">${data.myFlexContentVo.flx_idx }</th>
												<td><a
													href="./flex_readcontent_page?flx_idx=${data.myFlexContentVo.flx_idx }"
													style="color: black">${data.myFlexContentVo.flx_ttl }</a></td>
												<td>${data.memberVo.mbr_nick }</td>
												<td>${data.myFlexContentVo.flx_fdat }</td>
												<td>${data.myFlexContentVo.flx_rcnt }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
						</div>

						<div class="col pd-2 border-button border-warning">
							<h5 class="text-center mt-4 font-weight-bold">댓글</h5>
							<c:if test="${empty myFlexMyReplyList }">
								<h6 class="my-5 text-center">등록한 댓글이 없습니다.</h6>
							</c:if>
							<c:if test="${!empty myFlexMyReplyList }">
								<div align="right">
									<a href="./my_flex_reply_detail"
										style="font-size: 12px; color: black">더보기</a>
								</div>
								<table class="table table-borderless" style="font-size: 12px">
									<thead>
										<tr class="table-warning">
											<th scope="col">댓글번호</th>
											<th scope="col">댓글내용</th>
											<th scope="col">작성자</th>
											<th scope="col">댓글 작성일</th>
										</tr>
									</thead>
									<tbody>
										<!-- foreach문 수정완료 -->
										<c:forEach items="${myFlexMyReplyList }" var="data">
											<tr>
												<th scope="row">${data.myFlexReplyVo.frpl_idx }</th>
												<td><a
													href="./flex_readcontent_page?flx_idx=${data.myFlexReplyVo.flx_idx }"
													style="color: black">${data.myFlexReplyVo.frpl_con }</a></td>
												<td>${data.memberVo.mbr_nick }</td>
												<td>${data.myFlexReplyVo.frpl_fdat }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
						</div>

					</div>
				</div>
			</div>

			<div class="row">
				<div class="col border-top  border-warning">
					<h3 class="text-center my-4 font-weight-bold">운동합시다</h3>
					<div class="row">
						<c:if test="${myWokrOutMyWritingList.memberVO.mbr_trnr == 'T' }">
						<div class="col pd-2 border-button border-right border-warning">
							<h5 class="text-center mt-4 font-weight-bold">내가 쓴 글</h5>
							<c:if test="${empty workOutMyWritingList }">
								<h6 class="my-5 text-center">등록한 게시글이 없습니다.</h6>
							</c:if>
							<c:if test="${!empty workOutMyWritingList}">
								<table class="table table-borderless" style="font-size: 12px">
									<div align="right">
										<a href="./my_work_detail_list"
											style="font-size: 12px; color: black">더보기</a>
									</div>
									<thead>
										<tr class="table-warning">
											<th scope="col">글번호</th>
											<th scope="col">제목</th>
											<th scope="col">작성자</th>
											<th scope="col">작성일</th>
											<th scope="col">조회수</th>
										</tr>
									</thead>
									<tbody>
										<!-- foreach문 수정완료 -->
										<c:forEach items="${workOutMyWritingList }" var="data">
											<tr>
												<th scope="row">${data.workOutVo.wrk_idx }</th>
												<td><a
													href="./read_workout_page?wrk_idx=${data.workOutVo.wrk_idx }"
													style="color: black">${data.workOutVo.wrk_ttl }</a></td>
												<td>${data.memberVo.mbr_nick }</td>
												<td>${data.workOutVo.wrk_wdat }</td>
												<td>${data.workOutVo.wrk_rcnt }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
						</div>
						</c:if>

						<div class="col pd-2 border-button border-warning">
							<h5 class="text-center mt-4 font-weight-bold">댓글</h5>
							<c:if test="${empty workOutMyReplyList }">
								<h6 class="my-5 text-center">등록한 댓글이 없습니다.</h6>
							</c:if>
							<c:if test="${!empty workOutMyReplyList }">
								<div align="right">
									<a href="./my_work_reply_detail"
										style="font-size: 12px; color: black">더보기</a>
								</div>
								<table class="table table-borderless" style="font-size: 12px">
									<thead>
										<tr class="table-warning">
											<th scope="col">댓글번호</th>
											<th scope="col">댓글내용</th>
											<th scope="col">작성자</th>
											<th scope="col">댓글 작성일</th>
										</tr>
									</thead>
									<tbody>
										<!-- foreach문 수정완료 -->
										<c:forEach items="${workOutMyReplyList }" var="data">
											<tr>
												<th scope="row">${data.workOutReplyVo.wrpl_idx }</th>
												<td><a
													href="./read_workout_page?wrk_idx=${data.workOutReplyVo.wrk_idx }"
													style="color: black">${data.workOutReplyVo.wrpl_con }</a></td>
												<td>${data.memberVo.mbr_nick }</td>
												<td>${data.workOutReplyVo.wrpl_wdat }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</c:if>
						</div>
					</div>
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