package com.tafreshiali

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

fun Project.android(): LibraryExtension =
    extensions.getByType(LibraryExtension::class.java)


fun Project.versionCatalog(): VersionCatalog =
    extensions.getByType<VersionCatalogsExtension>().named("libs")

//https://stackoverflow.com/a/63068879
//https://youtu.be/Z97sl7MrrzE?si=2iLJJQlqVFjZCTUe
fun Project.findLibrary(dependencyName: String) {
    versionCatalog().findLibrary(dependencyName).ifPresent {
        val foundDependency = it.orNull
        if (foundDependency != null) {
            println("REQUESTED_LIBRARY is $dependencyName ")
            println("REQUESTED_LIBRARY module is ${foundDependency.module} ")
            println("REQUESTED_LIBRARY artifacts is ${foundDependency.artifacts} ")
            println("REQUESTED_LIBRARY group is ${foundDependency.group} ")
            println("REQUESTED_LIBRARY version is ${foundDependency.version} ")
            return@ifPresent
        }
        println("REQUESTED_LIBRARY $dependencyName Didn't find in the versionCatalog")
    }
}

fun Project.findBundle(bundleName: String) {
    versionCatalog().findBundle(bundleName).ifPresent {
        val foundBundle = it.orNull
        if (foundBundle != null) {
            println("REQUESTED_BUNDLE is $foundBundle ")
            return@ifPresent
        }
        println("REQUESTED_BUNDLE $bundleName Didn't find in the versionCatalog")
    }
}

fun Project.addNecessaryPlugins(pluginsList: List<String>) {
    val availablePluginsList =
        versionCatalog().pluginAliases.filter { it in pluginsList.map { pluginName -> pluginName } }
    project.apply {
        availablePluginsList.forEach {
            versionCatalog().findPlugin(it).ifPresent { validPlugin ->
                println(" fuck plugin ${validPlugin.get().pluginId}")
                plugin(validPlugin.get().pluginId)
            }
        }
    }

}

fun Project.getVersionProperty(versionProperty: String): String {
    var propertyValue = ""
    versionCatalog().findVersion(versionProperty).ifPresent {
        propertyValue = it.displayName
    }
    return propertyValue
}

