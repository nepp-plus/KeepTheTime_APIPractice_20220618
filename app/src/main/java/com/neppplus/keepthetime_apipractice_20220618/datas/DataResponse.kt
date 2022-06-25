package com.neppplus.keepthetime_apipractice_20220618.datas

// "data": 이름표로 오는 {  } 내부의 데이터를 담기 위한 클래스

data class DataResponse(
    val user: UserData, // "user": { } 를 대응하기 위한 또 다른 클래스 필요
    val token : String,  // "token" 이름표로 오는 "eycklva..." 문구 담는 공간.
)