<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 11. 25.
  Time: 오전 10:13
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--jstl사용해보기 foreach var items--%>
<h1>점심 메뉴 조회 JSTL 사용</h1>

<ol>
    <c:forEach var="food" items="${foodList}">
    <a href="/food/detail?fno=${food.fno}"><li>${food.title} 가격 : ${food.price}원</li></a>
    </c:forEach>
</ol>
<a href="/food/main">
    <button>돌아가기</button>
</a>
</body>
</html>
