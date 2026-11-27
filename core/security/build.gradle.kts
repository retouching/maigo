plugins {
    alias(libs.plugins.maigo.android.library)
    alias(libs.plugins.maigo.android.hilt)
}

android {
    namespace = "party.maigo.music.core.security"
}

dependencies {
    api(projects.core.common)
    implementation(libs.androidx.datastore.preferences)
}
