<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/style3.css">
<link href="https://use.fontawesome.com/releases/v6.1.2/css/all.css" rel="stylesheet">
<title>注文完了 | GouMao</title>
</head>
<body>
<jsp:include page="/header1.jsp"></jsp:include>
<div id="b-inner">
<div class="inner">
	<h2 class="cart-h2">ご注文完了</h2>
	<div class="order-content">
		<p class="thanks">ご注文ありがとうございました。</p>
		<p class="ut">万一、注文した商品が１週間以上届かない場合、トラブルの可能性もありますので大変お手数ではございますが、お電話、メールよりお問い合わせくださいませ。</p>
		<p class="ut">今後ともご愛顧賜りますようお願い申し上げます。</p>
		<p class="ut">引き続きお買い物をお楽しみください。</p>
		<a href = "index.jsp" class="order-btn">トップへ戻る</a>
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