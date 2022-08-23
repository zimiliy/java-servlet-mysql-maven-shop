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
<html>
<head>
<meta charset="UTF-8">
    <title>情報更新</title>

</head>
<body>
<form method="post" action="UpdateServlet">
    ユーザー名:<input type="text" name="username" value="<%=user.getUSER() %>"/><br/>

    新しいパスワード:<input type="password" name="password"/><br/>
    新しいパスワード(確認):<input type="password" name="password2"/><br/>
    メールアドレス:<input type="text" name="mail" value="<%=user.getEMAIL() %>"/><br/>
    住所:<input type="text" name="address" value="<%=user.getADDRESS() %>"/><br/>
    <input type="submit" value="更新"/>
    <input type="reset" value="リセット">
    <a href="login.jsp">ログインページへ</a>

</form>
</body>
</html>
