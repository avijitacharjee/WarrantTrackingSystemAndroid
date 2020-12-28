package com.avijit.warranttrackingsystem.di.module

import androidx.lifecycle.ViewModel
import com.avijit.warranttrackingsystem.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module

/**
 * Created by Avijit Acharjee on 12/27/2020 at 10:24 PM.
 * Email: avijitach@gmail.com.
 */
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel) : ViewModel
}