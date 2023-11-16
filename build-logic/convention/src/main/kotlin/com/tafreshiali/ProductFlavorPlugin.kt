package com.tafreshiali

import org.gradle.api.Plugin
import org.gradle.api.Project

class ProductFlavorPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.applyProductFlavors()
    }

    private fun Project.applyProductFlavors() {
        android().apply {
            flavorDimensions += "default"
            productFlavors {
                create("qa") {
                    dimension = "default"
                }
            }
        }
    }
}