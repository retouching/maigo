import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "party.maigo.music.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_21)
    }
}

dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
    compileOnly(libs.compose.gradle.plugin)
    compileOnly(libs.ksp.gradle.plugin)
    compileOnly(libs.hilt.gradle.plugin)
    compileOnly(libs.detekt.gradle.plugin)
    compileOnly(libs.ktlint.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("maigoJvmLibrary") {
            id = "maigo.jvm.library"
            implementationClass = "MaigoJvmLibraryConventionPlugin"
        }
        register("maigoAndroidLibrary") {
            id = "maigo.android.library"
            implementationClass = "MaigoAndroidLibraryConventionPlugin"
        }
        register("maigoAndroidApplication") {
            id = "maigo.android.application"
            implementationClass = "MaigoAndroidApplicationConventionPlugin"
        }
        register("maigoAndroidCompose") {
            id = "maigo.android.compose"
            implementationClass = "MaigoAndroidComposeConventionPlugin"
        }
        register("maigoAndroidHilt") {
            id = "maigo.android.hilt"
            implementationClass = "MaigoAndroidHiltConventionPlugin"
        }
        register("maigoQuality") {
            id = "maigo.quality"
            implementationClass = "MaigoQualityConventionPlugin"
        }
    }
}
