plugins {
    alias(libs.plugins.maigo.android.library)
    alias(libs.plugins.maigo.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "party.maigo.music.core.network"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.security)
    implementation(libs.okhttp)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.okhttp.mockwebserver)
}
