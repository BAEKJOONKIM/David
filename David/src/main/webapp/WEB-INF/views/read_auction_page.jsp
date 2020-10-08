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
	var timer_bidding_check=null;
	var remainingTime=0;
	var now_bdng_idx=null;
	var now_bid_mbr_idx=null;
	var now_bid_cst=null;
	var rpl_idx_todelete=null;
	function check_bidding(){
		var postdata="auc_idx="+${data.auctionInfo.auc_idx};
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				//주의
				var data = JSON.parse(xmlhttp.responseText);
				
				var a = document.getElementById("currentBiddingNick");
				var b = document.getElementById("currentBiddingCost");
				var c = document.getElementById("remainTime");
				var d = document.getElementById("bidRegiButton");

				
				if(data.firstVo == "null"){
					clearInterval(timer_bidding_check);
				}else{
					remainingTime = Math.ceil(data.remainingTime/1000);
					c.innerText=remainingTime;
					a.innerText=data.memberVo.mbr_nick;
					b.innerText=data.firstVo.bdng_cst;
				}
				
				if(remainingTime<0){
					clearInterval(timer_bidding_check);
					//c.innerText="경매종료";
					now_bdng_idx=data.firstVo.bdng_idx;
					now_bid_mbr_idx=data.memberVo.mbr_idx;
					now_bid_cst=data.firstVo.bdng_cst;
					end_bidding();
					
					//for(var i=d.childNodes.length-1; i>=0 ;i--){
					//	d.removeChild(d.childNodes[i]);
					//}
				}			
			}
		}
		
		xmlhttp.open("post", "./check_bidding", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);	
	}
	timer_bidding_check=setInterval(check_bidding, 1000);
	//경매 확정
	function end_bidding(){
		var postdata="bdng_idx="+now_bdng_idx+"&auc_idx="+${data.auctionInfo.auc_idx}+"&mbr_idx="+now_bid_mbr_idx+"&bid_cst="+now_bid_cst;
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				//주의
				var data = JSON.parse(xmlhttp.responseText);
			}
		}
		
		xmlhttp.open("post", "./bid_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);
		
		var c = document.getElementById("remainTime");
		
		c.innerText="경매종료";
	}
	
	function bidding_action(){
		
		var auc_idx = ${data.auctionInfo.auc_idx};
		var auc_sdat = '${data.auctionInfo.auc_sdat}';
		var auc_edat = '${data.auctionInfo.auc_edat}';
		var bdng_cst = document.getElementById("bdng_cst").value;
		
		var postdata="auc_idx="+auc_idx +"&auc_sdat="+auc_sdat+"&auc_edat="+auc_edat+"&bdng_cst="+bdng_cst;
		
		var cost = document.getElementById("bdng_cst");
		
		if(cost.value==""){
			alert("가격을 입력해주세요");
			cost.focus();
			return;
		}
		
		
		var xmlhttp = new XMLHttpRequest();

		//호출 후 값을 받았을때... 처리 로직....
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				//주의
				var data = JSON.parse(xmlhttp.responseText);
				
				var a = document.getElementById("currentBiddingNick");
				var b = document.getElementById("currentBiddingCost");
				
				if (data.result=="success"){
					alert("입찰이 성공되었습니다.");
					
					a.innerHTML="<h2>"+data.currentAuctionInfo.bidMember.mbr_nick+"</h2>";
					b.innerHTML="<h2>"+data.currentAuctionInfo.biddingFirst.bdng_cst+"</h2>";
				} else{
					alert("입찰 실패 : "+data.reason);
				}
				if(timer_bidding_check != null){
					clearInterval(timer_bidding_check);
					timer_bidding_check=setInterval(check_bidding, 1000);
				}else{
					timer_bidding_check=setInterval(check_bidding, 1000);
				}

			}
		}
		
		xmlhttp.open("post", "./bidding_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);			
	}
	
	function read_reply() {
		
		var postdata = "auc_idx="+${data.auctionInfo.auc_idx};
		
		var xmlhttp = new XMLHttpRequest();
		
		//호출 후 처리...
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				var dataList = JSON.parse(xmlhttp.responseText);
				var replyDiv = document.getElementById("replyDiv");
				
				var temp = document.getElementById("replyDiv");
				
				for(var i=temp.childNodes.length-1; i>=0 ;i--){
					temp.removeChild(temp.childNodes[i]);
				}
				//<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#auctionReportModal" 
				//onClick="auction_report()" id="reportButton">신고하기</button>
				for(data of dataList.replyList){
					var br = document.createElement("br");
					var br2 = document.createElement("br");
					var div = document.createElement("div");
					var nickdiv = document.createElement("div");
					var btn = document.createElement("button");
					var rbtn = document.createElement("button");
					var cbtn = document.createElement("button");
					var repl_idx = data.reply.arpl_idx;
					var repl_con = document.createTextNode(data.reply.arpl_con);
					var repl_nick = document.createTextNode(data.memberVo.mbr_nick);
					var repl_date = document.createTextNode(data.reply.arpl_rdat+"       ");
					div.setAttribute('class', 'alert alert-dark');
					btn.setAttribute('class','btn btn-danger');
					btn.setAttribute('onClick','reply_delete('+repl_idx+')');
					btn.innerText="삭제";
					rbtn.setAttribute('class','btn btn-warning');
					rbtn.setAttribute('data-toggle','modal');
					rbtn.setAttribute('data-target','#auctionReplyReportModal');
					rbtn.setAttribute('onClick','arpl_idx_todelete('+repl_idx+')');
					//<button type="button" class="btn btn-light">Light</button>
					rbtn.innerText="신고";
					cbtn.setAttribute('class','btn btn-light');
					cbtn.setAttribute('onClick','lets_chat('+data.memberVo.mbr_idx+')');
					div.appendChild(br);
					cbtn.appendChild(repl_nick)
					nickdiv.appendChild(cbtn);
					div.appendChild(nickdiv);
					div.appendChild(br2);
					div.appendChild(repl_con);
					div.appendChild(br);
					div.appendChild(repl_date);
					if(document.getElementById("sessionUserData") != null){
						div.appendChild(rbtn);
					}
					if(document.getElementById("sessionUserData") != null && document.getElementById("sessionUserData").value == data.reply.mbr_idx){
						div.appendChild(btn);
					}
					replyDiv.appendChild(div);
					
						
				}
				
				var button = document.getElementById("replyButton");
				button.setAttribute('onClick','close_reply()');
				button.innerText="댓글 닫기";
				
			}
			
		}
		
		
		
		xmlhttp.open("post", "./read_reply", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);	
	}
	
	function arpl_idx_todelete(idx) {
		
		rpl_idx_todelete = idx;
		
	}
	
	function close_reply(){
		var postdata = "auc_idx="+${data.auctionInfo.auc_idx};
		var xmlhttp = new XMLHttpRequest();
		
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				var dataList = JSON.parse(xmlhttp.responseText);
				var temp = document.getElementById("replyDiv");
				
				for(var i=temp.childNodes.length-1; i>=0 ;i--){
					temp.removeChild(temp.childNodes[i]);
				}
				
				var button = document.getElementById("replyButton");
				button.setAttribute('onClick','read_reply()');
				button.innerText="댓글 보기";
				
			}
		}
		
		xmlhttp.open("post", "./read_reply", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);
	}
	
	function reply_write(){
		var auc_idx=${data.auctionInfo.auc_idx};
		var arpl_con=document.getElementById("arpl_con").value;
		var postdata="auc_idx="+auc_idx+"&arpl_con="+arpl_con;
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				var dataList = JSON.parse(xmlhttp.responseText);
				
				close_reply();
				
				read_reply();
			}
		}
		
		xmlhttp.open("post", "./write_reply_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);
		
	}
	
	function reply_delete(arpl_idx){
		var postdata="auc_idx="+${data.auctionInfo.auc_idx}+"&arpl_idx="+arpl_idx;
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				var dataList = JSON.parse(xmlhttp.responseText);
						
			}
		
		}
		
		xmlhttp.open("post", "./reply_delete_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);
		
		read_reply();
		
	}
	
	
	function auction_pick(){
		var auc_idx=${data.auctionInfo.auc_idx };
		var postdata;
		if(document.getElementById("sessionUserData") != null){
			var mbr_idx=document.getElementById("sessionUserData").value;
			postdata="auc_idx="+auc_idx+"&mbr_idx="+mbr_idx;
			}
		var pickButton=document.getElementById("pickButton");
		var unPickButton=document.getElementById("unpickButton");
		
		
		var xmlhttp = new XMLHttpRequest();
		//호출 후 처리...
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				var data = xmlhttp.responseText;
			
				if(data=='clickLike'){
					pickButton.innerText="un찜하기";
					upPickButton.innerText="un찜하기";
				}else{
					pickButton.innerText="찜하기";
					unPickButton.innerText="찜하기";
				}
			}
		}
		
		
		
		xmlhttp.open("post", "./auction_pick_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);	
	}
	
	function delete_sure() {
		var box = document.getElementById("ask_delete");
		var temp = document.getElementById("ask_delete");
		
		for(var i=temp.childNodes.length-1; i>=0 ;i--){
			temp.removeChild(temp.childNodes[i]);	
		}
		
		
		var text = document.createTextNode("삭제하시겠습니까.");
		var div1 = document.createElement("div");
		var div2 = document.createElement("div");
		var btn1 = document.createElement("button");
		var btn2 = document.createElement("button");
		
		box.setAttribute('style','width:300px; height:300px; position:relative;');
		btn1.innerText="yes";
		btn1.setAttribute('class','btn btn-outline-warning');
		btn1.setAttribute('onClick','auction_delete()');
		btn2.innerText="no";
		btn2.setAttribute('class','btn btn-outline-warning');
		btn2.setAttribute('onClick','delete_cancel()');
		div1.appendChild(btn1);
		div2.appendChild(btn2);
		box.appendChild(text);
		box.appendChild(div1);
		box.appendChild(div2);
		
	}
	
	function delete_cancel(){
		var temp = document.getElementById("ask_delete");
		
		for(var i=temp.childNodes.length-1; i>=0 ;i--){
			temp.removeChild(temp.childNodes[i]);	
		}
	}
	
	function auction_delete(){
		var form = document.getElementById("delete_form");
		var auc_idx = ${data.auctionInfo.auc_idx};
		var postdata = "auc_idx="+auc_idx;
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				var data = JSON.parse(xmlhttp.responseText);
				
				if(data.result == "success"){
					form.submit();
				}else{
					alert(data.result.reason);
					var temp = document.getElementById("ask_delete");
					
					for(var i=temp.childNodes.length-1; i>=0 ;i--){
						temp.removeChild(temp.childNodes[i]);	
					}
				}	
			}
		}
		
		xmlhttp.open("post", "./delete_auction_action", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);	
	}
	
	function report_auction() {
		var b_idx = ${data.auctionInfo.auc_idx};
		var rpt_con = document.getElementById("rpt_con").value;
		var postdata = "b_idx="+b_idx+"&rpt_con="+rpt_con;
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				var data = JSON.parse(xmlhttp.responseText);
				if(data.result == "success"){
					alert("신고되었습니다.");
				}else{
					alert("실패했습니다.");
				}
			}
		}
		
		
		xmlhttp.open("post", "./report_auction", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);
	}
	
	function report_auction_reply() {
		var b_idx = rpl_idx_todelete;
		var rpt_con = document.getElementById("rpt_rpl_con").value;
		var postdata = "b_idx="+b_idx+"&rpt_con="+rpt_con;		
		
		var xmlhttp = new XMLHttpRequest();
		xmlhttp.onreadystatechange = function(){
			if(xmlhttp.readyState==4 && xmlhttp.status == 200){
				var data = JSON.parse(xmlhttp.responseText);
				if(data.result == "success"){
					alert("신고되었습니다.");
				}else{
					alert("실패했습니다.");
				}
				
			}
		}
		
		
		xmlhttp.open("post", "./report_reply_auction", true);
		xmlhttp.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xmlhttp.send(postdata);
	}

	
	
	
