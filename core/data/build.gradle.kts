plugins {
    alias(libs.plugins.maigo.android.library)
    alias(libs.plugins.maigo.android.hilt)
}

android {
    namespace = "party.maigo.music.core.data"
}

dependencies {
    api(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.security)
    implementation(projects.core.database)
    implementation(projects.core.network)
    implementation(projects.core.media)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.hilt.work)
}
