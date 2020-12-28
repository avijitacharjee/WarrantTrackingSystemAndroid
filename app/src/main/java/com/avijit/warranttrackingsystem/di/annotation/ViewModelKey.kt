package com.avijit.warranttrackingsystem.di.annotation

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Created by Avijit Acharjee on 12/28/2020 at 4:01 PM.
 * Email: avijitach@gmail.com.
 */
@MapKey
@Target(AnnotationTarget.FUNCTION)
annotation class ViewModelKey(val value : KClass<out ViewModel>)