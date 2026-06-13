import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.detekt) apply false
}

subprojects {
    plugins.withId("io.gitlab.arturbosch.detekt") {

        tasks.withType<Detekt>().configureEach {
            reports {
                html.required.set(true)
                xml.required.set(true)
                sarif.required.set(true)
            }
        }
    }
}
