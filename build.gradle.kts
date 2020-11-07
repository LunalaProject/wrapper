plugins {
    `maven-publish`
    kotlin("multiplatform") version "1.4.10"
    kotlin("plugin.serialization") version "1.4.10"
}

group = "com.gabriel.lunala.project"
version = "1.0"

plugins.apply("maven-publish")

repositories {
    mavenCentral()
}
kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }
    js {
        browser {
            testTask {
                useKarma {
                    useChromeHeadless()
                    webpackConfig.cssSupport.enabled = true
                }
            }
        }
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    
    sourceSets {
        val commonMain by getting {
            repositories {
                mavenCentral()
                jcenter()
            }
            dependencies {
                implementation("io.ktor:ktor-client-core:1.4.1")
                implementation("io.ktor:ktor-client-core:1.4.1")
                implementation("io.ktor:ktor-client-serialization:1.4.1")
                implementation("io.github.microutils:kotlin-logging:1.12.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.0-RC2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio-jvm:1.4.1")
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation("org.junit.jupiter:junit-jupiter:5.4.2")
                runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
            }
        }
        val jsMain by getting
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
        val nativeMain by getting
        val nativeTest by getting
    }
}