<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">

<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">


<script>
var check_timer = null;
check_timer = setInterval(check_new_member,1000);
function check_new_member(){
	var xmlhttp = new XMLHttpRequest();
	
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var datalist = JSON.parse(xmlhttp.responseText);
			
			//갱신된 리스트 갖고오기 
			var list = document.getElementById("now_member_list");
			
			//지우기 위해 갖고 오기
			var temp = document.getElementById("now_member_list");
			for (var i=temp.childNodes.length-1 ; i>=0; i--){
				temp.removeChild(temp.childNodes[i]);
			}
			
			for(data of datalist.newMemberList){
				//table > thead > tr_th > th1_th >th2_th >th3_th
				/** var table = document.createElement("table");
				var thead = document.createElement("thead");
				var tr_th = document.createElement("tr");
				var th1_th = document.createElement("th");
				var th2_th = document.createElement("th");
				var th3_th = document.createElement("th");**/
				
				//tbody(id)> tr > th_tb > td1_tb >td2_tb
				//var tbody = document.createElement("tbody");
				//var tr_tb = document.createElement("tr");
				var tr = document.createElement("tr");
				var th_tb = document.createElement("th");
				var td1_tb = document.createElement("td");
				var td2_tb = document.createElement("td");
				
				
				var iden = data.newMemberIdentity;
				var nick = data.memberVo.mbr_nick;
				var joindate = data.memberVo.mbr_joindate;
				
				
				/**th1_th.innerText="#회원전형";
				th2_th.innerText="#닉네임";
				th3_th.innerText="#가입일시";**/
				th_tb.innerText = iden;
				td1_tb.innerText = nick;
				td2_tb.innerText = joindate;
				
				/**table.setAttribute('class','table table-borderless');
				table.setAttribute('style','font-size: 12px');
				tr_th.setAttribute('class','table-warning')
				th1_th.setAttribute('scope','col')
				th2_th.setAttribute('scope','col')
				th3_th.setAttribute('scope','col')**/
				th_tb.setAttribute('scope','row');
				
				/**table.appendChild(thead);
				thead.appendChild(tr_th);
				tr_th.appendChild(th1_th);
				tr_th.appendChild(th2_th);
				tr_th.appendChild(th3_th);
				table.appendChild(tbody);**/
				//tbody.appendChild(tr_tb);
				//tr_tb.appendChild(tr);
				tr.appendChild(th_tb);
				tr.appendChild(td1_tb);
				tr.appendChild(td2_tb);
				
				
				//list.appendChild(table);	
				list.appendChild(tr);
			}
			
		}
	}
	
	xmlhttp.open("get", "./check_new_member", true);
	//전송할때 어떤 방식으로 전송할건지
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	//login_page호출한다
	xmlhttp.send();
}

</script>

<style>
p.title {
	font-family: 'Do Hyeon';
	font-size: 45px;
}

p.smtitle {
	font-family: 'Do Hyeon';
	font-size: 17px;
}
</style>


