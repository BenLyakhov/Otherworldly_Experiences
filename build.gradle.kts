// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

//ext {
//    roomVersion='2.6.1'
//}

extra["roomVersion"] = "2.6.1"
buildscript {
    // Define your roomVersion here if it's within the buildscript block
    extra["roomVersion"] = "2.6.1"
}
// Define extra properties for all subprojects
allprojects {
    extra["roomVersion"] = "2.6.1"
}