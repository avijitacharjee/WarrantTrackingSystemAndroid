package com.avijit.warranttrackingsystem.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Avijit Acharjee on 12/28/2020 at 12:08 AM.
 * Email: avijitach@gmail.com.
 */
@Module
class AppModule(context: Context) {
    var mContext : Context = context
    @Provides
    @Singleton
    fun provideApplication() : Context {
        return mContext
    }
}