package com.neppplus.keepthetime_apipractice_20220618

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityEditScheduleBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class EditScheduleActivity : BaseActivity() {

    lateinit var binding: ActivityEditScheduleBinding

//    선택한 약속 일시를 저장할 캘린더 변수.
    val mSelectedDateTime = Calendar.getInstance() // 현재 일시가 자동 저장

//    선택한 약속 일자를 표시할 양식 변수
    val mDisplayDateFormat = SimpleDateFormat("M월 d일 (E)") // 6월 1일 (수) 양식
    val mDisplayTimeFormat = SimpleDateFormat( "a h시 m분" )

//    선택 약속일시를 => 서버에 보내기 적합한 형태로 가공할 양식 변수
    val mServerFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")


//    xml에 배치된 맵뷰변수 끌어오기 => 네이버 요구 코드 작성 가능
    private lateinit var mapView: MapView

//    맵뷰 내의 실제 지도 변수
    lateinit var naverMap : NaverMap


//    선택한 목적지의 좌표를 저장해두자.
    lateinit var mSelectedPosition : LatLng


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_schedule)
        
        mapView = binding.mapView // 예제와 달리 데이터 바인딩 활용 가능

        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        problem : 지도의 스크롤과 / 스크롤뷰의 스크롤이 동시 실행 => 지도의 스크롤이 무시됨. (상하 이동 안됨)
//        solution : 지도위에 겹쳐둔 텍스트뷰에 손이 닿으면 (touch) -> 스크롤뷰의 기능 일시 정지 (지도만 스크롤 가능)

        binding.txtScrollHelper.setOnTouchListener { view, motionEvent ->

            binding.scrollView.requestDisallowInterceptTouchEvent(true)

            false  // 텍스트뷰의 터치 외에도, 뒤에 가려져있는 지도의 동작도 같이 실행. (return false)
        }

        binding.btnSave.setOnClickListener {

//            서버에 보내줄 데이터 변수에 담아두기

            val inputTitle = binding.edtTitle.text.toString()  // 입력한 제목 저장

//            선택 일시를 "2022-07-16 19:35" 형태로 가공해서 서버에 보내야함.
            val datetimeStr = mServerFormat.format( mSelectedDateTime.time )

            val inputPlaceName = binding.edtPlaceName.text.toString()

//            네이버지도에서 마지막으로 클릭한 좌표 (LatLng) => 위도, 경도값으로 따로 추출.

            val lat = mSelectedPosition.latitude
            val lng = mSelectedPosition.longitude

//            제목, 일시, 장소명, 위/경도 종합해서 서버에 전송해주자.

            apiList.postRequestAddAppointment(
                ContextUtil.getLoginUserToken(mContext),
                inputTitle,
                datetimeStr,
                inputPlaceName,
                lat,
                lng
            ).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

//                    임시 응답 처리 - 성공으로 처리되면 => 이전 화면으로 돌아가기
                    if (response.isSuccessful) {
                        Toast.makeText(mContext, "약속을 등록했습니다.", Toast.LENGTH_SHORT).show()
                        finish()
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })


        }

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

//        지도 객체 얻어서 > 할일 작성

        binding.mapView.getMapAsync {

//            it 변수가 네이버지도 객체 => 멤버변수에 담아 두고 사용하자.
            naverMap = it

//            넵플러스 학원 위치를 변수로 임시 저장

            val neppPlusLatLng = LatLng(  37.57791, 127.03357  )

//            넵플러스 학원 위치로 지도 이동 (카메라 이동)

            val cameraUpdate = CameraUpdate.scrollTo(  neppPlusLatLng  )
            naverMap.moveCamera( cameraUpdate )

//            넵플러스 학원 위치로 마커 띄우기 (장소 표시)

            val marker = Marker()
            marker.position =  neppPlusLatLng

            marker.icon = OverlayImage.fromResource( R.drawable.red_marker )

            marker.width = 100
            marker.height = 100

            marker.map = naverMap

//            넵플러스 학원 위치를 선택한 것으로 설정.
            mSelectedPosition = neppPlusLatLng


//            정보창 달아보기

            val infoWindow = InfoWindow()

            infoWindow.adapter = object : InfoWindow.DefaultTextAdapter(mContext) {
                override fun getText(p0: InfoWindow): CharSequence {
                    return "목적지"
                }

            }

            infoWindow.open(marker)

//            지도 영역을 클릭하면, (이벤트처리)
            naverMap.setOnMapClickListener { pointF, latLng ->

//                열려있는 정보창 닫아주기 => 폐기
//                infoWindow.close()

//                기존 마커를 클릭된 지점으로 옮겨보자.

//                임시 : 새로 마커를 만들어서 찍어보자

//                val newMarker = Marker() // 클릭 될 때 마다 새 마커 생성 X. 처음 만든 마커의 위치만 이동
//                newMarker.position = latLng
//                newMarker.map = naverMap

                marker.position = latLng // 기존 마커의 위치를 클릭된 좌표로 변경

//                선택된 위치값도 같이 변경
                mSelectedPosition = latLng

            }

//            마커를 클릭하면 (이벤트처리)

            marker.setOnClickListener {

                if (marker.hasInfoWindow()) {
//                    이미 열려있다. => 닫아주자.
                    infoWindow.close()
                }
                else {
//                    마커에 인포윈도우 달아주기.
                    infoWindow.open(marker)
                }

                true // return true와 동일
            }


        }

    }


//    네이버 지도가 요구하는 함수들 작성

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


}