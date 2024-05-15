package base.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

interface BaseScope : CoroutineScope {

    val uiDispatcher: CoroutineDispatcher
    val bgDispatcher: CoroutineDispatcher

    override val coroutineContext: CoroutineContext
        get() = SupervisorJob() + uiDispatcher

}