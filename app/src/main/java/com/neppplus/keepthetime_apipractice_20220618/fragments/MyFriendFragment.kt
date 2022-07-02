package com.neppplus.keepthetime_apipractice_20220618.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.AddFriendActivity
import com.neppplus.keepthetime_apipractice_20220618.R
import com.neppplus.keepthetime_apipractice_20220618.databinding.FragmentMyFriendBinding

class MyFriendFragment : BaseFragment() {

    lateinit var binding: FragmentMyFriendBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_my_friend, container, false )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnAddFriend.setOnClickListener {

            val myIntent = Intent(mContext, AddFriendActivity::class.java)
            startActivity(myIntent)

        }

    }

    override fun setValues() {

    }
}