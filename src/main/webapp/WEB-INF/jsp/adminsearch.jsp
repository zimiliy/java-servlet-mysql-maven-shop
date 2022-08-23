<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品の変更・削除・検索ページ | GouMao</title>
</head>
<body class="addItem_body">
<jsp:include page="/header3.jsp"></jsp:include>

<h1 class="addI_h1">商品の変更・削除・検索ページ</h1>

<div class="adminSearch_item">
	<form class="adminS_form" action="AdminSeaechServlet" method="post" class="prd_search_box flex">
		<input type="text" name="keyword" class="prd_search_keyword" placeholder="商品名、キーワードを入力" />
		<input type="submit" class="prd_search_btn font-jp2" value="検索" />
		<p class="adminS_p">
			<input type="radio" name="mainId" value="A"/>犬
			<input type="radio" name="mainId" value="B"/>猫
		</p>
	</form>
</div>

<div class="as_footer">
<jsp:include page="/footer3.jsp"></jsp:include>
</div>
</body>
</html>