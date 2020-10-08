<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>

function lets_chat(idx) {
	var chat_div = document.getElementById("chat_div");
	
	var temp = document.getElementById("chat_div");
	for(var i=temp.childNodes.length-1; i>=0 ;i--){
		temp.removeChild(temp.childNodes[i]);	
	}
	
	var chat_button = document.createElement("button");
	chat_button.setAttribute('onClick','do_chat('+idx+')');
	chat_button.innerText="채팅";
	chat_div.appendChild(chat_button);
	
}

function do_chat(idx){
	var postdata="mbr_idx="+idx;
	var member = document.getElementById("sessionUserData").value;
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState==4 && xmlhttp.status == 200){
			var data = JSON.parse(xmlhttp.responseText);
				
			view_chat(member);
		}
	}
	
	xmlhttp.open("post", "./start_chat", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(postdata);
}

function view_chat(){
	var postdata=null;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState==4 && xmlhttp.status == 200){
			var data = JSON.parse(xmlhttp.responseText);
				
			var chat_div = document.getElementById("chat_div");
			var close_btn = document.createElement("button");
			var span = document.createElement("span");
			var close_div = document.createElement("div");
			var br = document.createElement("br");
			chat_div.setAttribute('style','height:420px; width:270px; float:right; position:fixed; right:50px; top:100px; overflow:auto; background-color:snow; z-index:1;');
			
			close_div.setAttribute('class','alert alert-dark');
			close_btn.setAttribute('class','close');
			close_btn.setAttribute('aria-label','Close');
			close_btn.setAttribute('onClick','close_chat()');
			span.setAttribute('aria-hidden','true');
			span.innerText="X";
			close_btn.appendChild(span);
			close_div.appendChild(close_btn);
			close_div.appendChild(br);
			
			var temp = document.getElementById("chat_div");
			for(var i=temp.childNodes.length-1; i>=0 ;i--){
				temp.removeChild(temp.childNodes[i]);	
			}
			
			chat_div.appendChild(close_div);
			
			for(list of data.roomList){
				var div=document.createElement("div");
				var members = "";
				var last = list.room.rct_chat;
				var br = document.createElement("br");
				for(member of list.memberList){
					members = members + member.mbr_nick+" ";
				}
				var names = document.createTextNode(members);
				var lastTalk = document.createTextNode(last);
				var a = document.createElement("a");
				a.setAttribute('href','javascript:into_room('+list.room.room_idx+');');
				a.appendChild(names);
				div.setAttribute('class','alert alert-dark');
				
				div.appendChild(a);
				div.appendChild(br);
				div.appendChild(lastTalk);
				chat_div.appendChild(div);
				
			}
		}
	}
	
	xmlhttp.open("post", "./view_chat_list", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(postdata);
}

function close_chat() {
	var temp = document.getElementById("chat_div");
	var caht_div = document.createElement("div");
	for(var i=temp.childNodes.length-1; i>=0 ;i--){
		temp.removeChild(temp.childNodes[i]);	
	}
	temp.setAttribute('style','height:50px; width:50px; float:right; position:fixed; right:80px; top:200px; z-index:1;');
	
}

