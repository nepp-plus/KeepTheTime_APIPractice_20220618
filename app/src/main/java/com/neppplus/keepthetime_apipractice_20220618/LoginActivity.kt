package com.neppplus.keepthetime_apipractice_20220618

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.kakao.sdk.user.UserApiClient
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

//        카카오 로그인이 눌리면 => 카톡 서버에서 로그인 시도

        binding.btnKakaoLogin.setOnClickListener {

//            질문 - 카카오톡이 설치되어있나? 되어있다면 실제 카톡 앱으로 로그인, 아니면 임시방편으로 로그인

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(mContext)) {
//                카톡 로그인 가능

                UserApiClient.instance.loginWithKakaoTalk(mContext) {token, error ->

                    Log.d("카톡로그인", "실제 카톡앱으로 로그인")
                    if (error == null) {
//                        에러가 없으면 내정보 카카오에서 가져오기
                        getMyKakaoInfo()
                    }
                }

            }
            else {
//                카톡 로그인 불가 -> 임시방편 활용
                UserApiClient.instance.loginWithKakaoAccount(mContext) {token, error ->

                    Log.d("카톡로그인", "임시방편으로 활용")

                    if (error == null) {
//                        에러가 없으면 내정보 카카오에서 가져오기
                        getMyKakaoInfo()
                    }

                }
            }


        }

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

//    카카오톡 서버에서 내 정보 받아오는 함수

    fun getMyKakaoInfo() {

        UserApiClient.instance.me { user, error ->

            if (error == null) {
//                사용자 정보 활용
                user?.let {

//                    사용자 정보를 잘 받아온경우
                    Log.d("카톡로그인", "고유번호 : ${user.id}")
                    Log.d("카톡로그인", "닉네임 : ${user.kakaoAccount!!.profile!!.nickname}")

                }
            }
            else {
//                에러 발생
                Toast.makeText(mContext, "카카오 서버에서 내 정보를 가져오지 못했습니다.", Toast.LENGTH_SHORT).show()
            }

        }

    }

}