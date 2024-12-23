// 페이징 된 댓글 데이터 가져오기
async function getReplyPage({mno,page,size,goLast}) {
    const result = await axios.get(`/review/${mno}`,
        {params: {page,size}})
    console.log(result)
    if (goLast) {
        const last = result.data.last
        return getReplyPage({mno,last,size})
    }
    return result.data
}

// 데이터 추가하기 id 를 받음
async function register(reviewObj){
    const result = await axios.post(`/review/register`, reviewObj)
    return result.data
}

// 데이터 수정 id 를 받음
async function update(reviewObj){
    const result = await axios.put(`/review/update`,reviewObj)
    return result.data
}

// 데이터 삭제 id 를 받음
async function delRep(rno) {
    const result = await axios.delete(`/review/delete/${rno}`)
    return result.data
}

// 댓글 디테일
async function repDetail(rno) {
    const result = await axios.get(`/review/read/${rno}`)
    return result.data
}