package com.neppplus.keepthetime_apipractice_20220618.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neppplus.keepthetime_apipractice_20220618.fragments.FriendRequestFragment
import com.neppplus.keepthetime_apipractice_20220618.fragments.MyFriendFragment

class FriendManageViewPager2Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MyFriendFragment()
            else -> FriendRequestFragment()
        }
    }
}