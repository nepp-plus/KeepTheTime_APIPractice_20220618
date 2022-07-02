package com.neppplus.keepthetime_apipractice_20220618.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.neppplus.keepthetime_apipractice_20220618.R
import com.neppplus.keepthetime_apipractice_20220618.datas.UserData

class SearchedUserAdapter(
    val mContext: Context,
    resId: Int,
    val mList: ArrayList<UserData>
) : ArrayAdapter<UserData>(mContext, resId, mList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var tempRow = convertView

        if (tempRow == null) {
            tempRow = LayoutInflater.from(mContext).inflate( R.layout.searched_user_list_item, null )
        }

        val row = tempRow!!


        return row
    }

}