package com.winthan.watchmekmp.android.di

import com.winthan.watchmekmp.android.detail.DetailViewModel
import com.winthan.watchmekmp.android.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(it.get(), get(), get()) }
}