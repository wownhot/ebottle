<%@page import="com.kosmo.project.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<title>EBProject</title>
<style type="text/css">
#slider {
  position: relative;
  overflow: hidden;
  margin: 20px auto 0 auto;
  border-radius: 4px;
}
@media(max-width:800px){
	#slider{
		display: none;
	}
	#pccoupon{
		display:none;
	}

}
#mobileback{
	display:block;
}
@media(min-width:800px){
	#mobileback{
		display:none;
	}
	
}
#mobilecoupon{
	display:block;
}
@media(min-width:800px){
	#mobilecoupon{
		display:none;
	}
}

#slider ul {
  position: relative;
  margin: 0;
  padding: 0;
  height: 400px;
  list-style: none;
}

#slider ul li {
  position: relative;
  display: block;
  float: left;
  margin: 0;
  padding: 0;
  width: 1000px;
  height: 450px;
  background: #ccc;
  text-align: center;
  line-height: 450px;
}

a.control_prev, a.control_next {
  position: absolute;
  top: 40%;
  z-index: 999;
  display: block;
  padding: 4% 3%;
  width: auto;
  height: auto;
  background: #2a2a2a;
  color: #fff;
  text-decoration: none;
  font-weight: 600;
  font-size: 18px;
  opacity: 0.8;
  cursor: pointer;
}

a.control_prev:hover, a.control_next:hover {
  opacity: 1;
  -webkit-transition: all 0.2s ease;
}

a.control_prev {
  border-radius: 0 2px 2px 0;
}

a.control_next {
  right: 0;
  border-radius: 2px 0 0 2px;
}

.slider_option {
  position: relative;
  margin: 10px auto;
  width: 160px;
  font-size: 18px;
}

</style>

<script>
	function goPage(prm) {
		if (prm == 'update') {
			document.f.action = "/userinfo";
			document.f.method = "get";
			document.f.submit();
		} else if (prm == 'logout') {
			document.f.action = "/logout";
			document.f.method = "post";
			document.f.submit();
		} else if (prm == 'admin') {
			document.f.action = "/userlist";
			document.f.method = "get";
			document.f.submit();
		} else if (prm == 'point') {
			document.f.action = "/pointlist";
			document.f.method = "get";
			document.f.submit();
		} else if (prm == 'grade') {
			document.f.action = "/gradelist";
			document.f.method = "get";
			document.f.submit();
		}
	}
