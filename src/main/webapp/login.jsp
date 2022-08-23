<%--
  Created by IntelliJ IDEA.
  User: KNuser31
  Date: 2022/8/2
  Time: 9:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String before=(String)request.getAttribute("before"); %>
<html>
<head>
	<link href="https://use.fontawesome.com/releases/v6.1.2/css/all.css" rel="stylesheet">

    <title>ログイン | GouMao</title>
</head>
<body>
<div class="yohaku"></div>

<div class="overlay">

<!-- ログインフォーム -->
<form method="post">

 <!-- con = フォーム内のアイテムのコンテナ-->
 <div class="con">
 
	<!-- 　　ここからヘッダーのコンテナ　　 -->
	<header class="head-form">
    	<h2 class="logologo"><img src="css/images/Logowanko.png" alt="logo"></h2>
    		<!-- welcome message -->
    		<p class="text">ユーザー名とパスワードを入力してGouMaoにログイン！</p>
	</header>
	<!-- 　　ここまでヘッダーのコンテナ　　 -->
	
	
	<div class="field-set">
		<!--　ユーザーネーム -->
		<div class="taisyo">
		<div class="input-item">
         	<i class="fa fa-user-circle"></i>　ユーザー名
        </div>
    	<!-- ユーザーネーム入力 -->
		<input  class="form-input" id="txt-input" type="text" placeholder="@UserName" name="username" required/>
		<br>
		</div>
		<div class="taisyo">
		<!-- パスワード -->
    	<div class="input-item">
    		<i class="fa fa-key"></i>　パスワード
        </div>
    	<!-- パスワード入力-->
		<input class="form-input" id="pwd" type="password" placeholder="Password" name="password" required/>
		<!-- パスワードの目 -->
    	<div>
        	<i class="fa fa-eye" aria-hidden="true"  type="button" id="eye"></i>
    	</div>
    	<br>
		 </div>
		<input type="hidden" name="before" value="<%=before %>">
		<input class="log-in" type="submit" formaction="LoginServlet" value="ログイン"/>
	</div>
	 
	 
	<!--     ここからその他のボタン     -->
	<div class="other">	
		<!--   リセットボタン   -->
		<input class="btn submits frgt-pass" type="reset" value="リセット">
		<!--   新規登録のリンク   -->
		<a href = "signUp.jsp">新規登録
			<!--   新規登録のアイコン  -->
			<i class="fa fa-user-plus" aria-hidden="true"></i>
		</a>
	</div>
	<!--     ここまでその他のボタン     -->


<!--
<form>
	<button class="btn submits sign-up" formaction="signUp.jsp">新規登録 
    	<i class="fa fa-user-plus" aria-hidden="true"></i>
	</button> 
</form>
 -->


 </div>
 <!--   ここまでコンテナ  -->
</form>

<!--  フッター -->
<div class="login_footer">
<jsp:include page="/footer5.jsp"/>
</div>

</div>
<script>
function show() {
    var p = document.getElementById('pwd');
    p.setAttribute('type', 'text');
}

function hide() {
    var p = document.getElementById('pwd');
    p.setAttribute('type', 'password');
}

var pwShown = 0;

document.getElementById("eye").addEventListener("click", function () {
    if (pwShown == 0) {
        pwShown = 1;
        show();
    } else {
        pwShown = 0;
        hide();
    }
}, false);

</script>
</body>
</html>
