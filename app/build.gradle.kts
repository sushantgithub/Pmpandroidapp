plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.sushant.pmpstudy"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.sushant.pmpstudy"
        minSdk = 26
        targetSdk = 36
        versionCode = 32
        versionName = "2.8.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    signingConfigs {
        val storePath = providers.environmentVariable("ANDROID_KEYSTORE_PATH").orNull
        val storePasswordValue = providers.environmentVariable("ANDROID_KEYSTORE_PASSWORD").orNull
        val keyAliasValue = providers.environmentVariable("ANDROID_KEY_ALIAS").orNull
        val keyPasswordValue = providers.environmentVariable("ANDROID_KEY_PASSWORD").orNull

        if (
            !storePath.isNullOrBlank() &&
            !storePasswordValue.isNullOrBlank() &&
            !keyAliasValue.isNullOrBlank() &&
            !keyPasswordValue.isNullOrBlank()
        ) {
            create("release") {
                storeFile = file(storePath)
                storePassword = storePasswordValue
                keyAlias = keyAliasValue
                keyPassword = keyPasswordValue
            }
        }
    }

    val releaseSigningConfig = signingConfigs.findByName("release")

    buildTypes {
        release {
            isMinifyEnabled = true
            releaseSigningConfig?.let { signingConfig = it }
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.12.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    debugImplementation("androidx.compose.ui:ui-tooling")

    testImplementation("junit:junit:4.13.2")
}
