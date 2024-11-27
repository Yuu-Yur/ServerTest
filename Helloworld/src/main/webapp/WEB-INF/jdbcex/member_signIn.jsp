<%--
  Created by IntelliJ IDEA.
  User: gkcjd
  Date: 24. 11. 27.
  Time: 오후 9:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인</title>
</head>
<body>
<h1>
    로그인 화면
</h1>
<form action="/member/signIn" method="post">
    <input type="hidden" name="mno" value="${sessionScope.mno}">
    <input type="text" name="pw" placeholder="비밀번호를 입력해 주세요">
    <button type="submit">로그인</button>
</form>
</body>
</html>
