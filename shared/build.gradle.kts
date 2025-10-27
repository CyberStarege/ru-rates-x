plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {

    jvm()
    js(IR) {
        browser()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.serialization.json)
                implementation(libs.bundles.ktor.common)
                implementation(libs.koin.core)
                implementation(libs.koin.compose.viewmodel)
                implementation(libs.multiplatform.settings.no.arg)
                implementation(libs.lifecycle.runtime.compose)
                implementation(libs.lifecycle.viewmodel.compose)
            }
        }

        val commonTest by getting {
            dependencies {

            }
        }

        val jsMain by getting {
            dependencies {
                implementation(libs.ktor.client.js)

            }
        }
    }
}