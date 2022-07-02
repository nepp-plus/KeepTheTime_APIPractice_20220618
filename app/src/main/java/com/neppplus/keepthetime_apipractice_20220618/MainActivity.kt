package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.neppplus.keepthetime_apipractice_20220618.adapters.MainViewPager2Adapter
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityMainBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : BaseActivity() {

//   (activity_main) xml의 요소를 데이터바인딩을 통해 끌어오는 binding 변수 생성.
    lateinit var binding : ActivityMainBinding

    lateinit var mvp2a: MainViewPager2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding 변수 대입 : setContentView를 DataBindingUtil을 통해서 실행.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        바텀 네비게이션의 메뉴 선택 > 페이지 이동

        binding.mainBottomNav.setOnItemSelectedListener {

//            클릭된 아이템에 따라 (it 변수가 알려줌) => 다른 행동 실행 => when 문법 활용

            when (it.itemId) {
                R.id.schedule -> {

//                    0번 페이지로 ViewPager2의 페이지 이동
                    binding.mainViewPager2.currentItem = 0

                }
                R.id.myInfo -> {
                    binding.mainViewPager2.currentItem = 1
                }
            }


//            Boolean 값 리턴 필요 : true로 리턴
            return@setOnItemSelectedListener true
        }

    }

    override fun setValues() {

        mvp2a = MainViewPager2Adapter( this ) // Activity를 넣어야 함. (Context 대입 불가) => this 를 대입.
        binding.mainViewPager2.adapter = mvp2a


    }
}