package party.maigo.music.core.common

import kotlinx.coroutines.CoroutineDispatcher

/**
 * Provides injectable coroutine dispatchers so production code never hardcodes global dispatchers.
 */
interface DispatcherProvider {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val mainImmediate: CoroutineDispatcher
}
