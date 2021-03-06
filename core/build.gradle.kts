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
    implementation(deps.androidx.constraintLayout)
    implementation(deps.androidx.coreKtx)
    implementation(deps.androidx.appCompat)
    implementation(deps.androidx.material)

    //coroutines
    addCoroutines()

    //koin
    addKoin()

    //lifecycle
    implementation(deps.lifecycle.runtimeKtx)

    //for loading photos
    implementation(deps.glide)
}