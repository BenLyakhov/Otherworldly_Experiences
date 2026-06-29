import org.gradle.kotlin.dsl.androidTestImplementation
import org.gradle.kotlin.dsl.annotationProcessor
import org.gradle.kotlin.dsl.implementation

plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.oe"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.example.vacations"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.recyclerview)
    implementation("com.kizitonwose.calendar:view:2.6.0")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.5")
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
//    Room Components
//    These below are not working/not recognized in the current versions of the IDE.
//    after these should be the correct ones that I got from reddit page for D308

//    WRONG (what is in video 2 in panopto):
//    implementation "androidx.room:room-runtime:$rootProject.roomVersion"
//    annotationProcessor "androidx.room:room-compiler:$rootProject.roomVersion"
//    androidTestImplementation "androidx.room:room-testing:$rootProject.roomVersion"

//    CORRECT (From reddit):
    implementation("androidx.room:room-runtime:${rootProject.extra.get("roomVersion")}")
    annotationProcessor("androidx.room:room-compiler:${rootProject.extra.get("roomVersion")}")
    androidTestImplementation("androidx.room:room-testing:${rootProject.extra.get("roomVersion")}")
}