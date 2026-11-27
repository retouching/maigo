package party.maigo.music.core.model

/**
 * Domain-level error hierarchy shared across layers without leaking transport or storage internals.
 */
sealed interface DomainError {
    data object NetworkUnavailable : DomainError

    data object Unauthorized : DomainError

    data object NotFound : DomainError

    data class ServerError(val code: Int? = null) : DomainError

    data object StorageError : DomainError

    data object InvalidConfig : DomainError
}
