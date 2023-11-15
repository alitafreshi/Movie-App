package com.tafreshiali

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler

class AndroidComposeDependencies : Plugin<Project> {
    override fun apply(project: Project) {
        project.findLibrary(dependencyName = "compose.bom")
    }


    private fun DependencyHandler.compose(project: Project) {
        project.findLibrary(dependencyName = "compose.bom")
    }

}