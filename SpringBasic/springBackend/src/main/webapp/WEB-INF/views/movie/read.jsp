<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap demo</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        영화 상세페이지
                    </div>
                    <div class="card-body">
                        <div class="input-group mb-3">
                            <span class="input-group-text">Title</span>
                            <input type="text" name="title" class="form-control" readonly
                                   value='<c:out value="${movieDTO.title}"></c:out>'>
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text">Reservation</span>
                            <input type="text" name="reservation" class="form-control" readonly
                                   value=<c:out value="${movieDTO.reservation}%"></c:out>>
                        </div>

                        <div class="input-group mb-3">
                            <span class="input-group-text">ReleaseDate</span>
                            <input type="date" name="releaseDate" class="form-control" readonly
                                   value=<c:out value="${movieDTO.releaseDate}"></c:out>>
                        </div>
                        <ul class="list-group">
                            <c:forEach var="dto" items="${pageResponseDTO.dtoList}">
                            <li class="list-group-item">${dto.userName}  ${dto.content}</li>
                            </c:forEach>
                        </ul>
                        <div class="my-4">
                            <div class="float-end">
                                <button type="button" class="btn btn-secondary">목록가기</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
<script>
    const serverValidResult = {};
    <c:forEach items="${errors}" var="error">
    serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult)
</script>

<script>
    document.querySelector(".btn-primary").addEventListener("click",
        function (e) {
            self.location = `/movie/update?mid=${movieDTO.mid}&${pageRequestDTO.link}`
                , false
        })
    document.querySelector(".btn-secondary").addEventListener("click",
        function (e) {
            self.location = "/movie/main?${pageRequestDTO.link}"
                , false
        })
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>