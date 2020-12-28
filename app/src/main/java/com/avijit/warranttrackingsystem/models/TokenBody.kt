package com.avijit.warranttrackingsystem.models

/**
 * Created by Avijit Acharjee on 12/28/2020 at 4:08 PM.
 * Email: avijitach@gmail.com.
 */
data class TokenBody (
    var client_id : Int,
    var client_secret : String,
    var grant_type : String,
    var username : String,
    var password : String
)