<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/52e12d5aa9.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<script>
		var loadFile = function(event) {
			var reader = new FileReader();
			reader.onload = function() {
				var output = document.getElementById('output');
				output.src = reader.result;
			};
			reader.readAsDataURL(event.target.files[0]);
		};
	</script>

	<jsp:include page="./nav.jsp"></jsp:include>
	<div class="row">
		<div class="col-2 " style="background-color: #FFE499">
			<h1 class="my-2 font-weight-bold" align="center">MY PAGE</h1>
			<div class="row mt-3">
				<div class="col">
					<div class="btn-group-vertical float-right">
						<a href="./mypage_history"
							class="btn btn-outline-warning btn-lg text-dark" id="all"
							style="width: 215px; background-color: #FFFFFF;">나의 활동</a> <a
							href="./pw_check"
							class="btn btn-outline-warning btn-lg text-dark" id="all"
							style="width: 215px; background-color: #FEFFAD;">회원정보</a> <a
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
		<div class="col my-5 ">
			<form action="./mypage_update_action" method="post"
				enctype="multipart/form-data">
				<h1 class="mt-3 mb-5 font-weight-bold text-center">회원정보</h1>
				<div class="row" style="height: 350px">
					<div
						class="col-4 border-top border-button border-right border-warning">

						<c:if test="${!empty memberImg.memberImgVo.mbri_url }">
							<div class="row mt-3">
								<div class="col">
									<img class="border border-secondary ml-5 rounded-circle" id="output" src="/upload/${memberImg.memberImgVo.mbri_url }"
										width=230px, height=230px>
								</div>
							</div>
							<div class="row mt-3">
								<div class="custom-file">
									<input type="file" name="url" class="custom-file-input"
										id="validatedCustomFile" required accept="image/*" onchange="loadFile(event)"
										> <label
										class="custom-file-label" for="validatedCustomFile">사진수정</label>
								</div>
							</div>
							<div class="row">
								<a href="./delete_member_img_action"
									class="btn btn-outline-danger btn-block">기본이미지로변경</a>
							</div>
						</c:if>

						<c:if test="${empty memberImg.memberImgVo.mbri_url }">
							<div class="row mt-5">
								<img class="border border-secondary ml-5 rounded-circle"
									src="resources/user-solid.png" id="output"
									width=230px, height=230px />
							</div>
							<div class="row mt-3">
								<div class="custom-file">
									<input type="file" name="url" class="custom-file-input"
										id="validatedCustomFile" required accept="image/*" onchange="loadFile(event)"
										> <label
										class="custom-file-label" for="validatedCustomFile">사진수정</label>
								</div>
							</div>
						</c:if>
					</div>
					<div class="col border-top border-button border-warning">
						<h4 class="text-left my-4 font-weight-bold">기본정보</h4>
						<div class="row mt-2">
							<div class="col-2 mt-2">아이디</div>
							<div class="col">
								<input type="text" readonly class="form-control-plaintext"
									name="mbr_id" value="${usertrnrInfo.mbr_id}"
									style="width: 500px">
							</div>
						</div>
						<div class="row my-2">
							<div class="col-2 mt-2">닉네임</div>
							<div class="col">
								<input type="text" class="form-control" name="mbr_nick"
									value="${usertrnrInfo.mbr_nick}" style="width: 500px">
							</div>
						</div>
						<div class="row my-2">
							<div class="col-2 mt-2">비밀번호</div>
							<div class="col" align="left">
								<a href="./pw_change" class="btn btn-warning text-light">비밀번호
									변경</a>
							</div>
						</div>
						<div class="row my-2">
							<div class="col-2 mt-2">Email</div>
							<div class="col">
								<input type="text" class="form-control" name="mbr_emil"
									value="${usertrnrInfo.mbr_emil}" style="width: 500px">
							</div>
						</div>
						<div class="row">
							<div class="col-2">
								트레이너<br>인증
							</div>
							<c:choose>
								<c:when
									test="${usertrnrInfo.mbr_trnr == 'F' && userVrfInfo.mbr_vrf == '대기'}">
									<div class="col-3">
										<input type="text" readonly class="form-control-plaintext"
											name="mbr_trnr" value="신청처리 대기중">
									</div>
									<div>
										<a
											href="./trainer_verification_cancel_action?mbr_idx=${userVrfInfo.mbr_idx }"
											class="btn btn-outline-warning">신청취소</a>
									</div>
								</c:when>
								<c:when test="${userVrfInfo.mbr_vrf == '거절'}">
									<div class="col-3">
										<input type="text" readonly class="form-control-plaintext"
											name="mbr_trnr" value="인증실패">
									</div>
									<div>
										<a
											href="./trainer_verification?mbr_idx=${usertrnrInfo.mbr_idx }"
											class="btn btn-outline-warning">재신청</a>
									</div>
								</c:when>

								<c:when
									test="${usertrnrInfo.mbr_trnr == 'T' && userVrfInfo.mbr_vrf == '승인'}">
									<div class="col-3">
										<input type="text" readonly class="form-control-plaintext"
											name="mbr_trnr" value="승인완료">
									</div>
									<div>
										<a
											href="./trainer_cancel_action?mbr_idx=${usertrnrInfo.mbr_idx }"
											class="btn btn-outline-warning">일반회원으로 변환</a>
									</div>
								</c:when>

								<c:otherwise>
									<div class="col-2">
										<input type="text" readonly class="form-control-plaintext"
											name="mbr_trnr" value="일반회원">
									</div>
									<div>
										<a
											href="./trainer_verification?mbr_idx=${usertrnrInfo.mbr_idx}"
											class="btn btn-outline-warning">트레이너 승급신청 </a>
									</div>

								</c:otherwise>
							</c:choose>

							<div class="col"></div>

						</div>
					</div>
				</div>
				<div class="row" style="height: 215px">
					<div
						class="col border-top border-right border-button border-warning">
						<h4 class=" my-4 font-weight-bold">관심분야</h4>
						<div class="row">
							<div class="col">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="t1"
										value="1" name="wrk_cat"
										<c:if test="${!empty interestMap.wrk_cat1 }">checked</c:if>>
									<label class="form-check-label" for="t1">하체</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="t2"
										value="2" name="wrk_cat"
										<c:if test="${!empty interestMap.wrk_cat2 }">checked</c:if>>
									<label class="form-check-label" for="t2">복부</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="t3"
										value="3" name="wrk_cat"
										<c:if test="${!empty interestMap.wrk_cat3 }">checked</c:if>>
									<label class="form-check-label" for="t3">팔</label>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col">
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="b1"
										value="4" name="wrk_cat"
										<c:if test="${!empty interestMap.wrk_cat4 }">checked</c:if>>
									<label class="form-check-label" for="b1">가슴</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="b2"
										value="5" name="wrk_cat"
										<c:if test="${!empty interestMap.wrk_cat5 }">checked</c:if>>
									<label class="form-check-label" for="b2">어깨</label>
								</div>
								<div class="form-check form-check-inline">
									<input class="form-check-input" type="checkbox" id="b3"
										value="6" name="wrk_cat"
										<c:if test="${!empty interestMap.wrk_cat6 }">checked</c:if>>
									<label class="form-check-label" for="b3">등</label>
								</div>
							</div>
						</div>
					</div>

					<div class="col border-top border-button border-warning">
						<h4 class=" my-4 font-weight-bold">나의 몸 상태</h4>
						<div class="row">
							<div class="col-2 mt-2">키</div>
							<div class="col" align="left">
								<input type="text" class="form-control" name="bd_height"
									value="${bodyData.bodyVo.bd_height}" style="width: 100px">
							</div>
						</div>
						<div class="row mt-2">
							<div class="col-2 mt-2" align="center">몸무게</div>
							<div class="col" align="left">
								<input type="text" class="form-control" name="bd_weight"
									value="${bodyData.bodyVo.bd_weight}" style="width: 100px">
							</div>
						</div>
						<div class="row my-2">
							<div class="col-2 mt-2">나이</div>
							<div class="col" align="left">
								<input type="text" class="form-control" name="bd_age"
									value="${bodyData.bodyVo.bd_age}" style="width: 100px">
							</div>
						</div>
						<br>
					</div>
				</div>

				<div class="row mt-4">
					<div class="col border-top border-warning text-right">
						<a href="#" class="text-danger">탈퇴하기</a>
					</div>

				</div>
				<div class="row mt-2">
					<div class="col">
						<button type="submit" class="btn btn-warning btn-block">회원정보수정</button>
					</div>
				</div>



			</form>
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