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
<div class="modal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Modal title</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>Modal body text goes here.</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>
<%--입력 폼에 관련 유효성 체크, 서버로부터  erros 키로 값을 받아오면, --%>
<%--자바스크립 콘솔에 임시 출력.--%>
<script>
    const serverValidResult = {};
    // jstl , 반복문으로, 서버로부터 넘어온 여러 에러 종류가 많습니다.
    //     하나씩 꺼내서, 출력하는 용도.,
    <c:forEach items="${errors}" var="error">
    serverValidResult['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult)

    const serverValidResult2 = {};
    // jstl , 반복문으로, 서버로부터 넘어온 여러 에러 종류가 많습니다.
    //     하나씩 꺼내서, 출력하는 용도.,
    <c:forEach items="${errors2}" var="error">
    serverValidResult2['${error.getField()}'] = '${error.defaultMessage}'
    </c:forEach>
    console.log(serverValidResult2)
</script>

<%--목록가기 및 수정폼 가기 이벤트 리스너--%>
<script>
    // 수정폼
    <%--document.querySelector(".btn-primary").addEventListener("click",--%>
    <%--    function (e) {--%>
    <%--        // 수정폼으로 가야함. 그러면, 필요한 준비물 tno 번호가 필요함--%>
    <%--        self.location = "/movie/update?tno=" +${todoDTO.tno}--%>
    <%--            , false--%>
    <%--    })--%>
    // 목록
    document.querySelector(".btn-secondary").addEventListener("click",
        function (e) {
            // 수정폼으로 가야함. 그러면, 필요한 준비물 tno 번호가 필요함
            self.location = "/movie/main?${pageRequestDTO.link}"
                , false
        })

    // 삭제기능.
    document.querySelector(".btn-danger").addEventListener("click",
        function (e) {
            // 폼에서, 필요한  tno가져오기.
            const formObj = document.querySelector("form")

            // 기본 폼 방식으로 전달하는 기본 기능 제거 하고,
            e.preventDefault()
            e.stopPropagation() // 상위 태그로 전파 방지

            // 삭제시 포스트로, tno 번호를 전달하는 방식.
            // formObj , 원래 action: /todo/update
            // 속성을 변경 가능해서, 임시로, 삭제 url 변경.
            // 삭제 후에도, 검색 내역을 유지하기.
            formObj.action = "/movie/delete?${pageRequestDTO.link}"
            formObj.method = "post"
            // todoDTO 모든 멤버가 같이 전달됨.
            // tno, title, dueDate, finished, writer
            formObj.submit()
        }, false)

    // 방법2
    //수정 로직 처리
    document.querySelector(".btn-primary").addEventListener("click",
        function (e) {
            // 폼에서, 필요한  tno가져오기.
            const formObj = document.querySelector("form")

            // 기본 폼 방식으로 전달하는 기본 기능 제거 하고,
            e.preventDefault()
            e.stopPropagation() // 상위 태그로 전파 방지

            formObj.action = "/movie/update?${pageRequestDTO.link}"
            formObj.method = "post"
            // todoDTO 모든 멤버가 같이 전달됨.
            // tno, title, dueDate, finished, writer
            formObj.submit()
        }, false)
</script>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>