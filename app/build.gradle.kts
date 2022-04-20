plugins {
    id("org.jetbrains.kotlin.android")
    androidApplication
    kotlinAndroid
}

android {
    compileSdk = appConfig.compileSdkVersion
    buildToolsVersion = appConfig.buildToolsVersion

    defaultConfig {
        applicationId = appConfig.applicationId
        minSdk = appConfig.minSdkVersion
        targetSdk = appConfig.targetSdkVersion
        versionCode = appConfig.versionCode
        versionName = appConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            applicationIdSuffix = ".debug"
        }
        getByName("release") {
            isDebuggable = false
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

    kotlinOptions { jvmTarget = JavaVersion.VERSION_1_8.toString() }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //koin
    addKoin()

    //navigation
    implementation(deps.jetpackNavigation.uiNavigation)
    implementation(deps.jetpackNavigation.fragmentNavigation)
    implementation(deps.jetpackNavigation.moduleSupport)

    //modules
    implementation(core)
    implementation(domain)
    implementation(data)
    implementation(featureGreeting)
}