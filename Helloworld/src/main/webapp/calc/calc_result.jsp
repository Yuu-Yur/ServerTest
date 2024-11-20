<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 20.
  Time: 오후 2:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--    ${}표기는 서버에서 넘어온 데이터를 조회할 때 사용하는 EL형식 다른 프론트 엔진에서도 자주 사용--%>
    <h1>넘어온 데이터 확인 num1 : ${param.num1}</h1>
    <h1>넘어온 데이터 확인 num2 : ${param.num2}</h1>
</body>
</html>
