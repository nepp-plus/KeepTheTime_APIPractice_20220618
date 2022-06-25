package com.neppplus.keepthetime_apipractice_20220618

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.api.APIList
import com.neppplus.keepthetime_apipractice_20220618.api.ServerAPI
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityLoginBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil
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

//        회원가입 버튼이 눌리면, 단순 화면 이동

        binding.btnSignUp.setOnClickListener {

            val myIntent = Intent(mContext, SignUpActivity::class.java)
            startActivity(myIntent)

        }


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

                        Log.d("토큰?", br.data.token) // 이 토큰값을 저장을 잘 해둬야 함.

//                        실제로 토큰값 저장
                        ContextUtil.setLoginUserToken( mContext, br.data.token )

//                        로그인한 사람의 닉네임을 토스트로.

                        Toast.makeText(mContext, "${br.data.user.nickname}님, 환영합니다!", Toast.LENGTH_SHORT).show()

//                        성공했으면 메인화면으로 이동.

                        val myIntent = Intent(mContext, MainActivity::class.java)
                        startActivity(myIntent)

                        finish()


                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }


//        체크박스가 눌리면, 그 값을 저장

        binding.autoLoginCheckBox.setOnCheckedChangeListener { compoundButton, isChecked ->

//            지금 변경된 체크 여부값을, SharedPreferences에 저장
            ContextUtil.setAutoLogin(mContext,  isChecked)

        }


    }

    override fun setValues() {

//        화면이 켜질때, 저장된 체크 여부를 반영

        binding.autoLoginCheckBox.isChecked =  ContextUtil.isAutoLogin(mContext)

    }
}