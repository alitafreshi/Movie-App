@Suppress("DSL_SCOPE_VIOLATION")
repositories{
    google()
    mavenCentral()
    mavenLocal()
    gradlePluginPortal()
    maven(url = "https://jitpack.io")
}
plugins{
    `kotlin-dsl`
}