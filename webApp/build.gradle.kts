import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.jetbrains.plugin.compose)
    alias(libs.plugins.jetbrains.compose)
}


kotlin {

    js(IR) {
        outputModuleName.set("webApp")
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "frontend.js"
                cssSupport {
                    enabled = true
                }
                scssSupport {
                    enabled = true
                }
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
            binaries.executable()
        }
    }

    sourceSets {

        val jsMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(compose.html.core)
                implementation(compose.runtime)
                implementation(libs.routing.compose)
                implementation(libs.koin.core)
            }
        }
    }
}