package com.neppplus.keepthetime_apipractice_20220618.utils

import android.content.Context

class ContextUtil {

    companion object {

//        ContextUtil.기능() 형태로 사용하기 위함.

//        일종의 파일 명을 정해두고, 거기서 항목을 저장 / 조회.
        private val prefName = "KeepTheTimePref" // 파일명 변수로.

//        저장할 데이터의 항목 이름을 변수로 담아두자.
        private val LOGIN_USER_TOKEN = "LOGIN_USER_TOKEN" // 오탈자 방지용.
        private val AUTO_LOGIN = "AUTO_LOGIN"

//        실제로 저장 기능 / 조회 기능 (함수 2개)

        fun setLoginUserToken( context: Context, token: String ) {
//            메모장파일 (SharedPreferences) 열기
            val pref = context.getSharedPreferences( prefName, Context.MODE_PRIVATE )

            pref.edit().putString( LOGIN_USER_TOKEN,  token ).apply()

        }

        fun getLoginUserToken( context: Context ) : String {
            val pref = context.getSharedPreferences( prefName, Context.MODE_PRIVATE )

//            적혀있는 토큰값 리턴 => 저장을 한번도 안했는데, 불러낸다면? 기본값을 뭘로 할건지. (예시 : "")
            return pref.getString( LOGIN_USER_TOKEN, "" )!!

        }

        fun setAutoLogin( context: Context, isAutoLogin: Boolean ) {
            val pref = context.getSharedPreferences( prefName, Context.MODE_PRIVATE )
            pref.edit().putBoolean( AUTO_LOGIN, isAutoLogin ).apply()
        }

        fun isAutoLogin( context: Context ) : Boolean {
            val pref = context.getSharedPreferences( prefName, Context.MODE_PRIVATE )
            return pref.getBoolean( AUTO_LOGIN, false )
        }

    }

}