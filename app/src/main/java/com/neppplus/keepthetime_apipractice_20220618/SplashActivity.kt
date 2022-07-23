package com.neppplus.keepthetime_apipractice_20220618

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil
import java.security.MessageDigest

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

//        키해쉬값  로그 찍기
        getKeyHash()

//        2.5초 후에 자동로그인 가능 여부 판단

        val myHandler = Handler(Looper.getMainLooper())

        myHandler.postDelayed( {

//        자동 로그인을 할 상황인지 파악해보자
//        해야 한다 기준 : 자동로그인 사용 체크? + 이전에 로그인 성공 했는가? (저장된 토큰 : 빈칸 아니어야함) => 둘 다 만족 : AND 조건
//        해야한다 => 바로 메인으로 / 자동로그인 X => 로그인 화면으로

            if ( ContextUtil.isAutoLogin(mContext) && ContextUtil.getLoginUserToken(mContext) != "" ) {

                val myIntent = Intent(mContext, MainActivity::class.java)
                startActivity(myIntent)
                finish()

            }
            else {
                val myIntent = Intent(mContext, LoginActivity::class.java)
                startActivity(myIntent)
                finish()
            }

        }, 2500 )




    }

    fun getKeyHash() {

        val info = packageManager.getPackageInfo(
            "com.neppplus.keepthetime_apipractice_20220618",
            PackageManager.GET_SIGNATURES
        )
        for (signature in info.signatures) {
            val md: MessageDigest = MessageDigest.getInstance("SHA")
            md.update(signature.toByteArray())
            Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
        }
    }

}