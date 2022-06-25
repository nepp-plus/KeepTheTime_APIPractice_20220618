package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.api.APIList
import com.neppplus.keepthetime_apipractice_20220618.api.ServerAPI
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityLoginBinding
import retrofit2.Retrofit

class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnLogin.setOnClickListener {

//            앱에서 입력한 Email / PW 변수에 저장

            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            
//            서버에 로그인 (keepthetime.xyz/user => POST로 접근 => 이메일, 비번 전달) 해야함

//            BaseActivity가 물려주는 서버의 기능 목록 활용.

            apiList.postRequestLogin(
                inputEmail,
                inputPw
            )

        }

    }

    override fun setValues() {

    }
}