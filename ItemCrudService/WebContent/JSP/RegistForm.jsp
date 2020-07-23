<%@page import="lib.CommonMethods"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="model.pojo.*"%>
<%@ page import="lib.CommonMethods"%>
<%List<String> errorMessages = (List<String>) request.getAttribute("errorMessages");

InputDataDto dto = new InputDataDto();
if(!CommonMethods.isEmpty(request.getAttribute("inputData"))){
	dto = (InputDataDto) request.getAttribute("inputData");
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録画面</title>
</head>
<body>
<div>
	<h2>商品登録</h2>
	<p>必要項目をご入力下さい</p>
</div>

<%if(!CommonMethods.isEmpty(errorMessages)){ %>
	<div class = "error__message">
	<%for(String message: errorMessages){ %>
			<p><font color="red"><%=message %></font></p>
		<% } %>
		</div>
	<% } %>

	<div class = "input__block">
		<form action="/ItemCrudService/CrudServlet" method="post">
			商品名: <input type="text" name="item_name" value="<%=dto.getItemName() %>">
			<br><br>
			メーカー名：<SELECT NAME="maker_name">
				<OPTION VALUE="<%=dto.getMakerName() %>" selected>
					<%=dto.getMakerName() %></OPTION>
				<OPTION VALUE="apple">apple</OPTION>
				<OPTION VALUE="sony">sony</OPTION>
				<OPTION VALUE="nec">nec</OPTION>
				<OPTION VALUE="microsoft">microsoft</OPTION>
			</SELECT>
			<br><br>
			価格：<input type="text" name="price" value="<%=dto.getPrice() %>">
			<br> <br>
		<button name="screenTransition" value="insert">登録</button>
	    </form>
	</div>

	<br><br>
	<a href="/ItemCrudService/JSP/TopMenu.jsp">トップ画面に戻る</a>
</body>
</html>