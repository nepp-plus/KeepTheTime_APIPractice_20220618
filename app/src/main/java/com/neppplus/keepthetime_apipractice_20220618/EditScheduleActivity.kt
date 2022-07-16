package com.neppplus.keepthetime_apipractice_20220618

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityEditScheduleBinding
import java.text.SimpleDateFormat
import java.util.*

class EditScheduleActivity : BaseActivity() {

    lateinit var binding: ActivityEditScheduleBinding

//    선택한 약속 일시를 저장할 캘린더 변수.
    val mSelectedDateTime = Calendar.getInstance() // 현재 일시가 자동 저장

//    선택한 약속 일자를 표시할 양식 변수
    val mDisplayDateFormat = SimpleDateFormat("M월 d일 (E)") // 6월 1일 (수) 양식

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

//                    임시 - txtDate에 반영 => 실제 : Calendar / SimpleDateFormat 활용해서 반영

//                    binding.txtDate.text = "${month}월 ${dayOfMonth}일"  // month값이 0~11기준으로 계산해야함


//                    1) 선택된 year, month, dayOfMonth를 Calendar에 반영 (set)

                    mSelectedDateTime.set( year, month, dayOfMonth )

//                    2) Calendar에 들어간값을 => 양식으로 가공해서 텍스트뷰에 반영

                    binding.txtDate.text = mDisplayDateFormat.format( mSelectedDateTime.time )


                }
            }

//            선택된 일시 (기본 - 현재 일시) 를 DatePicker의 기본값으로 설정

            val dialog = DatePickerDialog(
                mContext,
                dateSetListener,
                mSelectedDateTime.get(Calendar.YEAR),
                mSelectedDateTime.get(Calendar.MONTH),
                mSelectedDateTime.get(Calendar.DAY_OF_MONTH)
            ).show()

        }

    }

    override fun setValues() {

    }
}