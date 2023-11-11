package com.tafreshiali

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidModulePlugins : Plugin<Project> {

    override fun apply(project: Project) {
        applyAndroidGradlePlugins(project)
    }

    private fun applyAndroidGradlePlugins(project: Project) {
        project.addNecessaryPlugins(
            pluginsList = listOf(
                "com.android.library",
                "org.jetbrains.kotlin.android",
                "org.jetbrains.kotlin.kapt",
                "dagger.hilt.android",
                "jetpack.navigation.safe.args-plugin",
                "org.jetbrains.koltin.parcelize.plugin"
            )
        )

    }
}