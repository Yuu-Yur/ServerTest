<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오전 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>도서 검색</h2>
<form action="/book/search" method="post">
    <input type="text" name="sBookName">
    <button type="submit">검색</button>
</form>
</body>
</html>
