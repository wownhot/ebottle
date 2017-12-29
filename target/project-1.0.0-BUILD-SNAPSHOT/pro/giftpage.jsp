<%@page import="com.kosmo.project.GiftVO"%>
<%@page import="com.kosmo.project.GiftDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기프티콘페이지</title>
<style type="text/css">
@import url(//fonts.googleapis.com/earlyaccess/jejugothic.css);
	.gifttext{
		text-align:center;
		font-size:6vw;
		font-family: 'Jeju Gothic', sans-serif;
	}
	
	.form{
		margin-top:5%;
		margin-left:5%;
		width:90%;
	}
	table{
		border-collapse: collapse;
		border-spacing: 0;
		font-family: 'Jeju Gothic', sans-serif;
	}
	table td{
		padding:2px;
		border:1px solid silver;	
	}
	.left{
		float:left;
	}
	.right{
		float:right;
	}
</style>
</head>
<body style="background-color:white">
	<%
		GiftVO vo = new GiftVO();
		vo = (GiftVO)request.getAttribute("giftvo");
		
	%>
	<div style="height:auto;width:70%;margin-left: 10%;margin-right: 10%;margin-top:20%;
	padding:5% 5%; background-color:#d73423">
		<h3 class="gifttext"style="margin-top:0;margin-bottom:0;color:white;">
		<img src="../img/giftbox.png" style="width:10%;vertical-align:top"/>
		선물하기</h3>
	<div style="padding-bottom:5%;background:white;border-radius: 5px;">
		<p style="margin-bottom:0"><img class="form" alt="상품" src="<%=vo.getProductimg()%>"></p>
		<p class="gifttext"><%=vo.getProductname() %></p>
		<p style="margin:0"><img style="margin:0 5%;width:90%;" alt="바코드" src="<%=vo.getBarcodeimg()%>"></p>
		<p class="gifttext"style="margin-top:0;"><%=vo.getBarcode()%></p>
		<hr/>
		<table class="form">
			<tr>
				<td><div class="left">선물날짜:</div> <div class="right"><%=vo.getGiftdate().split(" ")[0]%></div></td>
			</tr>
			<tr>
				<td><div class="left">주문번호:</div> <div class="right"><%=vo.getBarcode()%></div></td>
			</tr>
			<tr>
				<td><div class="left">교환처:</div> <div class="right">제휴편의점</div></td>
			</tr>
		</table>
		<p class="gifttext" style="margin-bottom:0;font-size:2.5vw;color:silver">1544-0000</p>
		<p class="gifttext" style="margin:0;font-size:2.5vw;color:silver">EBottle 고객센터</p>
	</div>
	</div>
</body>
</html>