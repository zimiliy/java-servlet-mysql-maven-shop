<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,model.CartItem,java.util.List" %>
<% List<CartItem> cartList=(List)session.getAttribute("cartList");
	List<String> emList=(List)request.getAttribute("errorMsgList");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/style3.css">
<link href="https://use.fontawesome.com/releases/v6.1.2/css/all.css" rel="stylesheet">

<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<title>カート | GouMao</title>
</head>
<body>
<jsp:include page="/header1.jsp"></jsp:include>
<div id="b-inner">
<div class="inner">
	<h2 class="cart-h2">ショッピングカート</h2>
	<p class="cart-p">カートの内容をご確認ください。ご注文が完了するまでは、商品が確保されたことにはなりません。</p>
	
	<div class="cart-content">
		<%if(emList!=null){
			for(String em:emList){%>
			<p><%=em %></p>
		<%	}
		 } %>
		<%if(cartList!=null && cartList.size()!=0){ %>
		<div class="cart-area">
			<form action="CartEditServlet" method="post">
				<div class="cart-header">
					<div class="cart-th car4">商品詳細</div>
					<div class="cart-th car2">価格</div>
					<div class="cart-th car2">数量</div>
					<div class="cart-th car2">金額</div>
					<div class="cart-th car2">&emsp;&emsp;</div>
				</div>
		<%
		int smallSum=0;
		for(int i=0;i<cartList.size();i++){ 
			CartItem ci=cartList.get(i);
			smallSum=smallSum+(ci.getItem().getPrice()*ci.getBuyNum());%>
				<div class="cart-item">
					<div class="cart-img">
						<img src="${pageContext.request.contextPath }\upload\<%=ci.getItem().getImage() %>" width="120" height="120">
					</div>
					<div class="cart-name">
						<a href="ItemServlet?iId=<%=ci.getItem().getIID()%>"><c:out value="<%=ci.getItem().getName() %>"/></a>
					</div>
					<div class="cart-price">
					<% String pi = String.format("%,d",ci.getItem().getPrice()); %>
						<p>&yen;<c:out value="<%=pi %>"/></p>
					</div>
					<div class="cart-count">
						<input type="number" value="<%=ci.getBuyNum() %>" name="<%=i %>" min="1" max="<%=ci.getItem().getStock() %>" class="cc-number">
						<input type="submit" value="数量変更" class="cc-submit" id="cc-submit"><br><label for="cc-submit" class="m-none">数量変更</label><label for="cc-submit" class="p-none"><i class="fa-solid fa-rotate"></i></label>
					</div>
					<div class="cart-sum">
					<% String ps = String.format("%,d",ci.getItem().getPrice()*ci.getBuyNum()); %>
						<p>&yen;<%= ps %></p>
					</div>
					<div class="cart-delete">
						<a href="CartEditServlet?deleteIndex=<%=i %>" class="m-none">削除</a>
						<a href="CartEditServlet?deleteIndex=<%=i %>" class="p-none"><i class="fa-solid fa-xmark"></i></a>
					</div>
				</div>
		<% } %>
			</form>
		</div>
		
		<div class="pay-area">
		<% String csum = String.format("%,d",smallSum); %>
			<table>
				<tr>
					<th>小計(税込)</th>
					<td>&yen;<%=csum %></td>
				</tr>
				<tr>
					<th>送料</th>
					<td>&yen;0</td>
				</tr>
				<tr class="tr-sum">
					<th>合計(税込)</th>
					<td class="td-sum">&yen;<%=csum %></td>
				</tr>
			</table>
			<a href="CartServlet?action=buy&before=cart" class="pa-buy-btn">購入手続きに進む</a>
		</div>
	<%}else{ %>
		<p class="cart-none"><i class="fa-solid fa-triangle-exclamation"></i>&nbsp;カートの中身がありません</p>
	<%} %>
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