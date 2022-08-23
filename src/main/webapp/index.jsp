<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.NewItemSearchLogic" %>
<%@ page import="DAO.ItemDAO,model.Item,java.util.List,model.userDB,model.RecoLogic" %>
<%userDB user=(userDB)session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/style3.css">
<link href="https://use.fontawesome.com/releases/v6.1.2/css/all.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">

<link href="https://file003.shop-pro.jp/PA01462/217/js/slick.css" rel="stylesheet">
<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="https://file003.shop-pro.jp/PA01462/217/js/slick.min.js"></script>
<script>
    $(function(){
        $('.hero--slider').slick({
          speed: 500,
          infinite: true,
          slidesToShow: 1,
          slidesToScroll: 1,
          dots: true,
          arrows: true,
          prevArrow: '<div class="slick-arrow slick-prev"><span class="icon-arrow"></span></div>',
          nextArrow: '<div class="slick-arrow slick-next"><span class="icon-arrow"></span></div>',
          autoplay: true,
          autoplaySpeed: 3000,
        });
      	$('.home_bnr--slider').slick({
          lazyLoad: 'progressive',
          speed: 500,
          infinite: true,
          slidesToShow: 1,
          slidesToScroll: 1,
          dots: true,
          arrows: false,
          autoplay: true,
          autoplaySpeed: 3000,
        });
	});


</script>
<title>TOP | GouMao</title>
</head>
<body>
<jsp:include page="header1.jsp"></jsp:include>
<div id="b-inner" class="">
<div class="index-wrap">
  <div class="home_hero" id="hero">
    <div class="inner">
      <ul class="hero--slider">
        <li>
          <a href="SearchItemServlet?mainId=A&subId=1">
            <img src="images/GouMao_ad1.jpg" alt="" class="img">
          </a>
        </li>
        <li>
          <a href="SearchItemServlet?mainId=A&subId=2">
            <img src="images/GouMao_ad2.jpg" alt="" class="img">
          </a>
        </li>
        <li>
          <a href="ItemServlet?iId=B_1659666852278_5&paging=1">
            <img src="images/GouMao_ad3.jpg" alt="" class="img">
          </a>
        </li>
      </ul>
    </div>
  </div>

