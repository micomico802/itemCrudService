<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
</head>
<body>
	<div>
		<h2>商品情報メニュー</h2>
		<br>
		<button name="search__button"
			onclick="location.href= '/ItemCrudService/JSP/SearchForm.jsp' ">商品検索</button>
		<br> <br>
		<button name="regist__button"
			onclick="location.href= '/ItemCrudService/JSP/RegistForm.jsp' ">新規追加</button>
		<br> <br>
	</div>
</body>
</html>