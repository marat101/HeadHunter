plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "ru.marat.headhunter"
    compileSdk = (project.ext.get("compileSdk") as String).toInt()

    defaultConfig {
        applicationId = "ru.marat.headhunter"
        minSdk = (project.ext.get("minSdk") as String).toInt()
        targetSdk = (project.ext.get("targetSdk") as String).toInt()
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:${project.ext.get("androidxCore")}")
    implementation("androidx.appcompat:appcompat:${project.ext.get("androidxAppcompat")}")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.dagger:dagger:${project.ext.get("dagger")}")
    kapt("com.google.dagger:dagger-compiler:${project.ext.get("dagger")}")
}