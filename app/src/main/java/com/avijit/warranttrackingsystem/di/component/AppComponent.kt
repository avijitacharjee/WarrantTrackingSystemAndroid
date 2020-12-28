package com.avijit.warranttrackingsystem.di.component

import android.app.Application
import com.avijit.warranttrackingsystem.MainActivity
import com.avijit.warranttrackingsystem.di.module.AppModule
import dagger.Binds
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Avijit Acharjee on 12/28/2020 at 12:18 AM.
 * Email: avijitach@gmail.com.
 */
@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    /*@Component.Factory
    interface Factory {
        fun create(@BindsInstance context : Context) : AppComponent
    }*/
    fun inject(mainActivity: MainActivity) : MainActivity
}