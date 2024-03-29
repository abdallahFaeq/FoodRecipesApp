plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.foodrecipeapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodrecipeapp"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures{
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // add dependencies
    // room DB
    val room_version = "2.6.1"

    implementation("androidx.room:room-ktx:$room_version")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")

    // kotlin coroutines dependency
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")

    // circle image
    implementation ("de.hdodenhof:circleimageview:3.1.0")

    // roundend image library
    implementation ("com.makeramen:roundedimageview:2.3.0")

    // scalable size unit
    implementation ("com.intuit.sdp:sdp-android:1.1.0")
    // scalable size unit for texts
    implementation ("com.intuit.ssp:ssp-android:1.1.0")

    // Retrofit & OkHttp
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    // logging interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // easy permission library
    implementation ("pub.devrel:easypermissions:3.0.0")

    // glide dependency
    implementation ("com.github.bumptech.glide:glide:4.16.0")

}

// circle image .. ssp ..sdp .. screen density .. crop image