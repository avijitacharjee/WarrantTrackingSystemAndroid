package com.avijit.warranttrackingsystem.models

/**
 * Created by Avijit Acharjee on 12/27/2020 at 4:45 PM.
 * Email: avijitach@gmail.com.
 */
data class Token (
    var token_type : String,
    var expires_in: Int,
    var access_token : String,
    var refresh_token : String
)