function into_room(idx){
	var postdata="room_idx="+idx;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState==4 && xmlhttp.status == 200){
			var data = JSON.parse(xmlhttp.responseText);
			
			close_chat();
			var chat_div = document.getElementById("chat_div");
			chat_div.setAttribute('style','height:420px; width:270px; float:right; position:fixed; right:50px; top:100px; overflow:auto; background-color:snow; z-index:1;');
			var view_chat = document.createElement("div");
			var write_chat = document.createElement("div");
			var close_btn = document.createElement("button");
			var exit_btn = document.createElement("button");
			var span = document.createElement("span");
			var espan = document.createElement("span");
			var close_div = document.createElement("div");
			var br = document.createElement("br");
			var textarea = document.createElement("input");
			var chat = document.createElement("input");
			var form = document.createElement("form");
			var session = ${sessionUserData.mbr_idx};
			close_div.setAttribute('class','alert alert-dark');
			exit_btn.setAttribute('class','close');
			exit_btn.setAttribute('aria-label','Close');
			exit_btn.setAttribute('onClick','exit_chat('+idx+')');
			exit_btn.setAttribute('style','float: left;');
			espan.setAttribute('aria-hidden','true');
			espan.innerText="퇴장";
			exit_btn.appendChild(espan);
			close_btn.setAttribute('class','close');
			close_btn.setAttribute('aria-label','Close');
			close_btn.setAttribute('onClick','view_chat()');
			close_btn.setAttribute('style','float: right;');
			span.setAttribute('aria-hidden','true');
			span.innerText="이전";
			close_btn.appendChild(span);
			close_div.appendChild(close_btn);
			close_div.appendChild(exit_btn);
			close_div.appendChild(br);
			view_chat.setAttribute('style','height:250px; width:250px; overflow:auto;');
			
			write_chat.setAttribute('style','height:40px; width:250px;');
			textarea.setAttribute('type','text');
			textarea.setAttribute('style','width:270px;')
			textarea.setAttribute('class','form-control');
			textarea.setAttribute('id','chatCon');
			textarea.setAttribute('row','3');
			chat.setAttribute('type','button');
			chat.setAttribute('class','btn btn-primary btn-sm btn-block');
			chat.setAttribute('onClick','input_chat('+idx+')');
			chat.value="입력";
			
			write_chat.appendChild(textarea);
			
			chat_div.appendChild(close_div);
			for(chatt of data.chat){
				var block_div = document.createElement("div");
				var content = document.createTextNode(chatt.content.chat_con);
				var conbr = document.createElement("br");
				var sender = document.createTextNode(chatt.memberVo.mbr_nick);
				var msender = document.createTextNode("me");
				if(chatt.content.sender_idx == "0"){
					var system = document.createTextNode("=========system=========");
					block_div.appendChild(system);
					block_div.appendChild(conbr);
					block_div.appendChild(content);
				}else if(chatt.content.sender_idx == session){
					var row = document.createElement("div");
					var rcol = document.createElement("div");
					var lcol = document.createElement("div");
					rcol.setAttribute('style','float:right; width:180px;');
					rcol.setAttribute('class','alert alert-warning');
					lcol.setAttribute('style','float:right; width:50px;');
					rcol.appendChild(content);
					lcol.appendChild(msender);
					block_div.appendChild(rcol);
					block_div.appendChild(lcol);
				}else{
					var row = document.createElement("div");
					var rcol = document.createElement("div");
					var lcol = document.createElement("div");
					rcol.setAttribute('style','float:left; width:50px');
					lcol.setAttribute('class','alert alert-dark');
					lcol.setAttribute('style','float:left; width:180px;');
					lcol.appendChild(content);
					rcol.appendChild(sender);
					block_div.appendChild(lcol);
					block_div.appendChild(rcol);
				}
				view_chat.appendChild(block_div);
				
			}
			
			chat_div.appendChild(view_chat);
			chat_div.appendChild(write_chat);
			chat_div.appendChild(chat);
			
			
		}
	}
	
	
	xmlhttp.open("post", "./view_chatting", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(postdata);
}

function input_chat(idx){
	var chatConBox = document.getElementById("chatCon");
	var chatCon = chatConBox.value;
	chatConBox.value="";
	var session = ${sessionUserData.mbr_idx};
	var postdata="room_idx="+idx+"&sender_idx="+session+"&chat_con="+chatCon;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState==4 && xmlhttp.status == 200){
			var data = JSON.parse(xmlhttp.responseText);
			
			into_room(idx);
		
		}
	}
	
	xmlhttp.open("post", "./write_chat", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(postdata);
}

function exit_chat(idx){
	var mbr_idx=${sessionUserData.mbr_idx};
	var postdata="room_idx="+idx+"&mbr_idx="+mbr_idx;
	
	var xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function(){
		if(xmlhttp.readyState==4 && xmlhttp.status == 200){
			var data = JSON.parse(xmlhttp.responseText);
			
			view_chat();
			
		}
	}
	
	xmlhttp.open("post", "./out_chat", true);
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded");
	xmlhttp.send(postdata);
}




</script>
<link
	href="https://fonts.googleapis.com/css?family=Do+Hyeon|Jua|Noto+Sans+KR&display=swap"
	rel="stylesheet">

<style>
a.navbar-brand {
	font-family: 'Do Hyeon';
	font-size: 20px;
}

div.searchbutton {
	font-family: 'Do Hyeon';
	font-size: 20px;
}

nav.LinkButton {
	font-family: 'Do Hyeon';
	/*font-family: 'Yeon Sung';*/
	/*font-family: 'Noto Sans KR';*/
	background-color: white;
	/*font-weight: bold;*/
	height: 65px;
	margin: 0px;
}

