<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Item,model.Item,java.util.List,DAO.ItemDAO" %>
<%  ItemDAO dao=new ItemDAO();
	List<Item> itemList=dao.ranking(); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%for(int i=0;i<3;i++){ %>
		<a href="ItemServlet?iId=<%=itemList.get(i).getIID()%>">
			<img src="${pageContext.request.contextPath }\upload\<%=itemList.get(i).getImage() %>">
		</a>
	<%} %>
</body>
</html>