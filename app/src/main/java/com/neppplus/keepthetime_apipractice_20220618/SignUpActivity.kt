package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivitySignUpBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : BaseActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnCheckEmail.setOnClickListener {

//            입력한 이메일을 > 중복 확인 API에 넘겨서 > 응답 확인

            val inputEmail = binding.edtEmail.text.toString()

//            GET - /user/check API를 호출해서, 중복 여부 검사하자.

            apiList.getRequestUserCheck(
                "EMAIL",
                inputEmail
            ).enqueue( object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
//                    응답이 성공 / 실패냐만 구별.

                    if (response.isSuccessful) {
                        binding.txtEmailCheckResult.text = "사용해도 좋은 이메일입니다."
                    }
                    else {
                        binding.txtEmailCheckResult.text = "중복된 이메일 입니다. 다른 이메일을 사용해주세요."
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }

//        회원가입 화면의 가입버튼 => API 통신으로 회원가입 요청 / 응답 처리

        binding.btnSignUp.setOnClickListener {

            val inputEmail = binding.edtEmail.text.toString()
            val inputPw = binding.edtPassword.text.toString()
            val inputNickname = binding.edtNickname.text.toString()
            
//            받아낸 정보들을 서버의 회원가입에 요청 전달

            apiList.putRequestSignUp(
                inputEmail,
                inputPw,
                inputNickname
            ).enqueue( object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {
                        Toast.makeText(mContext, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT).show()
                    }
                    else {
                        Toast.makeText(mContext, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show()
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