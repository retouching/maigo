import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.tasks.compile.JavaCompile
import org.gradle.jvm.toolchain.JavaLanguageVersion
import org.gradle.jvm.toolchain.JavaToolchainService
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

class MaigoAndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("maigo.quality")

            extensions.configure<JavaPluginExtension> {
                toolchain {
                    languageVersion.set(JavaLanguageVersion.of(21))
                }
            }

            val toolchains = extensions.getByType<JavaToolchainService>()
            tasks.withType<JavaCompile>().configureEach {
                javaCompiler.set(
                    toolchains.compilerFor {
                        languageVersion.set(JavaLanguageVersion.of(21))
                    },
                )
            }

            extensions.configure<LibraryExtension> {
                compileSdk {
                    version = release(37) {
                        minorApiLevel = 1
                    }
                }

                defaultConfig {
                    minSdk = 26
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_21
                    targetCompatibility = JavaVersion.VERSION_21
                }

                lint {
                    abortOnError = true
                    warningsAsErrors = false
                    lintConfig = rootProject.file("lint.xml")
                }

                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                        all {
                            it.useJUnitPlatform()
                            it.failOnNoDiscoveredTests.set(false)
                        }
                    }
                }
            }

            extensions.findByType(KotlinAndroidProjectExtension::class.java)?.apply {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_21)
                    allWarningsAsErrors.set(true)
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                add("testImplementation", platform(libs.findLibrary("junit-bom").get()))
                add("testImplementation", libs.findLibrary("junit-jupiter").get())
                add("testRuntimeOnly", libs.findLibrary("junit-platform-launcher").get())
                add("androidTestImplementation", libs.findLibrary("androidx-test-runner").get())
                add("androidTestImplementation", libs.findLibrary("androidx-test-ext-junit").get())
            }
        }
    }
}
