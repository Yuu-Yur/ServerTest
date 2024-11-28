<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 25.
  Time: 오후 2:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>메뉴 등록 폼</h1>
<form action="/food/reg" method="post">
    <input type="text" name="iTitle" placeholder="메뉴명">
    <input type="number" name="iPrice" placeholder="가격">
    <button type="submit">등록</button>
</form>
<a href="/food/main">
    <button>돌아가기</button>
</a>
</body>
</html>
