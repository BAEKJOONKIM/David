<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="https://kit.fontawesome.com/52e12d5aa9.js"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>


function read_new_reply() {
	var brd_idx = document.getElementById("brd_idx").value;
	//var brpl_idx = document.getElementById("brpl_idx").value;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var datalist = JSON.parse(xmlhttp.responseText);
		
		//새 리스트
		var newlist = document.getElementById("replylist");
		
		//옛날 리스트
		var oldlist = document.getElementById("replylist");
		for(var i= oldlist.childNodes.length-1; i>=0; i--){
			oldlist.removeChild(oldlist.childNodes[i]);
		}
		
		
		for(data of datalist.replyData){
			
			//tr > td_n >td_m >td_c >td_d
		
			var tr = document.createElement("tr");
			var td_n = document.createElement("td");
			var td_m = document.createElement("td");
			var td_c = document.createElement("td");
			var p = document.createElement("p");
			var td_d = document.createElement("td");
			var c_if = document.createElement("c:if");
			var button = document.createElement("button");
			var a = document.createElement("a");
		
			var brpl_idx = data.boardReplyVo.brpl_idx;
			var mbr_nick = data.memberVo.mbr_nick;
			var brpl_con = data.boardReplyVo.brpl_con;
			var brpl_rdat = data.boardReplyVo.brpl_rdat;
			var mbr_idx = data.memberVo.mbr_idx
		
			td_n.innerText = brpl_idx;
			td_m.innerText = mbr_nick;
			p.innerText = brpl_con;
			td_d.innerText = brpl_rdat;
			button.innerText = "삭제";
			
			a.setAttribute('style','color:black;');
			a.setAttribute('href','javascript:lets_chat('+mbr_idx+')');
			a.appendChild(td_m)
			
			p.setAttribute('class','text-break text-center');
			c_if.setAttribute('test','${!empty sessionUserData && sessionUserData.mbr_idx == replydata.boardReplyVo.mbr_idx }');
			button.setAttribute('onClick', 'deleteReply('+ brpl_idx +')');
			button.setAttribute('type','button');
			button.setAttribute('class','btn btn-outline-warning btn-sm ml-2');
			button.setAttribute('style','height:25px;padding-top:3px;font-size:13px;font-family:Do Hyeon');
			
			tr.appendChild(td_n);
			tr.appendChild(a);
			tr.appendChild(td_c);
			tr.appendChild(td_d);
			td_c.appendChild(p);
			td_d.appendChild(c_if);
			
			if(data.sessionUserData == "true"){
			c_if.appendChild(button);
			}
			
			
			
			newlist.appendChild(tr);
		}
		
		}
		
		}
	
	//전송할때 어떤 방식으로 전송할건지
	xmlhttp.open("post", "./read_new_reply", true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	//login_page호출한다
	xmlhttp.send("brd_idx=" + brd_idx);
	
	
	
}


function deleteReply(brpl_idx){
	
	var brpl_con = document.getElementById("brpl_con").value;
	var brd_idx = document.getElementById("brd_idx").value;
	
	var xmlhttp = new XMLHttpRequest();
	 
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			read_new_reply();
		}
}
	//2.
	xmlhttp.open("post", "./board_delete_reply_action", true);
	//전송할때 어떤 방식으로 전송할건지
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	//login_page호출한다
	xmlhttp.send('brpl_con=' + brpl_con + '&brd_idx=' + brd_idx +'&brpl_idx=' + brpl_idx);
}





function sendReply(){
	
	//1.
	var brpl_con = document.getElementById("brpl_con").value;
	var brd_idx = document.getElementById("brd_idx").value;
	
	
	var xmlhttp = new XMLHttpRequest();
	 
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			
			//3. insert성공하고 나서 실행되는 것; 
			read_new_reply();
			
			//작성하기 누르면 textarea지워주기 
			var textInBox = document.getElementById("brpl_con");
			textInBox.value="";
	
		}
		
}
		//2.
		xmlhttp.open("post", "./board_write_reply_action", true);
		//전송할때 어떤 방식으로 전송할건지
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		//login_page호출한다
		xmlhttp.send('brpl_con=' + brpl_con + '&brd_idx=' + brd_idx);
}






</script>


<style>
p.title {
	font-size: 30px;
	font-family: 'Do Hyeon';
	padding: 10px 0px 10px 0px;
	margin: 0px;
}

div.words {
	font-size: 18px;
	font-family: 'Do Hyeon';
}

label.words {
	font-size: 17px;
	font-family: 'Do Hyeon';
}

div.writer {
	font-size: 17px;
	font-family: 'Do Hyeon';
}

p.likecount {
	font-size: 17px;
	font-family: 'Do Hyeon';
}

a.words {
	font-size: 19px;
	font-family: 'Do Hyeon';
}

input.words {
	font-size: 19px;
	font-family: 'Do Hyeon';
}

form.words {
	font-size: 17px;
	font-family: 'Do Hyeon';
}

p.words {
	font-size: 17px;
	font-family: 'Do Hyeon';
	padding-top: 20px;
}

i.good {
	padding: 23px 0px 0px 5px;
}
</style>

