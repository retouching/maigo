package party.maigo.music.core.common

/**
 * Injectable time source to keep timestamps deterministic in unit tests.
 */
interface Clock {
    fun nowEpochMillis(): Long
}
