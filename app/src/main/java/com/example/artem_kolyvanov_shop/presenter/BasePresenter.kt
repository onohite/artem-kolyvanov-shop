package com.example.artem_kolyvanov_shop.presenter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.MvpView
import java.net.UnknownHostException
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<TView : MvpView>:MvpPresenter<TView>(),CoroutineScope {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Main + job)

    override val coroutineContext: CoroutineContext = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}