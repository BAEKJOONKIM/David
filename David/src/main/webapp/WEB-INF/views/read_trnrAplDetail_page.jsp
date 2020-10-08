<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>트레이너 신청글 자세히 보기 페이지</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
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
						<h1 class="my-5 text-center">신청내용</h1>


						<div class="row mt-5">
							<div class="col">
								<div class="row">
									<div class="col">성함:</div>
									<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${trnrAplInfo.MBR_NAME}</div>
								</div>
							</div>

							<div class="col">
								<div class="row">
									<div class="col ml-3">닉네임:</div>
									<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${trnrAplInfo.MBR_NICK}</div>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col">
								<div class="row">
									<div class="col">생년월일:</div>
									<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${trnrAplInfo.MBR_BTH }</div>
								</div>
							</div>

							<div class="col">
								<div class="row">
									<div class="col ml-3">성별:</div>
									<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${trnrAplInfo.MBR_SEX }</div>
								</div>
							</div>
						</div>
						<br>
						<div class="row">
							<div class="col">
								<div class="row">
									<div class="col">연락처:</div>
									<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${trnrAplInfo.MBR_NMBR }</div>
								</div>
							</div>

							<div class="col">
								<div class="row">
									<div class="col ml-3">신청일시:</div>
									<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${trnrAplInfo.TRN_VDAT}</div>
								</div>
							</div>
						</div>
						<br>

						<div class="row">
							<div class="col-2">자격증:</div>
							<div class="col"></div>
							<div class="col"></div>
							<div class="col"></div>
							<div class="col">
								<a
									href="./confirm_trnrApl_action?mbr_idx=${trnrAplInfo.MBR_IDX}"
									class="btn btn-warning btn-block" role="button">승인</a>
							</div>
							<div class="col">
								<a role="button" class="btn btn-warning btn-block"
										data-toggle="modal" data-target="#rejectModal">거절</a>
							</div>

							<!-- Modal -->
							<div class="modal fade" id="rejectModal" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content">
										<div class="modal-header">
											<h6 class="modal-title" id="exampleModalLabel">승급신청 거절을 하고계십니다</h6>
											<button type="button" class="close" data-dismiss="modal"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
										<br>
										<div class="modal-body font-weight-bold">해당 회원의 승급신청요청을 거절하시겠습니까?</div>
										<br>
										<div class="modal-footer justify-content-center">
											<a role="button" class="btn btn-outline-secondary"
												data-dismiss="modal">아니요</a> <a role="button"
												class="btn btn-warning"
												href="./reject_trnrApl_action?mbr_idx=${trnrAplInfo.MBR_IDX}"">예</a>
										</div>
									</div>
								</div>
							</div>


						</div>
						<div class="row mt-4">
							<div
								class="col shadow-none p-2 mb-3 bg-light rounded text-center">
								<img src="/upload/${trnrAplInfo.TRN_TURL}" height="500px">
							</div>
						</div>
						<br>


						<div class="row">
							<div class="col">
								<a href="./deleteApl_action?trn_idx=${trnrAplInfo.TRN_IDX }"
									class="btn btn-secondary btn-block" role="button"> 삭제</a>
							</div>
							<div class="col"></div>
							<div class="col"></div>
							<div class="col">
								<a href="./trnr_applicationlist_page?r_num=1"
									class="btn btn-secondary btn-block" role="button">목록으로</a>
							</div>
						</div>
						<br>

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