package com.avijit.warranttrackingsystem.models

/**
 * Created by Avijit Acharjee on 1/1/2021 at 6:02 PM.
 * Email: avijitach@gmail.com.
 */
data class NetworkResponse<T> (
    var data : List<T>,
    var message : String
)