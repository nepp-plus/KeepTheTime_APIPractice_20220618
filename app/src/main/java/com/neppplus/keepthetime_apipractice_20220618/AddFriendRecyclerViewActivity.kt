package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityAddFriendRecyclerViewBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import com.neppplus.keepthetime_apipractice_20220618.datas.UserData
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFriendRecyclerViewActivity : BaseActivity() {

    lateinit var binding: ActivityAddFriendRecyclerViewBinding

//    검색 결과 나온 사람들을 담을 목록 변수
    val mSearchedUserList = ArrayList<UserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_friend_recycler_view)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

//        검색 버튼이 눌리면 => 서버에서 사용자 목록을 검색해서 => 그 결과를 ArrayList에 add

        binding.btnSearch.setOnClickListener {

            val inputKeyword = binding.edtNickname.text.toString()

            apiList.getRequestSearchUser(
                ContextUtil.getLoginUserToken(mContext),
                inputKeyword
            ).enqueue( object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {

//                        검색이 성공적으로 이뤄졌다면
//                        서버가 내려준 목록을 => ArrayList에 추가 => 리싸이클러뷰의 내용물로 반영

                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            } )


        }

    }

    override fun setValues() {

    }
}