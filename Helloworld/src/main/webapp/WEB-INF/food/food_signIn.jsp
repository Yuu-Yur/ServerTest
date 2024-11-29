<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 28.
  Time: 오전 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 페이지</title>
</head>
<body>
<h1>
    로그인 페이지
</h1>
<form action="/food/signIn" method="post">
    <input type="text" name="id" placeholder="아이디를 입력해 주세요">
    <input type="password" name="pw" placeholder="비밀번호를 입력해 주세요">
    <input type="checkbox" name="autoSI">
    <button type="submit">로그인</button>
</form>
<a href="/food/main">
    <button>돌아가기</button>
</a>
</body>
</html>
