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
		<div class="col-2"></div>
		<div class="col-8">
		<div class="row">
			<c:if test="${!empty sessionUserData}">
			<div class="container" style="text-align: right;">
				<a href="./write_auction_info_page"><button type="button"
						class="btn btn-warning">경매하기</button></a>
			</div>
			</c:if>
			<form class="form-inline my-2 my-lg-0 container" action="./auction_list_page" style="text-align:center;">
				<img src="resources/auction.jpg" width="100px" height="38px" style="padding:0px 10px 0px 10px;">
				<input class="form-control mr-sm-2" style="width: auto;" 
					type="search" placeholder="Search" aria-label="Search" name="search">
				<button class="btn btn-warning my-2 my-sm-0" type="submit">Search</button>
			</form>
			<br>
			<br>
			<br>
			<br>
			<div class="card-deck">
			<c:forEach items="${auctionList}" var="auction">
				<div class="col-4">
				<div class="card" style="width:18.2rem;">
 		 		<img src="/upload/${auction.thumbnail }" class="card-img-top" alt="..." style="height:11.5rem;">
  				<div class="card-body">
    			<h5 class="card-title">${auction.auctionInfo.auc_pnm }</h5>
    			<c:choose>
				<c:when test="${nowTime<auction.auctionInfo.auc_sdat }">
				<p class="card-text">경매 시작 전</p>
				</c:when>
				<c:when test="${nowTime>auction.auctionInfo.auc_edat }">
				<p class="card-text">끝난 경매</p>
				</c:when>
				<c:when test="${!empty auction.isBidded }">
				<p class="card-text">끝난 경매</p>
				</c:when>
				<c:otherwise>
				<p class="card-text">경매 진행중</p>
				</c:otherwise>
				</c:choose>
    			<a href="./read_auction_page?auc_idx=${auction.auctionInfo.auc_idx }" class="btn btn-primary">자세히보기</a>
  				</div>
				</div>
				<br>
				</div>
			</c:forEach>
			
			</div>
			
			<div class="row container">
			
			<nav aria-label="Page navigation example">
			<br>
  				<ul class="pagination">
    				<c:if test="${nowRound > 1}">
    					<li class="page-item"><a class="page-link" href="./auction_list_page?search=${search }&n_r=${nowRound-1 }&n_p=${(nowRound-2)*5+1 }">이전</a></li>
    				</c:if>
    				<c:if test="${count > ((nowRound-1)*30)+0 }">
   						<li class="page-item"><a class="page-link" href="./auction_list_page?search=${search }&n_r=${nowRound }&n_p=${(nowRound-1)*5+1 }">${(nowRound-1)*5+1 }</a></li>
    				</c:if>
    				<c:if test="${count > ((nowRound-1)*30)+6 }">
    					<li class="page-item"><a class="page-link" href="./auction_list_page?search=${search }&n_r=${nowRound }&n_p=${(nowRound-1)*5+2 }">${(nowRound-1)*5+2 }</a></li>
    				</c:if>
    				<c:if test="${count > ((nowRound-1)*30)+12 }">
    					<li class="page-item"><a class="page-link" href="./auction_list_page?search=${search }&n_r=${nowRound }&n_p=${(nowRound-1)*5+3 }">${(nowRound-1)*5+3 }</a></li>
    				</c:if>
    				<c:if test="${count > ((nowRound-1)*30)+18 }">
    					<li class="page-item"><a class="page-link" href="./auction_list_page?search=${search }&n_r=${nowRound }&n_p=${(nowRound-1)*5+4 }">${(nowRound-1)*5+4 }</a></li>
    				</c:if>
    				<c:if test="${count > ((nowRound-1)*30)+24 }">
    					<li class="page-item"><a class="page-link" href="./auction_list_page?search=${search }&n_r=${nowRound }&n_p=${(nowRound-1)*5+5 }">${(nowRound-1)*5+5 }</a></li>
    				</c:if>
    				<c:if test="${count > ((nowRound-1)*30)+30 }">
    					<li class="page-item"><a class="page-link" href="./auction_list_page?search=${search }&n_r=${nowRound+1 }&n_p=${(nowRound)*5+1 }">다음</a></li>
  					</c:if>
  				</ul>
			</nav>
			</div>
		</div>
		</div>
		<div class="col-2"></div>
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