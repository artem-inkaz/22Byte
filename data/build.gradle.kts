plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
    id ("kotlin-parcelize")
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 22
        targetSdk = 32
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Retrofit
    implementation(Retrofit.main)
    implementation(Retrofit.converterGSON)
    implementation(Retrofit.coroutinesAdapter)

    // OkHTTP
    implementation(OkHttp.main)
    implementation(OkHttp.logging_interceptor)

    // Coroutines
    implementation(Coroutines.core)
    implementation(Coroutines.android)

    //Dagger - Hilt
    implementation(Hilt.android)
    kapt(Hilt.android_compiler)

    //Room
    implementation(Room.runtime)
    implementation(Room.ktx)
    implementation(Room.roomCommon)
    kapt(Room.compiler)

    implementation(AndroidX.ktx_core)
    implementation(AndroidX.appCompat)
    implementation(Google.material)
    //Test
    testImplementation (Test.junit)
    androidTestImplementation (Test.junit_ext)
    androidTestImplementation (Espresso.core)

    implementation(project(":common"))
}