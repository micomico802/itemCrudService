<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>結果</title>
</head>
<body>
	<% String message = (String)request.getAttribute("resultMessage"); %>
	<br>
	<p><%= message %></p>

	<a href = "/ItemCrudService/JSP/TopMenu.jsp">トップ画面に戻る</a>
</body>
</html>