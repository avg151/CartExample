plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.cart_data"

    compileSdk = 36
    defaultConfig { minSdk = 24 }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Связь по Clean Architecture: data -> domain
//    implementation(project(":feature:cart:cart_domain"))

//    implementation(libs.kotlinx.coroutines.android)
}