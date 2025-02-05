<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 24. 12. 3.
  Time: 오전 9:39
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<h1>reg 화면 호출</h1>
<div class="container-fluid">
    <div class="row">
        <!--        <h1>Header</h1>-->
        <div class="row">
            <div class="col">
                <nav class="navbar navbar-expand-lg bg-body-tertiary">
                    <div class="container-fluid">
                        <a class="navbar-brand" href="#">Navbar</a>
                        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                        </button>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="#">Home</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="#">Link</a>
                                </li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                                       aria-expanded="false">
                                        Dropdown
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a class="dropdown-item" href="#">Action</a></li>
                                        <li><a class="dropdown-item" href="#">Another action</a></li>
                                        <li>
                                            <hr class="dropdown-divider">
                                        </li>
                                        <li><a class="dropdown-item" href="#">Something else here</a></li>
                                    </ul>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link disabled" aria-disabled="true">Disabled</a>
                                </li>
                            </ul>
                            <form class="d-flex" role="search">
                                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                                <button class="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>
                <div class="row content">
                    <div class="col">
                        <!--                        카드 시작-->
                        <div class="card">
                            <div class="card-header">
                                Featured
                            </div>
                            <div class="card-body">
                                <form action="/todo/reg" method="post">
                                    <div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text">Title</span>
                                            <input type="text" name="title" class="form-control" placeholder="해야할 일">
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text">DueDate</span>
                                            <input type="date" name="dueDate" class="form-control" value="2024-12-03">
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="input-group-text">Writer</span>
                                            <input type="text" name="writer" class="form-control" placeholder="작성자">
                                        </div>
                                        <div class="input-group mb-3">
                                            <span class="form-check-label">Finished</span>
                                            <input type="checkbox" name="finished" class="check-input">
                                        </div>
                                    </div>
                                    <div class="my-4">
                                        <div class="float-end">
                                            <button type="submit" class="btn btn-primary">작성</button>
                                            <button type="reset" class="btn btn-secondary">초기화</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row content">
        <h1>Content</h1>
    </div>
    <div class="row footer">
        <!--        <h1>Footer</h1>-->
        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>
</div>

<%--입력 폼에 관련 유효성 체크, 서버로부터  erros 키로 값을 받아오면, --%>
<%--자바스크립 콘솔에 임시 출력.--%>
<script>
    const serverValidResult = {    };
    // jstl , 반복문으로, 서버로부터 넘어온 여러 에러 종류가 많습니다.
    //     하나씩 꺼내서, 출력하는 용도.,
    <c:forEach var="error" items="${errors.getAllErrors()}">
    serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult)
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>



</body>
</html>
