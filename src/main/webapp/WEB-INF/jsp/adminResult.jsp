<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,java.util.List" %>
<%List<Item> itemList=(List)session.getAttribute("itemList"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理画面 | GouMao</title>
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
</head>
<body class="addItem_body">
	<jsp:include page="/header3.jsp"></jsp:include>
	
	<h1 class="addI_h1">検索結果画面</h1>
	
	<form class="adminS_form" action="AdminSeaechServlet" method="post" class="prd_search_box flex">
	<input type="text" name="keyword" class="prd_search_keyword" placeholder="商品名、キーワードを入力" />
	<input type="submit" class="prd_search_btn font-jp2" value="検索" />
	<p class="adminS_p">
		<input type="radio" name="mainId" value="A"/>犬
		<input type="radio" name="mainId" value="B"/>猫
	</p>
	</form>
	
	<form action="StockEditServlet" method="post">
	<div class="adminR_block">
	<%for(int i=0;i<itemList.size();i++){
		Item item=itemList.get(i);%>
		<div class="adminR_item">
			<div class="adminR_img">
				<img src= "${pageContext.request.contextPath }\upload\<%= item.getImage() %>">
			</div>
			<div class="adminR_text">
				<c:out value="<%=item.getName() %>"/><br>
				<% String ip = String.format("%,d",item.getPrice());%>
			   &yen;<c:out value="<%=ip %>"/><br>
			   <% String is = String.format("%,d",item.getStock());%>
			   在庫数：<c:out value="<%=is %>"/><br>
				<br>
				<div class="adminR_a">
					<a href="EditItemServlet?iId=<%=item.getIID() %>" >変更</a> |
					<a href="DeleteItemServlet?iId=<%=item.getIID() %>"onclick = "return check()">削除</a><br>
				</div>
				在庫追加
				
					<input type="number" name="<%=i %>">
					<input type="hidden" name="<%=item.getIID() %>" value="<%=item.getIID() %>">
					<input type="submit" value="追加">
				
			</div>
		</div>
	<% } %>
	</div>
	</form>
	
	<jsp:include page="/footer3.jsp"></jsp:include>
</body>
</html>