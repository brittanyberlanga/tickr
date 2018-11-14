package io.tickr

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class LifecycleScope : CoroutineScope, LifecycleObserver {

    private val parentJob = Job()

    override val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy(source: LifecycleOwner) {
        parentJob.cancel()
    }
}