<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 25.
  Time: 오전 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>점심 메뉴 소개</h1>
<form action="/food/main">
    <p>등록된 메뉴 조회 (jtsl 사용)
        <button type="submit" name="button" value="read">조회</button>
    </p>
    <p>
        메뉴 등록
        <button type="submit" name="button" value="register">등록</button>
    </p>
    <p>
        제일 많이 먹은 점심 메뉴
        <button type="submit" name="button" value="favorite">보기</button>
    </p>
    <p>
        랜덤 점심 메뉴
        <button type="submit" name="button" value="random">dice roll</button>
    </p>
</form>
</body>
</html>
