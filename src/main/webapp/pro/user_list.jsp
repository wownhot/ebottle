<%@page import="com.kosmo.project.UserVO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="../css/bootstrap.css">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width", initial-scale="1">
	<title>EBProject</title>
	<style type="text/css">

	</style>
	


<script type="text/javascript">
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
var input_modal = document.getElementById('id01');
window.onclick = function(event) {
	    if (event.target == input_modal) {
	        input_modal.style.display = "none";
	    }
}
 function update(prm){
	 location.href="/userdetail?seq="+prm;
 }
 function del(){
	 document.f2.action = "/userdelete";
	 document.f2.submit();
 }
 
 function check(prm) {
	 var form;
	 if(prm=='input'){
		 form = document.input; 
		 if(form.psw.value==""||form.name.value==""||form.email.value==""||form.id.value==""||form.repsw.value==""){
		    	alert("정보를 정확히 입력해 주세요.");
		    }else if( form.repsw.value != form.psw.value) {
		        alert("비밀번호가 일치하지 않습니다.");
		        form.psw.focus();
		        form.psw.clear();
		        form.repsw.value();
		    }else{
		    	form.submit();
		    }
	 }
}
 $(document).ready(function(e){
	    $('.search-panel .dropdown-menu').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-panel span#search_concept').text(concept);
			$('.input-group #search_param').val(param);
		});
	    
	});
 function goSearch(){
		document.serchf.method="post";
		document.serchf.submit();
	}
 
 </script>
</head>
<body style="background-color:#eee">
<%UserVO vo = null; 
try{
	 vo = (UserVO)session.getAttribute("USER");
}catch(Exception e){

} %>
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
		<div style="padding-right:20px;padding-left:20px;background-color:white">
      <p style=";background-repeat:no-repeat;background-size:120px 120px;height:120px;text-align: center;padding-top:50px;font-size: 30px">회원관리</p>
      <p><input type="button" class="btn btn-success" onClick="del()" value="회원삭제" style="color:white;background-color:black;border:none">
      <button class="btn btn-warning" data-toggle="modal" data-target="#myModal" style="color:white;background-color:black;border:none;margin-top:0px;width:auto;">회원추가</button>
      </p>
      <hr>
      <form name="f2" method="post">
      <div class="table-responsive">
      <table class = "table table-striped table-hover">
        <thead>
          <tr>
          	<th>		</th>
            <th>회원번호</th>
            <th>이름</th>
            <th>아이디</th>
            <th>이메일</th>
            <th>등급</th>
          </tr>
        </thead>
        <tbody>
        <%
ArrayList<UserVO> list = new ArrayList<UserVO>();
list = (ArrayList<UserVO>)request.getAttribute("LIST");

for(int i=0;i<list.size();i++){
%>
<tr>
	<td><input type="checkbox" name="check_user" value="<%=list.get(i).getSeq()%>"></td>
	<td onClick="update(<%=list.get(i).getSeq()%>)"><%=list.get(i).getSeq()%></td>
	<td onClick="update(<%=list.get(i).getSeq()%>)"><%=list.get(i).getName()%></td>
	<td onClick="update(<%=list.get(i).getSeq()%>)"><%=list.get(i).getId()%></td>
	<td onClick="update(<%=list.get(i).getSeq()%>)"><%=list.get(i).getEmail()%></td>
	<td onClick="update(<%=list.get(i).getSeq()%>)"><%=list.get(i).getAccount()%></td>
</tr>
<%
}
%>
        </tbody> 
        </table>
        </div>
 </form>
 
        <hr>
      <br/>
      <div class="text-center">
        <ul class="pagination">
         <%=request.getAttribute("PAGING") %>
        </ul>
      </div>
      
      <div >
			<div class="row">    
			<div class="col-xs-8 col-xs-offset-2">
            <form action="/userlist" class="input-group" name="serchf">
            	
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="margin:0">
                        <span id="search_concept">선택</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#name">회원명</a></li>
                      <li><a href="#id">아이디</a></li>
                    </ul>
                </div>
                 
                <input type="hidden" name="search_param" value="all" id="search_param">
                <input type="hidden" name="currentPage" value="1">         
                <input type="text" class="form-control" name="sk" id="x" placeholder="회원을 입력하세요...">
                <span class="input-group-btn" style="padding:0px;height:100%">
                    <button onclick="goSearch()" class="btn btn-default" style="margin:0;" id="sb" type="button"><span class="glyphicon glyphicon-search"></span></button>
                </span>
            </form>
        </div>
        </div>
        </div>
        <br>
    </div>
 
 	
		<!-- 
			<h1 class="text-center">EBProject를 소개 합니다.</h1>
			<p class="text-center">공병을 포인트로 교환하여 쇼핑하기</p>
			<p class="text-center"><a class="btn btn-primary btn-lg" href="#" role="button">포인트 쇼핑하기</a></p> -->
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




 <div id="myModal" class="modal">
 <span onclick="document.getElementById('myModal').style.display='none'" class="close" title="Close Modal">×</span>
  
  
 <form class="form-signin" name="input" action="/userinsert" method="post">
<div class="container">
    <div>
        <div style="width:800px; padding-left: 300px; padding-top:110px">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h3 class="text-center">
                        회원 추가</h3>
                    <form class="form form-signup" role="form">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-pencil"></span>
                            </span>
                            <input type="text" name="name" class="form-control" placeholder="이름을 입력하세요" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span></span>
                            <input type="Text" placeholder="이메일 양식에 맞게 작성해주세요  :) 예 : xxx@naver.com" name="email" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-star"></span></span>
                            <input type="Text" placeholder="아이디를 입력해주세요  " name="id" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                            <input type="Text" placeholder="등급을 입력해주세요  " name="account" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                            <input type="Text" placeholder="비밀번호를 입력해주세요  " name="psw" class="form-control"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                            <input type="Text" placeholder="비밀번호를 한번더 입력해주세요  " name="repsw" class="form-control"/>
                        </div>
                    </div>
                <button type="button" class="btn btn-sm btn-primary btn-block" onclick="check('input')">회원추가</button>
                <button type="button" data-dismiss="modal" class="btn btn-sm btn-danger btn-block">취소</button>
        		
            </div>
        </div>
    </div>
</div>
</form>
  
</div>
 
</body>
</html>