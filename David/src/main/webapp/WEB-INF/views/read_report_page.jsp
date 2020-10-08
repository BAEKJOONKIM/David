<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고글 자세히 보기 페이지</title>
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
						<h1 class="text-center my-5">신고글내용</h1>
						<div class="row">
							<div class="col">작성자:</div>
							<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${reportcontent.MBR_IDX}</div>
						</div>
						<br>
						<div class="row">
							<div class="col">카테고리:</div>
							<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${reportcontent.RPT_CON}</div>
						</div>
						<br>
						<div class="row">
							<div class="col">신고위치:</div>
							<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${reportcontent.RPT_BCDN}</div>
						</div>
						<br>
						<div class="row">
							<div class="col">신고당한 회원코드:</div>
							<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">
								${reportcontent.memberReportTarget.mbr_idx}</div>
						</div>
						<br>
						<div class="row">
							<div class="col">신고당한 회원닉네임:</div>
							<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${reportcontent.memberReportTarget.mbr_nick}
								<a class="btn btn-warning btn-sm float-right" href="#"
									role="button">회원정보 보러가기</a>
								 <a class="btn btn-warning btn-sm float-right mr-1"
									href="./send_warning_email_action?mbr_idx=${reportcontent.memberReportTarget.mbr_idx}"
									role="button">경고이메일보내기</a>
							</div>


						</div>
						<br>

						<div class="row">
							<div class="col">해당글 신고수:</div>
							<div class="col-8 shadow-none p-2 mb-3 bg-light rounded">${reportcontent.reportCount}
								<a class="btn btn-warning btn-sm float-right"
									href="./admin_delete?rpt_idx=${reportcontent.RPT_IDX }"
									role="button">게시글 삭제</a>
								<c:if test="${reportcontent.reportCount >=3}">
									<a class="btn btn-warning btn-sm float-right"
										href="./put_into_blackList_action?mbr_idx=${reportcontent.memberReportTarget.mbr_idx}"
										role="button">블랙리스트 추가</a>
								</c:if>
							</div>
						</div>
						<br>

						<div class="row">
							<div class="col"></div>
							<div class="col"></div>
							<div class="col"></div>
							<div class="col mt-5">
								<a href="./reportlist_page" class="btn btn-warning btn-block"
									role="button">목록</a>
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