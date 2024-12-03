<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 12. 3.
  Time: 오전 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>reg 화면 호출</h1>
<form action="/todo/reg" method="post">
    <input type="text" name="id">
    <input type="password" name="pw">
    <button type="submit">회원 가입</button>
</form>
</body>
</html>
