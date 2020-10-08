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
p.title {
	font-size: 45px;
	font-family: 'Do Hyeon';
	padding: 10px 0px 10px 0px;
	margin: 0px;
}

div.words {
	font-size: 17px;
	font-family: 'Do Hyeon';
}

a.words {
	font-size: 17px;
	font-family: 'Do Hyeon';
}

input.words {
	font-size: 18px;
	font-family: 'Do Hyeon';
}
</style>



</head>
<body>

	<jsp:include page="./nav.jsp"></jsp:include>
	<c:choose>
		<c:when test="${empty sessionUserData}">
			<jsp:include page="./cannot_read_page.jsp"></jsp:include>
		</c:when>



		<c:otherwise>
			<div class="container">
				<div class="row">
					<div class="col"></div>
					<div class="col-8">

						<div class="row text-center" style="padding: 40px 0px 40px 0px;">
							<div class="col ">
								<p class="title">
									<a href="./flex_main_page" class="text-dark">문의글</a>
								</p>
							</div>
						</div>
						<div class="row  float-right mb-2">
							<div class="col">
								<c:choose>
									<c:when test="${!empty sessionManagerData }">
										<a href="./inquiry_list_page" style="width: 80px"
											class="btn btn-warning words">목록</a>
									</c:when>
									<c:otherwise>
										<a href="./mypage_inquiry_list" style="width: 80px"
											class="btn btn-warning words">목록</a>

									</c:otherwise>
								</c:choose>

							</div>
						</div>

						<hr class="my-2">
						<br>
						<div class="row words">
							<div class="col-2">제목:</div>
							<div class="col">${inquiry.inquiryVo.inq_ttl }</div>
						</div>
						<div class="row words mt-2">
							<div class="col-2">작성자:</div>
							<div class="col">${inquiry.memberVo.mbr_nick }</div>
						</div>
						<div class="row words mt-2">
							<div class="col-2">문의내용:</div>
							<div class="col">

								<textarea id="inq_con" class="form-control-plaintext"
									style="padding: 0px;" name="inq_con" rows="3">${inquiry.inquiryVo.inq_con }</textarea>
								<input type="hidden" value="${inquiry.inquiryVo.inq_idx }"
									name="inq_idx" id="inq_idx">


							</div>
						</div>
						<div class="row">
							<br>
							<!-- 선추가 -->
						</div>


						<!-- 댓글보기 -->
						<c:forEach items="${inqReply }" var="data">
							<div class="row words">
								<div class="col-2 ">문의 답변 내용</div>
								<div class="col">${data.inqReplyVo.irpl_con }</div>
								<c:if test="${!empty sessionManagerData}">
									<div class="col-2">
										<a
											href="./inquiry_reply_delete_action?irpl_idx=${data.inqReplyVo.irpl_idx}&inq_idx=${data.inqReplyVo.inq_idx}"
											class="btn btn-outline-danger">삭제</a>
									</div>
								</c:if>

							</div>
						</c:forEach>

						<!-- 관리자 댓글 -->
						<c:if test="${!empty sessionManagerData}">
							<hr class="my-4">
							<form action="./inquiry_reply_action">
								<div class="row mt-3">
									<div class="col-2 words">문의 답변:</div>
									<div class="col-8">
										<textarea class="form-control" name="irpl_con" rows="3"
											col="70"></textarea>
										<input type="hidden" name="inq_idx"
											value="${inquiry.inquiryVo.inq_idx }">
									</div>
									<div class="col">
										<input type="submit" class="btn btn-warning words" value="등록"
											style="width: 80px">
									</div>
								</div>
							</form>
						</c:if>


						<div class="row mt-3">

							<c:if
								test="${!empty sessionUserData && inquiry.memberVo.mbr_idx==sessionUserData.mbr_idx} ">
								<div class="col justify-content-center">
									<a
										href="./delete_inquiry_aciton?inq_idx=${inquiry.inquiryVo.inq_idx }"
										class="btn btn-outline-warning text-center">삭제</a>
								</div>
								<div class="col justify-content-center">
									<a
										href="./update_inquiry?inq_idx=${inquiry.inquiryVo.inq_idx }"
										class="btn btn-outline-warning text-center ">수정</a>
								</div>
							</c:if>
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