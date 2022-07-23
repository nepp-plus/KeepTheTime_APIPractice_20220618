package com.neppplus.keepthetime_apipractice_20220618.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.neppplus.keepthetime_apipractice_20220618.R
import com.neppplus.keepthetime_apipractice_20220618.ViewScheduleActivity
import com.neppplus.keepthetime_apipractice_20220618.datas.AppointmentData

class MyScheduleAdapter(
    val mContext: Context,
    val mList: ArrayList< AppointmentData >
) : RecyclerView.Adapter< MyScheduleAdapter.MyViewHolder >() {

    inner class MyViewHolder(val row: View) : RecyclerView.ViewHolder(row) {

        val txtTitle = row.findViewById<TextView>(R.id.txtTitle)
        val txtDateTime = row.findViewById<TextView>(R.id.txtDateTime)
        val txtPlaceName = row.findViewById<TextView>(R.id.txtPlaceName)

        fun bind( data: AppointmentData ) {
            txtTitle.text =  data.title
            txtDateTime.text =  data.datetime
            txtPlaceName.text = data.place

//            리싸이클러뷰의 클릭 이벤트 => 인터페이스를 활용해야 함.
//            임시방편 : 어댑터의 row를 클릭하면 이벤트 처리.

            row.setOnClickListener {

//                일정목록을 자세히 보여주는 화면으로 이동

                val myIntent = Intent(mContext, ViewScheduleActivity::class.java)

                myIntent.putExtra("schedule", data)
                
//                startActivity : 액티비티 / 프래그먼트가 물려주는 기능
//                어댑터에서는 활용 불가 : 화면단의 도움을 받아서 실행
                mContext.startActivity( myIntent )

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val row = LayoutInflater.from(mContext).inflate( R.layout.my_schedule_list_item, parent, false )
        return MyViewHolder(row)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bind( mList[position] )

    }

    override fun getItemCount() = mList.size

}