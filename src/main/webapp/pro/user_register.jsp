<%@page import="com.kosmo.project.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.ArrayList"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/login.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width", initial-scale="1">
	<title>EBProject</title>
	<style type="text/css">
		.jumbotron{
			background-image: url('../img/bottleback.png');
			background-size: cover;
			color: white;
		}
	</style>
	
<script>
$(function() {
	$(document.input.inputbutton).css("background-color","#1CA347");
	$(document.input.inputbutton).prop("disabled",true);
	
    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

});
 function goPage(prm) {
	 if(prm=='update'){
		 document.f.action = "/userinfo";
		 document.f.method="get";
		 document.f.submit();
	 }else if(prm=='logout'){
		 document.f.action = "/logout";
		 document.f.method="post";
		 document.f.submit();
	 }else if(prm=='admin'){
		 document.f.action = "/userlist";
		 document.f.method="get";
		 document.f.submit();
	 }else if(prm=='point'){
		 document.f.action = "/pointlist";
		 document.f.method="get";
		 document.f.submit();
	 }
}
 
 function check(prm) {
	 var form;
	 if(prm=='input'){
		 form = document.input; 
		 form.action="/register";
		 form.submit();
	 }else if(prm=='login'){
		 form = document.log;
		 var inputId = $(form.userid).val();
		 var inputPw = $(form.password).val();
		 if(inputId==""||inputPw==""){
			   	alert("정보를 정확히 입력해 주세요.");
			}else{
				$.ajax({
					type:"POST",
					data:{
						userid : inputId,
						password: inputPw
					},
					url : "/checklog",
					success: function(data){
						if(data=='0'){
							alert("아이디와 비밀번호를 확인해 주세요.");
						}else if(data=='1'){
							form.action="/login" 
						   	form.submit();
						}
					}
				});
			 
			}
		 
	 }   
}
var idcheck=0;
var pwcheck=0;
var signupcheck=0;
function checkid(){
	var form = document.input;
	var inputed = $(form.userid).val();
	$.ajax({
		type:"POST",
		data:{
			userid : inputed
		},
		url : "/checkid",
		success: function(data){
			if(inputed==""||data=='1'){
				$(form.userid).css("background-color","#FFCECE")
				$(form.inputbutton).prop("disabled",true);
				idcheck=0;
			}else if(data=='0'){
				$(form.userid).css("background-color","#B0F6AC")
				idcheck=1;
				if(idcheck==1&&pwcheck==1&&signupcheck==1)
					$(form.inputbutton).prop("disabled",false);
			}
		}
	});
}
function checkpw() {
	var form = document.input;
    var inputed = $(form.password2).val();
    var reinputed = $('#confirm-password').val();
    if(reinputed=="" && (inputed != reinputed || inputed == reinputed)){
        $(form.inputbutton).prop("disabled", true);
        $('#confirm-password').css("background-color", "#FFCECE");
        pwcheck = 0;
    }
    else if (inputed == reinputed) {
        $('#confirm-password').css("background-color", "#B0F6AC");
        pwcheck = 1;
        if(idcheck==1 && pwcheck == 1 && signupcheck==1) {
            $(form.inputbutton).prop("disabled", false);
        }
    } else if (inputed != reinputed) {
        pwcheck = 0;
        $(form.inputbutton).prop("disabled", true);
        $('#confirm-password').css("background-color", "#FFCECE");
        
    }
}
function checksignup() {
	var form = document.input;
    var nickname = $(form.username).val();
    var email = $(form.email).val();
    if(nickname=="" || email=="") {
    	signupcheck=0;
        $(form.inputbutton).prop("disabled", true);
    } else {
    	signupcheck=1;
    	if(idcheck==1&&pwcheck==1&&signupcheck==1)
    		$(form.inputbutton).prop("disabled", false);
    }
}

 </script>
</head>
<body>
<%
UserVO vo = null; 
 try{
	 vo = (UserVO)session.getAttribute("USER");
 }catch(Exception e){

 }

 %>
