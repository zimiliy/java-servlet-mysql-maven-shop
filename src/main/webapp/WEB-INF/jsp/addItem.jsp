<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "model.Itemseve" %>

    
 <%
 String name = (String)request.getAttribute("name");
 String mainid = (String)request.getAttribute("mainid");
 String subid = (String)request.getAttribute("subid");
 String price = (String)request.getAttribute("price");
 String stock = (String)request.getAttribute("stock");
 String information = (String)request.getAttribute("information");
 String spec = (String)request.getAttribute("spec");
 String material = (String)request.getAttribute("material");
 Part part = (Part)request.getAttribute("part");
 String tag = (String)request.getAttribute("tag");
 
 String msgn = (String)request.getAttribute("msgn");
 String msgp = (String)request.getAttribute("msgp");
 String msgst = (String)request.getAttribute("msgst");
 String msgi = (String)request.getAttribute("msgi");
 String msgsp = (String)request.getAttribute("msgsp");
 String msgm = (String)request.getAttribute("msgm");
 String msgpart = (String)request.getAttribute("msgpart");
 String msgt = (String)request.getAttribute("msgt");

 if(name == null){
 	name = "";
 }
 if(price == null){
 	price = "";
 }
 if(stock == null){
 	stock = "";
 }
 if(information == null){
 	information = "";
 }
 if(spec == null){
 	spec = "";
 }
 if(material == null){
 	material = "";
 }
 %>
    
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0">
<title>商品追加 | GouMao</title>
<script type="text/javascript">
	function check(){
		var result = window.confirm("登録します。よろしいですか？");
		if(result){
			return true;
		}else{
			return false;
		}
	}
</script>

<style>
	.msg{
	color: red;
	}
</style>
</head>

