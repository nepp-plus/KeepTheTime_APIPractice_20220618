package com.neppplus.keepthetime_apipractice_20220618.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.neppplus.keepthetime_apipractice_20220618.datas.UserData

class SearchedUserAdapter(
    val mContext: Context,
    resId: Int,
    val mList: ArrayList<UserData>
) : ArrayAdapter<UserData>(mContext, resId, mList) {



}