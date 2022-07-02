package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.neppplus.keepthetime_apipractice_20220618.adapters.FriendManageViewPager2Adapter
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityManageFriendBinding

class ManageFriendActivity : BaseActivity() {

    lateinit var binding: ActivityManageFriendBinding

    lateinit var fmvp2a : FriendManageViewPager2Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_manage_friend)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        fmvp2a = FriendManageViewPager2Adapter(this)
        binding.friendManageViewPager2.adapter = fmvp2a

        TabLayoutMediator( binding.friendManageTabLayout, binding.friendManageViewPager2 ) { tab, position ->

            tab.text = when(position) {
                0 -> "내 친구 목록"
                else -> "친구 요청 목록"
            }

        }.attach()

    }
}