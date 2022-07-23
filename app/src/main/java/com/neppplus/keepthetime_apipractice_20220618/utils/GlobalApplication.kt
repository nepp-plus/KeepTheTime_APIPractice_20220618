package com.neppplus.keepthetime_apipractice_20220618.utils

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "c734db44328fa286aa0611ef35786082")

    }

}