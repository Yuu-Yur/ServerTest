/*axios 비동기 처리문법을 동기화코드처럼 이용 가능한 문법
async, await 키워드를 이용해서, 비동기 처리를 동기화코드처럼 이용 가능
axios promise 타입 기반의 http 클라이언트, axios 를 이용해 RestController 에 http 요청 가능
async 비동기 함수 선언 ,함수가 항상 Promise 를 반환하도록 보장
await 함수진행중 값을 받아올 때 까지 시간이 걸리는데
이때 계속 기다리는 것이 아니라 값을 받아 올 때 까지 다른것(함수)를 할 수 있게 해줌
, 간단하게 서버에서 값이 올때까지 기다린다
처음 요청 시 서버에서 값이 '올 것' 이란 promise 타입을 반환 thread 와 비슷
js 이용 ==> static/js 에 만듬*/

// 함수 미리 정의
// async 비동기 함수 선언, Promise 타입을 반환받을 것임 함수명: get, parameter: bno
async function get(fno) {
    // await 이 뒤의 값이 올때까지 기다릴 것임, axios 를 이용해 /replies/list/${bno} 에 get 요청 보냄
    const result = await axios.get(`/replies/list/${fno}`)
    console.log(result)
}
// 페이징 된 댓글 데이터 가져오기
async function getReplyPage({fno,page,size,goLast}) {
    const result = await axios.get(`/food/rep/${fno}`,
        {params: {page,size}})
    if (goLast) {
        const last = result.data.last
        return getReplyPage({fno,last,size})
    }
    return result.data
}

// 데이터 추가하기 id 를 받음
async function register(replyObj){
    const result = await axios.post(`/food/rep/reg`, replyObj)
    return result.data
}

// 데이터 수정 id 를 받음
async function update(replyObj){
    const result = await axios.put(`/food/rep/upd`,replyObj)
    return result.data
}

// 데이터 삭제 id 를 받음
async function delRep(replyId) {
    const result = await axios.delete(`/food/rep/del/${replyId}`)
    return result.data
}

// 댓글 디테일
async function repDetail(replyId) {
    const result = await axios.get(`/food/rep/detail/${replyId}`)
    return result.data
}