</head>
<body>
	<jsp:include page="./nav_simple.jsp"></jsp:include>


	<c:choose>
		<c:when test="${empty sessionManagerData}">
			<jsp:include page="./cannot_read_page.jsp"></jsp:include>
		</c:when>

		<c:otherwise>
			<div class="row">
				<div class="col-2" style="background-color: #FFE499"></div>
				<div class="col text-center my-4">
					<p class="mt-4 title">MANAGER PAGE</p>
					<br> <br>
					<div class="row" style="height: 270px">
						<div
							class="col-3 border-top border-button border-right border-warning">
							<img src="./resources/Cat Run.gif" class="img-thumbnail mt-4">
						</div>
						<div class="col pd-2 border-top border-button border-warning">
							<p class="text-center my-4 smtitle">회원통계표</p>
							<table class="table table-borderless" style="font-size: 12px">
								<thead>
									<tr class="table-warning">
										<th scope="col">#</th>
										<th scope="col">일반회원</th>
										<th scope="col">트레이너</th>
										<th scope="col">총 회원</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<th scope="row">F</th>
										<td>${allMemberList.memberNF }</td>
										<td>${allMemberList.memberTF }</td>
										<td>${allMemberList.memberFNum }</td>
									</tr>
									<tr>
										<th scope="row">M</th>
										<td>${allMemberList.memberNM }</td>
										<td>${allMemberList.memberTM }</td>
										<td>${allMemberList.memberMNum }</td>
									</tr>
									<tr>
										<th scope="row">ALL</th>
										<td>${allMemberList.memberNAll }</td>
										<td>${allMemberList.memberTAll }</td>
										<td>${allMemberList.memberAllNum }</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div
							class="col pd-2  border-top border-button border-left border-warning">
							<p class="text-center my-4 smtitle">새로 가입 리스트</p>

							<table class="table table-borderless" style="font-size: 12px">
								<thead>
									<tr class="table-warning">
										<th scope="col">#회원전형</th>
										<th scope="col">#닉네임</th>
										<th scope="col">#가입일시</th>
									</tr>
								</thead>
								<tbody id="now_member_list">
									<!--<c:forEach items="${newMemberList}" var="newMemberList">
										<tr>
											<th scope="row">${newMemberList.newMemberIdentity}</th>
											<td>${newMemberList.newMemberNick}</td>
											<td>${newMemberList.memberVo.mbr_joindate }
										</tr>
									</c:forEach>-->
								</tbody>
							</table>

						</div>
					</div>

					<div class="row" style="height: 450px">
						<div class="col pd-3 border-top border-button border-warning">
							<p class="text-center my-4 smtitle">트레이너 신청 리스트</p>
							<table class="table table-borderless" style="font-size: 12px">
								<thead>
									<tr class="table-warning">
										<th scope="col">#</th>
										<th scope="col">신청자</th>
										<th scope="col">신청일시</th>
										<th scope="col">처리상황</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${trnrsmList}" var="trnrsm">
										<tr>
											<th scope="row">${trnrsm.TRN_IDX}</th>
											<td>${trnrsm.MBR_NICK }</td>
											<td>${trnrsm.TRN_VDAT}</td>
											<td><c:if test="${trnrsm.MBR_VRF== '대기'}"> 대기 </c:if> <c:if
													test="${trnrsm.MBR_VRF== '승인'}"> 승인 </c:if> <c:if
													test="${trnrsm.MBR_VRF== '거절'}"> 거절 </c:if></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<br>

							<div class="row">
								<div class="col"></div>
								<div class="col"></div>
								<div class="col">
									<nav aria-label="Page navigation example" class="float-right"
										style="height: 30px">
										<ul class="pagination">
											<li class="page-item"><a class="page-link" href="#"
												aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
											</a></li>
											<c:forEach begin="1" end="${pageNum+1}" varStatus="status">
												<li class="page-item"><a class="page-link"
													href="./manager_page?r_num=${status.index }">${status.index }</a></li>
											</c:forEach>

											<li class="page-item"><a class="page-link" href="#"
												aria-label="Next"> <span aria-hidden="true">&raquo;</span>
											</a></li>
										</ul>
									</nav>
								</div>

							</div>
							<div class="row mt-3">
								<div class="col"></div>
								<div class="col">
									<a href="./trnr_applicationlist_page?r_num=1" role="button"
										class="btn btn-sm font-weight-bold"
										style="background-color: #FFCC00">more</a>
								</div>
								<div class="col"></div>
							</div>

						</div>
						<div
							class="col pd-3 border-top border-button border-left border-warning">
							<p class="text-center my-4 smtitle">고객센터 문의</p>
							<table class="table table-borderless" style="font-size: 12px">
								<thead>
									<tr class="table-warning">
										<th scope="col">#</th>
										<th scope="col">회원닉네임</th>
										<th scope="col">제목</th>
										<th scope="col">문의일시</th>
										<th scope="col">처리현황</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${allInquiry}" var="aI">
										<tr>
											<th scope="row">${aI.INQ_IDX}</th>
											<td>${aI.MBR_NICK }</td>
											<td>${aI.INQ_TTL}</td>
											<td>${aI.INQ_IDAT}</td>
											<td><c:if test="${aI.INQ_VRF=='T'}">답변완료</c:if>
												<c:if test="${aI.INQ_VRF=='N'}">처리중</c:if></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<br>
							<div class="row">
								<div class="col"></div>
								<div class="col">
									<a href="./inquiry_list_page" role="button"
										class="mt-2 btn btn-sm font-weight-bold"
										style="background-color: #FFCC00">more</a>
								</div>
								<div class="col"></div>
							</div>
						</div>
					</div>

					<div class="row" style="height: 450px">
						<div class="col pd-3 border-top border-button border-warning">
							<div class="row">
								<!--<div class="col">
									***************  
									<i class="far fa-thumbs-down"></i>
								</div>-->
								<div class="col">
									<p class="text-center my-4 smtitle">신고 리스트</p>
								</div>
							</div>
							<table class="table table-borderless" style="font-size: 12px">
								<thead>
									<tr class="table-warning">
										<th scope="col">#</th>
										<th scope="col">카테고리</th>
										<th scope="col">작성자</th>
										<th scope="col">작성일</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:forEach items="${reportsmlist}" var="reportsmlist">
											<tr>
												<th scope="row">${reportsmlist.RPT_IDX}</th>
												<td>${reportsmlist.RPT_CON}</td>
												<td>${reportsmlist.MBR_NICK}</td>
												<td>${reportsmlist.RPT_RDAT}</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
							<div class="row">
								<div class="col"></div>
								<div class="col">
									<a href="./reportlist_page" role="button"
										class="btn btn-sm font-weight-bold"
										style="background-color: #FFCC00">more</a>
								</div>
								<div class="col"></div>
							</div>
						</div>
						<div
							class="col pd-3 border-top border-button border-left border-warning">
							<p class="text-center my-4 smtitle">블랙리스트</p>
							<table class="table table-borderless" style="font-size: 12px">
								<thead>
									<tr class="table-warning">
										<th scope="col">#</th>
										<th scope="col">회원아이디</th>
										<th scope="col">닉네임</th>
										<th scope="col">회원전형</th>
										<th scope="col">블록일시</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<c:forEach items="${blsmList }" var="blsmlist">
											<tr>
												<th scope="row">${blsmlist.BL_IDX}</th>
												<td>${blsmlist.MBR_ID }</td>
												<td>${blsmlist.MBR_NICK }</td>
												<td>${blsmlist.MBR_TRNR }</td>
												<td>${blsmlist.BL_DATE}</td>
											</tr>
										</c:forEach>
									</tr>
								</tbody>
							</table>
							<br>
							<div class="row">
								<div class="col"></div>
								<div class="col">
									<a href="#" role="button" class="btn btn-sm font-weight-bold"
										style="background-color: #FFCC00">more</a>
								</div>
								<div class="col"></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-2" style="background-color: #FFE499"></div>
			</div>
		</c:otherwise>
	</c:choose>




	<!-- https://htmlcolorcodes.com/ -->







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