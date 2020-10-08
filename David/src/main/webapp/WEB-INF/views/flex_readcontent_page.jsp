
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
</head>


<style>
p.title {
	font-size: 45px;
	font-family: 'Do Hyeon';
	padding: 10px 0px 10px 0px;
	margin: 0px;
}

p.smtitle {
	font-size: 25px;
	font-family: 'Do Hyeon';
	margin: 0px;
	padding-top: 5px
}

p.con {
	font-size: 25px;
	font-family: 'Do Hyeon';
	margin: 0px;
	padding-top: 5px
}

a.words {
	font-size: 17px;
	font-family: 'Do Hyeon';
}

a.back {
	font-size: 19px;
	font-family: 'Do Hyeon';
}

button.words {
	font-size: 17px;
	font-family: 'Do Hyeon';
}

div.words {
	font-size: 18px;
	font-family: 'Do Hyeon';
}

div.content {
	font-size: 15px;
	font-family: 'Noto Sans KR';
}

a.content {
	font-size: 14px;
	font-family: 'Noto Sans KR';
}

input.words {
	font-size: 19px;
	font-family: 'Do Hyeon';
}

table.content {
	font-size: 15px;
	font-family: ' Noto Sans KR;
}

a.delete {
	font-size: 13px;
	font-family: 'Do Hyeon';
	height: 25px;
	padding: 3px 3px 0px 3px;
	margin-left: 5px;
}
</style>

