package com.neppplus.keepthetime_apipractice_20220618.datas

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AppointmentData(
    val id: Int,
    @SerializedName("user_id") // 서버가 주는 이름표
    val userId: Int, // 내가 사용하고싶은 변수명  (서로 다름)
    val title: String,
    val datetime: String,
    @SerializedName("start_place")
    val startPlace: String,
    @SerializedName("start_latitude")
    val startLat: Double,
    @SerializedName("start_longitude")
    val startLng: Double,
    val place: String,
    val latitude: Double,
    val longitude: Double,
    @SerializedName("created_at")
    val createdAt: String,
    val user: UserData, // 기존에 만들어둔 UserData형태가 대응 가능
    @SerializedName("invited_friends")
    val invitedFriends : ArrayList<UserData>
) : Serializable