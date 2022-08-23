<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% int sum=(int)request.getAttribute("sum");
   String before=(String)request.getAttribute("before");
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/style3.css">
<link href="https://use.fontawesome.com/releases/v6.1.2/css/all.css" rel="stylesheet">
<title>購入確認 | GouMao</title>
<style>
.active {
	width: 100%;
}
.passive  {
	display: none;
	width: 100%;
}
</style>
<script>
	function showCreditCard() {
		document.getElementById("creditCard_area").className = "active";
		document.getElementById("convenience_area").className = "passive";
		document.getElementById("cashOnDelivery_area").className = "passive";
		
		document.getElementById("normalTable").className = "active";
		document.getElementById("deliveryTable").className = "passive";
	}
	function showConvenience() {
		document.getElementById("creditCard_area").className = "passive";
		document.getElementById("convenience_area").className = "active";
		document.getElementById("cashOnDelivery_area").className = "passive";
		
		document.getElementById("normalTable").className = "active";
		document.getElementById("deliveryTable").className = "passive";
	}
	function showCashOnDelivery() {
		document.getElementById("creditCard_area").className = "passive";
		document.getElementById("convenience_area").className = "passive";
		document.getElementById("cashOnDelivery_area").className = "active";
		
		document.getElementById("normalTable").className = "passive";
		document.getElementById("deliveryTable").className = "active";
	}
</script>
</head>
<body>
<jsp:include page="/header1.jsp"/> 
<div id="b-inner">
<div class="inner">
<h2 class="cart-h2">ご注文手続き</h2>

<div class="cart-content">
	<div class="cart-area2">
		<h3>お支払方法</h3>
		<div class="payment-area">
			<label class="ECM_RadioInput"><input class="ECM_RadioInput-Input" type="radio" name="target" value="cc" onchange="showCreditCard();" checked><span class="ECM_RadioInput-DummyInput"></span><span class="ECM_RadioInput-LabelText">クレジットカード</span></label>
			<ul id="creditCard_area" class="active">
				<li>お支払いに必要なカード情報を入力してください。</li>
				<li class="Form-Item"><p class="Form-Item-Label">名前</p><input type="text" placeholder='TARO YAMADA' name="ccname" class="Form-Item-Input"></li>
				<li class="Form-Item"><p class="Form-Item-Label">カード</p><input type="text" maxlength="16" placeholder='0000 0000 0000 0000' name="ccnumber" class="Form-Item-Input"></li>
				<li class="Form-Item"><p class="Form-Item-Label">有効期限</p><input type="text" maxlength="2" placeholder='mm' name="ccmonth" class="Form-Item-Input-m">&nbsp;&nbsp;<input type="text" maxlength="2" placeholder='dd' name="ccday" class="Form-Item-Input-day"></li>
				<li class="Form-Item"><p class="Form-Item-Label">CVC番号</p><input type="text" maxlength="3" placeholder='000' name="cvc" class="Form-Item-Input-cvc"></li>
			</ul>
			
			<label class="ECM_RadioInput"><input class="ECM_RadioInput-Input" type="radio" name="target" value="cs" onchange="showConvenience();"><span class="ECM_RadioInput-DummyInput"></span><span class="ECM_RadioInput-LabelText">コンビニ決済</span></label>
			<div id="convenience_area" class="passive">
				<p>商品を受け取る店舗を選択してください。</p>
				<div class="map">
					<iframe src="https://www.google.com/maps/embed?pb=!1m14!1m8!1m3!1d6888.964595368555!2d136.8860074456371!3d35.426107464780934!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x60030610b68e3b8f%3A0xd5406da60317f031!2z77yI5qCq77yJVlLjg4bjgq_jg47jgrvjg7Pjgr_jg7w!5e0!3m2!1sja!2sjp!4v1658968113365!5m2!1sja!2sjp" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
				</div>
			</div>
			
			<label class="ECM_RadioInput"><input class="ECM_RadioInput-Input" type="radio" name="target" value="cod" onchange="showCashOnDelivery();"><span class="ECM_RadioInput-DummyInput"></span><span class="ECM_RadioInput-LabelText">代金引換</span></label>
			<div id="cashOnDelivery_area" class="passive">
				<p>お荷物の数にかかわらず330円（税込）の代金引換手数料がかかります。</p>
				<p>配送業者がお客様に発行するお渡しする領収書が正式な領収書となります。</p>
				<p>領収書の再発行は、どのような状況でもお受けできませんので、後日必要となる場合に備え安全な場所に保管してください。</p>
			</div>
		</div>	
	</div>


	<div class="pay-area">
		<table id="normalTable" class="active">
			<tr>
				<th>小計(税込)</th>
				<% String ps = String.format("%,d",sum); %>
				<td>&yen;<%=ps %></td>
			</tr>
			<tr>
				<th>送料</th>
				<td>&yen;0</td>
			</tr>
			<tr class="tr-sum">
				<th>合計(税込)</th>
				<td class="td-sum">&yen;<%=ps %></td>
			</tr>
		</table>
		<table id="deliveryTable" class="passive">
			<tr>
				<th>小計(税込)</th>
				<td>&yen;<%=ps %></td>
			</tr>
			<tr>
				<th>送料</th>
				<td>&yen;0</td>
			</tr>
			<tr>
				<th>代引手数料</th>
				<td>&yen;330</td>
			</tr>
			<tr class="tr-sum">
				<th>合計(税込)</th>
				<% String psd = String.format("%,d",sum + 330); %>
				<td class="td-sum">&yen;<%=psd %></td>
			</tr>
		</table>
	<%if(before!=null){ %>
		<form action="BuyServlet" method="post">
		<input type="submit" class="bc-buy-btn" value="注文確定する"/>
		</form>
		
	<%}else{ %>
	    <form action="BuyServlet" method="get">
		<input type="submit" class="bc-buy-btn" value="注文確定する"/>
		</form>
	<%} %>
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
<jsp:include page="/footer1.jsp"/> 
</body>
</html>