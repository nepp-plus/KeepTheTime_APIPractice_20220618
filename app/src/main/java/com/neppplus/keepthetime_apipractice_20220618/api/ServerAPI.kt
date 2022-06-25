package com.neppplus.keepthetime_apipractice_20220618.api

import android.util.Log
import retrofit2.Retrofit

class ServerAPI {

//    companion object { } 내부에 적는 함수 / 변수들은,
//    클래스이름.함수() 형태로, 인스턴스화를 거치지 않고서도 사용 가능하게 됨.
//    JAVA : static 개념에 대응됨.


    companion object {

//        Retrofit 자료형 변수 => 실제 서버 연결 담당.
//        서버연결 담당객체는, 하나만 만들고 => 여러 화면이 공유하자. (메모리 절약)
//        하나가 만들어져 있다면, 그냥 사용 Vs. 아직 안만들어져 있다면 => 그때만 만들자.
//        Singleton 패턴 활용

        private var retrofit : Retrofit? = null  // 처음에는 아직 안 만든 상태로 두자.

//        API 서버의 주소 (기초 주소)?  https://keepthetime.xyz  => 이 내부의 여러 기능 활용
        private val BASE_URL = "https://keepthetime.xyz"

//        위의 두 변수는, 이 클래스에서 세팅을 위해서만 활용. (외부 노출 X)

    }

}