<body class="addItem_body">
<jsp:include page="/header3.jsp"></jsp:include>

	<h1 class="addI_h1">商品追加</h1>
	
	<form class="addI_form" action = "Upload" method = "post" enctype="multipart/form-data" onSubmit = "return check()">
	
	<!--   名前 -->
	<div class="addI_item">
		<label class="label_left">名前</label><br>
		<input id="name" type = "text" placeholder="名前を入力" required name = "name" value = <c:out value = "${name}" />><br>
		<% if(msgn != null){ %>
		<p class = "msg"><%= msgn %></p>
		<%} %>
	</div>
	
	<!--  犬か猫 -->
	<div class="addI_item">
     <fieldset class="">
       <legend>犬 or 猫</legend><br>
		 <%if(mainid == null || mainid.equals("A")){ %>
		 <input type = "radio" name = "mainid" value = "A" checked>犬
		 <input type = "radio" name = "mainid" value = "B">猫<br>
		 <%}else{ %>
		 <input type = "radio" name = "mainid" value = "A">犬
		 <input type = "radio" name = "mainid" value = "B" checked>猫<br>
		 <%} %>
	 </fieldset>
	</div>
	
	<!-- サブカテ -->
	<div class="addI_item">
     <fieldset>
		<legend>サブカテゴリー</legend><br>
		<%if(subid == null || subid.equals("1")){ %>
		<input type = "radio" name = "subid" value = "1" checked>エサ<br>
		<input type = "radio" name = "subid" value = "2">服<br>
		<input type = "radio" name = "subid" value = "3">おもちゃ<br>
		<input type = "radio" name = "subid" value = "4">お散歩<br>
		<input type = "radio" name = "subid" value = "5">ヘルス&ケア<br>
		<input type = "radio" name = "subid" value = "6">ハウス<br>
		<input type = "radio" name = "subid" value = "7">その他<br>
		<%}else if(subid.equals("2")){ %>
		<input type = "radio" name = "subid" value = "1">エサ<br>
		<input type = "radio" name = "subid" value = "2"checked>服<br>
		<input type = "radio" name = "subid" value = "3">おもちゃ<br>
		<input type = "radio" name = "subid" value = "4">お散歩<br>
		<input type = "radio" name = "subid" value = "5">ヘルス&ケア<br>
		<input type = "radio" name = "subid" value = "6">ハウス<br>
		<input type = "radio" name = "subid" value = "7">その他<br>
		<%}else if(subid.equals("3")){ %>
		<input type = "radio" name = "subid" value = "1">エサ<br>
		<input type = "radio" name = "subid" value = "2">服<br>
		<input type = "radio" name = "subid" value = "3"checked>おもちゃ<br>
		<input type = "radio" name = "subid" value = "4">お散歩<br>
		<input type = "radio" name = "subid" value = "5">ヘルス&ケア<br>
		<input type = "radio" name = "subid" value = "6">ハウス<br>
		<input type = "radio" name = "subid" value = "7">その他<br>
		<%}else if(subid.equals("4")){ %>
		<input type = "radio" name = "subid" value = "1">エサ<br>
		<input type = "radio" name = "subid" value = "2">服<br>
		<input type = "radio" name = "subid" value = "3">おもちゃ<br>
		<input type = "radio" name = "subid" value = "4"checked>お散歩<br>
		<input type = "radio" name = "subid" value = "5">ヘルス&ケア<br>
		<input type = "radio" name = "subid" value = "6">ハウス<br>
		<input type = "radio" name = "subid" value = "7">その他<br>
		<%}else if(subid.equals("5")){ %>
		<input type = "radio" name = "subid" value = "1">エサ<br>
		<input type = "radio" name = "subid" value = "2">服<br>
		<input type = "radio" name = "subid" value = "3">おもちゃ<br>
		<input type = "radio" name = "subid" value = "4">お散歩<br>
		<input type = "radio" name = "subid" value = "5"checked>ヘルス&ケア<br>
		<input type = "radio" name = "subid" value = "6">ハウス<br>
		<input type = "radio" name = "subid" value = "7">その他<br>
		<%}else if(subid.equals("6")){ %>
		<input type = "radio" name = "subid" value = "1">エサ<br>
		<input type = "radio" name = "subid" value = "2">服<br>
		<input type = "radio" name = "subid" value = "3">おもちゃ<br>
		<input type = "radio" name = "subid" value = "4">お散歩<br>
		<input type = "radio" name = "subid" value = "5">ヘルス&ケア<br>
		<input type = "radio" name = "subid" value = "6"checked>ハウス<br>
		<input type = "radio" name = "subid" value = "7">その他<br>
		<%}else if(subid.equals("7")){ %>
		<input type = "radio" name = "subid" value = "1">エサ<br>
		<input type = "radio" name = "subid" value = "2">服<br>
		<input type = "radio" name = "subid" value = "3">おもちゃ<br>
		<input type = "radio" name = "subid" value = "4">お散歩<br>
		<input type = "radio" name = "subid" value = "5">ヘルス&ケア<br>
		<input type = "radio" name = "subid" value = "6">ハウス<br>
		<input type = "radio" name = "subid" value = "7"checked>その他<br>
		<%}%>
	 </fieldset>
	</div>
	
	<!-- 価格 -->
	<div class="addI_item">
		<label class="label_left">価格</label><br>
		<input type = "text" placeholder="価格を入力" required name = "price" value = <c:out value = "${price}" />><br>
			<% if(msgp != null){ %>
		<p class = "msg"><%= msgp %></p>
		<%} %>
	</div>

	<!-- 在庫 -->
	<div class="addI_item">
		<label class="label_left">在庫数</label><br>
		<input type = "text" placeholder="在庫を入力" required name = "stock" value =<c:out value = "${stock}" />><br>
			<% if(msgst != null){ %>
		<p class = "msg"><%= msgst %></p>
		<%} %>
	</div>
	
	<!-- 商品情報 -->
	<div class="addI_item">
		<label class="label_left">商品情報</label><br>
		<textarea name = "information" placeholder="商品情報を入力"><c:out value = "${information}" /></textarea><br>
			<% if(msgi != null){ %>
		<p class = "msg"><%= msgi %></p>
		<%} %>
	</div>
	
	<!-- サイズとか -->
	<div class="addI_item">
		<label class="label_left">サイズ・スペック</label><br>
		<textarea name = "spec" placeholder="サイズ・スペックを入力"><c:out value = "${spec}" /></textarea><br>
			<% if(msgsp != null){ %>
		<p class = "msg"><%= msgsp %></p>
		<%} %>
	</div>
	
	<!-- 原材料 -->
	<div class="addI_item">
		<label class="label_left">原材料</label><br>
		<textarea name = "material" placeholder="原材料を入力"><c:out value = "${material}" /></textarea><br>
			<% if(msgm != null){ %>
		<p class = "msg"><%= msgm %></p>
		<%} %>
	</div>
	
	<div class = "addI_item">
		<label class = "label_left">タグ</label><br>
		<textarea name = "tag" required  placeholder="タグを入力"><c:out value = "${tag}"/></textarea>
	</div>
			<% if(msgt != null){ %>
		<p class = "msg"><%= msgt %></p>
		<%} %>
	
	<!-- 画像 -->
	<div class="addI_item">
		<label class="label_left">画像</label><br>
		<input type = "file" name = "foto" value = <c:out value = "${foto}" />><br>
		<% if(msgpart != null){ %>
		<p class = "msg"><%= msgpart %></p>
		<%} %>
	</div>
	
	<div class="btns">
		<button class="addI_button" type="submit" >作成</button>
	</div>
	
	</form>
	
	<jsp:include page="/footer3.jsp"></jsp:include>
</body>
</html>