<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "model.userDB" %>
<% userDB user = (userDB)session.getAttribute("loginUser"); %>
<script>
$(function() {
    $('.hamburger').click(function() {
        $(this).toggleClass('active');
 
        if ($(this).hasClass('active')) {
            $('.globalMenuSp').addClass('active');
        } else {
            $('.globalMenuSp').removeClass('active');
        }
    });
});
</script>
<img src="images/grd.jpg" class="grd">
<div class="header0-wrap">
	<div class="inner">
	<div class="left-flex">
		<h1 id="logo"><a href="index.jsp"><img src="images/Logowanko2.png" alt="goumao"></a></h1>
	</div>
	<nav class="p-nav">
		<ul>
			<li>
				<a href="SearchItemServlet?mainId=A">
					<i class="fa-solid fa-dog"></i>
					<p>犬用品を探す&nbsp;<i class="fa-solid fa-angle-down"></i></p>
				</a>
			</li>
			<li>
				<a href="SearchItemServlet?mainId=B">
					<i class="fa-solid fa-cat"></i>
					<p>猫用品を探す&nbsp;<i class="fa-solid fa-angle-down"></i></p>
				</a>
			</li>
			<li>
				<a href="guide.jsp">
					<i class="fa-solid fa-book"></i>
					<p>ご利用ガイド</p>
				</a>
			</li>
			<li>
				<a href="question.jsp">
					<i class="fa-solid fa-clipboard-question"></i>
					<p>よくある質問</p>
				</a>
			</li>
		</ul>
	</nav>

	<ul class="nav1">
		<%if(user != null){ %>
		<li class="nav-btn1">
			<a href="LogoutServlet">
				<div class="btn-naka">
					<i class="fa-solid fa-right-from-bracket fa-lg"></i>
					<p>ログアウト</p>
				</div>
			</a>
		</li>
		<%}else{ %>
		<li class="nav-btn1">
			<a href="login.jsp">
				<div class="btn-naka">
					<i class="fa-solid fa-right-to-bracket fa-lg"></i>
					<p>ログイン</p>
				</div>
			</a>
		</li>
		<%} %>
		<%if(user != null){ %>
		<li class="nav-btn2">
			<a href="MypageServlet">
				<div class="btn-naka">
					<i class="fa-solid fa-circle-user fa-lg"></i>
					<p>マイページ</p>
				</div>
			</a>
		</li>
		<%}else{ %>
		<li class="nav-btn2">
			<a href="signUp.jsp">
				<div class="btn-naka">
					<i class="fa-solid fa-circle-user fa-lg"></i>
					<p>会員登録</p>
				</div>
			</a>
		</li>
		<%} %>
		<li class="nav-btn3">
			<a href="CartServlet">
				<div class="btn-naka">
					<i class="fa-solid fa-cart-shopping fa-lg"></i>
					<p>カート</p>
				</div>
			</a>
		</li>
		<li class="nav-btn3 p-none">
			<a class="toggle-btn">
				<i class="fa-solid fa-bars  fa-lg"></i>
			</a>
		</li>
	</ul>
	</div>
</div>

<div class="user-state">
	<div class="inner-us">
	<%if(user != null){ %>
		<p><strong><%= user.getUSER() %></strong>&nbsp;でログイン中</p>
	<%}else{ %>
		<p>ようこそ、<strong>ゲスト</strong>&nbsp;さん！</p>
	<%} %>
	</div>
</div>


<!-- レスポンシブナビゲーション -->

<div id="m-navArea" class="w-none p-none">
	<nav>
		<ul>
			<li>
				<ul>
					
					<li class="nav-btn">
						<a href="login.jsp">
							<p><i class="fa-solid fa-right-to-bracket fa-lg"></i>ログイン</p>
							
							<!-- 
							<div class="btn-naka">
								<i class="fa-solid fa-right-to-bracket fa-lg"></i>
								<p>ログイン</p>
							</div>
							-->
						</a>
					</li>
					
					
					<li class="nav-btn">
						<a href="signUp.jsp">
							<p><i class="fa-solid fa-circle-user fa-lg"></i>会員登録</p>
							
							<!-- 
							<div class="btn-naka">
								<i class="fa-solid fa-circle-user fa-lg"></i>
								<p>会員登録</p>
							</div>
							 -->
						</a>
					</li>
					
				</ul>
			</li>
			
			<li>
				<ul>
					<li><a href="SearchItemServlet?mainId=A&subId=1">&nbsp;フード</a></li>
					<li><a href="SearchItemServlet?mainId=A&subId=2">&nbsp;ファッション</a></li>
					<li><a href="SearchItemServlet?mainId=A&subId=3">&nbsp;オモチャ</a></li>
					<li><a href="SearchItemServlet?mainId=A&subId=4">&nbsp;お散歩・お出かけ</a></li>
					<li><a href="SearchItemServlet?mainId=A&subId=5">&nbsp;ヘルス・ケア</a></li>
					<li><a href="SearchItemServlet?mainId=A&subId=6">&nbsp;ハウス</a></li>
					<li><a href="SearchItemServlet?mainId=A&subId=7">&nbsp;その他</a></li>
				</ul>
			</li>
			
			<li>
				<ul>
					<li><a href="SearchItemServlet?mainId=B&subId=1">&nbsp;フード</a></li>
					<li><a href="SearchItemServlet?mainId=B&subId=2">&nbsp;ファッション</a></li>
					<li><a href="SearchItemServlet?mainId=B&subId=3">&nbsp;オモチャ</a></li>
					<li><a href="SearchItemServlet?mainId=B&subId=4">&nbsp;お散歩・お出かけ</a></li>
					<li><a href="SearchItemServlet?mainId=B&subId=5">&nbsp;ヘルス・ケア</a></li>
					<li><a href="SearchItemServlet?mainId=B&subId=6">&nbsp;ハウス</a></li>
					<li><a href="SearchItemServlet?mainId=B&subId=7">&nbsp;その他</a></li>
				</ul>
			</li>
			
			<li>
				<ul>
					<li><a href="guide.jsp"><p>ご利用ガイド</p></a></li>
					<li><a href="question.jsp"><p>よくある質問</p></a></li>
				</ul>
			</li>
		</ul>
	</nav>
</div>

<div id="mask"></div>

<script>
let nav = document.querySelector("#m-navArea")
let btn = document.querySelector(".toggle-btn")
let mask = document.querySelector("#mask")

btn.onclick = () => {
	nav.classList.toggle("w-none");
};


</script>

<!-- レスポンシブナビゲーションここま -->
