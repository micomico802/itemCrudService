<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.pojo.*"%>
<%@ page import="lib.CommonMethods"%>
<% ItemsDto editData = (ItemsDto) request.getAttribute("editData");
List<String> errorMessages = (List<String>) request.getAttribute("errorMessages");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品情報更新画面</title>
</head>
<body>
	<br> 更新したい商品情報の項目を修正してください。
	<br>
	<br>
	<br>

	<%if(!CommonMethods.isEmpty(errorMessages)){ %>
	<div class = "error__message">
	<%for(String message: errorMessages){ %>
			<p><font color="red"><%=message %></font></p>
		<% } %>
		</div>
	<% } %>

	<div class = "input__block">
		<form action="/ItemCrudService/CrudServlet" method="POST">
			ID:<%=editData.getItemId() %>(変更不可)
			<input type="hidden" name="item_id"
				value="<%=editData.getItemId() %>">
			<br><br>
			商品名: <input type="text" name="item_name"
				value="<%=editData.getItemName() %>"> <br><br>
			メーカー名：<SELECT NAME="maker_name">
				<OPTION VALUE="<%=editData.getMakerName() %>" selected>
					<%=editData.getMakerName() %></OPTION>
				<OPTION VALUE="apple">apple</OPTION>
				<OPTION VALUE="sony">sony</OPTION>
				<OPTION VALUE="nec">nec</OPTION>
				<OPTION VALUE="microsoft">microsoft</OPTION>
			</SELECT> <br><br>
			価格：<input type="text" name="price"
				value="<%=editData.getPrice() %>">
			<br> <br>
			<button name="screenTransition" value="update">更新</button>
		</form>
	</div>
	<br>
	<a href="/ItemCrudService/JSP/SearchInput.jsp">検索画面に戻る</a>

</body>
</html>