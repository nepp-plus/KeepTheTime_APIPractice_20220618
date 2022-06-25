package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.api.APIList
import com.neppplus.keepthetime_apipractice_20220618.api.ServerAPI
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
            ).enqueue( object : Callback<String> {

                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    서버가 어떤 내용이던, 우리에게 응답을 주는데 성공.
//                    로그인 성공 / 실패 등 결과를 받았다.

//                    최종 결과 성공 / 실패 별도로 코딩.

                    if ( response.isSuccessful ) {
//                        로그인 까지 성공
                        val bodyStr = response.body()!!  // 서버가 내려준 응답의 본문(body)을 String으로. (임시)

                        Log.d("성공응답본문", bodyStr)

                    }
                    else {

//                        아이디 / 비번 등이 틀림 : 로그인 실패

                        val errorBodyStr = response.errorBody()!!.string() // 유의 : toString() 안됨.

                        Log.d("실패응답본문", errorBodyStr)

                    }


                }

                override fun onFailure(call: Call<String>, t: Throwable) {

//                    아예 서버가 응답을 주지 못하는 경우. (물리적 연결 끊김 등)

                }

            })  // Callback : 서버에 요청을 보내고 (Call) 나서 돌아온 (back) 응답

        }

    }

    override fun setValues() {

    }
}