import Plugins.kotlinAndroid
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.plugin.mpp.pm20.util.libsDirectory

class MainAndroidGradlePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        applyAndroidGradlePlugins(project)
        setProjectConfig(project)
    }

    private fun applyAndroidGradlePlugins(project: Project) {
        project.addNecessaryPlugins(
            pluginsList = listOf(
                "com.android.library",
                "org.jetbrains.kotlin.android",
                "org.jetbrains.kotlin.kapt",
                "com.google.dagger.hilt.android"
            )
        )
    }

    private fun setProjectConfig(project: Project) {
        project.android().apply {
            compileSdk = project.getVersionProperty("apllication.compileSdk").toInt()
            defaultConfig {
                minSdk = project.getVersionProperty("application.minsdk").toInt()
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFiles("consumer-rules.pro")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_17
                targetCompatibility = JavaVersion.VERSION_17
            }


            buildFeatures {
                compose = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion =
                    project.getVersionProperty("versions.compose.compiler")

            }
            packaging {
                resources {
                    excludes += "/META-INF/{AL2.0,LGPL2.1}"
                }
            }

        }
    }





    private fun Project.android(): LibraryExtension =
        extensions.getByType(LibraryExtension::class.java)


    private fun Project.versionCatalog(): VersionCatalog =
        extensions.getByType<VersionCatalogsExtension>().named("libs")


    private fun addNecessaryLibraries(project: Project, dependecyName: String) {
        project.versionCatalog().findLibrary(dependecyName).ifPresent {
            project.apply {
                //TODO CHECK THIS
            }
        }

    }

    private fun Project.addNecessaryPlugins(pluginsList: List<String>) {

        val availdablePluginsList =
            versionCatalog().pluginAliases.filter { it in pluginsList.map { pluginName -> pluginName } }
        project.apply {
            availdablePluginsList.forEach {
                versionCatalog().findPlugin(it).ifPresent { vaildablPlugin ->
                    plugin(vaildablPlugin.get().pluginId)
                }
            }
        }

    }

    private fun Project.getVersionProperty(versionProperty: String): String {
        var propertyValue = ""
        versionCatalog().findVersion(versionProperty).ifPresent {
            propertyValue = it.displayName
        }
        return propertyValue
    }


}