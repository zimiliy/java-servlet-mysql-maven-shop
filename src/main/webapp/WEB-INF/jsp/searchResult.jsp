<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,java.util.List,model.PagingLogic,model.SearchWord" %>
<%List<Item> itemList=(List)session.getAttribute("itemList"); 
	String p_=(String)request.getAttribute("paging");
	if(p_==null){
		p_="1";
	}
	int paging=0;
	if(p_!=null){
		paging=Integer.parseInt(p_)*9-9;
	}
	SearchWord sw=(SearchWord)session.getAttribute("searchWord");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<link rel="stylesheet" href="css/style3.css">
<link href="https://use.fontawesome.com/releases/v6.1.2/css/all.css" rel="stylesheet">
<script type='text/javascript' src="//ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<title>検索結果 | GouMao</title>
</head>
<body>
<jsp:include page="/header1.jsp"></jsp:include>
<div id="b-inner">
<div class="inner">
<p class="return"><a href="index.jsp">ホーム</a>&nbsp;&nbsp;&nbsp;<i class="fa-solid fa-angles-right my-small"></i>&nbsp;&nbsp;&nbsp;検索結果</p>
	<div class="SR-content">
		<div class="SR1">
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
			<!-- 検索ワードを表示 -->
			<!-- フリーワードを表示 -->
			<p class="s-word">
			<%if(sw.getFreeWord()!=null){ %>
				<%for(String s:sw.getFreeWord()){ %>
					"<%=s %>"
				<%} %>
			<%} %>
			<!-- メインカテゴリーを表示 -->
			<%if(sw.getMainId()!=null){ %>
				"<%=sw.getMainId() %>"
			<%} %>
			<!-- サブカテゴリ―を表示 -->
				<%if(sw.getSubId()!=null){ %>
				"<%=sw.getSubId() %>"
			<%} %>
			の検索結果
			</p>
			<!-- 検索ワードの表示ここまで -->
			<form action="SearchItemServlet?action=serchResult" method="post" class="SRsearch-container">
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
			<!-- ソート -->
			<div class="sort">
				<p><i class="fa-solid fa-arrow-down-wide-short"></i>
				<a href="ItemSortServlet?sort=new">&nbsp;新着順</a>
				<a href="ItemSortServlet?sort=buycount">&nbsp;人気順</a>
				<a href="ItemSortServlet?sort=highPrice">&nbsp;価格高</a>
				<a href="ItemSortServlet?sort=lowPrice">&nbsp;価格低</a>
				</p>
			</div>
			<!-- ソートここまで -->
			
			<% if(itemList.size()>0){ %>
			<div class="ra-wrap">
			<%for(int i=0;i<9;i++){ 
				Item item=itemList.get(paging);%>
				<div class="result-area">
					<div class="ra-img">
						<a href="ItemServlet?iId=<%=item.getIID()%>&paging=<%=(paging/9)+1%>"><img src="${pageContext.request.contextPath }\upload\<%= item.getImage() %>"></a>
					</div>
					<% String pi = String.format("%,d",item.getPrice()); %>
					<div class="ra-text">
						<a href="ItemServlet?iId=<%=item.getIID()%>&paging=<%=(paging/9)+1%>"><c:out value="<%=item.getName() %>"/></a><br>
						<p>&yen;&nbsp;<c:out value="<%=pi %>"/>&nbsp;(税込)</p><br>
					</div>
				</div>
				<%System.out.println(item.getImage()); %>
				<% paging++; 
					if(paging==itemList.size()){
						break;
					}
					} %>
			</div>
				<ul class="pagenation">
					<%int p=Integer.parseInt(p_); %>
					<%if(p!=1){ %>
						<li><a href="SearchResultPaging?paging=<%=p-1 %>"><i class="fa-solid fa-angles-left"></i></a></li><!-- 前のページへ -->
					<%} %>
					<%PagingLogic pl=new PagingLogic(); %>
					<%for(int i=1;i<=(itemList.size()-1)/9+1;i++){ %>
						<%int r=pl.pagingLogic(p, (itemList.size()-1)/9+1, i);
						if(r==1){ %>
							<li <%if(i==p){ %>class="now"<%} %>><a href="SearchResultPaging?paging=<%=i %>"><%=i %></a></li>
						<%}else if(r==2){ %>
							<li>...</li>
						<%} %>
					<%} %>
					<%if(p!=(itemList.size()-1)/9+1){ %>
						<li><a href="SearchResultPaging?paging=<%=p+1 %>"><i class="fa-solid fa-angles-right"></i></a></li><!-- 次のページへ -->
					<%} %>
				</ul>
					<%}else{ %>
						<p class="check-none"><i class="fa-solid fa-circle-exclamation"></i>&nbsp;検索結果が見つかりませんでした。</p>
					<%} %>
				
		</div>
	</div>
	
</div>
<div class="inner">
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