package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityViewScheduleBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.AppointmentData

class ViewScheduleActivity : BaseActivity() {

    lateinit var binding: ActivityViewScheduleBinding

//    이 화면에 들고 들어온 약속 데이터
    lateinit var mAppointmentData: AppointmentData

//    로딩이 완료된 실제 지도 객체

    lateinit var naverMap: NaverMap

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
        
        binding.mapView.getMapAsync { 
            naverMap = it

//            약속장소 좌표 변수로 저장
            val latLng = LatLng( mAppointmentData.latitude, mAppointmentData.longitude )

//            카메라를 약속 장소로 이동

            val cameraUpdate = CameraUpdate.scrollTo( latLng )

            naverMap.moveCamera( cameraUpdate )

            
//            마커를 약속 장소에 표시

            val marker = Marker()
            marker.position = latLng
            marker.map = naverMap
            
        }

    }
}