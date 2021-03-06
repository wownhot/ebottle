<%@page import="com.kosmo.project.CartVO"%>
<%@page import="com.kosmo.project.PointVO"%>
<%@page import="com.kosmo.project.UserVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="../css/bootstrap.css">
<link rel="stylesheet" href="../css/modal.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width", initial-scale="1">
	<title>EBProject</title>
	<style type="text/css">
		.jumbotron{
			background-image: url('../img/bottleback.jpg');
			background-size: cover;
			color: black;
		}
	</style>
<script type="text/javascript">
$(document).ready(function(){
    //최상단 체크박스 클릭
    $("input[name=checkAll]").click(function(){
        //클릭되었으면
        if($("input[name=checkAll]").prop("checked")){
            //input태그의 name이 cSeq인 태그들을 찾아서 checked옵션을 true로 정의
            $("input[name=cSeq]").prop("checked",true);
            //클릭이 안되있으면
        }else{
            //input태그의 name이 cSeq인 태그들을 찾아서 checked옵션을 false로 정의
            $("input[name=cSeq]").prop("checked",false);
        }
    })
})

function productBuy(){
	document.f2.action = "/buycart";
	document.f2.method = "get";
	document.f2.submit();
}
function cartDelete() {
	document.f2.action = "/cartdelete"; 
	document.f2.method = "get";
	document.f2.submit();
}

function cartUpdate(prm) {
	document.getElementsByName("index")[0].value = prm;
	document.f2.action = "/cartUpdate";
	document.f2.method = "get";
	document.f2.submit();
}

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
	 }else if(prm=='grade'){
		 document.f.action = "/gradelist";
		 document.f.method="get";
		 document.f.submit();
	 }
}

 
 </script>
