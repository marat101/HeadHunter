plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    kotlin("plugin.serialization")
}

android {
    namespace = "ru.marat.headhunter"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.marat.headhunter"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":core_navigation"))
    implementation(project(":core_network:api"))
    implementation(project(":core_network:impl"))
    implementation(project(":core_view"))

    implementation(project(":feature_profile:api"))
    implementation(project(":feature_profile:impl"))
    implementation(project(":feature_search:api"))
    implementation(project(":feature_search:impl"))
    implementation(project(":feature_home:api"))
    implementation(project(":feature_home:impl"))
    implementation(project(":feature_root"))

    implementation("androidx.core:core-ktx:${project.ext.get("androidxCore")}")
    implementation("androidx.appcompat:appcompat:${project.ext.get("androidxAppcompat")}")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Dagger
    implementation("com.google.dagger:dagger:${project.ext.get("dagger")}")
    kapt("com.google.dagger:dagger-compiler:${project.ext.get("dagger")}")

    // Ktor
    implementation("io.ktor:ktor-client-core:2.3.5")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${project.ext.get("kotlinSerialization")}")
}