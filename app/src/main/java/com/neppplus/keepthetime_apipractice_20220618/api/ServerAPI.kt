package com.neppplus.keepthetime_apipractice_20220618.api

import android.content.Context
import android.util.Log
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

//        retrofit 변수에 접근하게 도와주는 함수 생성 (private 변수이므로, 다른 클래스에서는 접근 불가)

        fun getRetrofit(context: Context) : Retrofit {

//            만약, retrofit 변수가 아직 null 이라면 => 그때만 새로 생성
//            null 이 아니라면, (if문 스킵) => 만들어져있는 retrofit 변수 활용.

            if (retrofit == null) {

//                서버 API 요청이 발생하게되면 => 요청을 가로채서 => 헤더를 자동으로 추가해주자.
//                헤더 추가가 완료되면 => 원래 하려던 요청을 이어가도록  (토큰 자동 첨부)

                val interceptor = Interceptor {
                    with(it) {

//                        기존 요청에서 토큰을 덧붙인 새 요청으로 변경
                        val newRequest =  request().newBuilder()
                            .addHeader("X-Http-Token", ContextUtil.getLoginUserToken(context))
                            .build()

//                        추가된 요청을 이어서 진행
                        proceed(newRequest)

                    }
                }

//                가로채는 도구를 활용해서, 서버 통신을 하도록 세팅.
//                서버 통신 도구 (OkHttpClient) 커스텀 제작

                val myClient = OkHttpClient.Builder().addInterceptor(interceptor).build()

//                (통신 도구를 인터셉터가 달린 도구로) 레트로핏 변수를 채우자.

                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)  // 미리 변수로 담아둔 서버 주소를 활용.
                    .client(myClient) // 인터셉터를 갖고 헤더에 토큰을 자동첨부하는 클라이언트를 사용하게.
                    .addConverterFactory( GsonConverterFactory.create() ) // 서버가 주는 데이터를 쉽게 파싱.
                    .build() // 세팅 완료.

            }

            return retrofit!!  // null 일 가능성이 제거된 채로 리턴.
        }

    }

}