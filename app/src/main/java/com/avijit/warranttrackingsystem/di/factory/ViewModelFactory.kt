package com.avijit.warranttrackingsystem.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by Avijit Acharjee on 12/27/2020 at 10:29 PM.
 * Email: avijitach@gmail.com.
 */
class ViewModelFactory @Inject constructor(val viewModelMap : Map<Class<out ViewModel>,@JvmSuppressWildcards Provider<ViewModel>>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelMap [modelClass]!!.get() as T
    }

}