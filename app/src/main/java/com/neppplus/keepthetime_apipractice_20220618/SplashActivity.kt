package com.neppplus.keepthetime_apipractice_20220618

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil

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

    }
}