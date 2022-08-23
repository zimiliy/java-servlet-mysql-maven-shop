<%--
  Created by IntelliJ IDEA.
  User: KNuser31
  Date: 2022/8/2
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ page import="model.userDB" %>
<%userDB user=(userDB)session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/style3.css">
<link href="https://use.fontawesome.com/releases/v6.1.2/css/all.css" rel="stylesheet">
<title>アカウント情報編集 | GouMao</title>

</head>
<body>
<jsp:include page="/header1.jsp"></jsp:include>
<div id="b-inner">
<div class="inner">
	<h2 class="cart-h2">アカウント情報変更</h2>

<form method="post" action="UpdateServlet" class="cart-area3">
	<ul id="creditCard_area" class="active">
	    <li class="Form-Item-ue"><p class="Form-Item-Label">ユーザー名</p><input type="text" name="username" value="<%=user.getUSER() %>" class="Form-Item-Input-ue"></li><br>
	    <li class="Form-Item-ue"><p class="Form-Item-Label">新しいパスワード</p><input type="password" name="password" class="Form-Item-Input-ue"required></li><br>
	    <li class="Form-Item-ue"><p class="Form-Item-Label">新しいパスワード(確認)</p><input type="password" name="password2" class="Form-Item-Input-ue"required></li><br>
	    <li class="Form-Item-ue"><p class="Form-Item-Label">メールアドレス</p><input type="text" name="mail" value="<%=user.getEMAIL() %>" class="Form-Item-Input-ue"></li><br>
	    <li class="Form-Item-ue"><p class="Form-Item-Label">住所</p><input type="text" name="address" value="<%=user.getADDRESS() %>" class="Form-Item-Input-ue"></li>
	    <input type="submit" value="変 更 確 定" class="edit-btn">
	</ul>

</form>

</div>
</div>
<jsp:include page="/footer1.jsp"></jsp:include>
</body>
</html>
