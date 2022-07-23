package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityViewScheduleBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.AppointmentData

class ViewScheduleActivity : BaseActivity() {

    lateinit var binding: ActivityViewScheduleBinding

//    이 화면에 들고 들어온 약속 데이터
    lateinit var mAppointmentData: AppointmentData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_view_schedule)
        mAppointmentData = intent.getSerializableExtra("schedule") as AppointmentData
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

    }

    override fun setValues() {

        binding.txtTitle.text = mAppointmentData.title
        binding.txtDateTime.text = mAppointmentData.datetime

    }
}