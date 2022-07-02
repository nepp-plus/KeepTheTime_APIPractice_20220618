package com.neppplus.keepthetime_apipractice_20220618.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.neppplus.keepthetime_apipractice_20220618.R

class MyProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate( R.layout.fragment_my_profile, container, false )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

//        향후 동작 관련 코드 작성

//        1. 서버에 내 정보 요청 (GET - /user) 함수 사용 => MyProfileFragment > BaseFragment() > Fragment() 상속

//        2. 서버의 응답 분석 => 프사 / 닉네임 / 이메일 반영

    }


}