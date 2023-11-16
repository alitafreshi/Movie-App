package com.tafreshiali

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidComposeSubModulesPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.subprojects()
    }

    private fun Project.subprojects() {
        subprojects {
            afterEvaluate {
                //detect compose modules
                if (isAndroidComposeModule()) {
                    println("SUBMODULE displayableName is $displayName ")
                        addNecessaryPlugins(listOf("android.module.project.config"))
                }
            }
        }
    }
}