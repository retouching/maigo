package party.maigo.music.core.common

/**
 * Strips known secrets and credential query parameters from log lines before emission.
 */
class SecretRedactor(private val literalSecretsProvider: () -> Collection<String> = { emptyList() }) {
    fun redact(input: String): String {
        var result = QUERY_SECRET_REGEX.replace(input, "$1$REDACTED")
        result = AUTH_HEADER_REGEX.replace(result, "$1$REDACTED")
        for (secret in literalSecretsProvider()) {
            if (secret.isNotEmpty()) {
                result = result.replace(secret, REDACTED)
            }
        }
        return result
    }

    private companion object {
        const val REDACTED = "[REDACTED]"
        val QUERY_SECRET_REGEX =
            Regex(
                """([?&](?:p|t|s|password|token|salt|apiKey)=)[^&\s]+""",
                RegexOption.IGNORE_CASE
            )
        val AUTH_HEADER_REGEX =
            Regex(
                """((?:Authorization|Cookie)\s*:\s*)[^\r\n]+""",
                RegexOption.IGNORE_CASE
            )
    }
}
