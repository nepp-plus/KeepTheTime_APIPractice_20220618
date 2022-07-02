package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.adapters.SearchedUserAdapter
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityAddFriendBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import com.neppplus.keepthetime_apipractice_20220618.datas.UserData
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddFriendActivity : BaseActivity() {

    lateinit var binding: ActivityAddFriendBinding

//    서버 응답 파싱 => 이 목록에 서버가 주는 사용자 목록을 추가하자.
    val mSearchedUserList = ArrayList<UserData>()

    lateinit var mAdapter: SearchedUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_friend)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnSearch.setOnClickListener {

            val keyword = binding.edtNickname.text.toString()

            apiList.getRequestSearchUser(
                ContextUtil.getLoginUserToken(mContext),
                keyword
            ).enqueue(object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {
                    if (response.isSuccessful) {

//                        서버가 주는 응답중 > users 라는 [  ] 의 내용물을 > 리스트뷰에 뿌려주기
//                        리스트뷰의 작업이 다 되어있는 상태에서 > 서버가 준 데이터를 추가로 보여주자.

                        val br = response.body()!!

//                        기존에 검색되어있던 사용자 목록은 전부 삭제.
                        mSearchedUserList.clear()

//                        멤버변수로 있는 목록에 => 서버가 준 데이터 목록을 전부 추가.
                        mSearchedUserList.addAll( br.data.users )

//                        리스트뷰가 먼저 만들어지고 => 나중에 목록이 추가됨.
//                        어댑터에게 새로고침 감지
                        mAdapter.notifyDataSetChanged()

                    }
                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }

    }

    override fun setValues() {

        mAdapter = SearchedUserAdapter(mContext, R.layout.searched_user_list_item, mSearchedUserList)

        binding.searchedUserListView.adapter = mAdapter

    }
}