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
                    <div class="card-body">  <!--컨텐츠-->
                        <div>
                            <a href="/movie/main?released=true"> <!--검색,필터 940402 하청빈-->
                                <button type="button" class="btn ${pageRequestDTO.released ? "btn-primary" : "btn-secondary" } ">상영중인 영화</button>
                            </a>
                            <a href="/movie/main?released=false">
                                <button type="button" class="btn ${pageRequestDTO.released ? "btn-secondary" : "btn-primary" } ">상영 예정 영화</button>
                            </a>
                        </div>
                        <div>
                            <h5 class="card-title">검색</h5>
                            <form action="/movie/main" method="get">
                                <input type="hidden" name="released" value="${pageRequestDTO.released}">
                                <div class="mb-3">
                                    <input type="hidden" name="types" value="t">
                                    <input type="text" name="keyword" class="form-control" value="${pageRequestDTO.keyword}">
                                </div>
                                <div class="input-group mb-3 dueDateDiv">
                                    <label for="fromDate">개봉 시기</label>
                                    <input id="fromDate" type="date" name="from" class="form-control" value="${pageRequestDTO.from}">
                                    <input type="date" name="to" class="form-control" value="${pageRequestDTO.to}">
                                </div>
                                <div class="input-group mb-3">
                                    <div class="float-end">
                                        <button class="btn btn-primary" type="submit">검색</button>
                                        <button class="btn btn-secondary clearBtn" type="reset">초기화</button>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <table class="table">
                            <thead>
                            <tr>
                                <th scope="col">Mid</th>
                                <th scope="col">Title</th>
                                <th scope="col">ReservationRate</th>
                                <th scope="col">ReleaseDate</th>
                            </tr>
                            </thead>
                            <tbody>

                            <c:forEach var="dto" items="${pageResponseDTO.dtoList}">
                                <tr>
                                    <th scope="row"><c:out value="${dto.mid}"></c:out></th>
                                    <td><a href="/movie/read?mid=${dto.mid}&${pageRequestDTO.link}"
                                           class="text-decoration-none">
                                        <c:out value="${dto.title}"></c:out>
                                    </a></td>
                                    <td><c:out value="${dto.reservation}%"></c:out></td>
                                    <td><c:out value="${dto.releaseDate}"></c:out></td>
                                </tr>
                            </c:forEach>

                            </tbody>
                        </table>


                        <div class="float-end">
                            <ul class="pagination">
                                <li class="page-item ${pageResponseDTO.prev ? "" : "disabled"} ">
                                    <a class="page-link" data-num="${pageResponseDTO.page - 1}" size="${pageResponseDTO.size}">Previous</a>
                                </li>

                                <c:forEach begin="${pageResponseDTO.start}"
                                           end="${pageResponseDTO.end}" var="num">

                                    <li class="page-item ${pageResponseDTO.page == num ? "active" : ""}"
                                    ><a class="page-link" data-num="${num}" size="${pageResponseDTO.size}">${num}</a></li>
                                </c:forEach>


                                <li class="page-item ${pageResponseDTO.next ? "" : "disabled"}">
                                    <a class="page-link" data-num="${pageResponseDTO.end + 1}" size="${pageResponseDTO.size}">Next</a>
                                </li>
                            </ul>
                        </div> <!--페이지네이션 940402 하청빈-->

                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row footer">
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

<script type="text/javascript">

    document.querySelector(".pagination").addEventListener("click",
        function (e) {
            e.preventDefault()
            e.stopPropagation()

            const target = e.target
            if (target.tagName !== "A") {
                return
            }
            const num = target.getAttribute("data-num")
            const size = ${pageResponseDTO.size}
            const formObj = document.querySelector("form")

            formObj.innerHTML += `<input type='hidden' name='page' value='\${num}'>`
            formObj.innerHTML += `<input type='hidden' name='size' value='\${size}'>`
            formObj.submit()


        }, false)

    document.querySelector(".clearBtn").addEventListener("click",
        function (e) {
            e.preventDefault();
            e.stopPropagation();

            self.location = "/movie/main"
        }, false)


</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>