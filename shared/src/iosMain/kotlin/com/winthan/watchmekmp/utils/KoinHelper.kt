package com.winthan.watchmekmp.utils

import com.winthan.watchmekmp.di.getSharedModules
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(getSharedModules())
    }
}