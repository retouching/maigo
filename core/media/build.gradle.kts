plugins {
    alias(libs.plugins.maigo.android.library)
    alias(libs.plugins.maigo.android.hilt)
}

android {
    namespace = "party.maigo.music.core.media"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.network)
    implementation(libs.androidx.media3.exoplayer)
    implementation(libs.androidx.media3.session)
    implementation(libs.androidx.media3.datasource.okhttp)
}
