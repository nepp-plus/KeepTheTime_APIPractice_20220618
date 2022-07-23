package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityViewScheduleBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.AppointmentData
import com.odsay.odsayandroidsdk.API
import com.odsay.odsayandroidsdk.ODsayData
import com.odsay.odsayandroidsdk.ODsayService
import com.odsay.odsayandroidsdk.OnResultCallbackListener

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

//            정보창을 마커에 붙이기 => 커스텀 정보창 (장소명 + 소요시간-ODSay API/라이브러리 도움)

            val myODsay = ODsayService.init(mContext, "IlfT1SwuO6TqHZmMjP+ltXiqRNXRkFoXKrTsSxxJbpM")

            myODsay.requestSearchPubTransPath(
                mAppointmentData.startLng.toString(),
                mAppointmentData.startLat.toString(),
                mAppointmentData.longitude.toString(),
                mAppointmentData.latitude.toString(),
                null,
                null,
                null,
                object : OnResultCallbackListener {
                    override fun onSuccess(p0: ODsayData?, p1: API?) {

//                        길찾기에 성공하면 => 정보창 띄우기

                        val odsayJsonData = p0!!.json

                        val resultObj = odsayJsonData.getJSONObject("result")

                        val pathArr = resultObj.getJSONArray("path")

                        val firstPathObj = pathArr.getJSONObject(0)  // JSONArray의 { } 추출 : getJSONObject 중 몇번째

                        val infoObj = firstPathObj.getJSONObject("info")

                        val totalTime = infoObj.getInt("totalTime")

                        Log.d("totalTime", totalTime.toString())



                        val infoWindow = InfoWindow()
                        infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(mContext) {
                            override fun getText(p0: InfoWindow): CharSequence {
                                return "${mAppointmentData.place}까지 ${totalTime}분 소요"
                            }

                        }

                        infoWindow.open(marker)

                    }

                    override fun onError(p0: Int, p1: String?, p2: API?) {

                    }

                }
            )

            
        }

    }
}