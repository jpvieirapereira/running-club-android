import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.android.library) apply false
}

subprojects {
    pluginManager.withPlugin("org.jetbrains.kotlin.android"){
        configure<KotlinAndroidProjectExtension> {
            jvmToolchain {
                languageVersion.set(JavaLanguageVersion.of(21))
            }
        }
    }
    afterEvaluate {
        if (project.hasProperty("android")) {
            extensions.configure<com.android.build.gradle.BaseExtension>("android") {
                defaultConfig {
                    compileSdkVersion(36)
                    minSdk = 26
                    targetSdk = 36
                    versionCode = 1
                    versionName = "1.0"
                }
            }
        }
    }
}