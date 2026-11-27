plugins {
    alias(libs.plugins.maigo.android.library)
    alias(libs.plugins.maigo.android.compose)
    alias(libs.plugins.maigo.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "party.maigo.music.feature.player"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.designsystem)
    implementation(projects.core.media)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.kotlinx.collections.immutable)
    testImplementation(projects.core.testing)
}
