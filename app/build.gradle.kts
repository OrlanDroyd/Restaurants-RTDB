@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.org.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.dsl)
}

android {
    namespace = "com.gmail.orlandroyd.restaurants_rtdb"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.gmail.orlandroyd.restaurants_rtdb"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    implementation(libs.appcompat)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.retrofit)
    implementation(libs.logging.interceptor)
    implementation(libs.gson)
    implementation(libs.converter.gson)
    implementation(libs.navigation.compose)
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)
    // DI
    implementation(libs.dagger.hilt.android)
    kapt(libs.dagger.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
    androidTestImplementation(libs.dagger.hilt.android.testing)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
    testImplementation(libs.kotlinx.coroutines.test)
}
