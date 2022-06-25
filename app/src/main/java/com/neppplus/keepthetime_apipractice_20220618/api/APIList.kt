package com.neppplus.keepthetime_apipractice_20220618.api

import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.PUT

// ServerAPI : https://keepthetime.xyz 서버로 갈 것이라고 관련 세팅.
// APIList : 가려는 서버의 기능 목록들을 정리하는 하위 문서.

interface APIList {

//    로그인 기능

    @FormUrlEncoded  // FormData를 사용하려면 달아줘야함.
    @POST("/user")  // POST메쏘드로 - /user 주소로 접근 : 로그인
    fun postRequestLogin(
        @Field("email") email: String, // FormData에 - email 이름표로 넣을 변수 받기.
        @Field("password")  pw: String, // FormData에 - password 이름표로 넣을 변수(pw) 받기.
    ) : Call<BasicResponse>  // 파싱 구조를 잡아둔 클래스로 대응.

//    회원가입 기능

    @FormUrlEncoded
    @PUT("/user")
    fun putRequestSignUp(
        @Field("email") email: String,
        @Field("password") pw: String,
        @Field("nick_name") nick: String,
    ) : Call<BasicResponse>

}