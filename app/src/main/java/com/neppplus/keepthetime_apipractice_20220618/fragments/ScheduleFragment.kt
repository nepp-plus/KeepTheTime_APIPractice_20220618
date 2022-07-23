package com.neppplus.keepthetime_apipractice_20220618.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.keepthetime_apipractice_20220618.EditScheduleActivity
import com.neppplus.keepthetime_apipractice_20220618.R
import com.neppplus.keepthetime_apipractice_20220618.adapters.MyScheduleAdapter
import com.neppplus.keepthetime_apipractice_20220618.databinding.FragmentScheduleBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.AppointmentData
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScheduleFragment : BaseFragment() {

    lateinit var binding: FragmentScheduleBinding

    val mScheduleList = ArrayList<AppointmentData>()

    lateinit var mAdapter: MyScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate( inflater, R.layout.fragment_schedule, container, false )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnAddSchedule.setOnClickListener {

            val myIntent = Intent( mContext, EditScheduleActivity::class.java )
            startActivity(myIntent)

        }

    }

    override fun setValues() {
//        먼저 서버로 데이터를 가지러 가자 => 먼 길을 가야함
        getMySchedulesFromServer()

//        그 후에 어댑터 세팅 => 가까이에서 해결 가능. 늦게 시작하지만, 먼저 마무리 됨.
        mAdapter = MyScheduleAdapter( mContext, mScheduleList )
        binding.myScheduleRecyclerView.adapter = mAdapter
        binding.myScheduleRecyclerView.layoutManager = LinearLayoutManager(mContext)

    }

    fun getMySchedulesFromServer() {

        apiList.getRequestMyAppointment().enqueue(object : Callback<BasicResponse> {
            override fun onResponse(call: Call<BasicResponse>, response: Response<BasicResponse>) {

                if (response.isSuccessful) {
//                    받아온 일정 목록을 리싸이클러뷰와 연계

//                    서버가 내려주는 약속 목록 전부를 => 멤버변수의 일정 목록에 추가

                    val br = response.body()!!

//                    상황에 따라, 어댑터 세팅이 먼저 끝나고 / 나중에 데이터가 추가되는 경우도 있다.
                    mScheduleList.addAll( br.data.appointments )

//                    어댑터가 사용하는 ArrayList의 내용물 변경 => 어댑터도 새로 고침
                    mAdapter.notifyDataSetChanged()


                }

            }

            override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

            }

        })

    }


}