</head>
<body>
	<jsp:include page="./nav.jsp"></jsp:include>
	<div class="container">
		<div class="row">
			<!-- 1 -->
			<div class="col"></div>
			<!-- 2 -->
			<div class="col-9">
				<br>

				<div class="row alert alert-warning mx-auto" role="alert">
					<div class="col">
						<br>
						<p class="title">${data.boardVo.brd_ttl }</p>
						<br>
					</div>
					<div class="col"></div>
					<div class="col mt-2 mr-auto"></div>
					<c:choose>
						<c:when
							test="${!empty sessionUserData && data.boardVo.mbr_idx == sessionUserData.mbr_idx}">
							<a href="./board_update_page?brd_idx=${data.boardVo.brd_idx }"
								style="width: 90px; height: 40px; margin-top: 10px"
								role="button" class="btn btn-outline-warning words mr-2">수정
							</a>
							<a
								href="./board_delete_content_action?brd_idx=${data.boardVo.brd_idx }"
								style="width: 90px; height: 40px; margin-top: 10px"
								role="button" class="btn btn-outline-warning words">삭제</a>
						</c:when>
						<c:otherwise>
							<div class="row float-right mr-2">
								<p class="words">로그인해서 댓글 확인하세요!</p>
								<i class="fas fa-bell fa-1x good"></i>
							</div>

						</c:otherwise>
					</c:choose>
					<div class="col mt-2">
						<a href="./board_main_page" role="button" style="width: 120px"
							class="btn btn-warning words float-right">목록</a>
					</div>
				</div>
				<br>
				<div class="row mx-auto">
					<div class="col-2 words" style="text-align: left">글쓴이:
						${data.memberVo.mbr_nick }</div>
				</div>

				<hr class="my-4">

				<br>

				<div class="row mx-auto" style="height: 300px">
					<div class="col words">${data.boardVo.brd_con }</div>
				</div>
				<div class="row">
					<div class="col words" style="text-align: right">
						작성일: ${data.boardVo.brd_bdat } <br> 조회수:
						${data.boardVo.brd_rcnt }
					</div>
				</div>

				<hr class="my-4">
			</div>

			<!-- 3 -->
			<div class="col"></div>

		</div>
		<br>

		<!-- Reply -->
		<div class="row">
			<!-- 1 -->
			<div class="col"></div>
			<!-- 2 -->
			<div class="col-9">
				<div class="row">
					<c:if test="${!empty sessionUserData }">
						<div class="col-1">
							<form action="./board_like_action" method="post">
								<input type="hidden" id="brd_idx" name="brd_idx"
									value="${data.boardVo.brd_idx}" class="words" />

								<button type="submit" class="btn btn-light">
									<c:if test="${!empty dataLike}">
										<img src="./resources/heart!.png"
											style="width: 2rem; height: 2rem;">
									</c:if>
									<c:if test="${empty dataLike}">
										<img src="./resources/heart_empty2.png"
											style="width: 1.8rem; height: 1.6rem; margin-top: 0.2rem;">
									</c:if>
								</button>

							</form>
						</div>
					</c:if>
					<div class="col mt-2" style="text-align: left; padding: 0px">
						<p class="likecount ">좋아요 수 : ${countLikeData }</p>
					</div>

					<c:if test="${!empty sessionUserData }">
						<div class="col-4" style="text-align: right">
							<a class="btn btn-warning words" data-toggle="collapse"
								style="width: 160px" href="#collapseExample" role="button"
								aria-expanded="false" aria-controls="collapseExample"
								onClick="read_new_reply()"> 댓글 보기 </a>
						</div>
					</c:if>

				</div>

				<div class="collapse" id="collapseExample">
					<div class="card card-body">

						<div class="row mt-4">
							<div class="col">

								<!-- <form action="./board_write_reply_action" method="post"> -->
								<c:if test="${!empty sessionUserData }">
									<div class="row mb-2 writer">
										<div class="col-2">댓글 작성자 :</div>
										<div class="col">${sessionUserData.mbr_nick }</div>
										<div class="col"></div>
									</div>
									<div class="row">
										<!-- id, box -->
										<label for="brpl_con" class="col-sm-2 control-label words">댓글
											내용 :</label>
										<div class="col-sm-10">
											<textarea id="brpl_con" class="form-control" name="brpl_con"
												rows="3"></textarea>
											<input type="hidden" value="${data.boardVo.brd_idx}"
												name="brd_idx" id="brd_idx">
										</div>
									</div>
									<div class="row mt-4">
										<div class="col-2"></div>
										<div class="col-3">
											<input type="button"
												class="btn btn-outline-warning btn-block words"
												onClick="sendReply()" value="댓글 작성">
										</div>
										<div class="col"></div>
									</div>
									<!-- </form> -->
								</c:if>
							</div>
						</div>

						<div class="row mt-4">
							<table class="table-sm table table-hover"
								style="text-align: center">

								<thead>
									<tr>
										<th scope="col" style="width: 50px">#</th>
										<th scope="col" style="width: 100px">작성자</th>
										<th scope="col" style="width: 400px">내용</th>
										<th scope="col">작성일</th>
									</tr>
								</thead>
								<tbody id="replylist">
									<!--<c:forEach items="${replyData }" var="replydata">
										<tr>
											<td>${replydata.boardReplyVo.brpl_idx }</td>
											<td>${replydata.memberVo.mbr_nick }</td>

											<td><p class="text-break text-center">
													${replydata.boardReplyVo.brpl_con}</p></td>

											<td>${replydata.boardReplyVo.brpl_rdat }<c:if
													test="${!empty sessionUserData && sessionUserData.mbr_idx == replydata.boardReplyVo.mbr_idx }">
													<span onClick="#" type="button">삭제</span>
												</c:if>
											</td>

										</tr>
									</c:forEach>-->
								</tbody>

							</table>
						</div>
					</div>
				</div>

				<!-- 3 -->
			</div>
			<div class="col"></div>
		</div>


		<br> <br> <br>
	</div>
	<br>
	<br>





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