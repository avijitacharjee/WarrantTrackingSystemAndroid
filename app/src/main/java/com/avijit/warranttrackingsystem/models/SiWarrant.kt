package com.avijit.warranttrackingsystem.models

/**
 * Created by Avijit Acharjee on 1/1/2021 at 2:21 PM.
 * Email: avijitach@gmail.com.
 */
data class SiWarrant (
    var id : String,
    var warrant_id : String,
    var assigned_to : String,
    var assigned_by : String,
    var is_assigned : String,
    var is_completed : String,
    var executed_at : String,
    var status : String,
    var created_at : String,
    var updated_at : String,
    var process_number : String,
    var gr_number : String,
    var warrant_type : String,
    var criminal_name : String,
    var criminal_father_name : String,
    var criminal_address : String,
    var totalActivity : String
)

