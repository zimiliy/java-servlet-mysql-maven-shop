<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,model.CartItem,java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% List<CartItem> historyList=(List)session.getAttribute("historyList"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/style3.css">
<link href="https://use.fontawesome.com/releases/v6.1.2/css/all.css" rel="stylesheet">

<script type="text/javascript">
	function check(){
		var result = window.confirm("削除します。よろしいですか？");
		if(result){
			return true;
		}else{
			return false;
		}
	}
</script>

<title>マイページ | GouMao</title>
</head>
<body>
<jsp:include page="/header1.jsp"></jsp:include>
<div id="b-inner">
<div class="inner">
	<h2 class="cart-h2">マイページ</h2>
		<div class="cart-content">
		    <div class="account-area">
		    	<h3>アカウントメニュー</h3>
					<a href = "userEdit.jsp"><p>アカウント情報変更</p><i class="fa-solid fa-chevron-right fa-lg"></i></a>
					<a href = "DelServlet" onclick = "return check();" ><p>アカウント削除</p><i class="fa-solid fa-chevron-right fa-lg"></i></a>
		    </div>
		    <div class="phistory-area">
		        <h3>購入履歴</h3>
				<%if(historyList.size()!=0){ %>
					<%for(CartItem ci:historyList){ %>
						<div class="phistory-content">
							<img src="${pageContext.request.contextPath }\upload\<%=ci.getItem().getImage() %>"  width="120" height="120">
							
							<div class="phistory-name">
								<p class="ph-name"><c:out value="<%=ci.getItem().getName() %>"/></p>
							
								<div class="phistory-price">
									<div>
										<% String hci = String.format("%,d", ci.getItem().getPrice()); %>
										<p class="ph-p">価格&nbsp;&yen;<c:out value="<%=hci %>"/></p>
										<p class="ph-p">購入数&nbsp;<%=ci.getBuyNum() %>点</p>
									</div>
									<a href="ItemServlet?iId=<%=ci.getItem().getIID()%>">再度購入</a>
								</div>
							</div>
						</div>
					<% } %>
				<%}else{ %>
					<p class="ph-none"><i class="fa-solid fa-triangle-exclamation"></i>&nbsp;購入履歴がありません</p>
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
<jsp:include page="/footer1.jsp"></jsp:include>
</body>
</html>