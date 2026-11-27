package party.maigo.music.core.common

/**
 * Central structured logger contract. Concrete implementations must redact secrets before emitting output.
 */
interface AppLogger {
    fun debug(tag: String, message: String)

    fun info(tag: String, message: String)

    fun warn(tag: String, message: String, throwable: Throwable? = null)

    fun error(tag: String, message: String, throwable: Throwable? = null)
}
