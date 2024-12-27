// 화면 -> 서버 , 첨부 이미지 업로드
async function uploadToServer(formObj) {
    for (const [key, value] of formObj.entries()) {
        console.log("Key:", key, "Value:", value);
    }
    const response = await axios({
        method: 'post',
        url: '/files/upload',
        data: formObj,
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
    console.log(response)
    return response.data
}

// 화면 -> 서버, 첨부 이미지 삭제
async function removeFileToServer(uuid, fileName) {
    console.log(uuid)
    console.log(fileName)

    const response = await axios.delete(
        `/files/delete/${uuid}_${fileName}`
    );
    return response.data
}