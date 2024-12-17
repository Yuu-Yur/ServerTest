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
<c:forEach var="dto" items="${reviewDTOList}">
    <div class="modal fade" id="modal${dto.rid}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="/movie/update" method="post"> <!--댓글 작성 폼-->
                    <input type="hidden" name="mid" value="${movieDTO.mid}">
                    <input type="hidden" name="page" value="${pageRequestDTO.page}">
                    <input type="hidden" name="size" value="${pageRequestDTO.size}">
                    <input type="hidden" name="title" value="${movieDTO.title}">
                    <input type="hidden" name="rid" value="${dto.rid}">
                    <div class="modal-header">
                        <input type="text" class="form-control" name="userName" value="${dto.userName}">
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <textarea class="form-control" name="content">${dto.content}</textarea>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">취소</button>
                        <button type="submit" class="btn btn-primary">수정</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!--수정 모달 940402 하청빈-->
</c:forEach>
<div class="container-fluid">
    <div class="row">
        <div class="row content">
            <div class="col">
                <div class="card">
                    <div class="card-header">
                        영화 상세페이지
                    </div>
                    <div class="card-body"> <!--영화 상세 정보 940402 하청빈-->
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
                        <form action="/movie/reg" method="post"> <!--댓글 작성 폼 940402 하청빈-->
                            <input type="hidden" name="mid" value="${movieDTO.mid}">
                            <input type="hidden" name="page" value="${pageRequestDTO.page}">
                            <input type="hidden" name="size" value="${pageRequestDTO.size}">
                            <input type="hidden" name="title" value="${movieDTO.title}">
                            <div class="mb-3">
                                <label for="userName" class="form-label">작성자</label>
                                <input type="text" class="form-control" id="userName" name="userName">
                            </div>
                            <div class="mb-3">
                                <label for="content" class="form-label">리뷰</label>
                                <textarea type="text" class="form-control" id="content" name="content"></textarea>
                            </div>
                            <button type="submit" class="btn btn-primary">작성</button>
                        </form>
                        <c:forEach var="dto" items="${reviewDTOList}"> <!--리뷰 정보 940402 하청빈-->
                            <div class="card" style="width: 100%">
                                <div class="card-body">
                                    <h5 class="card-title"><c:out value="${dto.userName}"/></h5>
                                    <p class="card-text"><c:out value="${dto.content}"/></p>
                                </div>
                                <form action="/movie/delete" method="post"> <!--댓글 작성 폼-->
                                    <input type="hidden" name="mid" value="${movieDTO.mid}">
                                    <input type="hidden" name="page" value="${pageRequestDTO.page}">
                                    <input type="hidden" name="size" value="${pageRequestDTO.size}">
                                    <input type="hidden" name="title" value="${movieDTO.title}">
                                    <input type="hidden" name="rid" value="${dto.rid}">
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                                            data-bs-target="#modal${dto.rid}">
                                        수정
                                    </button> <!--수정 모달-->
                                    <input type="submit" class="btn btn-danger" value="삭제"> <!--940402 하청빈-->
                                </form>
                            </div>
                        </c:forEach>
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