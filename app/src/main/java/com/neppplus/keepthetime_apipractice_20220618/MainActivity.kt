package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

//   (activity_main) xml의 요소를 데이터바인딩을 통해 끌어오는 binding 변수 생성.
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding 변수 대입 : setContentView를 DataBindingUtil을 통해서 실행.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


//        모든 id 값들은 binding.해당id로 사용.
        binding.btnToast.setOnClickListener {
            Toast.makeText(this, "테스트", Toast.LENGTH_SHORT).show()
        }

        binding.btnLog.setOnClickListener { 
            
            Log.d("데이터바인딩", "로그 찍기")
            
        }

    }
}