</head>
<body>
<%UserVO vo = null; 
try{
	 vo = (UserVO)session.getAttribute("USER");
}catch(Exception e){
%>
location.href="/pro/user_main.jsp";
<%	
} 
%>
<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
				data-target="#bs-example-navbar-collapse-1" aria-expanded="false" style="width: auto;">
				<span class="sr-only"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/pro/user_main.jsp">EBproject</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li ><a href="#">소개<span class="sr-only"></span></a></li>
					<li><a href="/productlist?currentPage=1">포인트상품</a></li>
					<li><a href="/boardlist?currentPage=1">게시판</a></li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">포인트<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/pro/point_standard.jsp">적립기준 및 방법</a></li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">Q&A<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="#">1:1 질문</a></li>
							<li><a href="#">자주묻는 질문</a></li>	
						</ul>
					</li>
					<% 
						if(vo!=null){
					%>
					<li class="dropdown active">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">마이페이지<span class="caret"></span></a>
					<%	
						if(vo.getAccount().equals("admin")){
					%>
						<ul class="dropdown-menu">
							<li><a href="#" onClick="goPage('admin')">회원정보수정</a></li>
							<li><a href="#" onClick="goPage('grade')">회원등급</a></li>	
						</ul>
					<%
						}else{
					%>
						
						<ul class="dropdown-menu">
							<li><a href="#" onClick="goPage('update')">내정보수정</a></li>
							<li><a href="/gradeuserpoint?currentPage=1">회원등급</a></li>	
							<li><a href="#" onClick="goPage('point')">내포인트 보기</a></li>
							<li><a href="/cartlist?currentPage=1">장바구니</a></li>	
							<li><a href="/buylist?currentPage=1">구매목록</a></li>
							<li><a href="/giftlist?currentPage=1">선물함</a></li>				
						</ul>
					<%
						} 
					%>
						</li>
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
					<% if(vo!=null){
					%>
						<li><a href="#"	onclick="goPage('logout')">로그아웃</a></li>
					<%
 						}else{
 					%>
 							<li><a href="/pro/user_register.jsp"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
							<li><a href="/pro/user_login.jsp"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
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
		<div style="padding-right:20px;padding-left:20px;background-color:white">
		<H3 style="text-align: center;">장바구니</H3><hr>
<%ArrayList<CartVO> list = (ArrayList<CartVO>)request.getAttribute("ALL_LIST"); %>

	<form name="f2">
	<div class="table-responsive">
		<table class = "table table-striped table-hover">
		<colgroup>
		<col style="width: 5%">
		<col style="width: 50%">
		<col style="width: 20%">
		<col style="width: 25%">
		</colgroup>
		<thead>
			<tr>
				<th><center><input type="checkbox" name="checkAll"></center></th>
				<th><center>상품</center></th>
				<th><center>수량</center></th>
				<th><center>가격</center></th>
			</tr>
		</thead>
		<tbody>
<%for(int i=0; i<list.size(); i++) { %>
			<input type="hidden" name="cSeqForCartUpdate<%=i %>" value="<%=list.get(i).getCart_seq()%>">
			<input type="hidden" name="index">
			<tr>
				<td><center><input type="checkbox" value="<%=list.get(i).getCart_seq()%>" name="cSeq" ></center></td>
				<td><center>
					<a href="/productdetail?pseq=<%=list.get(i).getProduct_seq()%>"><%=list.get(i).getProduct_name()%>&nbsp;&nbsp;
						<img width="100px" height="100px" alt="" src="<%=list.get(i).getProduct_url()%>">
					</a></center>
				</td>
				<td><center><input type="number" value="<%=list.get(i).getCart_quantity() %>" min="1" name="cQuantity<%=i %>" style="width: 70%">
				<input type="button" onclick="cartUpdate('<%=i %>');" value="수정"></center></td>
				<td><center><%=list.get(i).getProduct_price() * list.get(i).getCart_quantity()%></center></td>
			</tr>
<%} %>
		</tbody>
		</table>
		</div>
		<br>
 	<div class="text-center">
        <ul class="pagination">
         <%=request.getAttribute("PAGING") %>
        </ul>
      </div>
   		<br>
   		<center>
		<button onClick="productBuy();">구매하기</button>
		<button onClick="cartDelete();">삭제하기</button>
		</center>
	</form>
    </div>
 
		<!-- 
			<h1 class="text-center">EBProject를 소개 합니다.</h1>
			<p class="text-center">공병을 포인트로 교환하여 쇼핑하기</p>
			<p class="text-center"><a class="btn btn-primary btn-lg" href="#" role="button">포인트 쇼핑하기</a></p> -->
		</div>
	</div>
	<footer style="background-color: #000000; color: #ffffff">
		<div class="container">
			<br>
			<div class="row">
				<div class="col-sm-2" style="text-align: center;"><h5>Copyright &copy; 2017</h5><h5>ProjectGroup Team EB</h5></div>
				<div class="col-sm-4"><h4>그룹소개</h4><p>저희는 Kosmo교육원에서 EBProject를 개발한 김기택, 정재현, 오원택, 정우근, 신준태, 이세현 입니다.</div>
				<div class="col-sm-2"><h4 style="text-align: center;">내비게이션</h4>
					<div class="list-group">
						<a href="#" class="list-group-item">소개</a>
						<a href="#" class="list-group-item">포인트상품</a>
						<a href="#" class="list-group-item">포인트</a>
						<a href="#" class="list-group-item">Q&A</a>
					</div>				
				</div>
				<div class="col-sm-2"><h4 style="text-align: center;">SNS</h4>
				<div class="list-group">
						<a href="#" class="list-group-item">KAKAOTALK</a>
						<a href="#" class="list-group-item">INSTAGRAM</a>
						<a href="#" class="list-group-item">FACEBOOK</a>
						<a href="#" class="list-group-item">LINE</a>
					</div>
				</div>
				<div class="col-sm-2"><h4 style="text-align: center;"><span class="glyphicon glyphicon-ok"></span>&nbsp;by Team EB</h4></div>				
			</div>
		</div>
	</footer>

</body>
</html>