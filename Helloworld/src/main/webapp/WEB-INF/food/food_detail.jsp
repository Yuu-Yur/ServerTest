<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 25.
  Time: 오전 11:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>오늘의 추천 점심 메뉴는 ${foodVO.title}</h1>
<h2>가격은 ${foodVO.price}</h2>
<h2>지금까지 ${foodVO.counter} 번 먹었습니다</h2>

<form action="/food/detail" method="post">
    <fieldset>
        <legend>메뉴 수정하기</legend>
            <input type="text" name="iFno" value="${foodVO.fno}" readonly>
            <input type="text" name="iTitle" value="${foodVO.title}">
            <input type="number" name="iPrice" value="${foodVO.price}">
            <input type="number" name="iCounter" value="${foodVO.counter}">
            <button type="submit" name = "button" value = "update">수정</button>
            <button type="submit" name = "button" value = "delete">삭제</button>
    </fieldset>
</form>
<a href="/food/input">
    <button>돌아가기</button>
</a>
</body>
</html>
