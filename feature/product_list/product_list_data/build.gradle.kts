plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(project(":feature:product_list:product_list_domain"))

    implementation(libs.kotlinx.coroutines.android)
}