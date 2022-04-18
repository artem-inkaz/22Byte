import java.io.File
import java.io.FileInputStream
import java.util.*

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

//       val prop = Properties().apply {
//           if (rootProject.file("newsapi.properties").exists()) {
//               load(FileInputStream(File(rootProject.rootDir, "newsapi.properties")))
//           }
//       }
//            buildConfigField(
//                "String",
//                "NEWS_API_KEY",
//                prop.getProperty("NEWS_API_KEY", "\"INVALID_API_KEY\"")
//            )
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
    implementation(project(mapOf("path" to ":app")))

    kapt(Hilt.android_compiler)

    //Room
    implementation(Room.runtime)
    implementation(Room.ktx)
    implementation(Room.roomCommon)
    kapt(Room.compiler)

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}