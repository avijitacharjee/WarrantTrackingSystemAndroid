package com.avijit.rms1.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData

import com.avijit.warranttrackingsystem.api.RetrofitService
import com.avijit.warranttrackingsystem.api.TokenApi
import com.avijit.warranttrackingsystem.models.Token
import com.avijit.warranttrackingsystem.models.TokenBody
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TokenRepository {
    val TAG = "dbg:"
    private var  tokenApi: TokenApi = RetrofitService.createService(TokenApi::class.java)
    fun token(token: TokenBody): MutableLiveData<Token>
         {
            val tokenResponseMutableLiveData: MutableLiveData<Token> = MutableLiveData<Token>()
            val f : Token = Token()

            tokenApi.token(token).enqueue(object : Callback<Token> {
                override fun onResponse(
                    call: Call<Token>,
                    response: Response<Token>
                ) {
                    if (response.isSuccessful) {
                        tokenResponseMutableLiveData.setValue(response.body())
                    } else {
                        tokenResponseMutableLiveData.value =f
                    }
                }

                override fun onFailure(
                    call: Call<Token>,
                    t: Throwable
                ) {
                    tokenResponseMutableLiveData.value=f
                }
            })
            return tokenResponseMutableLiveData
        }

    companion object {
        private lateinit var tokenRepository: TokenRepository
        fun getInstance() : TokenRepository{
            return TokenRepository()
        }
    }

}