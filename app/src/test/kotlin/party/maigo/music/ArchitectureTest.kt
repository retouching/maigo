package party.maigo.music

import com.lemonappdev.konsist.api.Konsist
import com.lemonappdev.konsist.api.verify.assertFalse
import org.junit.jupiter.api.Test

class ArchitectureTest {
    @Test
    fun `production code never imports Firebase or Google Play Services`() {
        Konsist.scopeFromProduction()
            .files
            .assertFalse { file ->
                file.imports.any { imp ->
                    imp.name.startsWith("com.google.firebase.") ||
                        imp.name.startsWith("com.google.android.gms.")
                }
            }
    }

    @Test
    fun `android util Log is never imported outside core security`() {
        Konsist.scopeFromProduction()
            .files
            .filterNot { it.moduleName == "core/security" }
            .assertFalse { file ->
                file.hasImport { it.name == "android.util.Log" }
            }
    }

    @Test
    fun `core model never imports android or androidx io packages`() {
        Konsist.scopeFromModule("core/model")
            .files
            .assertFalse { file ->
                file.imports.any { imp ->
                    imp.name.startsWith("android.") ||
                        imp.name.startsWith("okhttp3.") ||
                        imp.name.startsWith("androidx.room.")
                }
            }
    }
}