<nav class="navbar navbar-inverse">
	<div class="container-fluid"
		style="padding-left: 20%; padding-right: 20%">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/pro/user_main.jsp">EBmall</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li class="active-"><a href="#">소개<span class="sr-only"></span></a></li>


				<%
					if (vo != null) {
						if (vo.getAccount().equals("admin")) {
				%>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">포인트상품<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/productlist?currentPage=1">상품수정하기</a></li>
						<li><a href="product_addAll">상품일괄등록하기</a></li>
					</ul></li>
				<%
					} else {
				%>

				<li><a href="/productlist?currentPage=1">포인트상품</a></li>
				<%
					}
					} else {
				%>



				<li><a href="/productlist?currentPage=1">포인트상품</a></li>
				<%
					}
				%>
				<li><a href="/boardlist?currentPage=1">게시판</a></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">포인트<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/pro/point_standard.jsp">적립기준 및 방법</a></li>
					</ul></li>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">Q&A<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<%
							if (vo != null) {
								if (vo.getAccount().equals("member") || vo.getAccount().equals("manager")) {
						%>
						<li><a href="/QuestionListServlet?currentPage=1">1:1 질문</a></li>
						<%
							} else {
						%>

						<%
							}
							}
						%>
						<li><a href="#">자주묻는 질문</a></li>
					</ul></li>
				<%
					if (vo != null) {
				%>
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">마이페이지<span class="caret"></span></a> <%
 	if (vo.getAccount().equals("admin")) {
 %>
					<ul class="dropdown-menu">
						<li><a href="#" onClick="goPage('admin')">회원정보수정</a></li>
						<li><a href="#" onClick="goPage('grade')">회원등급</a></li>
					</ul> <%
 	} else {
 %>
					<ul class="dropdown-menu">
						<li><a href="#" onClick="goPage('update')">내정보수정</a></li>
						<li><a href="/gradeuserpoint?currentPage=1">회원등급</a></li>
						<li><a href="#" onClick="goPage('point')">내포인트 보기</a></li>
						<li><a href="/cartlist?currentPage=1">장바구니</a></li>
						<li><a href="/buylist?currentPage=1">구매목록</a></li>
						<li><a href="/giftlist?currentPage=1">선물함</a></li>
					</ul> <%
 	}
 %></li>
				<%
					}
				%>

			</ul>
			<!-- 
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<input type="text" class="from-control" placeholder="검색어를 입력하세요!">
					</div>
					<button type="submit" class="btn btn-default">검색</button>
				</form>
				 -->
			<form method="post" name="f">

				<input type="hidden" name="currentPage" value="1">

			</form>



			<ul class="nav navbar-nav navbar-right">
				<%
					if (vo != null) {
				%>
				<li><a href="#" onclick="goPage('logout')">로그아웃</a></li>
				<%
					} else {
				%>
				<!-- <li><a href="#" onclick="document.getElementById('id01').style.display='block'">회원가입</a></li>
							<li><a href="#"onclick="document.getElementById('id02').style.display='block'">로그인</a></li> -->
				<li><a href="/pro/user_register.jsp"><span
						class="glyphicon glyphicon-user"></span> 회원가입</a></li>
				<li><a href="/pro/user_login.jsp"><span
						class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
				<%
					}
				%>

			</ul>
			</li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container">
		<div class="jumbotron">
			<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" id="login-form-link">로그인</a>
							</div>
							<div class="col-xs-6">
								<a href="#" class="active" id="register-form-link">회원가입</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" name="log" method="post" role="form" style="display: none;">
									<div class="form-group">
										<input type="text" name="userid" id="userid" tabindex="1" class="form-control" placeholder="아이디" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="비밀번호">
									</div>
									<div class="form-group text-center">
										<input type="checkbox" tabindex="3" class="" name="remember" id="remember">
										<label for="remember" style="color:black">아이디 기억</label>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<button type="button" onclick="check('login')" tabindex="4" class="form-control btn btn-login">로그인</button>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="#" tabindex="5" class="forgot-password">비밀번호를 잊어버리셨나요?</a>
												</div>
											</div>
										</div>
									</div>
								</form>
								<form id="register-form" name="input" method="post" role="form" style="display: block;">
									<div class="form-group">
										<input type="text" name="userid" id="userid" tabindex="1" class="form-control" placeholder="아이디" value="" oninput="checkid()">
									</div>
									<div class="form-group">
										<input type="text" name="username" id="username" tabindex="1" class="form-control" placeholder="이름" value="" oninput="checksignup()">
									</div>
									<div class="form-group">
										<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="이메일" value="" oninput="checksignup()">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password2" tabindex="2" class="form-control" placeholder="비밀번호" oninput="checkpw()">
									</div>
									<div class="form-group">
										<input type="password" name="confirm-password" id="confirm-password" tabindex="2" class="form-control" placeholder="비밀번호확인" oninput="checkpw()">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<button type="button" name="inputbutton" onclick="check('input')" tabindex="4" class="form-control btn btn-register">가입</button>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</div>
	<footer style="background-color: #e5e3e3; color: #000000">
	<div class="container">
		<br>
		<div class="row">
			<div class="col-sm-2" style="text-align: center;">
				<h5>Copyright &copy; 2017</h5>
				<h5>ProjectGroup Team EB</h5>
			</div>
			<div class="col-sm-4">
				<h4>Group</h4>
				<p>저희는 Kosmo교육원에서 EBProject를 개발한 김기택, 정재현, 오원택, 정우근, 신준태, 이세현
					입니다.
			</div>
			<div class="col-sm-2">
				<h4 style="text-align: center;">Navigation</h4>
				<div class="list-group">
					<a href="#" class="list-group-item">소개</a> <a href="#"
						class="list-group-item">포인트상품</a> <a href="#"
						class="list-group-item">포인트</a> <a href="#"
						class="list-group-item">Q&A</a>
				</div>
			</div>
			<div class="col-sm-2">
				<h4 style="text-align: center;">SNS</h4>
				<div class="list-group">
					<a href="#" class="list-group-item">KAKAOTALK</a> <a href="#"
						class="list-group-item">INSTAGRAM</a> <a href="#"
						class="list-group-item">FACEBOOK</a> <a href="#"
						class="list-group-item">LINE</a>
				</div>
			</div>
			<div class="col-sm-2">
				<h4 style="text-align: center;">&nbsp;by Team EB</h4>
			</div>

		</div>

	</div>
	</footer>
	﻿

</body>
</html>