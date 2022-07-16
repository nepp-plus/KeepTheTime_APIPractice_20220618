package com.neppplus.keepthetime_apipractice_20220618

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityEditScheduleBinding

class EditScheduleActivity : BaseActivity() {

    lateinit var binding: ActivityEditScheduleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_schedule)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.txtDate.setOnClickListener {

            val dateSetListener = object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(p0: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

//                    임시 - txtDate에 반영

                    binding.txtDate.text = "${month}월 ${dayOfMonth}일"

                }
            }

            val dialog = DatePickerDialog(
                mContext,
                dateSetListener,
                2022,
                7,
                16
            ).show()

        }

    }

    override fun setValues() {

    }
}