<body>
	<jsp:include page="./nav.jsp"></jsp:include>

	<div class="container">
		<div class="row">
			<div class="col"></div>
			<div class="col-10">
				<div class="row text-center" style="padding: 40px 0px 40px 0px;">
					<div class="col ">
						<p class="title">
							<a href="./flex_main_page" class="text-dark">My Flex</a>
						</p>
					</div>
				</div>


				<div class="row mt-2 mb-4">
					<div class="col-2 btn-group" role="group"
						aria-label="Basic example">
						<c:if test="${sessionUserData.mbr_idx == data.memberVo.mbr_idx}">

							<button type="button" class="btn btn-outline-secondary words"  data-toggle="modal"
								data-target="#exampleModal">삭제</button>

							<div class="modal fade" id="exampleModal" tabindex="-1"
								role="dialog" aria-labelledby="exampleModalLabel"
								aria-hidden="true">
								<div class="modal-dialog" role="document">
									<div class="modal-content words">
										<div class="modal-header">
											<div class="text-center">컨텐츠 삭제 확인</div>
										</div>
										<div class="modal-body">
											<div class="text-center">정말 삭제하시겠습니까?</div>
										</div>
										<div class="modal-footer" style="height: 80px">
											<button type="button" class="btn btn-secondary words"
												data-dismiss="modal">최소</button>
											<a role="button" class="btn btn-warning words"
												href="./delete_content_action?flx_idx=${data.my_flexcontentVo.flx_idx }">
												삭제</a>

										</div>
									</div>
								</div>
							</div>

						</c:if>
						<c:if test="${sessionUserData.mbr_idx == data.memberVo.mbr_idx}">
							<a type="button" 
								href="./flex_updatecontent_page?flx_idx=${data.my_flexcontentVo.flx_idx }"
								class="btn btn-outline-warning words">수정</a>
						</c:if>
					</div>
					<div class="col ">
						<a href="./flex_main_page"
							class="btn btn-warning back float-right" style="width: 200px">목록</a>
					</div>
				</div>

				<div class="card mb-2">
					<div class="card-body">
						<div class="row mt-4">
							<div class="col">
								<a type="button"
									class="btn btn-outline-secondary content float-left"
									href="./flex_readcontent_page?flx_idx=${data.mrnum }">◁ 이전글</a>
							</div>
							<div class="col-9 justify-content-center">
								<img src="/upload/${data.my_flexcontentVo.flx_iurl }"
									style="height: 500px;">
							</div>
							<div class="col">
								<a type="button"
									class="btn btn-outline-secondary content float-right"
									href="./flex_readcontent_page?flx_idx=${data.prnum }">다음글 ▷</a>
							</div>
						</div>
						<br>
						<c:if test="${!empty sessionUserData }">
							<div class="row">
								<div class="col-auto">
									<c:if test="${!empty like}">
										<a type="button"
											href="./like_delete_action?flx_idx=${data.my_flexcontentVo.flx_idx }"
											class="btn text-danger"><h4>♥ ${likeData }</h4></a>
									</c:if>
									<c:if test="${empty like}">
										<a type="button"
											href="./like_in_action?flx_idx=${data.my_flexcontentVo.flx_idx }"
											class="btn "><h4>♡ ${likeData }</h4></a>
									</c:if>

								</div>

							</div>
						</c:if>


						<div class="row">

							<div class="col-auto">
								<p class="smtitle">${data.memberVo.mbr_nick }:</p>
							</div>
							<div class="col">
								<p class="con">${data.my_flexcontentVo.flx_con }</p>
							</div>

						</div>
						<br>
						<div class="row"></div>
						<div class="col"></div>
						<div class="col"></div>
						<div class="col content" style="text-align: right;">조회수 :
							${data.my_flexcontentVo.flx_rcnt } 회</div>
					</div>
				</div>

				<!-- 
				<div class="row mb-2">

					<div class="col-3">제목 :</div>
					<div class="col">${data.my_flexcontentVo.flx_ttl }</div>
				</div>

				<div class="row mb-2">
					<div class="col-3">내용 :</div>
					<div class="col">${data.my_flexcontentVo.flx_con }</div>
				</div>
 				-->
				<div class="card card-body">

					<div class="row">
						<div class="col">

							<form action="./write_flex_reply_action" method="post">

								<div class="row mb-2 ">
									<div class="col-2 content">댓글 작성자 :</div>
									<div class="col words">${sessionUserData.mbr_nick }</div>
								</div>

								<div class="row content">
									<!-- id, box -->
									<label for="content" class="col-sm-2 control-label">댓글
										내용 :</label>
									<div class="col-sm-10">
										<textarea id="content" class="form-control" name="frpl_con"
											rows="3"></textarea>
										<input type="hidden" value="${data.my_flexcontentVo.flx_idx }"
											name="flx_idx">
									</div>
								</div>
								<div class="row mt-4">
									<div class="col-2"></div>
									<div class="col-3">
										<c:if test="${!empty sessionUserData}">
											<input type="submit" class="btn btn-warning btn-block words"
												value="댓글 작성">
										</c:if>
									</div>
									<div class="col"></div>
								</div>

							</form>
						</div>
					</div>
					<div class="row mt-4">
						<div class="col">

							<table class="table-sm table table-hover content text-center">

								<thead>
									<tr>
										<th scope="col" style="width: 100px">작성자</th>
										<th scope="col" style="width: 400px">내용</th>
										<th scope="col" style="width: 400px">작성일</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${replydataList }" var="replydata">
										<tr>

											<td><a href="javascript:lets_chat(${replydata.memberVo.mbr_idx })" style="color:black;">${replydata.memberVo.mbr_nick }</a></td>
											<td>${replydata.my_flex_replyVo.frpl_con }</td>
											<td>${replydata.my_flex_replyVo.frpl_fdat }<c:if
													test="${sessionUserData.mbr_idx == replydata.my_flex_replyVo.mbr_idx }">
													<a
														href="./delete_flex_reply_action?frpl_idx=${replydata.my_flex_replyVo.frpl_idx }
											&flx_idx=${replydata.my_flex_replyVo.flx_idx}"
														class="btn btn-warning btn-sm delete">삭제</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>

							</table>

						</div>

					</div>

				</div>

			</div>
			<div class="col"></div>
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