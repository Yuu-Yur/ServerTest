<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 21.
  Time: 오전 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>도서 수정</h2>
<form action="/book/update" method="post">
    <input type="text" name="uBookName">
    <button type="submit">수정</button>
</form>
</body>
</html>
