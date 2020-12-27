package com.avijit.warranttrackingsystem.utils

/**
 * Created by Avijit Acharjee on 12/27/2020 at 5:15 PM.
 * Email: avijitach@gmail.com.
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }

    }

}