ul.top-right {
	font-family: 'Jua';
	font-size: 17px;
}

a.LBothers {
	padding-top: 20px;
	height: 65px;
	font-size: 20px;
}
</style>

<nav class="navbar navbar-expand-lg navbar-light bg-warning">
	<a class="navbar-brand" href="main_page"> <img
			src="/upload/spring_David_workout_upload/david_logo.png" width="180"
			height="60">
		</a>


	<ul class="navbar-nav ml-auto top-right">
		<!-- <li class="nav-item"><a class="nav-link" href="./">sitemap <span
				class="sr-only">(current)</span>
		</a></li> -->
		<c:choose>
			<c:when test="${empty sessionUserData }">
				<li class="nav-item"><a class="nav-link" href="./login_page">로그인</a></li>
				<li class="nav-item"><a class="nav-link" href="./signup_page">회원가입</a></li>
				<li class="nav-item text-disabled mt-2 ml-2">환영합니다!</li>
			</c:when>
			<c:when test="${!empty sessionManagerData}">
				<div>
					<button class="btn btn-light" onClick="view_chat()">채팅창</button>
				</div>
				<li class="nav-item"><a class="nav-link" href="./main_page">메인페이지</a></li>
				<li class="nav-item"><a class="nav-link" href="./manager_page">메니저페이지</a></li>
				<li class="nav-item"><a class="nav-link "
					href="./logout_action">로그아웃</a></li>
				<li class="nav-item"><a class="nav-link " href="./my_page">${sessionManagerData.mbr_nick }
						님 안녕하세요!</a></li>
			</c:when>
			<c:otherwise>
				<div>
					<button class="btn btn-light" onClick="view_chat()">채팅창</button>
				</div>
				<li class="nav-item"><a class="nav-link" href="./main_page">메인페이지</a></li>
				<li class="nav-item"><a class="nav-link " href="./my_page">${sessionUserData.mbr_nick }님,
						안녕하세요!</a></li>
				<li class="nav-item"><a class="nav-link "
					href="./logout_action">로그아웃</a></li>
			</c:otherwise>
		</c:choose>
	</ul>

</nav>


<nav class="nav nav-justified nav-pills LinkButton">
	<h5 class="nav-item p-0">
		<a
			class="nav-link border border-warning text-dark btn-outline-warning LBothers"
			href="#" role="button">알아갑시다</a>
	</h5>
	<h5 class="nav-item  p-0">
		<a
			class="nav-link border border-warning text-dark btn-outline-warning nav-item LBothers"
			role="button" href="./workout_page">운동합시다</a>
	</h5>
	<h5 class="nav-item  p-0">
		<a
			class="nav-link border border-warning text-dark nav-item btn-outline-warning LBothers"
			role="button" href="./auction_list_page">쇼핑합시다</a>
	</h5>
	<div class="nav-item dropdown text-dark">
		<button
			class="btn dropdown-toggle py-3 btn-block btn-outline-warning text-dark"
			type="button" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false" id="dropdownMenu2"
			style="font-size: 20px; height: 65px">소통합시다</button>
		<div class="dropdown-menu text-right " aria-labelledby="dropdownMenu2"
			style="width: 200px">
			<a class="dropdown-item my-2" href="./flex_main_page" role="button"
				style="font-size: 20px;">MyFlex</a>
			<div class="dropdown-divider"></div>
			<a class="dropdown-item my-2" href="./board_main_page" role="button"
				style="font-size: 20px;">자유게시판</a>
		</div>
	</div>
	<h5 class="nav-item">
		<a
			class="nav-link border border-warning text-dark nav-item btn-outline-warning LBothers"
			role="button" href="./customer_center">고객센터</a>
	</h5>

	<c:choose>
		<c:when test="${! empty sessionUserData }">
			<h5 class="nav-item  p-0">
				<a
					class="nav-link border border-warning text-dark btn-outline-warning LBothers"
					role="button" href="./mypage_history">마이페이지</a>
			</h5>
		</c:when>
		<c:otherwise>

		</c:otherwise>
	</c:choose>
</nav>

<div id="chat_div"
	style="height: 50px; width: 50px; float: right; position: fixed; right: 80px; top: 200px; z-index: 1;"></div>
	<input type="hidden" id="sessionUserData" value="${sessionUserData.mbr_idx }">