package party.maigo.music.core.common

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SecretRedactorTest {
    @Test
    fun `redacts query parameters, auth headers, and registered secrets`() {
        val redactor = SecretRedactor { listOf("super-secret-token") }

        val raw =
            "GET https://music.example/rest/ping.view?u=alice&t=abc123&s=xyz&p=enc:deadbeef " +
                "Authorization: Bearer super-secret-token"
        val redacted = redactor.redact(raw)

        assertEquals(
            "GET https://music.example/rest/ping.view?u=alice&t=[REDACTED]&s=[REDACTED]&p=[REDACTED] " +
                "Authorization: [REDACTED]",
            redacted
        )
    }
}
