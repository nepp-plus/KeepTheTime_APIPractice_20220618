package com.neppplus.keepthetime_apipractice_20220618.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.neppplus.keepthetime_apipractice_20220618.fragments.MyProfileFragment
import com.neppplus.keepthetime_apipractice_20220618.fragments.ScheduleFragment

// ViewPager2 에 대응하는 어댑터 작업.

class MainViewPager2Adapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {

        return when( position ) {
            0 -> ScheduleFragment()
            else -> MyProfileFragment()
        }

    }
}