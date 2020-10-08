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

<script>
	function like_action() {
		var wrk_idx = ${data.workOutVo.wrk_idx};


		//AJAX 호출...코드 시작
		var xmlhttp = new XMLHttpRequest();

		//호출 후 값을 받았을때... 처리 로직....
		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var data = JSON.parse(xmlhttp.responseText);
				var likeButton = document.getElementById("likeButton");

				while (likeButton.firstChild) {
					likeButton.removeChild(likeButton.firstChild);
				}

				if (data.isLike == "like") {
					//likeButton.innerHTML="<img src='/upload/spring_David_workout_upload/Finger_heart.png' style=\"width: 2rem; height: 2rem;\">";
					var img = document.createElement("img");
					img
							.setAttribute("src",
									"resources/Finger_heart.png");
					img.style.width = "2rem";
					img.style.height = "2rem";

					likeButton.appendChild(img);

				} else {
					//likeButton.innerHTML="<img src=\"/upload/spring_David_workout_upload/Finger_heart_empty.png\" style=\"width: 2rem; height: 2.15rem;\">";

					var img = document.createElement("img");
					img
							.setAttribute("src",
									"resources/Finger_heart_empty.png");
					img.style.width = "2rem";
					img.style.height = "2rem";

					likeButton.appendChild(img);

				}
			}
		};

		xmlhttp.open("post", "./like_workout_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");

		xmlhttp.send("wrk_idx=" + wrk_idx);

	}

	function pick_action() {
		var wrk_idx = ${data.workOutVo.wrk_idx};


		//AJAX 호출...코드 시작
		var xmlhttp = new XMLHttpRequest();

		//호출 후 값을 받았을때... 처리 로직....
		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				var data = JSON.parse(xmlhttp.responseText);
				var pickButton = document.getElementById("pickButton");

				while (pickButton.firstChild) {
					pickButton.removeChild(pickButton.firstChild);
				}

				if (data.isPick == "pick") {
					alert("찜 했습니다");
					var img = document.createElement("img");
					img.setAttribute("src",
							"resources/plus.png");
					img.style.width = "2rem";
					img.style.height = "2.15rem";

					pickButton.appendChild(img);

				} else {
					alert("찜 취소했습니다");
					var img = document.createElement("img");
					img.setAttribute("src",
							"resources/plus.png");
					img.style.width = "2rem";
					img.style.height = "2.15rem";

					pickButton.appendChild(img);
				}

			}
		};

		xmlhttp.open("post", "./pick_workout_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");

		xmlhttp.send("wrk_idx=" + wrk_idx);

	}

	function report_auction() {
		var b_idx = ${data.workOutVo.wrk_idx};
		var rpt_con = document.getElementById("rpt_con").value;
		var postdata = "b_idx="+b_idx+"&rpt_con="+rpt_con;
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				var data = JSON.parse(xmlhttp.responseText);
				if(data.result == "success"){
					alert("신고되었습니다.");
				}
			}
		}
		
		
		xmlhttp.open("post", "./report_workout_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);
	}
	

	function reply_action() {
		var wrk_idx = ${data.workOutVo.wrk_idx};
		var replyContent = document.getElementById("replyContent").value;
		var postdata = "wrk_idx=" + wrk_idx + "&wrpl_con=" + replyContent;
		var tempNick = document.getElementById("sessionUserData").value;
		var img = document.getElementById("img").src




		
		//AJAX 호출...코드 시작
		var xmlhttp = new XMLHttpRequest();

		//호출 후 값을 받았을때... 처리 로직....
		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {

				var data = xmlhttp.responseText;//JSON.parse(xmlhttp.responseText);
				var div = document.getElementById("replied");
				var br = document.createElement("br");
				var br2 = document.createElement("br");
				var img1 = document.createElement("img");
				var space = document.createTextNode(" ");
				var content = document.createTextNode(replyContent);
				var nick = document.createTextNode(tempNick);
				var time = document.createTextNode(data);
				img1.setAttribute('src', img);
				img1.style.width = "40px";
				img1.style.height = "40px";
				
				div.setAttribute('class', 'alert alert-warning');
				div.appendChild(img1);
				div.appendChild(space);
				div.appendChild(nick);
				div.appendChild(br);
				div.appendChild(content);
				div.appendChild(br2);
				div.appendChild(time);
				div.appendChild(btn);

			}

		};

		xmlhttp.open("post", "./write_workout_reply_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");

		xmlhttp.send(postdata);
	}

	function delete_action(wrpl_idx) {
		var wrpl_idx = document.getElementById("wrpl_idx").value
		var postdata = "wrk_idx=" + ${data.workOutVo.wrk_idx}+"&wrpl_idx=" + wrpl_idx;

		//AJAX 호출...코드 시작
		var xmlhttp = new XMLHttpRequest();

		//호출 후 값을 받았을때... 처리 로직....
		xmlhttp.onreadystatechange = function() {

			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {


			}
		};

		xmlhttp.open("post", "./delete_workout_reply_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");

		xmlhttp.send(postdata);

	}
