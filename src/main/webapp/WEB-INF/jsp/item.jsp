<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item" %>
<% Item i=(Item)request.getAttribute("item");
	String paging=(String)request.getAttribute("paging");%>
	<% String pi = String.format("%,d", i.getPrice()); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/style3.css">
<link href="https://use.fontawesome.com/releases/v6.1.2/css/all.css" rel="stylesheet">

<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script>
$(function(){
	$("#modal__btn").on("click", sayAddCart);
});
function sayAddCart(){
	alert("カートに追加しました。");
}
</script>

<title><c:out value="<%=i.getName() %>"/> | GouMao</title>
</head>
<body>
<jsp:include page="/header1.jsp"></jsp:include>
<div id="b-inner">
<div class="inner">
	<p class="return"><a href="index.jsp">ホーム</a>&nbsp;&nbsp;&nbsp;<%if(paging!=null){ %><i class="fa-solid fa-angles-right my-small"></i>&nbsp;&nbsp;&nbsp;<a href="SearchResultPaging?paging=<%=paging%>">検索結果</a><%} %></p>
	
	<div class="high-content">
		<div class="hc1">
			<img src="${pageContext.request.contextPath }\upload\<%= i.getImage() %>">
		</div>
		<div class="hc2">
			<h2><c:out value="<%=i.getName() %>"/></h2><br><br>
			<form  method="post">
			<table border="1">
				<tr>
					<th>型番</th>
					<td><c:out value="<%=i.getIID() %>"/></td>
				</tr>
				<tr>
					<th>販売価格</th>
					<td class="price">&yen;&nbsp;<c:out value="<%=pi %>"/>&nbsp;(税込)</td>
				</tr>
				<tr>
					<th>購入数</th>
					<td><input type="number" value="1" name="buyNum_" min="1" max="<%=i.getStock() %>"></td>
				</tr>
			</table>
			<input type="hidden" value="<%=i.getIID() %>" name="iId"><!-- ←これ気にしないで☆ -->
			
			<div class="share">
				<a href="https://twitter.com/share?ref_src=twsrc%5Etfw" class="twitter-share-button" data-show-count="false"></a><script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
				<div class="line-it-button" data-lang="ja" data-type="share-a" data-env="REAL" data-url="https://developers.line.biz/ja/docs/line-social-plugins/install-guide/using-line-share-buttons/" data-color="default" data-size="small" data-count="false" data-ver="3" style="display: none;"></div>
				<script src="https://www.line-website.com/social-plugins/js/thirdparty/loader.min.js" async="async" defer="defer"></script>
			</div>
			
			<div class="item-button">
				<div class="item-buy">
					<%if(i.getStock() != 0){ %>
					<input type="submit" value="カートに追加" formaction="CartServlet?action=cart&before=item&paging=<%=paging %>" id="modal__btn" class="add-cart">
					<input type="submit" value="すぐに購入" formaction="BuyOneItemServlet?before=one" class="buy-now">
				</div>
				<div class="item-buy-SO">
					<%}else{ %><!-- 在庫が無い場合 -->
					<input type="submit" value="SOLD OUT" disabled="disabled"formaction="CartServlet?action=cart&before=item" class="soldout">
					<input type="submit" value="SOLD OUT" disabled="disabled"formaction="CartServlet?action=buy&before=item" class="soldout">
					<%} %>
				</div>
			</div>
			</form>
		</div>
	</div>
	
	<div class="middle-content">
		<%if(i.getInformation() != ""){ %>
		<h3>商品情報</h3><hr color="#555">
		<pre><c:out value="<%=i.getInformation() %>"/></pre><br>
		<%}else{} %>

		<%if(i.getSpec() != ""){ %>
		<h3>サイズ、スペック</h3><hr color="#555">
		<pre><c:out value="<%=i.getSpec() %>"/></pre><br>
		<%}else{} %>
		
		<%if(i.getMaterial() != ""){ %>
		<h3>素材、原材料</h3><hr color="#555">
		<pre><c:out value="<%=i.getMaterial() %>"/></pre><br>
		<%}else{} %>
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
<jsp:include page="/footer1.jsp"></jsp:include>
</body>
</html>