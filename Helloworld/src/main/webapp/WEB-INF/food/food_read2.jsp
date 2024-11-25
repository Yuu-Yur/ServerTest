<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 25.
  Time: 오전 10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>html 코드를 서블릿에서 만들어서 조회</h1>
<h2>이 방식으로는 쿼리스트링으로 정보를 전달해주기 힘듬</h2>
<h2>서블릿에서 html 코드를 만들 때 a를 붙여 인덱싱을 해줘야함</h2>
${sb}
<a href="/food/input">
    <button>돌아가기</button>
</a>
</body>
</html>