</script>
</head>
<body>
	<jsp:include page="./nav.jsp"></jsp:include>
	<input type="hidden" id="sessionUserData" value="${sessionUserData.mbr_idx }">
<!-- <div class="container">-->
	<div>
	<hr class="my-4">
	</div>
	<div class="row">
	

	
		<div class="col-2"></div>
		<div class="col-8">

		<br>
		<br>
		<div class="row">
  			<div class="container alert alert-warning">
  			<div style="text-align:left; float: left">
  				${data.auctionInfo.auc_pnm }
  			</div>
  			<div style="text-align:right; float: right ">
  				등록인 : ${data.memberVo.mbr_nick }
  			</div>
  			</div>
		</div>
			<br>
			<div style="text-align:right">
			<c:if test="${!empty sessionUserData }">
			<a href="./update_auction_page?auc_idx=${data.auctionInfo.auc_idx }"><button
					type="button" class="btn btn-outline-warning">수정하기</button></a><br>
			
			<input type=button class="btn btn-outline-warning" onClick="delete_sure()" value="삭제하기">
			</c:if>
			</div>
			<br>
			
			<div id="carouselExampleControls" class="carousel slide w-75 mx-auto" data-ride="carousel" style="height:400px; overflow:auto; text-align:center;">
  				<div class="carousel-inner">
      			<div class="carousel-item active my-auto">
      			<img src="/upload/${data.firstImg.i_imgname }" class="d-block w-100 h-50" alt="...">
    		</div>
    		<c:if test="${!empty data.imgList }">
        		<c:forEach items="${data.imgList}" var="data">
					<div class="carousel-item my-auto" >
						<img src="/upload/${data.i_imgname }" class="d-block w-100 h-50" alt="...">
					</div>
				</c:forEach>
			</c:if>  
  				</div>
  				<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    			<span class="sr-only">Previous</span>
  				</a>
  				<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    			<span class="carousel-control-next-icon" aria-hidden="true"></span>
    			<span class="sr-only">Next</span>
  				</a>
			</div>
			
			<br>
			<div>
			<hr class="my-4">
			</div>
			
			${data.auctionInfo.auc_pexp }<br><br>
			<c:if test="${!empty sessionUserData }">
			<c:choose>
			<c:when test="${empty pick }">
			<button	id="pickButton" onClick="auction_pick()" type="button" class="btn btn-outline-warning">찜하기</button><br>
			</c:when>
			<c:otherwise>
			<button id="unpickButton" onClick="auction_pick()" type="button" class="btn btn-outline-warning">un찜하기</button><br>
			</c:otherwise>
			</c:choose>
			<!-- #exampleModal-->
			<button type="button" class="btn btn-warning" data-toggle="modal" data-target="#auctionReportModal" id="reportButton">신고하기</button>
			
			<div class="modal fade" id="auctionReportModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
    			  <div class="modal-header">
        			<h5 class="modal-title" id="exampleModalLabel">경매글 신고</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<div class="modal-body">
     			<div class="form-group">
    			<label for="exampleFormControlTextarea1">신고 내용</label>
    			<textarea class="form-control" id="rpt_con" rows="3"></textarea>
  				</div>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        			<button type="button" class="btn btn-primary" onClick="report_auction()" data-dismiss="modal">신고하기</button>
      			</div>
    			</div>
  			</div>
			</div>
			<!-- 댓글신고창 -->
			<div class="modal fade" id="auctionReplyReportModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  			<div class="modal-dialog" role="document">
    			<div class="modal-content">
    			  <div class="modal-header">
        			<h5 class="modal-title" id="exampleModalLabel">경매댓글신고</h5>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          			<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<div class="modal-body">
     			<div class="form-group">
    			<label for="exampleFormControlTextarea1">신고 내용</label>
    			<textarea class="form-control" id="rpt_rpl_con" rows="3"></textarea>
  				</div>
      			</div>
      			<div class="modal-footer">
        			<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
        			<button type="button" class="btn btn-primary" onClick="report_auction_reply()" data-dismiss="modal">신고하기</button>
      			</div>
    			</div>
  			</div>
			</div>
			
			
			<div>
			<hr class="my-4">
			</div>
			</c:if>
			<br> 현재 입찰자 : <span id="currentBiddingNick">${data.bidMember.mbr_nick}</span><br> 입찰가 :
			<span id="currentBiddingCost">${data.biddingFirst.bdng_cst}</span><br>
			<br>
			남은 시간 : <span id="remainTime"></span>
			
			
			<br>
			<c:if test="${!empty sessionUserData }">
			
				<input type="hidden" value="${data.auctionInfo.auc_idx}"
					name="auc_idx"> 입찰하기 - 누구 : ${sessionUserData.mbr_nick }<br>
					<input type="hidden" value="${data.auctionInfo.auc_sdat }" name="auc_sdat">
					<input type="hidden" value="${data.auctionInfo.auc_edat }" name="auc_edat">
				희망가격 : <div id="bidRegiButton"><input id="bdng_cst" type="text" name="bdng_cst"><input
					type="button" onClick="bidding_action()" value="등록"></div>
			
			</c:if>
			<br>
			
				<div>
				<hr class="my-4">
				</div>
			
				<c:if test="${!empty sessionUserData }">
					<div class="form-group">
						<label for="exampleFormControlTextarea1"><h4>댓글</h4></label>
						<textarea class="form-control" id="arpl_con"
							rows="3" ></textarea>
					</div>
					<input id="replyWriteAucidx" type="hidden" name="auc_idx"
						value="${data.auctionInfo.auc_idx }">
					<button	id="replyRegisterButton" onClick="reply_write()" type="button" class="btn btn-outline-warning">댓글등록</button>				
				</c:if>
			
			<br><br>
			
			<div>
				<button	id="replyButton" onClick="read_reply()" type="button" class="btn btn-outline-warning">댓글보기</button>
				
			<div id="replyDiv">
			</div>
			</div>
			


		</div>
		<div class="col-2">
		<div style="position:relative;" id="ask_delete"></div>
		<form action="./auction_delete_complete" id="delete_form">
		</form>
		
		
		
		</div>
		
	</div>


<!-- </div>-->

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