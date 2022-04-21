plugins {
    androidLibrary
    kotlinAndroid
}

android {
    compileSdk = appConfig.compileSdkVersion

    defaultConfig {
        minSdk = appConfig.minSdkVersion
        targetSdk = appConfig.targetSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        jvmTarget = appConfig.jvmVersion
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    //base
    implementation(deps.androidx.coreKtx)
    implementation(deps.androidx.appCompat)
    implementation(deps.androidx.material)

    //modules
    implementation(core)
    implementation(domain)

    //coroutines
    addCoroutines()

    //koin
    addKoin()

    //recycler view
    implementation(deps.androidx.recyclerView)

    //viewModel
    implementation(deps.lifecycle.viewModelKtx)

    //navigation
    implementation(deps.jetpackNavigation.uiNavigation)
    implementation(deps.jetpackNavigation.fragmentNavigation)
    implementation(deps.jetpackNavigation.moduleSupport)

    //logger
    implementation(deps.timber)
}