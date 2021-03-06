@file:Suppress("unused", "ClassName", "SpellCheckingInspection")

import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

object appConfig {

    const val jvmVersion = "1.8"
    const val compileSdkVersion = 32
    const val buildToolsVersion = "31.0.0"

    const val applicationId = "kg.ruslan.photosapp"

    const val minSdkVersion = 21
    const val targetSdkVersion = 32

    private const val MAJOR = 2
    private const val MINOR = 0
    private const val PATCH = 0
    const val versionCode = MAJOR * 10000 + MINOR * 100 + PATCH
    const val versionName = "$MAJOR.$MINOR.$PATCH"
}

object deps {
    object androidx {
        const val appCompat = "androidx.appcompat:appcompat:1.3.1"
        const val coreKtx = "androidx.core:core-ktx:1.7.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.3"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.1"
        const val material = "com.google.android.material:material:1.5.0"
    }

    object lifecycle {
        private const val version = "2.4.0"

        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
    }

    object coroutines {
        private const val version = "1.5.2"
        const val coroutinePlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.1.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
    }

    object jetpackNavigation {
        private const val version = "2.4.2"

        const val fragmentNavigation = "androidx.navigation:navigation-fragment-ktx:$version"
        const val uiNavigation = "androidx.navigation:navigation-ui-ktx:$version"
        const val moduleSupport = "androidx.navigation:navigation-dynamic-features-fragment:$version"
    }

    object koin {
        private const val version = "3.1.3"

        const val core = "io.insert-koin:koin-core:$version"
        const val android = "io.insert-koin:koin-android:$version"
        const val testJunit4 = "io.insert-koin:koin-test-junit4:$version"
    }

    const val gson = "com.google.code.gson:gson:2.8.6"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.31"
    const val flowExt = "io.github.hoc081098:FlowExt:0.1.0"
    const val glide = "com.github.bumptech.glide:glide:4.12.0"
    const val timber = "com.jakewharton.timber:timber:5.0.1"
}

private typealias PDsS = PluginDependenciesSpec
private typealias PDS = PluginDependencySpec

//plugins
inline val PDsS.androidApplication: PDS get() = id("com.android.application")
inline val PDsS.kotlinAndroid: PDS get() = id("kotlin-android")
inline val PDsS.kotlinKapt: PDS get() = id("kotlin-kapt")
inline val PDsS.kotlinParcelize: PDS get() = id("kotlin-parcelize")
inline val PDsS.androidLibrary: PDS get() = id("com.android.library")
inline val PDsS.kotlin: PDS get() = id("kotlin")
inline val PDsS.googleServices: PDS get() = id("com.google.gms.google-services")
inline val PDsS.firebaseCrashlitics:PDS get() = id("com.google.firebase.crashlytics")

//modules
inline val DependencyHandler.core get() = project(":core")
inline val DependencyHandler.domain get() = project(":domain")
inline val DependencyHandler.data get() = project(":data")
inline val DependencyHandler.featureGreeting get() = project(":feature-greeting")
inline val DependencyHandler.featurePhotos get() = project(":feature-photos")

//extansions
fun DependencyHandler.addKoin(wihtinTest: Boolean = false) {
    val configName = "implementation"

    add(configName, deps.koin.core)
    add(configName, deps.koin.android)
    if (wihtinTest) add(configName, deps.koin.testJunit4)
}

fun DependencyHandler.addCoroutines() {
    val configName = "implementation"

    add(configName, deps.coroutines.core)
    add(configName, deps.coroutines.android)
    add(configName, deps.coroutines.coroutinePlayServices)
}

fun ExternalModuleDependency.exclude(group: String, module: String) {
    exclude(
        mapOf(
            "group" to group,
            "module" to module
        )
    )
}
