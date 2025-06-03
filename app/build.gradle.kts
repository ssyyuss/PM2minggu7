plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.utsmobile"
    compileSdk = 35

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.example.utsmobile"
        minSdk = 24
        targetSdk = 35
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

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.junit.junit)
    implementation(libs.androidx.junit.ktx)
    val room_version = "2.6.1"

    // Room runtime & ktx
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")

    // Room Testing
    testImplementation("androidx.room:room-testing:$room_version")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // Core Testing for LiveData and coroutines (optional)
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    // Coroutine testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
}
