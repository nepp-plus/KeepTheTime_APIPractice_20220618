package com.neppplus.keepthetime_apipractice_20220618.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

// 모든 프래그먼트의 공통 기능을 물려주기 위한 중간 상속 클래스

abstract class BaseFragment : Fragment() {

    lateinit var mContext : Context

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mContext = requireContext() // 프래그먼트에서도 화면을 불러낼때, mContext 만 넣으면 해결
    }

    abstract fun setupEvents()
    abstract fun setValues()

}