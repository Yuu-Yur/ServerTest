/*axios 비동기 처리문법을 동기화코드처럼 이용 가능한 문법
async, await 키워드를 이용해서, 비동기 처리를 동기화코드처럼 이용 가능
axios promise 타입 기반의 http 클라이언트, axios 를 이용해 RestController 에 http 요청 가능
async 비동기 함수 선언 ,함수가 항상 Promise 를 반환하도록 보장
await 함수진행중 값을 받아올 때 까지 시간이 걸리는데
이때 계속 기다리는 것이 아니라 값을 받아 올 때 까지 다른것(함수)를 할 수 있게 해줌
, 간단하게 서버에서 값이 올때까지 기다린다
처음 요청 시 서버에서 값이 '올 것' 이란 promise 타입을 반환 thread 와 비슷
js 이용 ==> static/js 에 만듬
이하에서 restController 에 요청 해 받아오는 값(result 에 할당하는)은 JSON 형태이다.
이것을 그리고 이 함수뒤에 붙이는 .then.catch 는 이 JSON 을 받아오느냐 못받아오느냐로 나뉘고
.then() 에서 람다식에 쓰는 변수는 이 JSON 을 가르킨다 또한 JSON 의 키를 불러 값을 꺼낸다.
ex) .then(json -> console.log(json.key))
이하에서 restController 로 요청하는 것은 요청하는 http 주소(`` 사용)와
parameter(restController parameter 가 받음 )이다. parameter 가 하나일땐 (주소,parameter)
여럿일 땐 (주소,{params:{param1,param2,...}} 이때 쓰는 param1... 은 이 함수의 parameter이며
restController 가 받을 parameter 이다.
이 parameter 들을 보낼 때 주의할 점이 개개의 변수로 보내는것은 괜찮지만 클래스 형태로 보내고 싶을 때
클래스를 쓰면 안된다는 것이다. 변수는 역직렬화를 restController 의 parameter 에 자동으로 들어가며
이 변수들을 배열로 만들어 보내도 작동한다. 하지만 클래스는 역직렬화 할 수 없기에 Obj 에 JSON 형태를
만들어 보내 역직렬화한 후 restController 의 parameter 에 들어간다.
위의 restController 의 parameter 에 자동으로 들어가는것을 mapping 이라 하며 Jackson 이 해준다.
기준은 js 에서 요청할 때 쓴 parameter 의 이름이 기준이며 이것과 '비슷한' restController 의 parameter
에 mapping 해준다.
즉 기본 자료형이나 JSON 형태를 만들어 보내야 한다는 것이며 비슷한 수준까진 자동으로 매핑해준다.*/


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
    console.log(result)
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