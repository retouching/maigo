plugins {
    alias(libs.plugins.maigo.android.library)
    alias(libs.plugins.maigo.android.compose)
}

android {
    namespace = "party.maigo.music.core.designsystem"
}

dependencies {
    api(projects.core.model)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
}
