package com.neppplus.keepthetime_apipractice_20220618

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.neppplus.keepthetime_apipractice_20220618.databinding.ActivityEditPasswordBinding
import com.neppplus.keepthetime_apipractice_20220618.datas.BasicResponse
import com.neppplus.keepthetime_apipractice_20220618.utils.ContextUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPasswordActivity : BaseActivity() {

    lateinit var binding: ActivityEditPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_password)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        binding.btnEditPassword.setOnClickListener {

            val inputCurrentPw = binding.edtCurrentPassword.text.toString()
            val inputNewPw = binding.edtNewPassword.text.toString()

            apiList.patchRequestEditPassword(
                ContextUtil.getLoginUserToken(mContext),
                inputCurrentPw,
                inputNewPw
            ).enqueue( object : Callback<BasicResponse> {
                override fun onResponse(
                    call: Call<BasicResponse>,
                    response: Response<BasicResponse>
                ) {

                    if (response.isSuccessful) {
                        Toast.makeText(mContext, "비밀번호가 변경 되었습니다.", Toast.LENGTH_SHORT).show()
                    }

                }

                override fun onFailure(call: Call<BasicResponse>, t: Throwable) {

                }

            })

        }

    }

    override fun setValues() {

    }
}