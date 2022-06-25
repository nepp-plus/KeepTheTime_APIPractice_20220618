package com.neppplus.keepthetime_apipractice_20220618

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.neppplus.keepthetime_apipractice_20220618.api.APIList
import com.neppplus.keepthetime_apipractice_20220618.api.ServerAPI

// 모든 화면이 공통적으로 갖는 기능 / 변수들을 물려주는 용도.

abstract class BaseActivity : AppCompatActivity() {

//    this는 가리키는 대상이 달라질 수 있다.
//    미리 다른 변수에 this를 담아두고 => 상속으로 물려주면 코딩이 더 편해진다.

    lateinit var mContext: Context // 이 화면을 미리 담아둘 변수

//    API 기능 목록도 미리 세팅해서 물려주자. (모든 화면 코딩 필요 X)
    lateinit var apiList : APIList

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this // this 값을 미리 변수에 대입해두자.

//        서버 연결 세팅 / 기능 목록 불러내기 => 상속 구현
        val myRetrofit = ServerAPI.getRetrofit()  // 서버 연결 담당 변수 불러내기
        apiList = myRetrofit.create( APIList::class.java ) // 연결된 서버의 기능 목록 세팅

    }


//    두개의 구현 내용이 없는 추상 함수를 물려주게 설정.
//    BaseActivity를 상속받는 클래스들은, 이 두개의 함수를 구현해야함.

    abstract fun setupEvents()
    abstract fun setValues()

}