import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    kotlin("kapt")
}

android {
    namespace = "com.alikamran.newsapp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.alikamran.newsapp"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "API_KEY", "\"babe68289b82413faa4b295348a54dcf\"")
        buildConfigField("String", "BASE_URL", "\"https://newsapi.org/\"")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")



    val lifecycle_version = "2.6.1"
    val arch_version = "2.2.0"
    val room_version = "2.5.2"

    val nav_version = "2.6.0"



    // Kotlin
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    // Feature module Support
    implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

    // Testing Navigation
    androidTestImplementation("androidx.navigation:navigation-testing:$nav_version")





    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")


    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    implementation ("com.google.dagger:dagger:2.46.1")
    kapt("com.google.dagger:dagger-compiler:2.46.1")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.7.2")

    implementation ("com.github.bumptech.glide:glide:4.15.1")


    testImplementation( "androidx.arch.core:core-testing:$arch_version")
    androidTestImplementation ("androidx.arch.core:core-testing:$arch_version")
    testImplementation ("androidx.arch.core:core-testing:$arch_version")

    testImplementation ("com.google.truth:truth:1.1")
    androidTestImplementation ("com.google.truth:truth:1.1")
    testImplementation ("com.google.truth:truth:1.1")
    testImplementation ("org.mockito:mockito-core:2.28.2")


    testImplementation ("org.robolectric:robolectric:4.10.3")

    testImplementation("com.squareup.okhttp3:mockwebserver:4.11.0")

    testImplementation ("com.google.truth:truth:1.1.4")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")



    implementation ("com.intuit.sdp:sdp-android:1.1.0")
//    //hilt

    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
}
kapt {
    correctErrorTypes = true
}