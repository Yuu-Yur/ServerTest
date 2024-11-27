<%--
  Created by IntelliJ IDEA.
  User: gkcjd
  Date: 24. 11. 27.
  Time: 오후 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>마이 페이지</title>
</head>
<body>
<h1>${dto.id}님 환영합니다</h1>
<form action="/member/main/myPage" method="post">

    <p>
        <input type="hidden" name="mno" value="${dto.mno}">
        <input type="hidden" name="id" value="${dto.id}">
        비밀번호 <input type="text" name="pw" value="${dto.pw}">
        <input type="hidden" name="regdate" value="${dto.regdate}">
        <button type="submit" name="button" value="update">수정</button>
    </p>
    <p>
        <button type="submit" name="button" value="delete">회원 탈퇴</button>
    </p>
    <p>
        <button type="submit">돌아가기</button>
    </p>
</form>
</body>
</html>
