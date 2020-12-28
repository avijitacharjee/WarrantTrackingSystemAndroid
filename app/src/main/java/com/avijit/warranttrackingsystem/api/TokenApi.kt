package com.avijit.warranttrackingsystem.api

import com.avijit.warranttrackingsystem.models.Token
import com.avijit.warranttrackingsystem.models.TokenBody
import retrofit2.Call
import retrofit2.http.POST

/**
 * Created by Avijit Acharjee on 12/27/2020 at 5:08 PM.
 * Email: avijitach@gmail.com.
 */
interface TokenApi {
    @POST
    fun token(tokenBody: TokenBody) : Call<Token>
}