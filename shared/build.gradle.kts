
plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    
    jvm()

    sourceSets {
        val jvmMain by getting {
            dependencies {
                // Use the same version for all Ktor artifacts
                val ktorVersion = "2.3.8"

                // Ktor server core
                implementation("io.ktor:ktor-server-core:$ktorVersion")
                // For Netty engine
                implementation("io.ktor:ktor-server-netty:$ktorVersion")
                // For JSON serialization/deserialization
                // This has changed in Ktor 2.x, make sure to use the appropriate artifact
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                // For logging, if needed
                implementation("ch.qos.logback:logback-classic:1.2.6")
                // Any other Ktor or JVM dependencies you need
            }
        }
    }
}

android {
    namespace = "com.bitelingual.app.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
