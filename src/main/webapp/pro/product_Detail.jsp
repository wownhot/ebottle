<%@page import="com.kosmo.project.ProductVO"%>
<%@page import="com.kosmo.project.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<!--카카오 sdk -->
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>


<script src="../js/bootstrap.js"></script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<title>EBProject</title>
<style type="text/css">

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
		} else if (prm == "buy") {
			document.ff.action = "/buyproduct";
			document.ff.submit();

		} else if (prm == "cart") {
			document.ff.action = "/cartinsert";
			document.ff.submit();
		}else if(prm=='grade'){
			 document.f.action = "/gradelist";
			 document.f.method="get";
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
	<%
		ProductVO po = new ProductVO();
		po = (ProductVO) session.getAttribute("PRODUCT_DETAIL");
		String des = po.getDesc().replaceAll("\n", "<br>");
		int price=po.getPrice();
		
		if(vo!=null){
			double rate = Double.parseDouble(session.getAttribute("DISCOUNT").toString());
			price = (int)(price*rate);
		}
			/* price = (int)(po.getPrice()*RatingSale.getDiscount(vo.getAccount())); */
	%>
	<div class="container">
		<div class="jumbotron" style="background-color: white">
			<form name="ff" method="post">
				<input type="hidden" name="pseq" value="<%=po.getPseq()%>">
				<div class="card">
					<div class="container-fliud">
						<div class="wrapper row">
							<div class="preview col-md-6">

								<div class="preview-pic tab-content">
									<div class="tab-pane active" id="pic-1">
										<img style="width:100%;height:auto"src="<%=po.getUrl()%>" />
									</div>
								</div>

							</div>
							<div class="details col-md-6">
								<div class="panel panel-default text-center">
									<h2>
										<%=po.getPname()%>
									</h2>
									<input type="hidden" name="pname" value="<%=po.getPname()%>">
									<hr>
									<h3><img src="../img/star.png">97점</h3>
									<hr>
									<input type="hidden" name="price" value="<%=price%>">
									<h2>
									<span style="background-image: url('../img/hhh.png'); background-size: cover;"><%=po.getPrice() %>Point</span>&nbsp;&nbsp;
									<font color="#FF24A3"><span style="text-align:right;">&nbsp;<%=price%>Point</span></font>
									</h2>
									<hr>
									<h3>
										<b3>
										<h3 style="text-align: left;margin-left: 5%"><%=des%></h3>
									</h3>
									<hr>
									<h3>
									 재고량 &nbsp; &nbsp;<%=po.getStock()%>
									</h3>
								</div>

								<%
									if (vo != null) {
								%>
								<div class="text-center">
									<button class="add-to-cart btn btn-default" type="button"
										onclick="goPage('buy')" style="width:100%">
										<h3><span class="glyphicon glyphicon-gift"></span>상품 구매</h3>
									</button>
								</div>
								<div class="text-center">
									<button class="add-to-cart btn btn-default" type="button"
										onclick="goPage('cart')" style="width:100%">
										<h3><span class="glyphicon glyphicon-shopping-cart"></span>장바구니 담기</h3>
									</button>
								</div>
								
								
								<div class="text-center">
								
									<!-- 카카오톡링크 버튼 -->
									<a type="button" class="add-to-cart btn btn-default"
										href="javascript:test();" style="width:100%"><h3> <img
										src="//developers.kakao.com/assets/img/about/logos/kakaolink/kakaolink_btn_medium.png" style="width:30px"/>
										카카오톡으로 선물하기</h3>
									</a>
								</div>
								
								<!-- 카카오톡링크 버튼 스크립트 -->
								<script type='text/javascript'>
									/* 사용할 앱 javascript 키 설정 */
									Kakao.init('1c51e6c9099ebf37e2a77dccf1b8a851');
									
									function test(){
										var bar= new Date().getTime(); 
										var pseq = <%=po.getPseq()%>;
										var user = '<%=vo.getId()%>';
										var price1 = <%=price%>;
										$.ajax({
													type : "POST",
													data : {
														usersid : user,
														barcode : bar,
														ps : pseq,
														price : price1
													},
													url : "/kakaogift",
													success : function(data) {

														if (data == '0') {
															alert('포인트가 부족합니다.');
														} else if(data =='-1'){
															alert('상품 재고량이 부족합니다.');
														}else {
															//카카오링크 메시지 생성(모바일에서만 동작)
															/* 
															Kakao.Link.sendTalkLink({
																
																//메시지 내용
																label : '기프티콘 메시지 입니다.',
																//링크메시지 이미지
																image : {
																	src : 'http://kr.seaicons.com/wp-content/uploads/2015/06/gift-icon.png',
																	width : '300',
																	height : '200'
																},
																//버튼 url
																webButton : {
																	text : '기프티콘보기',
																	url :'http://ebottles.cafe24.com/giftpage?barcode='
																		+ data // 앱 설정의 웹 플랫폼에 등록한 도메인의 URL
																		/* 'http://192.168.0.41:100/giftpage?barcode=' */
																//}
															//});
															// */
															Kakao.Link.sendCustom({
															      templateId: 6303,
															      templateArgs: {
															        'url':'giftpage?barcode='
																		+ data
															      }
															    });
														}
													}
												});
									}
								</script>
								<%
									}
								%>
							</div>
						</div>
					</div>
				</div>
			</form>





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


</body>
</html>