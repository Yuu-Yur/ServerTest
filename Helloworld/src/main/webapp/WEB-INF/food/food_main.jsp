<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 25.
  Time: 오전 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${sessionScope.user != null}">
    <h1>환영합니다. ${sessionScope.user.id} 님</h1>
    <a href="/food/main?button=signOut"><button>로그아웃</button></a>
</c:if>
<h2>점심 메뉴 소개</h2>
<form action="/food/main">
    <p>등록된 메뉴 조회 (jtsl 사용)
        <button type="submit" name="button" value="read">조회</button>
    </p>
    <p>
        제일 많이 먹은 점심 메뉴
        <button type="submit" name="button" value="favorite">보기</button>
    </p>
    <p>
        랜덤 점심 메뉴
        <button type="submit" name="button" value="random">dice roll</button>
    </p>
    <p>
        <button type="submit" name="button" value="signUp">회원 가입</button>
    </p>
    <p>
        <button type="submit" name="button" value="signIn">로그인</button>
    </p>
</form>
</body>
</html>
