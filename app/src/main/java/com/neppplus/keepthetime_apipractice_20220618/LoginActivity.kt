package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.api.APIList
import com.neppplus.keepthetime_apipractice_20220618.api.ServerAPI
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityLoginBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
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
            ).enqueue( object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {
//                        로그인에 최종 성공이라면 => 응답 본문이, BasicResponse 형태로 파싱되어 돌아옴.

                        val br = response.body()!!

//                        Toast.makeText(mContext, br.message, Toast.LENGTH_SHORT).show()

//                        기본응답의 > data 내부의 > token값을 로그로.

                        Log.d("토큰?", br.data.token)

//                        로그인한 사람의 닉네임을 토스트로.

                        Toast.makeText(mContext, "${br.data.user.nickname}님, 환영합니다!", Toast.LENGTH_SHORT).show()


                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }

    }

    override fun setValues() {

    }
}