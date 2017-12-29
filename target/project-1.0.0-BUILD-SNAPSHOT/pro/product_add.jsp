<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="../css/bootstrap.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="../js/bootstrap.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>상품 등록</title>
</head>
<body>
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
                <input type="button" class="btn btn-sm btn-danger btn-block" onclick="history.go(-1)" value="취소">
               </form>
            </div>
        </div>
    </div>
</div>
</form>

</body>
</html>