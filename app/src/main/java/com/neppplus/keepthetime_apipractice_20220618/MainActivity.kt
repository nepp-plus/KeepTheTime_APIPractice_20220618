package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityMainBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

//   (activity_main) xml의 요소를 데이터바인딩을 통해 끌어오는 binding 변수 생성.
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding 변수 대입 : setContentView를 DataBindingUtil을 통해서 실행.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {


    }

    override fun setValues() {

//        화면에 들어오면, /user - GET으로 접근
//        내 정보를 가져와서, 프사 / 닉네임 표시.

//        로그인 한 사용자의 토큰값을 우선 로그로만.
        Log.d("로그인토큰", ContextUtil.getLoginUserToken(mContext))

        apiList.getRequestMyInfo(
            ContextUtil.getLoginUserToken(mContext)
        ).enqueue( object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

//                뭐가 되었던 (제대로건 / 에러건) 응답이 돌아오긴 한 경우 (물리적 연결만 성공)

                if ( response.isSuccessful ) {

//                    성공일때만 BasicResponse 형태로 제대로 담김.
                    val br = response.body()!!

                    Log.d("내 정보 조회", br.toString())

                    binding.txtLoginUserNickname.text =  br.data.user.nickname

//                    글라이드 활용 > 서버가 알려주는 프로필 사진 인터넷 주소의 그림을 반영하기

                    Glide.with(mContext).load( br.data.user.profileImageURL ).into( binding.imgLoginUserProfile )

                }


            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {
//                서버 연결에 물리적으로 실패.
            }


        } )

    }
}