package com.neppplus.keepthetime_apipractice_20220618.datas

data class BasicResponse(
    val code: Int,  // code라는 이름표로, 200등의 정수.
    val message: String, // message 라는 이름표로, "로그인 성공" 등의 문구.
    val data: DataResponse // data라는 이름표로, { } 가 내려옴. => DataResponse 클래스가 대응.
)
