
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
<!-- <link rel="stylesheet" href="../css/product.css"> -->
<link rel="stylesheet" href="../css/productlist.css">
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
	
	function goSearch(){
		document.serchf.method="post";
		document.serchf.submit();
	}
	
	$(document).ready(function(e){
	    $('.search-panel .dropdown-menu').find('a').click(function(e) {
			e.preventDefault();
			var param = $(this).attr("href").replace("#","");
			var concept = $(this).text();
			$('.search-panel span#search_concept').text(concept);
			$('.input-group #search_param').val(param);
			$('#sb').removeAttr('disabled');
		});
	});
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
		<div class="container-fluid">
			<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" 
				data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
				<span class="sr-only"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/pro/user_main.jsp">EBproject</a>
			</div>
			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">소개<span class="sr-only"></span></a></li>
					<%if(vo!=null){
						if(vo.getAccount().equals("admin"))
						{
					%>	<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">포인트상품<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/productlist?currentPage=1">상품수정하기</a></li> 
							<li><a href="/pro/product_add.jsp">상품등록하기</a></li>
							<li><a href="product_addAll">상품일괄등록하기</a></li>	
						</ul>
					</li>
					<% }else{ %>
					
					<li><a href="/productlist?currentPage=1">포인트상품</a></li><% 
					}
						}else{%>
					
					
											
						<li><a href="/productlist?currentPage=1">포인트상품</a></li>
						<%} %>
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
					<li class="dropdown">
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
			<div id="container">
			
			<%-- <div
				class="container_inner default_template_holder clearfix page_container_inner">
				<div class="vc_row wpb_row section vc_row-fluid"
					style="padding-top: 0px; text-align: left;">
					<div class=" full_section_inner clearfix">
						<div class="vc_col-sm-12 wpb_column vc_column_container">
							<div class="wpb_wrapper">
								<div
									class="projects_holder_outer v3 portfolio_with_space portfolio_standard ">
									<div class="filter_outer">
										<div class="filter_holder"></div>
									</div>
									<div class="projects_holder clearfix v3 standard">
										<%
											ArrayList<ProductVO> pList = new ArrayList<ProductVO>();

											pList = (ArrayList<ProductVO>) request.getAttribute("PRODUCT_LIST");
											for (int i = 0; i < pList.size(); i += 3) {
										%>
										<%
											for (int j = i; j < i + 3; j++) {

													if (j > pList.size() - 1) {
														break;
													}
										%>
										<article
											class="mix portfolio_category_17 portfolio_category_5  mix_all"
											style="display: inline-block; opacity: 1;">
										<div class="image_holder">
											<a class="portfolio_link_for_touch"
												href="/ProductDetailServlet?pseq=<%=pList.get(j).getPseq()%>"
												target="_self"> <span class="image"> <img
													width="1100" height="825" src="<%=pList.get(j).getUrl()%>"
													class="attachment-full wp-post-image"
													alt="qode interactive strata">
											</span>
											</a> <span class="text_holder"> <span class="text_outer">
													<span class="text_inner"> <span
														class="feature_holder"> <span
															class="feature_holder_icons">
																<a	class="preview qbutton small white"
																href="productupdate?pseq=<%=pList.get(j).getPseq()%>"
																target="_self">update</a>
														</span>
													</span>
												</span>
											</span>
											</span>
										</div>
										
										
										
										<div class="portfolio_description ">
											<h5 class="portfolio_title">
												<a
													href="/productupdate?pseq=<%=pList.get(j).getPseq()%>"
													target="_self"><%=pList.get(j).getPrice()%>Point</a>
											</h5>
											<span class="project_category"><%=pList.get(j).getPname()%></span>
										</div>
										</article>
										<a href="/ProductDetailServlet?pseq=<%=pList.get(j).getPseq()%>" ><img src="<%=pList.get(j).getUrl()%>"></a>

										<%
											}
										%>

										<%
											}
										%>
										<div class="filler"></div>
										<div class="filler"></div>
										<div class="filler"></div>
									</div>
								</div>
								<div class="separator  transparent center  "
									style="margin-top: 20px; margin-bottom: 20px;"></div>
								<div class="text-center">
									<ul class="pagination">
										<%=request.getAttribute("PRODUCT_PAGING")%>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>
		 --%>
		 
		 <div class="row">

				<%
				ArrayList<ProductVO> pList = new ArrayList<ProductVO>();
				pList = (ArrayList<ProductVO>) request.getAttribute("PRODUCT_LIST");
				for (int i = 0; i < pList.size();i++) {
				%>
				<div class="col-md-3 col-sm-6">
					<span class="thumbnail"> <img
						src="<%=pList.get(i).getUrl()%>"
						alt="...">
						<h4><%=pList.get(i).getPname()%></h4>
						<hr class="line">
						<div class="row">
							<div class="col-md-6 col-sm-6">
								<p class="price"><%=pList.get(i).getPrice()%> P</p>
							</div>
							<div class="col-md-6 col-sm-6">
								<a href="/productupdate?pseq=<%=pList.get(i).getPseq()%>">
									<button class="btn btn-info right">상품 수정</button>
								</a>
							</div>

						</div>
					</span>
				</div>
				<% 
				}
				%>
				<!-- END PRODUCTS -->
			</div>

			<div class="text-center">
				<ul class="pagination">
					<%=request.getAttribute("PRODUCT_PAGING")%>
				</ul>
			</div>
			
			<div >
			<div class="row">    
			<div class="col-xs-8 col-xs-offset-2">
            <form action="/productlist" class="input-group" name="serchf">
            	
                <div class="input-group-btn search-panel">
                    <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="margin:0">
                        <span id="search_concept">선택</span> <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" role="menu">
                      <li><a href="#title">상품명</a></li>
                      <li><a href="#content">내용</a></li>
                    </ul>
                </div>
                 
                <input type="hidden" name="search_param" value="all" id="search_param">
                <input type="hidden" name="currentPage" value="1">         
                <input type="text" class="form-control" name="sk" id="x" placeholder="검색어를 입력하세요...">
                <span class="input-group-btn" style="padding:0px;height:100%">
                    <button onclick="goSearch()" class="btn btn-default" style="margin:0;" id="sb" type="button"><span class="glyphicon glyphicon-search"></span></button>
                </span>
            </form>
        </div>
        </div>
        </div>
 <!--  
 <form class="form-signin" action="/productadd" method="post" enctype="multipart/form-data">
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h3 class="text-center">
                        상품 등록</h3>
                    <form class="form form-signup" role="form">
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-pencil"></span>
                            </span>
                            <input type="text" class="form-control" placeholder="상품명을 입력하세요" name="pname" required/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-usd"></span></span>
                            <input type="Text" class="form-control" placeholder="상품가격을 입력하세요" name="price" required/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="input-group">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-list-alt"></span></span>
                            <input type="Text" class="form-control" placeholder="상품재고를 입력하세요" name="stock" required/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label><b>상품설명</b></label><br>
     					<textarea rows="5" cols="" style="width:100%"name="desc" placeholder="상품설명을 입력하세요" required></textarea>
                    </div>
                    <div class="form-group">
                        
                            <input class="file" id="input-1" type="file" placeholder="상품이미지를 등록하세요" name="img" required>
                        
                    </div>
                    

                </div>
                <input type="submit" class="btn btn-sm btn-primary btn-block" value="등 록">
               </form> -->
            </div>
        </div>
    </div>
</div>
</form>

  </div>
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