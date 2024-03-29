package com.neppplus.keepthetime_apipractice_20220618.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_apipractice_20220618.EditPasswordActivity
import com.neppplus.keepthetime_apipractice_20220618.ManageFriendActivity
import com.neppplus.keepthetime_apipractice_20220618.R
import com.neppplus.keepthetime_apipractice_20220618.SplashActivity
import com.neppplus.keepthetime_apipractice_20220618.databinding.FragmentMyProfileBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileFragment : BaseFragment() {

    lateinit var binding: FragmentMyProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_profile, container, false )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnLogout.setOnClickListener {

//            로그아웃 : 기기에 저장된 토큰을 삭제. => 서버에게 요청 X. 내 안에서 해결
            ContextUtil.setLoginUserToken(mContext, "")  // 저장된 토큰이 "" 이 되도록 (로그인 안됨)

//            현재 화면 종료 > Splash 액티비티로 이동 (앱 재시작)

            val myIntent = Intent(mContext, SplashActivity::class.java)
//            인텐트로 화면 시작시에, 열려있던 모든 화면 닫으면서 실행
            myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(myIntent)



        }

        binding.btnManageFriend.setOnClickListener {

            val myIntent = Intent(mContext, ManageFriendActivity::class.java)
            startActivity(myIntent)

        }

        binding.btnEditPassword.setOnClickListener {

            val myIntent = Intent(mContext, EditPasswordActivity::class.java)
            startActivity(myIntent)

        }

    }

    override fun setValues() {

//        향후 동작 관련 코드 작성

//        1. 서버에 내 정보 요청 (GET - /user) 함수 사용 => MyProfileFragment > BaseFragment() > Fragment() 상속

        apiList.getRequestMyInfo().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {


//                   2. 서버의 응답 분석 => 프사 / 닉네임 / 이메일 반영

                    val br = response.body()!!

//                    프래그먼트도 데이터바인딩 세팅을 해야, 화면의 id 붙여둔 태그들을 가져다 사용 가능.
                    binding.txtNickname.text =  br.data.user.nickname
                    binding.txtEmail.text = br.data.user.email

                    Glide.with(mContext).load(br.data.user.profileImageURL).into( binding.imgProfile )

                }
            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })



    }


}