<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "model.userDB" %>
    <% userDB user = (userDB)session.getAttribute("loginUser"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>管理画面 | goumao</title>
</head>
<body class="admin_body">
	<jsp:include page="/header4.jsp"/>
	
	<div>
		<%= user.getUSER() %>でログイン中
	</div>

	<div class="admin_border">
		<h1>管理画面</h1>
	</div>
	
	<div class="admin_img">
		<img src="css/images/ロゴ/admin_img.png">
	</div>
	
	<nav class="admin_nav">	
		<div class="admin_a">
			<a class="a_button" href = "Upload">商品追加</a>
			<a class="a_button" href = "AdminSeaechServlet">商品変更、削除</a>
			<a class = "a_button" href = "LogoutServlet">ログアウト</a>
		</div>
	</nav>

<jsp:include page="/footer4.jsp"/>
</body>
</html>