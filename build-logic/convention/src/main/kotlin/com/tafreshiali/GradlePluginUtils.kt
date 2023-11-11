package com.tafreshiali

import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

fun Project.android(): LibraryExtension =
    extensions.getByType(LibraryExtension::class.java)


fun Project.versionCatalog(): VersionCatalog =
    extensions.getByType<VersionCatalogsExtension>().named("libs")


fun addNecessaryLibraries(project: Project, dependecyName: String) {
    project.versionCatalog().findLibrary(dependecyName).ifPresent {
        project.dependencies {
            //https://stackoverflow.com/a/63068879
            //https://youtu.be/Z97sl7MrrzE?si=2iLJJQlqVFjZCTUe
        }
    }

}

fun Project.addNecessaryPlugins(pluginsList: List<String>) {
    val availdablePluginsList =
        versionCatalog().pluginAliases.filter { it in pluginsList.map { pluginName -> pluginName } }
    project.apply {
        availdablePluginsList.forEach {
            versionCatalog().findPlugin(it).ifPresent { vaildablPlugin ->
                println(" fuck plugin ${vaildablPlugin.get().pluginId}")
                plugin(vaildablPlugin.get().pluginId)
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

