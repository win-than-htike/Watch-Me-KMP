package com.winthan.watchmekmp.utils

internal class IosDispatcher : Dispatcher {
    override val io = kotlinx.coroutines.Dispatchers.Default
}

internal actual fun provideDispatcher(): Dispatcher {
    return IosDispatcher()
}