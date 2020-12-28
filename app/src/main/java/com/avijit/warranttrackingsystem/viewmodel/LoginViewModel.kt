package com.avijit.warranttrackingsystem.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.avijit.rms1.repository.TokenRepository
import com.avijit.warranttrackingsystem.models.Token
import com.avijit.warranttrackingsystem.models.TokenBody
import javax.inject.Inject

/**
 * Created by Avijit Acharjee on 12/27/2020 at 8:49 PM.
 * Email: avijitach@gmail.com.
 */
class LoginViewModel : ViewModel() {
    private var tokenRepository : TokenRepository = TokenRepository.getInstance()
    private lateinit var token : MutableLiveData<Token>

    private fun getToken(tokenBody: TokenBody) :  MutableLiveData<Token> {
        return token
    }
}