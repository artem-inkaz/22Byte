import org.gradle.api.JavaVersion

object Modules {
    const val logging = ":logging"
    const val data = ":data"
    const val domain = ":domain"
}

object Config {
    const val APPLICATION_ID = "ui.smartpro.a22byte"
    const val COMPILE_SDK = 32
    const val MIN_SDK_VERSION = 22
    const val TARGET_SDK = 32
    const val VERSION_CODE = 1
    const val VERSION_NAME = "1.0"
    const val JVM_TARGET = "1.8"
    val java_version = JavaVersion.VERSION_1_8
    const val kotlinVersion = "1.6.10"
    const val gradle_plugin = "7.1.0"
    const val build_plugin = "3.4.0"
}

object LibVersion {
    const val ktx_core = "1.7.0"
    const val appCompatVersion = "1.4.1"
    const val material = "1.5.0"
    const val constraint_layout = "2.1.3"
    const val recyclerview = "1.2.1"
    const val lifecycle = "2.5.0-alpha06"
    const val navigationVersion = "2.4.2"
    const val ktx_fragment = "1.4.1"
    const val ktx_activity = "1.4.0"
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.0"
    const val coroutinesVersion = "1.4.2"
    const val coroutinesAdapter = "0.9.2"
    const val annotation = "1.1.0"
    const val roomVersion = "2.3.0-alpha04"
    const val roomCommon = "2.4.2"
    const val roomPaging = "2.4.0-alpha04"
    const val hiltVersion = "2.41"
    const val glide = "4.11.0"
    const val coil = "0.11.0"
    const val swipeRefreshVersion = "1.1.0"
    const val pagingVersion = "3.0.0"
    const val viewbinding = "1.4.6"
    const val timber = "5.0.1"
    const val fabric = "1.30.0"
    const val firebase = "29.2.1"
    const val firebase_crashlytics_gradle = "2.8.1"
    const val google_services = "4.3.10"
    const val firebase_crashlytics_ktx = "18.2.9"
    const val firebase_analytics_ktx = "20.1.2"

}

object Testing {
    const val junit = "4.13.2"
    const val android_test = "1.3.0"
    const val espresso = "3.4.0"
    const val android_test_junit = "1.1.3"
    const val arch_core_testing = "2.1.0"
    const val mockito = "3.7.7"
    const val mockitoInline = "3.2.4"
    const val mockitoKotlin = "2.2.0"
    const val robolectric = "4.6.1"
    const val mockwebServer = "4.7.2"
    const val dexmaker_version = "2.28.1"
    const val fragment_test_version = "1.3.0-alpha08"
    const val truth = "1.0.1"
}

object Dep {
    const val gradle_plugin = "com.android.tools.build:gradle:${Config.build_plugin}"
}

object Kotlin {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Config.kotlinVersion}"
    const val gradle_plugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Config.kotlinVersion}"
}

object Google {
    const val material = "com.google.android.material:material:${LibVersion.material}"
}

object Legacy{
    const val legacy_support = "androidx.legacy:legacy-support-v4:1.0.0"
    const val legacy_support_core = "androidx.legacy:legacy-support-core-utils:1.0.0"
}

object Coroutines {
    const val core =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${LibVersion.coroutinesVersion}"
    const val android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibVersion.coroutinesVersion}"
    const val test =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${LibVersion.coroutinesVersion}"
}

object AndroidX {
    const val appCompat = "androidx.appcompat:appcompat:${LibVersion.appCompatVersion}"
    const val constraint_layout =
        "androidx.constraintlayout:constraintlayout:${LibVersion.constraint_layout}"
    const val ktx_core = "androidx.core:core-ktx:${LibVersion.ktx_core}"
    const val ktx_fragment = "androidx.fragment:fragment-ktx:${LibVersion.ktx_fragment}"
    const val ktx_activity = "androidx.activity:activity-ktx:${LibVersion.ktx_activity}"
    const val annotation = "androidx.annotation:annotation:${LibVersion.annotation}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${LibVersion.recyclerview}"
    const val SwipeRefreshLayout =
        "androidx.swiperefreshlayout:swiperefreshlayout:${LibVersion.swipeRefreshVersion}"

}

object Lifecycle {
//    const val extensions = "androidx.lifecycle:lifecycle-extensions:${LibVersion.lifecycle}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibVersion.lifecycle}"
    const val compiler = "androidx.lifecycle:lifecycle-common-java8:${LibVersion.lifecycle}"
    const val lifeCycleLiveData =
        "androidx.lifecycle:lifecycle-livedata-ktx:${LibVersion.lifecycle}"
    const val lifeCycleRunTime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${LibVersion.lifecycle}"
    const val lifeCycleViewModelState ="androidx.lifecycle:lifecycle-viewmodel-savedstate:${LibVersion.lifecycle}"
}

object Room {
    const val runtime = "androidx.room:room-runtime:${LibVersion.roomVersion}"
    const val compiler = "androidx.room:room-compiler:${LibVersion.roomVersion}"
    const val ktx = "androidx.room:room-ktx:${LibVersion.roomVersion}"
    const val testing = "androidx.room:room-testing:${LibVersion.roomVersion}"
    const val roomPaging = "androidx.room:room-paging:${LibVersion.roomPaging}"
    const val roomCommon =
        "androidx.room:room-common:${LibVersion.roomCommon}"
}

