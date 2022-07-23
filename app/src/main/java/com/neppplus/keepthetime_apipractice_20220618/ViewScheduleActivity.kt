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
import com.naver.maps.map.overlay.PathOverlay
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

//                        경로선도 추가 - 임시 : 출발지 ~ 도착지 까지

                        val path = PathOverlay()

//                        경로선이 찍히는 지점들을 담아줄 ArrayList
                        val coords = ArrayList<LatLng>()

//                        출발지 추가
                        coords.add( LatLng( mAppointmentData.startLat, mAppointmentData.startLng ) )

//                        중간에 들리는 정거장 목록의 좌표들을 추가 => 실제 지나가는 경로들을 표현.
//                        ODSay API가 주는 정보 => 첫번째 추천 경로 (firstPathObj 변수)
//                        => 세부 경로 (subPath) 들 중, 정거장 좌표들만 모아서 추출.

                        val subPathArr = firstPathObj.getJSONArray("subPath")

//                        세부 경로 예시 : 도보로 지하철역까지 (0) -> 지하철타고 신사역까지 (1)
//                        -> 신사역에서 버스로 대로변 정거장(2) -> 대로변에서 목적지까지 도보로 (3)

//                        for문을 이용해서, 모든 세부경로를 전부 확인해보자.

                        for ( i in  0 until  subPathArr.length() ) {

                            val subPathObj = subPathArr.getJSONObject(i)

//                            세부 경로에 => 정거장 목록이 들어있다면, 그 내부 탐색 (null인가? 그것만 아니면 OK - NOT 연산)
                            if ( !subPathObj.isNull("passStopList") ) {

                                val passStopListObj = subPathObj.getJSONObject("passStopList")
                                val stationsArr = passStopListObj.getJSONArray("stations")

                                for (j in  0 until stationsArr.length() ) {

                                    val stationObj = stationsArr.getJSONObject(j)

                                    Log.d("들리는정거장", stationObj.toString())

                                }

                            }


                        }



//                        도착지 추가
                        coords.add( LatLng(mAppointmentData.latitude, mAppointmentData.longitude) )

                        path.coords  =  coords

                        path.map = naverMap

                    }

                    override fun onError(p0: Int, p1: String?, p2: API?) {

                    }

                }
            )

            
        }

    }
}