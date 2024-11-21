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
<h2>신간 도서 등록</h2>
<form action="/book/register" method="post">
    <input type="text" name="bookName">
    <button type="submit">등록하기</button>
</form>
</body>
</html>