object Navigation {
    const val safeArgs_gradle =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${LibVersion.navigationVersion}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${LibVersion.navigationVersion}"
    const val navigationKtx =
        "androidx.navigation:navigation-ui-ktx:${LibVersion.navigationVersion}"

}

object Hilt {
    const val viewmodel = "androidx.hilt:hilt-lifecycle-viewmodel:${LibVersion.hiltVersion}"
    const val compiler = "androidx.hilt:hilt-compiler:${LibVersion.hiltVersion}"
    const val android = "com.google.dagger:hilt-android:${LibVersion.hiltVersion}"
    const val android_compiler =
        "com.google.dagger:hilt-android-compiler:${LibVersion.hiltVersion}"
    const val gradlePlugin =
        "com.google.dagger:hilt-android-gradle-plugin:${LibVersion.hiltVersion}"
    const val dagger = "com.google.dagger:dagger:${LibVersion.hiltVersion}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${LibVersion.hiltVersion}"
    const val dagger_android = "com.google.dagger:dagger-android:${LibVersion.hiltVersion}"
    const val dagger_android_support = "com.google.dagger:dagger-android-support:${LibVersion.hiltVersion}"
    const val dagger_android_processor = "com.google.dagger:dagger-android-processor:${LibVersion.hiltVersion}"
}

object Analytics{
    const val google_services = "com.google.gms:google-services:${LibVersion.google_services}"
    const val gradle_firebase =
        "com.google.firebase:firebase-crashlytics-gradle:${LibVersion.firebase_crashlytics_gradle}"
    const val firebase = "com.google.firebase:firebase-bom:${LibVersion.firebase}"
    const val firebase_crashlytics_ktx = "com.google.firebase:firebase-crashlytics-ktx:${LibVersion.firebase_crashlytics_ktx}"
    const val firebase_analytics_ktx = "com.google.firebase:firebase-analytics-ktx:${LibVersion.firebase_analytics_ktx}"
}

object Retrofit {
    const val main = "com.squareup.retrofit2:retrofit:${LibVersion.retrofit}"
    const val converterGSON = "com.squareup.retrofit2:converter-gson:${LibVersion.retrofit}"
    const val coroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${LibVersion.coroutinesAdapter}"
}

object OkHttp {
    const val main = "com.squareup.okhttp3:okhttp:${LibVersion.okhttp}"
    const val logging_interceptor =
        "com.squareup.okhttp3:logging-interceptor:${LibVersion.okhttp}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Testing.mockwebServer}"
}

object Glide {
    const val runtime = "com.github.bumptech.glide:glide:${LibVersion.glide}"
    const val compiler = "com.github.bumptech.glide:compiler:${LibVersion.glide}"
    const val okhttp_integration =
        "com.github.bumptech.glide:okhttp3-integration:${LibVersion.glide}"
}

object Coil{
    const val coil = "io.coil-kt:coil:${LibVersion.coil}"
}

object Paging{
    const val paging = "androidx.paging:paging-runtime-ktx:${LibVersion.pagingVersion}"
}

object Timber {
    const val timber = "com.jakewharton.timber:timber:${LibVersion.timber}"
}

object ViewBinding {
    const val viewbinding =
        "com.github.kirich1409:viewbindingpropertydelegate:${LibVersion.viewbinding}"
}

object Test {
    const val junit = "junit:junit:${Testing.junit}"
    const val core = "androidx.test:core:${Testing.android_test}"
    const val rules = "androidx.test:rules:${Testing.android_test}"
    const val runner = "androidx.test:runner:${Testing.android_test}"
    const val junit_ext = "androidx.test.ext:junit:${Testing.android_test_junit}"
    const val junitKtx = "androidx.test.ext:junit-ktx:${Testing.android_test_junit}"
    const val coreKtx = "androidx.test:core-ktx:${Testing.android_test}"
    const val fragmentTest =
        "androidx.fragment:fragment-testing:${Testing.fragment_test_version}"
    const val arch_core_testing =
        "androidx.arch.core:core-testing:${Testing.arch_core_testing}"
    const val truth = "com.google.truth:truth:${Testing.truth}"
    const val robolectric = "org.robolectric:robolectric:${Testing.robolectric}"
}

object Espresso {
    const val core = "androidx.test.espresso:espresso-core:${Testing.espresso}"
    const val contrib = "androidx.test.espresso:espresso-contrib:${Testing.espresso}"
    const val idling_resource =
        "androidx.test.espresso:espresso-idling-resource:${Testing.espresso}"
    const val intents =
        "androidx.test.espresso:espresso-intents:${Testing.espresso}"
}

object Mockito {
    const val core = "org.mockito:mockito-core:${Testing.mockito}"
    const val android = "org.mockito:mockito-android:${Testing.mockito}"
    const val inline = "org.mockito:mockito-inline:${Testing.mockitoInline}"
    const val kotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Testing.mockitoKotlin}"
    const val dexMaker =
        "com.linkedin.dexmaker:dexmaker-mockito-inline:${Testing.dexmaker_version}"
}