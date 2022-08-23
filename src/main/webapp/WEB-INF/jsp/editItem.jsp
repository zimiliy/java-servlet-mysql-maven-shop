<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "model.Itemseve" %>

 <%
 String name = (String)request.getAttribute("name");
 String price = (String)request.getAttribute("price");
 String stock = (String)request.getAttribute("stock");
 String information = (String)request.getAttribute("information");
 String spec = (String)request.getAttribute("spec");
 String material = (String)request.getAttribute("material");
 Part part = (Part)request.getAttribute("part");
 String iId=(String)request.getAttribute("iId");
 String tag = (String)request.getAttribute("tag");
 
 String msgn = (String)request.getAttribute("msgn");
 String msgp = (String)request.getAttribute("msgp");
 String msgst = (String)request.getAttribute("msgst");
 String msgi = (String)request.getAttribute("msgi");
 String msgsp = (String)request.getAttribute("msgsp");
 String msgm = (String)request.getAttribute("msgm");
 String msgpart = (String)request.getAttribute("msgpart");
 
 if(name == null){
	 	name = "";
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
<title>商品変更 | GouMao</title>
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
</head>
<body class="addItem_body">
<jsp:include page="/header3.jsp"></jsp:include>

	<h1 class="addI_h1">商品変更</h1>
	
		<form class="addI_form" action = "EditItemServlet" method = "post" enctype="multipart/form-data" onSubmit = "return check()">
	
	<!--   名前 -->
	<div class="addI_item">
		<label class="label_left">名前</label><br>
	<input type = "text" placeholder="名前を入力" required name = "name" value = "<c:out value = "${name}" />"><br>
	<%if(msgn != null){ %>
	<p><%= msgn %></p>
	<%} %> 
	</div>
	
	<!-- 価格 -->
	<div class="addI_item">
		<label class="label_left">価格</label><br>
	<input type = "text" placeholder="価格を入力" required name = "price"  value = "<c:out value = "${price}" />"><br>
		<%if(msgp != null){ %>
	<p><%= msgp %></p>
	<%} %> 
	</div>
	
	<!-- 在庫 -->
	<div class="addI_item">
		<label class="label_left">在庫数</label><br>
	<input type = "text" placeholder="在庫を入力" required name = "stock"  value = "<c:out value = "${stock}" />"><br>
		<%if(msgst != null){ %>
	<p><%= msgst %></p>
	<%} %> 
	</div>
	
	<!-- 商品情報 -->
	<div class="addI_item">
		<label class="label_left">商品情報</label><br>
	<textarea placeholder="商品情報を入力"  name = "information"  ><c:out value = "${information}" /></textarea><br>
	</div>
	
	<!-- サイズとか -->
	<div class="addI_item">
		<label class="label_left">サイズ・スペック</label><br>
	<textarea  placeholder="サイズ・スペックを入力"  name = "spec" ><c:out value = "${spec}"/></textarea><br>
	</div>
	
	<!-- 原材料 -->
	<div class="addI_item">
		<label class="label_left">原材料</label><br>
	<textarea  placeholder="原材料を入力"  name = "material" ><c:out value = "${material}" /></textarea><br>
	</div>
	
	<div class = "addI_item">
		<label class = "label_left">タグ</label><br>
		<textarea placeholder= "タグを入力" name = "tag"><c:out value = "${tag}"/></textarea><br>
	</div>
	
	
	<!-- 画像 -->
	<div class="addI_item">
		<label class="label_left">画像</label><br>
	<input type = "file" name = "foto" value = "<c:out value = "${pageContext.request.contextPath }\upload\${part.getSubmittedFileName()}" />"><br>
	</div>
	
	<input type = "hidden" name = "iId" value = "${iId}">
	<div class="btns">
		<button class="addI_button" type="submit" >変更</button>
	</div>
	
	</form>

	<jsp:include page="/footer3.jsp"></jsp:include>
</body>
</html>