<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 28.
  Time: 오전 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입 페이지</title>
</head>
<body>
<h1>회원 가입</h1>
<form action="/food/main" method="post">
    <input type="text" name="id" placeholder="아이디">
    <input type="password" name="pw" placeholder="비밀번호">
    <button type="submit" name="button" value="signUp">회원 가입</button>
</form>
</body>
</html>
