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
</head>
<body>

	<jsp:include page="./nav.jsp"></jsp:include>

	<div class="row">
		<div class="col"></div>
		<div class="col-10">
			<h2 class="text-center my-5 font-weight-bold">My Flex 내가 쓴 댓글</h2>
			<div class="row">
				<div class="col pd-2 border-button border-warning">
					<c:if test="${empty myFlexReplyMyWritingList }">
						<h6 class="m-5 text-center">등록한 댓글이 없습니다.</h6>
					</c:if>
					<c:if test="${!empty myFlexReplyMyWritingList }">
						<table class="table table-hover" >
							<thead>
								<tr >
									<th scope="col">댓글번호</th>
									<th scope="col">댓글</th>
									<th scope="col">작성자</th>
									<th scope="col">작성일</th>
								</tr>
							</thead>
							<tbody>
								<!-- foreach문 수정완료 -->
								<c:forEach items="${myFlexReplyMyWritingList }" var="data">
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
					<nav aria-label="Page navigation example">
					<br>
		  				<ul class="pagination">
		    				<c:if test="${nowRound > 1}">
		    					<li class="page-item"><a class="page-link" href="./my_flex_reply_detail?n_r=${nowRound-1 }&n_p=${(nowRound-2)*5+1 }">이전</a></li>
		    				</c:if>
		    				<c:if test="${count > ((nowRound-1)*50)+0 }">
		   						<li class="page-item"><a class="page-link" href="./my_flex_reply_detaill?n_r=${nowRound }&n_p=${(nowRound-1)*5+1 }">${(nowRound-1)*5+1 }</a></li>
		    				</c:if>
		    				<c:if test="${count > ((nowRound-1)*50)+10 }">
		    					<li class="page-item"><a class="page-link" href="./my_flex_reply_detail?n_r=${nowRound }&n_p=${(nowRound-1)*5+2 }">${(nowRound-1)*5+2 }</a></li>
		    				</c:if>
		    				<c:if test="${count > ((nowRound-1)*50)+20 }">
		    					<li class="page-item"><a class="page-link" href="./my_flex_reply_detail?n_r=${nowRound }&n_p=${(nowRound-1)*5+3 }">${(nowRound-1)*5+3 }</a></li>
		    				</c:if>
		    				<c:if test="${count > ((nowRound-1)*50)+30 }">
		    					<li class="page-item"><a class="page-link" href="./my_flex_reply_detail?n_r=${nowRound }&n_p=${(nowRound-1)*5+4 }">${(nowRound-1)*5+4 }</a></li>
		    				</c:if>
		    				<c:if test="${count > ((nowRound-1)*50)+40 }">
		    					<li class="page-item"><a class="page-link" href="./my_flex_reply_detail?n_r=${nowRound }&n_p=${(nowRound-1)*5+5 }">${(nowRound-1)*5+5 }</a></li>
		    				</c:if>
		    				<c:if test="${count > ((nowRound-1)*50)+50 }">
		    					<li class="page-item"><a class="page-link" href="./my_flex_reply_detail?n_r=${nowRound+1 }&n_p=${(nowRound)*5+1 }">다음</a></li>
		  					</c:if>
		  				</ul>
					</nav>
				</div>
			</div>
		</div>
		<div class="col"></div>
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