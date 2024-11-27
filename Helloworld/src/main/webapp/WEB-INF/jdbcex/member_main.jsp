<%--
  Created by IntelliJ IDEA.
  User: gkcjd
  Date: 24. 11. 27.
  Time: 오후 7:51
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>멤버 crud 과제</title>
</head>
<body>
<h1>멤버 list</h1>
<table>
    <thead>
    <tr>
        <th>멤버번호</th>
        <th>아이디</th>
        <th>비밀번호</th>
        <th>가입일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="dto" items="${memberDTOList}">
            <tr>
                <td>${dto.mno}</td>
                <td><a href="/member/main/myPage?mno=${dto.mno}">${dto.id}</a></td>
                <td>${dto.pw}</td>
                <td>${dto.regdate}</td>
            </tr>
    </c:forEach>
    </tbody>
</table>
<form action="/member/reg" method="get">
    <button type="submit">회원 가입</button>
</form>
</body>
</html>
