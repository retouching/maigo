plugins {
    alias(libs.plugins.maigo.android.application)
    alias(libs.plugins.maigo.android.compose)
    alias(libs.plugins.maigo.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "party.maigo.music"

    defaultConfig {
        applicationId = "party.maigo.music"
        versionCode = 1
        versionName = "0.1.0"
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