<div class="inner">
	<div class="SR-content">
		<div class="SR1">
			<h2 class="h2-mini"><i class="fa-solid fa-magnifying-glass"></i>&nbsp;カテゴリーから探す</h2>
			<h3><i class="fa-solid fa-dog"></i>&nbsp;DOG</h3>
			<ul>
				<li class="categoryA"><a href="SearchItemServlet?mainId=A&subId=1">&nbsp;フード</a></li>
				<li class="categoryA"><a href="SearchItemServlet?mainId=A&subId=2">&nbsp;ファッション</a></li>
				<li class="categoryA"><a href="SearchItemServlet?mainId=A&subId=3">&nbsp;オモチャ</a></li>
				<li class="categoryA"><a href="SearchItemServlet?mainId=A&subId=4">&nbsp;お散歩・お出かけ</a></li>
				<li class="categoryA"><a href="SearchItemServlet?mainId=A&subId=5">&nbsp;ヘルス・ケア</a></li>
				<li class="categoryA"><a href="SearchItemServlet?mainId=A&subId=6">&nbsp;ハウス</a></li>
				<li class="categoryA"><a href="SearchItemServlet?mainId=A&subId=7">&nbsp;その他</a></li>
			</ul>
			<h3><i class="fa-solid fa-cat"></i>&nbsp;CAT</h3>
			<ul>
				<li class="categoryB"><a href="SearchItemServlet?mainId=B&subId=1">&nbsp;フード</a></li>
				<li class="categoryB"><a href="SearchItemServlet?mainId=B&subId=2">&nbsp;ファッション</a></li>
				<li class="categoryB"><a href="SearchItemServlet?mainId=B&subId=3">&nbsp;オモチャ</a></li>
				<li class="categoryB"><a href="SearchItemServlet?mainId=B&subId=4">&nbsp;お散歩・お出かけ</a></li>
				<li class="categoryB"><a href="SearchItemServlet?mainId=B&subId=5">&nbsp;ヘルス・ケア</a></li>
				<li class="categoryB"><a href="SearchItemServlet?mainId=B&subId=6">&nbsp;ハウス</a></li>
				<li class="categoryB"><a href="SearchItemServlet?mainId=B&subId=7">&nbsp;その他</a></li>
			</ul>
		</div>
	
		<div class="SR2">
			<h2 class="h2-big"><i class="fa-solid fa-magnifying-glass"></i>&nbsp;商品を探す</h2>
			<form action="SearchItemServlet?action=index" method="post" class="SRsearch-container">
				<div class="container">
					<div class="search">
						<input type="text" name="freeWord" placeholder="商品名、キーワードを入力" />
						<button type="submit"><i class="fa-brands fa-sistrix"></i></button>
					</div>
				</div>
				<p class="radio-btns">
					<input type="radio" class="option-input" name="mainId" value="A" id="radio1"/><label for="radio1">Dog</label>
					<input type="radio" class="option-input" name="mainId" value="B" id="radio2"/><label for="radio2">Cat</label>
				</p>
			</form>	

			<div class="index-content">
				<h2 class="h2-mini">売れ筋商品</h2>
				<div class="ic-wrap">
				<% ItemDAO dao=new ItemDAO(); 
				List<Item> rankList=dao.ranking();
				for(int i=0;i<5;i++){
					Item item=rankList.get(i);%>
					<div class="ic-area">
						<div class="ic-img">
							<a href="ItemServlet?iId=<%=item.getIID()%>"><img src="${pageContext.request.contextPath }\upload\<%= item.getImage() %>"></a>
						</div>
						<div class="ic-text">
							<a href="ItemServlet?iId=<%=item.getIID()%>"><%=item.getName() %></a>
						<% String pi = String.format("%,d",item.getPrice()); %>
							<p>&yen;&nbsp;<%=pi %>&nbsp;(税込)</p>
						</div>
					</div>
						<%if(i+1==rankList.size()){
							break;
						} %>
					
					<%} %>
				</div>
				
				<h2 class="h2-mini">新着商品</h2>
				<div class="ic-wrap">
			<% NewItemSearchLogic nisl=new NewItemSearchLogic();
				List<Item> newList=nisl.NewItemSearch();
				for(int i=0;i<5;i++){
					Item item=newList.get(i);%>
					<div class="ic-area">
						<div class="ic-img">
							<a href="ItemServlet?iId=<%=item.getIID()%>"><img src="${pageContext.request.contextPath }\upload\<%= item.getImage() %>"></a>
						</div>
						<div class="ic-text">
							<a href="ItemServlet?iId=<%=item.getIID()%>"><%=item.getName() %></a>
						<% String pi = String.format("%,d",item.getPrice()); %>
							<p>&yen;&nbsp;<%=pi %>&nbsp;(税込)</p>
						</div>
					</div>
						<%if(i+1==newList.size()){
							break;
						} %>
						
				<%} %>
				</div>
				
				<!-- オススメ -->
				<h2 class="h2-mini">あなたへのオススメ</h2>
				<div class="ic-wrap">
				<%if(user!=null){ %>
					<%RecoLogic rl=new RecoLogic();
					  List<Item> recoList=rl.reco(user);
					  if(recoList.size()!=0){
						  for(int i=0;i<5;i++){
						  Item item=recoList.get(i);%>
						 <div class="ic-area">
							<div class="ic-img">
								<a href="ItemServlet?iId=<%=item.getIID()%>"><img src="${pageContext.request.contextPath }\upload\<%= item.getImage() %>"></a>
							</div>
							<div class="ic-text">
								<a href="ItemServlet?iId=<%=item.getIID()%>"><%=item.getName() %></a>
							<% String pi = String.format("%,d",item.getPrice()); %>
								<p>&yen;&nbsp;<%=pi %>&nbsp;(税込)</p>
							</div>
						</div>
						<%} %>  
					<%}else{ %>
						<p class="check-none"><i class="fa-solid fa-triangle-exclamation"></i>&nbsp;商品を購入してください</p>
					<%} %>
				<%}else{ %>
					<p class="check-none"><i class="fa-solid fa-triangle-exclamation"></i>&nbsp;ログインしてください</p>
				<%} %>
				</div>
			</div>	
		</div>
	</div>
		<div class="f_shopping_guide">
		<ul>
			<li class="guide01">
                <h3>送料について</h3>
                <p class="txt">
                    当サイトでは、なんと<br>全品送料無料！<br>※沖縄県、クール便、大型送料を除く<br>　<br>
                </p>
            </li>

            <li class="guide02">
                <h3>お届けについて</h3>
                <p class="txt">
                    在庫のある商品は<br>2～5日程度でお届けします。<br>※一部地域・商品を除く<br>　<br>
                </p>
            </li>

            <li class="guide03">
                <h3>お支払い方法について</h3>
                <p class="txt">
                    3つのお支払い方法が選べます。<br>各種クレジット・コンビニ払い・代金引換<br><br>　<br>
                </p>
            </li>

            <li class="guide04">
                <h3>返品・交換について</h3>
                <p class="txt">
                    商品到着後10日以内で承ります。<br>返品・交換をご希望の場合は必ず、<br>0120-×××-×××までご連絡下さい。<br>　<br>
                </p>
            </li>
        </ul>
    </div>
</div>	
		

</div>
</div>
<jsp:include page="footer1.jsp"></jsp:include>
</body>
</html>