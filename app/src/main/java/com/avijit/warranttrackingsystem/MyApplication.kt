package com.avijit.warranttrackingsystem

import android.app.Application
import android.content.Context
import com.avijit.warranttrackingsystem.di.component.AppComponent
import com.avijit.warranttrackingsystem.di.component.DaggerAppComponent
import com.avijit.warranttrackingsystem.di.module.AppModule

/**
 * Created by Avijit Acharjee on 12/28/2020 at 12:53 AM.
 * Email: avijitach@gmail.com.
 */
class MyApplication : Application() {
    private lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(applicationContext))
            .build()
    }
    fun getAppComponent() : AppComponent {
        return appComponent
    }

}