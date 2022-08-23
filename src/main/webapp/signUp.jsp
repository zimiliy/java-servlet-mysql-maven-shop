<%--
  Created by IntelliJ IDEA.
  User: KNuser31
  Date: 2022/8/2
  Time: 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<link href="https://use.fontawesome.com/releases/v6.1.2/css/all.css" rel="stylesheet">
	
    <title>新規登録 | GouMao</title>
</head>
<body class="body2">
<div class="yohaku"></div>

<div class="overlay">

<!-- ログインフォーム -->
<form class="form2" method="post" action="RegisterServlet">
 <!-- con = フォーム内のアイテムのコンテナ-->
 <div class="con">
 
	<!-- 　　ここからヘッダーのコンテナ　　 -->
	<header class="head-form">
    	<h2 class="logologo"><img src="css/images/Logowanko.png" alt="logo"></h2>
    		<!-- welcome message -->
    		<p>GouMaoへようこそ！まずは登録！</p>
	</header>
	<!-- 　　ここまでヘッダーのコンテナ　　 -->
	
	
	<div class="field-set">
		<!--　ユーザーネーム -->
		<div class ="taisyo">
			<div class="input-item">
	        	<i class="fa fa-user-circle"></i> ユーザー名 　：
	        </div>
	        <!-- ユーザーネーム入力 -->
	    	<input class="form-input" type="text" placeholder="UserName" name="username" required/>
		</div>

    	<br>
    	
    	<!-- パスワード -->
    	<div class ="taisyo">
	    	<div class="input-item">
	    		<i class="fa fa-key"></i> パスワード　 ：
	        </div>
	        <!-- パスワード入力-->
	    	<input class="form-input" type="password" placeholder="Password" name="password" required/>
    	</div>

    	<br>
    	
    	<!-- パスワード確認 -->
    	<div class = "taisyo">
	    	<div class="input-item">
	    		パスワード確認 ：
	    	</div>
	    	<!-- パスワード入力確認-->
	    	<input class="form-input" type="password" name="password2"/>
    	</div>

    	<br/>
    	
    	<!-- メールアドレス -->
    	<div class = "taisyo">
    	    <div class="input-item">
    		<i class="fa-solid fa-envelope"></i> 　メール　　：
    		</div>
    		<!-- メールアドレス入力 -->
    		<input class="form-input" type="text"  placeholder="GouMao@exmple.com" name="mail" required/>
    	</div>

    	<br/>
    	
    	<!-- 住所 -->
    	<div class = "taisyo">
	    	<div class="input-item">
	    		<i class="fa-solid fa-house-chimney"></i> 　住　所　　：
	    	</div>
	    	<!-- 住所入力 -->
	    	<input class="form-input" type="text" placeholder="〇〇県〇〇町 123-45" name="address" required/>
    	</div>

    	<br/>
    </div>
    <input class="touroku-button" type="submit" value="登録！"/>
    <!--     ここからその他のボタン     -->
	<div class="other">	
    <!--   リセットボタン   -->
    <input  class="btn submits frgt-pass" type="reset" value="リセット">
    <a class="aaa" href="login.jsp">ログインへ</a>
 </div>
 <!--   ここまでコンテナ  -->
</form>
</div>
<!--  フッター -->
<jsp:include page="/footer5.jsp"/>
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
