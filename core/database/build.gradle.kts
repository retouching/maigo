plugins {
    alias(libs.plugins.maigo.android.library)
    alias(libs.plugins.maigo.android.hilt)
}

android {
    namespace = "party.maigo.music.core.database"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.security)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)
    implementation(libs.sqlcipher.android)
    implementation(libs.androidx.sqlite.ktx)
    ksp(libs.androidx.room.compiler)
    testImplementation(libs.androidx.room.testing)
    testImplementation(libs.robolectric)
}
