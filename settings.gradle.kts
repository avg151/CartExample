pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CartExample"
include(":app")

include(":core:network")
include(":core:design_system")

include(":core:navigation:navigation_api")
include(":core:navigation:navigation_impl")

include(":feature:shared:cart_widgets")

include(":feature:product_list:product_list_di")
include(":feature:product_list:product_list_data")
include(":feature:product_list:product_list_domain")
include(":feature:product_list:product_list_presentation")

include(":feature:product_detail:product_detail_data")
include(":feature:product_detail:product_detail_domain")
include(":feature:product_detail:product_detail_presentation")

include(":feature:cart:cart_data")
include(":feature:cart:cart_domain")
include(":feature:cart:cart_presentation")
include(":feature:cart:cart_di")