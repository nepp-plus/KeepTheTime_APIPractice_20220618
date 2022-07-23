package com.neppplus.keepthetime_apipractice_20220618.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.keepthetime_apipractice_20220618.R
import com.neppplus.keepthetime_apipractice_20220618.datas.AppointmentData

class MyScheduleAdapter(
    val mContext: Context,
    val mList: ArrayList< AppointmentData >
) : RecyclerView.Adapter< MyScheduleAdapter.MyViewHolder >() {

    inner class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val row = LayoutInflater.from(mContext).inflate( R.layout.my_schedule_list_item, parent, false )
        return MyViewHolder(row)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

    }

    override fun getItemCount() = mList.size

}