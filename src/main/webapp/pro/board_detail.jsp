
<%@page import="com.kosmo.project.BoardVO"%>
<%@page import="com.kosmo.project.ProductVO"%>
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
<link rel="stylesheet" href="../css/product.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width", initial-scale="1">
	<title>EBProject</title>
	<style type="text/css">
		

	</style>
	
<script>
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
	 }else if(prm=='login'){
		 form = document.login;
		 if(form.pw.value==""||form.id.value==""){
		   	alert("정보를 정확히 입력해 주세요.");
		 }else{
		   	form.submit();
		 }
	 }   
}
	// Get the modal
	var input_modal = document.getElementById('id01');
	var login_modal = document.getElementById('id02');
	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == input_modal) {
	        input_modal.style.display = "none";
	    }
	    if (event.target == login_modal) {
	        login_modal.style.display = "none";
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
			<div id="container">
			

	<%
		BoardVO bo = new BoardVO();
		bo = (BoardVO)session.getAttribute("BOARD_DETAIL");
		int bseq = bo.getBseq();
		String title = bo.getTitle();
		String uid = bo.getUserid();
		String contents = bo.getContents();
		String bdate = bo.getRegDate();
		bo.getRlist();
	%>			
  
 <form class="form-signin" action="/boardupdate?bseq=<%=bseq %>" name="hehe" method="post" enctype="multipart/form-data">
<div class="container">

    <div class="row">
        <div>
            <div class="panel panel-default" style="width:50%;margin-left: 25%; padding:2%">
                <div class="panel-body">
                    <h3 class="text-center">
                        </h3>
                    <form class="form form-signup" role="form">
                    <div class="form-group">
                        <div class="form-group">
                            <label><b><h2> <%=title %></h2></b></label><br>
                            <p style="text-align:right;font-size:15px"><%=bdate %></p>
                            <hr>
                           

                        </div>
                    </div>
                    <div class="input-group">
                        <label style="font-size:20px"><b>작성자 : <%=uid %>&nbsp;&nbsp;</b></label><br>

                    </div>					
                    
                    <div class="form-group">
                        <br>
     					<textarea rows="9" cols="" style="width:100%;"name="contents" required readonly><%=contents%></textarea>
                    </div>
                    <div class="form-group">
                        
                      	<label><b>파일다운로드</b></label><br>
                      	<a href="/boardfiledown?bseq=<%=bo.getBseq() %>&sname=<%=bo.getSysFname()%>&oname=<%=bo.getOrigFname()%>"><%=bo.getOrigFname() %></a>
                        
                    </div>
           <% if(vo!=null){
			if(vo.getId().equals(uid))
				{
	      %> 
               <input type="submit" class="btn btn-sm btn-primary btn-block" style="width:25%;margin-left:75%" value="게시글 수정">

                
           <%
			}}
           %>	    
           </form>              
	<div class="form-group" style="background-color:#FFFFD4;padding:10px;">
				   <label><b><h4>댓글</h4></b></label><br><hr>
				   
           <% if(vo!=null){
			
	      %> 					   
	    <form method="post" action="/replyinsert">
    	
    	<h3 style="margin-bottom:5px">댓글쓰기</h3>
		<input type="text" name="replyy" style="width:500px;font-size:15px"/>
		<input type="hidden" name="bseq" value="<%=bseq%>"/>
		<input type="submit" value="입력" class="btn btn-success btn-sm" style="margin-bottom:10px"/><hr>
		</form>
           <%
			}
           %>	
           		<%
		if(bo.getRlist().get(0).getUserid()!=null)
		{
			
			for(int i=0; i<bo.getRlist().size(); i++)
			{
		%>		&nbsp;작성일 : <input type="text" id="hhoho<%=bo.getRlist().get(i).getRseq()%>" value="<%=bo.getRlist().get(i).getRegDate()%>" readonly style="border: 0px;background-color:#FFFFD4">
				<p id="haha">
					<%=bo.getRlist().get(i).getUserid()%> :
				<input type="text" id="hoho<%=bo.getRlist().get(i).getRseq()%>" value="<%=bo.getRlist().get(i).getReply()%>" readonly style="border: 0px;background-color:#FFFFD4"/>
					<%
					  if(vo.getId().equals(bo.getRlist().get(i).getUserid())){
						  %>
						
						
						<input type="button" id="hhhoho<%=bo.getRlist().get(i).getRseq()%>" value="수정" onclick="replyupdate('#hoho<%=bo.getRlist().get(i).getRseq()%>')" style="border:0px; background-color:#FFFFD4; color: black;margin-left:43%;font-size:14px; ">
						<a href="/replydelete?bseq=<%=bseq %>&rseq=<%=bo.getRlist().get(i).getRseq()%>" style="font-size:14px">삭제</a>  
					
				
					<%	  
					  }
					%>
				</p>
				<hr style="border-top : 1px solid #bbb">
		<% 	}
			
		}
		%>
</form>
                    </div>
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
<script>
	$(":button").hover(
			function(){
				$(this).css("color","#1ABC9C");
			},
			function(){
				$(this).css("color","black");
			}
		);
var a=0;
function replyupdate(prm){ //onclick
	var inputed =$(prm).attr('id');//userid 
	var btn = "#hh"+inputed
	var date = "#h"+inputed  
	var recon = $(prm).val();

if(a==1){

	

	$.ajax({
		type:"POST",
		data:{        //userid넘기기
			rseq : inputed,
			 con : recon,
			
		},
		url : "/replyupdate", //controller
		success: function(data){
					$(date).val(data);
					$(prm).css("background-color","white");
					$(prm).css("border","solid 0px black");
					$(prm).attr("readonly" , true);
					$(btn).prop('value','수정');
		}
	});

	a=0;
	}else{
		$(prm).css("background-color","#B0F6AC");
		$(prm).attr("readonly" , false);
		$(prm).css("border","solid 1px black");

		$(btn).prop('value','수정완료');
		a=1;
	}
	
	
}	
</script>
</body>
</html>