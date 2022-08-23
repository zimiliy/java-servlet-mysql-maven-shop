<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>商品追加確定 | GouMao</title>
</head>
<body class="addItem_body">
<jsp:include page="/header3.jsp"></jsp:include>

	<h1 class="addIOK_h1">商品追加完了!!</h1>
	<div class="addIOK_img">
		<img src="css/images/addIOKanimal.png">
	</div>
	<p class="addIOK_p">商品の追加が完了しました。</p>
	<div class="addIOK_a">
		<a href = "Upload">追加画面へ戻る</a>
		<a href = "adminservlet">管理画面へ戻る</a>
	</div>
	
<jsp:include page="/footer3.jsp"></jsp:include>
</body>
</html>