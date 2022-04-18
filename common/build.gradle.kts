plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
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

    buildFeatures {
        android.buildFeatures.viewBinding = true
        android.buildFeatures.dataBinding = true
        buildConfig = true
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

    implementation(AndroidX.ktx_core)
    implementation(AndroidX.appCompat)
    implementation(Google.material)

    // Activity KTX
    implementation(AndroidX.ktx_activity)
    implementation(AndroidX.ktx_fragment)

    implementation(Lifecycle.lifeCycleRunTime)

    //Binding
    implementation(ViewBinding.viewbinding)

    //Dagger - Hilt
    implementation(Hilt.android)
    kapt(Hilt.android_compiler)

    //Test
    testImplementation (Test.junit)
    androidTestImplementation (Test.junit_ext)
    androidTestImplementation (Espresso.core)
}