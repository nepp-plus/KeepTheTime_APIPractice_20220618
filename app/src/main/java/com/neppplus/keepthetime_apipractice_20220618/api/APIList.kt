package com.neppplus.keepthetime_apipractice_20220618.api

import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import retrofit2.Call
import retrofit2.http.*

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

//    중복검사 기능

    @GET("/user/check")
    fun getRequestUserCheck(
        @Query("type") type: String,
        @Query("value") value: String,
    ) : Call<BasicResponse>

//    내 정보 받아오기 (연습용)

    @GET("/user")
    fun getRequestMyInfo(
        @Header("X-Http-Token") token:String,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @PATCH("/user/password")
    fun patchRequestEditPassword(
        @Header("X-Http-Token") token: String,
        @Field("current_password") currentPw: String,
        @Field("new_password") newPw: String
    ) : Call<BasicResponse>

    @GET("/search/user")
    fun getRequestSearchUser(
        @Header("X-Http-Token") token: String,
        @Query("nickname") nick: String,
    ) : Call<BasicResponse>

    @FormUrlEncoded
    @POST("/appointment")
    fun postRequestAddAppointment(
        @Header("X-Http-Token") token: String,
        @Field("title") title: String,
        @Field("datetime") datetime: String,
        @Field("place") place: String,
        @Field("latitude") lat: Double,
        @Field("longitude") lng: Double,
    ) : Call<BasicResponse>

}