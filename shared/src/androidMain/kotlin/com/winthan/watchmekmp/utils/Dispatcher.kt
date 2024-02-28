package com.winthan.watchmekmp.utils

internal class AndroidDispatcher : Dispatcher {
    override val io = kotlinx.coroutines.Dispatchers.IO
}

internal actual fun provideDispatcher(): Dispatcher {
    return AndroidDispatcher()
}