package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityMainBinding
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil

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

    }
}