</script>
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">
<style>
div.container {
	font-family: 'Jua';
	font-size: 20px;
}


</style>

</head>
<body>
	<jsp:include page="./nav.jsp"></jsp:include>
	<div>
		<hr class="my-4">
	</div>
	<div class="container">
		<div class='row'></div>
		<div class="col"></div>
		<div class="col">
			<div class="embed-responsive embed-responsive-16by9">
				<iframe class="embed-responsive-item"
					src="/upload/spring_David_workout_upload/${data.workOutVo.wrk_vurl }"
					frameborder="0" allowfullscreen></iframe>
			</div>
			<div class="row">
				<div class="container">
					<h3 class="display-5">${data.workOutVo.wrk_ttl}</h3>
				</div>
				<div class="col" style="text-align: left;">
					조회수 ${data.workOutVo.wrk_rcnt}회 <br>
					${data.workOutVo.wrk_wdat}
				</div>
				<c:if test="${!empty sessionUserData }">
					<div class="row" role="group" aria-label="Basic example"
						style="text-align: right;">

						<button id="likeButton" onClick="like_action()"
							class="btn btn-outline-warning border-0">
							<c:if test="${!empty data.isLike }">
								<img src="resources/Finger_heart.png"
									style="width: 2rem; height: 2rem;">

							</c:if>
							<c:if test="${empty data.isLike }">
								<img
									src="resources/Finger_heart_empty.png"
									style="width: 2rem; height: 2rem;">
							</c:if>

						</button>

						<button id="pickButton" onclick="pick_action()"
							class="btn btn-outline-warning border-0">
							<img src="resources/plus.png"
								style="width: 2rem; height: 2.15rem;">
						</button>
						<button type="button" class="btn btn-warning" data-toggle="modal"
							data-target="#auctionReportModal" id="reportButton">신고하기</button>

						<div class="modal fade" id="auctionReportModal" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">동영상 신고</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<div class="form-group" style="text-align:left;">
											<label for="exampleFormControlTextarea1">신고 내용</label>
											<textarea class="form-control" id="rpt_con" rows="3"></textarea>
										</div>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">취소</button>
										<button type="button" class="btn btn-primary"
											onClick="report_auction()" data-dismiss="modal">신고하기</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</c:if>
			</div>
			<div>
				<hr class="my-4">
			</div>
			<img src="/upload/${data.memberImgVo.mbri_url }"
										width=60px, height=60px> ${data.memberVo.mbr_nick }<br>
			${data.workOutVo.wrk_con }<br> <br> <a
				href="./workout_page">목록으로</a>
			<c:if
				test="${!empty sessionUserData && sessionUserData.mbr_idx == data.workOutVo.mbr_idx }">
				<a href="./update_workout_page?wrk_idx=${data.workOutVo.wrk_idx }">수정하기</a>
				<a
					href="./delete_workout_action?wrk_idx=${data.workOutVo.wrk_idx }&mbr_idx=${data.memberVo.mbr_idx}">삭제</a>
			</c:if>

		</div>
		<br>

		<h4>댓글</h4>
		<div>
		<div id="replied"></div>
			<c:forEach items="${replyimg }" var="data">


				<div class="alert alert-warning" role="alert">
					<input type="hidden" id="wrpl_idx" value="${data.WRPL_IDX }">
					<img src="/upload/${data.MBRI_URL }"
										width=40px, height=40px> <a href="javascript:lets_chat(${data.MBR_IDX})" style="color:black;">${data.MBR_NICK }</a> <br> ${data.WRPL_CON }<br>
					${data.WRPL_WDAT }
					<c:if
						test="${!empty sessionUserData && sessionUserData.mbr_idx == data.MBR_IDX }">
						<%-- <a href="./update_workout_reply_page?wrpl_idx=${data.wrpl_idx }"
							class="btn btn-warning">수정하기</a> --%>
						<button id="deleteButton" onclick="delete_action()"
							class="btn btn-danger">삭제</button>
					</c:if>
				</div>

			</c:forEach>
			
		</div>
		<br>
		<c:if test="${!empty sessionUserData }">
			<h5>댓글 달기</h5>
			<input type="hidden" id="sessionUserData"
				value="${sessionUserData.mbr_nick }">
			<div class="form-group">
				<label id="mbr_nick" for="nick"><img id="img" src="/upload/${memberImg.memberImgVo.mbri_url }"
										width=60px, height=60px> ${sessionUserData.mbr_nick }</label>
				<textarea class="form-control" id="replyContent" rows="3"
					name="replyContent"></textarea>
			</div>
		
			<div class="container" style="text-align: right">
				<button id="replyButton" onclick="reply_action()"
					class="btn btn-warning">댓글달기</button>
			</div>
		</c:if>
		<div class="col"></div>
		<br> <br>
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