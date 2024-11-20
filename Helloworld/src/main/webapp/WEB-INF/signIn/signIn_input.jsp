<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 20.
  Time: 오후 2:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>미니 실습) 서블릿으로 로그인 화면 접근</h1>
    <form action="/signIn/result" method="post">
        <p>
            ID
            <input type="text" name="ID">
        </p>
        <p>
            PASSWORD
            <input type="password" name="Password">
        </p>
        <button type="submit">SignIn</button>
    </form>
</body>
</html>
