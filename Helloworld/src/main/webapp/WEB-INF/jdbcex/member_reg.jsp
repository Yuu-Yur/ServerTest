<%--
  Created by IntelliJ IDEA.
  User: gkcjd
  Date: 24. 11. 27.
  Time: 오후 8:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원 가입</title>
</head>
<body>
<h1>회원 가입</h1>
<form action="/member/reg" method="post">
    <input type="text" name="id" placeholder="아이디">
    <input type="password" name="pw" placeholder="비밀번호">
    <button type="submit">가입하기</button>
</form>
</body>
</html>
