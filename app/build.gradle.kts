plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
}

android {
    namespace = "com.example.startprojeect"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.startprojeect"
        minSdk = 29
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
    implementation ("io.ktor:ktor-client-cio:2.3.8")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:2.3.0")
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("com.squareup.picasso:picasso:2.8")
    implementation ("com.github.barteksc:android-pdf-viewer:2.8.2")
    implementation ("com.github.dhaval2404:imagepicker-support:1.7.1")
    implementation("io.github.jan-tennert.supabase:storage-kt:2.3.0")
    implementation ("androidx.activity:activity-ktx:1.8.2")
    implementation ("androidx.fragment:fragment-ktx:1.6.2")
    implementation ("org.osmdroid:osmdroid-android:6.1.18")
    implementation("ru.egslava:MaskedEditText:1.0.5")
    implementation("com.google.android.gms:play-services-location:21.1.0")
    implementation ("com.yandex.android:maps.mobile:4.5.1-lite")
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.google.zxing:core:3.4.0")




    androidTestImplementation ("com.android.support.test:rules:1.0.2")
    debugImplementation("androidx.fragment:fragment-testing:1.7.0")
    androidTestImplementation("androidx.test:runner:1.5.2")
    androidTestImplementation("androidx.test:rules:1.5.0")
    androidTestImplementation("androidx.navigation:navigation-testing:2.7.7")




    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}