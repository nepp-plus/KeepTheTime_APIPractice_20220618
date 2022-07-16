package com.neppplus.keepthetime_apipractice_20220618

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
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
    val mDisplayTimeFormat = SimpleDateFormat( "a h시 m분" )

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

        binding.txtTime.setOnClickListener {

//            시간이 선택되면 할 일 계획

            val tsl = object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(p0: TimePicker?, hour: Int, minute: Int) {

//                    1) 선택된 시간/분을 Calendar에 set - 항목 별 세팅 (시간/분 만 세팅)

                    mSelectedDateTime.set(Calendar.HOUR_OF_DAY, hour)
                    mSelectedDateTime.set( Calendar.MINUTE, minute )

//                    2) txtTime의 문구에 양식을 갖춰 반영 : 오후 6시 5분

                    binding.txtTime.text =  mDisplayTimeFormat.format( mSelectedDateTime.time )


                }
            }

//            TimePickerDialog 띄우기

            val dialog = TimePickerDialog(
                mContext,
                tsl,
                mSelectedDateTime.get( Calendar.HOUR_OF_DAY ),
                mSelectedDateTime.get( Calendar.MINUTE ),
                false  // 시계 모양이 오전/오후를 별도 선택하도록.
            ).show()

        }

    }

    override fun setValues() {

    }
}