</script>
</head>
<body>
	<%
		UserVO vo = null;
		try {
			vo = (UserVO) session.getAttribute("USER");
		} catch (Exception e) {

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
	<div class="container" >

		<div class="jumbotron" style="background-color:white; padding-top:0px;">
			<div id="slider">
				<a href="#" class="control_next">></a> <a href="#"
					class="control_prev"><</a>
				<ul>
					<li><img src="../img/11.jpg" style="width:1000px"></li>
					<li><img src="../img/22.jpg" style="width:1000px"></li>
					<li><img src="../img/33.jpg" style="width:1000px"></li>
	
				</ul>
			</div>
			<img src="../img/mainback.png" id="mobileback" style="width:100%">
		</div>
			
			<div id="mobilecoupon">
				<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title"><span class="glyphicon glyphicon-gift"></span>
				&nbsp;&nbsp; 최신 상품 목록</h3>
			</div>
			<div class="panel-body">
				<div class="media">
					<div class="media-left">
						<a href="/productlist?currentPage=1"><img class="media-object" src="../img/sevencupon.png" alt="11번가 쿠폰 이미지"></a>
					</div>
					<div class="media-body">
						<h5><a href="#">세븐일레븐 모바일 상품권</a>&nbsp;<span class="badge">new</span></h5>
						공병 포인트로 상품 구매시 최대 20% 할인.				
					</div>
				</div>
				<hr>
				<div class="media">
					<div class="media-left">
						<a href="#"><img class="media-object" src="../img/gscupon.png" alt="11번가 쿠폰 이미지"></a>
					</div>
					<div class="media-body">
						<h5><a href="#">GS25 모바일 상품권</a>&nbsp;<span class="badge">new</span></h5>
						공병 포인트로 상품 구매시 최대 20% 할인.				
					</div>
					<hr>
					<div class="media">
					<div class="media-left">
						<a href="#"><img class="media-object" src="../img/cucupon.png" alt="11번가 쿠폰 이미지"></a>
					</div>
					<div class="media-body">
						<h5><a href="#">CU 모바일 상품권</a>&nbsp;<span class="badge">new</span></h5>
						공병 포인트로 상품 구매시 최대 20% 할인.				
					</div>
				</div>
				</div>
			</div>
		</div>
		</div>
		
		
		<div id="pccoupon">
		<div class="panel panel-primary">
			<div class="panel-heading">
				<h3 class="panel-title">
					<span class="glyphicon glyphicon-gift"></span> &nbsp;&nbsp; 최신 상품
					목록
				</h3>
			</div>
			<div class="panel-body" style="width: 50%; display: inline-block;">
				<div class="media">
					<div class="media-left">
						<a href="/productlist?currentPage=1"><img class="media-object"
							src="../img/sevencupon1.png" alt="11번가 쿠폰 이미지"></a>
					</div>
					<div class="media-body">
						<h5>
							<a href="#">세븐일레븐 5천원 모바일 상품권</a>&nbsp;<span class="badge">new</span>
						</h5>
						공병 포인트로 상품 구매시 최대 10% 할인.
					</div>
				</div>
				<hr>
				<div class="media">
					<div class="media-left">
						<a href="#"><img class="media-object"
							src="../img/gscupon1.png" alt="11번가 쿠폰 이미지"></a>
					</div>
					<div class="media-body">
						<h5>
							<a href="#">GS25 5천원 모바일 상품권</a>&nbsp;<span class="badge">new</span>
						</h5>
						공병 포인트로 상품 구매시 최대 10% 할인.
					</div>
					<hr>
					<div class="media">
						<div class="media-left">
							<a href="#"><img class="media-object"
								src="../img/cucupon.png" alt="11번가 쿠폰 이미지"></a>
						</div>
						<div class="media-body">
							<h5>
								<a href="#">CU 5천원 모바일 상품권</a>&nbsp;<span class="badge">new</span>
							</h5>
							공병 포인트로 상품 구매시 최대 10% 할인.
						</div>
					</div>
				</div>
			</div>
			<div class="panel-body" style="width: 45%; display: inline-block;">
				<div class="media">
					<div class="media-left">
						<a href="/productlist?currentPage=1"><img class="media-object"
							src="../img/sevencupon.png" alt="11번가 쿠폰 이미지"></a>
					</div>
					<div class="media-body">
						<h5>
							<a href="#">세븐일레븐 1만원 모바일 상품권</a>&nbsp;<span class="badge">new</span>
						</h5>
						공병 포인트로 상품 구매시 최대 10% 할인.
					</div>
				</div>
				<hr>
				<div class="media">
					<div class="media-left">
						<a href="#"><img class="media-object" src="../img/gscupon.png"
							alt="11번가 쿠폰 이미지"></a>
					</div>
					<div class="media-body">
						<h5>
							<a href="#">GS25 1만원 모바일 상품권</a>&nbsp;<span class="badge">new</span>
						</h5>
						공병 포인트로 상품 구매시 최대 10% 할인.
					</div>
					<hr>
					<div class="media">
						<div class="media-left">
							<a href="#"><img class="media-object"
								src="../img/cucupon1.png" alt="11번가 쿠폰 이미지"></a>
						</div>
						<div class="media-body">
							<h5>
								<a href="#">CU 1만원 모바일 상품권</a>&nbsp;<span class="badge">new</span>
							</h5>
							공병 포인트로 상품 구매시 최대 10% 할인.
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
<script>
var slideCount = $('#slider ul li').length;
var slideWidth = $('#slider ul li').width();
var slideHeight = $('#slider ul li').height();
var sliderUlWidth = slideCount * slideWidth;

$('#slider').css({ width: slideWidth, height: slideHeight });

$('#slider ul').css({ width: sliderUlWidth, marginLeft: - slideWidth });

$('#slider ul li:last-child').prependTo('#slider ul');

function moveLeft() {
    $('#slider ul').animate({
        left: + slideWidth
    }, 200, function () {
        $('#slider ul li:last-child').prependTo('#slider ul');
        $('#slider ul').css('left', '');
    });
};

function moveRight() {
    $('#slider ul').animate({
        left: - slideWidth
    }, 200, function () {
        $('#slider ul li:first-child').appendTo('#slider ul');
        $('#slider ul').css('left', '');
    });
};

$('a.control_prev').click(function () {
    moveLeft();
});

$('a.control_next').click(function () {
    moveRight();
});
</script>
</body>
</html>