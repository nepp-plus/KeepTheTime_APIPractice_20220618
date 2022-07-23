package com.neppplus.keepthetime_apipractice_20220618.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserData(
    val id: Int,
    val provider: String,
    val uid: String?, // 서버가 String을 주는게 기본. null을 줄때도 있다.
    val email: String,
    @SerializedName("ready_minute") // 서버의 이름표를 이것으로 찾아달라.
    val readyMinute: Int, // 서버 이름표 : ready_minute, 앱 변수 이름 : readyMinute 처럼 두 이름표가 서로 다를때.
    @SerializedName("nick_name")
    val nickname : String,
    @SerializedName("profile_img")
    val profileImageURL: String, // 서버의 이름표와, 변수의 이름은 전혀 달라도 됨.
    @SerializedName("created_at")
    val createdAt: String,  // 차후에는 실제로 날짜 양식으로 변경 예정
    @SerializedName("updated_at")
    val updateAt: String,  // 차후에는 실제로 날짜 양식으로 변경 예정
) : Serializable
