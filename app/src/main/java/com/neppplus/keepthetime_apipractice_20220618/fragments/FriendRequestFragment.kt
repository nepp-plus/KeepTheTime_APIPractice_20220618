package com.neppplus.keepthetime_apipractice_20220618.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.R
import com.neppplus.keepthetime_apipractice_20220618.databinding.FragmentFriendRequestBinding
import com.neppplus.keepthetime_apipractice_20220618.databinding.FragmentMyFriendBinding

class FriendRequestFragment : BaseFragment() {

    lateinit var binding: FragmentFriendRequestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_friend_request, container, false )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

    }
}