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
<h1>도서 목록</h1>
<a href="/book/update">
    <button>수정</button>
</a>
<a href="/book/register">
    <button>등록</button>
</a>
<%--<a href="/book/search">--%>
<%--    <button>검색</button>--%>
<%--</a>--%>
<form action="/book/delete" method="post">
    <input type="text" name="dBookName">
    <button type="submit">삭제</button>
</form>

<form action="/book/read">
    <input type="text" name="bookId" placeholder="0~9를 입력해주세요">
    <button type="submit">검색</button>
</form>

</body>
</html>
