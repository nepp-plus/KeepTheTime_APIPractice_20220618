package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivitySignUpBinding

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        회원가입 화면의 가입버튼 => API 통신으로 회원가입 요청 / 응답 처리

        binding.btnSignUp.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()
            
//            받아낸 정보들을 서버의 회원가입에 요청 전달
            


        }

    }

    override fun setValues() {

    }
}