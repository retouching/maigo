import java.io.StringReader
import java.util.Properties

plugins {
    alias(libs.plugins.maigo.android.application)
    alias(libs.plugins.maigo.android.compose)
    alias(libs.plugins.maigo.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

val keystorePropertiesText =
    providers.fileContents(rootProject.layout.projectDirectory.file("keystore.properties")).asText.orNull
val keystoreProperties =
    Properties().apply {
        if (!keystorePropertiesText.isNullOrBlank()) {
            load(StringReader(keystorePropertiesText))
        }
    }

fun signingValue(envKey: String, propertyKey: String): String? =
    providers.environmentVariable(envKey).orNull?.takeIf { it.isNotBlank() }
        ?: keystoreProperties.getProperty(propertyKey)?.takeIf { it.isNotBlank() }

val releaseKeystorePath = signingValue("MAIGO_KEYSTORE_PATH", "storeFile")
val releaseKeystorePassword = signingValue("MAIGO_KEYSTORE_PASSWORD", "storePassword")
val releaseKeyAlias = signingValue("MAIGO_KEY_ALIAS", "keyAlias")
val releaseKeyPassword = signingValue("MAIGO_KEY_PASSWORD", "keyPassword")

android {
    namespace = "party.maigo.music"

    defaultConfig {
        applicationId = "party.maigo.music"
        versionCode = 1
        versionName = "0.1.0"
    }

    val releaseSigningConfig =
        if (
            releaseKeystorePath != null &&
            releaseKeystorePassword != null &&
            releaseKeyAlias != null &&
            releaseKeyPassword != null
        ) {
            signingConfigs.create("release") {
                storeFile = rootProject.file(releaseKeystorePath)
                storePassword = releaseKeystorePassword
                keyAlias = releaseKeyAlias
                keyPassword = releaseKeyPassword
                enableV1Signing = false
                enableV2Signing = true
                enableV3Signing = true
                enableV4Signing = false
            }
        } else {
            null
        }

    buildTypes {
        release {
            if (releaseSigningConfig != null) {
                signingConfig = releaseSigningConfig
            }
        }
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.security)
    implementation(projects.core.database)
    implementation(projects.core.network)
    implementation(projects.core.media)
    implementation(projects.core.data)
    implementation(projects.core.designsystem)

    implementation(projects.feature.home)
    implementation(projects.feature.search)
    implementation(projects.feature.library)
    implementation(projects.feature.player)
    implementation(projects.feature.history)
    implementation(projects.feature.radio)
    implementation(projects.feature.settings)

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)

    testImplementation(projects.core.testing)
    testImplementation(libs.konsist)
}
