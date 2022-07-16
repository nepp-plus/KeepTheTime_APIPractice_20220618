package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.neppplus.keepthetime_apipractice_20220618.adapters.SearchedUserRecyclerAdapter
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

//    리싸이클러뷰 어댑터 변수
    lateinit var mAdapter: SearchedUserRecyclerAdapter

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
                inputKeyword
            ).enqueue( object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {

//                        검색이 성공적으로 이뤄졌다면
//                        서버가 내려준 목록을 => (기존 목록 제거하고) ArrayList에 추가 => 리싸이클러뷰의 내용물로 반영

                        val br = response.body()!!

                        mSearchedUserList.clear() // 기존 검색 결과 전부 삭제
                        mSearchedUserList.addAll( br.data.users )

//                        목록이 추가되었으니 -> 어댑터 내용 변경 -> 새로 고침
                        mAdapter.notifyDataSetChanged()

                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            } )


        }

    }

    override fun setValues() {

        mAdapter = SearchedUserRecyclerAdapter( mContext, mSearchedUserList )
        binding.searchedUserRecyclerView.adapter = mAdapter

//        리싸이클러뷰 : LayoutManager 설정 필요 => Grid로 활용
        binding.searchedUserRecyclerView.layoutManager = GridLayoutManager(mContext, 2)

    }
}