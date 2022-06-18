package com.neppplus.keepthetime_apipractice_20220618

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

//    this는 가리키는 대상이 달라질 수 있다.
//    미리 다른 변수에 this를 담아두고 => 상속으로 물려주면 코딩이 더 편해진다.

    lateinit var mContext: Context // 이 화면을 미리 담아둘 변수

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mContext = this // this 값을 미리 변수에 대입해두자.
    }


//    두개의 구현 내용이 없는 추상 함수를 물려주게 설정.
//    BaseActivity를 상속받는 클래스들은, 이 두개의 함수를 구현해야함.

    abstract fun setupEvents()
    abstract fun setValues()

}